package integrationtest.generatedbuilder;

public class InitialisedSetPropertyWithSetSetMethodBuilder {

    public static InitialisedSetPropertyWithSetSetMethodBuilder anInitialisedSetPropertyWithSetSetMethod() {
        return new InitialisedSetPropertyWithSetSetMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithSetSetMethod target = new uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithSetSetMethod();
    
    public InitialisedSetPropertyWithSetSetMethodBuilder() {}
    
    public InitialisedSetPropertyWithSetSetMethodBuilder withString(java.lang.String string) {
        getTarget().getStrings().add(string);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithSetSetMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.InitialisedSetPropertyWithSetSetMethod build() {
        return getTarget();
    }
}
