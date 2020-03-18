package com.example.civiswipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class SubmitIssue extends AppCompatActivity {
/*
submission form with Title, Photo, Description, and Location fields
 */
//TODO: create each element
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_issue);

        getIntent();
        //TODO: initialize each element's view
    }

    public void submit(View v) { //triggered when user submits form
        //TODO: check that each mandatory field is filled

        //TODO: write data to text file

        //TODO: save image to data directory?

        //return to main activity
        finish();
    }
}
