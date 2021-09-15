package com.example.hciproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void goHome(View view) {
        HomeFragment.HOME_POPUP_OPEN = false;
        HomeFragment.HOME_LATLNG = new LatLng(41.9, 12.5);
        HomeFragment.HOME_ZOOM = 11;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goBack(View view) { finish(); }
}
