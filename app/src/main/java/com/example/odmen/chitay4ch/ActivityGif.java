package com.example.odmen.chitay4ch;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toolbar;


import java.io.IOException;

/**
 * Created by odmen on 04.11.2017.
 */

public class ActivityGif extends AppCompatActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    SurfaceView gifvideo;
    MediaPlayer mediaPlayer;
    String url;
    ProgressBar progressBar;
    //private static ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitygif);
        url = getIntent().getStringExtra("url");
        setTitle(getIntent().getStringExtra("name"));
        Log.d("gifurl", url);
        gifvideo = (SurfaceView) findViewById(R.id.gifimage);
        progressBar = (ProgressBar) findViewById(R.id.progressBargif);
        progressBar.setVisibility(View.VISIBLE);
        //progressDialog=ProgressDialog.show(this,"","Loading...",true);
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
            mediaPlayer.setOnCompletionListener(this);
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

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        //progressDialog.dismiss();
        progressBar.setVisibility(View.GONE);
        mediaPlayer.start();


    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }
}

