package integrationtest.generatedbuilder;

public class RootBuilder {

    public static RootBuilder aRoot() {
        return new RootBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.Root target = new uk.co.buildergenerator.testmodel.Root();
    
    public RootBuilder() {}
    
    public RootBuilder withNodeTwo(integrationtest.generatedbuilder.NodeTwoBuilder nodeTwo) {
        target.setNodeTwo(nodeTwo.build());
        return this;
    }
    
    public RootBuilder withRootString(java.lang.String rootString) {
        target.setRootString(rootString);
        return this;
    }
    
    public RootBuilder withNodeOne(integrationtest.generatedbuilder.NodeOneBuilder nodeOne) {
        target.setNodeOne(nodeOne.build());
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.Root build() {
        return target;
    }
}
