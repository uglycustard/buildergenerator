package integrationtest.generatedbuilder;

public class CyclicDependencyBeanLeftBuilder {

    public static CyclicDependencyBeanLeftBuilder aCyclicDependencyBeanLeft() {
        return new CyclicDependencyBeanLeftBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.CyclicDependencyBeanLeft target = new uk.co.buildergenerator.testmodel.CyclicDependencyBeanLeft();
    
    private CyclicDependencyBeanLeftBuilder() {}
    
    public CyclicDependencyBeanLeftBuilder withCyclicDependencyBeanRight(integrationtest.generatedbuilder.CyclicDependencyBeanRightBuilder cyclicDependencyBeanRight) {
        target.setCyclicDependencyBeanRight(cyclicDependencyBeanRight.build());
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.CyclicDependencyBeanLeft build() {
        return target;
    }
}
