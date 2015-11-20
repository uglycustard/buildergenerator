package integrationtest.generatedbuilder;

public class InitialisedListPropertyWithAddMethodBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethod> {

    public static InitialisedListPropertyWithAddMethodBuilder anInitialisedListPropertyWithAddMethod() {
        return new InitialisedListPropertyWithAddMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethod target = new uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethod();
    
    public InitialisedListPropertyWithAddMethodBuilder() {}
    
    public InitialisedListPropertyWithAddMethodBuilder withString(java.lang.String string) {
        getTarget().addString(string);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethod build() {
        return getTarget();
    }
}
