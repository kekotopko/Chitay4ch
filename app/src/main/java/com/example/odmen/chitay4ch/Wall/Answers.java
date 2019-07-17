package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 25.03.2018.
 */

public class Answers {
    @SerializedName("id")
    long id;
    @SerializedName("text")
    String text;
    @SerializedName("votes")
    int votes;
    @SerializedName("rate")
    double rate;

    public double getRate() {
        return rate;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getVotes() {
        return votes;
    }
}
