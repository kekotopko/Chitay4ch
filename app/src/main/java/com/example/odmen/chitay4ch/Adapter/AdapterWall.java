package com.example.odmen.chitay4ch.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.odmen.chitay4ch.R;
import com.example.odmen.chitay4ch.Wall.Post;

import java.util.List;

/**
 * Created by odmen on 20.10.2017.
 */

public class AdapterWall extends RecyclerView.Adapter<AdapterWall.ViewHolder> {
    private List<Post> posts;

    public AdapterWall(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemwall, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.name.setText(post.getText());
        holder.textdate.setText(String.valueOf(post.getDate()));

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, textdate;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text);
            textdate = (TextView) itemView.findViewById(R.id.textdate);
            image = (ImageView) itemView.findViewById(R.id.imageWall);
        }
    }

}
