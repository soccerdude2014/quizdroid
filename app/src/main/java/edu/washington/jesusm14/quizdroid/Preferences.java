package edu.washington.jesusm14.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Preferences extends Activity {
    private Button button;
    private EditText urlText;
    private EditText intervalText;
    private QuizApp app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        button = (Button) findViewById(R.id.button2);
        urlText = (EditText) findViewById(R.id.editText);
        intervalText = (EditText) findViewById(R.id.editText2);
        app = QuizApp.getQuizApp();
        urlText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                app.setUrl(urlText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Fires right after the text has changed
                app.setUrl(urlText.getText().toString());
            }
        });
        intervalText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                app.setInterval(Integer.parseInt(intervalText.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Fires right after the text has changed
                app.setInterval(Integer.parseInt(intervalText.getText().toString()));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Preferences.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}
