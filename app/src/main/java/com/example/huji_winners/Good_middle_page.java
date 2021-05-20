package com.example.huji_winners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class Good_middle_page extends AppCompatActivity {

	Button logOutBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_good_middle_page);

		logOutBtn = findViewById(R.id.logoutBtn);
		logOutBtn.setOnClickListener(v -> {
			FirebaseAuth.getInstance().signOut();
			startActivity(new Intent(getApplicationContext(), MainActivity.class));
			finish();
		});
		Button fab = findViewById(R.id.event_button);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(getApplicationContext(), eventChoice.class)); // todo: to Idan's page
				// todo: to take from the cloud!
			}
		});
		Button fab1 = findViewById(R.id.new_event_button);
		fab1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(getApplicationContext(), CreateEvent.class)); // todo: to Idan's page
				// todo: to take from the cloud!
			}
		});

	}
}