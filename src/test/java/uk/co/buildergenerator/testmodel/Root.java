package uk.co.buildergenerator.testmodel;

import uk.co.buildergenerator.BuilderGenerator;

public class Root {

    private String rootString;
    private NodeOne nodeOne;
    private NodeTwo nodeTwo;
    
    public String getRootString() {
        return rootString;
    }

    public void setRootString(String rootString) {
        this.rootString = rootString;
    }

    public NodeOne getNodeOne() {
        return nodeOne;
    }

    public void setNodeOne(NodeOne nodeOne) {
        this.nodeOne = nodeOne;
    }

    public NodeTwo getNodeTwo() {
        return nodeTwo;
    }

    public void setNodeTwo(NodeTwo nodeTwo) {
        this.nodeTwo = nodeTwo;
    }
    
    public static void main(String[] args) {
        BuilderGenerator bg = new BuilderGenerator(Root.class);
        bg.setOutputDirectory("src/test/java");
        bg.setBuilderPackage("uk.co.buildergenerator.roottest");
        bg.setGenerationGap(true);
        bg.generateBuilders();
        
        
//        public static void main(String[] args) {
//            Root root = aRoot().withRootString("rootString")
//                               .withNodeOne(aNodeOne().withNodeOneString("nodeOneString"))
//                               .withNodeTwo(aNodeTwo().withNodeTwoString("nodeTwoString")
//                                                      .withNodeThree(aNodeThree().withNodeThreeString("nodeThreeString"))).build();
//            
//            assertEquals("rootString", root.getRootString());
//            assertEquals("nodeOneString", root.getNodeOne().getNodeOneString());
//            
//        }
        
    }

}
