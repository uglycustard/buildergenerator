package uk.co.buildergenerator.testmodel;

import java.util.PriorityQueue;
import java.util.Queue;

public class InitialisedQueuePropertyWithAddMethod {

    private Queue<String> strings = new PriorityQueue<String>();

    public Queue<String> getStrings() {
        return strings;
    }

    public void addString(String string) {
        strings.add(string);
    }
    
}
