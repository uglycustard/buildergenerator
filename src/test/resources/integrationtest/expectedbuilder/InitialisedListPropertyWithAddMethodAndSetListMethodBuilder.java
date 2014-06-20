package integrationtest.generatedbuilder;

public class InitialisedListPropertyWithAddMethodAndSetListMethodBuilder {

    public static InitialisedListPropertyWithAddMethodAndSetListMethodBuilder anInitialisedListPropertyWithAddMethodAndSetListMethod() {
        return new InitialisedListPropertyWithAddMethodAndSetListMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethodAndSetListMethod target = new uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethodAndSetListMethod();
    
    private InitialisedListPropertyWithAddMethodAndSetListMethodBuilder() {}
    
    public InitialisedListPropertyWithAddMethodAndSetListMethodBuilder withString(java.lang.String string) {
        target.addString(string);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethodAndSetListMethod build() {
        return target;
    }
}
