package com.example.ex11024;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;

public class New_Question_Tab extends AppCompatActivity {

    private final String FILENAME = "questions.txt";
    EditText eTQuestion, eTAnswer, eTOption2, eTOption3, eTOption4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question_tab);
        eTQuestion = findViewById(R.id.eTQuestion);
        eTAnswer = findViewById(R.id.eTAnswer);
        eTOption2 = findViewById(R.id.eTOption2);
        eTOption3 = findViewById(R.id.eTOption3);
        eTOption4 = findViewById(R.id.eTOption4);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.menuQuiz)
        {
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }
        else if(id == R.id.menuQuestion)
        {
            eTQuestion.setText("");
            eTAnswer.setText("");
            eTOption2.setText("");
            eTOption3.setText("");
            eTOption4.setText("");
        }
        else if(id == R.id.menuSett)
        {
            Intent it = new Intent(this, Settings.class);
            startActivity(it);
        }
        else if(id == R.id.menuCred)
        {
            Intent it = new Intent(this, Credits.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveQuestion(View view) {
        String question = eTQuestion.getText().toString();
        String answer = eTAnswer.getText().toString();
        String option2 = eTOption2.getText().toString();
        String option3 = eTOption3.getText().toString();
        String option4 = eTOption4.getText().toString();
        if(question != null && answer != null && option2 != null && option3 != null && option4 != null)
        {
            try{
                FileOutputStream fOS = openFileOutput(FILENAME, MODE_PRIVATE);
                OutputStreamWriter oSW = new OutputStreamWriter(fOS);
                BufferedWriter bW = new BufferedWriter(oSW);
                bW.write(formatQuestion(question, answer, option2, option3, option4));
                bW.close();
                oSW.close();
                fOS.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            AlertDialog.Builder adb;
            adb = new AlertDialog.Builder(this);
            adb.setTitle("Error");
            adb.setMessage("Please enter a question and all options");
            AlertDialog ad = adb.create();
            ad.show();
        }
    }

    private String formatQuestion(String question, String answer, String option2, String option3, String option4) {
        String formated = question + "\n";
        formated += answer + "\n";
        formated += option2 + "\n";
        formated += option3 + "\n";
        formated += option4 + "\n";
        formated += "@end@\n";
        return formated;
    }
}