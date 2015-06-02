package com.example.administrador.myapplication.models.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.models.persistence.UserRepository;

public class User implements Parcelable {
    private Integer mId;
    private String mLogin;
    private String mPassword;

    public User () {
        super();
    }

    public Integer getId () {
        return this.mId;
    }

    public void setId (Integer id) {
        this.mId = id;
    }

    public String getLogin () {
        return this.mLogin;
    }

    public void setLogin (String login) {
        this.mLogin = login;
    }

    public String getPassword () {
        return this.mPassword;
    }

    public void setPassword (String password) {
        this.mPassword = password;
    }

    public boolean authenticate() {
        return UserRepository.getInstance().authenticate(this);
    }

    public Integer countUsers () {
        return UserRepository.getInstance().countUsers();
    }

    public void save () {
        UserRepository.getInstance().save(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mId);
        dest.writeString(this.mLogin);
        dest.writeString(this.mPassword);
    }

    private User(Parcel in) {
        this.mId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mLogin = in.readString();
        this.mPassword = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
