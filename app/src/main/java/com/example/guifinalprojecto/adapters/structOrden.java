package com.example.guifinalprojecto.adapters;

public class structOrden {
    private String _id;
    private String idmenu;
    private String iduser;
    private String cantidad;
    private String log;
    private String lat;
    private String pagoTotal;
    private String estado;

    public structOrden(String id, String idmenu, String iduser, String cantidad, String log, String lat, String pagoTotal, String estado) {
        this._id = id;
        this.idmenu = idmenu;
        this.iduser = iduser;
        this.cantidad = cantidad;
        this.log = log;
        this.lat = lat;
        this.pagoTotal = pagoTotal;
        this.estado = estado;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(String idmenu) {
        this.idmenu = idmenu;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(String pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

