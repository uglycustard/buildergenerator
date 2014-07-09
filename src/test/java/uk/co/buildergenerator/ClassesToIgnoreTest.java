package uk.co.buildergenerator;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import uk.co.buildergenerator.testmodel.BeanWithNestedClass;
import uk.co.buildergenerator.testmodel.House;
import uk.co.buildergenerator.testmodel.Root;

public class ClassesToIgnoreTest {
    
    private ClassesToIgnore testee;
    
    @Before
    public void setup() throws Exception {
        testee = new ClassesToIgnore();
    }

    @Test
    public void doesNotIgnoreClassIfClassNameNotAdded() {
        assertFalse("should not have been ignored", testee.isIgnored(Root.class));
    }

    @Test
    public void isIgnoredIfClassNameHasBeenAdded() {
        testee.add(Root.class.getName());
        assertTrue("should have been ignored", testee.isIgnored(Root.class));
    }

    @Test
    public void isIgnoredIfNestedClassNameHasBeenAdded() {
        
        testee.add(BeanWithNestedClass.NestedClass.class.getCanonicalName());
        assertTrue("should have been ignored", testee.isIgnored(BeanWithNestedClass.NestedClass.class));
    }

    @Test
    public void isIgnoredIfClassNameHasBeenAddedUsingWildCard() {
        testee.add("uk.co.buildergenerator.testmodel.Hou*");
        assertTrue("should have been ignored", testee.isIgnored(House.class));
    }

    @Test
    public void isNotIgnoredIfClassNameHasBeenAddedButWildCardIsMissing() {
        testee.add("uk.co.buildergenerator.testmodel.Hou");
        assertFalse("should not have been ignored", testee.isIgnored(House.class));
    }

    @Test
    public void isIgnoredIfPackageNameHasBeenAddedUsingWildCard() {
        testee.add("uk.co.buildergenerator.testmodel*");
        assertTrue("should have been ignored", testee.isIgnored(House.class));
    }

    @Test
    public void javaPackageIsIgnoreByDefault() throws Exception {
        
        assertTrue("should have been ignored", testee.isIgnored(java.lang.String.class));
        assertTrue("should have been ignored", testee.isIgnored(java.io.File.class));
    }

    @Test
    public void jodaTimePackageIsIgnoreByDefault() throws Exception {
        
        assertTrue("should have been ignored", testee.isIgnored(LocalTime.class));
        assertTrue("should have been ignored", testee.isIgnored(LocalDate.class));
        assertTrue("should have been ignored", testee.isIgnored(LocalDateTime.class));
        assertTrue("should have been ignored", testee.isIgnored(DateTime.class));
    }
}
