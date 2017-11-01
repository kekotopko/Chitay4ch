package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

/**
 * Created by odmen on 31.10.2017.
 */

public class Video {
    @SerializedName("title")
    String title;
    @SerializedName("photo_130")
    String photo_130;
    @SerializedName("photo_320")
    String photo_320;
    @SerializedName("photo_640")
    String photo_640;
    @SerializedName("photo_800")
    String photo_800;
    @SerializedName("width")
    int width;
    @SerializedName("height")
    int height;

    public String getTitle() {
        return title;
    }

    public String getPhoto_130() {
        return photo_130;
    }

    public String getPhoto_320() {
        return photo_320;
    }

    public String getPhoto_800() {
        return photo_800;
    }

    public int getWidth() {
        if(width==0){
            width=1280;
        }
        return width;
    }

    public int getHeight() {
        if(height==0){
            height=720;
        }
        return height;
    }

    public String getPhoto_640() {
        return photo_640;
    }
}
