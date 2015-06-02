package uk.co.buildergenerator.testmodel;

import java.util.Map;

import uk.co.buildergenerator.BuilderGenerator;

public class BeanWithMapProperty {

    private Map<String, String> mapOfStrings;
    private Map mapOfAnything;

    public Map<String, String> getMapOfStrings() {
        return mapOfStrings;
    }

    public void setMapOfStrings(Map<String, String> mapOfStrings) {
        this.mapOfStrings = mapOfStrings;
    }
    
    public Map getMapOfAnything() {
        return mapOfAnything;
    }

    public void setMapOfAnything(Map mapOfAnything) {
        this.mapOfAnything = mapOfAnything;
    }

    public static void main(String[] args) {
        BuilderGenerator.generateBuilders(BeanWithMapProperty.class);
    }
}
