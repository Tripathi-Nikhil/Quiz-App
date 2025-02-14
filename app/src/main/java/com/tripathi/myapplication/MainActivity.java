package com.tripathi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;
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

    public void startQuiz(String subject) {
        DatabaseHelper db = new DatabaseHelper(this);
        List<QuestionModel> questions = db.getQuestionsList(subject);

        if (questions == null || questions.isEmpty()) {
            Toast.makeText(this, "No questions available for " + subject, Toast.LENGTH_SHORT).show();
            return; // Stay on MainActivity
        }

        Intent intent = new Intent(MainActivity.this, Question.class);
        intent.putExtra("subject", subject);
        startActivity(intent);
    }

    public void cplus(View view) {
        startQuiz("C++");
    }

    public void clang(View view) {
        startQuiz("C");
    }

    public void js(View view) {
        startQuiz("JavaScript");
    }

    public void php(View view) {
        startQuiz("PHP");
    }

    public void python(View view) {
        startQuiz("Python");
    }

    public void terraform(View view) {
        startQuiz("Terraform");
    }

    public void kotlin(View view) {
        startQuiz("Kotlin");
    }

    public void swift(View view) {
        startQuiz("Swift");
    }
}
