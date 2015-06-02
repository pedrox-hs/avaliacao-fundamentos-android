package com.example.administrador.myapplication.controllers;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.User;
import com.example.administrador.myapplication.util.AppUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final User user = new User();
        if (user.countUsers() == 0) {
            startActivity(new Intent(MainActivity.this, UserEmptyActivity.class));
        }

        setContentView(R.layout.activity_main_material);

        final EditText txtLogin = AppUtil.get(findViewById(R.id.editTextLogin));
        final EditText txtPass = AppUtil.get(findViewById(R.id.editTextPass));
        final Button btnLogin = (Button) findViewById(R.id.buttonLogin);

        // Change typeface for the password field
        txtPass.setTypeface(Typeface.DEFAULT);
        txtPass.setTransformationMethod(new PasswordTransformationMethod());

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setLogin(txtLogin.getText().toString());
                user.setPassword(txtPass.getText().toString());
                if (user.authenticate()) {
                    startActivity(new Intent(MainActivity.this, ServiceOrderListActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, R.string.msg_login_fail, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, R.string.msg_register_success, Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
