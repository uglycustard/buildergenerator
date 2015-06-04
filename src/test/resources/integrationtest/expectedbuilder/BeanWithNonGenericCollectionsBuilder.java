package integrationtest.generatedbuilder;

public class BeanWithNonGenericCollectionsBuilder {

    public static BeanWithNonGenericCollectionsBuilder aBeanWithNonGenericCollections() {
        return new BeanWithNonGenericCollectionsBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithNonGenericCollections target = new uk.co.buildergenerator.testmodel.BeanWithNonGenericCollections();
    
    public BeanWithNonGenericCollectionsBuilder() {}
    
    public BeanWithNonGenericCollectionsBuilder withStuff(java.lang.Object stuff) {
        if (getTarget().getStuff() == null) {
            getTarget().setStuff(new java.util.HashSet<java.lang.Object>());
        }        
        getTarget().getStuff().add(stuff);
        return this;
    }
    
    public BeanWithNonGenericCollectionsBuilder withBean(java.lang.Object bean) {
        getTarget().addBean(bean);
        return this;
    }
    
    public BeanWithNonGenericCollectionsBuilder withThing(java.lang.Object thing) {
        if (getTarget().getThings() == null) {
            getTarget().setThings(new java.util.ArrayList<java.lang.Object>());
        }        
        getTarget().getThings().add(thing);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BeanWithNonGenericCollections getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithNonGenericCollections build() {
        return getTarget();
    }
}
