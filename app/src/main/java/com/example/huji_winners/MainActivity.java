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
        Button signUpBtn = findViewById(R.id.signUpButton);
        Button loginBtn = findViewById(R.id.loginButton);
        signUpBtn.setOnClickListener(v -> openSignUpAct());
        loginBtn.setOnClickListener(v -> openLoginAct());
    }

    public void openSignUpAct() {
        Intent intent = new Intent(this, signUpActivity.class);
        startActivity(intent);
    }
    public void openLoginAct() {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }
}