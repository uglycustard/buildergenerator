package integrationtest.generatedbuilder;

public class HouseBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.House> {

    public static HouseBuilder aHouse() {
        return new HouseBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.House target = new uk.co.buildergenerator.testmodel.House();
    
    public HouseBuilder() {}
    
    public HouseBuilder withName(java.lang.String name) {
        getTarget().setName(name);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.House getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.House build() {
        return getTarget();
    }
}
