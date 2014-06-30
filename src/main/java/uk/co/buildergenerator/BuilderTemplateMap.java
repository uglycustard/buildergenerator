package uk.co.buildergenerator;

import java.util.HashMap;

class BuilderTemplateMap extends HashMap<String, Object> {


    private static final long serialVersionUID = 1L;
    
    static final String TARGET_CLASS_NAME_MAP_KEY = "targetClass";
    static final String FULLY_QUALIFIED_TARGET_CLASS_NAME_MAP_KEY = "fullyQualifiedTargetClass";
    static final String WITH_METHOD_LIST_MAP_KEY = "withMethodList";
    static final String BUILDER_PACKAGE_MAP_KEY = "builderPackage";
    static final String FACTORY_METHOD_PREFIX_MAP_KEY = "factoryMethodPrefix";
    
    BuilderTemplateMap(Class<?> targetClass, String builderPackage) {
        
        put(BUILDER_PACKAGE_MAP_KEY, builderPackage);
        put(TARGET_CLASS_NAME_MAP_KEY, targetClass.getSimpleName());
        put(FULLY_QUALIFIED_TARGET_CLASS_NAME_MAP_KEY, targetClass.getName());
        put(FACTORY_METHOD_PREFIX_MAP_KEY, startsWithVowel(targetClass.getSimpleName()) ? "an" : "a");
        put(WITH_METHOD_LIST_MAP_KEY, new WithMethodList(targetClass, builderPackage));
    }

    private static boolean startsWithVowel(String s) {

        return "aeiou".contains(s.substring(0, 1).toLowerCase());
    }

    String getTargetClassName() {
        return (String) get(TARGET_CLASS_NAME_MAP_KEY);
    }

    String getFullyQualifiedTargetClassName() {
        return (String) get(FULLY_QUALIFIED_TARGET_CLASS_NAME_MAP_KEY);
    }

    WithMethodList getWithMethodList() {
        return (WithMethodList) get(WITH_METHOD_LIST_MAP_KEY);
    }

    String getBuilderPackage() {
        return (String) get(BUILDER_PACKAGE_MAP_KEY);
    }

}
