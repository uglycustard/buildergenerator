package integrationtest.generatedbuilder;

public class CyclicDependencyBeanLeftBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.CyclicDependencyBeanLeft> {

    public static CyclicDependencyBeanLeftBuilder aCyclicDependencyBeanLeft() {
        return new CyclicDependencyBeanLeftBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.CyclicDependencyBeanLeft target = new uk.co.buildergenerator.testmodel.CyclicDependencyBeanLeft();
    
    public CyclicDependencyBeanLeftBuilder() {}
    
    public CyclicDependencyBeanLeftBuilder withCyclicDependencyBeanRight(integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.CyclicDependencyBeanRight> cyclicDependencyBeanRight) {
        getTarget().setCyclicDependencyBeanRight(cyclicDependencyBeanRight.build());
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.CyclicDependencyBeanLeft getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.CyclicDependencyBeanLeft build() {
        return getTarget();
    }
}
