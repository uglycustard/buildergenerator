package uk.co.buildergenerator.testmodel;

import java.util.LinkedList;

public class NullLinkedListPropertyWithSetLinkedListMethod {

    private LinkedList<String> strings;

    public LinkedList<String> getStrings() {
        return strings;
    }

    public void setStrings(LinkedList<String> strings) {
        this.strings = strings;
    }
    
}
