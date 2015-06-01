package com.example.administrador.myapplication.models.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.models.entities.User;

public class UserContract {
    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    public static final String[] COLUMNS = {ID, LOGIN, PASSWORD};

    public static String createTable() {
        final StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(LOGIN + " TEXT, ");
        sql.append(PASSWORD + " TEXT ");
        sql.append(");");

        return sql.toString();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues content = new ContentValues();
        content.put(ID, user.getId());
        content.put(LOGIN, user.getLogin());
        content.put(PASSWORD, user.getPassword());

        return content;
    }

    public static User bind (Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            user.setLogin(cursor.getString(cursor.getColumnIndex(LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));

            return user;
        }
        return null;
    }
}
