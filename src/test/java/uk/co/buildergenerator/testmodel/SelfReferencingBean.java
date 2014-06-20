package uk.co.buildergenerator.testmodel;

public class SelfReferencingBean {

    private SelfReferencingBean selfReferencingBean;

    public SelfReferencingBean getSelfReferencingBean() {
        return selfReferencingBean;
    }

    public void setSelfReferencingBean(SelfReferencingBean selfReferencingBean) {
        this.selfReferencingBean = selfReferencingBean;
    }
    
}
