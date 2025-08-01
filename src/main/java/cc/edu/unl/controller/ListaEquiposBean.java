package cc.edu.unl.controller;

import cc.edu.unl.business.EquipoService;
import cc.edu.unl.domain.Equipo;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListaEquiposBean implements Serializable {
    private List<Equipo> equipos;

    @Inject
    private EquipoService equipoService;

    @PostConstruct
    public void init() {
        equipos = equipoService.listarEquipos();
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
}
