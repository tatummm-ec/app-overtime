package cc.edu.unl.controller;

import cc.edu.unl.business.TorneoService;
import cc.edu.unl.domain.Torneo;
import cc.edu.unl.faces.FacesUtil;
import jakarta.annotation.PostConstruct;
//import jakarta.enterprise.context.ViewScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;


@Named
@ViewScoped
public class TorneoBean implements Serializable {

    private Torneo torneo;

    @Inject
    private TorneoService torneoService;
    private String editar;

    @PostConstruct
    public void init() {
        torneo = (Torneo) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash()
                .get("torneo");

        if (torneo == null) {
            torneo = new Torneo();
        }
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public String crearTorneo() {
        System.out.println("Torneo " +getTorneo().getNombre());
        torneoService.guardarTorneo(torneo);
        return "lista_torneos.xhtml?faces-redirect=true";
    }

    public String editar(){
        System.out.println("Torneo " +getTorneo().getNombre());
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash()
                .put("torneo", torneo);
        return "new_torneo?faces-redirect=true";
    }

    public String editarTorneo(){
        torneoService.editar(torneo);
        return "lista_torneos?faces-redirect=true";
    }
    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Torneo getTorneoSeleccionado() {
        return torneo;
    }
}
