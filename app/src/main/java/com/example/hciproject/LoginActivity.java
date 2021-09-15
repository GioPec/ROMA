package com.example.hciproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private EditText UserName, UserPassword;
    private MaterialCardView recuperaPasswordCard;
    private MaterialCardView recuperaPasswordDark;
    private Button RB;
    private Button LB;
    private Button GB;
    private Button FB;

    public static String UTENTE;

    public static boolean COME_FROM_REGISTRATION_COMPLETE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    private void init() {
        UserName = findViewById(R.id.UserName);
        UserPassword = findViewById(R.id.UserPassword);
        recuperaPasswordCard = findViewById(R.id.recuperaPasswordCard);
        recuperaPasswordDark = findViewById(R.id.recuperaPasswordDark);
        RB = findViewById(R.id.RegistrationButton);
        LB = findViewById(R.id.loginButton);
        GB = findViewById(R.id.loginButton2);
        FB = findViewById(R.id.loginButton3);

        if (COME_FROM_REGISTRATION_COMPLETE) {
            COME_FROM_REGISTRATION_COMPLETE = false;
            Snackbar.make(findViewById(android.R.id.content), "Registrazione effettuata. Benvenuto!", Snackbar.LENGTH_LONG)
                    .setAction("Chiudi", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }
    }

    public void onLoginSubmit(View view) {

        /*
        Snackbar.make(view, "Ciao " + UTENTE, Snackbar.LENGTH_SHORT)
                .setAction("Chiudi", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
        */

        String name = UserName.getText().toString();
        String pw = UserPassword.getText().toString();
        ProfileFragment.ACCESS_WITH_GOOGLE = false;

        if (((name.equals(RegistrationActivity.USER_NAME) || name.equals(RegistrationActivity.USER_EMAIL)) && !(pw.equals(RegistrationActivity.USER_PASSWORD)))) {

            Snackbar.make(view, "Password errata!", Snackbar.LENGTH_SHORT)
                    .setAction("Chiudi", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }
        else  {
            if (RegistrationActivity.USER_NAME!=null) {
                UTENTE = RegistrationActivity.USER_NAME;
            }
            else {
                UTENTE = UserName.getText().toString();
            }

            Intent intent = new Intent(this, InfoActivity.class);
            //intent.putExtra("EXTRA_NAME", name);
            //intent.putExtra("EXTRA_PW", pw);
            startActivity(intent);
        }
    }

    public void onLoginSubmitGoogleFacebook(View view) {
        UTENTE = "Giovanni";
        ProfileFragment.ACCESS_WITH_GOOGLE = true;

        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    public void onRegistrationClick(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void passwordDimenticata(View view) {
        recuperaPasswordCard.setVisibility(View.VISIBLE);
        recuperaPasswordDark.setVisibility(View.VISIBLE);
        RB.setEnabled(false);
        LB.setEnabled(false);
        GB.setEnabled(false);
        FB.setEnabled(false);
    }

    public void chiudiRecuperaPassword(View view) {
        recuperaPasswordCard.setVisibility(View.INVISIBLE);
        recuperaPasswordDark.setVisibility(View.INVISIBLE);
        RB.setEnabled(true);
        LB.setEnabled(true);
        GB.setEnabled(true);
        FB.setEnabled(true);
    }
}