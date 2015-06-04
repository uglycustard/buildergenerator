package integrationtest.generatedbuilder;

public class ArrayOfStringsPropertyWithSetArrayMethodBuilder {

    public static ArrayOfStringsPropertyWithSetArrayMethodBuilder anArrayOfStringsPropertyWithSetArrayMethod() {
        return new ArrayOfStringsPropertyWithSetArrayMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.ArrayOfStringsPropertyWithSetArrayMethod target = new uk.co.buildergenerator.testmodel.ArrayOfStringsPropertyWithSetArrayMethod();
    
    public ArrayOfStringsPropertyWithSetArrayMethodBuilder() {}
    
    public ArrayOfStringsPropertyWithSetArrayMethodBuilder withStrings(java.lang.String[] strings) {
        getTarget().setStrings(strings);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.ArrayOfStringsPropertyWithSetArrayMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.ArrayOfStringsPropertyWithSetArrayMethod build() {
        return getTarget();
    }
}
