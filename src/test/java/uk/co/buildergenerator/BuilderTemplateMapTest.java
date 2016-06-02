package uk.co.buildergenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static uk.co.buildergenerator.BuilderTemplateMap.BUILDER_INTERFACE_MAP_KEY;
import static uk.co.buildergenerator.BuilderTemplateMap.BUILDER_PACKAGE_MAP_KEY;
import static uk.co.buildergenerator.BuilderTemplateMap.FACTORY_METHOD_PREFIX_MAP_KEY;
import static uk.co.buildergenerator.BuilderTemplateMap.FULLY_QUALIFIED_TARGET_CLASS_NAME_MAP_KEY;
import static uk.co.buildergenerator.BuilderTemplateMap.GENERATION_GAP_BASE_BUILDER;
import static uk.co.buildergenerator.BuilderTemplateMap.GENERATION_GAP_BUILDER;
import static uk.co.buildergenerator.BuilderTemplateMap.SUPER_CLASS_MAP_KEY;
import static uk.co.buildergenerator.BuilderTemplateMap.SUPER_CLASS_SPECIFIED_MAP_KEY;
import static uk.co.buildergenerator.BuilderTemplateMap.TARGET_CLASS_NAME_MAP_KEY;
import static uk.co.buildergenerator.BuilderTemplateMap.WITH_METHOD_LIST_MAP_KEY;

