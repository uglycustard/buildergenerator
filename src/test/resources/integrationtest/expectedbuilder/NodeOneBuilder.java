package integrationtest.generatedbuilder;

public class NodeOneBuilder {

    public static NodeOneBuilder aNodeOne() {
        return new NodeOneBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.NodeOne target = new uk.co.buildergenerator.testmodel.NodeOne();
    
    private NodeOneBuilder() {}
    
    public NodeOneBuilder withNodeOneString(java.lang.String nodeOneString) {
        target.setNodeOneString(nodeOneString);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.NodeOne build() {
        return target;
    }
}
