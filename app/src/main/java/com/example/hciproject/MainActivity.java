package com.example.hciproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static Map<String, String[]> profilesData = new HashMap<String, String[]>();
    public static Map<String, int[]> profilesReports = new HashMap<String, int[]>();
    public static Map<Integer, String[]> reportsData = new HashMap<Integer, String[]>();

    public FloatingActionButton addNewReportButton;
    public static com.google.android.material.card.MaterialCardView reportPopupCard;
    public static TextView titoloReportPopup;
    public static TextView categoriaReportPopup;
    public static TextView descrizioneReportPopup;
    public static ImageView fotoReportPopup;
    public static com.google.android.material.card.MaterialCardView popupNewReport;

    public static EditText popupTitolo;
    public static EditText popupDescrizione;
    public static Spinner popupCategoria;
    public static CheckBox popupUrgente;
    public static Button bottoneConferma;
    public static Button buttonImage;

    public static ImageView immaginePopup;

    public static Window theWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theWindow = getWindow();

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        navView.setOnNavigationItemSelectedListener(navListener);
        navView.setSelectedItemId(R.id.home_button);

        addNewReportButton = findViewById(R.id.floating_action_button);
        reportPopupCard = findViewById(R.id.ReportPopupCard);
        titoloReportPopup = findViewById(R.id.popupTitle);
        categoriaReportPopup = findViewById(R.id.popupCategory);
        descrizioneReportPopup = findViewById(R.id.popupDescription);
        fotoReportPopup = findViewById(R.id.imageView1);
        popupNewReport = findViewById(R.id.popupNewReport);

        popupTitolo = findViewById(R.id.editTextTextPersonName);
        popupDescrizione = findViewById(R.id.editTextTextPersonName2);
        popupCategoria = findViewById(R.id.spinner);
        popupUrgente = findViewById(R.id.checkBox);

        immaginePopup = findViewById(R.id.imageView1);

        bottoneConferma = findViewById(R.id.button3);
        buttonImage = findViewById(R.id.buttonImage);

        //Keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,
                    new HomeFragment()).commit();
        }
    }

    /*
    private void initAppContents() {
        String[] anna = {
                "Anna",
                "path_to_profile_image"
        };
        profilesData.put("anna", anna);
        int[] annaReports = {1,2,3};
        profilesReports.put("anna", annaReports);
        String[] report1 = {
                "Titolo",   //forse non necessario
                "Latitudine",
                "Longitudine",
                "Tipologia",    //buca, malfunzionamento, segnalazione
                "Urgente",      //boolean
                "Stato",        //attivo, preso in consideraz., risolto
                "Descrizione",
                "Immagine"      //facoltativa, (path)
        };
        reportsData.put(1, report1);
    }
    */

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = new HomeFragment();

                switch (item.getItemId()) {
                    case R.id.home:
                        addNewReportButton.setVisibility(View.VISIBLE); //mostra bottone solo in maps fragment
                        reportPopupCard.setVisibility(View.INVISIBLE);
                        MainActivity.popupNewReport.setVisibility(View.INVISIBLE);
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.explore:
                        addNewReportButton.setVisibility(View.INVISIBLE);
                        reportPopupCard.setVisibility(View.INVISIBLE);
                        MainActivity.popupNewReport.setVisibility(View.INVISIBLE);
                        selectedFragment = new ExploreFragment();
                        break;
                    case R.id.profile:
                        addNewReportButton.setVisibility(View.INVISIBLE);
                        reportPopupCard.setVisibility(View.INVISIBLE);
                        MainActivity.popupNewReport.setVisibility(View.INVISIBLE);
                        selectedFragment = new ProfileFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,
                        selectedFragment).commit();
                return true;
            }
        };

    public void createNewReport(View view) {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }

    public void mostraTuttoOnClick(View view) {
        //AlphaAnimation buttonClick = new AlphaAnimation(0.2f, 1.0f);
        //view.startAnimation(buttonClick);
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }

    public void onClickShowProfile(View view) {
        Intent intent = new Intent(this, ExternalProfileActivity.class);
        startActivity(intent);
    }

    public void AnnullaPopup(View view) {
        popupNewReport.setVisibility(View.INVISIBLE);
        HomeFragment.rimuoviMarkerPopup();
    }

    /*
    public void confermaPopup(View view) {

        popupTitolo = findViewById(R.id.editTextTextPersonName);
        popupDescrizione = findViewById(R.id.editTextTextPersonName2);
        popupCategoria = findViewById(R.id.spinner);
        popupUrgente = findViewById(R.id.checkBox);

        HomeFragment.popupTitolo = popupTitolo.getText().toString();
        HomeFragment.popupDescrizione = popupDescrizione.getText().toString();
        HomeFragment.popupCategoria = popupCategoria.getSelectedItem().toString();
        HomeFragment.popupUrgente = popupUrgente.isChecked();

        HomeFragment.newMarker.setTitle(HomeFragment.popupTitolo);
        HomeFragment.newMarker.setSnippet(HomeFragment.popupCategoria);
        //HomeFragment.newMarker.setPosition(HomeFragment.popupLatLngMarker);

        popupNewReport.setVisibility(View.INVISIBLE);
    }
    */
}