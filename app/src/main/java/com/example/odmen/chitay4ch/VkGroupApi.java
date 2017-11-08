package com.example.odmen.chitay4ch;

import com.example.odmen.chitay4ch.Groups.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by odmen on 11.10.2017.
 */

public interface VkGroupApi {
    @GET("method/groups.getById")
    Call<Data> getData(@Query("access_token") String token, @Query("v") String version, @Query("group_ids") String name);

    @GET("method/wall.get")
    Call<com.example.odmen.chitay4ch.Wall.Data> getPost(@Query("access_token") String token, @Query("v") String version, @Query("extended") int extended, @Query("owner_id") int owner_id, @Query("count") int count, @Query("offset") int offset);
}
