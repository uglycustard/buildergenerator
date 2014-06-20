package integrationtest.generatedbuilder;

public class BooleanPropertyBeanBuilder {

    public static BooleanPropertyBeanBuilder aBooleanPropertyBean() {
        return new BooleanPropertyBeanBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BooleanPropertyBean target = new uk.co.buildergenerator.testmodel.BooleanPropertyBean();
    
    private BooleanPropertyBeanBuilder() {}
    
    public BooleanPropertyBeanBuilder withTheBoolean(boolean theBoolean) {
        target.setTheBoolean(theBoolean);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BooleanPropertyBean build() {
        return target;
    }
}
