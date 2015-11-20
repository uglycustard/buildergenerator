package integrationtest.generationgap.generatedbuilder.base;

public class StringPropertyBeanBaseBuilder<T extends integrationtest.generationgap.generatedbuilder.base.StringPropertyBeanBaseBuilder<T>> implements integrationtest.generationgap.generatedbuilder.base.Builder<uk.co.buildergenerator.testmodel.StringPropertyBean> {
    
    private uk.co.buildergenerator.testmodel.StringPropertyBean target = new uk.co.buildergenerator.testmodel.StringPropertyBean();
    
    public StringPropertyBeanBaseBuilder() {}
    
    public T withTheString(java.lang.String theString) {
        getTarget().setTheString(theString);
        return (T) this;
    }
    
    protected uk.co.buildergenerator.testmodel.StringPropertyBean getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.StringPropertyBean build() {
        return getTarget();
    }
}
