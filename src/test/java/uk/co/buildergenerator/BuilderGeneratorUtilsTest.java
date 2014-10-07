package uk.co.buildergenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import uk.co.buildergenerator.testmodel.BeanWithAnInterfaceCollectionProperty;
import uk.co.buildergenerator.testmodel.BeanWithAnInterfaceProperty;
import uk.co.buildergenerator.testmodel.BeanWithJodaTime;
import uk.co.buildergenerator.testmodel.BeanWithNonGenericCollections;
import uk.co.buildergenerator.testmodel.BeanWithNullSubListInterfaceProperty;
import uk.co.buildergenerator.testmodel.House;
import uk.co.buildergenerator.testmodel.NullCollectionPropertyWithSetCollectionMethod;
import uk.co.buildergenerator.testmodel.NullLinkedListPropertyWithSetLinkedListMethod;
import uk.co.buildergenerator.testmodel.NullListPropertyWithSetListMethod;
import uk.co.buildergenerator.testmodel.NullQueuePropertyWithSetQueueMethod;
import uk.co.buildergenerator.testmodel.NullSetPropertyWithSetSetMethod;
import uk.co.buildergenerator.testmodel.NullTreeSetPropertyWithSetTreeSetMethod;

public class BuilderGeneratorUtilsTest {

	private BuilderGeneratorUtils testee;
	
	private ClassesToIgnore classesToIgnore = new ClassesToIgnore();
	
	private PropertyDescriptor propertyDescriptor;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setup() throws Exception {
		testee = new BuilderGeneratorUtils();
	}

	private void createPropertyDescriptor(String propertyName, Class<?> beanClass) {
		try {
			propertyDescriptor = new PropertyDescriptor(propertyName, beanClass);
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void jodaTimePropertiesAreNotSuitableForBuilder() {
		
		createPropertyDescriptor("localDate", BeanWithJodaTime.class);
		assertFalse("property should not be a builder", testee.isBuilder(propertyDescriptor, classesToIgnore));
	}
	
	@Test
    public void nullCollectionTypeIsCollection() throws Exception {
        
	    createPropertyDescriptor("strings", NullCollectionPropertyWithSetCollectionMethod.class);
	    assertEquals("java.util.ArrayList", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor));
    }

	@Test
	public void nullCollectionTypeIsList() throws Exception {

	    createPropertyDescriptor("strings", NullListPropertyWithSetListMethod.class);
	    assertEquals("java.util.ArrayList", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor));
	}

	@Test
	public void nullCollectionTypeIsSet() throws Exception {

	    createPropertyDescriptor("strings", NullSetPropertyWithSetSetMethod.class);
	    assertEquals("java.util.HashSet", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor));
	}
	
    @Test
    public void nullCollectionTypeIsTreeSet() throws Exception {

        createPropertyDescriptor("strings", NullTreeSetPropertyWithSetTreeSetMethod.class);
        assertEquals("java.util.TreeSet", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor));
    }

    @Test
    public void nullCollectionTypeIsQueue() throws Exception {

        createPropertyDescriptor("strings", NullQueuePropertyWithSetQueueMethod.class);
        assertEquals("java.util.PriorityQueue", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor));
    }
    
    @Test
    public void nullCollectionTypeWhenPropertyIsNotACollection() throws Exception {
        
        createPropertyDescriptor("name", House.class);
        assertEquals(null, testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor));
        
    }

    @Test
    public void nullCollectionTypeIsLinkedList() throws Exception {

        createPropertyDescriptor("strings", NullLinkedListPropertyWithSetLinkedListMethod.class);
        assertEquals("java.util.LinkedList", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor));
    }

    @Test
    public void nullCollectionTypeWhenUnrecognisedInterface() throws Exception {

        createPropertyDescriptor("strings", NullLinkedListPropertyWithSetLinkedListMethod.class);
        assertEquals("java.util.LinkedList", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor));
    }

    @Test
    public void interfacePropertiesAreNotBuilders() throws Exception {
        
        createPropertyDescriptor("anInterface", BeanWithAnInterfaceProperty.class);
        assertFalse("property should not be a builder", testee.isBuilder(propertyDescriptor, classesToIgnore));
    }
    
    @Test
    public void collectionsOfInterfacePropertiesAreNotBuilders() throws Exception {
        
        createPropertyDescriptor("anInterfacesArrayList", BeanWithAnInterfaceCollectionProperty.class);
        assertFalse("property should not be a builder", testee.isBuilder(propertyDescriptor, classesToIgnore));
    }

    @Test
    public void targetTypeClassForNonGenericList() throws Exception {
        
        createPropertyDescriptor("things", BeanWithNonGenericCollections.class);
        Class<?> targetTypeClass = testee.getTargetTypeClass(propertyDescriptor);
        assertEquals(Object.class, targetTypeClass);
    }
    
    @Ignore
    @Test
    public void cannotInitialiseAnUnknownSubInterfaceOfCollection() throws Exception {
        
        createPropertyDescriptor("subList", BeanWithNullSubListInterfaceProperty.class);
        assertFalse(testee.isCollectionNeedsInitialising(BeanWithNullSubListInterfaceProperty.class, propertyDescriptor));
        assertNull(testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor));
        assertEquals("setSubList", testee.getCollectionMethodSettingInvocation(propertyDescriptor, BeanWithNullSubListInterfaceProperty.class));
    }
    
    @Test
    public void treatUnknownSubInterfaceOfCollectionAsSimpleType() throws Exception {
        
        createPropertyDescriptor("subList", BeanWithNullSubListInterfaceProperty.class);
        assertFalse(testee.isCollection(propertyDescriptor));
        assertFalse(testee.isBuilder(propertyDescriptor, classesToIgnore));
    }

}
