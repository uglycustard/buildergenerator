package integrationtest.generatedbuilder;

public class NodeOneBuilder {

    public static NodeOneBuilder aNodeOne() {
        return new NodeOneBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NodeOne target = new uk.co.buildergenerator.testmodel.NodeOne();
    
    public NodeOneBuilder() {}
    
    public NodeOneBuilder withNodeOneString(java.lang.String nodeOneString) {
        getTarget().setNodeOneString(nodeOneString);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.NodeOne getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.NodeOne build() {
        return getTarget();
    }
}
