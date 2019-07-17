package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 25.03.2018.
 */

public class Poll {
    @SerializedName("id")
    int id;
    @SerializedName("owner_id")
    long owner_id;
    @SerializedName("question")
    String question;
    @SerializedName("votes")
    int votes;
    @SerializedName("answer_id")
    long answer_id;
    @SerializedName("anonymous")
    int anonymous;
    @SerializedName("answers")
    public List<Answers>answers;


    public int getId() {
        return id;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public String getQuestion() {
        return question;
    }

    public int getVotes() {
        return votes;
    }

    public long getAnswer_id() {
        return answer_id;
    }

    public int getAnonymous() {
        return anonymous;
    }

    public List<Answers> getAnswers() {
        return answers;
    }
}
