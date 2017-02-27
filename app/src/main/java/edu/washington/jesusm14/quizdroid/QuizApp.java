package edu.washington.jesusm14.quizdroid;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by Jesus Moreno on 2/12/2017.
 */

public class QuizApp extends Application {

    private Topic current;
    private static QuizApp instance = new QuizApp();
    private static Context contextInstance;
    private static int interval;
    private static String url;
    public static QuizApp getQuizApp() {
        return instance;
    }

    public TopicRepository getRepository() {

        return TopicRepository.getInstance();
    }

    public static Context getContext() {
        return contextInstance;
    }

    public static void setContext(Context context) {
        contextInstance = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("CREATE", "Created!");
    }

    public void setCurrentTopic(Topic current) {
        this.current = current;
    }

    public Topic getCurrentTopic() {
        return this.current;
    }

    public static int getInterval() {
        return interval;
    }

    public static void setInterval(int giveninterval) {
        interval = giveninterval;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String givenUrl) {
        url = givenUrl;
    }
}
