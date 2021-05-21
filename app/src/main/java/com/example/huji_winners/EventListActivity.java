//package com.example.huji_winners;
//
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class EventListActivity extends AppCompatActivity {
//
//	ArrayList<Event> events;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// ...
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_event_choice);
//
//		// Lookup the recyclerview in activity layout
//		RecyclerView rvContacts = (RecyclerView) findViewById(R.id.my_list);
//
//		// Initialize contacts
//		events = Event.createEventsList();
//		// Create adapter passing in the sample user data
//		EventAdapter adapter = new EventAdapter(events);
//		// Attach the adapter to the recyclerview to populate items
//		rvContacts.setAdapter(adapter);
//		// Set layout manager to position the items
//		rvContacts.setLayoutManager(new LinearLayoutManager(this));
//		// That's all!
//	}
//
//}
