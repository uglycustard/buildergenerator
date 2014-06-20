package integrationtest.generatedbuilder;

public class InitialisedListPropertyWithAddMethodBuilder {

    public static InitialisedListPropertyWithAddMethodBuilder anInitialisedListPropertyWithAddMethod() {
        return new InitialisedListPropertyWithAddMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethod target = new uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethod();
    
    private InitialisedListPropertyWithAddMethodBuilder() {}
    
    public InitialisedListPropertyWithAddMethodBuilder withString(java.lang.String string) {
        target.addString(string);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethod build() {
        return target;
    }
}
