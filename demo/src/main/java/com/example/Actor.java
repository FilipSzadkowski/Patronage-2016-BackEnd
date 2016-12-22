package com.example;

/**
 * Created by Filip on 2016-12-20.
 */
public class Actor {
    private final long id;

    private String FirstName;
    private String LastName;
    private int age;

    public Actor(long id, int age, String FirstName, String LastName) {
        this.id = id;
        this.age=age;
        this.FirstName=FirstName;
        this.LastName=LastName;
    }
    public long getId() {
        return id;
    }
    public int getAge() {
        return age;
    }
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void newAge(int newAge){this.age=newAge;}
    public void newFName(String newFName){this.FirstName=newFName;}
    public void newLName(String newLName){this.LastName=newLName;}
}
