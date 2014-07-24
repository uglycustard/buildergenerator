package uk.co.buildergenerator.testmodel;

import uk.co.buildergenerator.testmodel.BeanWithNestedClass.NestedClass;

public class BeanWithNestedClassProperty {

    private NestedClass nestedClass;

    public NestedClass getNestedClass() {
        return nestedClass;
    }

    public void setNestedClass(NestedClass nestedClass) {
        this.nestedClass = nestedClass;
    }
    
}
