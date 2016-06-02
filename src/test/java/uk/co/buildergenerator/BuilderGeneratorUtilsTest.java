package uk.co.buildergenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import uk.co.buildergenerator.testmodel.BeanWithAnInterfaceCollectionProperty;
import uk.co.buildergenerator.testmodel.BeanWithAnInterfaceProperty;
import uk.co.buildergenerator.testmodel.BeanWithJodaTime;
import uk.co.buildergenerator.testmodel.BeanWithMapProperty;
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
	    Map<Class<?>, String> collectionInitialisationTypes = new LinkedHashMap<Class<?>, String>();
	    collectionInitialisationTypes.put(Collection.class, "java.util.ArrayList");
		assertEquals("java.util.ArrayList", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor, collectionInitialisationTypes ));
    }

	@Test
	public void nullCollectionTypeIsList() throws Exception {

	    createPropertyDescriptor("strings", NullListPropertyWithSetListMethod.class);
	    Map<Class<?>, String> collectionInitialisationTypes = new LinkedHashMap<Class<?>, String>();
	    collectionInitialisationTypes.put(List.class, "java.util.ArrayList");
	    assertEquals("java.util.ArrayList", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor, collectionInitialisationTypes));
	}

	@Test
	public void nullCollectionTypeIsSet() throws Exception {

	    createPropertyDescriptor("strings", NullSetPropertyWithSetSetMethod.class);
	    Map<Class<?>, String> collectionInitialisationTypes = new LinkedHashMap<Class<?>, String>();
	    collectionInitialisationTypes.put(Set.class, "java.util.LinkedHashSet");
	    assertEquals("java.util.LinkedHashSet", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor, collectionInitialisationTypes));
	}
	
    @Test
    public void nullCollectionTypeIsTreeSet() throws Exception {

        createPropertyDescriptor("strings", NullTreeSetPropertyWithSetTreeSetMethod.class);
	    Map<Class<?>, String> collectionInitialisationTypes = new LinkedHashMap<Class<?>, String>();
        assertEquals("java.util.TreeSet", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor, collectionInitialisationTypes));
    }

    @Test
    public void nullCollectionTypeIsQueue() throws Exception {

        createPropertyDescriptor("strings", NullQueuePropertyWithSetQueueMethod.class);
	    Map<Class<?>, String> collectionInitialisationTypes = new LinkedHashMap<Class<?>, String>();
	    collectionInitialisationTypes.put(Queue.class, "java.util.PriorityQueue");
        assertEquals("java.util.PriorityQueue", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor, collectionInitialisationTypes));
    }
    
    @Test
    public void nullCollectionTypeWhenPropertyIsNotACollection() throws Exception {
        
        createPropertyDescriptor("name", House.class);
	    Map<Class<?>, String> collectionInitialisationTypes = new LinkedHashMap<Class<?>, String>();
        assertEquals(null, testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor, collectionInitialisationTypes));
        
    }

    @Test
    public void nullCollectionTypeIsLinkedList() throws Exception {

        createPropertyDescriptor("strings", NullLinkedListPropertyWithSetLinkedListMethod.class);
	    Map<Class<?>, String> collectionInitialisationTypes = new LinkedHashMap<Class<?>, String>();
        assertEquals("java.util.LinkedList", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor, collectionInitialisationTypes));
    }

    @Test
    public void nullCollectionTypeWhenUnrecognisedInterface() throws Exception {

        createPropertyDescriptor("strings", NullLinkedListPropertyWithSetLinkedListMethod.class);
	    Map<Class<?>, String> collectionInitialisationTypes = new LinkedHashMap<Class<?>, String>();
        assertEquals("java.util.LinkedList", testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor, collectionInitialisationTypes));
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
	    Map<Class<?>, String> collectionInitialisationTypes = new LinkedHashMap<Class<?>, String>();
        assertNull(testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor, collectionInitialisationTypes));
        assertEquals("setSubList", testee.getCollectionMethodSettingInvocation(propertyDescriptor, BeanWithNullSubListInterfaceProperty.class));
    }
    
    @Test
    public void treatUnknownSubInterfaceOfCollectionAsSimpleType() throws Exception {
        
        createPropertyDescriptor("subList", BeanWithNullSubListInterfaceProperty.class);
        assertFalse(testee.isCollection(propertyDescriptor));
        assertFalse(testee.isBuilder(propertyDescriptor, classesToIgnore));
    }

    
    @Test
    public void isMap() throws Exception {
        
        assertTrue(testee.isMap(Map.class));
    }
    
    @Test
    public void isNotMap() throws Exception {
        
        assertFalse(testee.isMap(List.class));
    }

    @Test
    public void mapPropertyWithGenerics() throws Exception {
        
        createPropertyDescriptor("mapOfStrings", BeanWithMapProperty.class);
        assertEquals("java.util.Map<java.lang.String, java.lang.String>", testee.getParameterType(propertyDescriptor, null, classesToIgnore));
    }

    @Test
    public void mapPropertyWithGenericsOfTwoDifferentTypes() throws Exception {
        
        createPropertyDescriptor("mapOfHouse", BeanWithMapProperty.class);
        assertEquals("java.util.Map<java.lang.String, uk.co.buildergenerator.testmodel.House>", testee.getParameterType(propertyDescriptor, null, classesToIgnore));
    }

    
    @Test
    public void mapPropertyWithoutGenerics() throws Exception {
        
        createPropertyDescriptor("mapOfAnything", BeanWithMapProperty.class);
        assertEquals("java.util.Map", testee.getParameterType(propertyDescriptor, null, classesToIgnore));
    }

}
