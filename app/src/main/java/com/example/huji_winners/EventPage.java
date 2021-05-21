package com.example.huji_winners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EventPage extends AppCompatActivity {

    TextView name, dateTime, time, location, spaceLeft, brief;
    int availableSlots;
    Button joinBtn;
    TextView attend;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        joinBtn = findViewById(R.id.JoinButton);
        attend = findViewById(R.id.attend);

        Event e = (Event) getIntent().getSerializableExtra("event");
        name = findViewById(R.id.EventName);
        dateTime = findViewById(R.id.EventDate);
        location = findViewById(R.id.EventLocation);
        spaceLeft = findViewById(R.id.EventPlaces);
        brief = findViewById(R.id.EventDescription);
        name.setText(e.getName());
        dateTime.setText("Date: " + e.getDate() + "   |   Time: " + e.getTime());
        location.setText("Location: " + e.getLocation());
        availableSlots = Integer.parseInt(e.maxPract) - e.getMembersList().length;
        spaceLeft.setText("Available spots: " + String.valueOf(availableSlots));
        brief.setText("Event Brief:\n\n" + e.getDescription());

        for (String member : e.getMembersList()) {
            System.out.println(member);
            if (member.equals(fAuth.getCurrentUser().getUid())) {
                joinBtn.setVisibility(View.INVISIBLE);
                attend.setVisibility(View.VISIBLE);
                attend.setText("You are attending this event!");
                break;
            }
        }

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (availableSlots == 0) {
                    Toast.makeText(EventPage.this, "Event Full.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EventPage.this, "Joined event successfully!", Toast.LENGTH_SHORT).show();
                    String userID = e.getHost();
                    DocumentReference docRef = fStore.collection("events").document(userID);
                    Map<String, Object> event = new HashMap<>();
                    event.put("title", e.getName());
                    event.put("time", e.getTime());
                    event.put("date", e.getDate());
                    event.put("location", e.getLocation());
                    event.put("maxPract", e.maxPract);
                    event.put("brief", e.getDescription());
                    event.put("members", e.getMembers().concat(fAuth.getCurrentUser().getUid()));
                    System.out.println("ADDED MEMBER: " + e.getMembers().concat(fAuth.getCurrentUser().getUid()));
                    Toast.makeText(EventPage.this, "Event Added.", Toast.LENGTH_SHORT).show();
                    docRef.set(event).addOnSuccessListener(unused -> Log.d("TAG", "onSuccess: user profile created for " + userID));
                    startActivity(new Intent(getApplicationContext(), Good_middle_page.class));
                }
            }
        });



    }
}