package com.example.faizan.voxox;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class editProfile extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        toolbar = (Toolbar)findViewById(R.id.editprToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.cab_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


    }
}
