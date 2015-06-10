package integrationtest.generationgap.generatedbuilder;

public class StringPropertyBeanWithSomethingElseForGenerationGapTestBaseBuilder<T extends integrationtest.generationgap.generatedbuilder.StringPropertyBeanWithSomethingElseForGenerationGapTestBaseBuilder<T>> extends integrationtest.generatedbuilder.Base<integrationtest.generatedbuilder.StringPropertyBeanWithSomethingElseForGenerationGapTest> {
    
    private uk.co.buildergenerator.testmodel.StringPropertyBeanWithSomethingElseForGenerationGapTest target = new uk.co.buildergenerator.testmodel.StringPropertyBeanWithSomethingElseForGenerationGapTest();
    
    public StringPropertyBeanWithSomethingElseForGenerationGapTestBaseBuilder() {}
    
    public T withTheString(java.lang.String theString) {
        getTarget().setTheString(theString);
        return (T) this;
    }
    
    protected uk.co.buildergenerator.testmodel.StringPropertyBeanWithSomethingElseForGenerationGapTest getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.StringPropertyBeanWithSomethingElseForGenerationGapTest build() {
        return getTarget();
    }
}
