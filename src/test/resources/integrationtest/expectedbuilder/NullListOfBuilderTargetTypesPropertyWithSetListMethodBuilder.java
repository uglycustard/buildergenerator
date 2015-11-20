package integrationtest.generatedbuilder;

public class NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.NullListOfBuilderTargetTypesPropertyWithSetListMethod> {

    public static NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder aNullListOfBuilderTargetTypesPropertyWithSetListMethod() {
        return new NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullListOfBuilderTargetTypesPropertyWithSetListMethod target = new uk.co.buildergenerator.testmodel.NullListOfBuilderTargetTypesPropertyWithSetListMethod();
    
    public NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder() {}
    
    public NullListOfBuilderTargetTypesPropertyWithSetListMethodBuilder withHouse(integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.House> house) {
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