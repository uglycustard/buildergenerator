package uk.co.buildergenerator.testmodel;

import java.util.ArrayList;
import java.util.List;

public class InitialisedListPropertyWithAddMethod {

    private List<String> strings = new ArrayList<String>();

    public List<String> getStrings() {
        return strings;
    }

    public void addString(String string) {
        strings.add(string);
    }
    
}
