package integrationtest.generatedbuilder;

public class ArrayOfPrimitiveIntsPropertyWithSetArrayMethodBuilder {

    public static ArrayOfPrimitiveIntsPropertyWithSetArrayMethodBuilder anArrayOfPrimitiveIntsPropertyWithSetArrayMethod() {
        return new ArrayOfPrimitiveIntsPropertyWithSetArrayMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.ArrayOfPrimitiveIntsPropertyWithSetArrayMethod target = new uk.co.buildergenerator.testmodel.ArrayOfPrimitiveIntsPropertyWithSetArrayMethod();
    
    public ArrayOfPrimitiveIntsPropertyWithSetArrayMethodBuilder() {}
    
    public ArrayOfPrimitiveIntsPropertyWithSetArrayMethodBuilder withInts(int[] ints) {
        target.setInts(ints);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.ArrayOfPrimitiveIntsPropertyWithSetArrayMethod build() {
        return target;
    }
}
