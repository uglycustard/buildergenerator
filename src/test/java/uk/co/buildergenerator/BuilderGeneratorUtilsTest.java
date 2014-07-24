package uk.co.buildergenerator;

import static org.junit.Assert.*;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import uk.co.buildergenerator.testmodel.BeanWithAnInterfaceCollectionProperty;
import uk.co.buildergenerator.testmodel.BeanWithAnInterfaceProperty;
import uk.co.buildergenerator.testmodel.BeanWithJodaTime;
import uk.co.buildergenerator.testmodel.House;
import uk.co.buildergenerator.testmodel.NullCollectionPropertyWithSetCollectionMethod;
import uk.co.buildergenerator.testmodel.NullLinkedListPropertyWithSetLinkedListMethod;
import uk.co.buildergenerator.testmodel.NullListPropertyWithSetListMethod;
import uk.co.buildergenerator.testmodel.NullQueuePropertyWithSetQueueMethod;
import uk.co.buildergenerator.testmodel.NullSetPropertyWithSetSetMethod;
import uk.co.buildergenerator.testmodel.NullTreeSetPropertyWithSetTreeSetMethod;
import uk.co.buildergenerator.testmodel.NullUnrecognisedCollectionPropertyWithSetUnrecognisedCollectionMethod;

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
    public void unrecognisedNullCollectionType() throws Exception {
        
        createPropertyDescriptor("bag", NullUnrecognisedCollectionPropertyWithSetUnrecognisedCollectionMethod.class);
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("don't know how to initialiase null collection type org.apache.commons.collections.Bag, please raise issue at https://github.com/uglycustard/buildergenerator/issues");
        testee.getCollectionTypeWhenCollectionNeedsInitialising(propertyDescriptor);
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

}
