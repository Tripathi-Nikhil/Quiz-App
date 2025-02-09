package com.tripathi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Question extends AppCompatActivity {
    String[] questions = {
            "What is the correct syntax to declare a pointer in C++?",
            "Which of the following is used to define a constant in C++?",
            "Which of the following is used to allocate memory dynamically in C++?",
            "Which of the following is not a valid C++ keyword?",
            "What is the size of int data type in C++ (on most modern systems)?"};
    String[] options = {
            "int ptr;", "int *ptr;", "ptr int;", "int &ptr;",
            "const", "constant", "define", "static",
            "malloc", "alloc", "new", "create",
            "class", "virtual", "override", "function",
            "2 bytes", "4 bytes", "8 bytes", "Depends on the compiler"
    };
    String[] Answers = {"int *ptr;",
            "const" ,
            "new",
            "function",
            "Depends on the compiler"};
    int index = 0;
    public static int marks = 0;
    public static int correct = 0;
    int wrong = 0;
    RadioGroup answers;
    RadioButton btn1, btn2, btn3, btn4;
    TextView dis_num, question, score, quit;
    Button nextbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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

        question.setText(questions[index]);
        btn1.setText(options[1]);
        btn2.setText(options[2]);
        btn3.setText(options[3]);
        btn4.setText(options[4]);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answers.getCheckedRadioButtonId() == -1){
                    Toast.makeText(Question.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton answer = findViewById(answers.getCheckedRadioButtonId());
                String selected = answer.getText().toString();
                if(selected.equals(Answers[index])){
                    correct++;
                    Toast.makeText(Question.this, "Correct Option", Toast.LENGTH_SHORT).show();
                }
                else{
                    wrong++;
                    Toast.makeText(Question.this, "Padhai Likhai Karo", Toast.LENGTH_SHORT).show();
                }
                index++;
                if(score!=null){
                    score.setText(""+correct);
                    if(index<questions.length){
                        question.setText(questions[index]);
                        btn1.setText(options[index*4]);
                        btn2.setText(options[index*4+1]);
                        btn3.setText(options[index*4+2]);
                        btn4.setText(options[index*4+3]);
                        dis_num.setText(index+1+"/"+questions.length);
                    }else{
                        Toast.makeText(Question.this, "Quiz Completed", Toast.LENGTH_SHORT).show();
                        marks=correct;
                        Intent intent = new Intent(Question.this, Result.class);
                        intent.putExtra("attempted", index);
                        intent.putExtra("correct", correct);
                        intent.putExtra("wrong", wrong);
                        startActivity(intent);
                        finish();
                    }
                    answers.clearCheck();
                }
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Question.this, Result.class);
                intent.putExtra("attempted", index);
                intent.putExtra("correct", correct);
                intent.putExtra("wrong", wrong);
                startActivity(intent);
                finish();
            }
        });
    }
}