package uk.co.buildergenerator;

import static uk.co.buildergenerator.WithMethodFactory.getWithMethodFactory;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;

import org.apache.commons.beanutils.PropertyUtils;

class WithMethodList extends ArrayList<WithMethod> {

    private static final long serialVersionUID = 1L;
    
    private static BuilderGeneratorUtils bgu = new BuilderGeneratorUtils();
    
    WithMethodList(Class<?> targetClass, String builderPackage) {
        
        for (PropertyDescriptor propertyDescriptor : PropertyUtils.getPropertyDescriptors(targetClass)) {
            
            if (!"class".equals(propertyDescriptor.getName()) && propertyDescriptor.getWriteMethod() != null 
                    || (bgu.isCollection(propertyDescriptor) && bgu.isCollectionAddMethod(propertyDescriptor, targetClass))) {
                add(getWithMethodFactory().createWithMethod(propertyDescriptor, targetClass, builderPackage));
            }
        }
    }

}
