package uk.co.buildergenerator.testmodel;

public class CyclicDependencyBeanRight {
    
    private CyclicDependencyBeanLeft cyclicDependencyBeanLeft;

    public CyclicDependencyBeanLeft getCyclicDependencyBeanLeft() {
        return cyclicDependencyBeanLeft;
    }

    public void setCyclicDependencyBeanLeft(CyclicDependencyBeanLeft cyclicDependencyBeanLeft) {
        this.cyclicDependencyBeanLeft = cyclicDependencyBeanLeft;
    }

}
