package com.example.guifinalprojecto.adapters;

public class structRests {
    private String logo;
    private String foto;
    private String _id;
    private String fechaReg;
    private String nombre;
    private String nit;
    private String propietario;
    private String nombrePropietario;
    private String calle;
    private String telefono;
    private String log;
    private String lat;
    private String message;

    public structRests(String logo, String foto, String _id, String fechaReg, String nombre, String nit, String propietario, String nombrePropietario, String calle, String telefono, String log, String lat) {
        this.logo = logo;
        this.foto = foto;
        this._id = _id;
        this.fechaReg = fechaReg;
        this.nombre = nombre;
        this.nit = nit;
        this.propietario = propietario;
        this.nombrePropietario= nombrePropietario;
        this.calle = calle;
        this.telefono = telefono;
        this.log = log;
        this.lat = lat;
        this.message = message;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(String fechaReg) {
        this.fechaReg = fechaReg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
