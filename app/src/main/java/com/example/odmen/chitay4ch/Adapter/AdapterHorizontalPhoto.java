package com.example.odmen.chitay4ch.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.odmen.chitay4ch.R;
import com.example.odmen.chitay4ch.Wall.Attachment;
import com.example.odmen.chitay4ch.Wall.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odmen on 25.10.2017.
 */

public class AdapterHorizontalPhoto extends RecyclerView.Adapter<AdapterHorizontalPhoto.ViewHolder> {

    List<Photo> photos = new ArrayList<>();
    private int mRowIndex = -1;


    public AdapterHorizontalPhoto() {

    }

    public void setData(List<Photo> data) {
        photos.clear();
        photos.addAll(data);
        notifyDataSetChanged();
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemphoto, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        double prop = photos.get(position).getWidth() / photos.get(position).getHeight();
        float params = holder.imagePhoto.getContext().getResources().getDisplayMetrics().density;
        holder.imagePhoto.setLayoutParams(new LinearLayout.LayoutParams((int) (photos.get(position).getHeight() * prop), (int) (photos.get(position).getHeight() * prop)));
        Picasso.with(holder.itemView.getContext()).load(photos.get(position).getPhoto()).into(holder.imagePhoto);

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            imagePhoto = (ImageView) itemView.findViewById(R.id.photoconteiner);

        }
    }
}
