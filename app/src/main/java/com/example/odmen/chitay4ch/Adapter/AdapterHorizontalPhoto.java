package com.example.odmen.chitay4ch.Adapter;

import android.app.Activity;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.odmen.chitay4ch.R;
import com.example.odmen.chitay4ch.Wall.Attachment;
import com.example.odmen.chitay4ch.Wall.Doc;
import com.example.odmen.chitay4ch.Wall.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

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
        if(data!=null) {
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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Activity activity = (Activity) holder.itemView.getContext();
        ViewGroup.LayoutParams layoutParams = holder.imagePhoto.getLayoutParams();
        float prop = (float) photos.get(position).getWidth() / (float) photos.get(position).getHeight();
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widthScr = size.x;
        if (photos.size() == 1) {
            layoutParams.width = widthScr;
            layoutParams.height = (int) ((float) widthScr / prop);
        } else {

            //float params = holder.imagePhoto.getContext().getResources().getDisplayMetrics().density;
            int height = holder.imagePhoto.getLayoutParams().height;
            layoutParams.width = (int) (prop * height);

        }
        holder.imagePhoto.setLayoutParams(layoutParams);
        if (photos.get(position).getPhoto604() != null) {
            photo = photos.get(position).getPhoto604();
        } else if (photos.get(position).getPhoto130() != null) {
            photo = photos.get(position).getPhoto130();
        } else {
            photo = photos.get(position).getPhoto75();
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
