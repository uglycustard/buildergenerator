package uk.co.buildergenerator.testmodel;

import java.util.ArrayList;
import java.util.List;

public class InitialisedListPropertyWithSetListMethod {

    private List<String> strings = new ArrayList<String>();

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }
    
}
