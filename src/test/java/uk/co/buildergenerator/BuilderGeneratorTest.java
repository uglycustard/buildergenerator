package uk.co.buildergenerator;

import static org.junit.Assert.*;
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

import uk.co.buildergenerator.testmodel.BeanWithPropertyToIgnore;
import uk.co.buildergenerator.testmodel.NodeOne;
import uk.co.buildergenerator.testmodel.NodeThree;
import uk.co.buildergenerator.testmodel.NodeTwo;
import uk.co.buildergenerator.testmodel.Root;

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
    }
    
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
	
}
