package cc.edu.unl.repository;

import cc.edu.unl.domain.Equipo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class EquipoRepository {

    @Inject
    private CrudGenericService crudService;

    public Equipo create(Equipo equipo) {
        return crudService.create(equipo);
    }

    public Equipo update(Equipo equipo) {
        return crudService.update(equipo);
    }

    public Equipo find(Long id) {
        return crudService.find(Equipo.class, id);
    }

    public void delete(Long id) {
        crudService.delete(Equipo.class, id);
    }

    public List<Equipo> findAll() {
        return crudService.findWithQuery("SELECT e FROM Equipo e");
    }
}
