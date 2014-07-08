package integrationtest.generatedbuilder;

public class BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessorsBuilder {

    public static BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessorsBuilder aBeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors() {
        return new BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessorsBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors target = new uk.co.buildergenerator.testmodel.BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors();
    
    public BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessorsBuilder() {}
    
    public BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessorsBuilder withWibble(java.lang.String wibble) {
        target.setWibble(wibble);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWhereFieldNameDiffersFromBeanProperteyNameFromAccessors build() {
        return target;
    }
}
