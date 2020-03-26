package com.example.civiswipe.ui.home;

import androidx.lifecycle.ViewModelProviders;

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
/*
Home Fragment displays users uploaded issues and links to the issue itself.
 */
public class HomeFragment extends Fragment {
    private DashboardViewModel homeviewModel;
    private TextView issueText;
    private ImageView img;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeviewModel =  ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        issueText = root.findViewById(R.id.home_issuetitle);
        img = root.findViewById(R.id.home_img);
        return root;
    }
    /*
    Setup will depend on how issue is saved. Should generate at least one dynamic uploaded issue...

    public void setUserIssue()
    {
        Issue issue = null;
        if(issue != null){
            issueText.setText(issue.getTitle());
            //image will depend on how it is passed from Issue
            img.setImageResource(issue.getImage());
        }
    }
     */
}
