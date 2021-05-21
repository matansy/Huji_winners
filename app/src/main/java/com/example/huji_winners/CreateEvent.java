package com.example.huji_winners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateEvent extends AppCompatActivity {

    int eventID;
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
        progressBar = findViewById(R.id.progressBar2);

        addButton.setOnClickListener(v -> {
            String titleString = title.getText().toString().trim();
            String timeString = time.getText().toString().trim();
            String dateString = date.getText().toString().trim();
            String locationString = location.getText().toString().trim();
            String maxPractString = maxPract.getText().toString().trim();
            String briefString = brief.getText().toString().trim();
            progressBar.setVisibility(View.VISIBLE);

            String userID = fAuth.getCurrentUser().getUid();
            DocumentReference docRef = fStore.collection("events").document(userID);
            Map<String, Object> event = new HashMap<>();
            event.put("title", titleString);
            event.put("time", timeString);
            event.put("date", dateString);
            event.put("location", locationString);
            event.put("maxPract", maxPractString);
            event.put("brief", briefString);
            event.put("members", userID.concat(","));
            Toast.makeText(CreateEvent.this, "Event Added.", Toast.LENGTH_SHORT).show();
            docRef.set(event).addOnSuccessListener(unused -> Log.d("TAG", "onSuccess: user profile created for " + userID));
            startActivity(new Intent(getApplicationContext(), Good_middle_page.class));
        });
    }
}