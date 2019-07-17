package com.example.odmen.chitay4ch.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.odmen.chitay4ch.R;
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
    String photo;


    public AdapterHorizontalPhoto() {

    }

    public void setData(List<Photo> data) {
        if (data != null) {
            photos.clear();
            photos.addAll(data);
        }
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

    private int getWindowWidth(Context context){
        Activity activity = (Activity) context;
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widthScr = size.x;
        return widthScr;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo ph=photos.get(position);
        ViewGroup.LayoutParams layoutParams = holder.imagePhoto.getLayoutParams();
        float prop = (float) ph.getWidth() / (float) ph.getHeight();
        int widthScr=getWindowWidth(holder.imagePhoto.getContext());
        if (position==0) {
            layoutParams.width = widthScr;
            layoutParams.height = (int) ((float) widthScr / prop);
        } else {
            Photo first=photos.get(0);
            float firstProp = (float) first.getWidth() / (float) first.getHeight();
            layoutParams.height = (int) ((float) widthScr / firstProp);
            layoutParams.width = (int) (prop * (float) layoutParams.height);

        }
        holder.imagePhoto.setLayoutParams(layoutParams);
        if (ph.getPhoto604() != null) {
            photo = ph.getPhoto604();
        } else if (ph.getPhoto130() != null) {
            photo = ph.getPhoto130();
        } else {
            photo = ph.getPhoto75();
        }


        Picasso.with(holder.itemView.getContext()).load(photo).into(holder.imagePhoto);
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
