package com.example.civiswipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.civiswipe.ui.comments.commentThread;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.navigation_dashboard, R.id.navigation_new_issue, R.id.navigation_issues, R.id.navigation_home)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if (savedInstanceState == null) {
            navView.setSelectedItemId(R.id.navigation_dashboard); //dashboard is default fragment when you start the app
        }






    }
    @Override
    public void onBackPressed() { //TODO: implement this method in comment method such that it goes back to main activity instead of closing app
        navView.setSelectedItemId(R.id.navigation_dashboard);
    }
        //this method may have been made redundant by onBackPressed but it's convenient to have it to call
        //when a form is submitted or a cancel button is pressed, not just when the user presses the back button
    public void backToDash(View v) {
        navView.setSelectedItemId(R.id.navigation_dashboard);
    }



}
