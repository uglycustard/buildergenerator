package uk.co.buildergenerator.testmodel;

public abstract class CustomerCarBuilder<T extends CustomerCarBuilder<T>> {
    
    protected abstract AbstractCar getTarget();
    
    public T withAccessory(String name, String value) {
        getTarget().addAccessory(name, value);
        return (T) this;
    }

}
