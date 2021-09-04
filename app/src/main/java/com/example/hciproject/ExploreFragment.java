package com.example.hciproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ExploreFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        TextView usernameEditText = view.findViewById(R.id.nomeUtente1);
        Log.e("AAAAAAAAAAAAA", usernameEditText.getText().toString());
        usernameEditText.setText("PadrePio");
        Log.e("AAAAAAAAAAAAA", usernameEditText.getText().toString());

        TextView usernameEditText2 = view.findViewById(R.id.nomeUtente2);
        usernameEditText2.setText(LoginActivity.UTENTE);

        return inflater.inflate(R.layout.fragment_explore, container, false);
    }
}