package cc.edu.unl.business;

import cc.edu.unl.domain.Equipo;
import jakarta.ejb.Stateless; // O @ApplicationScoped si no usas EJB y prefieres CDI puro
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

@Stateless // O @ApplicationScoped
public class EquipoService implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public List<Equipo> obtenerTodosLosEquipos() {
        return em.createNamedQuery("Equipo.findAll", Equipo.class).getResultList();
    }

    public Equipo buscarEquipoPorId(Long id) {
        return em.find(Equipo.class, id);
    }
    // Puedes añadir más métodos relacionados con Equipo aquí
}