package uk.co.buildergenerator.testmodel;

import java.util.TreeSet;

public class NullTreeSetPropertyWithSetTreeSetMethod {

    private TreeSet<String> strings;

    public TreeSet<String> getStrings() {
        return strings;
    }

    public void setStrings(TreeSet<String> strings) {
        this.strings = strings;
    }
    
}
