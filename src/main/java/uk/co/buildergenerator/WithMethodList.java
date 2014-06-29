package uk.co.buildergenerator;

import static uk.co.buildergenerator.BuilderGeneratorUtils.isCollection;
import static uk.co.buildergenerator.BuilderGeneratorUtils.isCollectionAddMethod;
import static uk.co.buildergenerator.WithMethodFactory.getWithMethodFactory;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;

import org.apache.commons.beanutils.PropertyUtils;

class WithMethodList extends ArrayList<WithMethod> {

    private static final long serialVersionUID = 1L;
    
    WithMethodList(Class<?> targetClass, String builderPackage) {
        
        for (PropertyDescriptor propertyDescriptor : PropertyUtils.getPropertyDescriptors(targetClass)) {
            
            if (!"class".equals(propertyDescriptor.getName()) && propertyDescriptor.getWriteMethod() != null 
                    || (isCollection(propertyDescriptor) && isCollectionAddMethod(propertyDescriptor, targetClass))) {
                add(getWithMethodFactory().createWithMethod(propertyDescriptor, targetClass, builderPackage));
            }
        }
    }

}
