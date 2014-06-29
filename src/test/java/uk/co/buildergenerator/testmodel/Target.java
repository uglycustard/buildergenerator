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
    
    public void setDay(String day) {
        this.day = day;
    }

    public boolean isHungry() {
        return hungry;
    }
    
    public void setHungry(boolean hungry) {
        this.hungry = hungry;
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
    
    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
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
    
    public void setAddress(Address address) {
        this.address = address;
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

    public void setHouse(House house) {
        this.house = house;
    }
    
}
