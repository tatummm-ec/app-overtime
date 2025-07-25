package cc.edu.unl.controller;

import cc.edu.unl.business.PartidoService;
import cc.edu.unl.business.EquipoService; // ¡Asegúrate de tener este servicio!
import cc.edu.unl.domain.Equipo;
import cc.edu.unl.domain.Partido;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext; // Importar FacesContext
import jakarta.faces.application.FacesMessage; // Importar FacesMessage

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

    @Inject
    private EquipoService equipoService; // Necesitas un EquipoService para cargar la lista de equipos

    private Partido partido;
    private List<Partido> partidos;
    private List<Equipo> equipos;

    // Propiedades para el formulario de creación de un nuevo partido
    // ¡Estas deben ser de tipo Equipo, no String!
    private Equipo nuevoEquipoLocal;
    private Equipo nuevoEquipoVisitante;
    private LocalDate nuevaFecha;
    private LocalTime nuevaHora;
    private String nuevoLugar;

    @PostConstruct
    public void init() {
        partido = new Partido();
        partidos = partidoService.obtenerPartidos();
        // Cargar los equipos disponibles al inicializar el bean
        // Este es el punto clave para que los selectOneMenu funcionen
        equipos = equipoService.obtenerTodosLosEquipos();
    }

    // --- Getters y setters para el objeto Partido y la lista de Partidos ---
    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    // --- Getter y setter para la lista de Equipos ---
    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    // --- Getters y setters para los campos del formulario de nuevo partido ---
    public Equipo getNuevoEquipoLocal() {
        return nuevoEquipoLocal;
    }

    public void setNuevoEquipoLocal(Equipo nuevoEquipoLocal) {
        this.nuevoEquipoLocal = nuevoEquipoLocal;
    }

    public Equipo getNuevoEquipoVisitante() {
        return nuevoEquipoVisitante;
    }

    public void setNuevoEquipoVisitante(Equipo nuevoEquipoVisitante) {
        this.nuevoEquipoVisitante = nuevoEquipoVisitante;
    }

    public LocalDate getNuevaFecha() {
        return nuevaFecha;
    }

    public void setNuevaFecha(LocalDate nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }

    public LocalTime getNuevaHora() {
        return nuevaHora;
    }

    public void setNuevaHora(LocalTime nuevaHora) {
        this.nuevaHora = nuevaHora;
    }

    public String getNuevoLugar() {
        return nuevoLugar;
    }

    public void setNuevoLugar(String nuevoLugar) {
        this.nuevoLugar = nuevoLugar;
    }

    // --- Acción para guardar un partido nuevo ---
    public String guardarPartido() {
        try {
            Partido nuevo = new Partido();
            nuevo.setEquipoLocal(nuevoEquipoLocal); // ¡Asignamos objetos Equipo!
            nuevo.setEquipoVisitante(nuevoEquipoVisitante); // ¡Asignamos objetos Equipo!
            nuevo.setFecha(nuevaFecha);
            nuevo.setHora(nuevaHora);
            nuevo.setLugar(nuevoLugar);

            partidoService.organizarPartido(nuevo);
            partidos = partidoService.obtenerPartidos(); // Refresca la lista
            limpiarFormulario(); // Limpia los campos del formulario
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Partido registrado correctamente."));
            return "partidos.xhtml?faces-redirect=true"; // Redirige a la página de partidos
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar el partido: " + e.getMessage()));
            System.err.println("Error al guardar partido: " + e.getMessage());
            return null; // Permanece en la misma página si hay un error
        }
    }

    // --- Método para limpiar los campos del formulario ---
    private void limpiarFormulario() {
        nuevoEquipoLocal = null;
        nuevoEquipoVisitante = null;
        nuevaFecha = null;
        nuevaHora = null;
        nuevoLugar = null;
    }
}