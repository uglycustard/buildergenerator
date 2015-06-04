package integrationtest.generatedbuilder;

public class InitialisedQueuePropertyWithAddMethodBuilder {

    public static InitialisedQueuePropertyWithAddMethodBuilder anInitialisedQueuePropertyWithAddMethod() {
        return new InitialisedQueuePropertyWithAddMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.InitialisedQueuePropertyWithAddMethod target = new uk.co.buildergenerator.testmodel.InitialisedQueuePropertyWithAddMethod();
    
    public InitialisedQueuePropertyWithAddMethodBuilder() {}
    
    public InitialisedQueuePropertyWithAddMethodBuilder withString(java.lang.String string) {
        getTarget().addString(string);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.InitialisedQueuePropertyWithAddMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.InitialisedQueuePropertyWithAddMethod build() {
        return getTarget();
    }
}
