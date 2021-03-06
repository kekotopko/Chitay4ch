package com.example.odmen.chitay4ch.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.odmen.chitay4ch.R;
import com.example.odmen.chitay4ch.Wall.Audio;
import com.example.odmen.chitay4ch.Wall.Doc;
import com.example.odmen.chitay4ch.Wall.Sizes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odmen on 04.11.2017.
 */

public class AdapterGif extends RecyclerView.Adapter<AdapterGif.ViewHolder> {
    List<Doc> docList = new ArrayList<>();
    ClickGif clickGif;


    public interface ClickGif {
        void onClick(Doc doc);
    }

    public AdapterGif() {


    }

    public void setData(List<Doc> data) {
        docList.clear();
        docList.addAll(data);
        notifyDataSetChanged();
    }

    public void setClickGif(ClickGif clickGif) {
        this.clickGif = clickGif;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemgif, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Doc doc = docList.get(position);
        holder.giftitle.setText(doc.getTitle());
        Sizes s = new Sizes();
        for (int i = 0; i < doc.getPreview().getPhotoGif().getListsize().size(); i++) {

            if (doc.getPreview().getPhotoGif().getListsize().get(i).getType().equals("s"))
                s = doc.getPreview().getPhotoGif().getListsize().get(i);

        }
        String imgres = s.getSrc();
        Picasso.with(holder.gifimage.getContext()).load(imgres).into(holder.gifimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickGif != null) {
                    clickGif.onClick(doc);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return docList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView giftitle;
        ImageView gifimage;

        public ViewHolder(View itemView) {
            super(itemView);
            giftitle = (TextView) itemView.findViewById(R.id.titlegif);
            gifimage = (ImageView) itemView.findViewById(R.id.imggif);
        }
    }
}
