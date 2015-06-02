package com.example.administrador.myapplication.controllers;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.User;
import com.example.administrador.myapplication.util.AppUtil;

public class UserActivity extends AppCompatActivity {
    EditText mEditTextLogin;
    EditText mEditTextPassword;
    EditText mEditTextRePassword;
    User mUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = new User();
        setContentView(R.layout.activity_user);

        mEditTextLogin = AppUtil.get(findViewById(R.id.editTextNewLogin));
        mEditTextPassword = AppUtil.get(findViewById(R.id.editTextNewPass));
        mEditTextRePassword = AppUtil.get(findViewById(R.id.editTextRePass));

        // Change typeface for the password field
        mEditTextPassword.setTypeface(Typeface.DEFAULT);
        mEditTextPassword.setTransformationMethod(new PasswordTransformationMethod());

        mEditTextRePassword.setTypeface(Typeface.DEFAULT);
        mEditTextRePassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionSaveUser:
                saveUser();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveUser() {
        boolean isValid = this.verifyMandatoryFields(mEditTextLogin, mEditTextPassword, mEditTextRePassword);

        isValid = isValid & this.verifyCorrectRePassword();

        if (isValid) {
            mUser.setLogin(mEditTextLogin.getText().toString().trim());
            mUser.setPassword(mEditTextPassword.getText().toString().trim());
            mUser.save();
            super.setResult(RESULT_OK);
            super.finish();
        }
    }

    private boolean verifyMandatoryFields(EditText... fields) {
        boolean isValid = true;
        for (EditText field : fields) {
            field.setError(null);
            if (TextUtils.isEmpty(field.getText())) {
                field.setError(getString(R.string.msg_mandatory));
                if (isValid) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    private boolean verifyCorrectRePassword() {
        final String valuePassword = mEditTextPassword.getText().toString().trim();
        final String valueRePassword = mEditTextRePassword.getText().toString().trim();
        if (!valuePassword.equals(valueRePassword)) {
            mEditTextRePassword.setError(super.getString(R.string.msg_invalid_repassword));
            return false;
        }
        return true;
    }
}
