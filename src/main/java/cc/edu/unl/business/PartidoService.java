package cc.edu.unl.business;

import cc.edu.unl.domain.Partido;
import cc.edu.unl.repository.CrudGenericService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class PartidoService {

    @Inject
    private CrudGenericService crudGenericService;

    // Organizar (crear) un nuevo partido
    public Partido organizarPartido(Partido partido) {
        return crudGenericService.create(partido);
    }

    // Obtener todos los partidos programados
    public List<Partido> obtenerPartidos() {
        return crudGenericService.findWithNamedQuery("Partido.findAll");
    }

    public Partido actualizarPartido(Partido partido) {
        return crudGenericService.update(partido);
    }

    public void eliminarPartido(Long id) {
        crudGenericService.delete(Partido.class, id);
    }
}

