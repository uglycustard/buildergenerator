package uk.co.buildergenerator;

import static uk.co.buildergenerator.WithMethodFactory.getWithMethodFactory;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;

public class TestUtils {

    public static WithMethod createWithMethod(String propertyName, Class<?> targetClass, String builderPackage, ClassesToIgnore classesToIgnore) {
        
        try {
            PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(targetClass.newInstance(), propertyName);
            return getWithMethodFactory().createWithMethod(propertyDescriptor, targetClass, builderPackage, classesToIgnore, CollectionInitialisationTypes.getDefaultMappings());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
