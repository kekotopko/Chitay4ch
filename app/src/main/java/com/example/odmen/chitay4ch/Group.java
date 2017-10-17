package com.example.odmen.chitay4ch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by odmen on 11.10.2017.
 */

public class Group {

    @SerializedName("id")
    long id;
    @SerializedName("name")
    String name;
    @SerializedName("photo_200")
    String photo;
    @SerializedName("screen_name")
    String screen_name;

    public Group(long id, String name, String photo, String screen_name) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.screen_name=screen_name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto_50() {
        return photo;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo_200) {
        this.photo = photo_200;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }
}
