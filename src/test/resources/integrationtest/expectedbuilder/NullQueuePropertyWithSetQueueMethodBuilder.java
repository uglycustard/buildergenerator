package integrationtest.generatedbuilder;

public class NullQueuePropertyWithSetQueueMethodBuilder {

    public static NullQueuePropertyWithSetQueueMethodBuilder aNullQueuePropertyWithSetQueueMethod() {
        return new NullQueuePropertyWithSetQueueMethodBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NullQueuePropertyWithSetQueueMethod target = new uk.co.buildergenerator.testmodel.NullQueuePropertyWithSetQueueMethod();
    
    private NullQueuePropertyWithSetQueueMethodBuilder() {}
    
    public NullQueuePropertyWithSetQueueMethodBuilder withString(java.lang.String string) {
        if (target.getStrings() == null) {
            target.setStrings(new java.util.PriorityQueue<java.lang.String>());
        }        
        target.getStrings().add(string);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.NullQueuePropertyWithSetQueueMethod build() {
        return target;
    }
}