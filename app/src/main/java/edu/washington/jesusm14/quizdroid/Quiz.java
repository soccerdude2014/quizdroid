package edu.washington.jesusm14.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Jesus Moreno on 1/29/2017.
 */

public class Quiz extends Activity {
    private RadioGroup choices;
    private RadioButton selection;
    private Button submit;
    private Intent prev;
    private String[] correct;
    private int num;
    private String[] questions;
    private Bundle bundle;
    private String[][] answers;
    private TextView question;
    private int[] rightWrong;
    private String selectedText;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_form);
        prev = getIntent();
        bundle = prev.getExtras();
        num = prev.getIntExtra("questionnumber", 0);
        correct = prev.getStringArrayExtra("correct");
        questions = prev.getStringArrayExtra("questions");
        answers = (String[][]) bundle.getSerializable("answers");
        RadioButton option1 = (RadioButton) findViewById(R.id.firstoption);
        RadioButton option2 = (RadioButton) findViewById(R.id.secondoption);
        RadioButton option3 = (RadioButton) findViewById(R.id.thirdoption);
        RadioButton option4 = (RadioButton) findViewById(R.id.fourthoption);
        option1.setText(answers[num][0]);
        option2.setText(answers[num][1]);
        option3.setText(answers[num][2]);
        option4.setText(answers[num][3]);
        question = (TextView) findViewById(R.id.question);
        question.setText(questions[num]);
        rightWrong = new int[]{0, questions.length};


        choices = (RadioGroup) findViewById(R.id.choices);
        submit = (Button) findViewById(R.id.submit_button);
        choices.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int id) {
                int selectedInt = choices.getCheckedRadioButtonId();
                selection = (RadioButton) findViewById(selectedInt);
                selectedText = (String) selection.getText();
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selectedText.equals(correct[num])) {
                            rightWrong[0]++;
                        }
                        Intent next = new Intent(Quiz.this, AnswerActivity.class);
                        next.putExtra("questions", questions);
                        next.putExtra("questionnumber", num);
                        next.putExtra("correct", correct);
                        next.putExtra("rightwrong", rightWrong);
                        next.putExtras(bundle);
                        next.putExtra("given", selection.getText());
                        startActivity(next);
                    }
                });
            }
        });






        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Quiz Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        AppIndex.AppIndexApi.start(mClient, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mClient, getIndexApiAction());
        mClient.disconnect();
    }
}
