package cc.edu.unl.business;

import cc.edu.unl.domain.Equipo;
import cc.edu.unl.domain.Torneo;
import cc.edu.unl.repository.CrudGenericService;
import cc.edu.unl.repository.TorneoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class TorneoService {
    private Logger logger = Logger.getAnonymousLogger();

    @PersistenceContext(unitName = "tuappPU")
    private EntityManager em;

    @PostConstruct
    public void init() {
        logger.info("Entity Manager Repository: " + em);
    }

    public List<Torneo> listarTorneos() {
        return em.createQuery("SELECT t FROM Torneo t").getResultList();
    }

    public void guardarTorneo(Torneo torneo) {
        em.persist(torneo);
    }

    public void editar(Torneo torneo) {
        em.merge(torneo);
    }

}
