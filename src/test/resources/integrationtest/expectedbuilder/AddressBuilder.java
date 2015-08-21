package integrationtest.generatedbuilder;

public class AddressBuilder {

    public static AddressBuilder anAddress() {

        return new AddressBuilder();
    }

    private uk.co.buildergenerator.testmodel.Address target = new uk.co.buildergenerator.testmodel.Address();

    public AddressBuilder() {

    }

    public AddressBuilder withHouse(integrationtest.generatedbuilder.HouseBuilder house) {

        getTarget().addHouse(house.build());
        return this;
    }

    protected uk.co.buildergenerator.testmodel.Address getTarget() {

        return target;
    }

    public uk.co.buildergenerator.testmodel.Address build() {

        return getTarget();
    }
}
