package com.example.guifinalprojecto.models;

public class user {
    private String _id;
    private String date;
    private String name;
    private String email;
    private String password;
    private String avatar;

    public user(String _id, String date, String name, String email, String password, String avatar) {
        this._id = _id;
        this.date = date;
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
