package cc.edu.unl.controller;

import cc.edu.unl.faces.FacesUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;

@Named("registerBean")
@RequestScoped
public class RegisterBean {

    private String name;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    public String register() {
        // Validar que las contraseñas coincidan
        if (!password.equals(confirmPassword)) {
            FacesUtil.addErrorMessage("Error", "Las contraseñas no coinciden.");
            return null;
        }

        // Verificar si el nombre de usuario ya existe
        if (AuthenticationBean.isUsernameTaken(username)) {
            FacesUtil.addErrorMessage("Error", "El nombre de usuario ya está en uso.");
            return null;
        }

        // Crear nuevo usuario y agregarlo
        AuthenticationBean.User newUser = new AuthenticationBean.User(name, email, username, password);
        AuthenticationBean.addRegisteredUser(newUser);

        // Mensaje de éxito y redirección a login
        FacesUtil.addSuccessMessageAndKeep("Éxito", "¡Registro completado! Ahora puedes iniciar sesión.");
        return "login.xhtml?faces-redirect=true";
    }

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
}
