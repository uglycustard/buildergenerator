package integrationtest.generatedbuilder;

public class NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder {

    public static NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder aNullListOfBuilderTargetTypesPropertyWithSetListMethod() {
        return new NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullListOfBuilderTargetTypesPropertyWithSetListMethod target = new uk.co.buildergenerator.testmodel.NullListOfBuilderTargetTypesPropertyWithSetListMethod();
    
    public NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder() {}
    
    public NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder withHouse(integrationtest.generatedbuilder.HouseBuilder house) {
        if (getTarget().getHouses() == null) {
            getTarget().setHouses(new java.util.ArrayList<uk.co.buildergenerator.testmodel.House>());
        }        
        getTarget().getHouses().add(house.build());
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.NullListOfBuilderTargetTypesPropertyWithSetListMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.NullListOfBuilderTargetTypesPropertyWithSetListMethod build() {
        return getTarget();
    }
}