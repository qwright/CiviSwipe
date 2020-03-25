package com.example.civiswipe.ui.newissue;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText title;
    ImageView image; //TODO: make this a user-submitted image
    EditText description;
    EditText location;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //newIssueViewModel =
             //   ViewModelProviders.of(this).get(NewIssueViewModel.class);
        View root = inflater.inflate(R.layout.fragment_submit_issue, container, false);
        cancel = root.findViewById(R.id.cancel);
        submit = root.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //triggered when submit button pressed
                //get data from fields that need to be verified
                String titletext = title.getText().toString();
                String descriptiontext = description.getText().toString();
                String locationtext = location.getText().toString();

                //check that each mandatory field is filled
                int duration = Toast.LENGTH_SHORT;
                if(TextUtils.isEmpty(titletext)) {
                  Toast.makeText(getActivity(), "Please add a title", duration).show();
                } else if (TextUtils.isEmpty(locationtext)) {
                    Toast.makeText(getActivity(), "Please describe the location", duration).show();
                } else if (TextUtils.isEmpty(descriptiontext)) {
                    Toast.makeText(getActivity(), "Please add a short description", duration).show();
                }

                //TODO: write data to text file

                //TODO: save image to data directory?
            }
        });
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