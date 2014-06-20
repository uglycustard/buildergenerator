package uk.co.buildergenerator.testmodel;

import java.util.List;

public class NullListPropertyWithSetListMethod {

    private List<String> strings;

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }
    
}
