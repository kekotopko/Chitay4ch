package com.example.odmen.chitay4ch;

import android.app.Application;
import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.net.InetSocketAddress;
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
    Context context=App.this;
    String proxyHost = "95.213.235.35";
    int proxyPort = 3752;

    @Override
    public void onCreate() {
        super.onCreate();


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(25, TimeUnit.SECONDS)
                .proxy(new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));



        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.vk.com")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(builder.build()))
                .build();

        Picasso.setSingletonInstance(picasso);

        vkGroupApi = retrofit.create(VkGroupApi.class);
    }

    public static VkGroupApi getVkGroupApi() {
        return vkGroupApi;
    }
}
