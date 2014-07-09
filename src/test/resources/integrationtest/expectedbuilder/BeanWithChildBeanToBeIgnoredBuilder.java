package integrationtest.generatedbuilder;

public class BeanWithChildBeanToBeIgnoredBuilder {

    public static BeanWithChildBeanToBeIgnoredBuilder aBeanWithChildBeanToBeIgnored() {
        return new BeanWithChildBeanToBeIgnoredBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithChildBeanToBeIgnored target = new uk.co.buildergenerator.testmodel.BeanWithChildBeanToBeIgnored();
    
    public BeanWithChildBeanToBeIgnoredBuilder() {}
    
    public BeanWithChildBeanToBeIgnoredBuilder withSomeProperty(java.lang.String someProperty) {
        target.setSomeProperty(someProperty);
        return this;
    }
    
    public BeanWithChildBeanToBeIgnoredBuilder withBeanToBeIgnored(uk.co.buildergenerator.testmodel.BeanToBeIgnored beanToBeIgnored) {
        target.setBeanToBeIgnored(beanToBeIgnored);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithChildBeanToBeIgnored build() {
        return target;
    }
}
