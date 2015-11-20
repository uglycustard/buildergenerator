package integrationtest.generatedbuilder;

public class InitialisedListPropertyWithAddMethodAndSetListMethodBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethodAndSetListMethod> {

    public static InitialisedListPropertyWithAddMethodAndSetListMethodBuilder anInitialisedListPropertyWithAddMethodAndSetListMethod() {
        return new InitialisedListPropertyWithAddMethodAndSetListMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethodAndSetListMethod target = new uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethodAndSetListMethod();
    
    public InitialisedListPropertyWithAddMethodAndSetListMethodBuilder() {}
    
    public InitialisedListPropertyWithAddMethodAndSetListMethodBuilder withString(java.lang.String string) {
        getTarget().addString(string);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethodAndSetListMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethodAndSetListMethod build() {
        return getTarget();
    }
}
