package com.example.huji_winners;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EventPage extends AppCompatActivity {

    TextView name, dateTime, time, location, spaceLeft, brief;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        Event e = (Event) getIntent().getSerializableExtra("event");
        name = findViewById(R.id.EventName);
        dateTime = findViewById(R.id.EventDate);
        location = findViewById(R.id.EventLocation);
        spaceLeft = findViewById(R.id.EventPlaces);
        brief = findViewById(R.id.EventDescription);
        name.setText(e.getName());
        dateTime.setText("Date: " + e.getDate() + "   |   Time: " + e.getTime());
        location.setText("Location: " + e.getLocation());
        spaceLeft.setText("Available spots: " + String.valueOf(Integer.parseInt(e.maxPract) - e.getMembers().length));
        brief.setText("Event Brief:\n\n" + e.getDescription());

    }
}