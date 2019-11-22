package com.example.atris20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    Button register;
    Button signIn;
    TextView forgotPassword;
    EditText password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        signIn = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);
        forgotPassword = findViewById(R.id.forgotPassword);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();

                if (emailText.isEmpty()) {
                    email.setError("Nie podano emailu");
                    email.requestFocus();
                }

                else if (passwordText.isEmpty()) {
                    password.setError("Nie podano hasła");
                    password.requestFocus();
                }

                else if (passwordText.isEmpty() && emailText.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Pola są puste", Toast.LENGTH_SHORT);
                }
                else if (!passwordText.isEmpty() && !emailText.isEmpty()) {
                    firebaseAuth.createUserWithEmailAndPassword(emailText, passwordText);
                }
            }
        });

    }

}
