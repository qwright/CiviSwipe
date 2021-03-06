package com.example.civiswipe.ui.comments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.civiswipe.R;

import java.util.ArrayList;

import static com.example.civiswipe.R.id.commentBox;
import static com.example.civiswipe.R.layout.individual_comment;


public class commentThread extends AppCompatActivity {

    // omg please work, this is the package name. Making it a global class so the getView method
    // in the CommentListAdapter class can set imageViews for user profile pictures
    public static String PACKAGE_NAME;
    private EditText newComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PACKAGE_NAME = getApplicationContext().getPackageName();

        setContentView(R.layout.activity_comment_thread);
        newComment  = (EditText) findViewById(commentBox);
        ImageButton back = (ImageButton) findViewById(R.id.backArrow);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //sending the number of questions to the quiz class if the a selection has been made
                //Intent intent =new Intent(commentThread.this, MainActivity.class);
                //startActivity(intent);
                finish();}

        });


        Comment karen = new Comment("pp1","Karen Phillips", "So glad this has been reported on!");
            karen.addSubComment( new Comment("pp4","Sue Johnson", "Why are you waiting on other people to report issues? You should be the one to do so. "));
            //karen.addSubComment( new Comment("pp1","Karen Phillips", "I was going to get around to it this week! Just needed to stop and take the picture."));
        Comment kate = new Comment("pp2","Kate Williams", "Hasn't there been enough damage done to the community?");
        Comment greg = new Comment("pp3","Greg Peteson", "What exactly is the big deal anyways?");
        Comment sue = new Comment("pp4","Sue Johnson", "Where exactly was the picture taken?");
            sue.addSubComment( new Comment("pp3","Greg Peteson", "The SE corner of Lakeview and Parkside i believe!"));
           // sue.addSubComment(new Comment("pp2","Misty Williams", "Are you sure Greg? Seems like they took it from the schoolyard there"));
            //sue.addSubComment( new Comment("pp4","Sue Johnson", "I think Misty is right. In any case, thanks you both so much I really appreciate the help!!! "));
        Comment karen2 = new Comment("pp1","Karen Phillips", "I just wanted to say that I am very grateful for the person who posted this issue. I really do appreciate you getting the word out.");

        final ArrayList<Comment> commentArrayList = new ArrayList<>();
        commentArrayList.add(karen);
        commentArrayList.add(kate);
        commentArrayList.add(greg);
        commentArrayList.add(sue);
        commentArrayList.add(karen2);


        // going to the custom comment adapter
        Resources res = getResources();

        final CommentListAdapter commentAdapter = new CommentListAdapter(this, individual_comment, commentArrayList, res);
        ListView myListView = (ListView) findViewById(R.id.commentList);
        myListView.setAdapter(commentAdapter);

        Button submitBtn = (Button) findViewById(R.id.postBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment = newComment.getText().toString();
                Comment nComment = new Comment("pp5","Nick Foster", comment);
                commentArrayList.add(nComment);
                commentAdapter.notifyDataSetChanged();
                newComment.setText("");


                Context context = getApplicationContext();
                CharSequence text = "Comment Posted!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();


            }

        });

    }

    }

