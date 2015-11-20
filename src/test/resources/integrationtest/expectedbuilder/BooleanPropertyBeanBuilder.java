package integrationtest.generatedbuilder;

public class BooleanPropertyBeanBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.BooleanPropertyBean> {

    public static BooleanPropertyBeanBuilder aBooleanPropertyBean() {
        return new BooleanPropertyBeanBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BooleanPropertyBean target = new uk.co.buildergenerator.testmodel.BooleanPropertyBean();
    
    public BooleanPropertyBeanBuilder() {}
    
    public BooleanPropertyBeanBuilder withTheBoolean(boolean theBoolean) {
        getTarget().setTheBoolean(theBoolean);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BooleanPropertyBean getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BooleanPropertyBean build() {
        return getTarget();
    }
}
