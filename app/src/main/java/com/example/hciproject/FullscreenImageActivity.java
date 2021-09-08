package com.example.hciproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FullscreenImageActivity extends AppCompatActivity {

    public static ImageView fullscreenImage;
    public static ImageButton closeButton;
    public static int fullscreenImageResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);

        fullscreenImage = findViewById(R.id.fullscreenImage);
        closeButton = findViewById(R.id.closeButton);

        if (fullscreenImageResource!=0) {
            fullscreenImage.setImageResource(fullscreenImageResource);
        }
        else {
            fullscreenImage.setImageURI(HomeFragment.selectedImageUri);
        }
    }

    public void close(View view) {
        finish();
    }
}
