package com.example.guifinalprojecto.models;

public class logInResponse {
    private String message, token, _id;

    public logInResponse(String message, String token, String _id) {
        this.message = message;
        this.token = token;
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
