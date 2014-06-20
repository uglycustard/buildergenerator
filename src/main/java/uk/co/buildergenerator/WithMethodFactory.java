package uk.co.buildergenerator;

import java.lang.reflect.Field;
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
    
    WithMethod createWithMethod(String propertyName, Class<?> targetClass, String builderPackage) {

        Field field = getFieldFromHierarchy(targetClass, propertyName);
        return new WithMethod(getPropertyName(field), 
                              getParameterType(field, builderPackage), 
                              getParameterName(field), 
                              isCollection(field),
                              isCollectionNeedsInitialising(targetClass, field),
                              getCollectionGetterMethodName(field), 
                              getCollectionSetterMethodName(field),
                              getCollectionTypeWhenCollectionNeedsInitialising(targetClass, field), 
                              getCollectionMethodSettingInvocation(field, targetClass), 
                              isBuilder(field), 
                              getBuilderTargetType(field));

    }

    private String getCollectionTypeWhenCollectionNeedsInitialising(Class<?> targetClass, Field field) {
        
        if (isList(field)) {
            return "java.util.ArrayList";
        } else if (isSet(field)) {
            return "java.util.HashSet";
        } else if (isQueue(field)) {
            return "java.util.PriorityQueue";
        }
        
        return null;
    }

    private boolean isCollectionNeedsInitialising(Class<?> targetClass, Field field) {
        
        if (isCollection(field)) {
            try {
                Method collectionGetterMethod = targetClass.getMethod(getCollectionGetterMethodName(field));
                if (collectionGetterMethod.invoke(targetClass.newInstance()) == null) {
                    return true;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        return false;
    }

    private boolean isCollectionAddMethod(Field field, Class<?> targetClass) {
        
        try {
            targetClass.getMethod(getAddMethodName(field), getTargetTypeClass(field));
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private String getAddMethodName(Field field) {
        return "add" + getPropertyName(field);
    }

    private String getCollectionMethodSettingInvocation(Field field, Class<?> targetClass) {
        
        if (isCollection(field)) {
            
            if (isCollectionAddMethod(field, targetClass)) {
                return getAddMethodName(field);
            } else {
                return getCollectionGetterMethodName(field) + "().add";
            }
        }
        
        return null;
        
    }

    private String getCollectionGetterMethodName(Field field) {
        
        if (isCollection(field)) {
            return "get" + capitaliseFirstLetter(field.getName());
        }
        
        return null;
    }

    private String getCollectionSetterMethodName(Field field) {
        if (isCollection(field)) {
            return "set" + capitaliseFirstLetter(field.getName());
        }
        
        return null;
    }

    private String getBuilderTargetType(Field field) {
        
        if (isBuilder(field)) {
            return getTargetType(field);
        }
        
        return null;
    }

    private String getParameterName(Field field) {
        
        if (isCollection(field)) {
            return makeSingular(field.getName());
        } else {
            return field.getName();
        }
    }

    private boolean isBuilder(Field field) {

        return !field.getType().isPrimitive() && !getTargetType(field).startsWith("java") && !isArray(field);
    }

    private boolean isCollection(Field field) {
        
        return Collection.class.isAssignableFrom(field.getType());
    }

    private boolean isList(Field field) {
        
        return List.class.isAssignableFrom(field.getType());
    }

    private boolean isSet(Field field) {
        
        return Set.class.isAssignableFrom(field.getType());
    }

    private boolean isQueue(Field field) {
        
        return Queue.class.isAssignableFrom(field.getType());
    }

    private Field getFieldFromHierarchy(Class<?> c, String propertyName) {
        try {
            return c.getDeclaredField(propertyName);
        } catch (NoSuchFieldException e) {
            if (c.getSuperclass() == null) {
                throw new RuntimeException("no bean property was found in target class hierachy with name: " + propertyName);
            }
            return getFieldFromHierarchy(c.getSuperclass(), propertyName);
        }
    }
    
    private String getParameterType(Field field, String builderPackage) {
        
        String parameterType = getTargetType(field);
        
        if (isBuilder(field)) {
            parameterType = builderPackage + parameterType.substring(parameterType.lastIndexOf(".")) + "Builder";
        }

        return parameterType;
        
    }

    private boolean isArray(Field field) {
        return field.getType().isArray();
    }

    private String getTargetType(Field field) {
        
        if (isArray(field)) {
            return getArrayName(field.getType());
        }
        
        return getTargetTypeClass(field).getName();
    }
    
    private Class<?> getTargetTypeClass(Field field) {
        
        if (isCollection(field)) {
            Type genericType = field.getGenericType();
            Type[] actualTypeArguments = ((ParameterizedType)genericType).getActualTypeArguments();
            return ((Class<?>) actualTypeArguments[0]);
        } else {
            return field.getType();
        }
    }
    
    private String getArrayName(Class<?> type) {
        Class<?> cl = type;
        int dimensions = 0;
        while (cl.isArray()) {
            dimensions++;
            cl = cl.getComponentType();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(cl.getName());
        for (int i = 0; i < dimensions; i++) {
            sb.append("[]");
        }
        return sb.toString();

    }

    private String getPropertyName(Field field) {
        
        String fieldName = field.getName();
        fieldName = capitaliseFirstLetter(fieldName);
        if (isCollection(field)) {
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
