package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

/**
 * Created by odmen on 17.10.2017.
 */

public class Photo {
    @SerializedName("id")
    int id;
    @SerializedName("photo_604")
    String photo;

    public String getPhoto() {
        return photo;
    }

}
