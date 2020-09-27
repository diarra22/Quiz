package com.cesi.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity ;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cesi.model.Question;
import com.cesi.model.QuestionBank;

import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity  {

    private QuestionBank questionBank;
    private Question question;
    private Question indexquestion;
    private int score;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    private TextView mtext;
    private Button[] buttonTab = new Button[4];
    private Question Currentquestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        questionBank = this.generateQuestions();
        buttonTab[0] = findViewById(R.id.button2);
        buttonTab[1] = findViewById(R.id.button3);
        buttonTab[2]= findViewById(R.id.button4);
        buttonTab[3] = findViewById(R.id.button5);
        mtext = findViewById(R.id.textView);
        this.displayQuestion();

    }
    public QuestionBank generateQuestions(){

        Question question1 = new Question("Who is the creator of Android?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),0, "Souvient toi en 2018 etc..");

        Question question2 = new Question("When did the first man land on the moon?",
               Arrays.asList("1958", "1962", "1967", "1969"),1,"la lune oh attention");


        Question question3 = new Question("What is the house number of The Simpsons?",
          Arrays.asList("42", "101", "666", "742"),2,"simpson 742 mon pote");

        Question question4 = new Question("What is Spring boot ?",
                Arrays.asList("It's a framework ", "oh i dont' no", "lol", "php"),0,"Spring Boot is a framework that facilitates the development of Spring- based");
        return new QuestionBank(Arrays.asList(question1,question2,question3,question4));
    }

    private void finJeu(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Parfait !")
        .setMessage("Votre score est " + score)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent = new Intent();
                intent.putExtra(BUNDLE_EXTRA_SCORE, score);
                setResult(RESULT_OK, intent);
                finish();
            }
        })
        .create()
        .show();
    }
    private void displayQuestion(){
    Currentquestion = questionBank.getQuestion();
    mtext.setText(Currentquestion.getQuestion());

   for(int i = 0  ; i<buttonTab.length; i++){
    buttonTab[i].setText(Currentquestion.getChoiceList().get(i));
   }
    }

    public void onAnswer(View view) {
        int rep = Integer.parseInt((String) view.getTag());
        if (rep == Currentquestion.getAnswer()) {
            //bonne reponse
            Toast.makeText(this, "Correct !", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            String text1 = "Incorrect \n" + "La bonne reponse etait :"  + Currentquestion.getChoiceList().get(Currentquestion.getAnswer()) +" Is a goog Answer\n" + Currentquestion.getBonneReponse();
            Toast.makeText(this, text1, Toast.LENGTH_SHORT).show();


        }

        if (questionBank.remainingQuestion() > 0) {
            displayQuestion();
        }else{
            //jeu terminée
            finJeu();
        }

    }



}