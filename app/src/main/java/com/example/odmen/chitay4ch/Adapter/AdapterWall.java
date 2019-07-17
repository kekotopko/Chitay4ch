package com.example.odmen.chitay4ch.Adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.odmen.chitay4ch.R;
import com.example.odmen.chitay4ch.Wall.Groups;
import com.example.odmen.chitay4ch.Wall.Post;
import com.example.odmen.chitay4ch.Wall.Profiles;
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
    private TitleClick titleClick;
    private AdapterHorizontalVideo.ClickVideo clickVideo;

    public void setClickGif(AdapterGif.ClickGif clickGif) {
        this.clickGif = clickGif;
    }

    public void setClickVideo(AdapterHorizontalVideo.ClickVideo clickVideo) {
        this.clickVideo = clickVideo;
    }

    public AdapterWall(List<Post> posts, List<Profiles> profilesList, List<Groups> groupsList, LoadClick loadClick, TitleClick titleClick) {
        this.posts = posts;
        this.loadClick = loadClick;
        this.profilesList = profilesList;
        this.titleClick = titleClick;
        this.groupsList = groupsList;
    }


    public interface LoadClick {
        void getOldpost();
    }

    public interface TitleClick {
        void getOwner(Post post);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemwall, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Post post = posts.get(position);
        long date = posts.get(position).getDate() * 1000;
        Date date1 = new Date(date);
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy  HH:mm", Locale.ENGLISH);
        if (posts.get(position).getText().length() < 1) {
            holder.text_layout.setVisibility(View.GONE);
            holder.textWall.setVisibility(View.GONE);
        } else {
            holder.text_layout.setVisibility(View.VISIBLE);
            holder.textWall.setText(posts.get(position).getText());
            holder.textWall.setVisibility(View.VISIBLE);
        }

        holder.textdate.setText(String.valueOf(dateFormat.format(date1).toLowerCase()));

        holder.adapterHorizontalPhoto.setData(posts.get(position).getlistphoto());
        holder.adapterHorizontalPhoto.setRowIndex(position);



        if(post.getPollList()!=null){
            holder.adapterPoll.setData(post.getPollList().getAnswers());
            holder.textPoll.setText(post.getPollList().getQuestion());
        }



        holder.adapterAudio.setData(posts.get(position).getlistaudio());
        holder.adapterAudio.setRowIndex(position);

        holder.horizontalVideo.setData(posts.get(position).getlistvideo());
        holder.horizontalVideo.setRowIndex(position);
        holder.countLikes.setText(String.valueOf(post.likes.getLikes()));
       holder.countViews.setText(String.valueOf(post.reposts_count.getCount()));




        holder.adapterGif.setData(posts.get(position).getdocList());
        holder.adapterGif.setClickGif(clickGif);
        holder.horizontalVideo.setClickVideo(clickVideo);

        holder.title_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleClick.getOwner(post);
            }
        });

        for (int i = 0; i < profilesList.size(); i++) {
            if (post.getFrom_id() == profilesList.get(i).getId()) {
                name = profilesList.get(i).getName();
                image = profilesList.get(i).getPhoto_50();

            }
        }
        for (int i = 0; i < groupsList.size(); i++) {
            Log.d("kekotopko", groupsList.get(i).getName());
            if (-post.getFrom_id() == groupsList.get(i).getId()) {
                name = groupsList.get(i).getName();
                image = groupsList.get(i).getPhoto_50();

            }
        }

        holder.textname.setText(name);
        Picasso.with(holder.image.getContext()).load(image).into(holder.image);


        if (posts.get(position).getlistphoto().isEmpty()) {
            holder.horizontallist.setVisibility(View.GONE);
        } else {
            holder.horizontallist.setVisibility(View.VISIBLE);
            SnapHelper helper=new PagerSnapHelper();
            holder.horizontallist.setOnFlingListener(null);
            helper.attachToRecyclerView(holder.horizontallist);
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
            SnapHelper helper_vid=new PagerSnapHelper();
            holder.videolist.setOnFlingListener(null);
            helper_vid.attachToRecyclerView(holder.videolist);
        }

        if (posts.get(position).getdocList().isEmpty()) {
            holder.listgif.setVisibility(View.GONE);
        } else {
            holder.listgif.setVisibility(View.VISIBLE);
        }

        if (post.getPollList()!=null) {
            holder.layout_poll.setVisibility(View.VISIBLE);
        } else {
            holder.layout_poll.setVisibility(View.GONE);
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
        SnapHelper rep_helper=new PagerSnapHelper();
        holder.rephorizontallist.setOnFlingListener(null);
        rep_helper.attachToRecyclerView(holder.rephorizontallist);

        //holder.adapterHorizontalPhoto.setRowIndex(position);

        holder.adapterAudio.setData(post.getlistaudio());
        //holder.adapterAudio.setRowIndex(position);

        holder.horizontalVideo.setData(post.getlistvideo());
        SnapHelper rep_helper_vid=new PagerSnapHelper();
        holder.repvideolist.setOnFlingListener(null);
        rep_helper_vid.attachToRecyclerView(holder.repvideolist);
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
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textWall, textdate, textname, countLikes,textPoll, countViews;
        public ImageView image;

        RecyclerView horizontallist, audiolist, videolist, listgif, poolList;
        private AdapterAudio adapterAudio;
        private AdapterHorizontalPhoto adapterHorizontalPhoto;
        private AdapterHorizontalVideo horizontalVideo;
        private AdapterGif adapterGif;
        private AdapterPoll adapterPoll;
        LinearLayout copyhistory;
        LinearLayout text_layout;
        RelativeLayout title_layout,layout_poll;


        public ViewHolder(View itemView) {
            super(itemView);
            countViews= (TextView) itemView.findViewById(R.id.countViews);
            countLikes = (TextView) itemView.findViewById(R.id.countLikes);
            textWall = (TextView) itemView.findViewById(R.id.text);
            textdate = (TextView) itemView.findViewById(R.id.textdate);
            image = (ImageView) itemView.findViewById(R.id.imageAvatar);
            textPoll= (TextView) itemView.findViewById(R.id.TextPoll);
            textname = (TextView) itemView.findViewById(R.id.textname);
            title_layout = (RelativeLayout) itemView.findViewById(R.id.title);
            copyhistory = (LinearLayout) itemView.findViewById(R.id.copyhistory);
            text_layout = (LinearLayout) itemView.findViewById(R.id.text_layout);
            layout_poll= (RelativeLayout) itemView.findViewById(R.id.layout_poll);
            poolList = (RecyclerView) itemView.findViewById(R.id.pollList);
            if (poolList != null) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);
                poolList.setLayoutManager(layoutManager);
                adapterPoll = new AdapterPoll();
                poolList.setAdapter(adapterPoll);
            }


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

//    public static class BtnHolder {
//        ImageView imageBtn;
//
//        public BtnHolder(View itemView) {
//            imageBtn = (ImageView) itemView.findViewById(R.id.imagebtnLoad);
//        }
//    }
//
//    private void bindBtnHolder(BtnHolder holder) {
//
//        holder.imageBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadClick.getOldpost();
//            }
//        }
//
//    }
}

