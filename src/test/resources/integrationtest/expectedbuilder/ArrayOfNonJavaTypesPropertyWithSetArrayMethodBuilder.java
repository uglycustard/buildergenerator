package integrationtest.generatedbuilder;

public class ArrayOfNonJavaTypesPropertyWithSetArrayMethodBuilder {

    public static ArrayOfNonJavaTypesPropertyWithSetArrayMethodBuilder anArrayOfNonJavaTypesPropertyWithSetArrayMethod() {
        return new ArrayOfNonJavaTypesPropertyWithSetArrayMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.ArrayOfNonJavaTypesPropertyWithSetArrayMethod target = new uk.co.buildergenerator.testmodel.ArrayOfNonJavaTypesPropertyWithSetArrayMethod();
    
    public ArrayOfNonJavaTypesPropertyWithSetArrayMethodBuilder() {}
    
    public ArrayOfNonJavaTypesPropertyWithSetArrayMethodBuilder withHostesses(uk.co.buildergenerator.testmodel.Hostess[] hostesses) {
        target.setHostesses(hostesses);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.ArrayOfNonJavaTypesPropertyWithSetArrayMethod build() {
        return target;
    }
}
