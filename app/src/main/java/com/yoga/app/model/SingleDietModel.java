package com.yoga.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleDietModel {

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

    @SerializedName("popular")
    String popular;

    @SerializedName("you_must_try")
    String youMustTry;

    @SerializedName("ingredients")
    List<String> ingredients;

    @SerializedName("instructions")
    List<String> instructions;

    @SerializedName("nutritions")
    List<String> nutritions;

    @SerializedName("veg")
    String veg;

    @SerializedName("up_next")
    List<UpNext> upNext;


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

    public void setPopular(String popular) {
        this.popular = popular;
    }
    public String getPopular() {
        return popular;
    }

    public void setYouMustTry(String youMustTry) {
        this.youMustTry = youMustTry;
    }
    public String getYouMustTry() {
        return youMustTry;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
    public List<String> getIngredients() {
        return ingredients;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
    public List<String> getInstructions() {
        return instructions;
    }

    public void setNutritions(List<String> nutritions) {
        this.nutritions = nutritions;
    }
    public List<String> getNutritions() {
        return nutritions;
    }

    public void setVeg(String veg) {
        this.veg = veg;
    }
    public String getVeg() {
        return veg;
    }

    public void setUpNext(List<UpNext> upNext) {
        this.upNext = upNext;
    }
    public List<UpNext> getUpNext() {
        return upNext;
    }

 /*   @SerializedName("id")
    String id;

    @SerializedName("category")
    String category;

    @SerializedName("title")
    String title;

    @SerializedName("video_link")
    String videoLink;

    @SerializedName("img")
    String img;

    @SerializedName("popular")
    String popular;

    @SerializedName("you_must_try")
    String youMustTry;

    @SerializedName("ingredients")
    String ingredients;

    @SerializedName("instructions")
    String instructions;

    @SerializedName("nutritions")
    String nutritions;

    @SerializedName("veg")
    String veg;

    @SerializedName("up_next")
    List<UpNext> upNext;


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

    public void setPopular(String popular) {
        this.popular = popular;
    }
    public String getPopular() {
        return popular;
    }

    public void setYouMustTry(String youMustTry) {
        this.youMustTry = youMustTry;
    }
    public String getYouMustTry() {
        return youMustTry;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public String getIngredients() {
        return ingredients;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public String getInstructions() {
        return instructions;
    }

    public void setNutritions(String nutritions) {
        this.nutritions = nutritions;
    }
    public String getNutritions() {
        return nutritions;
    }

    public void setVeg(String veg) {
        this.veg = veg;
    }
    public String getVeg() {
        return veg;
    }

    public void setUpNext(List<UpNext> upNext) {
        this.upNext = upNext;
    }
    public List<UpNext> getUpNext() {
        return upNext;
    }

*/
}
