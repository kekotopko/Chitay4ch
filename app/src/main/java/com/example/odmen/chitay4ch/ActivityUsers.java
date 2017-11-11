package com.example.odmen.chitay4ch;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.odmen.chitay4ch.Adapter.AdapterUsers;
import com.example.odmen.chitay4ch.Users.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by odmen on 11.11.2017.
 */

public class ActivityUsers extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    List<Users> items;
    DBuser dBuser;
    EditText editname;
    ImageView imageView, imageAvatar;
    private AdapterUsers adapterUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerpubilcs);
        imageView = (ImageView) findViewById(R.id.imagePoisk);
        imageAvatar = (ImageView) findViewById(R.id.repimageAvatar);
        editname = (EditText) findViewById(R.id.editname);
        imageView.setOnClickListener(this);
        items = new ArrayList<>();
        dBuser = new DBuser(this);
        listfromdb();
        recyclerView = (RecyclerView) findViewById(R.id.rececler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapterUsers = new AdapterUsers(items, new AdapterUsers.UsMyonClick() {
            @Override
            public void click(Users user) {
                items.remove(user);
                dbdeleting(user);
                adapterUsers.notifyDataSetChanged();
            }

            @Override
            public void clickstart(Users user) {
                Intent intent = new Intent(ActivityUsers.this, Activity_Wall.class);
                intent.putExtra("user", user);
                startActivityForResult(intent, 1533);
                Log.d("tag", String.valueOf(user.getId()));
            }
        });

        recyclerView.setAdapter(adapterUsers);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imagePoisk:


                App.getVkGroupApi().getUsers("337f50dc337f50dc331206c107333365d03337f337f50dc6ba8b16e4679ff22002b5775", "5.54", "photo_200", editname.getText().toString()).enqueue(new Callback<com.example.odmen.chitay4ch.Users.Data>() {
                    @Override
                    public void onResponse(Call<com.example.odmen.chitay4ch.Users.Data> call, Response<com.example.odmen.chitay4ch.Users.Data> response) {
                        List<Users> list = response.body().response;
                        try {
                            Users user = list.get(0);
                            adduser(user.getFirst_name(), user.getLast_name(), user.getPhoto_200(), (int) user.getId());
                            Toast.makeText(ActivityUsers.this, "Пользователь добавлен", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(ActivityUsers.this, "Пользователь с таким id не найден", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.odmen.chitay4ch.Users.Data> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(ActivityUsers.this, "Нет подключения к интернетам", Toast.LENGTH_LONG).show();
                    }
                });
        }
    }


    public void listfromdb() {
        SQLiteDatabase database = dBuser.getWritableDatabase();
        Cursor cursor = database.query("users", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int dbid = cursor.getColumnIndex("_id");
            int id = cursor.getColumnIndex("id");
            int firstname = cursor.getColumnIndex("first_name");
            int lastname = cursor.getColumnIndex("last_name");
            int img = cursor.getColumnIndex("image");

            do {
                int _id = cursor.getInt(id);
                String _firstname = cursor.getString(firstname);
                String _lastname = cursor.getString(lastname);
                String _img = cursor.getString(img);
                long _dbid = cursor.getLong(dbid);
                items.add(new Users(_id, _firstname, _lastname, _img));

            }
            while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void adduser(String first_name, String lastname, String img, int id) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase sqLiteDatabase = dBuser.getWritableDatabase();

        contentValues.put("id", id);
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", lastname);
        contentValues.put("image", img);
        long _id = sqLiteDatabase.insert("users", null, contentValues);
        items.add(new Users(id, first_name, lastname, img));
        adapterUsers.notifyDataSetChanged();
    }

    public void dbdeleting(Users users) {
        SQLiteDatabase database = dBuser.getWritableDatabase();
        database.delete("users", "id=?", new String[]{String.valueOf(users.getId())});

    }
}
