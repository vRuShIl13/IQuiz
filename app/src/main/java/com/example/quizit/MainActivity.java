package com.example.quizit;
import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner; // Import the Scanner class to read text files

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView question,questionNum;
    private Button opt1,opt2,opt3,opt4;
    private ArrayList<QuizModel> quizList;
    private ArrayList<Integer>doneQtns;
    Random random;
    int currenScore =0, questionsAttempted =1,currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.idQuestion);
        questionNum = findViewById(R.id.idNumQtn);
        opt1 = findViewById(R.id.idBtnOpt1);
        opt2 = findViewById(R.id.idBtnOpt2);
        opt3 = findViewById(R.id.idBtnOpt3);
        opt4 = findViewById(R.id.idBtnOpt4);
        quizList = new ArrayList<>();
        random= new Random();


        getQuestions(quizList,"questions.txt");
        doneQtns = new ArrayList<>();
        currentPosition= random.nextInt(quizList.size());
        doneQtns.add(currentPosition);

        setDatatoViews(currentPosition);

        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizList.get(currentPosition).getAns().trim().toLowerCase().equals(opt1.getText().toString().trim().toLowerCase())){
                    currenScore++;
                }
                questionsAttempted++;

                currentPosition = random.nextInt(quizList.size());
                while(doneQtns.contains(currentPosition) ) {
                    currentPosition = random.nextInt(quizList.size());
                }
                setDatatoViews(currentPosition);
                doneQtns.add(currentPosition);
            }
        });

        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizList.get(currentPosition).getAns().trim().toLowerCase().equals(opt2.getText().toString().trim().toLowerCase())){
                    currenScore++;
                }
                questionsAttempted++;
                currentPosition = random.nextInt(quizList.size());
                while(doneQtns.contains(currentPosition) ) {
                    currentPosition = random.nextInt(quizList.size());
                }
                doneQtns.add(currentPosition);
                setDatatoViews(currentPosition);
            }
        });
        opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizList.get(currentPosition).getAns().trim().toLowerCase().equals(opt3.getText().toString().trim().toLowerCase())){
                    currenScore++;
                }
                questionsAttempted++;
                currentPosition = random.nextInt(quizList.size());
                while(doneQtns.contains(currentPosition) ) {
                    currentPosition = random.nextInt(quizList.size());
                }
                doneQtns.add(currentPosition);
                setDatatoViews(currentPosition);
            }
        });
        opt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizList.get(currentPosition).getAns().trim().toLowerCase().equals(opt4.getText().toString().trim().toLowerCase())){
                    currenScore++;
                }
                questionsAttempted++;
                currentPosition = random.nextInt(quizList.size());
                while(doneQtns.contains(currentPosition) ) {
                    currentPosition = random.nextInt(quizList.size());
                }
                doneQtns.add(currentPosition);
                setDatatoViews(currentPosition);
            }
        });
    }

    private void setDatatoViews(int curr){
        if(questionsAttempted == quizList.size()){
            showResults();
        }else {
            questionNum.setText("Questions completed :" + questionsAttempted + "/" + quizList.size());
            question.setText(quizList.get(curr).getQuestion());
            opt1.setText(quizList.get(curr).getOpt1());
            opt2.setText(quizList.get(curr).getOpt2());
            opt3.setText(quizList.get(curr).getOpt3());
            opt4.setText(quizList.get(curr).getOpt4());
        }
    }

    //showws the results
    private void showResults(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.test_score_bottom,(LinearLayout)findViewById(R.id.idPageScore));
        TextView score = bottomSheetView.findViewById(R.id.idScore);
        Button res = bottomSheetView.findViewById(R.id.Restart);

        score.setText("Your score is: \n"+ currenScore + "/"+ quizList.size());

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doneQtns.clear();
                currentPosition = random.nextInt(quizList.size());
                doneQtns.add(currentPosition);
                setDatatoViews(currentPosition);
                questionsAttempted = 1;
                currenScore = 0;
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();


    }



    //adding the questions from a file of questions
    //in order to avoid errors. the question , choices and the answers are separated by commas.
    //example: ice location?,winnipeg,toronto,brandon,saskachewan,winnipeg
    private void getQuestions(ArrayList<QuizModel> quizList, String questions) {
        //quizList.add(new QuizModel("What is the name of the tallest building in the world?","Taj Mahal","Burj Khalifa","Statue of Unity","Summerland","Burj Khalifa"));

        String data;
        String [] blocks;
        String qtn;
        String o1;
        String o2;
        String o3;
        String o4;
        String ans;
        BufferedReader reader= null;
        try{
            reader= new BufferedReader(new InputStreamReader(getAssets().open("questions.txt"),"UTF-8"));
            String line;
            while((line = reader.readLine())!=null){
                blocks = line.split(",");
                qtn = blocks[0];
                o1 = blocks[1];
                o2 = blocks[2];
                o3 = blocks[3];
                o4 = blocks[4];
                ans = blocks[5];

                quizList.add(new QuizModel(qtn,o1,o2,o3,o4,ans));
            }
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally{
            if(reader!=null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}