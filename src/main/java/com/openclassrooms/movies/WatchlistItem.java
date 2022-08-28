package com.openclassrooms.movies;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class WatchlistItem {

    @NotBlank(message="Please enter the title")
    private String Title;
    private String Rating;
    private String Priority;
    @Size(max=50, message="The maximum allowed length is 50")
    private String Comment;
    public  Integer id=0;

    public WatchlistItem(String title, String rating, String priority, String comment, int id) {
        Title = title;
        Rating = rating;
        Priority = priority;
        Comment = comment;
        this.id = id;
    }

    public WatchlistItem() {

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }
}
