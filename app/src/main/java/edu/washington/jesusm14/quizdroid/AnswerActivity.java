package edu.washington.jesusm14.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerActivity extends Activity {
    private Button submit;
    private Intent prev;
    private String[] correct; //
    private int num; //
    private String[] questions; //
    private Bundle bundle;
    private String[][] answers; //
    private TextView givenAnswer;
    private TextView correctAnswer;
    private TextView numberCorrect;
    private int[] rightWrong; //
    private String given;
    private Intent next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        prev = getIntent();
        bundle = prev.getExtras();
        num = prev.getIntExtra("questionnumber", 0);
        correct = prev.getStringArrayExtra("correct");
        questions = prev.getStringArrayExtra("questions");
        answers = (String[][]) bundle.getSerializable("answers");
        rightWrong = prev.getIntArrayExtra("rightwrong");
        given = prev.getStringExtra("given");
        submit = (Button) findViewById(R.id.finishornext);
        givenAnswer = (TextView) findViewById(R.id.answergiven);
        correctAnswer = (TextView) findViewById(R.id.correctanswer);
        numberCorrect = (TextView) findViewById(R.id.numbercorrect);
        givenAnswer.setText("Your answer" + given);
        correctAnswer.setText("The Correct Answer" + correct[num]);
        numberCorrect.setText("You have " + rightWrong[0] + " out of " + rightWrong[1] + " correct!");
        submit = (Button) findViewById(R.id.finishornext);
        num++;

        if(num == questions.length) {
            submit.setText("Finish");
            next = new Intent(AnswerActivity.this, MainActivity.class);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                startActivity(next);
                }
            });
        } else {
            submit.setText("Next");
            next = new Intent(AnswerActivity.this, Quiz.class);
            next.putExtra("questions", questions);
            next.putExtra("questionnumber", num);
            next.putExtra("correct", correct);
            next.putExtra("rightwrong", rightWrong);
            next.putExtras(bundle);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(next);
                }
            });
        }

    }

}
