package com.example.harjoitustyo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

public class FragmentQuiz extends Fragment {

    private TextView questionTextView;

    private Button option1Button;
    private Button option2Button;
    private Button option3Button;
    private Button option4Button;
    private DataList dataList;

    private String answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9, answer10;

    private ArrayList<DataObject> dataObjects;

    private ArrayList<String> correctAnswers = new ArrayList<>();
   private String[] questions = {
    "Mikä on kunnan nimi?",
    "Kuinka monta asukasta kunnassa on vuonna 2022?",
    "Mikä on kunnan työllisyysaste 2022?",
    "Mikä on kunnan työpaikkaomavaraisuus 2022?",
    "Millainen sää kunnassa on nyt",
    "Mikä on kunnan sään kosteusaste%?",
    "Mikä on kunnan sään tuulen nopeus?",
    "Mikä on sään lämpötila tänään?",
    "Kuinka suuri osuus työpaikoista kuuluu jalostukseen kunnassa?",
    "Kuinka suuri osuus työpaikoista kuuluu palveluihin kunnassa?",
};



    private String[][] incorrectAnswers = {
        { "Helsinki", "Vantaa", "Espoo" },
        { String.valueOf(getRandomInt()), String.valueOf(getRandomInt()), String.valueOf(getRandomInt()) },
        { String.valueOf(getRandomInt()), String.valueOf(getRandomInt()), String.valueOf(getRandomInt()) },
        { String.valueOf(getRandomPercentage()), String.valueOf(getRandomPercentage()), String.valueOf(getRandomPercentage()) },
        { "Snow", "Rain", "Cloudy" },
        { String.valueOf(getRandomPercentage()), String.valueOf(getRandomPercentage()), String.valueOf(getRandomPercentage()) },
        { String.valueOf(getRandomPercentage()), String.valueOf(getRandomPercentage()), String.valueOf(getRandomPercentage()) },
        { String.valueOf(getRandomInt()), String.valueOf(getRandomInt()), String.valueOf(getRandomInt()) },
        { String.valueOf(getRandomPercentage()), String.valueOf(getRandomPercentage()), String.valueOf(getRandomPercentage()) },
        { String.valueOf(getRandomPercentage()), String.valueOf(getRandomPercentage()), String.valueOf(getRandomPercentage()) }
    };

    private Random random;

    private int getRandomInt() {
        if (random == null) {
            random = new Random();
        }
        return random.nextInt(200001);
    }

    private double getRandomPercentage() {
        if (random == null) {
            random = new Random();
        }
        return Math.round(random.nextDouble() * 1000) / 10.0;
    }
    private int currentQuestionIndex = 0;
    private int score = 0;

    // 8/10 answers are generated from fetched data, Answers are purposedly different from wrong answers for testing
    private void generateAnswer(ArrayList<DataObject> dataObjects,TabActivity activity) {
        correctAnswers.clear();
        //
        correctAnswers.add(activity.sendLocation());
        correctAnswers.add(dataObjects.get(0).getPopData().replace("Population 2022: ", ""));
        correctAnswers.add(dataObjects.get(0).getEmploymentData().replace("Employment rate of year 2022:",""));
        correctAnswers.add(dataObjects.get(0).getJobS().replace("Job self-suffieciency rate of year 2022:",""));
        correctAnswers.add(dataObjects.get(0).getWeatherDescriptionData().replace("Sää nyt:",""));
        correctAnswers.add(dataObjects.get(0).getWeatherHumidityData().replace("Kosteus",""));
        correctAnswers.add(dataObjects.get(0).getWeatherWindData().replace("Tuulennopeus:",""));
        correctAnswers.add(dataObjects.get(0).getWeatherTempData());
        correctAnswers.add("Oikea vastaus");
        correctAnswers.add("Oikea vastaus");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        TabActivity activity = (TabActivity) getActivity();
        dataObjects = dataList.getInstance().getDatas();

        generateAnswer(dataObjects,activity);

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


    // This shuffledAnswers method I copied from James Llena
    private String[] shuffledAnswers() {
        String[] answers = new String[4];
        int correctPosition = random.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == correctPosition) {
                answers[i] = correctAnswers.get(currentQuestionIndex);
            } else {
                int incorrectIndex = random.nextInt(3);
                answers[i] = incorrectAnswers[currentQuestionIndex][incorrectIndex];
            }
        }
        return answers;
    }

    private void displayQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        String[] shuffledAnswers = shuffledAnswers();
        option1Button.setText(shuffledAnswers[0]);
        option2Button.setText(shuffledAnswers[1]);
        option3Button.setText(shuffledAnswers[2]);
        option4Button.setText(shuffledAnswers[3]);
    }

    private void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(correctAnswers.get(currentQuestionIndex))) {
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
