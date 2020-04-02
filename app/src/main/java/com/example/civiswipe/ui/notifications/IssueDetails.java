package com.example.civiswipe.ui.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.civiswipe.R;
import com.example.civiswipe.ui.comments.commentThread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class IssueDetails extends AppCompatActivity implements View.OnClickListener{

    private ImageView img;
    private Button comment;
    private TextView title, loc, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_details);
        img = findViewById(R.id.image);
        title = findViewById(R.id.title);
        loc = findViewById(R.id.location);
        ImageButton back = (ImageButton) findViewById(R.id.backArrow);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //sending the number of questions to the quiz class if the a selection has been made
                //Intent intent =new Intent(commentThread.this, MainActivity.class);
                //startActivity(intent);
                finish();}

        });
        description = findViewById(R.id.description);
        int calling = getIntent().getIntExtra("calling-activity", 0);
        if(calling==1){
            readFile();
        }
        comment = findViewById(R.id.comments2);
        comment.setOnClickListener(this);
    }

    public void readFile()
    {
        img.setImageResource(R.drawable.treeonroad);
        try {
            FileInputStream fin = new FileInputStream(new File("/data/data/com.example.civiswipe/files/savedsubmissions.txt"));
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader buff = new BufferedReader(in);
            String csv = buff.readLine();
            String[] arr = csv.split(",");
            title.setText(arr[0]);
            loc.setText(arr[1]);
            description.setText(arr[2]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, commentThread.class);
        startActivity(intent);
    }
}
