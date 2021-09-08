package com.example.hciproject;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;

public class ProfileFragment extends Fragment {

    public static TextView descrizioneHidden;
    public static TextView categoriaHidden;
    public static ImageView immagineHidden;
    public static ImageView urgenteHidden;
    public static TextView titoloHidden;
    public static ImageButton markerButtonHidden;
    public static com.google.android.material.card.MaterialCardView cardHidden;

    public static ImageView profileImage;
    public static ImageView imageView_hidden_1;
    public static ImageView imageView;
    public static ImageView imageView2;
    public static ImageButton editButton;
    public static EditText usernameEditText222;

    public static TextView nome1;
    public static TextView nome2;
    public static TextView nome3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        MainActivity.rimuoviSegnalazioneCard = view.findViewById(R.id.rimuoviSegnalazioneCard);
        MainActivity.rimuoviSegnalazioneDark = view.findViewById(R.id.rimuoviSegnalazioneDark);

        MainActivity.cardDaRimuovere0 = view.findViewById(R.id.base_cardview_hidden_1);
        MainActivity.cardDaRimuovere1 = view.findViewById(R.id.base_cardview1);
        MainActivity.cardDaRimuovere2 = view.findViewById(R.id.base_cardview2);

        MaterialTextView usernameEditText = view.findViewById(R.id.profileUsername);
        usernameEditText.setText(LoginActivity.UTENTE);
        usernameEditText222 = view.findViewById(R.id.UserName);
        usernameEditText222.setText(LoginActivity.UTENTE);
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

        editButton = view.findViewById(R.id.editButton);

        nome1 = view.findViewById(R.id.textView_hidden_1);
        nome2 = view.findViewById(R.id.textView);
        nome3 = view.findViewById(R.id.textView2);

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
            markerButtonHidden.setTag("default");
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
        //return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    //@Override
    //public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {}
}