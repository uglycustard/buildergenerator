package integrationtest.generatedbuilder;

public class BeanWithJodaTimeBuilder {

    public static BeanWithJodaTimeBuilder aBeanWithJodaTime() {
        return new BeanWithJodaTimeBuilder();
    }
    
    private uk.co.buildergenerator.testmodel.BeanWithJodaTime target = new uk.co.buildergenerator.testmodel.BeanWithJodaTime();
    
    public BeanWithJodaTimeBuilder() {}
    
    public BeanWithJodaTimeBuilder withDateTime(org.joda.time.DateTime dateTime) {
        target.setDateTime(dateTime);
        return this;
    }
    
    public BeanWithJodaTimeBuilder withLocalTime(org.joda.time.LocalTime localTime) {
        target.setLocalTime(localTime);
        return this;
    }
    
    public BeanWithJodaTimeBuilder withLocalDate(org.joda.time.LocalDate localDate) {
        target.setLocalDate(localDate);
        return this;
    }
    
    public uk.co.buildergenerator.testmodel.BeanWithJodaTime build() {
        return target;
    }
}
