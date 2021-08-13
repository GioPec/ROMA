package com.example.hciproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    public EditText UserEmail, UserName, UserPassword;
    public static Button RegistrationImageButton;

    public static final int PICK_IMAGE = 2;
    public static Uri profileImage;
    public static ImageView imageView3;

    public static String USER_EMAIL;
    public static String USER_NAME;
    public static String USER_PASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        UserEmail = findViewById(R.id.UserEmail);
        UserName = findViewById(R.id.UserName);
        UserPassword = findViewById(R.id.UserPassword);
        RegistrationImageButton = findViewById(R.id.RegistrationImageButton);
        imageView3 = findViewById(R.id.imageView3);

        ///////////////////// Immagine profilo /////////////////////

        RegistrationImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) profileImage = data.getData();

        imageView3.setImageURI(profileImage);
    }

    public void onRegistrationSubmit(View view) {

        USER_EMAIL = UserEmail.getText().toString();
        USER_NAME = UserName.getText().toString();
        USER_PASSWORD = UserPassword.getText().toString();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}