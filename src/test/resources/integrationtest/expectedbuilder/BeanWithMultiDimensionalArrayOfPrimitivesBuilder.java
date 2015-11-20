package integrationtest.generatedbuilder;

public class BeanWithMultiDimensionalArrayOfPrimitivesBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.BeanWithMultiDimensionalArrayOfPrimitives> {

    public static BeanWithMultiDimensionalArrayOfPrimitivesBuilder aBeanWithMultiDimensionalArrayOfPrimitives() {
        return new BeanWithMultiDimensionalArrayOfPrimitivesBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithMultiDimensionalArrayOfPrimitives target = new uk.co.buildergenerator.testmodel.BeanWithMultiDimensionalArrayOfPrimitives();
    
    public BeanWithMultiDimensionalArrayOfPrimitivesBuilder() {}
    
    public BeanWithMultiDimensionalArrayOfPrimitivesBuilder withThreeDimenstionalIntArray(int[][][] threeDimenstionalIntArray) {
        getTarget().setThreeDimenstionalIntArray(threeDimenstionalIntArray);
        return this;
    }
    
    public BeanWithMultiDimensionalArrayOfPrimitivesBuilder withTwoDimensionalBooleanArray(boolean[][] twoDimensionalBooleanArray) {
        getTarget().setTwoDimensionalBooleanArray(twoDimensionalBooleanArray);
        return this;
    }
    
    public BeanWithMultiDimensionalArrayOfPrimitivesBuilder withFourDimensionalCharArray(char[][][][] fourDimensionalCharArray) {
        getTarget().setFourDimensionalCharArray(fourDimensionalCharArray);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BeanWithMultiDimensionalArrayOfPrimitives getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithMultiDimensionalArrayOfPrimitives build() {
        return getTarget();
    }
}
