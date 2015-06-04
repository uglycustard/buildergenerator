package integrationtest.generatedbuilder;

public class CyclicDependencyBeanRightBuilder {

    public static CyclicDependencyBeanRightBuilder aCyclicDependencyBeanRight() {
        return new CyclicDependencyBeanRightBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.CyclicDependencyBeanRight target = new uk.co.buildergenerator.testmodel.CyclicDependencyBeanRight();
    
    public CyclicDependencyBeanRightBuilder() {}
    
    public CyclicDependencyBeanRightBuilder withCyclicDependencyBeanLeft(integrationtest.generatedbuilder.CyclicDependencyBeanLeftBuilder cyclicDependencyBeanLeft) {
        getTarget().setCyclicDependencyBeanLeft(cyclicDependencyBeanLeft.build());
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.CyclicDependencyBeanRight getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.CyclicDependencyBeanRight build() {
        return getTarget();
    }
}
