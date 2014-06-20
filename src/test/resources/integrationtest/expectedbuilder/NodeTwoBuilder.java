package integrationtest.generatedbuilder;

public class NodeTwoBuilder {

    public static NodeTwoBuilder aNodeTwo() {
        return new NodeTwoBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NodeTwo target = new uk.co.buildergenerator.testmodel.NodeTwo();
    
    private NodeTwoBuilder() {}
    
    public NodeTwoBuilder withNodeThree(integrationtest.generatedbuilder.NodeThreeBuilder nodeThree) {
        target.setNodeThree(nodeThree.build());
        return this;
    }
    
    public NodeTwoBuilder withNodeTwoString(java.lang.String nodeTwoString) {
        target.setNodeTwoString(nodeTwoString);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.NodeTwo build() {
        return target;
    }
}
