package uk.co.buildergenerator.testmodel;

import java.util.HashSet;
import java.util.Set;

public class InitialisedSetPropertyWithSetSetMethod {

    private Set<String> strings = new HashSet<String>();

    public Set<String> getStrings() {
        return strings;
    }

    public void setStrings(Set<String> strings) {
        this.strings = strings;
    }
    
}
