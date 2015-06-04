package integrationtest.generatedbuilder;

public class NodeTwoBuilder {

    public static NodeTwoBuilder aNodeTwo() {
        return new NodeTwoBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NodeTwo target = new uk.co.buildergenerator.testmodel.NodeTwo();
    
    public NodeTwoBuilder() {}
    
    public NodeTwoBuilder withNodeThree(integrationtest.generatedbuilder.NodeThreeBuilder nodeThree) {
        getTarget().setNodeThree(nodeThree.build());
        return this;
    }
    
    public NodeTwoBuilder withNodeTwoString(java.lang.String nodeTwoString) {
        getTarget().setNodeTwoString(nodeTwoString);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.NodeTwo getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.NodeTwo build() {
        return getTarget();
    }
}
