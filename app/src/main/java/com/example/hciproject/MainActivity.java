package com.example.hciproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
    //public static TextView descrizioneReportPopup;
    public static TextView usernameReportPopup;
    public static ImageView fotoReportPopup;
    public static com.google.android.material.card.MaterialCardView popupNewReport;

    public static EditText popupTitolo;
    public static EditText popupDescrizione;
    public static Spinner popupCategoria;
    public static CheckBox popupUrgente;
    public static Button bottoneConferma;
    public static Button buttonImage;

    public static ImageView immaginePopup;

    public static ImageView imageView3;

    public static Window theWindow;

    //public static menu nomeUtenteBottomMenu;

    public static boolean primaVolta = true;

    public static ImageButton MB1, MB2, MB1H;
    public static ImageView I1, I2, I1H;
    public static ImageButton AB1, AB2, AB1H;

    public static MaterialCardView rimuoviSegnalazioneCard;
    public static MaterialCardView rimuoviSegnalazioneDark;
    public static MaterialCardView cardDaRimuovere0;
    public static MaterialCardView cardDaRimuovere1;
    public static MaterialCardView cardDaRimuovere2;

    public static Map<String, Integer> colorsDictionary = new HashMap<String, Integer>();
    public static Map<String, Float> colorsMarkersDictionary = new HashMap<String, Float>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorsDictionary.put("Buca", 0xFF0000FF);
        colorsDictionary.put("Segnaletica", 0xFFFFFF00);
        colorsDictionary.put("Altro problema stradale", 0xFF800080);
        colorsDictionary.put("Vegetazione", 0xFF00FF00);
        colorsDictionary.put("Fauna", 0xFFFC6A03);
        colorsDictionary.put("Guasto", 0xFFD0312D);
        colorsDictionary.put("Immondizia", 0xFFFF1CAE);
        colorsDictionary.put("Altro", 0xFFFFFFFF);

        colorsMarkersDictionary.put("Buca", BitmapDescriptorFactory.HUE_BLUE);
        colorsMarkersDictionary.put("Segnaletica", BitmapDescriptorFactory.HUE_YELLOW);
        colorsMarkersDictionary.put("Altro problema stradale", BitmapDescriptorFactory.HUE_VIOLET);
        colorsMarkersDictionary.put("Vegetazione", BitmapDescriptorFactory.HUE_GREEN);
        colorsMarkersDictionary.put("Fauna", BitmapDescriptorFactory.HUE_ORANGE);
        colorsMarkersDictionary.put("Guasto", BitmapDescriptorFactory.HUE_RED);
        colorsMarkersDictionary.put("Immondizia", 305.0f);
        colorsMarkersDictionary.put("Altro", 175.0f);

        theWindow = getWindow();

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        navView.setOnNavigationItemSelectedListener(navListener);
        navView.setSelectedItemId(R.id.home_button);

        //rimuoviSegnalazioneCard = findViewById(R.id.rimuoviSegnalazioneCard);
        //rimuoviSegnalazioneDark= findViewById(R.id.rimuoviSegnalazioneDark);

        /*
        MB1 = findViewById(R.id.markerButtonP);
        MB2 = findViewById(R.id.markerButton2P);
        MB1H = findViewById(R.id.markerButton_hidden);

        AB1 = findViewById(R.id.arrow_button1);
        AB2 = findViewById(R.id.arrow_button1);
        AB1H = findViewById(R.id.arrow_button1);

        I1 = findViewById(R.id.icon1P);
        I2 = findViewById(R.id.icon2P);
        I1H = findViewById(R.id.icon1_hidden);
         */

        addNewReportButton = findViewById(R.id.floating_action_button);
        reportPopupCard = findViewById(R.id.ReportPopupCard);
        titoloReportPopup = findViewById(R.id.popupTitle);
        categoriaReportPopup = findViewById(R.id.popupCategory);
        //descrizioneReportPopup = findViewById(R.id.popupDescription);
        usernameReportPopup = findViewById(R.id.popupUsername);
        fotoReportPopup = findViewById(R.id.imageView1);
        popupNewReport = findViewById(R.id.popupNewReport);

        popupTitolo = findViewById(R.id.editTextTextPersonName);
        popupDescrizione = findViewById(R.id.editTextTextPersonName2);
        popupCategoria = findViewById(R.id.spinner);
        popupUrgente = findViewById(R.id.checkBox);

        immaginePopup = findViewById(R.id.imageView1);

        imageView3 = findViewById(R.id.imageView3);

        bottoneConferma = findViewById(R.id.button3);
        buttonImage = findViewById(R.id.buttonImage);

        //nomeUtenteBottomMenu = findViewById(R.id.profile);
        //nomeUtenteBottomMenu.setText(LoginActivity.UTENTE);

        //Keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,
                    new HomeFragment()).commit();
        }
    }

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

    /*
    public void createNewReport(View view) {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }
    */

    public void mostraTuttoOnClick(View view) {
        //AlphaAnimation buttonClick = new AlphaAnimation(0.2f, 1.0f);
        //view.startAnimation(buttonClick);
        TextView popupU = view.findViewById(R.id.popupUsername);
        //TODO FIX
        if (true) { Intent intent = new Intent(this, ExternalProfileActivity.class); startActivity(intent); }   //popupU.getText().toString().equals("Gabriele")
        //else getFragmentManager().beginTransaction().replace(R.id.flFragment, new ProfileFragment()).addToBackStack(null).commit();

    }

    public void onClickShowProfile(View view) {
        Intent intent = new Intent(this, ExternalProfileActivity.class);
        startActivity(intent);
    }

    public void onClickShowMyProfile(View view) {
        Intent intent = new Intent(this, ExternalProfileActivity.class);
        startActivity(intent);
    }

    public void AnnullaPopup(View view) {
        HomeFragment.selectedImageUri = null;
        popupNewReport.setVisibility(View.INVISIBLE);
        HomeFragment.rimuoviMarkerPopup();
    }

    public void showInfo(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
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

    public void mostraSullaMappa(View view) {
        HomeFragment.caricamentoDaMarkerCategoria = view.getTag().toString();
        HomeFragment.caricamentoDaMarker = true;
        HomeFragment.HOME_POPUP_OPEN = false;
        //ExploreFragment.scrollY = ExploreFragment.scrollView2.getChildAt(0).getTop();
        //View c = ExploreFragment.scrollView2.getChildAt(0);
        //int scrolly = -c.getTop() + ExploreFragment.scrollView2.getFirstVisiblePosition() * c.getHeight();
        //Log.e("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", String.valueOf(ExploreFragment.scrollView2.getChildAt(0).getTop()));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void editUsername(View view) {
        if (!ProfileFragment.usernameEditText222.isEnabled()) {
            ProfileFragment.usernameEditText222.setEnabled(true);
            ProfileFragment.editButton.setImageResource(R.drawable.ok);
            ProfileFragment.editButton.setScaleX((float)1.5);
            ProfileFragment.editButton.setScaleY((float)1.5);
            ProfileFragment.usernameEditText222.requestFocus();
        } else {
            String x = ProfileFragment.usernameEditText222.getText().toString();
            if (x.matches("")) {
                ProfileFragment.usernameEditText222.setText(LoginActivity.UTENTE);
                ProfileFragment.usernameEditText222.requestFocus();
                Snackbar.make(view, "Lo username non può essere vuoto!", Snackbar.LENGTH_LONG)
                        .setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {}
                        }).show();
                return;
            }
            else {
                ProfileFragment.usernameEditText222.setEnabled(false);
                ProfileFragment.editButton.setImageResource(R.drawable.edit);
                ProfileFragment.editButton.setScaleX((float)1);
                ProfileFragment.editButton.setScaleY((float)1);
                ProfileFragment.nome1.setText(ProfileFragment.usernameEditText222.getText().toString());
                ProfileFragment.nome2.setText(ProfileFragment.usernameEditText222.getText().toString());
                ProfileFragment.nome3.setText(ProfileFragment.usernameEditText222.getText().toString());
                LoginActivity.UTENTE = ProfileFragment.usernameEditText222.getText().toString();
            }
        }
    }

    /*
    public void logout(View view) {
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

     */

    public void fullscreen(View view) {
        switch (String.valueOf(view.getTag())) {
            case "vegetazione":
                FullscreenImageActivity.fullscreenImageResource = R.drawable.vegetazione;
                break;
            case "buca":
                FullscreenImageActivity.fullscreenImageResource = R.drawable.buca;
                break;
            case "guasto":
                FullscreenImageActivity.fullscreenImageResource = R.drawable.guasto;
                break;
            case "altroproblemastradale":
                FullscreenImageActivity.fullscreenImageResource = R.drawable.altroproblemastradale;
                break;
            case "fauna":
                FullscreenImageActivity.fullscreenImageResource = R.drawable.fauna;
                break;
            case "immondizia":
                FullscreenImageActivity.fullscreenImageResource = R.drawable.immondizia;
                break;
            case "segnaletica":
                FullscreenImageActivity.fullscreenImageResource = R.drawable.segnaletica;
                break;
            case "mia":
                FullscreenImageActivity.fullscreenImageResource = 0;
                break;
        }
        Intent intent = new Intent(this, FullscreenImageActivity.class);
        startActivity(intent);
    }

    public void settings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public static int SEGN_DA_RIMUOVERE = 0;

    public void rimuoviSegnalazione(View view) {
        SEGN_DA_RIMUOVERE = Integer.parseInt(view.getTag().toString());
        rimuoviSegnalazioneCard.setVisibility(View.VISIBLE);
        rimuoviSegnalazioneDark.setVisibility(View.VISIBLE);
    }

    public void rimuoviSegnalazioneAnnulla(View view) {
        rimuoviSegnalazioneCard.setVisibility(View.INVISIBLE);
        rimuoviSegnalazioneDark.setVisibility(View.INVISIBLE);
    }

    public void rimuoviSegnalazioneOk(View view) {
        switch (SEGN_DA_RIMUOVERE) {
            case 0:
                cardDaRimuovere0.setVisibility(View.GONE);
                ProfileFragment.CARD_0_REMOVED = true;
                HomeFragment.newMarker.remove();
                break;
            case 1:
                cardDaRimuovere1.setVisibility(View.GONE);
                ProfileFragment.CARD_1_REMOVED = true;
                break;
            case 2:
                cardDaRimuovere2.setVisibility(View.GONE);
                ProfileFragment.CARD_2_REMOVED = true;
                break;

        }
        rimuoviSegnalazioneCard.setVisibility(View.INVISIBLE);
        rimuoviSegnalazioneDark.setVisibility(View.INVISIBLE);
    }
}