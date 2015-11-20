package integrationtest.generatedbuilder;

public class BooleanPropertyBeanWithIsMethodBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsMethod> {

    public static BooleanPropertyBeanWithIsMethodBuilder aBooleanPropertyBeanWithIsMethod() {
        return new BooleanPropertyBeanWithIsMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsMethod target = new uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsMethod();
    
    public BooleanPropertyBeanWithIsMethodBuilder() {}
    
    public BooleanPropertyBeanWithIsMethodBuilder withTheBoolean(boolean theBoolean) {
        getTarget().setTheBoolean(theBoolean);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsMethod build() {
        return getTarget();
    }
}
