package cc.edu.unl.controller;

import cc.edu.unl.controller.AuthenticationBean.Torneo; // Importa la clase interna
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("homeBean")
@ViewScoped
public class HomeBean implements Serializable {

    @Inject
    private AuthenticationBean authBean;

    private List<Torneo> misTorneos;

    @PostConstruct
    public void init() {
        // Le preguntamos al authBean por el usuario y sus torneos
        if (authBean != null && authBean.isUserLoggedIn()) {
            this.misTorneos = authBean.getCurrentUser().getTorneos();
        }
    }

    public List<Torneo> getMisTorneos() {
        return misTorneos;
    }

    public boolean isTieneTorneos() {
        return misTorneos != null && !misTorneos.isEmpty();
    }
}