package cc.edu.unl.repository;

import cc.edu.unl.domain.Torneo;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class TorneoRepository {

    @Inject
    private CrudGenericService crudService;

    public Torneo create(Torneo torneo) {
        return crudService.create(torneo);
    }

    public Torneo update(Torneo torneo) {
        return crudService.update(torneo);
    }

    public Torneo find(Long id) {
        return crudService.find(Torneo.class, id);
    }

    public void delete(Long id) {
        crudService.delete(Torneo.class, id);
    }

    public List<Torneo> findAll() {
        return crudService.findWithNamedQuery("Torneo.findAll");
    }

    public List<Torneo> findByNombre(String nombre) {
        Map<String, Object> params = new HashMap<>();
        params.put("nombre", nombre);
        return crudService.findWithNamedQuery("Torneo.findByNombre", params);
    }
}
