package uk.co.buildergenerator.testmodel;

public class AudiBaseBuilder<T extends uk.co.buildergenerator.testmodel.AudiBaseBuilder<T>> extends uk.co.buildergenerator.testmodel.CustomerCarBuilder<T> implements uk.co.buildergenerator.testmodel.Builder<uk.co.buildergenerator.testmodel.Audi> {

    private uk.co.buildergenerator.testmodel.Audi target = new uk.co.buildergenerator.testmodel.Audi();
    
    public AudiBaseBuilder() {}
    
    public T withAudiSpecificProperty(java.lang.String audiSpecificProperty) {
        getTarget().setAudiSpecificProperty(audiSpecificProperty);
        return (T) this;
    }
    
    public T withGenericCarProperty(java.lang.String genericCarProperty) {
        getTarget().setGenericCarProperty(genericCarProperty);
        return (T) this;
    }
    
    protected uk.co.buildergenerator.testmodel.Audi getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.Audi build() {
        return getTarget();
    }
}
