package com.example.hciproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private EditText UserName, UserPassword;

    //private ImageView foto;

    public static String UTENTE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    private void init() {
        UserName = findViewById(R.id.UserName);
        UserPassword = findViewById(R.id.UserPassword);
        //foto = findViewById(R.id.imageView2);

        //foto.setImageBitmap(MainActivity.imageBitmap);
    }

    public void onLoginSubmit(View view) {

        Snackbar.make(view, "Ciao " + UTENTE, Snackbar.LENGTH_SHORT)
                .setAction("Chiudi", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();

        String name = UserName.getText().toString();
        String pw = UserPassword.getText().toString();

        UTENTE = UserName.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("EXTRA_NAME", name);
        intent.putExtra("EXTRA_PW", pw);
        startActivity(intent);
    }

    public void onRegistrationClick(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}