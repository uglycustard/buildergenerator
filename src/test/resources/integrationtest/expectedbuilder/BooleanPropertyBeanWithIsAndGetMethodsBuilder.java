package integrationtest.generatedbuilder;

public class BooleanPropertyBeanWithIsAndGetMethodsBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsAndGetMethods> {

    public static BooleanPropertyBeanWithIsAndGetMethodsBuilder aBooleanPropertyBeanWithIsAndGetMethods() {
        return new BooleanPropertyBeanWithIsAndGetMethodsBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsAndGetMethods target = new uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsAndGetMethods();
    
    public BooleanPropertyBeanWithIsAndGetMethodsBuilder() {}
    
    public BooleanPropertyBeanWithIsAndGetMethodsBuilder withTheBoolean(boolean theBoolean) {
        getTarget().setTheBoolean(theBoolean);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsAndGetMethods getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsAndGetMethods build() {
        return getTarget();
    }
}
