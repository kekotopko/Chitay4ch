package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

/**
 * Created by odmen on 31.10.2017.
 */

public class Audio {
    @SerializedName("artist")
    String artist;
    @SerializedName("title")
    String title;
    @SerializedName("duration")
    int duration;

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }
}
