package cc.edu.unl.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Goleadores e")
})
public class Goleadores {
    @Id
    private Long id;

    private String nombre;
    private int goles;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getGoles() { return goles; }
    public void setGoles(int goles) { this.goles = goles; }
}
