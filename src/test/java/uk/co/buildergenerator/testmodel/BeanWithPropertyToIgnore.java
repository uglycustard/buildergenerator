package uk.co.buildergenerator.testmodel;

public class BeanWithPropertyToIgnore {

    private String property;
    private String propertyToIgnore;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getPropertyToIgnore() {
        return propertyToIgnore;
    }

    public void setPropertyToIgnore(String propertyToIgnore) {
        this.propertyToIgnore = propertyToIgnore;
    }

}
