package com.example.guifinalprojecto.adapters;

public class structMenu {
    private String foto;
    private String contador;
    private String _id;
    private String nombre;
    private String precio;
    private String descripcion;
    private String cantidad_por_dia;
    private String fechareg;
    private String id_rest;
    private String resta;
    private String imgresta;

    public structMenu(String foto, String contador, String _id, String nombre, String precio, String descripcion, String cantidad_por_dia, String fechareg, String id_rest, String resta, String imgresta) {
        this.foto = foto;
        this.contador = contador;
        this._id = _id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cantidad_por_dia = cantidad_por_dia;
        this.fechareg = fechareg;
        this.id_rest = id_rest;
        this.resta = resta;
        this.imgresta = imgresta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getCantidad_por_dia() {
        return cantidad_por_dia;
    }

    public void setCantidad_por_dia(String cantidad_por_dia) {
        this.cantidad_por_dia = cantidad_por_dia;
    }

    public String getFechareg() {
        return fechareg;
    }

    public void setFechareg(String fechareg) {
        this.fechareg = fechareg;
    }

    public String getId_rest() {
        return id_rest;
    }

    public void setId_rest(String id_rest) {
        this.id_rest = id_rest;
    }

    public String getResta() {
        return resta;
    }

    public void setResta(String resta) {
        this.resta = resta;
    }

    public String getImgresta() {
        return imgresta;
    }

    public void setImgresta(String imgresta) {
        this.imgresta = imgresta;
    }
}
