package uk.co.buildergenerator.testmodel;

import java.util.ArrayList;
import java.util.List;

public class Address {

    private List<House> houses = new ArrayList<House>();
    
    public List<House> getHouses() {
        return houses;
    }
    
    public void addHouse(House house) {
        houses.add(house);
    }

}
