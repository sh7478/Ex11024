package com.example.ex11024;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private final String FILENAME = "questions.txt";
    String txt = "";
    ArrayList<Question> questions = new ArrayList<>();
    TextView scoreTv, questionTv;
    RadioButton answer1Rb, answer2Rb, answer3Rb, answer4Rb;
    RadioGroup rg;
    int questionNumber = 0;
    int score = 0;
    int highscore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreTv = findViewById(R.id.scoreTv);
        questionTv = findViewById(R.id.questionTv);
        answer1Rb = findViewById(R.id.answer1Rb);
        answer2Rb = findViewById(R.id.answer2Rb);
        answer3Rb = findViewById(R.id.answer3Rb);
        answer4Rb = findViewById(R.id.answer4Rb);
        rg = findViewById(R.id.rg);
        readFile();
        Collections.shuffle(questions);
        showQuestion();
    }

    private void showQuestion()
    {
        if(questionNumber < questions.size()) {
            Collections.shuffle(Arrays.asList(questions.get(questionNumber).getAnswers()));
            questionTv.setText(questions.get(questionNumber).getQuestion());
            answer1Rb.setText(cleanAnswer(questions.get(questionNumber).getAnswers()[0]));
            answer2Rb.setText(cleanAnswer(questions.get(questionNumber).getAnswers()[1]));
            answer3Rb.setText(cleanAnswer(questions.get(questionNumber).getAnswers()[2]));
            answer4Rb.setText(cleanAnswer(questions.get(questionNumber).getAnswers()[3]));
            rg.clearCheck();
        }
        else
        {
            questionTv.setText("Game over!");
        }
    }

    private String cleanAnswer(String answer)
    {
        if(answer.contains("(Correct Answer)"))
        {
            return answer.substring(0, answer.length()-16);
        }
        return answer;
    }

    private void readFile() {

        String question = "";
        String fileName = FILENAME.substring(0, FILENAME.length() - 4);
        int resourceId = this.getResources().getIdentifier(fileName,
                "raw", this.getPackageName());
        try {
        InputStream iS = this.getResources().openRawResource(resourceId);
        InputStreamReader iSR = new InputStreamReader(iS);
        BufferedReader bR = new BufferedReader(iSR);
        String line = "a";

        while(line != null) {
            line = bR.readLine();
            question = line;
            String[] answers = new String[4];
            for (int i = 0; i < 4; i++) {
                answers[i] = bR.readLine();
            }
            line = bR.readLine();
            if(question != null) {
                questions.add(new Question(question, answers, findRightQuestion(answers)));
            }
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int findRightQuestion(String[] answers) {
        for(int i = 0; i < answers.length; i++)
        {
            if(answers[i] != null && answers[i].contains("(Correct Answer)"))
            {
                return i;
            }
        }
        return -1;
    }

    public void submitAnswer(View view) {
        if(answer1Rb.isChecked() || answer2Rb.isChecked() || answer3Rb.isChecked() || answer4Rb.isChecked())
        {
            String answer = questions.get(questionNumber).getCorrectAnswer();
            answer = answer.substring(0, answer.length()-16);
            RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
            if(rb.getText().toString().equals(answer))
            {
                score += 100;
                if(score > highscore)
                {
                    highscore = score;
                }
                scoreTv.setText("Score: " + score + "\nHighscore: "  + highscore);
            }
            else
            {
                if(score > 50) {
                    score -= 50;
                }
                if(score > highscore)
                {
                    highscore = score;
                }
                scoreTv.setText("Score: " + score + "\nHighscore: "  + highscore);
            }
            questionNumber++;
            showQuestion();
        }
        else
        {
            AlertDialog.Builder adb;
            adb = new AlertDialog.Builder(this);
            adb.setTitle("Error");
            adb.setMessage("Please select an answer");
            AlertDialog ad = adb.create();
            ad.show();
        }
    }
}