package com.tripathi.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_QUESTIONS = "questions";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SUBJECT = "subject";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_OPTION1 = "option1";
    private static final String COLUMN_OPTION2 = "option2";
    private static final String COLUMN_OPTION3 = "option3";
    private static final String COLUMN_OPTION4 = "option4";
    private static final String COLUMN_ANSWER = "answer";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SUBJECT + " TEXT, "
                + COLUMN_QUESTION + " TEXT, "
                + COLUMN_OPTION1 + " TEXT, "
                + COLUMN_OPTION2 + " TEXT, "
                + COLUMN_OPTION3 + " TEXT, "
                + COLUMN_OPTION4 + " TEXT, "
                + COLUMN_ANSWER + " TEXT)";
        db.execSQL(CREATE_TABLE);

        // Insert questions only if the table is empty
        insertDefaultQuestions(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    private void insertDefaultQuestions(SQLiteDatabase db) {
        if (getRowCount(db) == 0) {
            insertQuestions(db, "C++",
                    "Which of the following is a valid C++ data type?", "int", "float", "char", "all of the above", "all of the above",
                    "Which keyword is used to define a function?", "func", "define", "void", "class", "void",
                    "What is the output of 'cout << 2 + 2;'", "2", "4", "22", "Error", "4",
                    "Which symbol is used for comments in C++?", "//", "#", "/* */", "--", "//",
                    "Which of these is a looping structure?", "for", "if", "switch", "break", "for");

            insertQuestions(db, "Python",
                    "What is the correct file extension for Python files?", ".py", ".pt", ".pyt", ".pyth", ".py",
                    "Which function is used to output text in Python?", "print()", "echo()", "write()", "display()", "print()",
                    "Which of the following is not a Python data type?", "int", "str", "char", "list", "char",
                    "How do you insert comments in Python?", "//", "#", "/* */", "--", "#",
                    "What will 'len(\"Python\")' return?", "5", "6", "7", "Error", "6");

            insertQuestions(db, "JavaScript",
                    "Which keyword is used to declare variables in JavaScript?", "int", "var", "define", "let", "var",
                    "What will 'typeof null' return?", "null", "undefined", "object", "string", "object",
                    "Which operator is used to assign values?", "=", "==", "===", ":=", "=",
                    "How do you create a function in JavaScript?", "def myFunc()", "function myFunc()", "fun myFunc()", "create function myFunc()", "function myFunc()",
                    "Which method is used to add an element to an array?", "push()", "add()", "insert()", "append()", "push()");

            insertQuestions(db, "Swift",
                    "Which keyword is used to declare variables in Swift?", "var", "let", "define", "const", "var",
                    "What will 'let x = 5' do?", "Create a variable", "Create a constant", "Throw an error", "Nothing", "Create a constant",
                    "How do you print text in Swift?", "print()", "echo()", "System.out.println()", "cout <<", "print()",
                    "Which of these is an optional type in Swift?", "Int?", "String!", "Double?", "All of the above", "All of the above",
                    "Which statement is used for error handling?", "try-catch", "do-try-catch", "error handling", "catch-do", "do-try-catch");
            insertQuestions(db, "C",
                    "Which of the following is the correct syntax to declare a variable in C?", "int x;", "x int;", "declare x;", "variable x;", "int x;",
                    "Which symbol is used to indicate a single-line comment in C?", "//", "/*", "#", "--", "//",
                    "Which function is used to read formatted input from the user in C?", "scanf()", "input()", "read()", "gets()", "scanf()",
                    "Which header file is required for using printf() and scanf()?", "stdio.h", "conio.h", "stdlib.h", "math.h", "stdio.h",
                    "What will be the output of 'printf(\"%d\", 5/2);'?", "2", "2.5", "2.0", "Error", "2"
            );

            insertQuestions(db, "Terraform",
                    "Which language is Terraform written in?", "Go", "Python", "Java", "Ruby", "Go",
                    "What is the default file extension for Terraform configuration files?", ".tf", ".terraform", ".config", ".yaml", ".tf",
                    "Which Terraform command initializes a working directory?", "terraform init", "terraform start", "terraform apply", "terraform setup", "terraform init",
                    "How do you define a variable in Terraform?", "variable \"name\" {}", "var name {}", "define name {}", "let name {}", "variable \"name\" {}",
                    "Which backend storage type does Terraform NOT support?", "MySQL", "S3", "Azure Blob Storage", "Consul", "MySQL"
            );

            insertQuestions(db, "PHP",
                    "Which tag is used to start a PHP script?", "<?php", "<php>", "<?", "<script>", "<?php",
                    "Which function is used to output text in PHP?", "echo", "print", "write", "display", "echo",
                    "How do you declare a variable in PHP?", "$varname", "var varname;", "int varname;", "variable varname;", "$varname",
                    "Which operator is used for string concatenation in PHP?", ".", "+", "&", "++", ".",
                    "Which super global variable is used to collect form data?", "$_POST", "$_GET", "$_FORM", "$_REQUEST", "$_POST"
            );

            insertQuestions(db, "Kotlin",
                    "Which keyword is used to define a function in Kotlin?", "fun", "def", "function", "fn", "fun",
                    "Which symbol is used for string interpolation in Kotlin?", "$", "#", "@", "&", "$",
                    "What is the default visibility modifier in Kotlin?", "public", "private", "internal", "protected", "public",
                    "Which function is used to print text in Kotlin?", "println()", "print()", "System.out.println()", "display()", "println()",
                    "Which of the following is a valid way to declare a mutable variable in Kotlin?", "var x = 10", "let x = 10", "const x = 10", "define x = 10", "var x = 10"
            );
        }
    }

    private int getRowCount(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_QUESTIONS, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    private void insertQuestions(SQLiteDatabase db, String subject, String... questionsData) {
        for (int i = 0; i < questionsData.length; i += 6) { // Now reading 6 elements per question
            ContentValues values = new ContentValues();
            values.put(COLUMN_SUBJECT, subject);
            values.put(COLUMN_QUESTION, questionsData[i]);
            values.put(COLUMN_OPTION1, questionsData[i + 1]);
            values.put(COLUMN_OPTION2, questionsData[i + 2]);
            values.put(COLUMN_OPTION3, questionsData[i + 3]);
            values.put(COLUMN_OPTION4, questionsData[i + 4]);
            values.put(COLUMN_ANSWER, questionsData[i + 5]); // Explicitly setting the correct answer
            db.insert(TABLE_QUESTIONS, null, values);
        }
    }


    public List<QuestionModel> getQuestionsList(String subject) {
        List<QuestionModel> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUESTIONS + " WHERE subject = ?", new String[]{subject});

        if (cursor.moveToFirst()) {
            do {
                QuestionModel question = new QuestionModel(
                        cursor.getString(2), // Question
                        cursor.getString(3), // Option1
                        cursor.getString(4), // Option2
                        cursor.getString(5), // Option3
                        cursor.getString(6), // Option4
                        cursor.getString(7)  // Answer
                );
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return questionList;
    }
}
