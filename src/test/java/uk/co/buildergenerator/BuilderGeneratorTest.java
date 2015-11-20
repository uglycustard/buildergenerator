package uk.co.buildergenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.buildergenerator.BuilderGenerator.DEFAULT_OUTPUT_DIRECTORY;
import java.io.File;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import uk.co.buildergenerator.testmodel.BeanToBeIgnored;
import uk.co.buildergenerator.testmodel.BeanWithChildBeanToBeIgnored;
import uk.co.buildergenerator.testmodel.BeanWithNestedClass;
import uk.co.buildergenerator.testmodel.BeanWithNestedClassProperty;
import uk.co.buildergenerator.testmodel.BeanWithPropertyToIgnore;
import uk.co.buildergenerator.testmodel.NodeOne;
import uk.co.buildergenerator.testmodel.NodeThree;
import uk.co.buildergenerator.testmodel.NodeTwo;
import uk.co.buildergenerator.testmodel.Root;
import uk.co.buildergenerator.testmodel.StringPropertyBeanWithSomethingElse;

public class BuilderGeneratorTest {

	private BuilderGenerator testee;
	
	@Mock
    private BuilderWriter builderWriter;
	
	@Mock
	private FileUtils fileUtils;
	
	@Captor
	private ArgumentCaptor<BuilderTemplateMap> builderTemplateMapCaptor;
	
	@Captor
	private ArgumentCaptor<File> outputDirectoryCaptor;
	
	@Captor
	private ArgumentCaptor<String> generationGapBaseBuilderPackageCaptor;

	@Captor
	private ArgumentCaptor<File> generationGapBaseBuilderOutputDirectoryCaptor;

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
    public void setup() throws Exception {
	    initMocks(this);
    }

	@Test
	public void rootClass() {
		testee = new BuilderGenerator(Root.class);
		assertEquals(Root.class, testee.getRootClass());
	}
	
	@Test
	public void defaultBuilderPackage() throws Exception {
		testee = new BuilderGenerator(Root.class);
		assertEquals(Root.class.getPackage().getName() + "builder", testee.getBuilderPackage());
	}
	
	@Test
	public void defaultOutputDirectory() throws Exception {
		
		testee = new BuilderGenerator(Root.class);
		assertEquals(BuilderGenerator.DEFAULT_OUTPUT_DIRECTORY, testee.getOutputDirectory());
	}

	@Test
	public void defaultBuilderPackageCanBeOverriden() throws Exception {
		
		testee = new BuilderGenerator(Root.class);
		String builderPackage = "a.new.package";
		testee.setBuilderPackage(builderPackage);
		assertEquals(builderPackage, testee.getBuilderPackage());
	}
	
	@Test
	public void defaultOutputDirectoryCanBeOverriden() throws Exception {
		
		testee = new BuilderGenerator(Root.class);
		String outputDirectory = "a/new/directory";
		testee.setOutputDirectory(outputDirectory);
		assertEquals(outputDirectory, testee.getOutputDirectory());
	}
	
	@Test
    public void generateBuildersForSingleNodeGraphInDefaultOutputDirectory() throws Exception {
        
	    File file = mockExisitingOutputDirectoryFile(DEFAULT_OUTPUT_DIRECTORY);
	    
        testee = new BuilderGenerator(NodeThree.class, builderWriter, fileUtils);
        testee.generateBuilders();
        assertBuilderTemplateMapsCreatedForClasses(NodeThree.class);
        assertOutputDirectoryForAllBuilders(file);
        assertBuilderInterfaceGenerated(file, testee.getBuilderPackage());
    }
	
    @Test
	public void generateBuildersForSingleNodeGraphInSpecifiedOutputDirectory() throws Exception {

	    String outputDir = "another" + File.separator + "directory";
        File file = mockExisitingOutputDirectoryFile(outputDir);

        testee = new BuilderGenerator(NodeThree.class, builderWriter, fileUtils);
        testee.setOutputDirectory(outputDir);
	    testee.generateBuilders();
	    assertBuilderTemplateMapsCreatedForClasses(NodeThree.class);
	    assertOutputDirectoryForAllBuilders(file);
	    assertBuilderInterfaceGenerated(file, testee.getBuilderPackage());
	}

	@Test
	public void generateBuildersForSingleNodeGraphInSpecifiedOutputDirectoryThatDoesNotExist() throws Exception {

	    String outputDir = "another" + File.separator + "directory";
	    File file = mockNonExisitingOutputDirectoryFile(outputDir);

	    testee = new BuilderGenerator(NodeThree.class, builderWriter, fileUtils);
	    testee.setOutputDirectory(outputDir);
	    testee.generateBuilders();
	    assertBuilderTemplateMapsCreatedForClasses(NodeThree.class);
	    assertOutputDirectoryForAllBuilders(file);
	    assertOutputDirectoyWasCreated(file);
	    assertBuilderInterfaceGenerated(file, testee.getBuilderPackage());
	}

