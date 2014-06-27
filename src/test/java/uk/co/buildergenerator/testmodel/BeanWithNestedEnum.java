package uk.co.buildergenerator.testmodel;

public class BeanWithNestedEnum {

    public enum NestedEnum {
        NESTED_1, NESTED_2
    }
    
    private NestedEnum nestedEnum;

    public NestedEnum getNestedEnum() {
        return nestedEnum;
    }

    public void setNestedEnum(NestedEnum nestedEnum) {
        this.nestedEnum = nestedEnum;
    }
    
}
