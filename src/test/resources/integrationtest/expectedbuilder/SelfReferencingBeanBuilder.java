package integrationtest.generatedbuilder;

public class SelfReferencingBeanBuilder {

    public static SelfReferencingBeanBuilder aSelfReferencingBean() {
        return new SelfReferencingBeanBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.SelfReferencingBean target = new uk.co.buildergenerator.testmodel.SelfReferencingBean();
    
    private SelfReferencingBeanBuilder() {}
    
    public SelfReferencingBeanBuilder withSelfReferencingBean(integrationtest.generatedbuilder.SelfReferencingBeanBuilder selfReferencingBean) {
        target.setSelfReferencingBean(selfReferencingBean.build());
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.SelfReferencingBean build() {
        return target;
    }
}
