package com.example.odmen.chitay4ch;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by odmen on 11.10.2017.
 */

public class App extends Application {

    private static VkGroupApi vkGroupApi;

    @Override
    public void onCreate() {
        super.onCreate();


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(25, TimeUnit.SECONDS);


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.vk.com")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        vkGroupApi = retrofit.create(VkGroupApi.class);
    }

    public static VkGroupApi getVkGroupApi() {
        return vkGroupApi;
    }
}
