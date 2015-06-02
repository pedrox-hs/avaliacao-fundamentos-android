package com.example.administrador.myapplication.models.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.models.entities.User;
import com.example.administrador.myapplication.util.AppUtil;

public class UserRepository {

    private static class Singleton {
        public static final UserRepository INSTANCE = new UserRepository();
    }

    private UserRepository() {
        super();
    }

    public static UserRepository getInstance() {
        return Singleton.INSTANCE;
    }

    public Integer countUsers () {
        String countQuery = "SELECT  * FROM " + UserContract.TABLE;
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public void save (User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        if (user.getId() == null) {
            db.insert(UserContract.TABLE, null, UserContract.getContentValues(user));
        } else {
            String where = UserContract.ID + " = ?";
            String[] args = {user.getId().toString()};
            db.update(UserContract.TABLE, UserContract.getContentValues(user), where, args);
        }
        db.close();
        helper.close();
    }

    public boolean authenticate (User user) {
        String whereClause = " login = ? AND password = ? ";
        String[] whereArgs = new String[]{
                user.getLogin(),
                user.getPassword()
        };

        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        //prepare cursor
        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUMNS, whereClause, whereArgs, null, null, null);

        int total = cursor.getCount();

        db.close();
        helper.close();

        return (total > 0);
    }
}
