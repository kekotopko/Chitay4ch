package com.example.odmen.chitay4ch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.felipecsl.gifimageview.library.GifImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by odmen on 04.11.2017.
 */

public class ActivityGif extends AppCompatActivity {
    GifImageView gifImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getIntent().getStringExtra("url");
        gifImageView = (GifImageView) findViewById(R.id.gifimage);
        Picasso.with(gifImageView.getContext()).load(url).into(gifImageView);
        gifImageView.startAnimation();
    }
}
