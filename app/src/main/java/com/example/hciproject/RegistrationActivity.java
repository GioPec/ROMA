package com.example.hciproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class RegistrationActivity extends AppCompatActivity {

    private EditText UserEmail, UserName, UserPassword;

    public static String USER_EMAIL;
    public static String USER_NAME;
    public static String USER_PASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    private void init() {
        UserEmail = findViewById(R.id.UserEmail);
        UserName = findViewById(R.id.UserName);
        UserPassword = findViewById(R.id.UserPassword);

        USER_EMAIL = UserEmail.getText().toString();
        USER_NAME = UserName.getText().toString();
        USER_PASSWORD = UserPassword.getText().toString();
    }

    public void onRegistrationSubmit(View view) {

        if(false) return;   //TODO: validation o controllo dati
        Snackbar.make(view, "Confermi i dati scelti?", Snackbar.LENGTH_LONG)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();


        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}