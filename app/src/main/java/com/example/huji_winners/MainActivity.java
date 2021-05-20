package com.example.huji_winners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button lgnBtn = findViewById(R.id.lgnButton);
        lgnBtn.setOnClickListener(v -> openLogAct());
    }

    public void openLogAct() {
        Intent intent = new Intent(this, signUpActivity.class);
        startActivity(intent);
    }
}