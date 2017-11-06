package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odmen on 06.11.2017.
 */

public class PhotoGif {
    @SerializedName("sizes")
    List<Sizes> sizes;

    public List<Sizes> getListsize() {
        List<Sizes> sizelist = new ArrayList<>();
        if (sizes != null) {
        }
        sizelist.addAll(sizes);
        return sizelist;
    }
}
