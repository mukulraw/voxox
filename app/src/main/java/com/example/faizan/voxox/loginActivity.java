package com.example.faizan.voxox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {
    Button login;
    TextView signupText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();

         signupText = (TextView)findViewById(R.id.signupText);

        login = findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(loginActivity.this , MainActivity.class);
                startActivity(i);
            }
        });
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
                finish();




            }
        });

    }
}
