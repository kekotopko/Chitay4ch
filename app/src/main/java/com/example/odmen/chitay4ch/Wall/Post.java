package com.example.odmen.chitay4ch.Wall;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odmen on 17.10.2017.
 */

public class Post {

    @SerializedName("id")
    int id;
    @SerializedName("from_id")
    long from_id;
    @SerializedName("owner_id")
    long owner_id;
    @SerializedName("date")
    long date;
    @SerializedName("text")
    String text;
    @SerializedName("attachments")
    List<Attachment> attachmentseslist;
    @SerializedName("copy_history")
    List<Post> reposts;

    @SerializedName("likes")
    public Likes likes;

    @SerializedName("reposts")
    public Reposts reposts_count;


    public long getOwner_id() {
        return owner_id;
    }

    public long getDate() {
        return date;
    }

    public long getFrom_id() {
        return from_id;
    }

    public String getText() {
        return text;
    }

    public List<Post> getReposts() {
        return reposts;
    }

    public List<Photo> getlistphoto() {
        List<Photo> photos = new ArrayList<>();
        if (attachmentseslist != null) {
            for (int i = 0; i < attachmentseslist.size(); i++) {
                Attachment attachment = attachmentseslist.get(i);
                if (attachment.getType().equals("photo")) {
                    photos.add(attachment.getPhoto());
                }
            }
        }
        return photos;
    }

    public List<Audio> getlistaudio() {
        List<Audio> audios = new ArrayList<>();
        if (attachmentseslist != null) {
            for (int i = 0; i < attachmentseslist.size(); i++) {
                Attachment attachment = attachmentseslist.get(i);
                if (attachment.getType().equals("audio")) {
                    audios.add(attachment.getAudio());
                }
            }
        }
        return audios;
    }

    public List<Video> getlistvideo() {
        List<Video> videos = new ArrayList<>();
        if (attachmentseslist != null) {
            for (int i = 0; i < attachmentseslist.size(); i++) {
                Attachment attachment = attachmentseslist.get(i);
                if (attachment.getType().equals("video")) {
                    videos.add(attachment.getVideo());
                }
            }
        }
        return videos;
    }

    public List<Doc> getdocList() {
        List<Doc> docs = new ArrayList<>();
        if (attachmentseslist != null) {
            for (int i = 0; i < attachmentseslist.size(); i++) {
                Attachment attachment = attachmentseslist.get(i);
                if (attachment.getType().equals("doc")) {
                    docs.add(attachment.getDoc());
                }
            }
        }
        return docs;
    }

    public Poll getPollList(){
        Poll poll=null;
        if(attachmentseslist!=null){
            for(int i=0;i<attachmentseslist.size();i++){
                Attachment attachment=attachmentseslist.get(i);
                if(attachment.getType().equals("poll")){
                    poll=attachment.getPoll();
                }
            }
        }
        return poll;
    }


}
