package integrationtest.generationgap.generatedbuilder;

public class StringPropertyBeanBaseBuilder<T extends StringPropertyBeanBaseBuilder<T>> {
    
    private uk.co.buildergenerator.testmodel.StringPropertyBean target = new uk.co.buildergenerator.testmodel.StringPropertyBean();
    
    public StringPropertyBeanBaseBuilder() {}
    
    public T withTheString(java.lang.String theString) {
        target.setTheString(theString);
        return (T) this;
    }
    
    protected uk.co.buildergenerator.testmodel.StringPropertyBean getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.StringPropertyBean build() {
        return target;
    }
}
