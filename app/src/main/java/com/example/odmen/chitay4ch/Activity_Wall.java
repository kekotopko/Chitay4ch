package com.example.odmen.chitay4ch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.odmen.chitay4ch.Adapter.AdapterGif;
import com.example.odmen.chitay4ch.Adapter.AdapterWall;
import com.example.odmen.chitay4ch.Groups.Group;
import com.example.odmen.chitay4ch.Users.Users;
import com.example.odmen.chitay4ch.Wall.Data;
import com.example.odmen.chitay4ch.Wall.Doc;
import com.example.odmen.chitay4ch.Wall.GifVideo;
import com.example.odmen.chitay4ch.Wall.Groups;
import com.example.odmen.chitay4ch.Wall.Post;
import com.example.odmen.chitay4ch.Wall.Profiles;

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
    String photo,name;
    List<Post> posts = new ArrayList<>();
    List<Profiles> profilesList = new ArrayList<>();
    List<Groups> groupsList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Users user=null;
        Group group=null;
        if (getIntent().getParcelableExtra("group") != null) {
            group = getIntent().getParcelableExtra("group");
        } else user = getIntent().getParcelableExtra("user");
        if(group!=null){
        setTitle(group.getName());
        id = group.getId();
        photo = group.getPhoto_50();
        name=group.getName();}
        else {
            id=user.getId()*-1;
            photo=user.getPhoto_200();
            name=user.getFirst_name()+" "+user.getLast_name();
        }
        imageView = (ImageView) findViewById(R.id.repimageAvatar);
        setContentView(R.layout.listwall);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerwall);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        getPost(0);

        adapterWall = new AdapterWall(posts, profilesList, groupsList, photo, name, new AdapterWall.LoadClick() {
            @Override
            public void getOldpost() {
                getPost(posts.size() + 100);
            }
        });
        adapterWall.setClickGif(new AdapterGif.ClickGif() {
            @Override
            public void onClick(Doc doc) {
                Intent intent = new Intent(Activity_Wall.this, ActivityGif.class);
                String src = doc.getPreview().getVideo().getSrc();
                String name = doc.getTitle();
                intent.putExtra("url", src);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapterWall);
    }

    public void getPost(final int count) {
        App.getVkGroupApi().getPost("337f50dc337f50dc331206c107333365d03337f337f50dc6ba8b16e4679ff22002b5775", "5.54", 1, id * (-1), 100, count).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                posts.addAll(response.body().response.items);
                profilesList.addAll(response.body().response.profiles);
                groupsList.addAll(response.body().response.groups);
                adapterWall.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(Activity_Wall.this, "Нет подключения к интернетам", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
