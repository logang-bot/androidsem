package com.example.guifinalprojecto.models;

public class signUp {
    private String mensaje, token;

    public signUp(String mensaje, String token) {
        this.mensaje = mensaje;
        this.token = token;
    }

    public String getMessage() {
        return mensaje;
    }

    public String getToken() {
        return token;
    }

    public void setMessage(String message) {
        this.mensaje = mensaje;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
