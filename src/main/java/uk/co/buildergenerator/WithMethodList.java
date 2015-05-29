package uk.co.buildergenerator;

import static uk.co.buildergenerator.WithMethodFactory.getWithMethodFactory;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;

import org.apache.commons.beanutils.PropertyUtils;

class WithMethodList extends ArrayList<WithMethod> {

    private static final long serialVersionUID = 1L;
    
    private static BuilderGeneratorUtils bgu = new BuilderGeneratorUtils();
    
    WithMethodList(Class<?> targetClass, String builderPackage, PropertiesToIgnore propertiesToIgnore, ClassesToIgnore classesToIgnore) {
        
        for (PropertyDescriptor propertyDescriptor : PropertyUtils.getPropertyDescriptors(targetClass)) {
            
            try {
                if (!propertiesToIgnore.isPropertyIgnored(targetClass, propertyDescriptor.getName()) && propertyIsWritable(targetClass, propertyDescriptor)) {
                    add(getWithMethodFactory().createWithMethod(propertyDescriptor, targetClass, builderPackage, classesToIgnore));
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(String.format("error generating builder method for property [%s] in class [%s], please log an issue at www.buildergenerator.co.uk", propertyDescriptor.getDisplayName(), targetClass.getName()), e);
            }
        }
    }

    private boolean propertyIsWritable(Class<?> targetClass, PropertyDescriptor propertyDescriptor) {
        
        return propertyDescriptor.getWriteMethod() != null || (bgu.isCollection(propertyDescriptor) && bgu.isCollectionAddMethod(propertyDescriptor, targetClass));
    }

}
