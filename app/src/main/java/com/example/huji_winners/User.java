package com.example.huji_winners;

import java.util.Collections;
import java.util.List;

public class User {

    private String name, email;

    private List<Event> createdEvents;

    private List<Event> attendedEvents;

    private String[] birthDate;

    private final String hobbies;

    private final String preferences;

    public User(String name, String[] birthDate, String hobbies, String preferences, String email){
        this.name = name;
        this.hobbies = hobbies;
        this.birthDate = birthDate;
        this.preferences = preferences;
        this.email = email;
    }

    public void setBirthDate(String[] newDate){
        this.birthDate = newDate;
    }

    public String[] getBirthDate(String[] newDate){
        return this.birthDate;
    }

    public void addNewEvent(Event newEvent){
        this.createdEvents.add(newEvent);
    }

    public List<Event> getMyEvents(){
        return this.createdEvents;
    }

    public List<Event> getAttendedEvents(){
        return this.attendedEvents;
    }

}
