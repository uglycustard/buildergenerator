package integrationtest.generatedbuilder;

public class NullSetPropertyWithSetSetMethodBuilder {

    public static NullSetPropertyWithSetSetMethodBuilder aNullSetPropertyWithSetSetMethod() {
        return new NullSetPropertyWithSetSetMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullSetPropertyWithSetSetMethod target = new uk.co.buildergenerator.testmodel.NullSetPropertyWithSetSetMethod();
    
    public NullSetPropertyWithSetSetMethodBuilder() {}
    
    public NullSetPropertyWithSetSetMethodBuilder withString(java.lang.String string) {
        if (getTarget().getStrings() == null) {
            getTarget().setStrings(new java.util.HashSet<java.lang.String>());
        }        
        getTarget().getStrings().add(string);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.NullSetPropertyWithSetSetMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.NullSetPropertyWithSetSetMethod build() {
        return getTarget();
    }
}