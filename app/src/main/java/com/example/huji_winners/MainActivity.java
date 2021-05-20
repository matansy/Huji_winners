package com.example.huji_winners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signUpBtn = findViewById(R.id.signUpButton);
        Button loginBtn = findViewById(R.id.loginButton);
        Button logoutBtn = findViewById(R.id.logutButton);
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            signUpBtn.setVisibility(View.INVISIBLE);
            loginBtn.setVisibility(View.INVISIBLE);
            logoutBtn.setVisibility(View.VISIBLE);
        } else {
            signUpBtn.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.VISIBLE);
            logoutBtn.setVisibility(View.INVISIBLE);
        }


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
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}