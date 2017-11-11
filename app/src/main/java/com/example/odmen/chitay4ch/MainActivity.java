package com.example.odmen.chitay4ch;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.odmen.chitay4ch.Adapter.AdapterRecyclerPublic;
import com.example.odmen.chitay4ch.Groups.Data;
import com.example.odmen.chitay4ch.Groups.Group;

import java.util.ArrayList;
import java.util.List;

import retrofit2.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    List<Group> items;
    DBuser dBuser;
    EditText editname;
    ImageView imageView, imageAvatar,imageViewtest;
    private AdapterRecyclerPublic adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerpubilcs);
        imageView = (ImageView) findViewById(R.id.imagePoisk);
        imageAvatar = (ImageView) findViewById(R.id.repimageAvatar);
        editname = (EditText) findViewById(R.id.editname);
        imageView.setOnClickListener(this);
        imageViewtest= (ImageView) findViewById(R.id.imageView2);
        imageViewtest.setOnClickListener(this);
        items = new ArrayList<>();
        dBuser = new DBuser(this);
        listfromdb();
        recyclerView = (RecyclerView) findViewById(R.id.rececler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterRecyclerPublic(items, new AdapterRecyclerPublic.MyonClick() {
            @Override
            public void click(Group group) {
                items.remove(group);
                dbdeleting(group);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void clickstart(Group group) {
                Intent intent = new Intent(MainActivity.this, Activity_Wall.class);
                intent.putExtra("group", group);
                startActivityForResult(intent, 1533);
                Log.d("tag", String.valueOf(group.getId()));
            }
        });

        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imagePoisk:

                App.getVkGroupApi().getData("337f50dc337f50dc331206c107333365d03337f337f50dc6ba8b16e4679ff22002b5775", "5.54", editname.getText().toString()).enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, retrofit2.Response<Data> response) {
                        List<Group> list = response.body().response;
                        try {
                            Group group = list.get(0);
                            addgroup(group.getName(), group.getScreen_name(), group.getPhoto_50(), (int) group.getId());
                            Toast.makeText(MainActivity.this, "Паблик добавлен", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Паблик с таким id не найден", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(MainActivity.this, "Нет подключения к интернетам", Toast.LENGTH_LONG).show();

                    }
                });
                break;
            case R.id.imageView2:
                Intent intent=new Intent(MainActivity.this,ActivityUsers.class);
                startActivity(intent);
        }
    }


    public void listfromdb() {
        SQLiteDatabase database = dBuser.getWritableDatabase();
        Cursor cursor = database.query("public", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int dbid = cursor.getColumnIndex("_id");
            int id = cursor.getColumnIndex("vkid");
            int name = cursor.getColumnIndex("name");
            int img = cursor.getColumnIndex("image");
            int scrnname = cursor.getColumnIndex("scrn_name");
            do {
                int _id = cursor.getInt(id);
                String _name = cursor.getString(name);
                String _img = cursor.getString(img);
                long _dbid = cursor.getLong(dbid);
                String _scrnname = cursor.getString(scrnname);
                items.add(new Group(_id, _name, _img, _scrnname));

            }
            while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void addgroup(String name, String scrnname, String img, int id) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase sqLiteDatabase = dBuser.getWritableDatabase();

        contentValues.put("vkid", id);
        contentValues.put("name", name);
        contentValues.put("image", img);
        contentValues.put("scrn_name", scrnname);
        long _id = sqLiteDatabase.insert("public", null, contentValues);
        items.add(new Group(id, name, img, scrnname));
        adapter.notifyDataSetChanged();
    }

    public void dbdeleting(Group group) {
        SQLiteDatabase database = dBuser.getWritableDatabase();
        database.delete("public", "vkid=?", new String[]{String.valueOf(group.getId())});

    }

}
