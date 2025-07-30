package cc.edu.unl.controller;

import cc.edu.unl.business.TorneoService;
import cc.edu.unl.domain.Torneo;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("listaTorneosBean")
@ViewScoped
public class ListaTorneosBean implements Serializable  {
    List<Torneo>  torneos;

    @Inject
    TorneoService torneoService;

    @PostConstruct
    public void init() {
        torneos = torneoService.listarTorneos();
    }

    public List<Torneo> getTorneos() {
        return torneos;
    }

}
