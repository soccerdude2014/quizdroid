package edu.washington.jesusm14.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by Jesus Moreno on 2/12/2017.
 */

public class QuizApp extends Application {

    private Topic current;
    private static QuizApp instance = new QuizApp();

    public static QuizApp getQuizApp() {
        return instance;
    }

    public TopicRepository getRepository() {

        return TopicRepository.getInstance();
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
}
