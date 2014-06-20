package integrationtest.generatedbuilder;

public class InitialisedSetPropertyWithSetSetMethodBuilder {

    public static InitialisedSetPropertyWithSetSetMethodBuilder anInitialisedSetPropertyWithSetSetMethod() {
        return new InitialisedSetPropertyWithSetSetMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithSetSetMethod target = new uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithSetSetMethod();
    
    private InitialisedSetPropertyWithSetSetMethodBuilder() {}
    
    public InitialisedSetPropertyWithSetSetMethodBuilder withString(java.lang.String string) {
        target.getStrings().add(string);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithSetSetMethod build() {
        return target;
    }
}
