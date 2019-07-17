package com.example.odmen.chitay4ch;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.odmen.chitay4ch.Adapter.AdapterRecyclerPublic;
import com.example.odmen.chitay4ch.Adapter.Owner;
import com.example.odmen.chitay4ch.Groups.Data;
import com.example.odmen.chitay4ch.Groups.Group;
import com.example.odmen.chitay4ch.Users.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    List<Owner> items;
    DBuser dBuser;
    EditText editname;
    ImageView imageView, imageAvatar;
    private AdapterRecyclerPublic adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listpubilcs);
        imageView = (ImageView) findViewById(R.id.imagePoisk);
        imageAvatar = (ImageView) findViewById(R.id.repimageAvatar);
        editname = (EditText) findViewById(R.id.editname);
        imageView.setOnClickListener(this);
        items = new ArrayList<>();
        dBuser = new DBuser(this);
        listfromdb();
        if(!items.isEmpty()){
        Log.d("testlist",items.get(0).getType());}
        recyclerView = (RecyclerView) findViewById(R.id.rececler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterRecyclerPublic(items, new AdapterRecyclerPublic.MyonClick() {
            @Override
            public void click(Owner owner) {
                items.remove(owner);
                dbdeleting(owner);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void clickstart(Owner owner) {
                Intent intent = new Intent(MainActivity.this, Activity_Wall.class);
                Log.d("keks",String.valueOf(owner.getId()));
                if (owner.getType().equals("group")) {
                    Group group = new Group(owner.getId(), owner.getName(), owner.getPhoto_50(), owner.getScreen_name(),owner.getType());
                    intent.putExtra("group", group);
                    startActivityForResult(intent, 1533);
                } else {
                    Users users = new Users(owner.getId(), owner.getName(), owner.getScreen_name(), owner.getPhoto_50(),owner.getType());
                    intent.putExtra("user",users);
                    startActivityForResult(intent, 1533);
                }


            }

        });

        recyclerView.setAdapter(adapter);
        if (!items.isEmpty())
        {
            refreshPublicInfo();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refreshUserInfo();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.settings:
                Intent intent=new Intent(MainActivity.this,ActivitySettings.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imagePoisk:

                App.getVkGroupApi().getData(getString(R.string.token), "5.54", editname.getText().toString()).enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, retrofit2.Response<Data> response) {
                        List<Group> list = response.body().response;
                        try {
                            Group group = list.get(0);
                            addgroup(group.getName(), group.getScreen_name(), group.getPhoto_50(), (int) group.getId(),"group");
                            Toast.makeText(MainActivity.this, "Паблик добавлен", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            //Toast.makeText(MainActivity.this, "Паблик с таким id не найден", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(MainActivity.this, "Нет подключения к интернетам", Toast.LENGTH_LONG).show();

                    }
                });

                App.getVkGroupApi().getUsers("337f50dc337f50dc331206c107333365d03337f337f50dc6ba8b16e4679ff22002b5775", "5.54", "photo_200", editname.getText().toString()).enqueue(new Callback<com.example.odmen.chitay4ch.Users.Data>() {
                    @Override
                    public void onResponse(Call<com.example.odmen.chitay4ch.Users.Data> call, Response<com.example.odmen.chitay4ch.Users.Data> response) {
                        List<Users> list = response.body().response;
                        try {
                            Users user = list.get(0);
                            adduser(user.getFirst_name(), user.getLast_name(), user.getPhoto_200(), (int) user.getId(),"user");
                            Toast.makeText(MainActivity.this, "Пользователь добавлен", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                           // Toast.makeText(MainActivity.this, "Пользователь с таким id не найден", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.odmen.chitay4ch.Users.Data> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(MainActivity.this, "Нет подключения к интернетам", Toast.LENGTH_LONG).show();
                    }
                });
                break;


        }
    }


    public void listfromdb() {
        items.clear();
        SQLiteDatabase database = dBuser.getWritableDatabase();
        Cursor cursor = database.query("public", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int dbid = cursor.getColumnIndex("_id");
            int id = cursor.getColumnIndex("vkid");
            int name = cursor.getColumnIndex("name");
            int img = cursor.getColumnIndex("image");
            int scrnname = cursor.getColumnIndex("scrn_name");
            int type=cursor.getColumnIndex("type");
            do {
                int _id = cursor.getInt(id);
                String _name = cursor.getString(name);
                String _img = cursor.getString(img);
                long _dbid = cursor.getLong(dbid);
                String _type=cursor.getString(type);
                String _scrnname = cursor.getString(scrnname);
                items.add(new Owner(_id, _name, _img, _scrnname,_type));

            }
            while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void addgroup(String name, String scrnname, String img, int id, String type) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase sqLiteDatabase = dBuser.getWritableDatabase();

        contentValues.put("vkid", id);
        contentValues.put("name", name);
        contentValues.put("image", img);
        contentValues.put("scrn_name", scrnname);
        contentValues.put("type", type);
        long _id = sqLiteDatabase.insert("public", null, contentValues);
        items.add(new Owner(id, name, img, scrnname,type));
        adapter.notifyDataSetChanged();
    }

    public void dbdeleting(Owner owner) {
        SQLiteDatabase database = dBuser.getWritableDatabase();
        database.delete("public", "vkid=?", new String[]{String.valueOf(owner.getId())});


    }

    public void refreshPublicInfo() {

        String ids="";
        List<Owner>groups=new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getType().equals("group")) {
                groups.add(items.get(i));
            }

        }
        if(!groups.isEmpty()){
        for(int a=0;a<groups.size();a++) {
            ids=ids+groups.get(a).getId();
            if(a!=groups.size()){
                ids=ids+",";
            }
        }
        }
        if(ids.length()>1) {
            Log.d("testlist", ids);
            App.getVkGroupApi().getData(getString(R.string.token), "5.54", ids).enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, retrofit2.Response<Data> response) {
                    List<Group> list = response.body().response;

                    for (int i = 0; i < list.size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("name", list.get(i).getName());
                        cv.put("image", list.get(i).getPhoto_50());
                        SQLiteDatabase database = dBuser.getWritableDatabase();
                        int updCount = database.update("public", cv, "vkid=?", new String[]{String.valueOf(list.get(i).getId())});
                        Log.d("updated", String.valueOf(updCount));
                        cv = null;

                    }

                    listfromdb();
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

    }

    public void refreshUserInfo() {

        String ids="";
        List<Owner>user=new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getType().equals("user")) {
                user.add(items.get(i));
            }

        }
        if(!user.isEmpty()){
            for(int a=0;a<user.size();a++) {
                ids=ids+user.get(a).getId();
                if(a!=user.size()){
                    ids=ids+",";
                }
            }
        }
        if(ids.length()>1) {
            Log.d("testlist", ids);
            App.getVkGroupApi().getUsers("337f50dc337f50dc331206c107333365d03337f337f50dc6ba8b16e4679ff22002b5775", "5.54", "photo_200", ids).enqueue(new Callback<com.example.odmen.chitay4ch.Users.Data>() {
                @Override
                public void onResponse(Call<com.example.odmen.chitay4ch.Users.Data> call, Response<com.example.odmen.chitay4ch.Users.Data> response) {
                    List<Users> list = response.body().response;
                    for (int i = 0; i < list.size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("name", list.get(i).getFirst_name()+list.get(i).getLast_name());
                        cv.put("image", list.get(i).getPhoto_200());
                        SQLiteDatabase database = dBuser.getWritableDatabase();
                        int updCount = database.update("public", cv, "vkid=?", new String[]{String.valueOf(list.get(i).getId())});
                        Log.d("updated", String.valueOf(updCount));
                        cv = null;

                    }

                    listfromdb();
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<com.example.odmen.chitay4ch.Users.Data> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        }
    }

/*----------------------------Users--------------------------------------------------*/


    public void adduser(String first_name, String lastname, String img, int id,String type) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase sqLiteDatabase = dBuser.getWritableDatabase();

        contentValues.put("vkid", id);
        contentValues.put("name", first_name + " " + lastname);
        contentValues.put("image", img);
        contentValues.put("type",type);
        long _id = sqLiteDatabase.insert("public", null, contentValues);
        items.add(new Owner(id, first_name + " " + lastname, img, "test",type));
        adapter.notifyDataSetChanged();


    }


    public void getUser(String id) {


    }
}


