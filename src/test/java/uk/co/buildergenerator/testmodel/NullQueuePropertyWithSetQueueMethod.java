package uk.co.buildergenerator.testmodel;

import java.util.Queue;

public class NullQueuePropertyWithSetQueueMethod {

    private Queue<String> strings;

    public Queue<String> getStrings() {
        return strings;
    }

    public void setStrings(Queue<String> strings) {
        this.strings = strings;
    }
    
}
