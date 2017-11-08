package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by odmen on 17.10.2017.
 */

public class Response {
    public List<Post> items;

    @SerializedName("profiles")
    public List<Profiles> profiles;
    @SerializedName("groups")
    public List<Groups> groups;


}
