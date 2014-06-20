package ${builderPackage};

public class ${targetClass}Builder {

    public static ${targetClass}Builder ${factoryMethodPrefix}${targetClass}() {
        return new ${targetClass}Builder();
    }
    
    private ${fullyQualifiedTargetClass} target = new ${fullyQualifiedTargetClass}();
    
    private ${targetClass}Builder() {}
    
    <#list withMethodList as withMethod>
    public ${targetClass}Builder with${withMethod.propertyName}(${withMethod.parameterType} ${withMethod.parameterName}) {
    <#if withMethod.collection>
    <#if withMethod.collectionNeedsInitialising>
        if (target.${withMethod.collectionGetterMethodName}() == null) {
            target.${withMethod.collectionSetterMethodName}(new ${withMethod.collectionType}<${withMethod.parameterType}>());
        }        
    </#if>
        target.${withMethod.collectionMethodSettingInvocation}(${withMethod.parameterName}<#if withMethod.builder>.build()</#if>);
    </#if>
    <#if !withMethod.collection>
        target.set${withMethod.propertyName}(${withMethod.parameterName}<#if withMethod.builder>.build()</#if>);
    </#if>
        return this;
    }
    
    </#list>
    public ${fullyQualifiedTargetClass} build() {
        return target;
    }
}
