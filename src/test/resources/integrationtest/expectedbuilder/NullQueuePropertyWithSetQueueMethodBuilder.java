package integrationtest.generatedbuilder;

public class NullQueuePropertyWithSetQueueMethodBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.NullQueuePropertyWithSetQueueMethod> {

    public static NullQueuePropertyWithSetQueueMethodBuilder aNullQueuePropertyWithSetQueueMethod() {
        return new NullQueuePropertyWithSetQueueMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullQueuePropertyWithSetQueueMethod target = new uk.co.buildergenerator.testmodel.NullQueuePropertyWithSetQueueMethod();
    
    public NullQueuePropertyWithSetQueueMethodBuilder() {}
    
    public NullQueuePropertyWithSetQueueMethodBuilder withString(java.lang.String string) {
        if (getTarget().getStrings() == null) {
            getTarget().setStrings(new java.util.PriorityQueue<java.lang.String>());
        }        
        getTarget().getStrings().add(string);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.NullQueuePropertyWithSetQueueMethod getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.NullQueuePropertyWithSetQueueMethod build() {
        return getTarget();
    }
}