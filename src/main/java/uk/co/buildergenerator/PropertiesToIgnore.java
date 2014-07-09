package uk.co.buildergenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PropertiesToIgnore {
    
    private final Map<Class<?>, List<String>> ignoreProperties = new HashMap<Class<?>, List<String>>();

    void addPropertyToIgnore(Class<?> targetClass, String propertyName) {
        if (ignoreProperties.get(targetClass) == null) {
            ignoreProperties.put(targetClass, new ArrayList<String>());
        }
        ignoreProperties.get(targetClass).add(propertyName);
    }
    
    boolean isPropertyIgnored(Class<?> targetClass, String propertyName) {
        return "class".equals(propertyName) || ignoreProperties.get(targetClass) != null && ignoreProperties.get(targetClass).contains(propertyName);
    }

}
