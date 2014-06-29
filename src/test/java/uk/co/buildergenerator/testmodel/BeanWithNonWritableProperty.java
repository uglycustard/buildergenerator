package uk.co.buildergenerator.testmodel;

public class BeanWithNonWritableProperty {

    private String writableProperty;
    private String nonWritableProperty = "some value";

    public String getWritableProperty() {
        return writableProperty;
    }

    public void setWritableProperty(String writableProperty) {
        this.writableProperty = writableProperty;
    }

    public String getNonWritableProperty() {
        return nonWritableProperty;
    }

}
