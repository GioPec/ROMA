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
        recuperaPasswordCard = findViewById(R.id.recuperaPasswordCard);
        recuperaPasswordDark = findViewById(R.id.recuperaPasswordDark);
        RB = findViewById(R.id.RegistrationButton);
        LB = findViewById(R.id.LoginButton);
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

        if ((name.equals(RegistrationActivity.USER_NAME) && !(pw.equals(RegistrationActivity.USER_PASSWORD)))) {

            Snackbar.make(view, "Password errata!", Snackbar.LENGTH_SHORT)
                    .setAction("Chiudi", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }
        else  {
            UTENTE = UserName.getText().toString();

            Intent intent = new Intent(this, InfoActivity.class);
            //intent.putExtra("EXTRA_NAME", name);
            //intent.putExtra("EXTRA_PW", pw);
            startActivity(intent);
        }
    }

    public void onLoginSubmitGoogleFacebook(View view) {
        UTENTE = "Giovanni"; //TODO: da decidere

        Intent intent = new Intent(this, MainActivity.class);
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

    }

    public void chiudiRecuperaPassword(View view) {
        recuperaPasswordCard.setVisibility(View.INVISIBLE);
        recuperaPasswordDark.setVisibility(View.INVISIBLE);
        RB.setEnabled(true);
        LB.setEnabled(true);
    }
}