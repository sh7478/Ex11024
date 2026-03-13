package com.example.ex11024;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private final String FILENAME = "questions.txt";
    String txt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readFile();
    }

    private void readFile() {

        String fileName = FILENAME.substring(0, FILENAME.length() - 4);
        int resourceId = this.getResources().getIdentifier(fileName,
                "raw", this.getPackageName());
        try {
        InputStream iS = this.getResources().openRawResource(resourceId);
        InputStreamReader iSR = new InputStreamReader(iS);
        BufferedReader bR = new BufferedReader(iSR);
        StringBuilder sB = new StringBuilder();
        String line = bR.readLine();
        while(line != null)
        {
            sB.append(line+'\n');
            line = bR.readLine();
        }
        txt = sB.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}