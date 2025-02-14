package com.tripathi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class Result extends AppCompatActivity {
    TextView correct, wrong, total, subjectName;
    CircularProgressBar progress;
    TextView result;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        correct = findViewById(R.id.correct);
        wrong = findViewById(R.id.wrong);
        total = findViewById(R.id.total);
        progress = findViewById(R.id.progress);
        result = findViewById(R.id.result);
        home = findViewById(R.id.home);
        subjectName = findViewById(R.id.subject_name); // Add a TextView in your XML for this

        // Get data from intent
        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        int attempted = intent.getIntExtra("attempted", 0);
        int correct1 = intent.getIntExtra("correct", 0);
        int wrong1 = intent.getIntExtra("wrong", 0);

        // Set data to UI elements
        subjectName.setText("Subject: " + subject);
        correct.setText(String.valueOf(correct1));
        wrong.setText(String.valueOf(wrong1));
        total.setText(String.valueOf(attempted));
        result.setText(correct1 + "");
        progress.setProgress((float) correct1 / attempted * 100);

        // Go back to home
        home.setOnClickListener(v -> {
            Intent homeIntent = new Intent(Result.this, MainActivity.class);
            startActivity(homeIntent);
            finishAffinity();
        });
    }
}
