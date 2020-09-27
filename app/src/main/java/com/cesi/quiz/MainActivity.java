package com.cesi.quiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cesi.model.User;

public class MainActivity extends AppCompatActivity {


    private Button mPlaybutton;
    private EditText mEditText;
    public final int ACTIVITY_CODE = 109;

    private SharedPreferences mPreferences;
    private User utilisateur;
    public String firstname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlaybutton = findViewById(R.id.button);
        mPlaybutton.setEnabled(false);
        mEditText = findViewById(R.id.editFirstName);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // code ici
                // if(s.toString().length() !=0){
                //  mPlaybutton.setEnabled(true);
                //  }
                mPlaybutton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPreferences = getPreferences(MODE_PRIVATE);
        String firstname = mPreferences.getString("firstname", null);

        if (firstname != null) {
            mEditText.setText(firstname);
            int score = mPreferences.getInt("score", -1);
            if (score >= 0) {
                TextView text = findViewById(R.id.textView);
                text.setText("De retour" + firstname + " Score de " + score);
            }
        }
    }

    public void onPlay(View view){
        utilisateur = new User(mEditText.getText().toString());
        mPreferences.edit().putString(firstname, utilisateur.getFirstName()).apply();
        Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
        startActivityForResult(gameActivity,ACTIVITY_CODE);
       // startActivity(gameActivity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_CODE && resultCode == RESULT_OK) {
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            utilisateur.setScore(score);
            Log.i("main", "Le score est " + score);
            TextView text = findViewById(R.id.textView);
            text.setText(getText(R.string.BienvenueTexte).toString() + " Score de " + score);
            mPreferences.edit().putInt(String.valueOf(score), utilisateur.getScore()).apply();
        }

    }


    //

}