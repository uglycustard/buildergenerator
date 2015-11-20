package integrationtest.generatedbuilder;

public class InitialisedListPropertyWithSetListMethodBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.InitialisedListPropertyWithSetListMethod> {

    public static InitialisedListPropertyWithSetListMethodBuilder anInitialisedListPropertyWithSetListMethod() {
        return new InitialisedListPropertyWithSetListMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.InitialisedListPropertyWithSetListMethod target = new uk.co.buildergenerator.testmodel.InitialisedListPropertyWithSetListMethod();
    
    public InitialisedListPropertyWithSetListMethodBuilder() {}
    
    public InitialisedListPropertyWithSetListMethodBuilder withString(java.lang.String string) {
        getTarget().getStrings().add(string);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.InitialisedListPropertyWithSetListMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.InitialisedListPropertyWithSetListMethod build() {
        return getTarget();
    }
}
