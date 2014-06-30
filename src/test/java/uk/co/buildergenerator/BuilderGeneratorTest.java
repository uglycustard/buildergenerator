package uk.co.buildergenerator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.buildergenerator.BuilderGenerator.DEFAULT_OUTPUT_DIRECTORY;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import uk.co.buildergenerator.testmodel.NodeOne;
import uk.co.buildergenerator.testmodel.NodeThree;
import uk.co.buildergenerator.testmodel.NodeTwo;
import uk.co.buildergenerator.testmodel.Root;

public class BuilderGeneratorTest {

	private BuilderGenerator testee;
	
	@Mock
    private BuilderWriter builderWriter;
	
	@Captor
	private ArgumentCaptor<BuilderTemplateMap> builderTemplateMapCaptor;
	
	@Captor
	private ArgumentCaptor<File> outputDirectoryCaptor;
	
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
        
        testee = new BuilderGenerator(NodeThree.class, builderWriter);
        testee.generateBuilders();
        assertBuilderTemplateMaps(NodeThree.class);
        assertOutputDirectoryPathForAllBuilders(DEFAULT_OUTPUT_DIRECTORY);
    }
	
	@Test
	public void generateBuildersForSingleNodeGraphInSpecifiedOutputDirectory() throws Exception {

	    String outputDir = "another" + File.separator + "directory";
        testee = new BuilderGenerator(NodeThree.class, builderWriter);
        testee.setOutputDirectory(outputDir);
	    testee.generateBuilders();
	    assertBuilderTemplateMaps(NodeThree.class);
	    assertOutputDirectoryPathForAllBuilders(outputDir);
	}

	@Test
	public void generateBuildersForMultiNodeGraphInDefaultOutputDirectory() throws Exception {

	    testee = new BuilderGenerator(Root.class, builderWriter);
	    testee.generateBuilders();
	    assertBuilderTemplateMaps(Root.class, NodeOne.class, NodeTwo.class, NodeThree.class);
	    assertOutputDirectoryPathForAllBuilders(DEFAULT_OUTPUT_DIRECTORY);
	}
	
    @Test
    public void generateBuildersForMultiNodeGraphInSpecifiedOutputDirectory() throws Exception {

        String outputDir = "another" + File.separator + "different" + File.separator + "directory";
        testee = new BuilderGenerator(Root.class, builderWriter);
        testee.setOutputDirectory(outputDir);
        testee.generateBuilders();
        assertBuilderTemplateMaps(Root.class, NodeOne.class, NodeTwo.class, NodeThree.class);
        assertOutputDirectoryPathForAllBuilders(outputDir);
    }

    private void assertBuilderTemplateMaps(Class<?>...expectedClasses) {
	    verify(builderWriter, times(expectedClasses.length)).generateBuilder(builderTemplateMapCaptor.capture(), outputDirectoryCaptor.capture());
	    List<BuilderTemplateMap> allBuilderTemplateMaps = builderTemplateMapCaptor.getAllValues();
	    for (Class<?> expectedClass : expectedClasses) {
	        assertEquals(expectedClass.getCanonicalName(), findFrom(allBuilderTemplateMaps, expectedClass).getFullyQualifiedTargetClassName());
            
        }
	}

    private void assertOutputDirectoryPathForAllBuilders(String expectedOutputDirectory) {
        for (File file : outputDirectoryCaptor.getAllValues()) {
            assertEquals(expectedOutputDirectory, file.getPath());
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
