package cc.edu.unl.controller;

import cc.edu.unl.domain.Torneo;
import cc.edu.unl.domain.User;
import cc.edu.unl.faces.FacesUtil;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Define el bean administrado con nombre "authBean" y ámbito de sesión
@Named
@SessionScoped
public class AuthenticationBean implements Serializable {

    private String username;
    private String password;
    private boolean userLoggedIn=false;
    private User currentUser;

    // Lista estática de usuarios registrados
    private static List<User> registeredUsers = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Solo inicializar si la lista está vacía para evitar duplicados
        if (registeredUsers.isEmpty()) {
            // Crear usuario "Admin"
            User adminUser = new User( "admin", "12345678");
            //adminUser.setTorneo(new ArrayList<>());
            // --> PRUEBA: Simular que el admin tiene torneos
            //adminUser.getTorneos().add(new Torneo("Copa de Verano 2025"));
            //adminUser.getTorneos().add(new Torneo("Liga Universitaria"));
            registeredUsers.add(adminUser);
            // Crear un usuario sin torneos para probar
            registeredUsers.add(new User("juan", "12345678"));
        }
    }


    public String login() {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.currentUser = user;
                FacesUtil.addSuccessMessageAndKeep("Éxito","¡Bienvenido, " + currentUser.getUsername() + "!");
                return "home.xhtml?faces-redirect=true";
            }
        }
        FacesUtil.addErrorMessage("Error", "Usuario o contraseña incorrectos.");
        return null;
    }


    public String logout() {
        FacesUtil.addSuccessMessageAndKeep("Info", "Sesión cerrada correctamente.");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true"; // Redirecciona a login
    }


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

    // Getters y setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public User getCurrentUser() { return currentUser; }

    public boolean isUserLoggedIn() {
        return this.currentUser != null;
    }


    // Clase interna estática que representa un usuario

}
