package com.example.ex11024;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
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
            Intent it = new Intent(this, Settings.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
}