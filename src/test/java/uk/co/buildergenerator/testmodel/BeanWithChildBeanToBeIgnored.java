package uk.co.buildergenerator.testmodel;

public class BeanWithChildBeanToBeIgnored {

    private String someProperty;
    
    private BeanToBeIgnored beanToBeIgnored;

    public String getSomeProperty() {
        return someProperty;
    }

    public void setSomeProperty(String someProperty) {
        this.someProperty = someProperty;
    }

    public BeanToBeIgnored getBeanToBeIgnored() {
        return beanToBeIgnored;
    }

    public void setBeanToBeIgnored(BeanToBeIgnored beanToBeIgnored) {
        this.beanToBeIgnored = beanToBeIgnored;
    }
}
