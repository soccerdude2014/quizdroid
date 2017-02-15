package edu.washington.jesusm14.quizdroid;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends Activity implements TopicFragment.OnFragmentInteractionListener, QuestionFragment.OnFragmentInteractionListener,
                                                    AnswerFragment.OnFragmentInteractionListener {
    private Intent prev;
    private Question[] questions;
    private String[][] answers;
    private String[] correct;
    private String prevDescription;
    private int questionNumber;
    private Bundle bundle;
    private TopicFragment topicFragment;
    private QuestionFragment questionFragment;
    private AnswerFragment answerFragment;
    private Fragment current;
    private boolean correctAnswer;
    private boolean checkedAnswer;
    private int score;
    private int givenAnswerInt;
    private Button button;
    private QuizApp app;
    private Topic topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        app = QuizApp.getQuizApp();
        topic = app.getCurrentTopic();
        questionNumber = 0;
        prev = getIntent();
        questions = topic.getCollection();
        //prevDescription = prev.getStringExtra("description");
        //correct = prev.getStringArrayExtra("correct");
        //bundle = prev.getExtras();


        button = (Button) findViewById(R.id.mainbutton);
        button.setText("Begin");
        current = TopicFragment.newInstance(topic.getLongDescription(), topic.getCollection().length);
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, current);
        tx.commit();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText().toString().equals("Begin")) {
                    current = QuestionFragment.newInstance(questions[questionNumber].getQuestion(), questions[questionNumber].getAnswers(), questions[questionNumber].getCorrectAnswer());
                    button.setText("Submit");
                } else if(button.getText().toString().equals("Submit")) {
                    if(checkedAnswer) {
                        if(correctAnswer) {
                            score++;
                        }
                        current = AnswerFragment.newInstance(questions[questionNumber].getAnswers()[givenAnswerInt], questions[questionNumber].getAnswers()[questions[questionNumber].getCorrectAnswer()], score, questions.length);
                        questionNumber++;
                        if(questionNumber == questions.length) {
                            button.setText("Finish");
                        } else {
                            button.setText("Next");
                        }
                    }
                } else if(button.getText().toString().equals("Next")) {
                    current = QuestionFragment.newInstance(questions[questionNumber].getQuestion(), questions[questionNumber].getAnswers(), questions[questionNumber].getCorrectAnswer());
                    button.setText("Submit");
                } else {
                    Intent i = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(i);
                }
                FragmentTransaction tx = getFragmentManager().beginTransaction();
                tx.replace(R.id.fragment_placeholder, current);
                tx.commit();
            }
        });

       /* FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, current);
        tx.commit();*/



    }
    @Override
    public void onFragmentInteraction(Uri uri){

    }

    public void isAnswerCorrect(boolean data) {
        this.correctAnswer = data;
    }

    public void isAnswerChecked(boolean data) {
        this.checkedAnswer = data;
    }

    public void givenAnswer(int data) {
        this.givenAnswerInt = data;
    }
}
