package com.example.administrador.myapplication.models.persistence;

import android.content.ContentValues;

import com.example.administrador.myapplication.models.entities.User;

public class UserContract {
    public final String TABLE = "user";
    public final String ID = "id";
    public final String LOGIN = "login";
    public final String PASSWORD = "password";

    public final String[] COLUMNS = {ID, LOGIN, PASSWORD};

    public static String createTable() {
        final StringBuilder sql = new StringBuilder();

        return sql.toString();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues content = new ContentValues();
        //content.put(ID, user.getId());
        return content;
    }
}
