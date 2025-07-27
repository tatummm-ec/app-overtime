package cc.edu.unl.controller;

import cc.edu.unl.domain.Torneo;
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
@Named("authBean")
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
            User adminUser = new User("Admin", "admin@example.com", "admin", "1234");
            // --> PRUEBA: Simular que el admin tiene torneos
            adminUser.getTorneos().add(new Torneo(1L, "Copa de Verano 2025"));
            adminUser.getTorneos().add(new Torneo(2L, "Liga Universitaria"));
            registeredUsers.add(adminUser);

            // Crear un usuario sin torneos para probar
            registeredUsers.add(new User("Juan", "juan@example.com", "juan", "123"));
        }
    }


    public String login() {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.currentUser = user;
                FacesUtil.addSuccessMessageAndKeep("Éxito","¡Bienvenido, " + currentUser.getName() + "!");
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
    public static class User implements Serializable {
        private String name;
        private String email;
        private String username;
        private String password;
        private List<Torneo> torneos = new ArrayList<>();

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
        public List<Torneo> getTorneos() {return torneos; }
    }

    public static class Torneo implements Serializable {
        private Long id;
        private String nombre;

        public Torneo(Long id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        // Getters...
        public Long getId() { return id; }
        public String getNombre() { return nombre; }
    }

}