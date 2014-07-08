package integrationtest.generatedbuilder;

public class InitialisedSetPropertyWithAddMethodBuilder {

    public static InitialisedSetPropertyWithAddMethodBuilder anInitialisedSetPropertyWithAddMethod() {
        return new InitialisedSetPropertyWithAddMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithAddMethod target = new uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithAddMethod();
    
    public InitialisedSetPropertyWithAddMethodBuilder() {}
    
    public InitialisedSetPropertyWithAddMethodBuilder withString(java.lang.String string) {
        target.addString(string);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithAddMethod build() {
        return target;
    }
}
