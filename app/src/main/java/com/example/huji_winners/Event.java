package com.example.huji_winners;

import java.util.List;

public class Event {

	private String eventName;

	private String time;

	private final User host;

	private List<User> attended;

	private String description;

	public Event(String eventName, String time, User host, String description){
		this.eventName = eventName;
		this.time = time;
		this.host = host;
		this.attended.add(host);
		this.description = description;
	}

	public void addAttendant(User newAtt){
		this.attended.add(newAtt);
	}

	public List<User> getAttendants(){
		return this.attended;
	}

	public String getTime(){
		return this.time;
	}

	public User getHost(){
		return this.host;
	}

	public String getDescription(){
		return this.description;
	}
}
