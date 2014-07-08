package integrationtest.generatedbuilder;

public class SubClassOfInitialisedListPropertyWithSetListMethodBuilder {

    public static SubClassOfInitialisedListPropertyWithSetListMethodBuilder aSubClassOfInitialisedListPropertyWithSetListMethod() {
        return new SubClassOfInitialisedListPropertyWithSetListMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.SubClassOfInitialisedListPropertyWithSetListMethod target = new uk.co.buildergenerator.testmodel.SubClassOfInitialisedListPropertyWithSetListMethod();
    
    public SubClassOfInitialisedListPropertyWithSetListMethodBuilder() {}
    
    public SubClassOfInitialisedListPropertyWithSetListMethodBuilder withString(java.lang.String string) {
        target.getStrings().add(string);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.SubClassOfInitialisedListPropertyWithSetListMethod build() {
        return target;
    }
}
