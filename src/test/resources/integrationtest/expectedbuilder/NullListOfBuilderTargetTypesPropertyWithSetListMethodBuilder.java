package integrationtest.generatedbuilder;

public class NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder {

    public static NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder aNullListOfBuilderTargetTypesPropertyWithSetListMethod() {
        return new NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullListOfBuilderTargetTypesPropertyWithSetListMethod target = new uk.co.buildergenerator.testmodel.NullListOfBuilderTargetTypesPropertyWithSetListMethod();
    
    private NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder() {}
    
    public NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder withHouse(integrationtest.generatedbuilder.HouseBuilder house) {
        if (target.getHouses() == null) {
            target.setHouses(new java.util.ArrayList<uk.co.buildergenerator.testmodel.House>());
        }        
        target.getHouses().add(house.build());
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.NullListOfBuilderTargetTypesPropertyWithSetListMethod build() {
        return target;
    }
}