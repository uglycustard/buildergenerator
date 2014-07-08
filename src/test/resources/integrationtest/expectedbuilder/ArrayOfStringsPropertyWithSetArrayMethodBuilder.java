package integrationtest.generatedbuilder;

public class ArrayOfStringsPropertyWithSetArrayMethodBuilder {

    public static ArrayOfStringsPropertyWithSetArrayMethodBuilder anArrayOfStringsPropertyWithSetArrayMethod() {
        return new ArrayOfStringsPropertyWithSetArrayMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.ArrayOfStringsPropertyWithSetArrayMethod target = new uk.co.buildergenerator.testmodel.ArrayOfStringsPropertyWithSetArrayMethod();
    
    public ArrayOfStringsPropertyWithSetArrayMethodBuilder() {}
    
    public ArrayOfStringsPropertyWithSetArrayMethodBuilder withStrings(java.lang.String[] strings) {
        target.setStrings(strings);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.ArrayOfStringsPropertyWithSetArrayMethod build() {
        return target;
    }
}
