package integrationtest.generatedbuilder;

public class SelfReferencingBeanBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.SelfReferencingBean> {

    public static SelfReferencingBeanBuilder aSelfReferencingBean() {
        return new SelfReferencingBeanBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.SelfReferencingBean target = new uk.co.buildergenerator.testmodel.SelfReferencingBean();
    
    public SelfReferencingBeanBuilder() {}
    
    public SelfReferencingBeanBuilder withSelfReferencingBean(integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.SelfReferencingBean> selfReferencingBean) {
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
