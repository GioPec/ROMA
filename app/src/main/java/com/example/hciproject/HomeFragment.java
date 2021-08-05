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
                    .title("BUCA")
                    .snippet("CATEGORIA1")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    );

            LatLng ll2 = new LatLng(41.88, 12.42);
            marker2 = googleMap.addMarker(new MarkerOptions()
                    .position(ll2)
                    .title("BUCA")
                    .snippet("CATEGORIA2")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            );

            LatLng ll3 = new LatLng(41.81, 12.54);
            marker3 = googleMap.addMarker(new MarkerOptions()
                    .position(ll3)
                    .title("BUCA")
                    .snippet("CATEGORIA3")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
            );

            LatLng ll4 = new LatLng(41.90, 12.55);
            marker4 = googleMap.addMarker(new MarkerOptions()
                    .position(ll4)
                    .title("ALBERO")
                    .snippet("CATEGORIA4")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            );

            LatLng ll5 = new LatLng(41.75, 12.40);
            marker5 = googleMap.addMarker(new MarkerOptions()
                    .position(ll5)
                    .title("BUCA")
                    .snippet("CATEGORIA5")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
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
                    if (marker.getSnippet().equals("CATEGORIA1"))
                    {
                        MainActivity.titoloReportPopup.setText("TITOLO");
                        MainActivity.categoriaReportPopup.setText("Categoria1");
                        MainActivity.descrizioneReportPopup.setText("Descrizione bella della Categoria1 -\n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget felis quis arcu euismod gravida eu vitae metus. Nullam dignissim dapibus lorem, in pulvinar leo faucibus quis. Vivamus lacinia, mauris ut vulputate lacinia, enim urna euismod sem, ut vulputate massa quam at ex. Ut vitae eleifend est, in interdum elit. Maecenas imperdiet tortor mauris, sagittis molestie tortor fermentum ac. Phasellus facilisis tempor ipsum nec congue. Aenean eget nulla interdum, posuere nisl fermentum, vestibulum enim. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas eget varius nisl, euismod luctus purus. Curabitur id tincidunt nulla, non lacinia libero. Suspendisse potenti. In varius condimentum velit non dictum. Pellentesque varius accumsan lectus lobortis sagittis.");
                        //MainActivity.reportPopupCard.setBackgroundColor(0xFF930000);    //red
                        MainActivity.reportPopupCard.setStrokeColor(0xFF930000);    //red
                        //MainActivity.fotoReportPopup.setImageBitmap();
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("CATEGORIA2"))
                    {
                        MainActivity.titoloReportPopup.setText("TITOLO");
                        MainActivity.categoriaReportPopup.setText("Categoria2");
                        MainActivity.descrizioneReportPopup.setText("Descrizione bella della Categoria2");
                        //MainActivity.reportPopupCard.setBackgroundColor(0xFF0000FF);    //blue
                        MainActivity.reportPopupCard.setStrokeColor(0xFF0000FF);    //blue
                        //MainActivity.fotoReportPopup.setImageBitmap();
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("CATEGORIA3"))
                    {
                        MainActivity.titoloReportPopup.setText("TITOLO");
                        MainActivity.categoriaReportPopup.setText("Categoria3");
                        MainActivity.descrizioneReportPopup.setText("Descrizione bella della Categoria3");
                        //MainActivity.reportPopupCard.setBackgroundColor(0xFFFFFF00);    //yellow
                        MainActivity.reportPopupCard.setStrokeColor(0xFFFFFF00);    //yellow
                        //MainActivity.fotoReportPopup.setImageBitmap();
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("CATEGORIA4"))
                    {
                        MainActivity.titoloReportPopup.setText("TITOLO");
                        MainActivity.categoriaReportPopup.setText("Categoria4");
                        MainActivity.descrizioneReportPopup.setText("Descrizione bella della Categoria4");
                        //MainActivity.reportPopupCard.setBackgroundColor(0xFF00FF00);    //green
                        MainActivity.reportPopupCard.setStrokeColor(0xFF00FF00);    //green
                        //MainActivity.fotoReportPopup.setImageBitmap();
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else if (marker.getSnippet().equals("CATEGORIA5"))
                    {
                        MainActivity.titoloReportPopup.setText("TITOLO");
                        MainActivity.categoriaReportPopup.setText("Categoria5");
                        MainActivity.descrizioneReportPopup.setText("Descrizione bella della Categoria5");
                        //File imgFile = new File("..\\..\\src\\main\\res\\drawable-v24\\buca");
                        //MainActivity.immaginePopup.setImageURI(Uri.fromFile(imgFile));
                        MainActivity.immaginePopup.setImageResource(R.drawable.buca);
                        //MainActivity.reportPopupCard.setBackgroundColor(0xFFFF9900);    //orange
                        MainActivity.reportPopupCard.setStrokeColor(0xFFFF9900);    //orange
                        //MainActivity.fotoReportPopup.setImageBitmap();
                        MainActivity.reportPopupCard.setVisibility(newVisibility);
                    }
                    else
                    {
                        if (marker.getTag()==selectedImageUri) {    //marker aggiunto dall'utente
                            MainActivity.titoloReportPopup.setText(popupTitolo);
                            MainActivity.categoriaReportPopup.setText(popupCategoria);
                            MainActivity.descrizioneReportPopup.setText(popupDescrizione);
                            MainActivity.immaginePopup.setImageURI(selectedImageUri);
                            //MainActivity.reportPopupCard.setBackgroundColor(0xFFFF9900);    //
                            MainActivity.reportPopupCard.setStrokeColor(0xFF800080);    //
                            //MainActivity.fotoReportPopup.setImageBitmap();
                            MainActivity.reportPopupCard.setVisibility(newVisibility);
                        }
                        else {
                            MainActivity.titoloReportPopup.setText(popupTitolo);
                            MainActivity.categoriaReportPopup.setText(popupCategoria);
                            MainActivity.descrizioneReportPopup.setText(popupDescrizione);
                            //MainActivity.reportPopupCard.setBackgroundColor(0xFFFF9900);    //
                            MainActivity.reportPopupCard.setStrokeColor(0xFF800080);    //
                            //MainActivity.fotoReportPopup.setImageBitmap();
                            MainActivity.reportPopupCard.setVisibility(newVisibility);
                        }
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