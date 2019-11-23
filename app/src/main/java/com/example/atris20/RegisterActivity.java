package com.example.atris20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText email;
    EditText name;
    EditText surname;
    EditText dateOfBirth;
    Button registerButton;
    Button backToLogin;
    EditText password;
    EditText confirmPassword;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.registerEmail);
        name = findViewById(R.id.registerName);
        surname = findViewById(R.id.registerSurname);
        confirmPassword = findViewById(R.id.registerConfirmPassword);
        dateOfBirth = findViewById(R.id.registerDateOfBirth);
        password = findViewById(R.id.registerPassword);
        registerButton = findViewById(R.id.registerUser);
        backToLogin = findViewById(R.id.registerBackToLoginButton);

//        backToLogin.setOnClickListener((v)=> {
//                Intent i = new (RegisterActivity.this, LoginActivity.class);
//        startActivity(i);
//        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();

                if (emailText.isEmpty()) {
                    email.setError("Nie podano emailu");
                    email.requestFocus();
                } else if (passwordText.isEmpty()) {
                    password.setError("Nie podano hasła");
                    password.requestFocus();
                } else if (passwordText != confirmPassword.getText().toString()) {
                    Toast.makeText(RegisterActivity.this, "Hasła nie są jednakowe", Toast.LENGTH_SHORT);
                } else if (passwordText.isEmpty() && emailText.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Pola są puste", Toast.LENGTH_SHORT);
                } else if (!passwordText.isEmpty() && !emailText.isEmpty() && passwordText == confirmPassword.getText().toString()) {
                    firebaseAuth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Rejestracja nie powiodła się, spróbuj ponownie", Toast.LENGTH_SHORT).show();
                            }
                            else  {
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                        }
                    });
                }
                 else {
                     Toast.makeText(RegisterActivity.this, "Coś poszło nie tak", Toast.LENGTH_SHORT).show();
                }
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}