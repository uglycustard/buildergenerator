package uk.co.buildergenerator;

import static org.junit.Assert.assertFalse;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import org.junit.Before;
import org.junit.Test;

import uk.co.buildergenerator.testmodel.BeanWithJodaTime;

public class BuilderGeneratorUtilsTest {

	private BuilderGeneratorUtils testee;
	
	private ClassesToIgnore classesToIgnore = new ClassesToIgnore();
	
//	@Mock
	private PropertyDescriptor propertyDescriptor;
	
	@Before
	public void setup() throws Exception {
//		initMocks(this);
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


}
