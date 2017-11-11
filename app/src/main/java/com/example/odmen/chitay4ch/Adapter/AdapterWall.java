package com.example.odmen.chitay4ch.Adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.odmen.chitay4ch.Groups.Group;
import com.example.odmen.chitay4ch.R;
import com.example.odmen.chitay4ch.Users.Users;
import com.example.odmen.chitay4ch.Wall.Groups;
import com.example.odmen.chitay4ch.Wall.Post;
import com.example.odmen.chitay4ch.Wall.Profiles;
import com.example.odmen.chitay4ch.Wall.Response;
import com.felipecsl.gifimageview.library.GifImageView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by odmen on 20.10.2017.
 */

public class AdapterWall extends RecyclerView.Adapter<AdapterWall.ViewHolder> {
    private List<Post> posts;
    private LoadClick loadClick;
    private String name;
    private String image;
    private List<Profiles> profilesList;
    private List<Groups> groupsList;
    private AdapterGif.ClickGif clickGif;

    public void setClickGif(AdapterGif.ClickGif clickGif) {
        this.clickGif = clickGif;
    }

    public AdapterWall(List<Post> posts, List<Profiles> profilesList, List<Groups> groupsList, String image, String name, LoadClick loadClick) {
        this.posts = posts;
        this.image=image;
        this.loadClick = loadClick;
        this.name = name;
        this.profilesList = profilesList;
        this.groupsList = groupsList;
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
            Post post = posts.get(position);
            long date = posts.get(position).getDate() * 1000;
            Date date1 = new Date(date);
            DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy  HH:mm", Locale.ENGLISH);
            if (posts.get(position).getText().length() < 1) {
                holder.textWall.setVisibility(View.GONE);
            } else {
                holder.textWall.setText(posts.get(position).getText());
                holder.textWall.setVisibility(View.VISIBLE);
            }
            Picasso.with(holder.image.getContext()).load(image).into(holder.image);
            holder.textdate.setText(String.valueOf(dateFormat.format(date1).toLowerCase()));

            holder.adapterHorizontalPhoto.setData(posts.get(position).getlistphoto());
            holder.adapterHorizontalPhoto.setRowIndex(position);

            holder.adapterAudio.setData(posts.get(position).getlistaudio());
            holder.adapterAudio.setRowIndex(position);

            holder.horizontalVideo.setData(posts.get(position).getlistvideo());
            holder.horizontalVideo.setRowIndex(position);


            holder.adapterGif.setData(posts.get(position).getdocList());
            holder.adapterGif.setClickGif(clickGif);


            holder.textname.setText(name);
            if (posts.get(position).getlistphoto().isEmpty()) {
                holder.horizontallist.setVisibility(View.GONE);
            } else {
                holder.horizontallist.setVisibility(View.VISIBLE);
            }


            if (posts.get(position).getlistaudio().isEmpty()) {
                holder.audiolist.setVisibility(View.GONE);
            } else {
                holder.audiolist.setVisibility(View.VISIBLE);
            }

            if (posts.get(position).getlistvideo().isEmpty()) {
                holder.videolist.setVisibility(View.GONE);

            } else {
                holder.videolist.setVisibility(View.VISIBLE);
            }

            if (posts.get(position).getdocList().isEmpty()) {
                holder.listgif.setVisibility(View.GONE);
            } else {
                holder.listgif.setVisibility(View.VISIBLE);
            }
            LayoutInflater layoutInflater = LayoutInflater.from(holder.itemView.getContext());
            List<Post> repost = post.getReposts() == null ? new ArrayList<Post>() : post.getReposts();
            int countrep = repost.size() - holder.copyhistory.getChildCount();
            if (countrep > 0) {
                for (int i = 0; i < countrep; i++) {
                    View v = layoutInflater.inflate(R.layout.itemrepost, holder.copyhistory, false);
                    holder.copyhistory.addView(v);
                }
            }
            for (int e = 0; e < holder.copyhistory.getChildCount(); e++) {
                View v = holder.copyhistory.getChildAt(e);
                if (e < repost.size()) {
                    v.setVisibility(View.VISIBLE);
                    RepostHolder h = new RepostHolder(v);
                    onBindViewHolder(h, repost.get(e));
                } else {
                    v.setVisibility(View.GONE);
                }
            }

        }
    }


    public void onBindViewHolder(RepostHolder holder, Post post) {

        long date = post.getDate() * 1000;
        Date date1 = new Date(date);
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy  HH:mm", Locale.ENGLISH);
        if (post.getText().length() < 1) {
            holder.reptextWall.setVisibility(View.GONE);
        } else {
            holder.reptextWall.setText(post.getText());
            holder.reptextWall.setVisibility(View.VISIBLE);
        }
        String image = null;
        String repname = null;
        for (int i = 0; i < profilesList.size(); i++) {
            Profiles profiles = profilesList.get(i);

            if (post.getOwner_id() == profiles.getId()) {
                if (profiles.getPhoto_200() != null) {
                    image = profiles.getPhoto_200();
                } else if (profiles.getPhoto_100() != null) {
                    image = profiles.getPhoto_100();
                } else {
                    image = profiles.getPhoto_50();
                }
                repname = profiles.getName();
            }

        }

        for (int i = 0; i < groupsList.size(); i++) {
            Groups groups = groupsList.get(i);
            Log.d("lestok", groupsList.get(i).getName());

            if (post.getOwner_id() == groups.getId() * (-1)) {
                if (groups.getPhoto_200() != null) {
                    image = groups.getPhoto_200();
                } else if (groups.getPhoto_100() != null) {
                    image = groups.getPhoto_100();
                } else {
                    image = groups.getPhoto_50();
                }
                repname = groups.getName();
            }

        }


        Picasso.with(holder.repimage.getContext()).load(image).into(holder.repimage);
        holder.reptextname.setText(repname);


        holder.reptextdate.setText(String.valueOf(dateFormat.format(date1).toLowerCase()));

        holder.adapterHorizontalPhoto.setData(post.getlistphoto());
        //holder.adapterHorizontalPhoto.setRowIndex(position);

        holder.adapterAudio.setData(post.getlistaudio());
        //holder.adapterAudio.setRowIndex(position);

        holder.horizontalVideo.setData(post.getlistvideo());
        //holder.horizontalVideo.setRowIndex(position);


        if (post.getlistphoto().isEmpty()) {
            holder.rephorizontallist.setVisibility(View.GONE);
        } else {
            holder.rephorizontallist.setVisibility(View.VISIBLE);
        }


        if (post.getlistaudio().isEmpty()) {
            holder.repaudiolist.setVisibility(View.GONE);
        } else {
            holder.repaudiolist.setVisibility(View.VISIBLE);
        }

        if (post.getlistvideo().isEmpty()) {
            holder.repvideolist.setVisibility(View.GONE);

        } else {
            holder.repvideolist.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return posts.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textWall, textdate, textname;
        public ImageView image, imageBtn;

        RecyclerView horizontallist, audiolist, videolist, listgif;
        private AdapterAudio adapterAudio;
        private AdapterHorizontalPhoto adapterHorizontalPhoto;
        private AdapterHorizontalVideo horizontalVideo;
        private AdapterGif adapterGif;
        LinearLayout copyhistory;


        public ViewHolder(View itemView) {
            super(itemView);
            textWall = (TextView) itemView.findViewById(R.id.text);
            textdate = (TextView) itemView.findViewById(R.id.textdate);
            image = (ImageView) itemView.findViewById(R.id.imageAvatar);

            textname = (TextView) itemView.findViewById(R.id.textname);
            imageBtn = (ImageView) itemView.findViewById(R.id.imagebtnLoad);
            copyhistory = (LinearLayout) itemView.findViewById(R.id.copyhistory);


            horizontallist = (RecyclerView) itemView.findViewById(R.id.listPhoto);
            if (horizontallist != null) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
                horizontallist.setLayoutManager(layoutManager);
                adapterHorizontalPhoto = new AdapterHorizontalPhoto();
                horizontallist.setAdapter(adapterHorizontalPhoto);
            }
            audiolist = (RecyclerView) itemView.findViewById(R.id.listaudio);
            if (audiolist != null) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);
                audiolist.setLayoutManager(layoutManager);
                adapterAudio = new AdapterAudio();
                audiolist.setAdapter(adapterAudio);
            }
            videolist = (RecyclerView) itemView.findViewById(R.id.listvideo);
            if (videolist != null) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
                videolist.setLayoutManager(layoutManager);
                horizontalVideo = new AdapterHorizontalVideo();
                videolist.setAdapter(horizontalVideo);
            }

            listgif = (RecyclerView) itemView.findViewById(R.id.listgif);
            if (listgif != null) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);
                listgif.setLayoutManager(layoutManager);
                adapterGif = new AdapterGif();
                listgif.setAdapter(adapterGif);
            }


        }


    }


    public static class RepostHolder {
        public TextView reptextWall, reptextdate, reptextname;
        public ImageView repimage;
        RecyclerView rephorizontallist, repaudiolist, repvideolist;
        private AdapterAudio adapterAudio;
        private AdapterHorizontalPhoto adapterHorizontalPhoto;
        private AdapterHorizontalVideo horizontalVideo;
        View itemview;


        public RepostHolder(View itemView) {
            itemview = itemView;
            reptextWall = (TextView) itemView.findViewById(R.id.reptext);
            reptextname = (TextView) itemView.findViewById(R.id.reptextname);
            reptextdate = (TextView) itemView.findViewById(R.id.reptextdate);
            repimage = (ImageView) itemView.findViewById(R.id.repimageAvatar);
            repaudiolist = (RecyclerView) itemView.findViewById(R.id.replistaudio);


            rephorizontallist = (RecyclerView) itemView.findViewById(R.id.replistPhoto);
            if (rephorizontallist != null) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
                rephorizontallist.setLayoutManager(layoutManager);
                adapterHorizontalPhoto = new AdapterHorizontalPhoto();
                rephorizontallist.setAdapter(adapterHorizontalPhoto);
            }
            repaudiolist = (RecyclerView) itemView.findViewById(R.id.replistaudio);
            if (repaudiolist != null) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);
                repaudiolist.setLayoutManager(layoutManager);
                adapterAudio = new AdapterAudio();
                repaudiolist.setAdapter(adapterAudio);
            }
            repvideolist = (RecyclerView) itemView.findViewById(R.id.replistvideo);
            if (repvideolist != null) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
                repvideolist.setLayoutManager(layoutManager);
                horizontalVideo = new AdapterHorizontalVideo();
                repvideolist.setAdapter(horizontalVideo);
            }

        }
    }


    @Override
    public int getItemViewType(int position) {
        return (position == posts.size()) ? R.layout.btnloadpost : R.layout.itemwall;
    }
}

