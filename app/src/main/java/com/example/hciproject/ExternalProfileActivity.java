package com.example.hciproject;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ExternalProfileActivity extends AppCompatActivity {

    public static ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_profile);

        /////////////////////////////////////////////////////////////

        scrollView = findViewById(R.id.scrollView2);

        ////////////////   1   /////////////////

        CardView cardView1 = findViewById(R.id.base_cardview1);
        ImageButton arrow1 = findViewById(R.id.arrow_button1);
        LinearLayout hiddenView1 = findViewById(R.id.hidden_view1);


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
        if(MainActivity.ID_BOTTONE==1){
            hiddenView1.setVisibility(View.VISIBLE);
            arrow1.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
        }

        /////////////////   2   ///////////////////

        CardView cardView2 = findViewById(R.id.base_cardview2);
        ImageButton arrow2 = findViewById(R.id.arrow_button2);
        LinearLayout hiddenView2 = findViewById(R.id.hidden_view2);

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
                    arrow2.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                }
            }
        });
        if(MainActivity.ID_BOTTONE==2){
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.scrollTo(0, 400);
                }
            });
            hiddenView2.setVisibility(View.VISIBLE);
            arrow2.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
        }

        /////////////////   3   ///////////////////

        CardView cardView3 = findViewById(R.id.base_cardview3);
        ImageButton arrow3 = findViewById(R.id.arrow_button3);
        LinearLayout hiddenView3 = findViewById(R.id.hidden_view3);

        arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenView3.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    //  by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    //TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    hiddenView3.setVisibility(View.GONE);
                    arrow3.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    hiddenView3.setVisibility(View.VISIBLE);
                    arrow3.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                }
            }
        });
        if(MainActivity.ID_BOTTONE==3){
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.scrollTo(0, 800);
                }
            });
            hiddenView3.setVisibility(View.VISIBLE);
            arrow3.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
        }

        /////////////////   4   ///////////////////

        CardView cardView4 = findViewById(R.id.base_cardview4);
        ImageButton arrow4 = findViewById(R.id.arrow_button4);
        LinearLayout hiddenView4 = findViewById(R.id.hidden_view4);

        arrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenView4.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    //  by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    //TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    hiddenView4.setVisibility(View.GONE);
                    arrow4.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(cardView4, new AutoTransition());
                    hiddenView4.setVisibility(View.VISIBLE);
                    arrow4.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                }
            }
        });
        if(MainActivity.ID_BOTTONE==4){
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.scrollTo(0, 1300);
                }
            });
            hiddenView4.setVisibility(View.VISIBLE);
            arrow4.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
        }

        /////////////////   5   ///////////////////

        CardView cardView5 = findViewById(R.id.base_cardview5);
        ImageButton arrow5 = findViewById(R.id.arrow_button5);
        LinearLayout hiddenView5 = findViewById(R.id.hidden_view5);

        arrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenView5.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    //  by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    //TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    hiddenView5.setVisibility(View.GONE);
                    arrow5.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                    hiddenView5.setVisibility(View.VISIBLE);
                    arrow5.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                }
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public void mostraSullaMappa(View view) {
        HomeFragment.caricamentoDaMarkerCategoria = view.getTag().toString();
        HomeFragment.caricamentoDaMarker = true;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goBack(View view) {
        finish();
    }

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
}