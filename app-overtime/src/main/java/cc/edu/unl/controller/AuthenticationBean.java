package cc.edu.unl.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Define el bean administrado con nombre "authBean" y ámbito de sesión
@Named("authBean")
@SessionScoped
public class AuthenticationBean implements Serializable {

    private String username;
    private String password;
    private boolean userLoggedIn = false;

    // Lista estática de usuarios registrados
    private static List<User> registeredUsers = new ArrayList<>();

    static {
        registeredUsers.add(new User("Admin", "admin@example.com", "admin", "1234"));
    }


    public String login() {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.userLoggedIn = true;
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "¡Bienvenido, " + username + "!"));
                return "index.xhtml?faces-redirect=true";
                //return "index.xhtml?faces-redirect=true"; // Redirecciona al índice

            }
            if (!userLoggedIn) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales Incorrectas", "¡Pon bien la clave " + username + "!"));
                return null;

            }

        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrectos."));
        return null;
    }


    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.userLoggedIn = false;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sesión cerrada correctamente."));
        return "login.xhtml?faces-redirect=true"; // Redirecciona a login
    }

    // Getters y setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isUserLoggedIn() { return userLoggedIn; }


    public static void addRegisteredUser(User user) {
        if (user != null && !isUsernameTaken(user.getUsername())) {
            registeredUsers.add(user);
            System.out.println("User registered: " + user.getUsername());
        }
    }


    public static boolean isUsernameTaken(String username) {
        for (User user : registeredUsers) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // Clase interna estática que representa un usuario
    public static class User implements Serializable {
        private String name;
        private String email;
        private String username;
        private String password;

        // Constructor
        public User(String name, String email, String username, String password) {
            this.name = name;
            this.email = email;
            this.username = username;
            this.password = password;
        }

        // Getters
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getUsername() { return username; }
        public String getPassword() { return password; }
    }
}