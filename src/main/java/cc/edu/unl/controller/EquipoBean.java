package cc.edu.unl.controller;

import cc.edu.unl.business.EquipoService;
import cc.edu.unl.domain.Equipo;
import cc.edu.unl.faces.FacesUtil;
import jakarta.annotation.PostConstruct;
//import jakarta.enterprise.context.ViewScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("equipoBean")
@ViewScoped
public class EquipoBean implements Serializable {

    private Equipo equipo;
    private List<Equipo> equipos;

    private Long idEquipoSeleccionado;

    @Inject
    private EquipoService equipoService;

    @PostConstruct
    public void init() {
        equipo = new Equipo();
        cargarEquipos();
    }

    public void cargarEquipos() {
        equipos = equipoService.listarEquipos();
    }

    public void cargarEquipoPorId() {
        if (idEquipoSeleccionado != null) {
            try {
                equipo = equipoService.obtenerEquipoPorId(idEquipoSeleccionado);
            } catch (IllegalArgumentException e) {
                FacesUtil.addErrorMessage("No se pudo cargar el equipo: " + e.getMessage());
            }
        }
    }

    public String guardar() {
        try {
            if (equipo.getId() == null) {
                equipoService.crearEquipo(equipo.getNombre(), equipo.getCiudad());
                FacesUtil.addSuccessMessageAndKeep("Equipo creado exitosamente.");
            } else {
                equipoService.obtenerEquipoPorId(equipo.getId()); // Valida existencia
                equipoService.listarEquipos().stream()
                        .filter(e -> e.getId().equals(equipo.getId()))
                        .findFirst()
                        .ifPresent(e -> {
                            e.setNombre(equipo.getNombre());
                            e.setCiudad(equipo.getCiudad());
                            equipoService.crearEquipo(e.getNombre(), e.getCiudad());
                        });
                FacesUtil.addSuccessMessageAndKeep("Equipo editado exitosamente.");
            }
            return "lista-equipos?faces-redirect=true";
        } catch (IllegalArgumentException e) {
            FacesUtil.addErrorMessage(e.getMessage());
            return null;
        }
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public Long getIdEquipoSeleccionado() {
        return idEquipoSeleccionado;
    }

    public void setIdEquipoSeleccionado(Long idEquipoSeleccionado) {
        this.idEquipoSeleccionado = idEquipoSeleccionado;
    }
}
