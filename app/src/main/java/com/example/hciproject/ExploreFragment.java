package com.example.hciproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ExploreFragment extends Fragment {

    public static ScrollView scrollView2;
    public static int scrollY = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainActivity.ID_BOTTONE  = 0;

        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        //TODO ?
        TextView usernameEditText = view.findViewById(R.id.nomeUtente1);

        TextView usernameEditText2 = view.findViewById(R.id.nomeUtente2);
        usernameEditText2.setText(LoginActivity.UTENTE);

        //smoothScroller.setTargetPosition(MainActivity.EXPLORE_POSITION);

        return inflater.inflate(R.layout.fragment_explore, container, false);
    };
}