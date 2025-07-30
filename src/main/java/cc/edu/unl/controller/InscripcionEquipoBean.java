package cc.edu.unl.controller;

import cc.edu.unl.business.InscripcionService;
import cc.edu.unl.domain.Equipo;
import cc.edu.unl.domain.Torneo;
import cc.edu.unl.faces.FacesUtil;
import cc.edu.unl.repository.CrudGenericService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class InscripcionEquipoBean implements Serializable {

    private List<Torneo> torneos;
    private Torneo torneoSeleccionado;

    private String nombreEquipoBusqueda;
    private List<Equipo> equiposFiltrados;
    private List<Equipo> equiposInscritos = new ArrayList<>();

    @EJB
    private CrudGenericService crud;

    @EJB
    private InscripcionService inscripcionService;

    @PostConstruct
    public void init() {
        torneos = crud.findWithNamedQuery("Torneo.findAll");
    }

    public void buscarEquipos() {
        Map<String, Object> params = new HashMap<>();
        params.put("nombre", "%" + nombreEquipoBusqueda + "%");
        equiposFiltrados = crud.findWithNamedQuery("Equipo.buscarPorNombre", params);
    }

    public void agregarEquipo(Equipo equipo) {
        if (!equiposInscritos.contains(equipo)) {
            equiposInscritos.add(equipo);
        }
    }

    public void eliminarEquipo(Equipo equipo) {
        equiposInscritos.remove(equipo);
    }

    public void guardarInscripciones() {
        if (torneoSeleccionado == null) {
            FacesUtil.addErrorMessage("Debe seleccionar un torneo.");
            return;
        }

        if (equiposInscritos == null || equiposInscritos.isEmpty()) {
            FacesUtil.addErrorMessage("Debe seleccionar al menos un equipo para inscribir.");
            return;
        }

        int exitos = 0;
        for (Equipo equipo : equiposInscritos) {
            try {
                inscripcionService.inscribirEquipo(equipo, torneoSeleccionado);
                exitos++;
            } catch (IllegalArgumentException e) {
                FacesUtil.addErrorMessage("Error con el equipo '" + equipo.getNombre() + "': " + e.getMessage());
            } catch (Exception e) {
                FacesUtil.addErrorMessage("Error inesperado al inscribir '" + equipo.getNombre() + "'");
            }
        }

        if (exitos > 0) {
            FacesUtil.addSuccessMessageAndKeep("Se inscribieron " + exitos + " equipo(s) correctamente.");
        }

        equiposInscritos.clear(); // Limpiar selección después del guardado
    }

    // Getters y Setters
    public List<Torneo> getTorneos() {
        return torneos;
    }

    public Torneo getTorneoSeleccionado() {
        return torneoSeleccionado;
    }

    public void setTorneoSeleccionado(Torneo torneoSeleccionado) {
        this.torneoSeleccionado = torneoSeleccionado;
    }

    public String getNombreEquipoBusqueda() {
        return nombreEquipoBusqueda;
    }

    public void setNombreEquipoBusqueda(String nombreEquipoBusqueda) {
        this.nombreEquipoBusqueda = nombreEquipoBusqueda;
    }

    public List<Equipo> getEquiposFiltrados() {
        return equiposFiltrados;
    }

    public List<Equipo> getEquiposInscritos() {
        return equiposInscritos;
    }
}
