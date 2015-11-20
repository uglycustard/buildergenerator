package integrationtest.generatedbuilder;

public class BeanWithNullSubListInterfacePropertyBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.BeanWithNullSubListInterfaceProperty> {

    public static BeanWithNullSubListInterfacePropertyBuilder aBeanWithNullSubListInterfaceProperty() {
        return new BeanWithNullSubListInterfacePropertyBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithNullSubListInterfaceProperty target = new uk.co.buildergenerator.testmodel.BeanWithNullSubListInterfaceProperty();
    
    public BeanWithNullSubListInterfacePropertyBuilder() {}
    
    public BeanWithNullSubListInterfacePropertyBuilder withSubList(uk.co.buildergenerator.testmodel.SubList subList) {
        getTarget().setSubList(subList);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BeanWithNullSubListInterfaceProperty getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithNullSubListInterfaceProperty build() {
        return getTarget();
    }
}