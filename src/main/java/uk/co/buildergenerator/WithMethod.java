package uk.co.buildergenerator;

public class WithMethod {
    
    private String propertyName;
    private String parameterType;
    private String parameterName;
    private boolean isCollection;
    private boolean isCollectionNeedsInitialising;
    private String collectionGetterMethodName;
    private String collectionSetterMethodName;
    private String collectionType;
    private String collectionMethodSettingInvocation;
    private boolean isBuilder;
    private String builderTargetType;

    WithMethod(String propertyName, String parameterType, String parameterName, boolean isCollection, boolean isCollectionNeedsInitialising, String collectionGetterMethodName, String collectionSetterMethodName, String collectionType, String collectionMethodSettingInvocation, boolean isBuilder, String builderTargetType) {
        this.propertyName = propertyName;
        this.parameterType = parameterType;
        this.parameterName = parameterName;
        this.isCollection = isCollection;
        this.isCollectionNeedsInitialising = isCollectionNeedsInitialising;
        this.collectionGetterMethodName = collectionGetterMethodName;
        this.collectionSetterMethodName = collectionSetterMethodName;
        this.collectionType = collectionType;
        this.collectionMethodSettingInvocation = collectionMethodSettingInvocation;
        this.isBuilder = isBuilder;
        this.builderTargetType = builderTargetType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getParameterType() {
        return parameterType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public boolean isCollection() {
        return isCollection;
    }
    
    public boolean isCollectionNeedsInitialising() {
        return isCollectionNeedsInitialising;
    }
    
    public String getCollectionGetterMethodName() {
        return collectionGetterMethodName;
    }
    
    public String getCollectionSetterMethodName() {
        return collectionSetterMethodName;
    }
    
    public String getCollectionType() {
        return collectionType;
    }

    public String getCollectionMethodSettingInvocation() {
        return collectionMethodSettingInvocation;
    }

    public boolean isBuilder() {
        return isBuilder;
    }

    public String getBuilderTargetType() {
        return builderTargetType;
    }
}