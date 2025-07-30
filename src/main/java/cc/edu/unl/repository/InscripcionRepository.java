package cc.edu.unl.repository;

import cc.edu.unl.domain.Equipo;
import cc.edu.unl.domain.Inscripcion;
import cc.edu.unl.domain.Torneo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class InscripcionRepository {

    @Inject
    private CrudGenericService crudService;

    public Inscripcion create(Inscripcion inscripcion) {
        return crudService.create(inscripcion);
    }

    public List<Inscripcion> findAll() {
        return crudService.findWithNamedQuery("Inscripcion.findAll");
    }

    public boolean estaInscrito(Equipo equipo, Torneo torneo) {
        TypedQuery<Inscripcion> query = crudService.getEntityManager().createQuery(
                "SELECT i FROM Inscripcion i WHERE i.equipo = :equipo AND i.torneo = :torneo",
                Inscripcion.class
        );
        query.setParameter("equipo", equipo);
        query.setParameter("torneo", torneo);
        return !query.getResultList().isEmpty();
    }
}
