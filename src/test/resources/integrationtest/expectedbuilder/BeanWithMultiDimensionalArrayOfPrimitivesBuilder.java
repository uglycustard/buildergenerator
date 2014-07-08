package integrationtest.generatedbuilder;

public class BeanWithMultiDimensionalArrayOfPrimitivesBuilder {

    public static BeanWithMultiDimensionalArrayOfPrimitivesBuilder aBeanWithMultiDimensionalArrayOfPrimitives() {
        return new BeanWithMultiDimensionalArrayOfPrimitivesBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithMultiDimensionalArrayOfPrimitives target = new uk.co.buildergenerator.testmodel.BeanWithMultiDimensionalArrayOfPrimitives();
    
    public BeanWithMultiDimensionalArrayOfPrimitivesBuilder() {}
    
    public BeanWithMultiDimensionalArrayOfPrimitivesBuilder withThreeDimenstionalIntArray(int[][][] threeDimenstionalIntArray) {
        target.setThreeDimenstionalIntArray(threeDimenstionalIntArray);
        return this;
    }
    
    public BeanWithMultiDimensionalArrayOfPrimitivesBuilder withTwoDimensionalBooleanArray(boolean[][] twoDimensionalBooleanArray) {
        target.setTwoDimensionalBooleanArray(twoDimensionalBooleanArray);
        return this;
    }
    
    public BeanWithMultiDimensionalArrayOfPrimitivesBuilder withFourDimensionalCharArray(char[][][][] fourDimensionalCharArray) {
        target.setFourDimensionalCharArray(fourDimensionalCharArray);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithMultiDimensionalArrayOfPrimitives build() {
        return target;
    }
}
