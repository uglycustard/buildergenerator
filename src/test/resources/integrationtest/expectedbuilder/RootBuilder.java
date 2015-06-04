package integrationtest.generatedbuilder;

public class RootBuilder {

    public static RootBuilder aRoot() {
        return new RootBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.Root target = new uk.co.buildergenerator.testmodel.Root();
    
    public RootBuilder() {}
    
    public RootBuilder withNodeTwo(integrationtest.generatedbuilder.NodeTwoBuilder nodeTwo) {
        getTarget().setNodeTwo(nodeTwo.build());
        return this;
    }
    
    public RootBuilder withRootString(java.lang.String rootString) {
        getTarget().setRootString(rootString);
        return this;
    }
    
    public RootBuilder withNodeOne(integrationtest.generatedbuilder.NodeOneBuilder nodeOne) {
        getTarget().setNodeOne(nodeOne.build());
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.Root getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.Root build() {
        return getTarget();
    }
}
