package integrationtest.generatedbuilder;

public class NullLinkedListPropertyWithSetLinkedListMethodBuilder {

    public static NullLinkedListPropertyWithSetLinkedListMethodBuilder aNullLinkedListPropertyWithSetLinkedListMethod() {
        return new NullLinkedListPropertyWithSetLinkedListMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullLinkedListPropertyWithSetLinkedListMethod target = new uk.co.buildergenerator.testmodel.NullLinkedListPropertyWithSetLinkedListMethod();
    
    public NullLinkedListPropertyWithSetLinkedListMethodBuilder() {}
    
    public NullLinkedListPropertyWithSetLinkedListMethodBuilder withString(java.lang.String string) {
        if (target.getStrings() == null) {
            target.setStrings(new java.util.LinkedList<java.lang.String>());
        }        
        target.getStrings().add(string);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.NullLinkedListPropertyWithSetLinkedListMethod build() {
        return target;
    }
}