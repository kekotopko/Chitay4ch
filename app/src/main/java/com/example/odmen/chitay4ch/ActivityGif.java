package com.example.odmen.chitay4ch;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.odmen.chitay4ch.Wall.Video;
import com.felipecsl.gifimageview.library.GifImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by odmen on 04.11.2017.
 */

public class ActivityGif extends AppCompatActivity implements MediaPlayer.OnPreparedListener {
    SurfaceView gifvideo;
    MediaPlayer mediaPlayer;
    String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitygif);
        url = getIntent().getStringExtra("url");
        Log.d("gifurl", url);
        gifvideo = (SurfaceView) findViewById(R.id.gifimage);
        try {
            playVideo(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }


    public void playVideo(String path) throws IOException {

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gifvideo.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                mediaPlayer.setDisplay(surfaceHolder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int widh, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });
        onPrepared(mediaPlayer);


    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}

