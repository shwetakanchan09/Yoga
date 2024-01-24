package com.yoga.app.model;

import com.google.gson.annotations.SerializedName;

public class YogaModel {
    @SerializedName("id")
    String id;

    @SerializedName("category")
    String category;

    @SerializedName("sub_category")
    String subCategory;

    @SerializedName("title")
    String title;

    @SerializedName("video_link")
    String videoLink;

    @SerializedName("img")
    String img;

    @SerializedName("popular")
    String popular;

    @SerializedName("weight_loss")
    String weightLoss;


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

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
    public String getSubCategory() {
        return subCategory;
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

    public void setPopular(String popular) {
        this.popular = popular;
    }
    public String getPopular() {
        return popular;
    }

    public void setWeightLoss(String weightLoss) {
        this.weightLoss = weightLoss;
    }
    public String getWeightLoss() {
        return weightLoss;
    }

}

