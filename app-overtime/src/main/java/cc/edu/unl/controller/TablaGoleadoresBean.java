package cc.edu.unl.controller;

import cc.edu.unl.business.TablaGoleadoresService;
import cc.edu.unl.domain.Goleador;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class TablaGoleadoresBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TablaGoleadoresService tablaGoleadoresService;

    private List<Goleador> goleadores;

    @PostConstruct
    public void init() {
        goleadores = tablaGoleadoresService.obtenerRanking();
    }

    public List<Goleador> getGoleadores() {
        return goleadores;
    }
}
