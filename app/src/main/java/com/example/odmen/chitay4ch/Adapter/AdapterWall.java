package com.example.odmen.chitay4ch.Adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.odmen.chitay4ch.Groups.Group;
import com.example.odmen.chitay4ch.R;
import com.example.odmen.chitay4ch.Wall.Post;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by odmen on 20.10.2017.
 */

public class AdapterWall extends RecyclerView.Adapter<AdapterWall.ViewHolder> {
    private List<Post> posts;
    private Group group;
    private LoadClick loadClick;
    private String name;


    public AdapterWall(List<Post> posts, Group group, String name, LoadClick loadClick) {
        this.posts = posts;
        this.group = group;
        this.loadClick = loadClick;
        this.name = name;
    }


    public interface LoadClick {
        void getOldpost();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == R.layout.itemwall) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemwall, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.btnloadpost, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (position == posts.size()) {
            holder.imageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadClick.getOldpost();
                }
            });
        } else {
            long date = posts.get(position).getDate() * 1000;
            Date date1 = new Date(date);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            if (posts.get(position).getText().length() < 1) {
                holder.textWall.setVisibility(View.GONE);
            } else {
                holder.textWall.setText(posts.get(position).getText());
                holder.textWall.setVisibility(View.VISIBLE);
            }
            Picasso.with(holder.image.getContext()).load(group.getPhoto_50()).into(holder.image);
            holder.textdate.setText(String.valueOf(dateFormat.format(date1)));
            holder.adapterHorizontalPhoto.setData(posts.get(position).getlistphoto());
            holder.adapterHorizontalPhoto.setRowIndex(position);
            holder.textname.setText(name);
            if (posts.get(position).getlistphoto().isEmpty()) {
                holder.horizontallist.setVisibility(View.GONE);
            } else {
                holder.horizontallist.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return posts.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textWall, textdate, textname;
        public ImageView image, imageBtn;
        private AdapterHorizontalPhoto adapterHorizontalPhoto;
        RecyclerView horizontallist;


        public ViewHolder(View itemView) {
            super(itemView);
            textWall = (TextView) itemView.findViewById(R.id.text);
            textdate = (TextView) itemView.findViewById(R.id.textdate);
            image = (ImageView) itemView.findViewById(R.id.imageAvatar);
            textname = (TextView) itemView.findViewById(R.id.textname);
            imageBtn = (ImageView) itemView.findViewById(R.id.imagebtnLoad);


            horizontallist = (RecyclerView) itemView.findViewById(R.id.listPhoto);
            if (horizontallist != null) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
                horizontallist.setLayoutManager(layoutManager);
                adapterHorizontalPhoto = new AdapterHorizontalPhoto();
                horizontallist.setAdapter(adapterHorizontalPhoto);
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        return (position == posts.size()) ? R.layout.btnloadpost : R.layout.itemwall;
    }
}
