package com.example.civiswipe;

public class Comment {


    String userId;
    String comment;

    public Comment(){}
    public Comment(String userId, String comment){
        this.comment = comment;
        this.userId  = userId;
    }

    public String getComment() {
        return comment;
    }

    public String getUserId() {
        return userId;
    }
    public void setComment(String comment) {
this.comment = comment;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
