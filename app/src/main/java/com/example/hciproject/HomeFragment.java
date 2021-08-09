package com.example.hciproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;

import static androidx.core.content.ContextCompat.getSystemService;

public class HomeFragment extends Fragment {

    public static Marker marker1, marker2, marker3, marker4, marker5;

    public static Marker newMarker;
    public static LatLng popupLatLngMarker;
    public static String popupTitolo;
    public static String popupCategoria;
    public static String popupDescrizione;
    public static boolean popupUrgente;
    public static Uri selectedImageUri;

    public static final int PICK_IMAGE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) selectedImageUri = data.getData();

        MainActivity.imageView3.setImageURI(selectedImageUri);
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        @Override
        public void onMapReady(GoogleMap googleMap) {

            //googleMap.setMyLocationEnabled(true);
            //googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            // TODO per la user position: https://developers.google.com/maps/documentation/android-sdk/current-place-tutorial
            googleMap.getUiSettings().setMapToolbarEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true); //false
            googleMap.setMinZoomPreference(10);
            googleMap.setMaxZoomPreference(20);

            // Map bounds
            LatLngBounds romeBounds = new LatLngBounds(
                    new LatLng(41.77, 12.364), // SW bounds
                    new LatLng(42.0, 12.65)  // NE bounds
            );
            googleMap.setLatLngBoundsForCameraTarget(romeBounds);
            //googleMap.moveCamera(CameraUpdateFactory.newLatLng(rome));

            /* Custom marker
            int height = 100;
            int width = 100;
            BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.mipmap.marker);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            */

            ////////////////////////////////////////////////////////////////////////////////////////

            LatLng ll1 = new LatLng(41.9, 12.5);
            marker1 = googleMap.addMarker(new MarkerOptions()
                    .position(ll1)
                    .snippet("Buca")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    );

            LatLng ll2 = new LatLng(41.88, 12.42);
            marker2 = googleMap.addMarker(new MarkerOptions()
                    .position(ll2)
                    .snippet("Segnaletica")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
            );

            LatLng ll3 = new LatLng(41.81, 12.54);
            marker3 = googleMap.addMarker(new MarkerOptions()
                    .position(ll3)
                    .snippet("Altro problema stradale")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
            );

            LatLng ll4 = new LatLng(41.90, 12.55);
            marker4 = googleMap.addMarker(new MarkerOptions()
                    .position(ll4)
                    .snippet("Vegetazione")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            );

            LatLng ll5 = new LatLng(41.75, 12.40);
            marker5 = googleMap.addMarker(new MarkerOptions()
                    .position(ll5)
                    .snippet("Fauna")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
            );

            LatLng ll6 = new LatLng(41.78, 12.45);
            marker5 = googleMap.addMarker(new MarkerOptions()
                    .position(ll6)
                    .snippet("Guasto")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            );

            LatLng ll7 = new LatLng(41.71, 12.39);
            marker5 = googleMap.addMarker(new MarkerOptions()
                    .position(ll7)
                    .snippet("Immondizia")
                    .icon(BitmapDescriptorFactory.defaultMarker(305.0f))
            );

            if (popupTitolo!=null) {
                newMarker = googleMap.addMarker(new MarkerOptions()
                        .position(popupLatLngMarker)
                        .title(popupTitolo)
                        .snippet(popupCategoria)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                );
            }

            ////////////////////////////////////////////////////////////////////////////////////////

            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    MainActivity.reportPopupCard.setVisibility(View.INVISIBLE);
                }
            });

            ////////////////////////////////////////////////////////////////////////////////////////

            // Setting a click event handler for the map
            googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {

                    popupLatLngMarker = latLng;

                    // Animating to the touched position
                    //googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

                    MainActivity.popupNewReport.setVisibility(View.VISIBLE);


                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.spinnerCategories, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    MainActivity.popupCategoria.setAdapter(adapter);

                    /*
                    newMarker = googleMap.addMarker(new MarkerOptions()
                            .position(popupLatLngMarker)
                            .title(popupTitolo)
                            .snippet(popupCategoria)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                    );
                    */
                    newMarker = googleMap.addMarker(new MarkerOptions()
                            .position(popupLatLngMarker)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                    );
                }
            });

            ////////////////////////////////////////////////////////////////////////////////////////

            MainActivity.bottoneConferma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    popupTitolo = MainActivity.popupTitolo.getText().toString();
                    popupCategoria = MainActivity.popupCategoria.getSelectedItem().toString();
                    popupDescrizione = MainActivity.popupDescrizione.getText().toString();
                    popupUrgente = MainActivity.popupUrgente.isChecked();

                    newMarker.setTitle(HomeFragment.popupTitolo);
                    newMarker.setSnippet(HomeFragment.popupCategoria);
                    newMarker.setTag(selectedImageUri);

                    newMarker.setIcon(BitmapDescriptorFactory.defaultMarker(MainActivity.colorsMarkersDictionary.get(popupCategoria))); //cambio colore marker

                    MainActivity.imageView3.setImageURI(null);

                    MainActivity.popupNewReport.setVisibility(View.INVISIBLE);

                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(popupLatLngMarker, 12));


                    //InputMethodManager imm = (InputMethodManager) getSystemService(getContext()); //TODO
                    //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    //MainActivity.theWindow.setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN );

                }
            });

            ////////////////////////////////////////////////////////////////////////////////////////

            MainActivity.buttonImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

                }
            });

            ////////////////////////////////////////////////////////////////////////////////////////

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    int newVisibility=View.VISIBLE;
                    int firstPopUpVisibility = MainActivity.reportPopupCard.getVisibility();
                    if (firstPopUpVisibility==View.INVISIBLE) newVisibility=View.VISIBLE;
                    //else newVisibility=View.INVISIBLE;

                    if (marker.getTag()!=null && marker.getTag()==selectedImageUri) {    //marker aggiunto dall'utente
                        MainActivity.titoloReportPopup.setText(popupTitolo);
                        MainActivity.categoriaReportPopup.setText(popupCategoria);
                        MainActivity.descrizioneReportPopup.setText(popupDescrizione);
                        MainActivity.immaginePopup.setImageURI(selectedImageUri);
                        MainActivity.reportPopupCard.setStrokeColor(MainActivity.colorsDictionary.get(popupCategoria));
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Buca"))
                    {
                        MainActivity.titoloReportPopup.setText("Attenzione! Buca profonda al lato della strada!");
                        MainActivity.categoriaReportPopup.setText("Buca");
                        MainActivity.descrizioneReportPopup.setText("Fate attenzione all'angolo tra via delle Primule e via delle Violette, soprattutto se viaggiate in moto...");
                        MainActivity.reportPopupCard.setStrokeColor(0xFF0000FF);    //blue
                        MainActivity.immaginePopup.setImageResource(R.drawable.buca);
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Segnaletica"))
                    {
                        MainActivity.titoloReportPopup.setText("Mi è arrivata la multa, ma il cartello non si vedeva..");
                        MainActivity.categoriaReportPopup.setText("Segnaletica");
                        MainActivity.descrizioneReportPopup.setText("In questo tratto di strada il sorpasso è proibito. Peccato che non è segnalato, o meglio, il cartello che dovrebbe avvisare di ciò è stato...");
                        MainActivity.reportPopupCard.setStrokeColor(0xFFFFFF00);    //yellow
                        MainActivity.immaginePopup.setImageResource(R.drawable.segnaletica);
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Altro problema stradale"))
                    {
                        MainActivity.titoloReportPopup.setText("Sono dieci mesi che non funziona! Fate qualcosa!");
                        MainActivity.categoriaReportPopup.setText("Altro problema stradale");
                        MainActivity.descrizioneReportPopup.setText("È passato quasi un anno e ancora nessuna traccia di intervento da parte del comune. Tutte le sere tornando a casa ho paura...");
                        MainActivity.reportPopupCard.setStrokeColor(0xFF800080);    //violet
                        MainActivity.immaginePopup.setImageResource(R.drawable.altroproblemastradale);
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Vegetazione"))
                    {
                        MainActivity.titoloReportPopup.setText("Mia nonna non riesce più a percorrerlo");
                        MainActivity.categoriaReportPopup.setText("Vegetazione");
                        MainActivity.descrizioneReportPopup.setText("Come da titolo, faccio questa segnalazione per portare alla vostra attenzione un problema urgente di degrado urbano...");
                        MainActivity.reportPopupCard.setStrokeColor(0xFF00FF00);    //green
                        MainActivity.immaginePopup.setImageResource(R.drawable.vegetazione);
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Fauna"))
                    {
                        MainActivity.titoloReportPopup.setText("Fate attenzione, avvistati cinghiali in zona!!");
                        MainActivity.categoriaReportPopup.setText("Fauna");
                        MainActivity.descrizioneReportPopup.setText("In 60 anni non ho mai visto una roba del genere, ormai non hanno manco più paura delle macchine! O li catturate o me li mangio...");
                        MainActivity.immaginePopup.setImageResource(R.drawable.fauna);
                        MainActivity.reportPopupCard.setStrokeColor(0xFFFC6A03);    //orange
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Guasto"))
                    {
                        MainActivity.titoloReportPopup.setText("Mettetele in funzione una volta per tutte!");
                        MainActivity.categoriaReportPopup.setText("Guasto");
                        MainActivity.descrizioneReportPopup.setText("Non le ho mai viste in funzione, mi tocca sempre prendere le scale a piedi. È un disagio per me e per molti altri pendolari...");
                        MainActivity.immaginePopup.setImageResource(R.drawable.guasto);
                        MainActivity.reportPopupCard.setStrokeColor(0xFFD0312D);    //red
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Immondizia"))
                    {
                        MainActivity.titoloReportPopup.setText("Vergogna!!!");
                        MainActivity.categoriaReportPopup.setText("Immondizia");
                        MainActivity.descrizioneReportPopup.setText("Devo fare lo slalom per uscire di casa!!");
                        MainActivity.immaginePopup.setImageResource(R.drawable.immondizia);
                        MainActivity.reportPopupCard.setStrokeColor(0xFFA1045A);    //purple
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    return true;    //false per centrare la camera e altro
                }
            });
        }
    };

    public static void rimuoviMarkerPopup() {
        newMarker.remove();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.home_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}