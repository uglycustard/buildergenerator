package uk.co.buildergenerator.testmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings({"rawtypes", "unchecked"})
public class BeanWithNonGenericCollections {

    private List things;
    private Set stuff;
    private List beans = new ArrayList();

    public List getThings() {
        return things;
    }

    public void setThings(List things) {
        this.things = things;
    }

    public Set getStuff() {
        return stuff;
    }

    public void setStuff(Set stuff) {
        this.stuff = stuff;
    }

    public List getBeans() {
        return beans;
    }

    public void addBean(Object bean) {
        this.beans.add(bean);
    }
}
