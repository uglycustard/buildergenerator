package integrationtest.generatedbuilder;

public class BooleanPropertyBeanWithIsMethodBuilder {

    public static BooleanPropertyBeanWithIsMethodBuilder aBooleanPropertyBeanWithIsMethod() {
        return new BooleanPropertyBeanWithIsMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsMethod target = new uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsMethod();
    
    private BooleanPropertyBeanWithIsMethodBuilder() {}
    
    public BooleanPropertyBeanWithIsMethodBuilder withTheBoolean(boolean theBoolean) {
        target.setTheBoolean(theBoolean);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsMethod build() {
        return target;
    }
}
