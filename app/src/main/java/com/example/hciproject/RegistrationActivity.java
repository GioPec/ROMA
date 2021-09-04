package com.example.hciproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class RegistrationActivity extends AppCompatActivity {

    public EditText UserEmail, UserName, UserPassword, UserPassword2;
    public static Button RegistrationImageButton;

    public static final int PICK_IMAGE = 2;
    public static Uri profileImage;
    public static ImageView imageView3;

    public static String USER_EMAIL;
    public static String USER_NAME;
    public static String USER_PASSWORD;
    public static String USER_PASSWORD2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        UserEmail = findViewById(R.id.UserEmail);
        UserName = findViewById(R.id.UserName);
        UserPassword = findViewById(R.id.UserPassword);
        UserPassword2 = findViewById(R.id.UserPassword2);
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

        USER_PASSWORD = UserPassword.getText().toString();
        USER_PASSWORD2 = UserPassword2.getText().toString();
        USER_EMAIL = UserEmail.getText().toString();
        USER_NAME = UserName.getText().toString();

        if (!USER_PASSWORD.equals(USER_PASSWORD2)) {

            Snackbar.make(view, "Le password non corrispondono!", Snackbar.LENGTH_SHORT)
                    .setAction("Chiudi", v -> {

                    }).show();
        }
        else if (!TextUtils.isEmpty(USER_EMAIL) && !Patterns.EMAIL_ADDRESS.matcher(USER_EMAIL).matches()){
            Snackbar.make(view, "Inserisci una email valida!", Snackbar.LENGTH_SHORT)
                    .setAction("Chiudi", v -> {

                    }).show();
        }
        else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}