package com.example.hciproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.textview.MaterialTextView;

public class ProfileFragment extends Fragment {

    TextView descrizioneHidden;
    TextView categoriaHidden;
    ImageView immagineHidden;
    ImageView urgenteHidden;
    TextView titoloHidden;
    ImageButton markerButtonHidden;
    com.google.android.material.card.MaterialCardView cardHidden;

    public static ImageView profileImage;
    public static ImageView imageView_hidden_1;
    public static ImageView imageView;
    public static ImageView imageView2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        MaterialTextView usernameEditText = view.findViewById(R.id.profileUsername);
        usernameEditText.setText(LoginActivity.UTENTE);
        MaterialTextView usernameEditText2 = view.findViewById(R.id.textView);
        usernameEditText2.setText(LoginActivity.UTENTE);
        MaterialTextView usernameEditText3 = view.findViewById(R.id.textView2);
        usernameEditText3.setText(LoginActivity.UTENTE);
        MaterialTextView usernameEditText4 = view.findViewById(R.id.textView_hidden_1);
        usernameEditText4.setText(LoginActivity.UTENTE);

        profileImage = view.findViewById(R.id.profileImage);
        imageView_hidden_1 = view.findViewById(R.id.imageView_hidden_1);
        imageView = view.findViewById(R.id.imageView);
        imageView2 = view.findViewById(R.id.imageView2);

        profileImage.setImageURI(RegistrationActivity.profileImage);
        imageView_hidden_1.setImageURI(RegistrationActivity.profileImage);
        imageView.setImageURI(RegistrationActivity.profileImage);
        imageView2.setImageURI(RegistrationActivity.profileImage);

        /////////////////////////////////////////////////////////////

        if (HomeFragment.selectedImageUri != null) {
            descrizioneHidden = view.findViewById(R.id.textView1_hidden_1);
            categoriaHidden = view.findViewById(R.id.list_of_subjects1_hidden);
            immagineHidden  = view.findViewById(R.id.icon1_hidden);
            titoloHidden = view.findViewById(R.id.textView1_hidden_1_1);
            urgenteHidden = view.findViewById(R.id.imageViewUrg);
            markerButtonHidden = view.findViewById(R.id.markerButton_hidden);

            descrizioneHidden.setText(HomeFragment.popupDescrizione);
            categoriaHidden.setText(HomeFragment.popupCategoria);
            titoloHidden.setText(HomeFragment.popupTitolo);
            immagineHidden.setImageURI(HomeFragment.selectedImageUri);
            markerButtonHidden.setTag(HomeFragment.popupCategoria);
            if (HomeFragment.popupUrgente) urgenteHidden.setVisibility(View.VISIBLE);

            cardHidden = view.findViewById(R.id.base_cardview_hidden_1);
            cardHidden.setStrokeColor((MainActivity.colorsDictionary.get(HomeFragment.popupCategoria)));
            cardHidden.setVisibility(View.VISIBLE);
        }

        /////////////////////////////////////////////////////////////

        ////////////////   0 (hidden)   /////////////////

        ImageButton arrow0 = view.findViewById(R.id.arrow_button1_hidden);
        LinearLayout hiddenView0 = view.findViewById(R.id.hidden_view1_hidden_1);


        arrow0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenView0.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    //  by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    //TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                    hiddenView0.setVisibility(View.GONE);
                    arrow0.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(cardHidden, new AutoTransition());
                    hiddenView0.setVisibility(View.VISIBLE);
                    arrow0.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                }
            }
        });

        ////////////////   1   /////////////////

        CardView cardView1 = view.findViewById(R.id.base_cardview1);
        ImageButton arrow1 = view.findViewById(R.id.arrow_button1);
        LinearLayout hiddenView1 = view.findViewById(R.id.hidden_view1);


        arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenView1.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    //  by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    //TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                    hiddenView1.setVisibility(View.GONE);
                    arrow1.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                    hiddenView1.setVisibility(View.VISIBLE);
                    arrow1.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                }
            }
        });

        /////////////////   2   ///////////////////

        CardView cardView2 = view.findViewById(R.id.base_cardview2);
        ImageButton arrow2 = view.findViewById(R.id.arrow_button2);
        LinearLayout hiddenView2 = view.findViewById(R.id.hidden_view2);

        arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenView2.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    //  by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    //TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    hiddenView2.setVisibility(View.GONE);
                    arrow2.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    hiddenView2.setVisibility(View.VISIBLE);
                    arrow2.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }
            }
        });

        /////////////////////////////////////////////////

        return view;
    }

    //@Override
    //public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {}
}