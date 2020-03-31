package com.example.civiswipe.ui.comments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.civiswipe.R;

import java.util.ArrayList;

public class CommentListAdapter extends ArrayAdapter<Comment> {

    private static final String TAG = "CommentListAdapter";

    private Context mContext;
    int mResource;
    Resources ress;

    public CommentListAdapter( Context context, int resource, ArrayList<Comment> objects, Resources res ) {
        super(context, resource, objects);
        mContext= context;
        mResource = resource;
        ress = res;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String userImg = getItem(position).getUserImgLocation();
        String userId = getItem(position).getUserId();
        String comment = getItem(position).getComment();

        Comment userComment = new Comment(userImg,userId,comment);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        // grabbing the img
        String myDrawableName = userImg;
        int resID = ress.getIdentifier(myDrawableName , "drawable", commentThread.PACKAGE_NAME);
        Drawable drawable = ress.getDrawable(resID);

        // finding the views by their ids
        ImageView imageViewUserImg = (ImageView) convertView.findViewById(R.id.pic);
        TextView textViewUserId = (TextView) convertView.findViewById(R.id.commentUsername);
        TextView textViewUserComment = (TextView) convertView.findViewById(R.id.commentComment);
        ListView subCommentList = (ListView) convertView.findViewById(R.id.subListThing);
        // setting the image and text views
        imageViewUserImg.setImageDrawable(drawable);
        textViewUserId.setText(userId);
        textViewUserComment.setText(comment);

        // setting the sub comments
        if(getItem(position).getCommentArrayList() != null){

            SubCommentListAdapter subCommentArrayAdapter = new SubCommentListAdapter(mContext, R.layout.sub_comment, getItem(position).getCommentArrayList(), ress);
            subCommentList.setAdapter(subCommentArrayAdapter);


        }

        return convertView;
    }
}
