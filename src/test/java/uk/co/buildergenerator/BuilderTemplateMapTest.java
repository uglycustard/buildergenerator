package uk.co.buildergenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static uk.co.buildergenerator.BuilderTemplateMap.BUILDER_PACKAGE_MAP_KEY;
import static uk.co.buildergenerator.BuilderTemplateMap.FACTORY_METHOD_PREFIX_MAP_KEY;
import static uk.co.buildergenerator.BuilderTemplateMap.FULLY_QUALIFIED_TARGET_CLASS_NAME_MAP_KEY;
import static uk.co.buildergenerator.BuilderTemplateMap.TARGET_CLASS_NAME_MAP_KEY;
import static uk.co.buildergenerator.BuilderTemplateMap.WITH_METHOD_LIST_MAP_KEY;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Test;

import uk.co.buildergenerator.testmodel.Address;
import uk.co.buildergenerator.testmodel.BeanWithPropertyToIgnore;
import uk.co.buildergenerator.testmodel.House;
import uk.co.buildergenerator.testmodel.SubTarget;
import uk.co.buildergenerator.testmodel.Target;

public class BuilderTemplateMapTest {

    private PropertiesToIgnore propertiesToIgnore = new PropertiesToIgnore();
    private ClassesToIgnore classesToIgnore = new ClassesToIgnore();

    @Test
    public void targetClassName() {
        
        BuilderTemplateMap testee = new BuilderTemplateMap(Target.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore);
        String expectedTargetClassName = Target.class.getSimpleName();
        assertEquals(expectedTargetClassName, testee.get(TARGET_CLASS_NAME_MAP_KEY));
        assertEquals(expectedTargetClassName, testee.getTargetClassName());
    }

    @Test
    public void targetClassFullyQualifiedName() throws Exception {
        
        BuilderTemplateMap testee = new BuilderTemplateMap(House.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore);
        String expectedTargetClassFullyQualifiedName = House.class.getName();
        assertEquals(expectedTargetClassFullyQualifiedName, testee.get(FULLY_QUALIFIED_TARGET_CLASS_NAME_MAP_KEY));
        assertEquals(expectedTargetClassFullyQualifiedName, testee.getFullyQualifiedTargetClassName());
    }
    
    @Test
    public void factoryMethodPrefix() throws Exception {

        BuilderTemplateMap testee = new BuilderTemplateMap(House.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore);
        assertEquals("a", testee.get(FACTORY_METHOD_PREFIX_MAP_KEY));
    }
    
    @Test
    public void factoryMethodPrefixForClassStartingWithVowel() throws Exception {

        BuilderTemplateMap testee = new BuilderTemplateMap(Address.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore);
        assertEquals("an", testee.get(FACTORY_METHOD_PREFIX_MAP_KEY));
    }
    
    @Test
    public void withMethods() throws Exception {
        
        WithMethodList expectedWithMethodList = new WithMethodList(SubTarget.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore);
        BuilderTemplateMap testee = new BuilderTemplateMap(SubTarget.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore);
        WithMethodList actualWithMethodList = (WithMethodList) testee.get(WITH_METHOD_LIST_MAP_KEY);
        assertTrue(EqualsBuilder.reflectionEquals(expectedWithMethodList, actualWithMethodList));
        assertSame(actualWithMethodList, testee.getWithMethodList());
    }

    @Test
    public void withMethodsWithIgnoreProperties() throws Exception {
        
        propertiesToIgnore.addPropertyToIgnore(BeanWithPropertyToIgnore.class, "propertyToIgnore");
        WithMethodList expectedWithMethodList = new WithMethodList(BeanWithPropertyToIgnore.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore);
        BuilderTemplateMap testee = new BuilderTemplateMap(BeanWithPropertyToIgnore.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore);
        WithMethodList actualWithMethodList = (WithMethodList) testee.get(WITH_METHOD_LIST_MAP_KEY);
        assertTrue(EqualsBuilder.reflectionEquals(expectedWithMethodList, actualWithMethodList));
        assertSame(actualWithMethodList, testee.getWithMethodList());
        assertEquals("should have ignore property", 1, actualWithMethodList.size());
    }

    @Test
    public void builderPackage() throws Exception {

        String builderPackage = "some.other.package";
        BuilderTemplateMap testee = new BuilderTemplateMap(Address.class, builderPackage, propertiesToIgnore, classesToIgnore);
        assertEquals(builderPackage, testee.get(BUILDER_PACKAGE_MAP_KEY));
        assertEquals(builderPackage, testee.getBuilderPackage());
    }

}
