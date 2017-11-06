package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by odmen on 04.11.2017.
 */

public class Doc {

    @SerializedName("title")
    String title;
    @SerializedName("url")
    String url;
    @SerializedName("type")
    int type;
    @SerializedName("preview")
    Preview preview;

    public Preview getPreview() {
        return preview;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getType() {
        return type;
    }


}
