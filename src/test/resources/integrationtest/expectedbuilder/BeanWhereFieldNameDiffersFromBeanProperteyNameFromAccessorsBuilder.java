package integrationtest.generatedbuilder;

public class BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessorsBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors> {

    public static BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessorsBuilder aBeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors() {
        return new BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessorsBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors target = new uk.co.buildergenerator.testmodel.BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors();
    
    public BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessorsBuilder() {}
    
    public BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessorsBuilder withWibble(java.lang.String wibble) {
        getTarget().setWibble(wibble);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors build() {
        return getTarget();
    }
}
