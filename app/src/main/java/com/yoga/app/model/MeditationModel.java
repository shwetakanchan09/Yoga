package com.yoga.app.model;

import com.google.gson.annotations.SerializedName;

public class MeditationModel {
    @SerializedName("id")
    String id;

    @SerializedName("category")
    String category;

    @SerializedName("title")
    String title;

    @SerializedName("video_link")
    String videoLink;

    @SerializedName("img")
    String img;


    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }
    public String getVideoLink() {
        return videoLink;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getImg() {
        return img;
    }

}
