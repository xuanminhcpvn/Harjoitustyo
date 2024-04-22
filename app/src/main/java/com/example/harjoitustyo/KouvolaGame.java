package com.example.harjoitustyo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class KouvolaGame extends Fragment {

    private TextView questionTextView;
    private Button yesButton;
    private Button noButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kouvola_game, container, false);

        questionTextView = view.findViewById(R.id.textView2);
        yesButton = view.findViewById(R.id.button);
        noButton = view.findViewById(R.id.button2);

        yesButton.setOnClickListener(v -> showCongratulationsMessage());
        noButton.setOnClickListener(v -> showWrongAnswerMessage());

        return view;
    }

    public void showCongratulationsMessage() {
        questionTextView.setText("Onnea! Vastasit oikein!");
        yesButton.setVisibility(View.GONE);
        noButton.setVisibility(View.GONE);
    }

    public void showWrongAnswerMessage() {
        questionTextView.setText("Väärä vastaus! Usko tai älä kouvostoliitossa on aina harmaata ja sataa.");
        yesButton.setVisibility(View.GONE);
        noButton.setVisibility(View.GONE);
    }
}