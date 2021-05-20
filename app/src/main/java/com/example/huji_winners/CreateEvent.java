package com.example.huji_winners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateEvent extends AppCompatActivity {

    static int eventID = 0;
    EditText title, time, date, location, maxPract, brief;
    Button addButton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        title = findViewById(R.id.GetEventName);
        time = findViewById(R.id.GetEventTime);
        date = findViewById(R.id.GetEventDate);
        location = findViewById(R.id.GetEventLocation);
        maxPract = findViewById(R.id.GetMaxMembers);
        brief = findViewById(R.id.GetBrief);

        fAuth = FirebaseAuth.getInstance();
        addButton = findViewById(R.id.SubmitButton);
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        addButton.setOnClickListener(v -> {
            String titleString = title.getText().toString().trim();
            String timeString = time.getText().toString().trim();
            String dateString = date.getText().toString().trim();
            String locationString = location.getText().toString().trim();
            String maxPractString = maxPract.getText().toString().trim();
            String briefString = brief.getText().toString().trim();
            progressBar.setVisibility(View.VISIBLE);

            eventID++;
            String userID = fAuth.getCurrentUser().getUid();
            DocumentReference docRef = fStore.collection("events").document(String.valueOf(eventID));
            Map<String, Object> user = new HashMap<>();
            user.put("title", titleString);
            user.put("time", timeString);
            user.put("date", dateString);
            user.put("location", locationString);
            user.put("maxPract", maxPractString);
            user.put("brief", briefString);
            Toast.makeText(CreateEvent.this, "Event Added.", Toast.LENGTH_SHORT).show();
            docRef.set(user).addOnSuccessListener(unused -> Log.d("TAG", "onSuccess: user profile created for " + userID));
            startActivity(new Intent(getApplicationContext(), eventChoice.class));
        });
    }
}