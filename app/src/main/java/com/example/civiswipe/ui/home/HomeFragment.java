package com.example.civiswipe.ui.home;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.civiswipe.Issue;
import com.example.civiswipe.R;
import com.example.civiswipe.ui.dashboard.DashboardViewModel;
import com.example.civiswipe.ui.notifications.IssueDetails;

/*
Home Fragment displays users uploaded issues and links to the issue itself.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    private DashboardViewModel homeviewModel;
    private TextView issueText;
    private ImageView img;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeviewModel =  ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        issueText = root.findViewById(R.id.home_issuetitle);
        issueText.setText("Tree on road\n 666 Academy Way");
        img = root.findViewById(R.id.home_img);
        img.setImageResource(R.drawable.treeonroad);
        issueText.setOnClickListener(this);
        img.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), IssueDetails.class);
        intent.putExtra("calling-activity", 1);
        startActivity(intent);
    }
}
