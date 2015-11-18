package integrationtest.generationgap.generatedbuilder;

public class StringPropertyBean2BaseBuilder<T extends integrationtest.generationgap.generatedbuilder.StringPropertyBean2BaseBuilder<T>> {
    
    private uk.co.buildergenerator.testmodel.StringPropertyBean2 target = new uk.co.buildergenerator.testmodel.StringPropertyBean2();
    
    public StringPropertyBean2BaseBuilder() {}
    
    public T withTheString(java.lang.String theString) {
        getTarget().setTheString(theString);
        return (T) this;
    }
    
    protected uk.co.buildergenerator.testmodel.StringPropertyBean2 getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.StringPropertyBean2 build() {
        return getTarget();
    }
}
