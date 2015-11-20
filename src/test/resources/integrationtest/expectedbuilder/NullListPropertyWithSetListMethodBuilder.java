package integrationtest.generatedbuilder;

public class NullListPropertyWithSetListMethodBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.NullListPropertyWithSetListMethod> {

    public static NullListPropertyWithSetListMethodBuilder aNullListPropertyWithSetListMethod() {
        return new NullListPropertyWithSetListMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullListPropertyWithSetListMethod target = new uk.co.buildergenerator.testmodel.NullListPropertyWithSetListMethod();
    
    public NullListPropertyWithSetListMethodBuilder() {}
    
    public NullListPropertyWithSetListMethodBuilder withString(java.lang.String string) {
        if (getTarget().getStrings() == null) {
            getTarget().setStrings(new java.util.ArrayList<java.lang.String>());
        }        
        getTarget().getStrings().add(string);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.NullListPropertyWithSetListMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.NullListPropertyWithSetListMethod build() {
        return getTarget();
    }
}