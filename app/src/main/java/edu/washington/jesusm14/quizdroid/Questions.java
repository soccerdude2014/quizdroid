package edu.washington.jesusm14.quizdroid;

/**
 * Created by Jesus Moreno on 1/26/2017.
 */

public class Questions {
    private int mTextResId;
    private boolean mAnswerTrue;
    public Questions(int mTextResId, boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
        this.mTextResId = mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }
}
