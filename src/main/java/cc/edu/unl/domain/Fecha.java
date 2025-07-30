package cc.edu.unl.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "FECHA")
@NamedQueries({
        @NamedQuery(name = "Fecha.findAll", query = "SELECT f FROM Fecha f")
})
public class Fecha implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate inicio;
    private LocalDate fin;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    // equals y hashCode por buenas prácticas (opcional pero recomendado)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fecha)) return false;
        Fecha fecha = (Fecha) o;
        return Objects.equals(id, fecha.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
