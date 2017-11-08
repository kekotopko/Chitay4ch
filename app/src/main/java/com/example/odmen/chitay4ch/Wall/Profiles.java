package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

/**
 * Created by odmen on 02.11.2017.
 */

public class Profiles {
    @SerializedName("id")
    long id;
    @SerializedName("first_name")
    String first_name;
    @SerializedName("last_name")
    String last_name;
    @SerializedName("photo_200")
    String photo_200;
    @SerializedName("photo_50")
    String photo_50;
    @SerializedName("photo_100")
    String photo_100;

    public long getId() {
        return id;
    }

    public String getName() {
        return first_name + " " + last_name;
    }

    public String getPhoto_200() {
        return photo_200;
    }

    public String getPhoto_50() {
        return photo_50;
    }

    public String getPhoto_100() {
        return photo_100;
    }
}
