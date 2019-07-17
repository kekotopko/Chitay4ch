package com.example.odmen.chitay4ch.Groups;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by odmen on 11.10.2017.
 */

public class Group implements Parcelable {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("photo_200")
    String photo;
    @SerializedName("screen_name")
    String screen_name;

    String type;

    public Group(int id, String name, String photo, String screen_name,String type) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.screen_name = screen_name;
    }

    protected Group(Parcel in) {
        id = in.readInt();
        name = in.readString();
        photo = in.readString();
        screen_name = in.readString();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto_50() {
        return photo;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo_200) {
        this.photo = photo_200;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(photo);
        parcel.writeString(screen_name);
    }
}
