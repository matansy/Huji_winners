package com.example.huji_winners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huji_winners.ui.login.LoginActivity;


public class MainActivity extends AppCompatActivity {

    Button lgnBtn = findViewById(R.id.lgnButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogAct();
            }
        });
    }

    public void openLogAct() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}