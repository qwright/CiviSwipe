package com.example.civiswipe.ui.comments;

import java.util.ArrayList;

public class Comment {

    String userImgLocation;
    String userId;
    String comment;
    ArrayList<Comment> commentArrayList;

    public Comment(){}
    public Comment(String UserImg, String userId, String comment){
        this.comment = comment;
        this.userId  = userId;
        this.userImgLocation = UserImg;
        commentArrayList = new ArrayList<>();
    }

    public String getUserImgLocation(){
        return userImgLocation;
    }

    public String getComment() {
        return comment;
    }

    public String getUserId() {
        return userId;
    }


    public void setUserImgLocation(String userImg){ this.userImgLocation = userImg;}

    public void setComment(String comment) {
this.comment = comment;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    // adding a subcomment or reading it
    public void addSubComment(Comment comment){commentArrayList.add(comment);}

    public ArrayList<Comment> getCommentArrayList() {return commentArrayList;}



}
