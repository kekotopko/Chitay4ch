package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

/**
 * Created by odmen on 17.10.2017.
 */

public class Photo {
    @SerializedName("id")
    int id;
    @SerializedName("photo_75")
    String photo75;
    @SerializedName("photo_130")
    String photo130;
    @SerializedName("photo_604")
    String photo604;
    @SerializedName("photo_807")
    String photo807;
    @SerializedName("photo_1280")
    String photo1280;

    @SerializedName("width")
    int width;
    @SerializedName("height")
    int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getPhoto75() {
        return photo75;
    }

    public String getPhoto130() {
        return photo130;
    }

    public String getPhoto604() {
        return photo604;
    }

    public String getPhoto807() {
        return photo807;
    }

    public String getPhoto1280() {
        return photo1280;
    }

}


