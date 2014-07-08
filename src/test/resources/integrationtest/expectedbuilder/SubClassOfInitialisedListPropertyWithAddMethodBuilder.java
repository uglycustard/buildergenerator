package integrationtest.generatedbuilder;

public class SubClassOfInitialisedListPropertyWithAddMethodBuilder {

    public static SubClassOfInitialisedListPropertyWithAddMethodBuilder aSubClassOfInitialisedListPropertyWithAddMethod() {
        return new SubClassOfInitialisedListPropertyWithAddMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.SubClassOfInitialisedListPropertyWithAddMethod target = new uk.co.buildergenerator.testmodel.SubClassOfInitialisedListPropertyWithAddMethod();
    
    public SubClassOfInitialisedListPropertyWithAddMethodBuilder() {}
    
    public SubClassOfInitialisedListPropertyWithAddMethodBuilder withString(java.lang.String string) {
        target.addString(string);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.SubClassOfInitialisedListPropertyWithAddMethod build() {
        return target;
    }
}
