package cc.edu.unl.controller;

import cc.edu.unl.business.EquipoService;
import cc.edu.unl.business.TorneoService;
import cc.edu.unl.domain.Equipo;
import cc.edu.unl.domain.Torneo;
import cc.edu.unl.faces.FacesUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.DualListModel;


import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class InscripcionEquipoBean implements Serializable {

    //private List<Torneo> torneos;
    private Torneo torneo;
    private DualListModel<Equipo> equipos;
    private Boolean isRegisterOK;
    @Inject
    EquipoService equipoService;
    @Inject
    TorneoService torneoService;

    @PostConstruct
    public void init() {
        isRegisterOK = Boolean.FALSE;
        List<Equipo> equiposlist = equipoService.listarEquipos();
        List<Equipo> target = new ArrayList<>();
        equipos = new DualListModel<>(equiposlist, target);
        torneo = (Torneo) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash()
                .get("torneo");

        if (torneo == null) {
            torneo = new Torneo();
        }
    }

    public void mostrarSeleccionados() {
        try {
            List<Equipo> seleccionados = equipos.getTarget();
            torneo.setEquipos(seleccionados);
            torneoService.editar(torneo);
            isRegisterOK=Boolean.TRUE;
            FacesUtil.addSuccessMessage("Success", "Se registaron correctamente los equipos");
        }catch (Exception e){
            isRegisterOK=Boolean.FALSE;
        }
    }

    // Getters y Setters
    public DualListModel<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(DualListModel<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneoSeleccionado) {
        this.torneo = torneoSeleccionado;
    }

    public Boolean getRegisterOK() {
        return isRegisterOK;
    }

    public void setRegisterOK(Boolean registerOK) {
        isRegisterOK = registerOK;
    }
}
