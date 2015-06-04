package integrationtest.generatedbuilder;

public class SubClassOfInitialisedListPropertyWithSetListMethodBuilder {

    public static SubClassOfInitialisedListPropertyWithSetListMethodBuilder aSubClassOfInitialisedListPropertyWithSetListMethod() {
        return new SubClassOfInitialisedListPropertyWithSetListMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.SubClassOfInitialisedListPropertyWithSetListMethod target = new uk.co.buildergenerator.testmodel.SubClassOfInitialisedListPropertyWithSetListMethod();
    
    public SubClassOfInitialisedListPropertyWithSetListMethodBuilder() {}
    
    public SubClassOfInitialisedListPropertyWithSetListMethodBuilder withString(java.lang.String string) {
        getTarget().getStrings().add(string);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.SubClassOfInitialisedListPropertyWithSetListMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.SubClassOfInitialisedListPropertyWithSetListMethod build() {
        return getTarget();
    }
}
