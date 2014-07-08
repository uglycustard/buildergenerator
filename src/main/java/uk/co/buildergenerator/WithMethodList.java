package uk.co.buildergenerator;

import static uk.co.buildergenerator.WithMethodFactory.getWithMethodFactory;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

class WithMethodList extends ArrayList<WithMethod> {

    private static final long serialVersionUID = 1L;
    
    private static BuilderGeneratorUtils bgu = new BuilderGeneratorUtils();
    
    WithMethodList(Class<?> targetClass, String builderPackage, List<String> propertiesToIgnore) {
        
        if (propertiesToIgnore == null) {
            propertiesToIgnore = new ArrayList<String>();
        }
        
        propertiesToIgnore.add("class");
        
        for (PropertyDescriptor propertyDescriptor : PropertyUtils.getPropertyDescriptors(targetClass)) {
            
            if (propertyNotIgnored(propertiesToIgnore, propertyDescriptor) && propertyIsWritable(targetClass, propertyDescriptor)) {
                add(getWithMethodFactory().createWithMethod(propertyDescriptor, targetClass, builderPackage));
            }
        }
    }

    private boolean propertyIsWritable(Class<?> targetClass, PropertyDescriptor propertyDescriptor) {
        
        return propertyDescriptor.getWriteMethod() != null || (bgu.isCollection(propertyDescriptor) && bgu.isCollectionAddMethod(propertyDescriptor, targetClass));
    }

    private boolean propertyNotIgnored(List<String> propertiesToIgnore, PropertyDescriptor propertyDescriptor) {
        
        return !propertiesToIgnore.contains(propertyDescriptor.getName());
    }

}
