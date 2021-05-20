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

public class signUpActivity extends AppCompatActivity {

    EditText fullName, email, password, dateBirth, diet, describe;
    Button registerButton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullName = findViewById(R.id.fullNameText);
        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        dateBirth = findViewById(R.id.dateText);
        diet = findViewById(R.id.dietText);
        describe = findViewById(R.id.describeText);

        registerButton = findViewById(R.id.registerBtn);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

//        if (fAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }

//        public void logout(View view) {
//            FirebaseAuth.getInstance().signOut();
//            startActivity(new Intent(getApplicationContext(), loginActivity.class));
//            finish();
//        }

        registerButton.setOnClickListener(v -> {
            String emailString = email.getText().toString().trim();
            String passwordString = password.getText().toString().trim();
            // TODO: Check mail ending
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
            // TODO: Handle all data!!
            progressBar.setVisibility(View.VISIBLE);
            fAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(signUpActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        // change to desired page
                    } else {
                        Toast.makeText(signUpActivity.this, "User creation failed. "
                                + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}