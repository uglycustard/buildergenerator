package uk.co.buildergenerator.testmodel;

import java.util.HashSet;
import java.util.Set;

public class InitialisedSetPropertyWithAddMethod {

    private Set<String> strings = new HashSet<String>();

    public Set<String> getStrings() {
        return strings;
    }

    public void addString(String string) {
        strings.add(string);
    }
    
}
