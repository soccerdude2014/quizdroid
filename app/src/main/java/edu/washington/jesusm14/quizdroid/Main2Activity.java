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
    private String[] questions;
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
    private String givenAnswerString;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        questionNumber = 0;
        prev = getIntent();
        questions = prev.getStringArrayExtra("questions");
        prevDescription = prev.getStringExtra("description");
        correct = prev.getStringArrayExtra("correct");
        bundle = prev.getExtras();
        answers = (String[][]) bundle.getSerializable("answers");

        button = (Button) findViewById(R.id.mainbutton);
        button.setText("Begin");
        current = TopicFragment.newInstance(prevDescription, questions.length);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText().toString().equals("Begin")) {
                    current = QuestionFragment.newInstance(questions[questionNumber], answers[questionNumber], correct[questionNumber]);
                    button.setText("Submit");
                } else if(button.getText().toString().equals("Submit")) {
                    if(checkedAnswer) {
                        if(correctAnswer) {
                            score++;
                        }
                        current = AnswerFragment.newInstance(givenAnswerString, correct[questionNumber], score, questions.length);
                        questionNumber++;
                        if(questionNumber == questions.length) {
                            button.setText("Finish");
                        } else {
                            button.setText("Next");
                        }
                    }
                } else if(button.getText().toString().equals("Next")) {
                    current = QuestionFragment.newInstance(questions[questionNumber], answers[questionNumber], correct[questionNumber]);
                    button.setText("Submit");
                } else {
                    Intent i = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, current);
        tx.commit();



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

    public void givenAnswer(String data) {
        this.givenAnswerString = data;
    }
}
