package io.xicp.myspace.region2;

import android.os.Parcel;


import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class Answer implements Serializable {
    private String question;
    private String answer;
    private String isCorrect;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }


}
