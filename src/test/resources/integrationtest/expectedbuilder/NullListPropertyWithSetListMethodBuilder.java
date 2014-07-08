package integrationtest.generatedbuilder;

public class NullListPropertyWithSetListMethodBuilder {

    public static NullListPropertyWithSetListMethodBuilder aNullListPropertyWithSetListMethod() {
        return new NullListPropertyWithSetListMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullListPropertyWithSetListMethod target = new uk.co.buildergenerator.testmodel.NullListPropertyWithSetListMethod();
    
    public NullListPropertyWithSetListMethodBuilder() {}
    
    public NullListPropertyWithSetListMethodBuilder withString(java.lang.String string) {
        if (target.getStrings() == null) {
            target.setStrings(new java.util.ArrayList<java.lang.String>());
        }        
        target.getStrings().add(string);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.NullListPropertyWithSetListMethod build() {
        return target;
    }
}