package uk.co.buildergenerator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.co.buildergenerator.testmodel.Root;

public class PropertiesToIgnoreTest {
    
    private PropertiesToIgnore testee;
    
    @Before
    public void setup() throws Exception {
        testee = new PropertiesToIgnore();
    }

    @Test
    public void propertyIsNotIgnoreIfNotAdded() {
        assertFalse("property should not have been ignored", testee.isPropertyIgnored(Root.class, "rootString"));
    }

    @Test
    public void propertyIsIgnoreIfItHasBeenAdded() {
        testee.addPropertyToIgnore(Root.class, "rootString");
        assertTrue("property should have been ignored", testee.isPropertyIgnored(Root.class, "rootString"));
    }
    
    @Test
    public void classPropertyIsIgnoreByDefault() throws Exception {
        
        assertTrue("property should have been ignored", testee.isPropertyIgnored(Root.class, "class"));
    }

}
