package cc.edu.unl.business;

import cc.edu.unl.domain.Goleadores;
import cc.edu.unl.repository.CrudGenericService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TablaGoleadoresService {

    @Inject
    private CrudGenericService crudGenericService;

    // Obtener ranking de goleadores ordenado
    public List<Goleadores> obtenerRanking() {
        List<Goleadores> goleadores = crudGenericService.findWithNamedQuery("Goleador.findAll");
        return goleadores.stream()
                .sorted((g1, g2) -> Integer.compare(g2.getGoles(), g1.getGoles()))
                .collect(Collectors.toList());
    }

    public Goleadores crearGoleador(Goleadores goleador) {
        return crudGenericService.create(goleador);
    }

    public Goleadores actualizarGoleador(Goleadores goleador) {
        return crudGenericService.update(goleador);
    }

    public void eliminarGoleador(Long id) {
        crudGenericService.delete(Goleadores.class, id);
    }
}
