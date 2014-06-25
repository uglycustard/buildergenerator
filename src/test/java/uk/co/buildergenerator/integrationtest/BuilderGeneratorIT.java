package uk.co.buildergenerator.integrationtest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import uk.co.buildergenerator.BuilderGenerator;
import uk.co.buildergenerator.testmodel.ArrayOfNonJavaTypesPropertyWithSetArrayMethod;
import uk.co.buildergenerator.testmodel.ArrayOfPrimitiveIntsPropertyWithSetArrayMethod;
import uk.co.buildergenerator.testmodel.ArrayOfStringsPropertyWithSetArrayMethod;
import uk.co.buildergenerator.testmodel.BooleanPropertyBean;
import uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsAndGetMethods;
import uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsMethod;
import uk.co.buildergenerator.testmodel.CyclicDependencyBeanLeft;
import uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethod;
import uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethodAndSetListMethod;
import uk.co.buildergenerator.testmodel.InitialisedListPropertyWithSetListMethod;
import uk.co.buildergenerator.testmodel.InitialisedQueuePropertyWithAddMethod;
import uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithAddMethod;
import uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithSetSetMethod;
import uk.co.buildergenerator.testmodel.NullListPropertyWithSetListMethod;
import uk.co.buildergenerator.testmodel.NullQueuePropertyWithSetQueueMethod;
import uk.co.buildergenerator.testmodel.NullSetPropertyWithSetSetMethod;
import uk.co.buildergenerator.testmodel.Root;
import uk.co.buildergenerator.testmodel.SelfReferencingBean;
import uk.co.buildergenerator.testmodel.StringPropertyBean;
import uk.co.buildergenerator.testmodel.SubClassOfInitialisedListPropertyWithAddMethod;
import uk.co.buildergenerator.testmodel.SubClassOfInitialisedListPropertyWithSetListMethod;

public class BuilderGeneratorIT {
    
    private static final String BUILDER_PACKAGE = "integrationtest.generatedbuilder";
    private static final String OUTPUT_DIRECTORY = "./target/test-classes";
//    private static final String OUTPUT_DIRECTORY = "./src/test/java";

