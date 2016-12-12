package com.example.abc.mindvalley_aliasheer_android_test.Database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ali Asheer on 12/11/2016.
 */
// Model class for Json data
public class Model {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("liked_by_user")
    @Expose
    private Boolean likedByUser;
    @SerializedName("current_user_collections")
    @Expose
    private List<Object> currentUserCollections = null;
    @SerializedName("urls")
    @Expose
    private Urls urls;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @return The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * @return The color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color The color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return The likes
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * @param likes The likes
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    /**
     * @return The likedByUser
     */
    public Boolean getLikedByUser() {
        return likedByUser;
    }

    /**
     * @param likedByUser The liked_by_user
     */
    public void setLikedByUser(Boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    /**
     * @return The user
     */
    public List<Object> getCurrentUserCollections() {
        return currentUserCollections;
    }

    /**
     * @param currentUserCollections The current_user_collections
     */
    public void setCurrentUserCollections(List<Object> currentUserCollections) {
        this.currentUserCollections = currentUserCollections;
    }
    /**
     *
     * @return
     * The urls
     */
    public Urls getUrls() {
        return urls;
    }

    /**
     *
     * @param urls
     * The urls
     */
    public void setUrls(Urls urls) {
        this.urls = urls;
    }
    public Model(String color){
        this.color=color;
        this.createdAt=createdAt;
        //this.urls.regular=UrlRegular;

    }
   public class Urls{
        @SerializedName("raw")
        @Expose
        private String raw;
        @SerializedName("full")
        @Expose
        private String full;
        @SerializedName("regular")
        @Expose
        private String regular;

        public String getRaw() {
            return raw;
        }
        public String getFull() {
            return full;
        }
        public String getRegular() {
            return regular;
        }
        public void setRaw(String raw) {
            this.raw = raw;
        }
        public void setFull(String full) {
            this.full = full;
        }
        public void setRegular(String regular) {
            this.regular = regular;
        }


    }
    public Model() {

    }

}
