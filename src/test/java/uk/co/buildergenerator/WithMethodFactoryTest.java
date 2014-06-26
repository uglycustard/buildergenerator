package uk.co.buildergenerator;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.junit.Assert.assertEquals;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import uk.co.buildergenerator.testmodel.Address;
import uk.co.buildergenerator.testmodel.ArrayOfStringsPropertyWithSetArrayMethod;
import uk.co.buildergenerator.testmodel.BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors;
import uk.co.buildergenerator.testmodel.BooleanPropertyBean;
import uk.co.buildergenerator.testmodel.Delegate;
import uk.co.buildergenerator.testmodel.Hostess;
import uk.co.buildergenerator.testmodel.House;
import uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethod;
import uk.co.buildergenerator.testmodel.InitialisedListPropertyWithAddMethodAndSetListMethod;
import uk.co.buildergenerator.testmodel.InitialisedListPropertyWithSetListMethod;
import uk.co.buildergenerator.testmodel.NullListPropertyWithSetListMethod;
import uk.co.buildergenerator.testmodel.NullQueuePropertyWithSetQueueMethod;
import uk.co.buildergenerator.testmodel.NullSetPropertyWithSetSetMethod;
import uk.co.buildergenerator.testmodel.Person;
import uk.co.buildergenerator.testmodel.SubTarget;
import uk.co.buildergenerator.testmodel.Target;

public class WithMethodFactoryTest {

    private static final String BUILDER_PACKAGE = "uk.co.buildergeneratorbuilder";
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private String constructExpectedBuilderType(Class<?> c) {
        String className = c.getName();
        return BUILDER_PACKAGE + className.substring(className.lastIndexOf(".")) + "Builder";
    }

    private void assertWithMethodsEqual(WithMethod expected, WithMethod actual) {
        String expectedToString = ToStringBuilder.reflectionToString(expected, SHORT_PREFIX_STYLE);
        String actualToString = ToStringBuilder.reflectionToString(actual, SHORT_PREFIX_STYLE);
        assertEquals(expectedToString, actualToString);
    }

