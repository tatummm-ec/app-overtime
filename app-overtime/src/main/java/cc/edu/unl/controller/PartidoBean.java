package cc.edu.unl.controller;

import cc.edu.unl.business.PartidoService;
import cc.edu.unl.domain.Equipo;
import cc.edu.unl.domain.Partido;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Named
@SessionScoped
public class PartidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PartidoService partidoService;

    private Partido partido;

    private List<Partido> partidos;

    private List<Equipo> equipos;  // Para los selectOneMenu de equipos

    // Valores temporales para el formulario
    private String equipoLocal;
    private String equipoVisitante;
    private LocalDate fecha;
    private LocalTime hora;
    private String lugar;

    @PostConstruct
    public void init() {
        partido = new Partido();
        partidos = partidoService.obtenerPartidos();
        // Aquí deberías cargar los equipos de algún servicio, asumo que lo tienes:
        // Por ahora dejo equipos = null o cargarlo después
    }

    // Getter y setters
    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    // Acción para guardar un partido nuevo
    public String guardarPartido() {
        try {
            Partido nuevo = new Partido();
            nuevo.setEquipoLocal(equipoLocal);
            nuevo.setEquipoVisitante(equipoVisitante);
            nuevo.setFecha(fecha);
            nuevo.setHora(hora);
            nuevo.setLugar(lugar);
            partidoService.organizarPartido(nuevo);
            partidos = partidoService.obtenerPartidos(); // refrescar lista
            limpiarFormulario();
            return "partidos.xhtml?faces-redirect=true";
        } catch (Exception e) {
            // Manejar error, mostrar mensaje en JSF
            return null;
        }
    }

    private void limpiarFormulario() {
        equipoLocal = null;
        equipoVisitante = null;
        fecha = null;
        hora = null;
        lugar = null;
    }

}
