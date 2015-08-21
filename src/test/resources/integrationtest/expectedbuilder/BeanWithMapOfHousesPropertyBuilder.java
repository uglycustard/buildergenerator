package integrationtest.generatedbuilder;

public class BeanWithMapOfHousesPropertyBuilder {

    public static BeanWithMapOfHousesPropertyBuilder aBeanWithMapOfHousesProperty() {

        return new BeanWithMapOfHousesPropertyBuilder();
    }

    private uk.co.buildergenerator.testmodel.BeanWithMapOfHousesProperty target = new uk.co.buildergenerator.testmodel.BeanWithMapOfHousesProperty();

    public BeanWithMapOfHousesPropertyBuilder() {

    }

    public BeanWithMapOfHousesPropertyBuilder withMapOfHouse(
            java.util.Map<java.lang.String, uk.co.buildergenerator.testmodel.House> mapOfHouse) {

        getTarget().setMapOfHouse(mapOfHouse);
        return this;
    }

    protected uk.co.buildergenerator.testmodel.BeanWithMapOfHousesProperty getTarget() {

        return target;
    }

    public uk.co.buildergenerator.testmodel.BeanWithMapOfHousesProperty build() {

        return getTarget();
    }
}
