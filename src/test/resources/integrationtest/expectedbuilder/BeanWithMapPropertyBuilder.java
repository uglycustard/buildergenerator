package integrationtest.generatedbuilder;

public class BeanWithMapPropertyBuilder {

    public static BeanWithMapPropertyBuilder aBeanWithMapProperty() {
        return new BeanWithMapPropertyBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithMapProperty target = new uk.co.buildergenerator.testmodel.BeanWithMapProperty();
    
    public BeanWithMapPropertyBuilder() {}
    
    public BeanWithMapPropertyBuilder withMapOfStrings(java.util.Map<java.lang.String, java.lang.String> mapOfStrings) {
        target.setMapOfStrings(mapOfStrings);
        return this;
    }
    
    public BeanWithMapPropertyBuilder withMapOfAnything(java.util.Map mapOfAnything) {
        target.setMapOfAnything(mapOfAnything);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithMapProperty build() {
        return target;
    }
}
