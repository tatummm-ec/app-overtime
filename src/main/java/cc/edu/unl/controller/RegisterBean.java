package cc.edu.unl.controller;

import cc.edu.unl.business.UsuarioService;
import cc.edu.unl.domain.User;
import cc.edu.unl.faces.FacesUtil;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.View;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class RegisterBean implements Serializable {
    private User user;
    private String passwordValidate;
    @Inject
    private UsuarioService usuarioService;


    @PostConstruct
    public void init(){
        user = new User();
    }

    public String crearUsuario() {
        if (user.getPassword().equals(passwordValidate)) {
            usuarioService.crearUsuario(user);
            return "login.xhtml?faces-redirect=true";
        } else {
            FacesUtil.addErrorMessage("Las contraseñas no coinciden");
            return null;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPasswordValidate() {
        return passwordValidate;
    }

    public void setPasswordValidate(String passwordValidate) {
        this.passwordValidate = passwordValidate;
    }
}
