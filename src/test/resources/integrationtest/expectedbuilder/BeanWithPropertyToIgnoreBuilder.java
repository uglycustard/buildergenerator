package integrationtest.generatedbuilder;

public class BeanWithPropertyToIgnoreBuilder {

    public static BeanWithPropertyToIgnoreBuilder aBeanWithPropertyToIgnore() {
        return new BeanWithPropertyToIgnoreBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithPropertyToIgnore target = new uk.co.buildergenerator.testmodel.BeanWithPropertyToIgnore();
    
    public BeanWithPropertyToIgnoreBuilder() {}
    
    public BeanWithPropertyToIgnoreBuilder withProperty(java.lang.String property) {
        target.setProperty(property);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithPropertyToIgnore build() {
        return target;
    }
}
