package cc.edu.unl.business;

import cc.edu.unl.domain.Partido;
import cc.edu.unl.repository.CrudGenericService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Stateless
public class PartidoService implements Serializable {

    private static final long serialVersionUID = 1L;

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

    // Actualizar un partido existente
    public Partido actualizarPartido(Partido partido) {
        return crudGenericService.update(partido);
    }

    // Eliminar un partido por ID
    public void eliminarPartido(Long id) {
        crudGenericService.delete(Partido.class, id);
    }
}
