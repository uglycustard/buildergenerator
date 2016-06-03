package uk.co.buildergenerator;

import static uk.co.buildergenerator.WithMethodFactory.getWithMethodFactory;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;

class WithMethodList extends ArrayList<WithMethod> {

    private static final long serialVersionUID = 1L;
    
    private static BuilderGeneratorUtils bgu = new BuilderGeneratorUtils();

	private static final Comparator<PropertyDescriptor> ORDER_BY_NAME_COMPARATOR = new Comparator<PropertyDescriptor>() {
		@Override
		public int compare(PropertyDescriptor o1, PropertyDescriptor o2) {
			return o1.getName().compareTo(o2.getName());
		}
	};
    
    WithMethodList(Class<?> targetClass, String builderPackage, PropertiesToIgnore propertiesToIgnore, ClassesToIgnore classesToIgnore) {
        
        PropertyDescriptor[] properyDescriptors = PropertyUtils.getPropertyDescriptors(targetClass);
        Arrays.sort(properyDescriptors, ORDER_BY_NAME_COMPARATOR);
		for (PropertyDescriptor propertyDescriptor : properyDescriptors) {
            
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
