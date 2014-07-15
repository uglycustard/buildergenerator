package uk.co.buildergenerator.testmodel;

import java.util.Collection;

public class NullCollectionPropertyWithSetCollectionMethod {

    private Collection<String> strings;

    public Collection<String> getStrings() {
        return strings;
    }

    public void setStrings(Collection<String> strings) {
        this.strings = strings;
    }
    
}
