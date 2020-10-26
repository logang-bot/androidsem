package com.example.guifinalprojecto.adapters;

public class structMenu {
    private String nombre;
    private String precio;
    private String descripcion;
    private String fechareg;
    private String foto;
    private String id_rest;
    private String contador;
    private String cantidad_por_dia;


    public structMenu(String nombre, String precio, String descripcion, String fechareg, String foto, String id_rest, String contador, String cantidad_por_dia) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fechareg = fechareg;
        this.foto = foto;
        this.id_rest = id_rest;
        this.contador = contador;
        this.cantidad_por_dia = cantidad_por_dia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechareg() {
        return fechareg;
    }

    public void setFechareg(String fechareg) {
        this.fechareg = fechareg;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId_rest() {
        return id_rest;
    }

    public void setId_rest(String id_rest) {
        this.id_rest = id_rest;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public String getCantidad_por_dia() {
        return cantidad_por_dia;
    }

    public void setCantidad_por_dia(String cantidad_por_dia) {
        this.cantidad_por_dia = cantidad_por_dia;
    }
}
