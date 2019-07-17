package com.example.odmen.chitay4ch.Adapter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 27.03.2018.
 */

public class Owner {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("photo_200")
    String photo;
    @SerializedName("screen_name")
    String screen_name;

    String type;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Owner(int id, String name, String img, String scrnname, String type) {
        this.id=id;
        this.name=name;
        this.photo=img;
        this.screen_name=scrnname;
        this.type=type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto_50() {
        return photo;
    }

    public String getScreen_name() {
        return screen_name;
    }
}
