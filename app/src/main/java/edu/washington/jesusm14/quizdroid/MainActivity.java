package edu.washington.jesusm14.quizdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity {

    private String[] topics = new String[]{
            "Math", "Physics", "Marvel Super Heros", "Soccer"
    };
    private String[] mathQuestions;
    private String[][] mathAnswers;
    private String[] mathCorrectAnswers;
    private String[] physicsQuestions;
    private String[][] physicsAnswers;
    private String[] physicsCorrectAnswers;
    private String[] marvelQuestions;
    private String[][] marvelAnswers;
    private String[] marvelCorrectAnswers;
    private String[] soccerQuestions;
    private String[][] soccerAnswers;
    private String[] soccerCorrectAnswers;
    private ListView listView;
    private Bundle bundle;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mathQuestions = new String[]{
                getString(R.string.math_q1)
        };

        mathAnswers = new String[][]{
                {getString(R.string.math_q1_a1), getString(R.string.math_q1_a2),
                        getString(R.string.math_q1_a3c), getString(R.string.math_q1_a4)}
        };

        mathCorrectAnswers = new String[]{
                getString(R.string.math_q1_a3c)
        };

        physicsQuestions = new String[]{
                getString(R.string.physics_q1)
        };

        physicsAnswers = new String[][]{
                {getString(R.string.physics_q1_a1), getString(R.string.physics_q1_a2c),
                        getString(R.string.physics_q1_a3), getString(R.string.physics_q1_a4)}
        };

        physicsCorrectAnswers = new String[]{
                getString(R.string.physics_q1_a2c)
        };

        marvelQuestions = new String[]{
                getString(R.string.marvel_q1)
        };
        marvelAnswers = new String[][]{
                {getString(R.string.marvel_q1_a1), getString(R.string.marvel_q1_a2c),
                        getString(R.string.marvel_q1_a3), getString(R.string.marvel_q1_a4)}
        };

        marvelCorrectAnswers = new String[]{
                getString(R.string.physics_q1_a2c)
        };

        soccerQuestions = new String[]{
                getString(R.string.soccer_q1)
        };

        soccerAnswers = new String[][]{
                {getString(R.string.soccer_q1_a1), getString(R.string.soccer_q1_a2),
                        getString(R.string.soccer_q1_a3), getString(R.string.soccer_q1_a4c)}
        };

        soccerCorrectAnswers = new String[]{
                getString(R.string.soccer_q1_a4c)
        };

        bundle = new Bundle();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, topics);

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) listView.getItemAtPosition(position);
                Intent next = new Intent(MainActivity.this, Main2Activity.class);
                if (itemValue.equals(topics[0])) {
                    next.putExtra("questions", mathQuestions);
                    bundle.putSerializable("answers", mathAnswers);
                    next.putExtra("correct", mathCorrectAnswers);
                    next.putExtra("description", getString(R.string.math_description));
                } else if (itemValue.equals(topics[1])) {
                    next.putExtra("questions", physicsQuestions);
                    bundle.putSerializable("answers", physicsAnswers);
                    next.putExtra("correct", physicsCorrectAnswers);
                    next.putExtra("description", getString(R.string.physics_description));
                } else if (itemValue.equals(topics[2])) {
                    next.putExtra("questions", marvelQuestions);
                    bundle.putSerializable("answers", marvelAnswers);
                    next.putExtra("correct", marvelCorrectAnswers);
                    next.putExtra("description", getString(R.string.marvel_description));
                } else if (itemValue.equals(topics[3])) {
                    next.putExtra("questions", soccerQuestions);
                    bundle.putSerializable("answers", soccerAnswers);
                    next.putExtra("correct", soccerCorrectAnswers);
                    next.putExtra("description", getString(R.string.soccer_description));
                }
                next.putExtras(bundle);
                startActivity(next);
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
                .setName("Main Page") // TODO: Define a title for the content shown.
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
