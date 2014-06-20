package integrationtest.generatedbuilder;

public class NodeThreeBuilder {

    public static NodeThreeBuilder aNodeThree() {
        return new NodeThreeBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NodeThree target = new uk.co.buildergenerator.testmodel.NodeThree();
    
    private NodeThreeBuilder() {}
    
    public NodeThreeBuilder withNodeThreeString(java.lang.String nodeThreeString) {
        target.setNodeThreeString(nodeThreeString);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.NodeThree build() {
        return target;
    }
}
