package cc.edu.unl.controller;

import cc.edu.unl.business.PartidoService;
import cc.edu.unl.business.EquipoService; // ¡Asegúrate de tener este servicio!
import cc.edu.unl.domain.Equipo;
import cc.edu.unl.domain.Partido;
import cc.edu.unl.faces.FacesUtil;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Named
@SessionScoped
public class PartidoBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private PartidoService partidoService;

    @Inject
    private TorneoBean torneoBean;


    @Inject
    private EquipoService equipoService; //  para cargar la lista de equipos

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
        if (torneoBean.getTorneoSeleccionado() != null) {
            //partidos = partidoService.obtenerPartidosPorTorneo(torneoBean.getTorneoSeleccionado().getId());
            equipos = torneoBean.getTorneoSeleccionado().getEquipos(); // si la relación existe
        } else {
            partidos = List.of();
            equipos = List.of();
        }
    }


    // --- Acción para guardar un partido nuevo ---
    public String guardarPartido() {
        if (nuevoEquipoLocal == null || nuevoEquipoVisitante == null) {
            FacesUtil.addErrorMessage("Error", "Debe seleccionar ambos equipos.");
            return null;
        }

        if (nuevoEquipoLocal.equals(nuevoEquipoVisitante)) {
            FacesUtil.addErrorMessage("Error", "El equipo local y visitante no pueden ser el mismo.");
            return null;
        }

        try {
            Partido nuevo = new Partido();
            nuevo.setEquipoLocal(nuevoEquipoLocal);
            nuevo.setEquipoVisitante(nuevoEquipoVisitante);
            nuevo.setFecha(nuevaFecha);
            nuevo.setHora(nuevaHora);
            //nuevo.setLugar(nuevoLugar);
            //nuevo.setTorneo(torneoBean.getTorneoSeleccionado());

            //partidoService.organizarPartido(nuevo);
            //partidos = partidoService.obtenerPartidos(); // Refresca la lista
            limpiarFormulario();
            //partidos = partidoService.obtenerPartidosPorTorneo(torneoBean.getTorneoSeleccionado().getId());
            FacesUtil.addSuccessMessage("Éxito", "Partido registrado correctamente.");
            return "torneo.xhtml?faces-redirect=true";

        } catch (Exception e) {
            FacesUtil.addErrorMessage("Error", "No se pudo registrar el partido" );
            System.err.println("Error al guardar partido: " + e.getMessage());
            return null; // Permanece en la misma página si hay un error
        }
    }

    // --- Limpiar los campos del formulario ---
    private void limpiarFormulario() {
        nuevoEquipoLocal = null;
        nuevoEquipoVisitante = null;
        nuevaFecha = null;
        nuevaHora = null;
        nuevoLugar = null;
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

}
