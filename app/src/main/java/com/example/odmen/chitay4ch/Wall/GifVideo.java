package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

/**
 * Created by odmen on 06.11.2017.
 */
public class GifVideo {
    @SerializedName("src")
    String src;
    @SerializedName("width")
    int width;
    @SerializedName("height")
    int height;

    public String getSrc() {
        return src;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