    @Test
    public void stringMethod() throws Exception {
        
        WithMethod expected = new WithMethod("Day", "java.lang.String", "day", false, false, null, null, null, null, false, null);
        WithMethod actual = TestUtils.createWithMethod("day", Target.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }

    @Test
    public void booleanMethod() throws Exception {
        
        WithMethod expected = new WithMethod("Hungry", "boolean", "hungry", false, false, null, null, null, null, false, null);
        WithMethod actual = TestUtils.createWithMethod("hungry", Target.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
    @Test
    public void beanWithBooleanPrimitive() throws Exception {
        
        WithMethod expected = new WithMethod("TheBoolean", "boolean", "theBoolean", false, false, null, null, null, null, false, null);
        WithMethod actual = TestUtils.createWithMethod("theBoolean", BooleanPropertyBean.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
    @Test
    public void listOfStrings() throws Exception {
        
        WithMethod expected = new WithMethod("Month", "java.lang.String", "month", true, false, "getMonths", "setMonths", "java.util.ArrayList", "addMonth", false, null);
        WithMethod actual = TestUtils.createWithMethod("months", Target.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
    @Test
    public void builderMethod() throws Exception {
        
        WithMethod expected = new WithMethod("Delegate", constructExpectedBuilderType(Delegate.class), "delegate", false, false, null, null, null, null, true, Delegate.class.getName());
        WithMethod actual = TestUtils.createWithMethod("delegate", Target.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
    @Test
    public void listOfBuilders() throws Exception {
        
        WithMethod expected = new WithMethod("Person", constructExpectedBuilderType(Person.class), "person", true, false, "getPersons", "setPersons", "java.util.ArrayList", "addPerson", true, Person.class.getName());
        WithMethod actual = TestUtils.createWithMethod("persons", Target.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
    @Test
    public void singularBuilderWithFieldNameEndingWithDoubleS() throws Exception {
        
        WithMethod expected = new WithMethod("Address", constructExpectedBuilderType(Address.class), "address", false, false, null, null, null, null, true, Address.class.getName());
        WithMethod actual = TestUtils.createWithMethod("address", Target.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
    @Test
    public void listOfBuildersWithFieldNameEndingWithSses() throws Exception {

        WithMethod expected = new WithMethod("Hostess", constructExpectedBuilderType(Hostess.class), "hostess", true, false, "getHostesses", "setHostesses", "java.util.ArrayList", "addHostess", true, Hostess.class.getName());
        WithMethod actual = TestUtils.createWithMethod("hostesses", Target.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }

    @Test
    public void singularBuilderWithFieldNameEndingWithSe() throws Exception {
        
        WithMethod expected = new WithMethod("House", constructExpectedBuilderType(House.class), "house", false, false, null, null, null, null, true, House.class.getName());
        WithMethod actual = TestUtils.createWithMethod("house", Target.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }

    @Test
    public void listOfBuildersWithFieldNameEndingWithSes() throws Exception {
        
        WithMethod expected = new WithMethod("House", constructExpectedBuilderType(House.class), "house", true, false, "getHouses", "setHouses", "java.util.ArrayList", "addHouse", true, House.class.getName());
        WithMethod actual = TestUtils.createWithMethod("houses", Address.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }

    @Test
    public void subClassFields() throws Exception {

        WithMethod expected = new WithMethod("Night", "java.lang.String", "night", false, false, null, null, null, null, false, null);
        WithMethod actual = TestUtils.createWithMethod("night", SubTarget.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
    @Test
    public void inherittedFields() throws Exception {

        WithMethod expected = new WithMethod("Day", "java.lang.String", "day", false, false, null, null, null, null, false, null);
        WithMethod actual = TestUtils.createWithMethod("day", SubTarget.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }

    @Test
    public void listPropertyWithAddMethod() throws Exception {
        
        WithMethod expected = new WithMethod("String", "java.lang.String", "string", true, false, "getStrings", "setStrings", "java.util.ArrayList", "addString", false, null);
        WithMethod actual = TestUtils.createWithMethod("strings", InitialisedListPropertyWithAddMethod.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
    @Test
    public void listPropertyWithSetListMethod() throws Exception {
        
        WithMethod expected = new WithMethod("String", "java.lang.String", "string", true, false, "getStrings", "setStrings", "java.util.ArrayList", "getStrings().add", false, null);
        WithMethod actual = TestUtils.createWithMethod("strings", InitialisedListPropertyWithSetListMethod.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }

    @Test
    public void listPropertyWithAddMethodAndSetListMethod() throws Exception {
        
        WithMethod expected = new WithMethod("String", "java.lang.String", "string", true, false, "getStrings", "setStrings", "java.util.ArrayList", "addString", false, null);
        WithMethod actual = TestUtils.createWithMethod("strings", InitialisedListPropertyWithAddMethodAndSetListMethod.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
    @Test
    public void listPropertyWithSetListMethodAndGetListReturnsNull() throws Exception {
        
        WithMethod expected = new WithMethod("String", "java.lang.String", "string", true, true, "getStrings", "setStrings", "java.util.ArrayList", "getStrings().add", false, null);
        WithMethod actual = TestUtils.createWithMethod("strings", NullListPropertyWithSetListMethod.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
    @Test
    public void setPropertyWithSetSetMethodAndGetSetReturnsNull() throws Exception {
        
        WithMethod expected = new WithMethod("String", "java.lang.String", "string", true, true, "getStrings", "setStrings", "java.util.HashSet", "getStrings().add", false, null);
        WithMethod actual = TestUtils.createWithMethod("strings", NullSetPropertyWithSetSetMethod.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }

    @Test
    public void queuePropertyWithSetQueueMethodAndGetQueueReturnsNull() throws Exception {
        
        WithMethod expected = new WithMethod("String", "java.lang.String", "string", true, true, "getStrings", "setStrings", "java.util.PriorityQueue", "getStrings().add", false, null);
        WithMethod actual = TestUtils.createWithMethod("strings", NullQueuePropertyWithSetQueueMethod.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
    @Test
    public void arrayOfStringsProperty() throws Exception {
        
        WithMethod expected = new WithMethod("Strings", "java.lang.String[]", "strings", false, false, null, null, null, null, false, null);
        WithMethod actual = TestUtils.createWithMethod("strings", ArrayOfStringsPropertyWithSetArrayMethod.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
    @Test
    public void beanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors() throws Exception {
        
        WithMethod expected = new WithMethod("Wibble", "java.lang.String", "wibble", false, false, null, null, null, null, false, null);
        WithMethod actual = TestUtils.createWithMethod("wibble", BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors.class, BUILDER_PACKAGE);
        assertWithMethodsEqual(expected, actual);
    }
    
}