import java.util.LinkedHashMap;
import java.util.Map;

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
	private Map<Class<?>, String> collectionInitialisationTypes = new LinkedHashMap<Class<?>, String>();

    
    
    @Test
    public void targetClassName() {
        
        BuilderTemplateMap testee = new BuilderTemplateMap(Target.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        String expectedTargetClassName = Target.class.getSimpleName();
        assertEquals(expectedTargetClassName, testee.get(TARGET_CLASS_NAME_MAP_KEY));
        assertEquals(expectedTargetClassName, testee.getTargetClassName());
    }

    @Test
    public void targetClassFullyQualifiedName() throws Exception {
        
        BuilderTemplateMap testee = new BuilderTemplateMap(House.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        String expectedTargetClassFullyQualifiedName = House.class.getName();
        assertEquals(expectedTargetClassFullyQualifiedName, testee.get(FULLY_QUALIFIED_TARGET_CLASS_NAME_MAP_KEY));
        assertEquals(expectedTargetClassFullyQualifiedName, testee.getFullyQualifiedTargetClassName());
    }
    
    @Test
    public void factoryMethodPrefix() throws Exception {

        BuilderTemplateMap testee = new BuilderTemplateMap(House.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        assertEquals("a", testee.get(FACTORY_METHOD_PREFIX_MAP_KEY));
    }
    
    @Test
    public void factoryMethodPrefixForClassStartingWithVowel() throws Exception {

        BuilderTemplateMap testee = new BuilderTemplateMap(Address.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        assertEquals("an", testee.get(FACTORY_METHOD_PREFIX_MAP_KEY));
    }
    
    @Test
    public void withMethods() throws Exception {
        
        WithMethodList expectedWithMethodList = new WithMethodList(SubTarget.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        BuilderTemplateMap testee = new BuilderTemplateMap(SubTarget.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        WithMethodList actualWithMethodList = (WithMethodList) testee.get(WITH_METHOD_LIST_MAP_KEY);
        assertTrue(EqualsBuilder.reflectionEquals(expectedWithMethodList, actualWithMethodList));
        assertSame(actualWithMethodList, testee.getWithMethodList());
    }

    @Test
    public void withMethodsWithIgnoreProperties() throws Exception {
        
        propertiesToIgnore.addPropertyToIgnore(BeanWithPropertyToIgnore.class, "propertyToIgnore");
        WithMethodList expectedWithMethodList = new WithMethodList(BeanWithPropertyToIgnore.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        BuilderTemplateMap testee = new BuilderTemplateMap(BeanWithPropertyToIgnore.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        WithMethodList actualWithMethodList = (WithMethodList) testee.get(WITH_METHOD_LIST_MAP_KEY);
        assertTrue(EqualsBuilder.reflectionEquals(expectedWithMethodList, actualWithMethodList));
        assertSame(actualWithMethodList, testee.getWithMethodList());
        assertEquals("should have ignore property", 1, actualWithMethodList.size());
    }

    @Test
    public void builderPackage() throws Exception {

        String builderPackage = "some.other.package";
        BuilderTemplateMap testee = new BuilderTemplateMap(Address.class, builderPackage, propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        assertEquals(builderPackage, testee.get(BUILDER_PACKAGE_MAP_KEY));
        assertEquals(builderPackage, testee.getBuilderPackage());
    }

    @Test
    public void notGenerationGapByDefault() throws Exception {
        
        BuilderTemplateMap testee = new BuilderTemplateMap(Target.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        assertFalse(testee.isGeneratioGapBaseBuilder());
        assertEquals(false, testee.get(GENERATION_GAP_BASE_BUILDER));
        assertFalse(testee.isGeneratioGapBuilder());
        assertEquals(false, testee.get(GENERATION_GAP_BUILDER));
    }
    
    @Test
    public void setAsGenerationGapBaseBuilder() throws Exception {
        
        BuilderTemplateMap testee = new BuilderTemplateMap(Target.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        testee.setAsGenerationGapBaseBuilder();
        assertTrue(testee.isGeneratioGapBaseBuilder());
        assertEquals(true, testee.get(GENERATION_GAP_BASE_BUILDER));
        assertFalse(testee.isGeneratioGapBuilder());
        assertEquals(false, testee.get(GENERATION_GAP_BUILDER));
    }

    @Test
    public void setGenerationGapBaseBuilderPackage() throws Exception {
        
        BuilderTemplateMap testee = new BuilderTemplateMap(Target.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        testee.setGenerationGapBaseBuilderPackage("new.package");
        assertEquals("new.package", testee.getGenerationGapBaseBuilderPackage());
        assertEquals("new.package.Builder", testee.getBuilderInterface());
    }

    @Test
    public void setAsGenerationGapBuilder() throws Exception {
        
        BuilderTemplateMap testee = new BuilderTemplateMap(Target.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        testee.setAsGenerationGapBuilder();
        assertFalse(testee.isGeneratioGapBaseBuilder());
        assertEquals(false, testee.get(GENERATION_GAP_BASE_BUILDER));
        assertTrue(testee.isGeneratioGapBuilder());
        assertEquals(true, testee.get(GENERATION_GAP_BUILDER));
    }

    @Test
    public void setAsGenerationGapBuilderAfterChangingGenerationGapBaseBuilderPackageResetsBuilderInterface() throws Exception {
        
        BuilderTemplateMap testee = new BuilderTemplateMap(Target.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        assertEquals("uk.co.buildergenerator.Builder", testee.getBuilderInterface());
        testee.setGenerationGapBaseBuilderPackage("new.package");
        assertEquals("new.package.Builder", testee.getBuilderInterface());
        testee.setAsGenerationGapBuilder();
        assertEquals("uk.co.buildergenerator.Builder", testee.getBuilderInterface());
    }

    @Test
	public void noSuperClassSpecifiedByDefault() throws Exception {
		
        BuilderTemplateMap testee = new BuilderTemplateMap(Target.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
    	assertFalse(testee.isSuperClassSpecified());
    	assertEquals(false, testee.get(SUPER_CLASS_SPECIFIED_MAP_KEY));
    	assertNull(testee.getSuperClass());
    	assertNull(testee.get(SUPER_CLASS_MAP_KEY));
	}
    
    @Test
	public void superClassSpecified() throws Exception {
		
        BuilderTemplateMap testee = new BuilderTemplateMap(Target.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        String superClass = "com.example.Something<T>";
        testee.setSuperClass(superClass);
    	assertTrue(testee.isSuperClassSpecified());
    	assertEquals(true, testee.get(SUPER_CLASS_SPECIFIED_MAP_KEY));
    	assertEquals(superClass, testee.getSuperClass());
    	assertEquals(superClass, testee.get(SUPER_CLASS_MAP_KEY));
	}
    
    @Test
	public void superClassSpecifiedAsNull() throws Exception {
		
        BuilderTemplateMap testee = new BuilderTemplateMap(Target.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        String superClass = null;
        testee.setSuperClass(superClass);
    	assertFalse(testee.isSuperClassSpecified());
    	assertEquals(false, testee.get(SUPER_CLASS_SPECIFIED_MAP_KEY));
    	assertEquals(superClass, testee.getSuperClass());
    	assertEquals(superClass, testee.get(SUPER_CLASS_MAP_KEY));
	}

    @Test
    public void builderInterface() throws Exception {
        
        BuilderTemplateMap testee = new BuilderTemplateMap(Target.class, "uk.co.buildergenerator", propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        String builderInterface = "uk.co.buildergenerator.Builder";
        assertEquals(builderInterface, testee.getBuilderInterface());
        assertEquals(builderInterface, testee.get(BUILDER_INTERFACE_MAP_KEY));
    }

}
