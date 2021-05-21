package com.example.huji_winners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class eventChoice extends AppCompatActivity {

	FirebaseAuth fAuth;
	FirebaseFirestore fStore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// ...
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_choice);

		// Lookup the recyclerview in activity layout
		RecyclerView rvContacts = (RecyclerView) findViewById(R.id.my_list);

		fAuth = FirebaseAuth.getInstance();
		fStore = FirebaseFirestore.getInstance();
		ArrayList<Event> events = new ArrayList<Event>();

		fStore.collection("events").get().addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				for (QueryDocumentSnapshot document : task.getResult()) {
					Event e = new Event(document.getData(), document.getId());
					events.add(e);
					System.out.println(e);
					System.out.println(e.getName());
				}
				EventAdapter adapter = new EventAdapter(eventChoice.this, events);
				// Attach the adapter to the recyclerview to populate items
				rvContacts.setAdapter(adapter);
				// Set layout manager to position the items
				rvContacts.setLayoutManager(new LinearLayoutManager(this));
			}
		});
	}
}