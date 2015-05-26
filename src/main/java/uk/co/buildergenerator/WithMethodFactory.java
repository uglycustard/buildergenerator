package uk.co.buildergenerator;

import java.beans.PropertyDescriptor;

//TODO: Get rid of this class
class WithMethodFactory {
    
    static WithMethodFactory getWithMethodFactory() {
        return new WithMethodFactory();
    }
    
    private WithMethodFactory() {}
    
    private BuilderGeneratorUtils bgu = new BuilderGeneratorUtils();
    
    WithMethod createWithMethod(PropertyDescriptor propertyDescriptor, Class<?> targetClass, String builderPackage, ClassesToIgnore classesToIgnore) {
        
        
        try {
            
            String propertyName = bgu.getPropertyName(propertyDescriptor);
            String parameterType = bgu.getParameterType(propertyDescriptor, builderPackage, classesToIgnore);
            String parameterName = bgu.getParameterName(propertyDescriptor);
            boolean collection = bgu.isCollection(propertyDescriptor);
            boolean collectionNeedsInitialising = bgu.isCollectionNeedsInitialising(targetClass, propertyDescriptor);
            String collectionGetterMethodName = bgu.getCollectionGetterMethodName(propertyDescriptor);
            String collectionSetterMethodName = bgu.getCollectionSetterMethodName(propertyDescriptor);
            String collectionTypeWhenCollectionNeedsInitialising = bgu.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor);
            String collectionMethodSettingInvocation = bgu.getCollectionMethodSettingInvocation(propertyDescriptor, targetClass);
            boolean builder = bgu.isBuilder(propertyDescriptor, classesToIgnore);
            String builderTargetType = bgu.getBuilderTargetType(propertyDescriptor, classesToIgnore);
            return new WithMethod(propertyName, parameterType, parameterName, collection, collectionNeedsInitialising, collectionGetterMethodName, collectionSetterMethodName, collectionTypeWhenCollectionNeedsInitialising, collectionMethodSettingInvocation, builder, builderTargetType);
        } catch (RuntimeException e) {
            
            throw new RuntimeException(String.format("error generating builder method for property [%s] in class [%s], please log an issue at www.buildergenerator.co.uk", propertyDescriptor.getDisplayName(), targetClass.getName()), e);
        }
    }
    
}
