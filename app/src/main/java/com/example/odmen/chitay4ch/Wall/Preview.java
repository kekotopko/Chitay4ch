package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odmen on 04.11.2017.
 */

public class Preview {
    @SerializedName("video")
    public GifVideo video;
    @SerializedName("photo")
    PhotoGif photoGif;

    public PhotoGif getPhotoGif() {
        return photoGif;
    }

    public GifVideo getVideo() {
        return video;
    }
}
