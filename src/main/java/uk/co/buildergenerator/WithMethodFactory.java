package uk.co.buildergenerator;

import java.beans.PropertyDescriptor;

//TODO: Get rid of this class
class WithMethodFactory {
    
    static WithMethodFactory getWithMethodFactory() {
        return new WithMethodFactory();
    }
    
    private WithMethodFactory() {}
    
    private BuilderGeneratorUtils bgu = new BuilderGeneratorUtils();
    
    WithMethod createWithMethod(PropertyDescriptor propertyDescriptor, Class<?> targetClass, String builderPackage) {
        
        String propertyName = bgu.getPropertyName(propertyDescriptor);
        String parameterType = bgu.getParameterType(propertyDescriptor, builderPackage);
        String parameterName = bgu.getParameterName(propertyDescriptor);
        boolean collection = bgu.isCollection(propertyDescriptor);
        boolean collectionNeedsInitialising = bgu.isCollectionNeedsInitialising(targetClass, propertyDescriptor);
        String collectionGetterMethodName = bgu.getCollectionGetterMethodName(propertyDescriptor);
        String collectionSetterMethodName = bgu.getCollectionSetterMethodName(propertyDescriptor);
        String collectionTypeWhenCollectionNeedsInitialising = bgu.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor);
        String collectionMethodSettingInvocation = bgu.getCollectionMethodSettingInvocation(propertyDescriptor, targetClass);
        boolean builder = bgu.isBuilder(propertyDescriptor);
        String builderTargetType = bgu.getBuilderTargetType(propertyDescriptor);
        
        return new WithMethod(propertyName, parameterType, parameterName, collection, collectionNeedsInitialising, collectionGetterMethodName, collectionSetterMethodName, collectionTypeWhenCollectionNeedsInitialising, collectionMethodSettingInvocation, builder, builderTargetType);
    }
    
}
