package com.example.civiswipe.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.civiswipe.MainActivity;
import com.example.civiswipe.R;
import com.example.civiswipe.commentThread;

public class NotificationsFragment extends Fragment implements View.OnClickListener{
    TextView issue;
    private NotificationsViewModel notificationsViewModel;
    Intent intent;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        /*final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }*/
        intent = new Intent(getActivity(), IssueDetails.class);
        issue = root.findViewById(R.id.issue1);
        issue.setOnClickListener(this);
        return root;

    }

    @Override
    public void onClick(View v) {
        startActivity(intent);
    }
}