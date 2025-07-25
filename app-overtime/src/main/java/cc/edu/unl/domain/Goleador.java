package cc.edu.unl.domain;

import jakarta.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Goleador.findAll", query = "SELECT g FROM Goleador g")
})
public class Goleador {

    @Id
    private Long id;

    private String nombre;
    private int goles;

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getGoles() { return goles; }
    public void setGoles(int goles) { this.goles = goles; }
}
