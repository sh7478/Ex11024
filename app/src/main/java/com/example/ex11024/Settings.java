package com.example.ex11024;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Settings extends AppCompatActivity {

    SharedPreferences sp;
    EditText userNameEt;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        userNameEt = findViewById(R.id.userNameEt);
        sp = getSharedPreferences("GamePrefs", MODE_PRIVATE);
    }

    public void resetScore(View view) {
        editor = sp.edit();
        editor.putInt("highscore", 0);
        editor.commit();
    }

    public void changeUsername(View view) {
        if(userNameEt.getText().toString().length() > 0)
        {
            editor = sp.edit();
            editor.putString("username", userNameEt.getText().toString());
            editor.commit();
        }
        else
        {
            AlertDialog.Builder adb;
            adb = new AlertDialog.Builder(this);
            adb.setTitle("Error");
            adb.setMessage("Please enter a username before changing it");
            AlertDialog ad = adb.create();
            ad.show();
        }
    }
}