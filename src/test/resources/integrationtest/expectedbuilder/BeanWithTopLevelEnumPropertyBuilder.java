package integrationtest.generatedbuilder;

public class BeanWithTopLevelEnumPropertyBuilder {

    public static BeanWithTopLevelEnumPropertyBuilder aBeanWithTopLevelEnumProperty() {
        return new BeanWithTopLevelEnumPropertyBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithTopLevelEnumProperty target = new uk.co.buildergenerator.testmodel.BeanWithTopLevelEnumProperty();
    
    public BeanWithTopLevelEnumPropertyBuilder() {}
    
    public BeanWithTopLevelEnumPropertyBuilder withTopLevelEnum(uk.co.buildergenerator.testmodel.TopLevelEnum topLevelEnum) {
        target.setTopLevelEnum(topLevelEnum);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithTopLevelEnumProperty build() {
        return target;
    }
}
