package com.example.civiswipe;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

import com.example.civiswipe.ui.comments.Comment;

import java.util.LinkedList;

public class Issue {
    private String title, location, description, userId;
    //Might not be imageview... (drawable resource int or image object?)
    private Bitmap image;
    private LinkedList<Comment> thread;

    //Add userid?
    public Issue(String title, String location, Bitmap image, String description){
        this.title = title;
        this.location = location;
        this.image = image;
        this.description = description;
        thread = new LinkedList<Comment>();
    }

    public String getTitle(){
        return title;
    }
    public String getLocation(){
        return location;
    }
    public String getDescription(){
        return description;
    }
    public Bitmap getImage(){
        return image;
    }
    public LinkedList<Comment> getThread(){
        return thread;
    }
    public void addComment(Comment c){
        thread.add(c);
    }

}
