package cc.edu.unl.repository;

import cc.edu.unl.domain.Partido;
import cc.edu.unl.domain.Torneo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

@Stateless
public class PartidoRepository {

    @Inject
    private CrudGenericService crudService;

    public Partido create(Partido partido) {
        return crudService.create(partido);
    }

    public Partido update(Partido partido) {
        return crudService.update(partido);
    }

    public Partido find(Long id) {
        return crudService.find(Partido.class, id);
    }

    public List<Partido> findAll() {
        return crudService.findWithNamedQuery("Partido.findAll");
    }
    public List<Partido> findByTorneo(Torneo torneo) {
        return crudService.findWithNamedQuery("Partido.findByTorneo", Map.of("torneo", torneo));
    }


}
