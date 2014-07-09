package uk.co.buildergenerator;

import java.util.ArrayList;
import java.util.List;

class ClassesToIgnore {

    private final List<String> toBeIgnored = new ArrayList<String>(); 
    
    {
        toBeIgnored.add("java*");
        toBeIgnored.add("org.joda.time*");
    }
    
    void add(String toIgnore) {
        this.toBeIgnored.add(toIgnore);
    }

    boolean isIgnored(Class<?> targetClass) {
        
        String canonicalName = targetClass.getCanonicalName();
        for (String ignored : toBeIgnored) {
            
            if ( wildCardMatch(ignored, canonicalName) || equalityMatch(ignored, canonicalName) )  {
                return true;
            }
        }
        
        return false;
    }

    private boolean wildCardMatch(String ignored, String targetClassCanonicalName) {
        return ignored.endsWith("*") && targetClassCanonicalName.startsWith(ignored.substring(0, ignored.length()-1));
    }
    
    private boolean equalityMatch(String ignored, String targetClassCanonicalName) {
        return targetClassCanonicalName.equals(ignored);
    }

}
