package integrationtest.generatedbuilder;

public class InitialisedSetPropertyWithAddMethodBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithAddMethod> {

    public static InitialisedSetPropertyWithAddMethodBuilder anInitialisedSetPropertyWithAddMethod() {
        return new InitialisedSetPropertyWithAddMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithAddMethod target = new uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithAddMethod();
    
    public InitialisedSetPropertyWithAddMethodBuilder() {}
    
    public InitialisedSetPropertyWithAddMethodBuilder withString(java.lang.String string) {
        getTarget().addString(string);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithAddMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithAddMethod build() {
        return getTarget();
    }
}
