package uk.co.buildergenerator;

import static uk.co.buildergenerator.BuilderGeneratorUtils.capitaliseFirstLetter;
import static uk.co.buildergenerator.BuilderGeneratorUtils.getAddMethodName;
import static uk.co.buildergenerator.BuilderGeneratorUtils.getPropertyName;
import static uk.co.buildergenerator.BuilderGeneratorUtils.getTargetTypeClass;
import static uk.co.buildergenerator.BuilderGeneratorUtils.isCollection;
import static uk.co.buildergenerator.BuilderGeneratorUtils.isCollectionAddMethod;
import static uk.co.buildergenerator.BuilderGeneratorUtils.makeSingular;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class WithMethodFactory {
    
    static WithMethodFactory getWithMethodFactory() {
        return new WithMethodFactory();
    }
    
    private WithMethodFactory() {}
    
    WithMethod createWithMethod(PropertyDescriptor propertyDescriptor, Class<?> targetClass, String builderPackage) {
        
        String propertyName = getPropertyName(propertyDescriptor);
        String parameterType = getParameterType(propertyDescriptor, builderPackage);
        String parameterName = getParameterName(propertyDescriptor);
        boolean collection = isCollection(propertyDescriptor);
        boolean collectionNeedsInitialising = isCollectionNeedsInitialising(targetClass, propertyDescriptor);
        String collectionGetterMethodName = getCollectionGetterMethodName(propertyDescriptor);
        String collectionSetterMethodName = getCollectionSetterMethodName(propertyDescriptor);
        String collectionTypeWhenCollectionNeedsInitialising = getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor);
        String collectionMethodSettingInvocation = getCollectionMethodSettingInvocation(propertyDescriptor, targetClass);
        boolean builder = isBuilder(propertyDescriptor);
        String builderTargetType = getBuilderTargetType(propertyDescriptor);
        
        return new WithMethod(propertyName, parameterType, parameterName, collection, collectionNeedsInitialising, collectionGetterMethodName, collectionSetterMethodName, collectionTypeWhenCollectionNeedsInitialising, collectionMethodSettingInvocation, builder, builderTargetType);
    }
    
    private String getCollectionTypeWhenCollectionNeedsInitialising(PropertyDescriptor propertyDescriptor) {
        
        if (isList(propertyDescriptor.getPropertyType())) {
            return "java.util.ArrayList";
        } else if (isSet(propertyDescriptor.getPropertyType())) {
            return "java.util.HashSet";
        } else if (isQueue(propertyDescriptor.getPropertyType())) {
            return "java.util.PriorityQueue";
        }
        
        return null;
    }

    private boolean isCollectionNeedsInitialising(Class<?> targetClass, PropertyDescriptor propertyDescriptor) {
        
        if (isCollection(propertyDescriptor)) {
            try {
                Method collectionGetterMethod = propertyDescriptor.getReadMethod();
                if (collectionGetterMethod.invoke(targetClass.newInstance()) == null) {
                    return true;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        return false;
    }

    private String getCollectionMethodSettingInvocation(PropertyDescriptor propertyDescriptor, Class<?> targetClass) {
        
        if (isCollection(propertyDescriptor)) {
            
            if (isCollectionAddMethod(propertyDescriptor, targetClass)) {
                return getAddMethodName(propertyDescriptor);
            } else {
                return getCollectionGetterMethodName(propertyDescriptor) + "().add";
            }
        }
        
        return null;
        
    }

    private String getCollectionGetterMethodName(PropertyDescriptor propertyDescriptor) {
        
        if (isCollection(propertyDescriptor)) {
            return propertyDescriptor.getReadMethod().getName();
        }
        
        return null;
    }

    private String getCollectionSetterMethodName(PropertyDescriptor propertyDescriptor) {
        if (isCollection(propertyDescriptor)) {
            return "set" + capitaliseFirstLetter(propertyDescriptor.getName());
        }
        
        return null;
    }

    private String getBuilderTargetType(PropertyDescriptor propertyDescriptor) {
        
        if (isBuilder(propertyDescriptor)) {
            return getTargetTypeClass(propertyDescriptor).getCanonicalName();
        }
        
        return null;
    }

    private String getParameterName(PropertyDescriptor propertyDescriptor) {
        
        if (isCollection(propertyDescriptor)) {
            return makeSingular(propertyDescriptor.getName());
        } else {
            return propertyDescriptor.getName();
        }
    }

    private boolean isBuilder(PropertyDescriptor propertyDescriptor) {

        Class<?> propertyType = propertyDescriptor.getPropertyType();
        
        return !propertyType.isPrimitive() && !getTargetTypeClass(propertyDescriptor).getCanonicalName().startsWith("java") && !isArray(propertyDescriptor) && !isEnum(propertyDescriptor);

    }

    private boolean isEnum(PropertyDescriptor propertyDescriptor) {
        return propertyDescriptor.getPropertyType().isEnum();
    }

    private boolean isList(Class<?> type) {
        return List.class.isAssignableFrom(type);
    }

    private boolean isSet(Class<?> type) {
        return Set.class.isAssignableFrom(type);
    }

    private boolean isQueue(Class<?> type) {
        return Queue.class.isAssignableFrom(type);
    }

    private String getParameterType(PropertyDescriptor propertyDescriptor, String builderPackage) {
        
        String parameterType = getTargetTypeClass(propertyDescriptor).getCanonicalName();
        
        if (isBuilder(propertyDescriptor)) {
            parameterType = builderPackage + parameterType.substring(parameterType.lastIndexOf(".")) + "Builder";
        }

        return parameterType;
        
    }

    private boolean isArray(PropertyDescriptor propertyDescriptor) {
        return propertyDescriptor.getPropertyType().isArray();
    }

}
