package integrationtest.generatedbuilder;

public class CyclicDependencyBeanRightBuilder {

    public static CyclicDependencyBeanRightBuilder aCyclicDependencyBeanRight() {
        return new CyclicDependencyBeanRightBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.CyclicDependencyBeanRight target = new uk.co.buildergenerator.testmodel.CyclicDependencyBeanRight();
    
    public CyclicDependencyBeanRightBuilder() {}
    
    public CyclicDependencyBeanRightBuilder withCyclicDependencyBeanLeft(integrationtest.generatedbuilder.CyclicDependencyBeanLeftBuilder cyclicDependencyBeanLeft) {
        target.setCyclicDependencyBeanLeft(cyclicDependencyBeanLeft.build());
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.CyclicDependencyBeanRight build() {
        return target;
    }
}
