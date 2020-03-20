package com.example.civiswipe.ui.newissue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.civiswipe.R;

public class NewIssueFragment extends Fragment {

    private NewIssueViewModel newIssueViewModel;

    Button cancel;
    Button submit;
    TextView title;
    ImageView image;
    EditText description;
    EditText location;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //newIssueViewModel =
             //   ViewModelProviders.of(this).get(NewIssueViewModel.class);
        View root = inflater.inflate(R.layout.fragment_submit_issue, container, false);
        cancel = root.findViewById(R.id.cancel);
        submit = root.findViewById(R.id.submit);
        title = root.findViewById(R.id.title);
        image = root.findViewById(R.id.image);
        description = root.findViewById(R.id.description);
        location = root.findViewById(R.id.location);
        //final TextView textView = root.findViewById(R.id.text_new_submission);
       /* newIssueViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}