package com.example.ex11024;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.Collections;

public class Settings extends AppCompatActivity {

    SharedPreferences sp;
    EditText userNameEt;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        userNameEt = findViewById(R.id.userNameEt);
        sp = getSharedPreferences("GAME_PREFS", MODE_PRIVATE);
    }

    public void resetScore(View view) {
        editor = sp.edit();
        editor.putInt("highscore", 0);
        editor.commit();
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
            Intent it = new Intent(this, New_Question_Tab.class);
            startActivity(it);
        }
        else if(id == R.id.menuSett)
        {
            userNameEt.setText("");
        }
        else if(id == R.id.menuCred)
        {
            Intent it = new Intent(this, Credits.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
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