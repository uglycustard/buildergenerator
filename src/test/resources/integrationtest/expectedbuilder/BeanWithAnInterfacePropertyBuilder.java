package integrationtest.generatedbuilder;

public class BeanWithAnInterfacePropertyBuilder {

    public static BeanWithAnInterfacePropertyBuilder aBeanWithAnInterfaceProperty() {
        return new BeanWithAnInterfacePropertyBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithAnInterfaceProperty target = new uk.co.buildergenerator.testmodel.BeanWithAnInterfaceProperty();
    
    public BeanWithAnInterfacePropertyBuilder() {}
    
    public BeanWithAnInterfacePropertyBuilder withAnInterface(uk.co.buildergenerator.testmodel.AnInterface anInterface) {
        getTarget().setAnInterface(anInterface);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BeanWithAnInterfaceProperty getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithAnInterfaceProperty build() {
        return getTarget();
    }
}
