package integrationtest.generatedbuilder;

public class BeanWithAnInterfaceCollectionPropertyBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.BeanWithAnInterfaceCollectionProperty> {

    public static BeanWithAnInterfaceCollectionPropertyBuilder aBeanWithAnInterfaceCollectionProperty() {
        return new BeanWithAnInterfaceCollectionPropertyBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithAnInterfaceCollectionProperty target = new uk.co.buildergenerator.testmodel.BeanWithAnInterfaceCollectionProperty();
    
    public BeanWithAnInterfaceCollectionPropertyBuilder() {}
    
    public BeanWithAnInterfaceCollectionPropertyBuilder withAnInterfacesNullList(uk.co.buildergenerator.testmodel.AnInterface anInterfacesNullList) {
        if (getTarget().getAnInterfacesNullList() == null) {
            getTarget().setAnInterfacesNullList(new java.util.ArrayList<uk.co.buildergenerator.testmodel.AnInterface>());
        }        
        getTarget().getAnInterfacesNullList().add(anInterfacesNullList);
        return this;
    }
    
    public BeanWithAnInterfaceCollectionPropertyBuilder withAnInterfacesArrayList(uk.co.buildergenerator.testmodel.AnInterface anInterfacesArrayList) {
        getTarget().getAnInterfacesArrayList().add(anInterfacesArrayList);
        return this;
    }
    
    public BeanWithAnInterfaceCollectionPropertyBuilder withAnInterfacesArrayListWithAddMethod(uk.co.buildergenerator.testmodel.AnInterface anInterfacesArrayListWithAddMethod) {
        getTarget().addAnInterfacesArrayListWithAddMethod(anInterfacesArrayListWithAddMethod);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BeanWithAnInterfaceCollectionProperty getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithAnInterfaceCollectionProperty build() {
        return getTarget();
    }
}
