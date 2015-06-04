package integrationtest.generatedbuilder;

public class StringPropertyBeanBuilder {

    public static StringPropertyBeanBuilder aStringPropertyBean() {
        return new StringPropertyBeanBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.StringPropertyBean target = new uk.co.buildergenerator.testmodel.StringPropertyBean();
    
    public StringPropertyBeanBuilder() {}
    
    public StringPropertyBeanBuilder withTheString(java.lang.String theString) {
        getTarget().setTheString(theString);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.StringPropertyBean getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.StringPropertyBean build() {
        return getTarget();
    }
}
