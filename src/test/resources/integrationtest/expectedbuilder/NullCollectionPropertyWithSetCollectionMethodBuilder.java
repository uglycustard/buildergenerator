package integrationtest.generatedbuilder;

public class NullCollectionPropertyWithSetCollectionMethodBuilder {

    public static NullCollectionPropertyWithSetCollectionMethodBuilder aNullCollectionPropertyWithSetCollectionMethod() {
        return new NullCollectionPropertyWithSetCollectionMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullCollectionPropertyWithSetCollectionMethod target = new uk.co.buildergenerator.testmodel.NullCollectionPropertyWithSetCollectionMethod();
    
    public NullCollectionPropertyWithSetCollectionMethodBuilder() {}
    
    public NullCollectionPropertyWithSetCollectionMethodBuilder withString(java.lang.String string) {
        if (getTarget().getStrings() == null) {
            getTarget().setStrings(new java.util.ArrayList<java.lang.String>());
        }        
        getTarget().getStrings().add(string);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.NullCollectionPropertyWithSetCollectionMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.NullCollectionPropertyWithSetCollectionMethod build() {
        return getTarget();
    }
}