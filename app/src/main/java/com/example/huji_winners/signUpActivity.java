package com.example.huji_winners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class signUpActivity extends AppCompatActivity {

    EditText fullName, email, password, dateBirth, diet, describe;
    RadioGroup radioGrp;
    RadioButton radioBtn;
    Button registerButton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID, genderString = "None";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        radioGrp = (RadioGroup) findViewById(R.id.radioGroup);
        fullName = findViewById(R.id.fullNameText);
        email = findViewById(R.id.emailTextLogin);
        password = findViewById(R.id.passwordTextLogin);
        dateBirth = findViewById(R.id.dateText);
        diet = findViewById(R.id.dietText);
        describe = findViewById(R.id.describeText);

        registerButton = findViewById(R.id.registerBtn);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);





        registerButton.setOnClickListener(v -> {
            String emailString = email.getText().toString().trim();
            String passwordString = password.getText().toString().trim();
            String fullNameString = fullName.getText().toString().trim();
            String dateString = dateBirth.getText().toString().trim();
            String dietString = diet.getText().toString().trim();
            String describeString = describe.getText().toString().trim();



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

            progressBar.setVisibility(View.VISIBLE);
            fAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(signUpActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                    userID = fAuth.getCurrentUser().getUid();
                    DocumentReference docRef = fStore.collection("users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("fName", fullNameString);
                    user.put("email", emailString);
                    user.put("dateBirth", dateString);
                    user.put("diet", dietString);
                    user.put("describe", describeString);
//                    user.put("sex", genderString);
                    docRef.set(user).addOnSuccessListener(unused -> Log.d("TAG", "onSuccess: user profile created for " + userID));
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(signUpActivity.this, "User creation failed. "
                            + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        });
//
//        radioGrp.setOnCheckedChangeListener((group, checkedId) -> {
//            switch(checkedId){
//                case R.id.maleBtn:
//                    genderString = "Male";
//                    break;
//                case R.id.femaleBtn:
//                    genderString = "Female";
//                    break;
//            }
//        });
    }
}