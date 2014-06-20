package integrationtest.generatedbuilder;

public class NullSetPropertyWithSetSetMethodBuilder {

    public static NullSetPropertyWithSetSetMethodBuilder aNullSetPropertyWithSetSetMethod() {
        return new NullSetPropertyWithSetSetMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullSetPropertyWithSetSetMethod target = new uk.co.buildergenerator.testmodel.NullSetPropertyWithSetSetMethod();
    
    private NullSetPropertyWithSetSetMethodBuilder() {}
    
    public NullSetPropertyWithSetSetMethodBuilder withString(java.lang.String string) {
        if (target.getStrings() == null) {
            target.setStrings(new java.util.HashSet<java.lang.String>());
        }        
        target.getStrings().add(string);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.NullSetPropertyWithSetSetMethod build() {
        return target;
    }
}