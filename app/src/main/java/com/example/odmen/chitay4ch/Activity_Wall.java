package com.example.odmen.chitay4ch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.odmen.chitay4ch.Groups.Data;
import com.example.odmen.chitay4ch.Wall.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by odmen on 16.10.2017.
 */

public class Activity_Wall extends AppCompatActivity {
    int id;
    List<Post>posts=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getVkGroupApi().getPost("337f50dc337f50dc331206c107333365d03337f337f50dc6ba8b16e4679ff22002b5775", "5.54", id).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                posts.add(response.body().r)
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        id = data.getIntExtra("id", 15333);
    }
}
