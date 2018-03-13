package com.skimax.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    ConstraintLayout gameConstraintLayout;


    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(view.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100, 1000){


            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l / 1000) + "s");

            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("your score is : " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }
        }.start();



    }


    public void generateQuestion (){

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);


        sumTextView.setText(Integer.toString(a) + " * " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        int incorrectAnswers;

        for (int i = 0; i < 4; i++){

            if (i == locationOfCorrectAnswer) {

                answers.add(a * b);

            } else {

                incorrectAnswers = rand.nextInt(41);

                while ( incorrectAnswers == a * b){

                    incorrectAnswers = rand.nextInt(41);


                }

                answers.add(incorrectAnswers);

            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));






    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(ConstraintLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));

    }


    public void chooseAnswer(View view){

        //Log.i("Tag", (String) view.getTag());

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            resultTextView.setText("Correct!");

        } else {

            resultTextView.setText("Wrong!");

        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));
        generateQuestion();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView =(TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);


        gameConstraintLayout = (ConstraintLayout)findViewById(R.id.gameConstraintLayout);







    }
}
