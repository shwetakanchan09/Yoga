package com.yoga.app.roomdb;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Save implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;
    private String videoLink;
    private String title;
    private String image;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Save(String id, String videoLink, String title, String image) {
        this.id = id;
        this.videoLink = videoLink;
        this.title = title;
        this.image = image;
    }
}
