package integrationtest.generatedbuilder;

public class BeanWithNestedEnumBuilder {

    public static BeanWithNestedEnumBuilder aBeanWithNestedEnum() {
        return new BeanWithNestedEnumBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithNestedEnum target = new uk.co.buildergenerator.testmodel.BeanWithNestedEnum();
    
    public BeanWithNestedEnumBuilder() {}
    
    public BeanWithNestedEnumBuilder withNestedEnum(uk.co.buildergenerator.testmodel.BeanWithNestedEnum.NestedEnum nestedEnum) {
        target.setNestedEnum(nestedEnum);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithNestedEnum build() {
        return target;
    }
}
