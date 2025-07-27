package cc.edu.unl.business;

import cc.edu.unl.domain.Partido;
import cc.edu.unl.domain.Torneo;
import cc.edu.unl.repository.CrudGenericService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Stateless
public class TorneoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CrudGenericService crudGenericService;

    // Puedes inyectar PartidoService si necesitas buscar partidos por torneo
    @Inject
    private PartidoService partidoService;

    public Torneo crearTorneo(Torneo torneo) {
        return crudGenericService.create(torneo);
    }

    public List<Torneo> obtenerTodosLosTorneos() {
        return crudGenericService.findWithNamedQuery("Torneo.findAll");
    }

    public Torneo buscarTorneoPorId(Long id) {
        return crudGenericService.find(Torneo.class, id);
    }

    public Torneo actualizarTorneo(Torneo torneo) {
        return crudGenericService.update(torneo);
    }

    public void eliminarTorneo(Long id) {
        crudGenericService.delete(Torneo.class, id);
    }


    // Si la entidad Partido tiene un campo @ManyToOne Torneo, puedes usar:
    /*
    public List<Partido> obtenerPartidosPorTorneo(Long torneoId) {
        // Asumiendo que Partido tiene un campo 'torneo'
        return crudGenericService.getEntityManager()
                .createQuery("SELECT p FROM Partido p WHERE p.torneo.id = :torneoId", Partido.class)
                .setParameter("torneoId", torneoId)
                .getResultList();
    }
    */

    public List<Partido> obtenerPartidosDeTodosLosTorneos() {
        return partidoService.obtenerPartidos(); // Devuelve todos los partidos disponibles
    }
}
