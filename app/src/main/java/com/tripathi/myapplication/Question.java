package com.tripathi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Question extends AppCompatActivity {
    private List<QuestionModel> questionList;
    private int index = 0;
    private int correct = 0, wrong = 0;
    private String subject;

    private TextView question, score, dis_num, quit;
    private RadioGroup answers;
    private RadioButton btn1, btn2, btn3, btn4;
    private Button nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        subject = getIntent().getStringExtra("subject"); // Get subject from MainActivity

        DatabaseHelper db = new DatabaseHelper(this);
        questionList = db.getQuestionsList(subject);

        if (questionList == null || questionList.isEmpty()) {
            Toast.makeText(this, "No questions available for " + subject, Toast.LENGTH_LONG).show();
            finish(); // Close this activity and return to MainActivity
            return;
        }

        // Initialize UI components
        answers = findViewById(R.id.answers);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        dis_num = findViewById(R.id.dis_num);
        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        quit = findViewById(R.id.quit);
        nextbtn = findViewById(R.id.nextbtn);

        loadQuestion(index);

        nextbtn.setOnClickListener(v -> {
            if (answers.getCheckedRadioButtonId() == -1) {
                Toast.makeText(Question.this, "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedBtn = findViewById(answers.getCheckedRadioButtonId());
            String selectedAnswer = selectedBtn.getText().toString();

            if (selectedAnswer.equals(questionList.get(index).getAnswer())) {
                correct++;
                Toast.makeText(Question.this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                wrong++;
                Toast.makeText(Question.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
            }

            index++;
            if (index < questionList.size()) {
                loadQuestion(index);
            } else {
                Intent intent = new Intent(Question.this, Result.class);
                intent.putExtra("subject", subject);
                intent.putExtra("attempted", index);
                intent.putExtra("correct", correct);
                intent.putExtra("wrong", wrong);
                startActivity(intent);
                finish();
            }
            answers.clearCheck();
        });

        quit.setOnClickListener(v -> {
            Intent intent = new Intent(Question.this, Result.class);
            intent.putExtra("subject", subject);
            intent.putExtra("attempted", index);
            intent.putExtra("correct", correct);
            intent.putExtra("wrong", wrong);
            startActivity(intent);
            finish();
        });
    }

    private void loadQuestion(int index) {
        if (index < questionList.size()) {
            QuestionModel q = questionList.get(index);
            question.setText(q.getQuestion());
            btn1.setText(q.getOption1());
            btn2.setText(q.getOption2());
            btn3.setText(q.getOption3());
            btn4.setText(q.getOption4());
            dis_num.setText((index + 1) + "/" + questionList.size());
            score.setText("Score: " + correct);
        }
    }
}
