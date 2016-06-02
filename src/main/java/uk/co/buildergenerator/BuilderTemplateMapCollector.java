package uk.co.buildergenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO: TDD me
class BuilderTemplateMapCollector {

    private final Class<?> targetClass;
    private final String builderPackage;
    private final PropertiesToIgnore propertiesToIgnore;
    private final ClassesToIgnore classesToIgnore;
    private final Map<Class<?>, String> collectionInitialisationTypes;

    BuilderTemplateMapCollector(Class<?> targetClass, String builderPackage, PropertiesToIgnore propertiesToIgnore, ClassesToIgnore classesToIgnore, Map<Class<?>, String> collectionInitialisationTypes) {
		this.targetClass = targetClass;
        this.builderPackage = builderPackage;
        this.propertiesToIgnore = propertiesToIgnore;
        this.classesToIgnore = classesToIgnore;
        this.collectionInitialisationTypes = collectionInitialisationTypes;
    }
    
    List<BuilderTemplateMap> collectBuilderTemplateMaps() {

        List<BuilderTemplateMap> builderTemplateMapList = new ArrayList<BuilderTemplateMap>();
        collectBuilderTemplateMaps(builderTemplateMapList, targetClass, builderPackage);
        return builderTemplateMapList;
    }

    private void collectBuilderTemplateMaps(List<BuilderTemplateMap> builderTemplateMapList, Class<?> targetClass, String builderPackage) {

        try {

        	if (ignoredClass(targetClass) || alreadyCollected(builderTemplateMapList, targetClass)) {
            	return;
            }

            BuilderTemplateMap builderTemplateMap = new BuilderTemplateMap(targetClass, builderPackage, propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
            builderTemplateMapList.add(builderTemplateMap);
            for (WithMethod withMethod : builderTemplateMap.getWithMethodList()) {
                if (withMethod.isBuilder()) {
                    String builderTargetType = withMethod.getBuilderTargetType();
                    collectBuilderTemplateMaps(builderTemplateMapList, Class.forName(builderTargetType), builderPackage);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean ignoredClass(Class<?> targetClass) {
        
        return classesToIgnore.isIgnored(targetClass);
	}

	private boolean alreadyCollected(List<BuilderTemplateMap> builderTemplateMapList, Class<?> targetClass) {
		
		for (BuilderTemplateMap builderTemplateMap : builderTemplateMapList) {
		    if (builderTemplateMap.getFullyQualifiedTargetClassName().equals(targetClass.getName())) {
		    	return true;
		    }
		}
		return false;
	}

}
