package com.example.administrador.myapplication.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.User;

public class UserEmptyActivity extends AppCompatActivity {
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = new User();
        setContentView(R.layout.activity_user_empty);

        final Button btnCreateUser = (Button) findViewById(R.id.buttonCreateUser);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserEmptyActivity.this, UserActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mUser.countUsers() > 0) {
            Toast.makeText(this, R.string.msg_register_success, Toast.LENGTH_LONG).show();
            super.finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            super.setResult(RESULT_OK);
            super.finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
