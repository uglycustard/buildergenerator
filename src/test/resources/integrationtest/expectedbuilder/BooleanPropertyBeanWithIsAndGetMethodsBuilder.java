package integrationtest.generatedbuilder;

public class BooleanPropertyBeanWithIsAndGetMethodsBuilder {

    public static BooleanPropertyBeanWithIsAndGetMethodsBuilder aBooleanPropertyBeanWithIsAndGetMethods() {
        return new BooleanPropertyBeanWithIsAndGetMethodsBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsAndGetMethods target = new uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsAndGetMethods();
    
    public BooleanPropertyBeanWithIsAndGetMethodsBuilder() {}
    
    public BooleanPropertyBeanWithIsAndGetMethodsBuilder withTheBoolean(boolean theBoolean) {
        target.setTheBoolean(theBoolean);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BooleanPropertyBeanWithIsAndGetMethods build() {
        return target;
    }
}
