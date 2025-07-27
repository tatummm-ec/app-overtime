package cc.edu.unl.controller;

import cc.edu.unl.business.PartidoService;
import cc.edu.unl.business.TorneoService;
import cc.edu.unl.domain.Equipo;
import cc.edu.unl.domain.Partido;
import cc.edu.unl.domain.Torneo;
import cc.edu.unl.faces.FacesUtil;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Named("torneoBean")
@SessionScoped
public class TorneoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TorneoService torneoService;

    @Inject
    private PartidoService partidoService;

    @Inject
    private AuthenticationBean authBean;

    private String nombreTorneo;
    private String categoria;
    private Date fechaInicio;
    private Date fechaFin;

    // Para mostrar info, partidos próximos, etc.
    private List<Torneo> torneosDisponibles;
    private Torneo torneoSeleccionado;
    private List<Partido> proximosPartidos;
    private List<String> nombresEquipos = new ArrayList<>();


    private String nombreEquipoTemp;


    @PostConstruct
    public void init() {
        cargarTorneosDisponibles();
        nombresEquipos.add("");
        /*Al inicio se seleccione un torneo por defecto o el primero:
          if (torneosDisponibles != null && !torneosDisponibles.isEmpty()) {
            this.torneoSeleccionado = torneosDisponibles.get(0);
            cargarProximosPartidos();
         */
    }

    public void agregarEquipo() {
        if (nombreEquipoTemp != null && !nombreEquipoTemp.trim().isEmpty()) {
            nombresEquipos.add(nombreEquipoTemp.trim());
            nombreEquipoTemp = "";
            FacesUtil.addSuccessMessage("Equipo agregado", "El equipo fue añadido correctamente.");
        } else {
            FacesUtil.addErrorMessage("Error", "Debes ingresar un nombre de equipo válido.");
        }
    }

    public void cargarTorneosDisponibles() {
        torneosDisponibles = torneoService.obtenerTodosLosTorneos();
    }

    public String crearTorneo() {
        try {
            if (!validarEntradas()) {
                return null;
            }
            LocalDate localFechaInicio = toLocalDate(fechaInicio);
            LocalDate localFechaFin = toLocalDate(fechaFin);

            Torneo nuevoTorneo = new Torneo(nombreTorneo, categoria, localFechaInicio, localFechaFin);
            torneoService.crearTorneo(nuevoTorneo);

            List<Equipo> equipos = nombresEquipos.stream()
                    .map(nombre -> new Equipo(nombre.trim()))
                    .toList();

            nuevoTorneo.setEquipos(equipos);

            FacesUtil.addSuccessMessage("Éxito", "Torneo '" + nombreTorneo + "' creado correctamente.");
            limpiarFormulario();
            cargarTorneosDisponibles();

            return "home.xhtml?faces-redirect=true";

        } catch (Exception e) {
            FacesUtil.addErrorMessage("Error", "No se pudo crear el torneo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    private boolean validarEntradas() {
        if (isBlank(nombreTorneo)) {
            FacesUtil.addErrorMessage("Validación", "El nombre del torneo es obligatorio.");
            return false;
        }

        if (fechaInicio == null || fechaFin == null) {
            FacesUtil.addErrorMessage("Validación", "Las fechas de inicio y fin son obligatorias.");
            return false;
        }

        LocalDate inicio = toLocalDate(fechaInicio);
        LocalDate fin = toLocalDate(fechaFin);

        if (fin.isBefore(inicio)) {
            FacesUtil.addErrorMessage("Validación", "La fecha de fin debe ser posterior a la fecha de inicio.");
            return false;
        }

        if (nombresEquipos == null || nombresEquipos.isEmpty()) {
            FacesUtil.addErrorMessage("Validación", "Debe agregar al menos un equipo.");
            return false;
        }

        Set<String> nombresUnicos = new HashSet<>();
        int index = 1;
        for (String nombre : nombresEquipos) {
            if (isBlank(nombre)) {
                FacesUtil.addErrorMessage("Validación", "El nombre del equipo " + index + " es obligatorio.");
                return false;
            }
            if (!nombresUnicos.add(nombre.trim().toLowerCase())) {
                FacesUtil.addErrorMessage("Validación", "El nombre del equipo " + index + " está duplicado.");
                return false;
            }
            index++;
        }

        return true;
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public String seleccionarTorneoPorId(Long torneoId) {
        this.torneoSeleccionado = torneoService.buscarTorneoPorId(torneoId);
        cargarProximosPartidos();
        return "torneo.xhtml?faces-redirect=true";
    }


    public void cargarProximosPartidos() {
        // Idealmente, aquí deberías filtrar por el torneoSeleccionado si tienes esa relación
        // if (torneoSeleccionado != null) {
        //    proximosPartidos = partidoService.obtenerPartidosPorTorneo(torneoSeleccionado.getId());
        // } else {
        proximosPartidos = partidoService.obtenerPartidos(); // Carga todos los partidos si no hay filtro por torneo
        // }
    }

    private void limpiarFormulario() {
        this.nombreTorneo = null;
        this.categoria = null;
        this.fechaInicio = null;
        this.fechaFin = null;
    }

    public String irATorneo(Long torneoId) {
        this.torneoSeleccionado = torneoService.buscarTorneoPorId(torneoId);
        cargarProximosPartidos();
        return "torneo?faces-redirect=true";
    }




    //Getters y Setters

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

    public Torneo getTorneoSeleccionado() {
        return torneoSeleccionado;
    }

    public TorneoService getTorneoService() {
        return torneoService;
    }

    public void setTorneoService(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    public PartidoService getPartidoService() {
        return partidoService;
    }

    public void setPartidoService(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    public AuthenticationBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthenticationBean authBean) {
        this.authBean = authBean;
    }

    public List<Torneo> getTorneosDisponibles() {
        return torneosDisponibles;
    }

    public void setTorneosDisponibles(List<Torneo> torneosDisponibles) {
        this.torneosDisponibles = torneosDisponibles;
    }

    public void setTorneoSeleccionado(Torneo torneoSeleccionado) {
        this.torneoSeleccionado = torneoSeleccionado;
    }

    public List<Partido> getProximosPartidos() {
        return proximosPartidos;
    }

    public void setProximosPartidos(List<Partido> proximosPartidos) {
        this.proximosPartidos = proximosPartidos;
    }

    public List<String> getNombresEquipos() {
        return nombresEquipos;
    }

    public void setNombresEquipos(List<String> nombresEquipos) {
        this.nombresEquipos = nombresEquipos;
    }

    public String getNombreEquipoTemp() {
        return nombreEquipoTemp;
    }

    public void setNombreEquipoTemp(String nombreEquipoTemp) {
        this.nombreEquipoTemp = nombreEquipoTemp;
    }
}
