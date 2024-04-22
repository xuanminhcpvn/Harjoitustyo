package com.example.harjoitustyo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentQuiz extends Fragment {

    private TextView questionTextView;
    private Button option1Button;
    private Button option2Button;
    private Button option3Button;
    private Button option4Button;

    private String[] questions = {"Question 1", "Question 2", "Question 3"};
    private String[] correctAnswers = {"Correct Answer 1", "Correct Answer 2", "Correct Answer 3"};
    private String[][] incorrectAnswers = {{"Incorrect Answer 1", "Incorrect Answer 2", "Incorrect Answer 3"},
            {"Incorrect Answer 1", "Incorrect Answer 2", "Incorrect Answer 3"},
            {"Incorrect Answer 1", "Incorrect Answer 2", "Incorrect Answer 3"}};

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        questionTextView = view.findViewById(R.id.questionTextView);
        option1Button = view.findViewById(R.id.option1Button);
        option2Button = view.findViewById(R.id.option2Button);
        option3Button = view.findViewById(R.id.option3Button);
        option4Button = view.findViewById(R.id.option4Button);

        displayQuestion();

        option1Button.setOnClickListener(v -> checkAnswer(option1Button.getText().toString()));
        option2Button.setOnClickListener(v -> checkAnswer(option2Button.getText().toString()));
        option3Button.setOnClickListener(v -> checkAnswer(option3Button.getText().toString()));
        option4Button.setOnClickListener(v -> checkAnswer(option4Button.getText().toString()));

        return view;
    }

    private void displayQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        option1Button.setText(correctAnswers[currentQuestionIndex]);
        option2Button.setText(incorrectAnswers[currentQuestionIndex][0]);
        option3Button.setText(incorrectAnswers[currentQuestionIndex][1]);
        option4Button.setText(incorrectAnswers[currentQuestionIndex][2]);
    }

    private void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
            score++;
        }

        currentQuestionIndex++;

        if (currentQuestionIndex < questions.length) {
            displayQuestion();
        } else {
            showFinalScore();
        }
    }

    private void showFinalScore() {
        // Display the final score to the user
        String finalScoreMessage = "Quiz completed!\nYour score: " + score + " out of " + questions.length;
        questionTextView.setText(finalScoreMessage);
        option1Button.setVisibility(View.GONE);
        option2Button.setVisibility(View.GONE);
        option3Button.setVisibility(View.GONE);
        option4Button.setVisibility(View.GONE);
    }
}
