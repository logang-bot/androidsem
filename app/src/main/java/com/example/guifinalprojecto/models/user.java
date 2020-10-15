package com.example.guifinalprojecto.models;

import java.util.Date;

public class user {
    private String _id, name, email, password;
    private Date date;

    public user(String _id, String name, String email, String password, Date date) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.date = date;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDate() {
        return date;
    }
}
