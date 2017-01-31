package edu.washington.jesusm14.quizdroid;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.view.View;
        import android.widget.Button;
        import android.widget.RadioButton;
        import android.widget.TextView;

        import org.w3c.dom.Text;

/**
 * Created by Jesus Moreno on 1/29/2017.
 */

public class QuestionActivity extends Activity {
    private Button button;
    private Intent prev;
    private String[] questions;
    private String[][] answers;
    private String[] correct;
    private String prevDescription;
    private int questionNumber;
    private TextView description;
    private TextView numQuestions;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_overview);

        button = (Button) findViewById(R.id.beginbutton);
        prev = getIntent();
        questions = prev.getStringArrayExtra("questions");
        prevDescription = prev.getStringExtra("description");
        correct = prev.getStringArrayExtra("correct");
        questionNumber = prev.getIntExtra("questionnumber", 0);
        bundle = prev.getExtras();
        answers = (String[][]) bundle.getSerializable("answers");

        description = (TextView) findViewById(R.id.descriptiontext);
        numQuestions = (TextView) findViewById(R.id.numberofqs);
        numQuestions.setText("Number of Questions: " + questions.length);
        description.setText(prevDescription);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent next = new Intent(QuestionActivity.this, Quiz.class);
                next.putExtra("questions", questions);
                next.putExtra("questionnumber", questionNumber);
                next.putExtra("correct", correct);
                next.putExtras(bundle);
                startActivity(next);
            }
        });
    }
}
