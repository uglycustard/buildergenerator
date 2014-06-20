package uk.co.buildergenerator.testmodel;

import java.util.ArrayList;
import java.util.List;

public class Target {

    private String day;
    private boolean hungry;
    private List<String> months = new ArrayList<String>();
    private Delegate delegate;
    private List<Person> persons = new ArrayList<Person>();
    private Address address;
    private List<Hostess> hostesses = new ArrayList<Hostess>();
    private House house;

    public String getDay() {
        return day;
    }
    
    public boolean isHungry() {
        return hungry;
    }
    
    public List<String> getMonths() {
        return months;
    }
    
    public void addMonth(String month) {
        months.add(month);
    }
    
    public Delegate getDelegate() {
        return delegate;
    }

    public List<Person> getPersons() {
        return persons;
    }
    
    public void addPerson(Person person) {
        persons.add(person);
    }
    
    public Address getAddress() {
        return address;
    }

    public List<Hostess> getHostesses() {
        return hostesses;
    }
    
    public void addHostess(Hostess hostess) {
        hostesses.add(hostess);
    }

    public House getHouse() {
        return house;
    }
    
}
