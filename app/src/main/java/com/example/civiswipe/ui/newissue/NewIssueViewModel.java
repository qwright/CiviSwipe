package com.example.civiswipe.ui.newissue;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewIssueViewModel extends ViewModel {
//since the fields in the New Issue fragment don't update or change, this class might not be used
    private MutableLiveData<String> mText;

    public NewIssueViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is a new issue fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}