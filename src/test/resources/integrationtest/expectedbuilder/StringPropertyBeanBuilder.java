package integrationtest.generatedbuilder;

public class StringPropertyBeanBuilder {

    public static StringPropertyBeanBuilder aStringPropertyBean() {
        return new StringPropertyBeanBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.StringPropertyBean target = new uk.co.buildergenerator.testmodel.StringPropertyBean();
    
    private StringPropertyBeanBuilder() {}
    
    public StringPropertyBeanBuilder withTheString(java.lang.String theString) {
        target.setTheString(theString);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.StringPropertyBean build() {
        return target;
    }
}
