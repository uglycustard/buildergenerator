package uk.co.buildergenerator.testmodel;

import org.apache.commons.collections.Bag;

public class NullUnrecognisedCollectionPropertyWithSetUnrecognisedCollectionMethod {

    private Bag bag;

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }
    
}
