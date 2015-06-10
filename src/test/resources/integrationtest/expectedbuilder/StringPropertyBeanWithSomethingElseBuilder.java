package integrationtest.generatedbuilder;

public class StringPropertyBeanWithSomethingElseBuilder extends integrationtest.generatedbuilder.Base<integrationtest.generatedbuilder.StringPropertyBeanWithSomethingElseBuilder> {
    public static StringPropertyBeanWithSomethingElseBuilder aStringPropertyBeanWithSomethingElse() {
        return new StringPropertyBeanWithSomethingElseBuilder();
    }    
    private uk.co.buildergenerator.testmodel.StringPropertyBeanWithSomethingElse target = new uk.co.buildergenerator.testmodel.StringPropertyBeanWithSomethingElse();
    
    public StringPropertyBeanWithSomethingElseBuilder() {}
    
    public StringPropertyBeanWithSomethingElseBuilder withTheString(java.lang.String theString) {
        getTarget().setTheString(theString);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.StringPropertyBeanWithSomethingElse getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.StringPropertyBeanWithSomethingElse build() {
        return getTarget();
    }
}
