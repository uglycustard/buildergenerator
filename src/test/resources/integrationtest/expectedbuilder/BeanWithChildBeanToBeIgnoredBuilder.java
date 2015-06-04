package integrationtest.generatedbuilder;

public class BeanWithChildBeanToBeIgnoredBuilder {

    public static BeanWithChildBeanToBeIgnoredBuilder aBeanWithChildBeanToBeIgnored() {
        return new BeanWithChildBeanToBeIgnoredBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithChildBeanToBeIgnored target = new uk.co.buildergenerator.testmodel.BeanWithChildBeanToBeIgnored();
    
    public BeanWithChildBeanToBeIgnoredBuilder() {}
    
    public BeanWithChildBeanToBeIgnoredBuilder withSomeProperty(java.lang.String someProperty) {
        getTarget().setSomeProperty(someProperty);
        return this;
    }
    
    public BeanWithChildBeanToBeIgnoredBuilder withBeanToBeIgnored(uk.co.buildergenerator.testmodel.BeanToBeIgnored beanToBeIgnored) {
        getTarget().setBeanToBeIgnored(beanToBeIgnored);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BeanWithChildBeanToBeIgnored getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithChildBeanToBeIgnored build() {
        return getTarget();
    }
}
