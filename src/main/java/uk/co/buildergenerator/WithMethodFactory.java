package uk.co.buildergenerator;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
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

    private boolean isCollectionAddMethod(PropertyDescriptor propertyDescriptor, Class<?> targetClass) {
        
        try {
            targetClass.getMethod(getAddMethodName(propertyDescriptor), getTargetTypeClass(propertyDescriptor));
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private String getAddMethodName(PropertyDescriptor propertyDescriptor) {
        return "add" + getPropertyName(propertyDescriptor);
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
            return getTargetType(propertyDescriptor);
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
        
        return !propertyType.isPrimitive() && !getTargetType(propertyDescriptor).startsWith("java") && !isArray(propertyDescriptor) && !isEnum(propertyDescriptor);

    }

    private boolean isEnum(PropertyDescriptor propertyDescriptor) {
        return propertyDescriptor.getPropertyType().isEnum();
    }

    private boolean isCollection(PropertyDescriptor propertyDescriptor) {
        
        return Collection.class.isAssignableFrom(propertyDescriptor.getPropertyType());
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
        
        String parameterType = getTargetType(propertyDescriptor);
        
        if (isBuilder(propertyDescriptor)) {
            parameterType = builderPackage + parameterType.substring(parameterType.lastIndexOf(".")) + "Builder";
        }

        return parameterType;
        
    }

    private boolean isArray(PropertyDescriptor propertyDescriptor) {
        return propertyDescriptor.getPropertyType().isArray();
    }

    private String getTargetType(PropertyDescriptor propertyDescriptor) {
        
        return getTargetTypeClass(propertyDescriptor).getCanonicalName();
    }
    
    private Class<?> getTargetTypeClass(PropertyDescriptor propertyDescriptor) {
        
        if (isCollection(propertyDescriptor)) {
            
            Type genericType = propertyDescriptor.getReadMethod().getGenericReturnType();
            Type[] actualTypeArguments = ((ParameterizedType)genericType).getActualTypeArguments();
            return ((Class<?>) actualTypeArguments[0]);
        } else {
            return propertyDescriptor.getPropertyType();
        }
    }
    
    private String getPropertyName(PropertyDescriptor propertyDescriptor) {
        String fieldName = propertyDescriptor.getName();
        fieldName = capitaliseFirstLetter(fieldName);
        if (isCollection(propertyDescriptor)) {
            fieldName = makeSingular(fieldName);
        }
        return fieldName;
    }

    private String capitaliseFirstLetter(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private String makeSingular(String fieldName) {
        
        if (fieldName.endsWith("sses")) {
            fieldName = fieldName.substring(0, fieldName.length()-2);
        } else if (fieldName.endsWith("s")) {
            fieldName = fieldName.substring(0, fieldName.length()-1);
        }
        return fieldName;
    }

}
