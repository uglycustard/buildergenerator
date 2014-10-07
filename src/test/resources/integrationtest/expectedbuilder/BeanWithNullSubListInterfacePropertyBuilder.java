package integrationtest.generatedbuilder;

public class BeanWithNullSubListInterfacePropertyBuilder {

    public static BeanWithNullSubListInterfacePropertyBuilder aBeanWithNullSubListInterfaceProperty() {
        return new BeanWithNullSubListInterfacePropertyBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithNullSubListInterfaceProperty target = new uk.co.buildergenerator.testmodel.BeanWithNullSubListInterfaceProperty();
    
    public BeanWithNullSubListInterfacePropertyBuilder() {}
    
    public BeanWithNullSubListInterfacePropertyBuilder withSubList(uk.co.buildergenerator.testmodel.SubList subList) {
        target.setSubList(subList);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithNullSubListInterfaceProperty build() {
        return target;
    }
}