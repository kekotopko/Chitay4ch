package com.example.odmen.chitay4ch;

import android.app.Application;
import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by odmen on 11.10.2017.
 */

public class App extends Application {

    private static VkGroupApi vkGroupApi;
    String proxyHost = "213.196.53.28";
    int proxyPort = 1084;

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
        vkGroupApi = retrofit.create(VkGroupApi.class);
    }

    public static VkGroupApi getVkGroupApi() {
        return vkGroupApi;
    }
}
