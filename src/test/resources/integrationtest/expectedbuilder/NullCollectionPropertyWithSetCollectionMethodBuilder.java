package integrationtest.generatedbuilder;

public class NullCollectionPropertyWithSetCollectionMethodBuilder {

    public static NullCollectionPropertyWithSetCollectionMethodBuilder aNullCollectionPropertyWithSetCollectionMethod() {
        return new NullCollectionPropertyWithSetCollectionMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullCollectionPropertyWithSetCollectionMethod target = new uk.co.buildergenerator.testmodel.NullCollectionPropertyWithSetCollectionMethod();
    
    public NullCollectionPropertyWithSetCollectionMethodBuilder() {}
    
    public NullCollectionPropertyWithSetCollectionMethodBuilder withString(java.lang.String string) {
        if (target.getStrings() == null) {
            target.setStrings(new java.util.ArrayList<java.lang.String>());
        }        
        target.getStrings().add(string);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.NullCollectionPropertyWithSetCollectionMethod build() {
        return target;
    }
}