package com.example.civiswipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class SubmitIssue extends AppCompatActivity {
/*
note: this class is redundant since the layout we're using involves fragments.
see NewIssueFragment.java and NewIssueViewModel.java instead
submission form with Title, Photo, Description, and Location fields
 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_issue);

        getIntent();

    }

    public void submit(View v) { //triggered when user submits form


        //return to main activity
        finish();
    }
}
