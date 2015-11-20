package integrationtest.generatedbuilder;

public class BeanWithTopLevelEnumPropertyBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.BeanWithTopLevelEnumProperty> {

    public static BeanWithTopLevelEnumPropertyBuilder aBeanWithTopLevelEnumProperty() {
        return new BeanWithTopLevelEnumPropertyBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithTopLevelEnumProperty target = new uk.co.buildergenerator.testmodel.BeanWithTopLevelEnumProperty();
    
    public BeanWithTopLevelEnumPropertyBuilder() {}
    
    public BeanWithTopLevelEnumPropertyBuilder withTopLevelEnum(uk.co.buildergenerator.testmodel.TopLevelEnum topLevelEnum) {
        getTarget().setTopLevelEnum(topLevelEnum);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BeanWithTopLevelEnumProperty getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithTopLevelEnumProperty build() {
        return getTarget();
    }
}
