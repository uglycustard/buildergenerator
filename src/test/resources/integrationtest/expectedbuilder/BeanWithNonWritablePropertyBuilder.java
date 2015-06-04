package integrationtest.generatedbuilder;

public class BeanWithNonWritablePropertyBuilder {

    public static BeanWithNonWritablePropertyBuilder aBeanWithNonWritableProperty() {
        return new BeanWithNonWritablePropertyBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithNonWritableProperty target = new uk.co.buildergenerator.testmodel.BeanWithNonWritableProperty();
    
    public BeanWithNonWritablePropertyBuilder() {}
    
    public BeanWithNonWritablePropertyBuilder withWritableProperty(java.lang.String writableProperty) {
        getTarget().setWritableProperty(writableProperty);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BeanWithNonWritableProperty getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithNonWritableProperty build() {
        return getTarget();
    }
}
