package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 08.02.2018.
 * Chitay4ch new
 */
public class Likes {
    @SerializedName("count")
    public int count;

    public int getLikes() {
        return count;
    }
}
