package cc.edu.unl.controller;

import cc.edu.unl.business.TablaPosicionesService;
import cc.edu.unl.domain.Equipo;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class TablaPosicionesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TablaPosicionesService tablaPosicionesService;

    private List<Equipo> posiciones;

    @PostConstruct
    public void init() {
        posiciones = tablaPosicionesService.obtenerTablaPosiciones();
    }

    public List<Equipo> getPosiciones() {
        return posiciones;
    }

}
