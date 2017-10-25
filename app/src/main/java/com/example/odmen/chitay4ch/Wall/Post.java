package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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

    public  List<Photo> getlistphoto() {
        List<Photo> photos = new ArrayList<>();
        for (int i = 0; i < attachmentseslist.size(); i++) {

            Attachment attachment = new Attachment();
            attachment = attachmentseslist.get(i);
            if (attachment.getType().equals("photo")) {
                photos.add(attachment.getPhoto());
            }
        }
        return photos;
    }


}
