package edu.washington.jesusm14.quizdroid;

/**
 * Created by Jesus Moreno on 2/12/2017.
 */

public class Topic {
    private String title;
    private String shortDescription;
    private String longDescription;
    private Question[] collection;

    public Topic(String title, String shortDescription, String longDescription, Question[] collection) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.collection = collection;
    }

    public Topic() {

    }

    public String getTitle() {
        return this.title;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public Question[] getCollection() {
        return this.collection;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortDescription(String desc) {
        this.shortDescription = desc;
    }

    public void setLongDescription(String desc) {
        this.longDescription = desc;
    }

    public void setCollection(Question[] q) {
        this.collection = q;
    }
}
