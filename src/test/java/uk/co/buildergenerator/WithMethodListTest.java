package uk.co.buildergenerator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Test;

import uk.co.buildergenerator.testmodel.BeanWithNonWritableProperty;
import uk.co.buildergenerator.testmodel.BeanWithPropertyToIgnore;
import uk.co.buildergenerator.testmodel.BooleanPropertyBean;
import uk.co.buildergenerator.testmodel.House;
import uk.co.buildergenerator.testmodel.Target;

public class WithMethodListTest {
    
    
    private static final String BUILDER_PACKAGE = "builder.package";
    private List<String> propertiesToIgnore = new ArrayList<String>();

    private WithMethodList createTestee(Class<?> targetClass) {
        return new WithMethodList(targetClass, BUILDER_PACKAGE, propertiesToIgnore);
    }

    private WithMethod find(List<WithMethod> withMethodList, String parameterName) {
        for (WithMethod candidateWithMethod : withMethodList) {
            if (parameterName.equals(candidateWithMethod.getParameterName())) {
                return candidateWithMethod;
            }
        }
        return null;
    }

    @Test
    public void targetWithStringProperty() throws Exception {
        
        List<WithMethod> withMethodList = createTestee(House.class);
        WithMethod actualWithMethod = find(withMethodList, "name");
        WithMethod expectedWithMethod = TestUtils.createWithMethod("name", House.class, BUILDER_PACKAGE);
        assertTrue(EqualsBuilder.reflectionEquals(expectedWithMethod, actualWithMethod));
    }

    @Test
    public void targetWithListOfStringsProperty() throws Exception {
        
        List<WithMethod> withMethodList = createTestee(Target.class);
        WithMethod actualWithMethod = find(withMethodList, "month");
        WithMethod expectedWithMethod = TestUtils.createWithMethod("months", Target.class, BUILDER_PACKAGE);
        assertTrue(EqualsBuilder.reflectionEquals(expectedWithMethod, actualWithMethod));
    }

    @Test
    public void targetWithNonJavaLangPropertyRequiringBuilder() throws Exception {
        
        List<WithMethod> withMethodList = createTestee(Target.class);
        WithMethod actualWithMethod = find(withMethodList, "delegate");
        WithMethod expectedWithMethod = TestUtils.createWithMethod("delegate", Target.class, BUILDER_PACKAGE);
        assertTrue(EqualsBuilder.reflectionEquals(expectedWithMethod, actualWithMethod));
    }

    @Test
    public void targetWithListOfNonJavaLangPropertyRequiringBuilders() throws Exception {
        
        List<WithMethod> withMethodList = createTestee(Target.class);
        WithMethod actualWithMethod = find(withMethodList, "hostess");
        WithMethod expectedWithMethod = TestUtils.createWithMethod("hostesses", Target.class, BUILDER_PACKAGE);
        assertTrue(EqualsBuilder.reflectionEquals(expectedWithMethod, actualWithMethod));
    }

    @Test
    public void targetWithMulitplePropertiesOfDifferentTypes() throws Exception {
        
        List<WithMethod> withMethodList = createTestee(Target.class);
        assertEquals(8, withMethodList.size());
    }

    @Test
    public void targetWithBooleanPrimitiveProperty() throws Exception {
        
        List<WithMethod> withMethodList = createTestee(BooleanPropertyBean.class);
        WithMethod actualWithMethod = find(withMethodList, "theBoolean");
        WithMethod expectedWithMethod = TestUtils.createWithMethod("theBoolean", BooleanPropertyBean.class, BUILDER_PACKAGE);
        assertTrue(EqualsBuilder.reflectionEquals(expectedWithMethod, actualWithMethod));
    }

    @Test
    public void beanWithNonWritablePropertyDoesNotCreateWiothMethodForTheNonWritableProperty() throws Exception {
        
        List<WithMethod> withMethodList = createTestee(BeanWithNonWritableProperty.class);
        WithMethod actualWithMethod = find(withMethodList, "nonWritableProperty");
        assertNull("expected no WithMethod for the non writable property", actualWithMethod);
    }
    
    @Test
    public void ignorePropertiesIfSpecifiedAndPresentInTargetClass() throws Exception {
        
        propertiesToIgnore.add("propertyToIgnore");
        List<WithMethod> withMethodList = createTestee(BeanWithPropertyToIgnore.class);
        WithMethod actualWithMethod = find(withMethodList, "propertyToIgnore");
        assertNull("expected no WithMethod for the ignored property", actualWithMethod);
    }

    @Test
    public void nullIgnoreProperties() throws Exception {
        
        propertiesToIgnore = null;
        List<WithMethod> withMethodList = createTestee(BeanWithPropertyToIgnore.class);
        WithMethod actualWithMethod = find(withMethodList, "propertyToIgnore");
        assertNotNull("expected no WithMethod for the ignored property", actualWithMethod);
    }

}
