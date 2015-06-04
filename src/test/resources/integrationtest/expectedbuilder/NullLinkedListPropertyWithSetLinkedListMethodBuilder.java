package integrationtest.generatedbuilder;

public class NullLinkedListPropertyWithSetLinkedListMethodBuilder {

    public static NullLinkedListPropertyWithSetLinkedListMethodBuilder aNullLinkedListPropertyWithSetLinkedListMethod() {
        return new NullLinkedListPropertyWithSetLinkedListMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullLinkedListPropertyWithSetLinkedListMethod target = new uk.co.buildergenerator.testmodel.NullLinkedListPropertyWithSetLinkedListMethod();
    
    public NullLinkedListPropertyWithSetLinkedListMethodBuilder() {}
    
    public NullLinkedListPropertyWithSetLinkedListMethodBuilder withString(java.lang.String string) {
        if (getTarget().getStrings() == null) {
            getTarget().setStrings(new java.util.LinkedList<java.lang.String>());
        }        
        getTarget().getStrings().add(string);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.NullLinkedListPropertyWithSetLinkedListMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.NullLinkedListPropertyWithSetLinkedListMethod build() {
        return getTarget();
    }
}