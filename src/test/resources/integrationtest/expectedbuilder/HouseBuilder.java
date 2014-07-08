package uk.co.buildergenerator.testmodelbuilder;

public class HouseBuilder {

    public static HouseBuilder aHouse() {
        return new HouseBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.House target = new uk.co.buildergenerator.testmodel.House();
    
    public HouseBuilder() {}
    
    public HouseBuilder withName(java.lang.String name) {
        target.setName(name);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.House build() {
        return target;
    }
}
