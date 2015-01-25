package ${builderPackage};

public class ${targetClass}<#if !generatioGapBaseBuilder>Builder</#if><#if generatioGapBaseBuilder>BaseBuilder<T extends ${targetClass}BaseBuilder<T>></#if> <#if generatioGapBuilder>extends ${targetClass}BaseBuilder<${targetClass}Builder> </#if>{
    <#if !generatioGapBaseBuilder>
    public static ${targetClass}Builder ${factoryMethodPrefix}${targetClass}() {
        return new ${targetClass}Builder();
    }</#if><#if !generatioGapBuilder>    
    private ${fullyQualifiedTargetClass} target = new ${fullyQualifiedTargetClass}();
    
    public ${targetClass}<#if generatioGapBaseBuilder>Base</#if>Builder() {}
    
    <#list withMethodList as withMethod>
    public <#if generatioGapBaseBuilder>T</#if><#if !generatioGapBaseBuilder>${targetClass}Builder</#if> with${withMethod.propertyName}(${withMethod.parameterType} ${withMethod.parameterName}) {
    <#if withMethod.collection>
    <#if withMethod.collectionNeedsInitialising>
        if (target.${withMethod.collectionGetterMethodName}() == null) {
        <#if !withMethod.builder>
            target.${withMethod.collectionSetterMethodName}(new ${withMethod.collectionType}<${withMethod.parameterType}>());
        </#if>
        <#if withMethod.builder>
            target.${withMethod.collectionSetterMethodName}(new ${withMethod.collectionType}<${withMethod.builderTargetType}>());
        </#if>
        }        
    </#if>
        target.${withMethod.collectionMethodSettingInvocation}(${withMethod.parameterName}<#if withMethod.builder>.build()</#if>);
    </#if>
    <#if !withMethod.collection>
        target.set${withMethod.propertyName}(${withMethod.parameterName}<#if withMethod.builder>.build()</#if>);
    </#if>
        return <#if generatioGapBaseBuilder>(T) </#if>this;
    }
    
    </#list><#if generatioGapBaseBuilder>
    protected ${fullyQualifiedTargetClass} getTarget() {
        return target;
    }
    
    </#if>
    public ${fullyQualifiedTargetClass} build() {
        return target;
    }</#if>
}
