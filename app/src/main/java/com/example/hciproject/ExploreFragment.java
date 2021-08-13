package com.example.hciproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;

public class ExploreFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        MaterialTextView usernameEditText = view.findViewById(R.id.nomeUtente1);
        usernameEditText.setText(LoginActivity.UTENTE);

        MaterialTextView usernameEditText2 = view.findViewById(R.id.nomeUtente2);
        usernameEditText2.setText(LoginActivity.UTENTE);

        //TODO

        return inflater.inflate(R.layout.fragment_explore, container, false);
    }
}