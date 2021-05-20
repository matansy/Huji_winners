package com.example.huji_winners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class loginActivity extends AppCompatActivity {

    EditText email, password;
    Button loginButton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailTextLogin);
        password = findViewById(R.id.passwordTextLogin);
        loginButton = findViewById(R.id.loginBtn);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        loginButton.setOnClickListener(v -> {
            String emailString = email.getText().toString().trim();
            String passwordString = password.getText().toString().trim();

            if (TextUtils.isEmpty(emailString)) {
                email.setError("Invalid Email.");
                progressBar.setVisibility(View.INVISIBLE);
                return;
            }
            if (TextUtils.isEmpty(passwordString)) {
                password.setError("Invalid Password.");
                progressBar.setVisibility(View.INVISIBLE);
                return;
            }
            if (passwordString.length() < 6) {
                password.setError("Password must be atleast 6 chars long.");
                progressBar.setVisibility(View.INVISIBLE);
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            fAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(loginActivity.this, "Logged in successfully.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        // change to desired page
                    } else {
                        Toast.makeText(loginActivity.this, "Login failed. "
                                + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            });
        });
    }
}