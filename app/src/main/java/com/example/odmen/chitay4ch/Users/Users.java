package com.example.odmen.chitay4ch.Users;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by odmen on 11.11.2017.
 */

public class Users implements Parcelable{
    @SerializedName("id")
    int id;
    @SerializedName("first_name")
    String first_name;
    @SerializedName("last_name")
    String last_name;
    @SerializedName("photo_200")
    String photo_200;
    @SerializedName("domain")
    String domain;

    String type;

    public String getType() {
        return type;
    }

    public Users(int id, String first_name, String last_name, String photo_200,String type) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.photo_200 = photo_200;
        this.type=type;
    }

    protected Users(Parcel in) {
        id = in.readInt();
        first_name = in.readString();
        last_name = in.readString();
        photo_200 = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhoto_200() {
        return photo_200;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(first_name);
        parcel.writeString(last_name);
        parcel.writeString(photo_200);
    }

    public String getDomain() {
        return domain;
    }
}
