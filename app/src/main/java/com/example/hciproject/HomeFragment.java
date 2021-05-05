package com.example.hciproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
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

            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.setMinZoomPreference(10);
            googleMap.setMaxZoomPreference(20);

            // Map bounds
            LatLngBounds romeBounds = new LatLngBounds(
                    new LatLng(41.77, 12.364), // SW bounds
                    new LatLng(42.0, 12.65)  // NE bounds
            );
            googleMap.setLatLngBoundsForCameraTarget(romeBounds);

            /* Custom marker
            int height = 100;
            int width = 100;
            BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.mipmap.marker);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            */

            LatLng rome = new LatLng(41.9, 12.5);
            googleMap.addMarker(new MarkerOptions().position(rome).title("Buca"));
            //googleMap.moveCamera(CameraUpdateFactory.newLatLng(rome));

        }
    };

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