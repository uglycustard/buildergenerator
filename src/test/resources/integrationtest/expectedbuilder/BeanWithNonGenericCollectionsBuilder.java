package integrationtest.generatedbuilder;

public class BeanWithNonGenericCollectionsBuilder {

    public static BeanWithNonGenericCollectionsBuilder aBeanWithNonGenericCollections() {
        return new BeanWithNonGenericCollectionsBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithNonGenericCollections target = new uk.co.buildergenerator.testmodel.BeanWithNonGenericCollections();
    
    public BeanWithNonGenericCollectionsBuilder() {}
    
    public BeanWithNonGenericCollectionsBuilder withStuff(java.lang.Object stuff) {
        if (target.getStuff() == null) {
            target.setStuff(new java.util.HashSet<java.lang.Object>());
        }        
        target.getStuff().add(stuff);
        return this;
    }
    
    public BeanWithNonGenericCollectionsBuilder withBean(java.lang.Object bean) {
        target.addBean(bean);
        return this;
    }
    
    public BeanWithNonGenericCollectionsBuilder withThing(java.lang.Object thing) {
        if (target.getThings() == null) {
            target.setThings(new java.util.ArrayList<java.lang.Object>());
        }        
        target.getThings().add(thing);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithNonGenericCollections build() {
        return target;
    }
}
