package com.example.odmen.chitay4ch.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.odmen.chitay4ch.R;
import com.example.odmen.chitay4ch.Wall.Audio;
import com.example.odmen.chitay4ch.Wall.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odmen on 31.10.2017.
 */

public class AdapterAudio extends RecyclerView.Adapter<AdapterAudio.ViewHolder> {
    List<Audio> audios = new ArrayList<>();
    private int mRowIndex = -1;

    public void setData(List<Audio> data) {
        audios.clear();
        audios.addAll(data);
        notifyDataSetChanged();
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemaudio, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title=audios.get(position).getTitle();
        if(title.length()>1) {
            holder.title.setText(title);
        }
        String artist=audios.get(position).getArtist();
        if (artist.length()>1) {
            holder.artist.setText(artist);
        }

    }

    @Override
    public int getItemCount() {
        return audios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView artist, title;

        public ViewHolder(View itemView) {
            super(itemView);
            artist = (TextView) itemView.findViewById(R.id.audioArtist);
            title = (TextView) itemView.findViewById(R.id.audioTitle);
        }
    }
}
