package integrationtest.generationgap.generatedbuilder;

public class CustomisedStringPropertyBeanBaseBuilder<T extends integrationtest.generationgap.generatedbuilder.CustomisedStringPropertyBeanBaseBuilder<T>> {
    
    private uk.co.buildergenerator.testmodel.CustomisedStringPropertyBean target = new uk.co.buildergenerator.testmodel.CustomisedStringPropertyBean();
    
    public CustomisedStringPropertyBeanBaseBuilder() {}
    
    public T withTheString(java.lang.String theString) {
        target.setTheString(theString);
        return (T) this;
    }
    
    protected uk.co.buildergenerator.testmodel.CustomisedStringPropertyBean getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.CustomisedStringPropertyBean build() {
        return target;
    }
}