	@Test
	public void generateBuildersWhenOutputDirectoryCreationFails() throws Exception {

	    String outputDir = "another" + File.separator + "directory";
	    RuntimeException cause = new RuntimeException("some failure reason");
        mockNonExisitingOutputDirectoryFileThatFailsToCreate(outputDir, cause);
	    
	    thrown.expect(cause.getClass());
	    thrown.expectMessage(cause.getMessage());

	    testee = new BuilderGenerator(NodeThree.class, builderWriter, fileUtils);
	    testee.setOutputDirectory(outputDir);
	    testee.generateBuilders();
	}


	@Test
	public void generateBuildersForMultiNodeGraphInDefaultOutputDirectory() throws Exception {

	    File file = mockExisitingOutputDirectoryFile(DEFAULT_OUTPUT_DIRECTORY);
	    testee = new BuilderGenerator(Root.class, builderWriter, fileUtils);
	    testee.generateBuilders();
	    assertBuilderTemplateMapsCreatedForClasses(Root.class, NodeOne.class, NodeTwo.class, NodeThree.class);
	    assertOutputDirectoryForAllBuilders(file);
	    assertBuilderInterfaceGenerated(file, testee.getBuilderPackage());
	}
	
    @Test
    public void generateBuildersForMultiNodeGraphInSpecifiedOutputDirectory() throws Exception {

        String outputDir = "another" + File.separator + "different" + File.separator + "directory";
        File file = mockExisitingOutputDirectoryFile(outputDir);

        testee = new BuilderGenerator(Root.class, builderWriter, fileUtils);
        testee.setOutputDirectory(outputDir);
        testee.generateBuilders();
        assertBuilderTemplateMapsCreatedForClasses(Root.class, NodeOne.class, NodeTwo.class, NodeThree.class);
        assertOutputDirectoryForAllBuilders(file);
        assertBuilderInterfaceGenerated(file, testee.getBuilderPackage());
    }
    
    @SuppressWarnings("deprecation")
    @Test
    public void generateBuildersIgnoringProperties() throws Exception {
        
        testee = new BuilderGenerator(BeanWithPropertyToIgnore.class, builderWriter, fileUtils);
        testee.setPropertyToIgnore(BeanWithPropertyToIgnore.class, "propertyToIgnore");
        testee.generateBuilders();
        assertBuilderTemplateMapsCreatedForClasses(BeanWithPropertyToIgnore.class);
        BuilderTemplateMap builderTemplateMap = builderTemplateMapCaptor.getValue();
        WithMethodList withMethodList = builderTemplateMap.getWithMethodList();
        assertEquals("should not have created WithMethod for ignored property", 1, withMethodList.size());
    }
	
    @Test
    public void generateBuildersIgnoringClasses() throws Exception {
        
        testee = new BuilderGenerator(BeanWithChildBeanToBeIgnored.class, builderWriter, fileUtils);
        testee.addClassToIgnore(BeanToBeIgnored.class);
        testee.generateBuilders();
        assertBuilderTemplateMapsCreatedForClasses(BeanWithChildBeanToBeIgnored.class);
    }

    @Test
    public void generateBuildersIgnoringNestedClasses() throws Exception {
        
        testee = new BuilderGenerator(BeanWithNestedClassProperty.class, builderWriter, fileUtils);
        testee.addClassToIgnore(BeanWithNestedClass.NestedClass.class);
        testee.generateBuilders();
        assertBuilderTemplateMapsCreatedForClasses(BeanWithNestedClassProperty.class);
    }
    
    @Test
	public void superClassNotSpecified() throws Exception {
		
        testee = new BuilderGenerator(StringPropertyBeanWithSomethingElse.class, builderWriter, fileUtils);
        testee.generateBuilders();
        assertBuilderTemplateMapsCreatedForClasses(StringPropertyBeanWithSomethingElse.class);
        BuilderTemplateMap builderTemplateMap = builderTemplateMapCaptor.getValue();
        assertFalse("builder super class was not specified", builderTemplateMap.isSuperClassSpecified());
	}

    @Test
	public void superClassSpecified() throws Exception {
		
        testee = new BuilderGenerator(StringPropertyBeanWithSomethingElse.class, builderWriter, fileUtils);
        String superClassStatement = "some.class.to.Extend";
		testee.addBuilderSuperClass(StringPropertyBeanWithSomethingElse.class, superClassStatement);
        testee.generateBuilders();
        assertBuilderTemplateMapsCreatedForClasses(StringPropertyBeanWithSomethingElse.class);
        BuilderTemplateMap builderTemplateMap = builderTemplateMapCaptor.getValue();
        assertTrue("builder super class was specified", builderTemplateMap.isSuperClassSpecified());
        assertEquals(superClassStatement, builderTemplateMap.getSuperClass());
	}

