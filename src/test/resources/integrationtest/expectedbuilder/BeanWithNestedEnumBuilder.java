package integrationtest.generatedbuilder;

public class BeanWithNestedEnumBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.BeanWithNestedEnum> {

    public static BeanWithNestedEnumBuilder aBeanWithNestedEnum() {
        return new BeanWithNestedEnumBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithNestedEnum target = new uk.co.buildergenerator.testmodel.BeanWithNestedEnum();
    
    public BeanWithNestedEnumBuilder() {}
    
    public BeanWithNestedEnumBuilder withNestedEnum(uk.co.buildergenerator.testmodel.BeanWithNestedEnum.NestedEnum nestedEnum) {
        getTarget().setNestedEnum(nestedEnum);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BeanWithNestedEnum getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithNestedEnum build() {
        return getTarget();
    }
}
