package com.tripathi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar = findViewById(R.id.toobar);
    }

    public void cplus(View view) {
        startActivity(new Intent(MainActivity.this, Question.class));
        finish();
    }

    public void clang(View view) {
        startActivity(new Intent(MainActivity.this, Question.class));
        finish();
    }
    public void js(View view) {
        startActivity(new Intent(MainActivity.this, Question.class));
        finish();
    }
    public void php(View view) {
        startActivity(new Intent(MainActivity.this, Question.class));
        finish();
    }

    public void python(View view) {
        startActivity(new Intent(MainActivity.this, Question.class));
        finish();
    }

    public void terraform(View view) {
        startActivity(new Intent(MainActivity.this, Question.class));
        finish();
    }

    public void kotlin(View view) {
        startActivity(new Intent(MainActivity.this, Question.class));
        finish();
    }

    public void swift(View view) {
        startActivity(new Intent(MainActivity.this, Question.class));
        finish();
    }
}