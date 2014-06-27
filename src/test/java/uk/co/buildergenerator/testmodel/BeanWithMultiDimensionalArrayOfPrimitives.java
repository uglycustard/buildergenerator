package uk.co.buildergenerator.testmodel;

public class BeanWithMultiDimensionalArrayOfPrimitives {

    private int[][][] threeDimenstionalIntArray;
    private boolean[][] twoDimensionalBooleanArray;
    private char[][][][] fourDimensionalCharArray;

    public int[][][] getThreeDimenstionalIntArray() {
        return threeDimenstionalIntArray;
    }

    public void setThreeDimenstionalIntArray(int[][][] threeDimenstionalIntArray) {
        this.threeDimenstionalIntArray = threeDimenstionalIntArray;
    }

    public boolean[][] getTwoDimensionalBooleanArray() {
        return twoDimensionalBooleanArray;
    }

    public void setTwoDimensionalBooleanArray(boolean[][] twoDimensionalBooleanArray) {
        this.twoDimensionalBooleanArray = twoDimensionalBooleanArray;
    }

    public char[][][][] getFourDimensionalCharArray() {
        return fourDimensionalCharArray;
    }

    public void setFourDimensionalCharArray(char[][][][] fourDimensionalCharArray) {
        this.fourDimensionalCharArray = fourDimensionalCharArray;
    }

}
