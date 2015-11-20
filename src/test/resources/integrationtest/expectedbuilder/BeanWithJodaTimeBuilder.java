package integrationtest.generatedbuilder;

public class BeanWithJodaTimeBuilder implements integrationtest.generatedbuilder.Builder<uk.co.buildergenerator.testmodel.BeanWithJodaTime> {

    public static BeanWithJodaTimeBuilder aBeanWithJodaTime() {
        return new BeanWithJodaTimeBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithJodaTime target = new uk.co.buildergenerator.testmodel.BeanWithJodaTime();
    
    public BeanWithJodaTimeBuilder() {}
    
    public BeanWithJodaTimeBuilder withDateTime(org.joda.time.DateTime dateTime) {
        getTarget().setDateTime(dateTime);
        return this;
    }
    
    public BeanWithJodaTimeBuilder withLocalTime(org.joda.time.LocalTime localTime) {
        getTarget().setLocalTime(localTime);
        return this;
    }
    
    public BeanWithJodaTimeBuilder withLocalDate(org.joda.time.LocalDate localDate) {
        getTarget().setLocalDate(localDate);
        return this;
    }
    
    protected uk.co.buildergenerator.testmodel.BeanWithJodaTime getTarget() {
        return target;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithJodaTime build() {
        return getTarget();
    }
}
