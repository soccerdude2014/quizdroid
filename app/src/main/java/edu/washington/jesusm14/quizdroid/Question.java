package edu.washington.jesusm14.quizdroid;

/**
 * Created by Jesus Moreno on 2/12/2017.
 */

public class Question {
    private String question;
    private String[] answers;
    private int correctAnswer;

    public Question (String question, String[] answer, int correctAnswer) {
        this.question = question;
        this.answers = answer;
        this.correctAnswer = correctAnswer;
    }

    public Question() {

    }
    public String getQuestion() {
        return this.question;
    }

    public String[] getAnswers() {
        return this.answers;
    }

    public int getCorrectAnswer() {
        return this.correctAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }



}