    private String readFile(String filename) throws IOException {
        
        BufferedReader r = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(filename)));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = r.readLine()) != null) {
            sb.append(line);
        }
        
        return sb.toString();
    }
    
    private void assertFilesEqual(String expectedBuilderFilename, String generatedBuilderFilename) throws IOException {

        String expected = readFile(expectedBuilderFilename);
        String actual = readFile(generatedBuilderFilename);
        assertEquals(expected, actual);
    }
    
    private BuilderGenerator createBuilderGenerator(Class<?> root, String builderPackage, String outputDirectory) {
    	BuilderGenerator bg = new BuilderGenerator(root);
    	bg.setBuilderPackage(builderPackage);
    	bg.setOutputDirectory(outputDirectory);
    	return bg;
    }
    
    @Test
    public void stringProperty() throws Exception {
        
        createBuilderGenerator(StringPropertyBean.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        String generatedBuilderFilename = "integrationtest/generatedbuilder/StringPropertyBeanBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/StringPropertyBeanBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void primitiveBooleanProperty() throws Exception {
        
        createBuilderGenerator(BooleanPropertyBean.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/BooleanPropertyBeanBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/BooleanPropertyBeanBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void primitiveBooleanPropertyWithIsMethod() throws Exception {
        
        createBuilderGenerator(BooleanPropertyBeanWithIsMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/BooleanPropertyBeanWithIsMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/BooleanPropertyBeanWithIsMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void primitiveBooleanPropertyWithIsAndGetMethods() throws Exception {
        
        createBuilderGenerator(BooleanPropertyBeanWithIsAndGetMethods.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/BooleanPropertyBeanWithIsAndGetMethodsBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/BooleanPropertyBeanWithIsAndGetMethodsBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }
    
    @Test
    public void beanGraphBuilderGeneration() throws Exception {

        createBuilderGenerator(Root.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();

        String generatedBuilderFilename = "integrationtest/generatedbuilder/RootBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/RootBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);

        generatedBuilderFilename = "integrationtest/generatedbuilder/NodeOneBuilder.java";
        expectedBuilderFilename = "integrationtest/expectedbuilder/NodeOneBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
        
        generatedBuilderFilename = "integrationtest/generatedbuilder/NodeTwoBuilder.java";
        expectedBuilderFilename = "integrationtest/expectedbuilder/NodeTwoBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
        
        generatedBuilderFilename = "integrationtest/generatedbuilder/NodeThreeBuilder.java";
        expectedBuilderFilename = "integrationtest/expectedbuilder/NodeThreeBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }
    
    @Test
    public void cyclicBeanDependency() throws Exception {
        
        createBuilderGenerator(CyclicDependencyBeanLeft.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/CyclicDependencyBeanLeftBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/CyclicDependencyBeanLeftBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
        
        generatedBuilderFilename = "integrationtest/generatedbuilder/CyclicDependencyBeanRightBuilder.java";
        expectedBuilderFilename = "integrationtest/expectedbuilder/CyclicDependencyBeanRightBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void selfReferencingBean() throws Exception {

        createBuilderGenerator(SelfReferencingBean.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/SelfReferencingBeanBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/SelfReferencingBeanBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }
    
    @Test
    public void listPropertyWithAddMethod() throws Exception {
        
        createBuilderGenerator(InitialisedListPropertyWithAddMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/InitialisedListPropertyWithAddMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/InitialisedListPropertyWithAddMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }
    
    @Test
    public void listPropertyWithAddMethodAndSetListMethod() throws Exception {
        
        createBuilderGenerator(InitialisedListPropertyWithAddMethodAndSetListMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/InitialisedListPropertyWithAddMethodAndSetListMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/InitialisedListPropertyWithAddMethodAndSetListMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }
    
    @Test
    public void initialisedListPropertyWithSetListMethod() throws Exception {
        
        createBuilderGenerator(InitialisedListPropertyWithSetListMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/InitialisedListPropertyWithSetListMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/InitialisedListPropertyWithSetListMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void subClassOfnitialisedListPropertyWithSetListMethod() throws Exception {
        
        createBuilderGenerator(SubClassOfInitialisedListPropertyWithSetListMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/SubClassOfInitialisedListPropertyWithSetListMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/SubClassOfInitialisedListPropertyWithSetListMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void subClassOfListPropertyWithAddMethod() throws Exception {
        
        createBuilderGenerator(SubClassOfInitialisedListPropertyWithAddMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/SubClassOfInitialisedListPropertyWithAddMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/SubClassOfInitialisedListPropertyWithAddMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void nullListPropertyWithSetListMethod() throws Exception {
        
        createBuilderGenerator(NullListPropertyWithSetListMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/NullListPropertyWithSetListMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/NullListPropertyWithSetListMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void setPropertyWithAddMethod() throws Exception {
        
        createBuilderGenerator(InitialisedSetPropertyWithAddMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/InitialisedSetPropertyWithAddMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/InitialisedSetPropertyWithAddMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void initialisedSetPropertyWithSetSetMethod() throws Exception {
        
        createBuilderGenerator(InitialisedSetPropertyWithSetSetMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/InitialisedSetPropertyWithSetSetMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/InitialisedSetPropertyWithSetSetMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void nullSetPropertyWithSetSetMethod() throws Exception {
        
        createBuilderGenerator(NullSetPropertyWithSetSetMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/NullSetPropertyWithSetSetMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/NullSetPropertyWithSetSetMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void initialisedQueuePropertyWithSetQueueMethod() throws Exception {
        
        createBuilderGenerator(InitialisedQueuePropertyWithAddMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/InitialisedQueuePropertyWithAddMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/InitialisedQueuePropertyWithAddMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void nullQueuePropertyWithSetQueueMethod() throws Exception {
        
        createBuilderGenerator(NullQueuePropertyWithSetQueueMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/NullQueuePropertyWithSetQueueMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/NullQueuePropertyWithSetQueueMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void arrayOfStringsPropertyWithSetArrayMethod() throws Exception {
        
        createBuilderGenerator(ArrayOfStringsPropertyWithSetArrayMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/ArrayOfStringsPropertyWithSetArrayMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/ArrayOfStringsPropertyWithSetArrayMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void arrayOfPrimitiveIntsPropertyWithSetArrayMethod() throws Exception {
        
        createBuilderGenerator(ArrayOfPrimitiveIntsPropertyWithSetArrayMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/ArrayOfPrimitiveIntsPropertyWithSetArrayMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/ArrayOfPrimitiveIntsPropertyWithSetArrayMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

    @Test
    public void arrayOfNonJavaTypesPropertyWithSetArrayMethod() throws Exception {
        
        createBuilderGenerator(ArrayOfNonJavaTypesPropertyWithSetArrayMethod.class, BUILDER_PACKAGE, OUTPUT_DIRECTORY).generateBuilders();
        
        String generatedBuilderFilename = "integrationtest/generatedbuilder/ArrayOfNonJavaTypesPropertyWithSetArrayMethodBuilder.java";
        String expectedBuilderFilename = "integrationtest/expectedbuilder/ArrayOfNonJavaTypesPropertyWithSetArrayMethodBuilder.java";
        assertFilesEqual(expectedBuilderFilename, generatedBuilderFilename);
    }

}
