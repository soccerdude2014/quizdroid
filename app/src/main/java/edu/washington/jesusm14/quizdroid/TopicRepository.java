package edu.washington.jesusm14.quizdroid;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 * Created by Jesus Moreno on 2/12/2017.
 */

public class TopicRepository {

    private static TopicRepository instance = new TopicRepository();

    public static TopicRepository getInstance() {
        return instance;
    }



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

    private List<Topic> topicList = new ArrayList<Topic>();

    public TopicRepository() {
        topicList.add(mathTopics);
        topicList.add(physics);
        topicList.add(marvel);
        topicList.add(soccer);
    }


    public String[] getTopics() {
        return topics;
    }


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
    }

}
