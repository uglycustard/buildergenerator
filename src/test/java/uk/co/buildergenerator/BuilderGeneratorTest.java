package uk.co.buildergenerator;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.co.buildergenerator.testmodel.Root;

public class BuilderGeneratorTest {

	private BuilderGenerator testee;

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
}
