package com.example.civiswipe.ui.newissue;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import com.example.civiswipe.MainActivity;
import com.example.civiswipe.R;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static android.app.Activity.RESULT_OK;

public class NewIssueFragment extends Fragment {

    private NewIssueViewModel newIssueViewModel;

    Button cancel;
    Button submit;
    Button addphoto;
    EditText title;
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
        addphoto = root.findViewById(R.id.photo);


        addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add a Photo");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                                startActivityForResult(takePictureIntent, 1);
                            }

                            /*File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                            startActivityForResult(takePictureIntent, 1); */
                        } else if (options[item].equals("Choose from Gallery")) {
                            //Intent choosePictureIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            Intent choosePictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            // choosePictureIntent.setType("image/*");
                            //choosePictureIntent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(choosePictureIntent, 2);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //triggered when submit button pressed
                //get data from fields that need to be verified
                String titletext = title.getText().toString();
                String descriptiontext = description.getText().toString();
                String locationtext = location.getText().toString();

                //check that each mandatory field is filled
                int duration = Toast.LENGTH_SHORT;
                if (TextUtils.isEmpty(titletext)) {
                    Toast.makeText(getActivity(), "Please add a title", duration).show();
                } else if (TextUtils.isEmpty(locationtext)) {
                    Toast.makeText(getActivity(), "Please describe the location", duration).show();
                } else if (TextUtils.isEmpty(descriptiontext)) {
                    Toast.makeText(getActivity(), "Please add a short description", duration).show();
                } else {

                    //write data to text file
                    String savedsubmissions = titletext + "," + locationtext + "," + descriptiontext + ",\n";
                    FileOutputStream outputStream;
                    try {
                        outputStream = getActivity().openFileOutput("savedsubmissions.txt", Context.MODE_APPEND);
                        outputStream.write(savedsubmissions.getBytes());
                        outputStream.close();
                        Toast.makeText(getActivity(), "Submission successful", duration).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Did not submit successfully", duration).show();
                    }
                    //TODO: save image to data directory?
                    //((MainActivity) getActivity()).backToDash(v);
                    backToDash(v);
                     /*
                    should go back to home fragment, currently closes out of app cuz we only have one activity
                    I think this page has the answer, but it is the middle of the night and I cannot parse anything rn
                    https://stackoverflow.com/questions/42297381/onclick-event-in-navigation-drawer
                    */
                }
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


    public void backToDash(View v) {
        ((MainActivity) getActivity()).backToDash(v);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if ((requestCode == 1 | requestCode == 2) && resultCode == RESULT_OK) {
        if (requestCode == 1 && resultCode == RESULT_OK) { //handles picture from camera
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(imageBitmap);
        } else if (requestCode == 2 && resultCode == RESULT_OK) { //handles picture from gallery
            try {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                //ImageView imageView = (ImageView) findViewById(R.id.imgView);
                image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Could not access photo", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
