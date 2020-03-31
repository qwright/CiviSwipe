package com.example.civiswipe.ui.dashboard;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.civiswipe.Issue;
import com.example.civiswipe.MainActivity;
import com.example.civiswipe.R;
import com.example.civiswipe.ui.comments.commentThread;
import com.example.civiswipe.ui.notifications.IssueDetails;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

import static android.graphics.Color.argb;


public class DashboardFragment extends Fragment implements View.OnClickListener{
    private static DashboardFragment dash;
    private ImageButton like, dislike;
    private Button comments;
    private ImageView imgView;
    private Integer[] imgList;
    private Stack<Integer> cardstack;
    private DashboardViewModel dashboardViewModel;
    private View likeDiv, disDiv;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //swipe
        imgView = root.findViewById(R.id.cardStack);
        final GestureDetector ges = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener(){
            private int CLICK_ACTION_THRESHHOLD = 200;
            private int MIN_SWIPE_DISTANCE_X = 100;
            private int MAX_SWIPE_DISTANCE_X = 1000;
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velX, float velY){
                float deltaX = e1.getX() - e2.getX();
                float deltaXabs = Math.abs(deltaX);

                if(deltaXabs >= MIN_SWIPE_DISTANCE_X && deltaXabs <= MAX_SWIPE_DISTANCE_X){
                    if(deltaX > 0 ){
                        //swipe right
                        swipeLike();
                    }else{
                        //swipe left
                        swipeDislike();
                    }
                }
                return super.onFling(e1,e2, velX, velY);
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                getIssue();
                return super.onSingleTapUp(e);
            }
        });
        imgView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ges.onTouchEvent(event);
                return true;
            }

        });

        //buttons
        like = root.findViewById(R.id.like_button);
        like.setOnClickListener(this);
        dislike = root.findViewById(R.id.dislike_button);
        dislike.setOnClickListener(this);
        // added in the comments button
        comments = root.findViewById(R.id.comments);
        comments.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v){
                                            //sending the number of questions to the quiz class if the a selection has been made
                                            Intent intent =new Intent(getActivity(), commentThread.class);
                                            startActivity(intent);
                                            };});
        //available images
        imgList = new Integer[]{
                R.drawable.broken_light,
                R.drawable.broken_sidewalk,
                R.drawable.broken_store,
                R.drawable.spray_paint
        };
        //init stack of cards
        generateStack();
        imgView.setImageResource(cardstack.pop());

        //dividers note: uses natural gesture is NATURAL so colors and logic needs to be inverted (for this project)
        likeDiv = root.findViewById(R.id.divider2);
        disDiv = root.findViewById(R.id.divider3);
        return root;
    }
    /*
    generateStack() will repopulate with "known" issues for this prototype. Uploaded issues should be visible to user under a different menu
     */
    public void generateStack() {
        cardstack = new Stack<>();
        Collections.shuffle(Arrays.asList(imgList));
        for(int i:imgList){
            cardstack.push(i);
        }
    }
    /*
    Get issue will direct to the issue's other resources via its image.
     */
    public void getIssue()
    {
        Intent intent = new Intent(getActivity(), IssueDetails.class);
        startActivity(intent);
    }
    /*
    onClick handles buttons
     */
    @Override
    public void onClick(View v) {
        if (cardstack.size() > 0) {
            Integer pic = cardstack.pop();
            imgView.setImageResource(pic);
        } else {
            imgView.setImageResource(R.drawable.nomore);
        }
    }

    public void swipeLike()
    {
        ObjectAnimator colorFade = ObjectAnimator.ofObject(disDiv, "backgroundColor", new ArgbEvaluator(), Color.argb(255,255,51,51), 0x00000000);
        colorFade.setDuration(500);
        colorFade.start();
        if (cardstack.size() > 0) {
            Integer pic = cardstack.pop();
            imgView.setImageResource(pic);
        }else{
            imgView.setImageResource(R.drawable.nomore);
        }
    }

    public void swipeDislike(){
        ObjectAnimator colorFade = ObjectAnimator.ofObject(likeDiv, "backgroundColor", new ArgbEvaluator(), Color.argb(255,124,252,0), 0x00000000);
        colorFade.setDuration(500);
        colorFade.start();
        if (cardstack.size() > 0) {
            Integer pic = cardstack.pop();
            imgView.setImageResource(pic);
        }else{
            imgView.setImageResource(R.drawable.nomore);
        }
    }

    public static DashboardFragment getInstance()
    {
        return dash;
    }
}