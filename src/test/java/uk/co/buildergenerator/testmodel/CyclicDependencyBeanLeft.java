package uk.co.buildergenerator.testmodel;

public class CyclicDependencyBeanLeft {

    private CyclicDependencyBeanRight cyclicDependencyBeanRight;

    public CyclicDependencyBeanRight getCyclicDependencyBeanRight() {
        return cyclicDependencyBeanRight;
    }

    public void setCyclicDependencyBeanRight(
            CyclicDependencyBeanRight cyclicDependencyBeanRight) {
        this.cyclicDependencyBeanRight = cyclicDependencyBeanRight;
    }
    
}
