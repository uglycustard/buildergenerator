package uk.co.buildergenerator.testmodel;

import java.util.ArrayList;
import java.util.List;

public class BeanWithAnInterfaceCollectionProperty {


    private List<AnInterface> anInterfacesArrayList = new ArrayList<AnInterface>();
    
    private List<AnInterface> anInterfacesNullList;
    
    private List<AnInterface> anInterfacesArrayListWithAddMethod = new ArrayList<AnInterface>();


    public List<AnInterface> getAnInterfacesArrayList() {
        return anInterfacesArrayList;
    }

    public void setAnInterfacesArrayList(List<AnInterface> anInterfacesArrayList) {
        this.anInterfacesArrayList = anInterfacesArrayList;
    }

    public List<AnInterface> getAnInterfacesNullList() {
        return anInterfacesNullList;
    }

    public void setAnInterfacesNullList(List<AnInterface> anInterfacesNullList) {
        this.anInterfacesNullList = anInterfacesNullList;
    }

    public List<AnInterface> getAnInterfacesArrayListWithAddMethod() {
        return anInterfacesArrayListWithAddMethod;
    }
    
    public void addAnInterfacesArrayListWithAddMethod(AnInterface anInterface) {
        anInterfacesArrayListWithAddMethod.add(anInterface);
    }
    
}
