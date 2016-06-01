package uk.co.buildergenerator;

import java.util.HashMap;
import java.util.Map;

class BuilderTemplateMap extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;
    
    static final String TARGET_CLASS_NAME_MAP_KEY = "targetClass";
    static final String FULLY_QUALIFIED_TARGET_CLASS_NAME_MAP_KEY = "fullyQualifiedTargetClass";
    static final String WITH_METHOD_LIST_MAP_KEY = "withMethodList";
    static final String BUILDER_PACKAGE_MAP_KEY = "builderPackage";
    static final String FACTORY_METHOD_PREFIX_MAP_KEY = "factoryMethodPrefix";
    static final String SUPER_CLASS_MAP_KEY = "superClass";
    static final String SUPER_CLASS_SPECIFIED_MAP_KEY = "superClassSpecified";
    static final String GENERATION_GAP_BASE_BUILDER = "generationGapBaseBuilder";
    static final String GENERATION_GAP_BUILDER = "generationGapBuilder";
    static final String GENERATION_GAP_BASE_BUILDER_PACKAGE = "generationGapBaseBuilderPackage";
    static final String BUILDER_INTERFACE_MAP_KEY = "builderInterface";
    
    BuilderTemplateMap(Class<?> targetClass, String builderPackage, PropertiesToIgnore propertiesToIgnore, ClassesToIgnore classesToIgnore, Map<Class<?>, String> collectionInitialisationTypes) {
        
        put(BUILDER_PACKAGE_MAP_KEY, builderPackage);
        put(TARGET_CLASS_NAME_MAP_KEY, targetClass.getSimpleName());
        put(FULLY_QUALIFIED_TARGET_CLASS_NAME_MAP_KEY, targetClass.getName());
        put(FACTORY_METHOD_PREFIX_MAP_KEY, startsWithVowel(targetClass.getSimpleName()) ? "an" : "a");
        put(WITH_METHOD_LIST_MAP_KEY, new WithMethodList(targetClass, builderPackage, propertiesToIgnore, classesToIgnore, collectionInitialisationTypes));
        put(GENERATION_GAP_BASE_BUILDER, false);
        put(GENERATION_GAP_BUILDER, false);
        put(SUPER_CLASS_SPECIFIED_MAP_KEY, false);
        setBuilderInterface(builderPackage);
    }

    private void setBuilderInterface(String builderPackage) {
        put(BUILDER_INTERFACE_MAP_KEY, builderPackage + ".Builder");
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

    String getGenerationGapBaseBuilderPackage() {
        return (String) get(GENERATION_GAP_BASE_BUILDER_PACKAGE);
    }

    boolean isGeneratioGapBaseBuilder() {
        return (Boolean) get(GENERATION_GAP_BASE_BUILDER);
    }

    boolean isGeneratioGapBuilder() {
        return (Boolean) get(GENERATION_GAP_BUILDER);
    }

    void setAsGenerationGapBaseBuilder() {
        put(GENERATION_GAP_BASE_BUILDER, true);
        put(GENERATION_GAP_BUILDER, false);
    }
    
    void setGenerationGapBaseBuilderPackage(String generationGapBaseBuilderPackage) {
        put(GENERATION_GAP_BASE_BUILDER_PACKAGE, generationGapBaseBuilderPackage);
        setBuilderInterface(generationGapBaseBuilderPackage);
    }

    void setAsGenerationGapBuilder() {
        put(GENERATION_GAP_BASE_BUILDER, false);
        put(GENERATION_GAP_BUILDER, true);
        setBuilderInterface(getBuilderPackage());
    }

	boolean isSuperClassSpecified() {
		return getSuperClass() != null;
	}

	void setSuperClass(String superClass) {
		
		if (superClass != null) {
			put(SUPER_CLASS_MAP_KEY, superClass);
			put(SUPER_CLASS_SPECIFIED_MAP_KEY, true);
		}
	}

	public String getSuperClass() {
		return (String) get(SUPER_CLASS_MAP_KEY);
	}

    String getBuilderInterface() {
        return (String) get(BUILDER_INTERFACE_MAP_KEY);
    }

}
