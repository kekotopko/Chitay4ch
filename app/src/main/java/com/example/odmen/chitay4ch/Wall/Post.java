package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by odmen on 17.10.2017.
 */

public class Post {
    @SerializedName("id")
    int id;
    @SerializedName("from_id")
    long from_id;
    @SerializedName("owner_id")
    long owner_id;
    @SerializedName("date")
    long date;
    @SerializedName("text")
    String text;
    @SerializedName("attachments")
    List<Attachment> attachmentseslist;

    public long getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
