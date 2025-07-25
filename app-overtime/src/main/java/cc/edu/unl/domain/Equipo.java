package cc.edu.unl.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Equipo") // Or your table name
@NamedQueries({
        @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e")
})
public class Equipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre; // Example: "Barcelona SC", "Emelec"

    // --- NEW PROPERTIES FOR TABLE STANDINGS ---
    private int puntos;         // Stores the points of the team
    private int golesFavor;     // Goals scored by the team
    private int golesContra;    // Goals conceded by the team
    private int partidosJugados; // Number of games played
    private int partidosGanados; // Number of games won
    private int partidosEmpatados; // Number of games tied
    private int partidosPerdidos; // Number of games lost
    // --- END NEW PROPERTIES ---

    public Equipo() {}

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    // Getters and Setters (existing)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    // --- NEW GETTERS AND SETTERS FOR TABLE STANDINGS ---
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    // Method to calculate goal difference (derived property, not directly stored as a field in DB usually)
    // You might also persist this if you want to avoid recalculating often, but it's derived data.
    @Transient // This annotation means it won't be persisted to the database
    public int getGolDiferencia() {
        return golesFavor - golesContra;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }
    // --- END NEW GETTERS AND SETTERS ---


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return id != null && Objects.equals(id, equipo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nombre;
    }
}