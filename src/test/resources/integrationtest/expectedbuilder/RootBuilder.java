package integrationtest.generatedbuilder;

public class RootBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.Root> {

    public static RootBuilder aRoot() {
        return new RootBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.Root target = new uk.co.buildergenerator.testmodel.Root();
    
    public RootBuilder() {}
    
    public RootBuilder withNodeOne(integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.NodeOne> nodeOne) {
        getTarget().setNodeOne(nodeOne.build());
        return this;
    }
    
    public RootBuilder withNodeTwo(integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.NodeTwo> nodeTwo) {
        getTarget().setNodeTwo(nodeTwo.build());
        return this;
    }
    
    public RootBuilder withRootString(java.lang.String rootString) {
        getTarget().setRootString(rootString);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.Root getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.Root build() {
        return getTarget();
    }
}
