package uk.co.buildergenerator.testmodel;

import java.util.Map;

import uk.co.buildergenerator.BuilderGenerator;

public class BeanWithMapOfHousesProperty {

    private Map<String, House> mapOfHouse;

    public static void main(String[] args) {
        BuilderGenerator.generateBuilders(BeanWithMapOfHousesProperty.class);
    }
    
    public Map<String, House> getMapOfHouse() {
    
        return mapOfHouse;
    }
    
    public void setMapOfHouse(Map<String, House> mapOfHouse) {
    
        this.mapOfHouse = mapOfHouse;
    }
}