    @Test
    public void generateBuildersForSingleNodeGraphInDefaultOutputDirectoryUsingGenerationGap() throws Exception {
        
        File file = mockExisitingOutputDirectoryFile(DEFAULT_OUTPUT_DIRECTORY);
        
        testee = new BuilderGenerator(NodeThree.class, builderWriter, fileUtils);
        testee.setGenerationGap(true);
        testee.generateBuilders();
        assertBuilderTemplateMapsCreatedForClassesUsingGenerationGap(file, testee.getBuilderPackage(), NodeThree.class);
        assertOutputDirectoryForAllBuilders(file);
        assertBuilderInterfaceGenerated(file, testee.getBuilderPackage());
    }
    
    @Test
	public void superClassSpecifiedWithGenerationGap() throws Exception {
    	// TODO
	}

    private void assertOutputDirectoyWasCreated(File file) {
	    verify(fileUtils).createDirectoriesIfNotExists(file);
	}
	
	private File mockExisitingOutputDirectoryFile(String path) {
	    File file = mockFile(path, true);
	    when(file.mkdirs()).thenThrow(new RuntimeException("directory already existed, should not have tried to create"));
        return file;
	}

    private File mockNonExisitingOutputDirectoryFile(String path) {
        File file = mockFile(path, false);
        return file;
    }

    private void mockNonExisitingOutputDirectoryFileThatFailsToCreate(String path, RuntimeException cause) {
        File file = mockFile(path, false);
        doThrow(cause).when(fileUtils).createDirectoriesIfNotExists(file);
    }

    private File mockFile(String path, boolean exists) {
	    File file = mock(File.class);
	    when(fileUtils.newFile(path)).thenReturn(file);
	    when(file.exists()).thenReturn(exists);
	    return file;
	}

    private void assertBuilderTemplateMapsCreatedForClasses(Class<?>...expectedClasses) {
	    verify(builderWriter, times(expectedClasses.length)).generateBuilder(builderTemplateMapCaptor.capture(), outputDirectoryCaptor.capture());
	    List<BuilderTemplateMap> createdBuilderTemplateMaps = builderTemplateMapCaptor.getAllValues();
	    for (Class<?> expectedClass : expectedClasses) {
	        assertEquals(expectedClass.getCanonicalName(), findFrom(createdBuilderTemplateMaps, expectedClass).getFullyQualifiedTargetClassName());
            
        }
	}

    private void assertBuilderTemplateMapsCreatedForClassesUsingGenerationGap(File generationGapBaseBuilderOutputDirectory, String generationGapBaseBuilderPackage, Class<?>...expectedClasses) {
        verify(builderWriter, times(expectedClasses.length)).generateBuilderWithGenerationGap(
                builderTemplateMapCaptor.capture(), outputDirectoryCaptor.capture(), generationGapBaseBuilderPackageCaptor.capture(), generationGapBaseBuilderOutputDirectoryCaptor.capture());
        List<BuilderTemplateMap> createdBuilderTemplateMaps = builderTemplateMapCaptor.getAllValues();
        for (Class<?> expectedClass : expectedClasses) {
            assertEquals(expectedClass.getCanonicalName(), findFrom(createdBuilderTemplateMaps, expectedClass).getFullyQualifiedTargetClassName());
            assertEquals(generationGapBaseBuilderPackage, generationGapBaseBuilderPackageCaptor.getValue());
            assertEquals(generationGapBaseBuilderOutputDirectory, generationGapBaseBuilderOutputDirectoryCaptor.getValue());
        }
    }

    private void assertOutputDirectoryForAllBuilders(File expectedOutputDirectory) {
        for (File file : outputDirectoryCaptor.getAllValues()) {
            assertSame(expectedOutputDirectory, file);
        }
    }
	
	private BuilderTemplateMap findFrom(List<BuilderTemplateMap> allValues, Class<?> c) {

	    for (BuilderTemplateMap builderTemplateMap : allValues) {
            if (builderTemplateMap.getFullyQualifiedTargetClassName().equals(c.getCanonicalName())) {
                return builderTemplateMap;
            }
        }
	    return null;
	}
	
	private void assertBuilderInterfaceGenerated(File outputDirectory, String builderPackage) {
	    verify(builderWriter).generateBuilderInteraface(outputDirectory, builderPackage);
	}
}
