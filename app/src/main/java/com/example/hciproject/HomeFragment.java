package com.example.hciproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

public class HomeFragment extends Fragment {

    public static Marker marker1, marker2, marker3, marker4, marker5;

    public static Marker newMarker;
    public static LatLng popupLatLngMarker;
    public static String popupTitolo;
    public static String popupCategoria;
    public static String popupDescrizione;
    public static String popupUsername;
    public static boolean popupUrgente;
    public static Uri selectedImageUri = null;

    public static final int PICK_IMAGE = 1;
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2;

    public static boolean caricamentoDaMarker = false;
    public static String caricamentoDaMarkerCategoria;

    public static LatLng HOME_LATLNG = new LatLng(41.9, 12.5);
    public static float HOME_ZOOM = 11;
    public static boolean HOME_POPUP_OPEN = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (MainActivity.primaVolta) {
            MainActivity.primaVolta = false;
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Per aggiungere una nuova segnalazione, tieni premuto sulla mappa", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Chiudi", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
        }

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) selectedImageUri = data.getData();

        MainActivity.imageView3.setImageURI(selectedImageUri);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //googleMap.setMyLocationEnabled(true);
                //googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            }
        }
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

            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                return;
            }

            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
            Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
            googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    if (locationResult.isSuccessful() && locationResult.getResult() != null) {
                        LatLng pos = new LatLng(locationResult.getResult().getLatitude(), locationResult.getResult().getLongitude());
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 20));
                        return true;
                    }
                    return false;
                }
            });

            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            googleMap.getUiSettings().setMapToolbarEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true); //false
            googleMap.setMinZoomPreference(11);
            googleMap.setMaxZoomPreference(20);

            // Map bounds
            LatLngBounds romeBounds = new LatLngBounds(
                    new LatLng(41.629372, 12.140821), // SW bounds
                    new LatLng(42.100291, 12.824020)  // NE bounds
            );
            googleMap.setLatLngBoundsForCameraTarget(romeBounds);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HOME_LATLNG, HOME_ZOOM));

            if (HOME_POPUP_OPEN) MainActivity.reportPopupCard.setVisibility(View.VISIBLE);
            else MainActivity.reportPopupCard.setVisibility(View.INVISIBLE);

            /* Custom marker
            int height = 100;
            int width = 100;
            BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.mipmap.marker);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            */

            ////////////////////////////////////////////////////////////////////////////////////////

            if (!ProfileFragment.CARD_2_REMOVED) {
                LatLng ll1 = new LatLng(41.942574, 12.473963);
                marker1 = googleMap.addMarker(new MarkerOptions()
                        .position(ll1)
                        .snippet("Buca")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                );
            }

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

            if (!ProfileFragment.CARD_1_REMOVED) {
                LatLng ll4 = new LatLng(41.898038, 12.562988);
                marker4 = googleMap.addMarker(new MarkerOptions()
                        .position(ll4)
                        .snippet("Vegetazione")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                );
            }

            LatLng ll5 = new LatLng(41.949815, 12.455857);
            marker5 = googleMap.addMarker(new MarkerOptions()
                    .position(ll5)
                    .snippet("Fauna")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
            );

            LatLng ll6 = new LatLng(41.901405, 12.500068);
            marker5 = googleMap.addMarker(new MarkerOptions()
                    .position(ll6)
                    .snippet("Guasto")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            );

            LatLng ll7 = new LatLng(41.848584, 12.486243);
            marker5 = googleMap.addMarker(new MarkerOptions()
                    .position(ll7)
                    .snippet("Immondizia")
                    .icon(BitmapDescriptorFactory.defaultMarker(305.0f))
            );

            if (!ProfileFragment.CARD_0_REMOVED) {
                if (popupTitolo != null) {
                    newMarker = googleMap.addMarker(new MarkerOptions()
                            .position(popupLatLngMarker)
                            .title(popupTitolo)
                            .snippet(popupCategoria)
                            .icon(BitmapDescriptorFactory.defaultMarker(MainActivity.colorsMarkersDictionary.get(popupCategoria)))
                    );
                    newMarker.setTag(selectedImageUri);
                }
            }

            //////////////////////////////   caricamento da marker   ///////////////////////////////

            if (caricamentoDaMarker) {
                caricamentoDaMarker = false;
                LatLng caricamentoLL;
                switch (caricamentoDaMarkerCategoria) {
                    case "Buca": caricamentoLL = new LatLng(41.942574, 12.473963); break;
                    case "Segnaletica": caricamentoLL = new LatLng(41.88, 12.42); break;
                    case "Altro problema stradale": caricamentoLL = new LatLng(41.81, 12.54); break;
                    case "Vegetazione": caricamentoLL = new LatLng(41.898038, 12.562988); break;
                    case "Fauna": caricamentoLL = new LatLng(41.949815, 12.455857); break;
                    case "Guasto": caricamentoLL = new LatLng(41.901405, 12.500068); break;
                    case "Immondizia": caricamentoLL = new LatLng(41.848584, 12.486243); break;
                    case "Altro": caricamentoLL = new LatLng(42.006310, 12.357117); break;  //piansaccoccia
                    default: caricamentoLL = newMarker.getPosition(); break;
                }
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(caricamentoLL, 17));
            }

            ////////////////////////////////////////////////////////////////////////////////////////

            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    MainActivity.reportPopupCard.setVisibility(View.INVISIBLE);
                    HOME_POPUP_OPEN = false;
                }
            });

            ////////////////////////////////////////////////////////////////////////////////////////

            // Setting a click event handler for the map
            googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {

                    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                    List<Address> addresses = null;
                    Address obj = null;
                    String add = "";
                    try {
                        addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                        obj = addresses.get(0);
                        add = obj.getAddressLine(0);
                        add = add + "\n" + obj.getCountryName();
                        add = add + "\n" + obj.getCountryCode();
                        add = add + "\n" + obj.getAdminArea();
                        add = add + "\n" + obj.getPostalCode();
                        add = add + "\n" + obj.getSubAdminArea();
                        add = add + "\n" + obj.getLocality();
                        add = add + "\n" + obj.getSubThoroughfare();
                        Log.v("IGA", "Address\n" + add);
                    } catch (IOException e) {e.printStackTrace();}

                    int pc;
                    if (obj.getPostalCode()!=null) pc = Integer.parseInt(obj.getPostalCode());
                    else pc=0;
                    int[] valid_pc = IntStream.rangeClosed(118, 199).toArray();

                    if(!IntStream.of(valid_pc).anyMatch(x -> x == pc)) {
                        Snackbar.make(getView(), "Purtroppo questa zona si trova fuori dal comune di Roma", Snackbar.LENGTH_LONG)
                                .setAction("Capito", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {}
                                }).show();
                    }
                    else {
                        popupLatLngMarker = latLng;

                        newMarker = googleMap.addMarker(new MarkerOptions()
                                .position(popupLatLngMarker)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        );

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

                        final Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.reportPopupCard.setVisibility(View.INVISIBLE);
                                HOME_POPUP_OPEN = false;
                                MainActivity.popupNewReport.setVisibility(View.VISIBLE);
                            }
                        }, 2000);

                        // Create an ArrayAdapter using the string array and a default spinner layout
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                                R.array.spinnerCategories, android.R.layout.simple_spinner_item);
                        // Specify the layout to use when the list of choices appears
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        // Apply the adapter to the spinner
                        MainActivity.popupCategoria.setAdapter(adapter);
                    }
                }
            });

            ////////////////////////////////////////////////////////////////////////////////////////

            MainActivity.bottoneConferma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (selectedImageUri!=null) {

                        popupTitolo = MainActivity.popupTitolo.getText().toString();
                        popupCategoria = MainActivity.popupCategoria.getSelectedItem().toString();
                        //popupDescrizione = MainActivity.popupDescrizione.getText().toString();
                        popupUsername = LoginActivity.UTENTE;
                        popupUrgente = MainActivity.popupUrgente.isChecked();

                        newMarker.setTitle(HomeFragment.popupTitolo);
                        newMarker.setSnippet(HomeFragment.popupCategoria);
                        newMarker.setTag(selectedImageUri);

                        newMarker.setIcon(BitmapDescriptorFactory.defaultMarker(MainActivity.colorsMarkersDictionary.get(popupCategoria))); //cambio colore marker

                        MainActivity.imageView3.setImageURI(null);
                        MainActivity.popupTitolo.setText("");
                        MainActivity.popupDescrizione.setText("");
                        MainActivity.popupUrgente.setChecked(false);

                        MainActivity.popupNewReport.setVisibility(View.INVISIBLE);

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(popupLatLngMarker, 12));

                        Snackbar.make(view, "Segnalazione inviata!", Snackbar.LENGTH_LONG)
                                .setAction("Ok", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {}
                                }).show();

                        //InputMethodManager imm = (InputMethodManager) getSystemService(getContext()); //TODO
                        //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                        //MainActivity.theWindow.setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN );
                    }
                    else {
                        Snackbar.make(view, "Aggiungi un'immagine!", Snackbar.LENGTH_LONG)
                                .setAction("Ok", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {}
                                }).show();
                    }

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

            googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                @Override
                public void onCameraIdle() {
                    HOME_LATLNG = googleMap.getCameraPosition().target;
                    HOME_ZOOM = googleMap.getCameraPosition().zoom;
                    HOME_POPUP_OPEN = MainActivity.reportPopupCard.getVisibility()==View.VISIBLE;
                }
            });

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                    int newVisibility=View.VISIBLE;
                    int firstPopUpVisibility = MainActivity.reportPopupCard.getVisibility();
                    if (firstPopUpVisibility==View.INVISIBLE) newVisibility=View.VISIBLE;
                    //else newVisibility=View.INVISIBLE;

                    if (marker.getTag()!=null && marker.getTag()==selectedImageUri)     //marker aggiunto dall'utente
                    {
                        MainActivity.titoloReportPopup.setText(popupTitolo);
                        MainActivity.categoriaReportPopup.setText(popupCategoria);
                        //MainActivity.descrizioneReportPopup.setText(popupDescrizione);
                        MainActivity.usernameReportPopup.setText(LoginActivity.UTENTE);
                        MainActivity.immaginePopup.setImageURI(selectedImageUri);
                        MainActivity.reportPopupCard.setStrokeColor(MainActivity.colorsDictionary.get(popupCategoria));
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Buca"))
                    {
                        MainActivity.titoloReportPopup.setText("Attenzione! Buca profonda al lato della strada!");
                        MainActivity.categoriaReportPopup.setText("Buca");
                        //MainActivity.descrizioneReportPopup.setText("Fate attenzione all'angolo tra via delle Primule e via delle Violette, soprattutto se viaggiate in moto...");
                        MainActivity.usernameReportPopup.setText(LoginActivity.UTENTE);
                        MainActivity.reportPopupCard.setStrokeColor(0xFF0000FF);    //blue
                        MainActivity.immaginePopup.setImageResource(R.drawable.buca);
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Segnaletica"))
                    {
                        MainActivity.titoloReportPopup.setText("Mi è arrivata la multa, ma il cartello non si vedeva...");
                        MainActivity.categoriaReportPopup.setText("Segnaletica");
                        //MainActivity.descrizioneReportPopup.setText("In questo tratto di strada il sorpasso è proibito. Peccato che non è segnalato, o meglio, il cartello che dovrebbe avvisare di ciò è stato...");
                        MainActivity.usernameReportPopup.setText("Gabriele");
                        MainActivity.reportPopupCard.setStrokeColor(0xFFFFFF00);    //yellow
                        MainActivity.immaginePopup.setImageResource(R.drawable.segnaletica);
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Altro problema stradale"))
                    {
                        MainActivity.titoloReportPopup.setText("Sono dieci mesi che non funziona! Fate qualcosa!");
                        MainActivity.categoriaReportPopup.setText("Altro problema stradale");
                        //MainActivity.descrizioneReportPopup.setText("È passato quasi un anno e ancora nessuna traccia di intervento da parte del comune. Tutte le sere tornando a casa ho paura...");
                        MainActivity.usernameReportPopup.setText("Gabriele");
                        MainActivity.reportPopupCard.setStrokeColor(0xFF800080);    //violet
                        MainActivity.immaginePopup.setImageResource(R.drawable.altroproblemastradale);
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Vegetazione"))
                    {
                        MainActivity.titoloReportPopup.setText("Mia nonna non riesce più a percorrerlo");
                        MainActivity.categoriaReportPopup.setText("Vegetazione");
                        //MainActivity.descrizioneReportPopup.setText("Come da titolo, faccio questa segnalazione per portare alla vostra attenzione un problema urgente di degrado urbano...");
                        MainActivity.usernameReportPopup.setText(LoginActivity.UTENTE);
                        MainActivity.reportPopupCard.setStrokeColor(0xFF00FF00);    //green
                        MainActivity.immaginePopup.setImageResource(R.drawable.vegetazione);
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Fauna"))
                    {
                        MainActivity.titoloReportPopup.setText("Fate attenzione, avvistati cinghiali in zona!!");
                        MainActivity.categoriaReportPopup.setText("Fauna");
                        //MainActivity.descrizioneReportPopup.setText("In 40 anni non ho mai visto una roba del genere, ormai non hanno manco più paura delle macchine! O li catturate o me li mangio...");
                        MainActivity.usernameReportPopup.setText("Gabriele");
                        MainActivity.immaginePopup.setImageResource(R.drawable.fauna);
                        MainActivity.reportPopupCard.setStrokeColor(0xFFFC6A03);    //orange
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Guasto"))
                    {
                        MainActivity.titoloReportPopup.setText("Mettetele in funzione una volta per tutte!");
                        MainActivity.categoriaReportPopup.setText("Guasto");
                        //MainActivity.descrizioneReportPopup.setText("Non le ho mai viste in funzione, mi tocca sempre prendere le scale a piedi. È un disagio per me e per molti altri pendolari...");
                        MainActivity.usernameReportPopup.setText("Gabriele");
                        MainActivity.immaginePopup.setImageResource(R.drawable.guasto);
                        MainActivity.reportPopupCard.setStrokeColor(0xFFD0312D);    //red
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("Immondizia"))
                    {
                        MainActivity.titoloReportPopup.setText("Vergogna!!!");
                        MainActivity.categoriaReportPopup.setText("Immondizia");
                        //MainActivity.descrizioneReportPopup.setText("Devo fare lo slalom per uscire di casa!!");
                        MainActivity.usernameReportPopup.setText("Gabriele");
                        MainActivity.immaginePopup.setImageResource(R.drawable.immondizia);
                        MainActivity.reportPopupCard.setStrokeColor(0xFFFF1CAE);    //purple
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else { }
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