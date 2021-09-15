package com.example.hciproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class SettingsActivity extends AppCompatActivity {

    private MaterialCardView recuperaPasswordCard;
    private MaterialCardView recuperaPasswordDark;
    private Button LB;
    private Button DAB;
    private Button SIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        recuperaPasswordCard = findViewById(R.id.recuperaPasswordCard);
        recuperaPasswordDark = findViewById(R.id.recuperaPasswordDark);
        LB = findViewById(R.id.button5);
        DAB = findViewById(R.id.button6);
        SIB = findViewById(R.id.button7);
    }

    public void seiSicuro(View view) {
        recuperaPasswordCard.setVisibility(View.VISIBLE);
        recuperaPasswordDark.setVisibility(View.VISIBLE);
        LB.setEnabled(false);
        DAB.setEnabled(false);
        SIB.setEnabled(false);
    }

    public void seiSicuroAnnulla(View view) {
        recuperaPasswordCard.setVisibility(View.INVISIBLE);
        recuperaPasswordDark.setVisibility(View.INVISIBLE);
        LB.setEnabled(true);
        DAB.setEnabled(true);
        SIB.setEnabled(true);
    }

    //LOGOUT
    public void seiSicuroOk(View view) {
        ProfileFragment.ACCESS_WITH_GOOGLE = false;
        recuperaPasswordCard.setVisibility(View.INVISIBLE);
        recuperaPasswordDark.setVisibility(View.INVISIBLE);
        LB.setEnabled(true);
        DAB.setEnabled(true);
        SIB.setEnabled(true);

        if (HomeFragment.selectedImageUri != null) {
            ProfileFragment.cardHidden.setVisibility(View.GONE);
        }
        HomeFragment.selectedImageUri = null;
        HomeFragment.HOME_POPUP_OPEN = false;
        HomeFragment.popupTitolo = null;
        HomeFragment.popupCategoria = null;
        HomeFragment.popupDescrizione = null;
        HomeFragment.popupUsername = null;
        ProfileFragment.CARD_0_REMOVED = false;
        ProfileFragment.CARD_1_REMOVED = false;
        ProfileFragment.CARD_2_REMOVED = false;
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    public void showInfo(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    public void goBack(View view) { finish(); }
}
