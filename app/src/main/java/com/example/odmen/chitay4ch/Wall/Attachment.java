package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

/**
 * Created by odmen on 17.10.2017.
 */

public class Attachment {
    @SerializedName("type")
    String type;
    @SerializedName("photo")
    Photo photo;


    public String getType() {
        return type;
    }

    public Photo getPhoto() {
        return photo;
    }
}
