package integrationtest.generatedbuilder;

public class BeanWithMapPropertyBuilder {

    public static BeanWithMapPropertyBuilder aBeanWithMapProperty() {
        return new BeanWithMapPropertyBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithMapProperty target = new uk.co.buildergenerator.testmodel.BeanWithMapProperty();
    
    public BeanWithMapPropertyBuilder() {}
    
    public BeanWithMapPropertyBuilder withMapOfStrings(java.util.Map<java.lang.String, java.lang.String> mapOfStrings) {
        getTarget().setMapOfStrings(mapOfStrings);
        return this;
    }
    
    public BeanWithMapPropertyBuilder withMapOfAnything(java.util.Map mapOfAnything) {
        getTarget().setMapOfAnything(mapOfAnything);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BeanWithMapProperty getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithMapProperty build() {
        return getTarget();
    }
}
