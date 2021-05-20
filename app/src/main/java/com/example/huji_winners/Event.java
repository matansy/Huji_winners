package com.example.huji_winners;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Event {

	private String eventName;

	private String time;
	private String date;
	private String place;

	String maxPract;

	private final String host;

	private List<User> attended;

	private String description;

	public Event(String eventName, String date, String time, String host, String description, String place){
		this.eventName = eventName;
		this.time = time;
		this.host = host;
//		this.attended.add(host);
		this.description = description;
		this.place = place;
		this.date = date;
	}

	public Event(Map<String, Object> event, String userID) {
		this.eventName = (String) event.get("title");
		this.time = (String) event.get("time");
		this.host = userID;
		this.description = (String) event.get("brief");
		this.place = (String) event.get("location");
		this.date = (String) event.get("date");
		this.maxPract = (String) event.get("maxPract");
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

	public String getDate(){
		return this.date;
	}

	public String getName(){
		return this.eventName;
	}

	public String getHost(){
		return this.host;
	}

	public String getDescription(){
		return this.description;
	}

//	public static ArrayList<Event> createEventsList(){
//		ArayList<Event> lst = new ArrayList<Event>();
//		String[] s = {"12","09","1995"};
//		User user = new User("Or", s, "Nothing", "Vegi", "orrejwan");
//		Event e1 = new Event("Picnic", "12/05/21", "10:30", user, "Fun", "Jerusalem");
//		Event e2 = new Event("Lunch", "20/05/21", "12:30", user, "Fun", "Jerusalem");
//		Event e3 = new Event("a", "20/05/21", "12:30", user, "Fun", "Jerusalem");
//		Event e4 = new Event("s", "20/05/21", "12:30", user, "Fun", "Jerusalem");
//		Event e5 = new Event("d", "20/05/21", "12:30", user, "Fun", "Jerusalem");
//		Event e6 = new Event("f", "20/05/21", "12:30", user, "Fun", "Jerusalem");
//		Event e7 = new Event("g", "20/05/21", "12:30", user, "Fun", "Jerusalem");
//		Event e8 = new Event("h", "20/05/21", "12:30", user, "Fun", "Jerusalem");
//		Event e9 = new Event("i", "20/05/21", "12:30", user, "Fun", "Jerusalem");
//		Event e10 = new Event("j", "20/05/21", "12:30", user, "Fun", "Jerusalem");
//		Event e11 = new Event("k", "20/05/21", "12:30", user, "Fun", "Jerusalem");
//		Event e12 = new Event("l", "20/05/21", "12:30", user, "Fun", "Jerusalem");
//		Event e13 = new Event("m", "20/05/21", "12:30", user, "Fun", "Jerusalem");
//		lst.add(e1);
//		lst.add(e2);
//		lst.add(e3);
//		lst.add(e4);
//		lst.add(e5);
//		lst.add(e6);
//		lst.add(e7);
//		lst.add(e8);
//		lst.add(e9);
//		lst.add(e10);
//		lst.add(e11);
//		lst.add(e12);
//		lst.add(e13);
//		return lst;
//	}
}
