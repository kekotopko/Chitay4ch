package com.example.odmen.chitay4ch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.odmen.chitay4ch.Adapter.AdapterWall;
import com.example.odmen.chitay4ch.Groups.Group;
import com.example.odmen.chitay4ch.Wall.Data;
import com.example.odmen.chitay4ch.Wall.Post;
import com.squareup.picasso.Picasso;

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
    ImageView imageView;
    RecyclerView recyclerView;
    private AdapterWall adapterWall;
    String photo;
    List<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Group group = getIntent().getParcelableExtra("group");
        id = group.getId();
        photo = group.getPhoto_50();
        imageView = (ImageView) findViewById(R.id.imageAvatar);
        setContentView(R.layout.listwall);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerwall);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        getPost(0);

        adapterWall = new AdapterWall(posts, group, new AdapterWall.LoadClick() {
            @Override
            public void getOldpost() {
                getPost(posts.size() + 100);
            }
        });
        recyclerView.setAdapter(adapterWall);
    }

    public void getPost(final int count) {
        App.getVkGroupApi().getPost("337f50dc337f50dc331206c107333365d03337f337f50dc6ba8b16e4679ff22002b5775", "5.54", id * (-1), 100, count).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                posts.addAll(response.body().response.items);
                adapterWall.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(Activity_Wall.this, "nit", Toast.LENGTH_SHORT).show();
            }
        });
    }

}