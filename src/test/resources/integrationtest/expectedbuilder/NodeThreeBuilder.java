package integrationtest.generatedbuilder;

public class NodeThreeBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.NodeThree> {

    public static NodeThreeBuilder aNodeThree() {
        return new NodeThreeBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NodeThree target = new uk.co.buildergenerator.testmodel.NodeThree();
    
    public NodeThreeBuilder() {}
    
    public NodeThreeBuilder withNodeThreeString(java.lang.String nodeThreeString) {
        getTarget().setNodeThreeString(nodeThreeString);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.NodeThree getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.NodeThree build() {
        return getTarget();
    }
}
