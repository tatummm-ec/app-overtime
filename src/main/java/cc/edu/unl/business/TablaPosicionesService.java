package cc.edu.unl.business;

import cc.edu.unl.domain.Equipo;
import cc.edu.unl.repository.CrudGenericService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TablaPosicionesService {

    @Inject
    private CrudGenericService crudGenericService;

    // Obtener la tabla de posiciones ordenada
    /*
    public List<Equipo> obtenerTablaPosiciones() {
        List<Equipo> equipos = crudGenericService.findWithNamedQuery("Equipo.findAll");
        return equipos.stream()
                .sorted((e1, e2) -> {
                    int comparePuntos = Integer.compare(e2.getPuntos(), e1.getPuntos());
                    if (comparePuntos == 0) {
                        return Integer.compare(e2.getGolDiferencia(), e1.getGolDiferencia());
                    }
                    return comparePuntos;
                })
                .collect(Collectors.toList());
    }
     */

    // Crear un nuevo equipo
    public Equipo crearEquipo(Equipo equipo) {
        return crudGenericService.create(equipo);
    }

    // Actualizar un equipo
    public Equipo actualizarEquipo(Equipo equipo) {
        return crudGenericService.update(equipo);
    }

    // Eliminar un equipo
    public void eliminarEquipo(Long id) {
        crudGenericService.delete(Equipo.class, id);
    }
}
