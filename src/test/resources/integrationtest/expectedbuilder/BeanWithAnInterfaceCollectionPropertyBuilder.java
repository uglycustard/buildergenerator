package integrationtest.generatedbuilder;

public class BeanWithAnInterfaceCollectionPropertyBuilder {

    public static BeanWithAnInterfaceCollectionPropertyBuilder aBeanWithAnInterfaceCollectionProperty() {
        return new BeanWithAnInterfaceCollectionPropertyBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithAnInterfaceCollectionProperty target = new uk.co.buildergenerator.testmodel.BeanWithAnInterfaceCollectionProperty();
    
    public BeanWithAnInterfaceCollectionPropertyBuilder() {}
    
    public BeanWithAnInterfaceCollectionPropertyBuilder withAnInterfacesNullList(uk.co.buildergenerator.testmodel.AnInterface anInterfacesNullList) {
        if (target.getAnInterfacesNullList() == null) {
            target.setAnInterfacesNullList(new java.util.ArrayList<uk.co.buildergenerator.testmodel.AnInterface>());
        }        
        target.getAnInterfacesNullList().add(anInterfacesNullList);
        return this;
    }
    
    public BeanWithAnInterfaceCollectionPropertyBuilder withAnInterfacesArrayList(uk.co.buildergenerator.testmodel.AnInterface anInterfacesArrayList) {
        target.getAnInterfacesArrayList().add(anInterfacesArrayList);
        return this;
    }
    
    public BeanWithAnInterfaceCollectionPropertyBuilder withAnInterfacesArrayListWithAddMethod(uk.co.buildergenerator.testmodel.AnInterface anInterfacesArrayListWithAddMethod) {
        target.addAnInterfacesArrayListWithAddMethod(anInterfacesArrayListWithAddMethod);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithAnInterfaceCollectionProperty build() {
        return target;
    }
}
