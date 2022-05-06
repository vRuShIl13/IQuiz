package com.example.quizit;
//this class is used to represent how a basic quiz will look like
//it is made up of question, the four choices and the answer.

public class QuizModel {
    private String question;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
    private String ans;

    //constructor that makes a typical quiz question , adds the fours choices and the answer to the question.
    public QuizModel(String question, String opt1, String opt2, String opt3, String opt4, String ans) {
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.ans = ans;
    }


    //getters and setters
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getOpt1() {
        return opt1;
    }
    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }
    public String getOpt2() {
        return opt2;
    }
    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }
    public String getOpt3() {
        return opt3;
    }
    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }
    public String getOpt4() {
        return opt4;
    }
    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }
    public String getAns() {
        return ans;
    }
    public void setAns(String ans) {
        this.ans = ans;
    }

}
