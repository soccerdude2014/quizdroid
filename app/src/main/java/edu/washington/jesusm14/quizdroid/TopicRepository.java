package edu.washington.jesusm14.quizdroid;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import android.os.Handler;

/**
 * Created by Jesus Moreno on 2/12/2017.
 */

public class TopicRepository {

    private class MyAsyncTask extends AsyncTask<String, String, String> {
        private ProgressDialog pd;
        private HttpURLConnection connection;
        private URL url;
        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL(QuizApp.getUrl());
                Toast.makeText(QuizApp.getContext(), url.toString(),
                        Toast.LENGTH_LONG).show();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }
            try {
                int response_code = connection.getResponseCode();

                if(response_code == HttpURLConnection.HTTP_OK) {
                    InputStream input = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    return(result.toString());
                } else {
                    return ("UNSUCCESSFUL");
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                return e2.toString();
            } finally {
                connection.disconnect();
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(QuizApp.getContext());

            pd.setMessage("JSON File Download being attempted...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected void onPostExecute(String result) {
            if(pd.isShowing()) {
                pd.dismiss();
            }
            try {

                JSONArray json = new JSONArray(result);

                for(int i = 0; i < json.length(); i++) {
                    JSONObject jdata = json.getJSONObject(i);
                    Topic topic = new Topic();
                    topic.setTitle(jdata.getString("title"));
                    topic.setLongDescription(jdata.getString("desc"));
                    topic.setShortDescription(jdata.getString("desc"));
                    JSONArray qs = jdata.getJSONArray("questions");
                    Question[] questionArray = new Question[qs.length()];
                    for(int j = 0; j < qs.length(); j++) {
                        JSONObject qdata = qs.getJSONObject(j);
                        Question current = new Question();
                        current.setQuestion(qdata.getString("text"));
                        current.setCorrectAnswer(Integer.parseInt(qdata.getString("answer")) - 1);
                        JSONArray answers = qdata.getJSONArray("answers");
                        String[] answerArray = new String[answers.length()];
                        for(int k = 0; k < answers.length(); k++) {
                            answerArray[k] = answers.getString(k);
                        }
                        current.setAnswers(answerArray);
                        questionArray[j] = current;
                    }
                    topic.setCollection(questionArray);
                    topicList.add(topic);
                }
                Intent test = new Intent(QuizApp.getContext(), MainActivity.class);
                QuizApp.getContext().startActivity(test);

            } catch (JSONException e) {
                Toast.makeText(QuizApp.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

        public void callAsynchronousTask() {
            final Handler handler = new Handler();
            Timer timer = new Timer();
            TimerTask doAsynchronousTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                ConnectivityManager cm =
                                        (ConnectivityManager)QuizApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                                boolean isConnected = activeNetwork != null &&
                                        activeNetwork.isConnectedOrConnecting();
                                if(!isConnected) {
                                    if(isAirplaneModeOn(QuizApp.getContext())) {
                                        AlertDialog.Builder dialog = new AlertDialog.Builder(QuizApp.getContext());
                                        dialog.setMessage("Would you like to go to settings to turn off airplane mode?");
                                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent dialogIntent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                                                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                QuizApp.getContext().startActivity(dialogIntent);
                                            }
                                        });
                                        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });
                                        AlertDialog alert = dialog.create();
                                        alert.show();
                                    } else {
                                        Toast.makeText(QuizApp.getContext(), "You are not connected to the internet!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    MyAsyncTask performBackgroundTask = new MyAsyncTask();
                                    performBackgroundTask.execute("");
                                }

                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                            }
                        }
                    });
                }
            };
            timer.schedule(doAsynchronousTask, 0, 60000 * QuizApp.getInterval()); //execute in every 50000 ms
        }

        @SuppressWarnings("deprecation")
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        public boolean isAirplaneModeOn(Context context) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                return Settings.System.getInt(context.getContentResolver(),
                        Settings.System.AIRPLANE_MODE_ON, 0) != 0;
            } else {
                return Settings.Global.getInt(context.getContentResolver(),
                        Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
            }
        }

    }

    private static TopicRepository instance = new TopicRepository();

    public static TopicRepository getInstance() {
        return instance;
    }

    public TopicRepository () {
        new MyAsyncTask().execute("");
    }


    /*
    private String[] mathQuestions = new String[]{
        "What is 2 + 2?", "What is the derivative of 5x?"
    };

    private String[][] mathAnswers = new String[][]{
        {"22", "-4", "4", "5"}, {"5", "5x^2", "x^5", "5x"}
    };

    private int[] mathCorrectAnswers = new int[] {
      2, 0
    };

    private String[] physicsQuestions = new String[]{
        "What is force equal to?"
    };

    private String[][] physicsAnswers = new String[][]{
        {"mc^2", "m*a", "K", "Distance * time"}
    };

    private int[] physicsCorrectAnswers = new int[]{
        1
    };

    private String[] marvelQuestions = new String[]{
        "Who is not a hero in the Avengers?"
    };
    private String[][] marvelAnswers = new String[][]{
        {"Iron Man", "Batman", "Captain America", "Hulk"}
    };

    private int[] marvelCorrectAnswers = new int[]{
        1
    };

    private String[] soccerQuestions = new String[]{
        "Which country won the 2014 FIFA World Cup?"
    };

    private String[][] soccerAnswers = new String[][]{
        {"Brazil", "Argentina", "United States", "Germany"}
    };

    private int[] soccerCorrectAnswers = new int[]{
        3
    };

    private String[] topics = new String[]{
      "Math", "Physics", "Marvel Super Heros", "Soccer"
    };



    private Topic mathTopics = new Topic("Math", "Math Short Description", "Math Long Description", new Question[] {
            new Question(mathQuestions[0], mathAnswers[0], mathCorrectAnswers[0]),
            new Question(mathQuestions[1], mathAnswers[1], mathCorrectAnswers[1])
    });
    private Topic physics = new Topic("Physics", "Physics Short Description", "Physics Long Description", new Question[] {
            new Question("What is Force equal to?", physicsAnswers[0], physicsCorrectAnswers[0])
    });
    private Topic marvel = new Topic("Marvel Super Heros", "Marvel Super Heros Short Description", "Marvel Super Heros Long Description", new Question[] {
            new Question("Who is NOT in the Avengers?", marvelAnswers[0], marvelCorrectAnswers[0])
    } );
    private Topic soccer = new Topic("Soccer", "Soccer Short Description", "Soccer Long Description", new Question[] {
            new Question("Which country won the 2014 FIFA World Cup?", soccerAnswers[0], soccerCorrectAnswers[0])
    } );
    */
    private List<Topic> topicList = new ArrayList<Topic>();



    public List<Topic> getTopics() {
        return topicList;
    }

    public String[] getTopicList() {
        List<Topic> list = getTopics();
        String[] top = new String[list.size()];
        for(int i = 0; i < top.length; i++) {
            Topic temp = list.get(i);
            top[i] = temp.getTitle();
        }
        return top;
    }

    public Topic getTopic(int i) {
        List<Topic> list = getTopics();
        return list.get(i);
    }

    /*
    public Topic getMathTopic() {
        return topicList.get(0);
    }



    public Topic getPhysicsTopic() {
        return topicList.get(1);
    }


    public Topic getMarvelTopic() {
        return topicList.get(2);
    }

    public Topic getSoccerTopic() {
        return topicList.get(3);
    } */

}
