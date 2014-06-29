package integrationtest.generatedbuilder;

public class BeanWithNonWritablePropertyBuilder {

    public static BeanWithNonWritablePropertyBuilder aBeanWithNonWritableProperty() {
        return new BeanWithNonWritablePropertyBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithNonWritableProperty target = new uk.co.buildergenerator.testmodel.BeanWithNonWritableProperty();
    
    private BeanWithNonWritablePropertyBuilder() {}
    
    public BeanWithNonWritablePropertyBuilder withWritableProperty(java.lang.String writableProperty) {
        target.setWritableProperty(writableProperty);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithNonWritableProperty build() {
        return target;
    }
}
