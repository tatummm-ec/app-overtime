package cc.edu.unl.controller;

import cc.edu.unl.business.EquipoService;
import cc.edu.unl.domain.Equipo;
import cc.edu.unl.domain.Torneo;
import cc.edu.unl.faces.FacesUtil;
import jakarta.annotation.PostConstruct;
//import jakarta.enterprise.context.ViewScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EquipoBean implements Serializable {

    private Equipo equipo;
    private String editarAction;


    @Inject
    private EquipoService equipoService;

    @PostConstruct
    public void init() {
        equipo = (Equipo) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash()
                .get("equipo");

        if (equipo == null) {
            equipo = new Equipo();
        }
    }

    public String crearEquipo() {
        equipoService.crearEquipo(equipo);
        return "lista_equipos.xhtml?faces-redirect=true";
    }

    public String editar(){
        System.out.println(equipo);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Flash flash = externalContext.getFlash();
        flash.put("equipo", equipo);
        flash.put("editar", editarAction);

        return "administrar_equipos.xhtml?faces-redirect=true";
    }

    public String editarEquipo(){
        equipoService.editarEquipo(equipo);
        return "lista_equipos?faces-redirect=true";
    }


    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getEditarAction() {
        return editarAction;
    }

    public void setEditarAction(String editarAction) {
        this.editarAction = editarAction;
    }
}
