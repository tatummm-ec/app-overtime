package cc.edu.unl.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TorneoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreTorneo;
    private String categoria;
    private java.util.Date fechaInicio;
    private java.util.Date fechaFin;

    // Para mostrar info, partidos próximos, etc.
    private Object torneoSeleccionado;
    private List<Object> proximosPartidos;

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public java.util.Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(java.util.Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public java.util.Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(java.util.Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Object getTorneoSeleccionado() {
        return torneoSeleccionado;
    }

    public void setTorneoSeleccionado(Object torneoSeleccionado) {
        this.torneoSeleccionado = torneoSeleccionado;
    }

    public List<Object> getProximosPartidos() {
        return proximosPartidos;
    }

    public void setProximosPartidos(List<Object> proximosPartidos) {
        this.proximosPartidos = proximosPartidos;
    }

    // Métodos para crear torneo, seleccionar torneo, etc.
}
