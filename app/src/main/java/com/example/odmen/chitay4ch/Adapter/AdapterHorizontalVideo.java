package com.example.odmen.chitay4ch.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.odmen.chitay4ch.R;
import com.example.odmen.chitay4ch.Wall.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odmen on 31.10.2017.
 */

public class AdapterHorizontalVideo extends RecyclerView.Adapter<AdapterHorizontalVideo.ViewHolder> {
    List<Video> videos = new ArrayList<>();
    private int mRowIndex = -1;
    String photo;
    ClickVideo clickVideo;


    public AdapterHorizontalVideo() {

    }

    public interface ClickVideo{
        void clickVideo(Video video);
    }

    public void setClickVideo(ClickVideo clickVideo){
        this.clickVideo=clickVideo;
    }

    public void setData(List<Video> data) {
        videos.clear();
        videos.addAll(data);
        notifyDataSetChanged();
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    @Override
    public AdapterHorizontalVideo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemvideo, parent, false);
        AdapterHorizontalVideo.ViewHolder viewHolder = new AdapterHorizontalVideo.ViewHolder(view);
        return viewHolder;
    }

    private int getWindowWidth(Context context){
        Activity activity = (Activity) context;
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widthScr = size.x;
        return widthScr;
    }

    @Override
    public void onBindViewHolder(AdapterHorizontalVideo.ViewHolder holder, final int position) {
        Activity activity = (Activity) holder.itemView.getContext();
        Video video=videos.get(position);
        ViewGroup.LayoutParams layoutParams = holder.imageVideo.getLayoutParams();
        float prop = (float) video.getWidth() / (float) video.getHeight();
        int widthScr=getWindowWidth(holder.imageVideo.getContext());
        if (position==0) {
            layoutParams.width = widthScr;
            layoutParams.height = (int) ((float) widthScr / prop);
        } else {
            Video first=videos.get(0);
            float firstProp = (float) first.getWidth() / (float) first.getHeight();
            layoutParams.height = (int) ((float) widthScr / firstProp);
            layoutParams.width = (int) (prop * (float) layoutParams.height);

        }
        holder.imageVideo.setLayoutParams(layoutParams);
        if (videos.get(position).getPhoto_640() != null) {
            photo = videos.get(position).getPhoto_640();
        } else if (videos.get(position).getPhoto_800() != null) {
            photo = videos.get(position).getPhoto_800();
        } else if (videos.get(position).getPhoto_320() != null) {
            photo = videos.get(position).getPhoto_320();
        } else {
            photo = videos.get(position).getPhoto_130();
        }
        Log.e("lukas", photo);
        holder.playideo.setVisibility(View.VISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickVideo!=null){
                    clickVideo.clickVideo(videos.get(position));
                }
            }
        });
        Picasso.with(holder.itemView.getContext()).load(photo).into(holder.imageVideo);
    }


    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageVideo, playideo;

        public ViewHolder(View itemView) {
            super(itemView);
            imageVideo = (ImageView) itemView.findViewById(R.id.videoconteiner);
            playideo = (ImageView) itemView.findViewById(R.id.playvideo);

        }
    }
}
