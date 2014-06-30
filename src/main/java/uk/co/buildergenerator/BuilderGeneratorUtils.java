package uk.co.buildergenerator;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class BuilderGeneratorUtils {

    boolean isCollection(PropertyDescriptor propertyDescriptor) {
        
        return Collection.class.isAssignableFrom(propertyDescriptor.getPropertyType());
    }
    
    boolean isCollectionNeedsInitialising(Class<?> targetClass, PropertyDescriptor propertyDescriptor) {
        
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
    
    String getCollectionGetterMethodName(PropertyDescriptor propertyDescriptor) {
        
        if (isCollection(propertyDescriptor)) {
            return propertyDescriptor.getReadMethod().getName();
        }
        
        return null;
    }
    
    String getCollectionSetterMethodName(PropertyDescriptor propertyDescriptor) {
        if (isCollection(propertyDescriptor)) {
            return "set" + capitaliseFirstLetter(propertyDescriptor.getName());
        }
        
        return null;
    }

    String getCollectionTypeWhenCollectionNeedsInitialising(PropertyDescriptor propertyDescriptor) {
        
        if (isList(propertyDescriptor.getPropertyType())) {
            return "java.util.ArrayList";
        } else if (isSet(propertyDescriptor.getPropertyType())) {
            return "java.util.HashSet";
        } else if (isQueue(propertyDescriptor.getPropertyType())) {
            return "java.util.PriorityQueue";
        }
        
        return null;
    }
    
    boolean isList(Class<?> type) {
        return List.class.isAssignableFrom(type);
    }

    boolean isSet(Class<?> type) {
        return Set.class.isAssignableFrom(type);
    }

    boolean isQueue(Class<?> type) {
        return Queue.class.isAssignableFrom(type);
    }
    
    String getCollectionMethodSettingInvocation(PropertyDescriptor propertyDescriptor, Class<?> targetClass) {
        
        if (isCollection(propertyDescriptor)) {
            
            if (isCollectionAddMethod(propertyDescriptor, targetClass)) {
                return getAddMethodName(propertyDescriptor);
            } else {
                return getCollectionGetterMethodName(propertyDescriptor) + "().add";
            }
        }
        
        return null;
        
    }

    boolean isCollectionAddMethod(PropertyDescriptor propertyDescriptor, Class<?> targetClass) {
        
        try {
            targetClass.getMethod(getAddMethodName(propertyDescriptor), getTargetTypeClass(propertyDescriptor));
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
    
    String getAddMethodName(PropertyDescriptor propertyDescriptor) {
        return "add" + getPropertyName(propertyDescriptor);
    }

    String getPropertyName(PropertyDescriptor propertyDescriptor) {
        String fieldName = propertyDescriptor.getName();
        fieldName = capitaliseFirstLetter(fieldName);
        if (isCollection(propertyDescriptor)) {
            fieldName = makeSingular(fieldName);
        }
        return fieldName;
    }

    Class<?> getTargetTypeClass(PropertyDescriptor propertyDescriptor) {
        
        if (isCollection(propertyDescriptor)) {
            
            Type genericType = propertyDescriptor.getReadMethod().getGenericReturnType();
            Type[] actualTypeArguments = ((ParameterizedType)genericType).getActualTypeArguments();
            return ((Class<?>) actualTypeArguments[0]);
        } else {
            return propertyDescriptor.getPropertyType();
        }
    }
    
    String capitaliseFirstLetter(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    String makeSingular(String fieldName) {
        
        if (fieldName.endsWith("sses")) {
            fieldName = fieldName.substring(0, fieldName.length()-2);
        } else if (fieldName.endsWith("s")) {
            fieldName = fieldName.substring(0, fieldName.length()-1);
        }
        return fieldName;
    }

    String getParameterType(PropertyDescriptor propertyDescriptor, String builderPackage) {
        
        String parameterType = getTargetTypeClass(propertyDescriptor).getCanonicalName();
        
        if (isBuilder(propertyDescriptor)) {
            parameterType = builderPackage + parameterType.substring(parameterType.lastIndexOf(".")) + "Builder";
        }

        return parameterType;
        
    }
    
    String getParameterName(PropertyDescriptor propertyDescriptor) {
        
        if (isCollection(propertyDescriptor)) {
            return makeSingular(propertyDescriptor.getName());
        } else {
            return propertyDescriptor.getName();
        }
    }

    boolean isBuilder(PropertyDescriptor propertyDescriptor) {

        Class<?> propertyType = propertyDescriptor.getPropertyType();
        
        return !propertyType.isPrimitive() && !getTargetTypeClass(propertyDescriptor).getCanonicalName().startsWith("java") && !isArray(propertyDescriptor) && !isEnum(propertyDescriptor);
    }
    
    String getBuilderTargetType(PropertyDescriptor propertyDescriptor) {
        
        if (isBuilder(propertyDescriptor)) {
            return getTargetTypeClass(propertyDescriptor).getCanonicalName();
        }
        
        return null;
    }

    boolean isEnum(PropertyDescriptor propertyDescriptor) {
        return propertyDescriptor.getPropertyType().isEnum();
    }


    boolean isArray(PropertyDescriptor propertyDescriptor) {
        return propertyDescriptor.getPropertyType().isArray();
    }

}
