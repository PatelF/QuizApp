package com.fenilpatel00.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    String question_Array[] = {"2 x 6", "5 x 6", "11 x 5", "12 x 9", "7 x 3", "8 x 7", "9 x 8", "10 x 5", "12 x 6", "5 x 9"};
    String answer_Array[] = {"12", "30", "55", "108", "21", "56", "72", "50", "72", "45"};

    private TextView question_Number;
    private TextView timer;
    private TextView question_Space;
    private TextView score;

    private Button option_Button1;
    private Button option_Button2;
    private Button option_Button3;
    private Button option_Button4;
    private Button continue_Button;

    private int count = 1;
    private int scoreCount = 0;
    private int question_Index = 0;
    private int got_AnsNum;
    private boolean clicked_Button = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        question_Number = findViewById(R.id.question_number);
        timer = findViewById(R.id.timer);
        question_Space = findViewById(R.id.question_Space);
        score = findViewById(R.id.scoreText);

        option_Button1 = findViewById(R.id.option_Button1);
        option_Button2 = findViewById(R.id.option_Button2);
        option_Button3 = findViewById(R.id.option_Button3);
        option_Button4 = findViewById(R.id.option_Button4);

        continue_Button  = findViewById(R.id.continue_Button);

        getQuestion();

        option_Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(1);
            }
        });

        option_Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(2);
            }
        });

        option_Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(3);
            }
        });

        option_Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(4);
            }
        });

        continue_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(clicked_Button == true){
                    nextQuestion();
                }
            }
        });
    }

    public void getQuestion(){

        String questionNumber_Text = "Question " + String.valueOf(count);
        question_Number.setText(questionNumber_Text);

        String got_Question = question_Array[question_Index];

        String questionSpace_Text = "What is " + got_Question + "?";
        question_Space.setText(questionSpace_Text);

        Random rand = new Random();
        got_AnsNum = rand.nextInt(4) + 1;

        getAnswers(got_AnsNum,question_Index);
    }

    public void getAnswers(int got_Num, int index){

        Random rand = new Random();

        String correct_Answer = answer_Array[index];
        int correct_Answer_Int = Integer.parseInt(correct_Answer);

        int wrongAnswers [] = new int [3];

        int i = 0;

        while(i < 3){
            int next_Num = rand.nextInt(100);

            if (next_Num != correct_Answer_Int){
                wrongAnswers[i] = next_Num;
                i++;
            }
        }

        if (got_Num == 1){
            option_Button1.setText(correct_Answer);
            option_Button2.setText(wrongAnswers[0] + "");
            option_Button3.setText(wrongAnswers[1] + "");
            option_Button4.setText(wrongAnswers[2] + "");

        }
        else if (got_Num == 2){
            option_Button1.setText(wrongAnswers[0] + "");
            option_Button2.setText(correct_Answer + "");
            option_Button3.setText(wrongAnswers[1] + "");
            option_Button4.setText(wrongAnswers[2] + "");
        }
        else if (got_Num == 3){
            option_Button1.setText(wrongAnswers[0] + "");
            option_Button2.setText(wrongAnswers[1] + "");
            option_Button3.setText(correct_Answer);
            option_Button4.setText(wrongAnswers[2] + "");
        }
        else{
            option_Button1.setText(wrongAnswers[0] + "");
            option_Button2.setText(wrongAnswers[1] + "");
            option_Button3.setText(wrongAnswers[2] + "");
            option_Button4.setText(correct_Answer);
        }
    }

    public void checkAnswer(int buttonPressed){

        colorButtons();

        if(buttonPressed == got_AnsNum && clicked_Button == false){
            question_Space.setText("Correct!");
            scoreCount++;
            score.setText("Score: " + scoreCount + "");
        }
        else{
            question_Space.setText("Incorrect!");
        }
        clicked_Button = true;

    }

    public void colorButtons(){

        if(got_AnsNum == 1){
            option_Button1.setTextColor(Color.GREEN);
            option_Button2.setTextColor(Color.RED);
            option_Button3.setTextColor(Color.RED);
            option_Button4.setTextColor(Color.RED);
        }
        if(got_AnsNum == 2){
            option_Button1.setTextColor(Color.RED);
            option_Button2.setTextColor(Color.GREEN);
            option_Button3.setTextColor(Color.RED);
            option_Button4.setTextColor(Color.RED);

        }
        if(got_AnsNum == 3){
            option_Button1.setTextColor(Color.RED);
            option_Button2.setTextColor(Color.RED);
            option_Button3.setTextColor(Color.GREEN);
            option_Button4.setTextColor(Color.RED);

        }
        if(got_AnsNum == 4){
            option_Button1.setTextColor(Color.RED);
            option_Button2.setTextColor(Color.RED);
            option_Button3.setTextColor(Color.RED);
            option_Button4.setTextColor(Color.GREEN);

        }
    }

    public void nextQuestion(){
        //Increment to next index in array and next question
        question_Index++;
        count++;
        clicked_Button = false;

        if(count <= 10){
            getQuestion();

            option_Button1.setTextColor(Color.BLACK);
            option_Button2.setTextColor(Color.BLACK);
            option_Button3.setTextColor(Color.BLACK);
            option_Button4.setTextColor(Color.BLACK);

            if (count == 10){
                continue_Button.setText("Finish");
            }
        }
        else{

            Intent intent = new Intent(QuizActivity.this, StartingScreenActivity.class);
            startActivity(intent);
        }
    }
}
