package cc.edu.unl.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NamedQueries({
        @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Partido e")
})
public class Partido {
    @Id
    private Long id;

    private String equipoLocal;
    private String equipoVisitante;

    private LocalDate fecha;
    private LocalTime hora;
    private String lugar;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEquipoLocal() { return equipoLocal; }
    public void setEquipoLocal(String equipoLocal) { this.equipoLocal = equipoLocal; }

    public String getEquipoVisitante() { return equipoVisitante; }
    public void setEquipoVisitante(String equipoVisitante) { this.equipoVisitante = equipoVisitante; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }
}

