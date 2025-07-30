package cc.edu.unl.repository;

import cc.edu.unl.domain.Fecha;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class FechaRepository {

    @Inject
    private CrudGenericService crudService;

    public Fecha create(Fecha fecha) {
        return crudService.create(fecha);
    }

    public Fecha update(Fecha fecha) {
        return crudService.update(fecha);
    }

    public Fecha find(Long id) {
        return crudService.find(Fecha.class, id);
    }

    public List<Fecha> findAll() {
        return crudService.findWithNamedQuery("Fecha.findAll");
    }

    public void delete(Long id) {
        crudService.delete(Fecha.class, id);
    }
}
