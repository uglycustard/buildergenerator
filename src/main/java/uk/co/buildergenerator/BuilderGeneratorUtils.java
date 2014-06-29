package uk.co.buildergenerator;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

class BuilderGeneratorUtils {

    static boolean isCollection(PropertyDescriptor propertyDescriptor) {
        
        return Collection.class.isAssignableFrom(propertyDescriptor.getPropertyType());
    }

    static boolean isCollectionAddMethod(PropertyDescriptor propertyDescriptor, Class<?> targetClass) {
        
        try {
            targetClass.getMethod(getAddMethodName(propertyDescriptor), getTargetTypeClass(propertyDescriptor));
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
    
    static String getAddMethodName(PropertyDescriptor propertyDescriptor) {
        return "add" + getPropertyName(propertyDescriptor);
    }

    static String getPropertyName(PropertyDescriptor propertyDescriptor) {
        String fieldName = propertyDescriptor.getName();
        fieldName = capitaliseFirstLetter(fieldName);
        if (isCollection(propertyDescriptor)) {
            fieldName = makeSingular(fieldName);
        }
        return fieldName;
    }

    static Class<?> getTargetTypeClass(PropertyDescriptor propertyDescriptor) {
        
        if (isCollection(propertyDescriptor)) {
            
            Type genericType = propertyDescriptor.getReadMethod().getGenericReturnType();
            Type[] actualTypeArguments = ((ParameterizedType)genericType).getActualTypeArguments();
            return ((Class<?>) actualTypeArguments[0]);
        } else {
            return propertyDescriptor.getPropertyType();
        }
    }
    
    static String capitaliseFirstLetter(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    static String makeSingular(String fieldName) {
        
        if (fieldName.endsWith("sses")) {
            fieldName = fieldName.substring(0, fieldName.length()-2);
        } else if (fieldName.endsWith("s")) {
            fieldName = fieldName.substring(0, fieldName.length()-1);
        }
        return fieldName;
    }


}
