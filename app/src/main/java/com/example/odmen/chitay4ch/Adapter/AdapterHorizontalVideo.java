package com.example.odmen.chitay4ch.Adapter;

import android.app.Activity;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.odmen.chitay4ch.R;
import com.example.odmen.chitay4ch.Wall.Photo;
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


    public AdapterHorizontalVideo() {

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

    @Override
    public void onBindViewHolder(AdapterHorizontalVideo.ViewHolder holder, int position) {
        Activity activity = (Activity) holder.itemView.getContext();
        ViewGroup.LayoutParams layoutParams = holder.imageVideo.getLayoutParams();
        float prop = (float) videos.get(position).getWidth() / (float) videos.get(position).getHeight();
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widthScr = size.x;
        if (videos.size() == 1) {
            layoutParams.width = widthScr;
            layoutParams.height = (int) ((float) widthScr / prop);
        } else {

            //float params = holder.imagePhoto.getContext().getResources().getDisplayMetrics().density;
            int height = holder.imageVideo.getLayoutParams().height;
            layoutParams.width = (int) (prop * height);

        }
        holder.imageVideo.setLayoutParams(layoutParams);
        if (videos.get(position).getPhoto_640()!=null) {
            photo = videos.get(position).getPhoto_640();
        } else if (videos.get(position).getPhoto_800() != null) {
            photo = videos.get(position).getPhoto_800();
        } else if (videos.get(position).getPhoto_320() != null) {
            photo = videos.get(position).getPhoto_320();
        } else {
            photo = videos.get(position).getPhoto_130();
        }
        holder.playideo.setVisibility(View.VISIBLE);
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
