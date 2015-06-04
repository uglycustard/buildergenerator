package integrationtest.generatedbuilder;

public class SelfReferencingBeanBuilder {

    public static SelfReferencingBeanBuilder aSelfReferencingBean() {
        return new SelfReferencingBeanBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.SelfReferencingBean target = new uk.co.buildergenerator.testmodel.SelfReferencingBean();
    
    public SelfReferencingBeanBuilder() {}
    
    public SelfReferencingBeanBuilder withSelfReferencingBean(integrationtest.generatedbuilder.SelfReferencingBeanBuilder selfReferencingBean) {
        getTarget().setSelfReferencingBean(selfReferencingBean.build());
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.SelfReferencingBean getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.SelfReferencingBean build() {
        return getTarget();
    }
}
