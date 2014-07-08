package uk.co.buildergenerator;

import java.util.ArrayList;
import java.util.List;

// TODO: TDD me
class BuilderTemplateMapCollector {

    private final Class<?> targetClass;
    private final String builderPackage;

    BuilderTemplateMapCollector(Class<?> targetClass, String builderPackage) {
        this.targetClass = targetClass;
        this.builderPackage = builderPackage;
    }
    
    List<BuilderTemplateMap> collectBuilderTemplateMaps() {

        List<BuilderTemplateMap> builderTemplateMapList = new ArrayList<BuilderTemplateMap>();
        collectBuilderTemplateMaps(builderTemplateMapList, targetClass,
                builderPackage);
        return builderTemplateMapList;
    }

    private void collectBuilderTemplateMaps(List<BuilderTemplateMap> builderTemplateMapList, Class<?> targetClass, String builderPackage) {

        try {

        	if (ignoredClass(targetClass) || alreadyCollected(builderTemplateMapList, targetClass)) {
            	return;
            }

            BuilderTemplateMap builderTemplateMap = new BuilderTemplateMap(targetClass, builderPackage);
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
		// TODO: expand me.
		return targetClass.getName().startsWith("org.joda.time");
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
