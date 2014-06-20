package uk.co.buildergenerator;

import static uk.co.buildergenerator.WithMethodFactory.getWithMethodFactory;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;

import org.apache.commons.beanutils.PropertyUtils;

class WithMethodList extends ArrayList<WithMethod> {

    private static final long serialVersionUID = 1L;
    
    WithMethodList(Class<?> targetClass, String builderPackage) {
        
        for (PropertyDescriptor propertyDescriptor : PropertyUtils.getPropertyDescriptors(targetClass)) {
            
            String propertyName = propertyDescriptor.getName();
            if (!"class".equals(propertyName)) {
                add(getWithMethodFactory().createWithMethod(propertyName, targetClass, builderPackage));
            }
        }
    }

}
