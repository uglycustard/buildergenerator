package uk.co.buildergenerator.testmodel;

import java.util.HashMap;
import java.util.Map;

public class StringPropertyBeanWithSomethingElse {

    private String theString;
    private Map<String, String> somethingElse = new HashMap<String, String>();

    public String getTheString() {
        return theString;
    }

    public void setTheString(String theString) {
        this.theString = theString;
    }
    
    public void setSomethingElse(String key, String value) {
    	somethingElse.put(key, value);
    }
    
    public String getSomethingElse(String key) {
    	return somethingElse.get(key);
    }
    
}
