package integrationtest.generatedbuilder;

public class ArrayOfNonJavaTypesPropertyWithSetArrayMethodBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.ArrayOfNonJavaTypesPropertyWithSetArrayMethod> {

    public static ArrayOfNonJavaTypesPropertyWithSetArrayMethodBuilder anArrayOfNonJavaTypesPropertyWithSetArrayMethod() {
        return new ArrayOfNonJavaTypesPropertyWithSetArrayMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.ArrayOfNonJavaTypesPropertyWithSetArrayMethod target = new uk.co.buildergenerator.testmodel.ArrayOfNonJavaTypesPropertyWithSetArrayMethod();
    
    public ArrayOfNonJavaTypesPropertyWithSetArrayMethodBuilder() {}
    
    public ArrayOfNonJavaTypesPropertyWithSetArrayMethodBuilder withHostesses(uk.co.buildergenerator.testmodel.Hostess[] hostesses) {
        getTarget().setHostesses(hostesses);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.ArrayOfNonJavaTypesPropertyWithSetArrayMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.ArrayOfNonJavaTypesPropertyWithSetArrayMethod build() {
        return getTarget();
    }
}
