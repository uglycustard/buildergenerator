package uk.co.buildergenerator.testmodel;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCar {

    private String genericCarProperty;
    
    // Non-JavaBean property
    private Map<String, String> accessories = new HashMap<String, String>();

    public String getGenericCarProperty() {
        return genericCarProperty;
    }

    public void setGenericCarProperty(String genericCarProperty) {
        this.genericCarProperty = genericCarProperty;
    }

    public void addAccessory(String name, String value) {
        accessories.put(name, value);
    }
    
    public String getAccessoryValue(String name) {
        return accessories.get(name);
    }
    
}
