package cc.edu.unl.business;

import cc.edu.unl.domain.Equipo;
import cc.edu.unl.domain.Partido;
import cc.edu.unl.domain.Torneo;
import cc.edu.unl.dto.EstadisticaDTO;
import cc.edu.unl.repository.PartidoRepository;
import jakarta.ejb.Stateless;

import java.util.*;
import java.util.stream.Collectors;
@Stateless
public class EstadisticaService {

    private  PartidoRepository partidoRepo;

    public EstadisticaService() {

    }

    public EstadisticaService(PartidoRepository partidoRepo) {
        this.partidoRepo = partidoRepo;
    }

    public List<EstadisticaDTO> obtenerTablaPosiciones(Torneo torneo) {
        List<Partido> partidos = partidoRepo.findByTorneo(torneo);

        Map<Equipo, EstadisticaDTO> estadisticas = new HashMap<>();

        for (Partido partido : partidos) {
            Equipo local = partido.getEquipoLocal();
            Equipo visitante = partido.getEquipoVisitante();

            estadisticas.putIfAbsent(local, new EstadisticaDTO(local));
            estadisticas.putIfAbsent(visitante, new EstadisticaDTO(visitante));

            EstadisticaDTO estLocal = estadisticas.get(local);
            EstadisticaDTO estVisitante = estadisticas.get(visitante);

            // Goles
            int golesLocal = Optional.ofNullable(partido.getGolesLocal()).orElse(0);
            int golesVisitante = Optional.ofNullable(partido.getGolesVisitante()).orElse(0);

            estLocal.agregarPartido(golesLocal, golesVisitante);
            estVisitante.agregarPartido(golesVisitante, golesLocal);
        }

        // Ordenar por puntos y diferencia de goles
        return estadisticas.values().stream()
                .sorted(Comparator.comparingInt(EstadisticaDTO::getPuntos).reversed()
                        .thenComparingInt(EstadisticaDTO::getDiferenciaGoles).reversed())
                .collect(Collectors.toList());
    }
}
