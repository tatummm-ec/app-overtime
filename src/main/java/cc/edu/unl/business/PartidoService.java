package cc.edu.unl.business;

import cc.edu.unl.domain.Equipo;
import cc.edu.unl.domain.Fecha;
import cc.edu.unl.domain.Partido;
import cc.edu.unl.repository.PartidoRepository;
import cc.edu.unl.repository.Veredicto;
import jakarta.inject.Inject;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Stateless
public class PartidoService implements Serializable{

    @Inject
    private PartidoRepository partidoRepo;

    // Constructor público vacío requerido para EJB
    public PartidoService() {
    }

    public Partido crearPartido(
            LocalDate fecha,
            LocalTime hora,
            Equipo equipoLocal,
            Equipo equipoVisitante,
            Fecha fechaRango
    ) {
        validarDatos(fecha, hora, equipoLocal, equipoVisitante, fechaRango);

        Partido partido = new Partido();
        partido.setFecha(fecha);
        partido.setHora(hora);
        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        partido.setFechaRango(fechaRango);
        partido.setVeredicto(null);
        partido.setGolesLocal(0);
        partido.setGolesVisitante(0);
        partido.setMarcadorLocal(0);
        partido.setMarcadorVisitante(0);

        return partidoRepo.create(partido);
    }

    public void registrarResultado(Long partidoId, int golesLocal, int golesVisitante, Veredicto veredicto) {
        Partido partido = partidoRepo.find(partidoId);
        if (partido == null) {
            throw new IllegalArgumentException("Partido no encontrado.");
        }

        if (golesLocal < 0 || golesVisitante < 0) {
            throw new IllegalArgumentException("Los goles no pueden ser negativos.");
        }

        Veredicto esperado = calcularVeredicto(golesLocal, golesVisitante);

        if (!Objects.equals(esperado, veredicto)) {
            throw new IllegalArgumentException("El veredicto no concuerda con los goles registrados.");
        }

        partido.setGolesLocal(golesLocal);
        partido.setGolesVisitante(golesVisitante);
        partido.setMarcadorLocal(golesLocal);
        partido.setMarcadorVisitante(golesVisitante);
        partido.setVeredicto(veredicto);

        partidoRepo.update(partido);
    }

    private Veredicto calcularVeredicto(int golesLocal, int golesVisitante) {
        if (golesLocal > golesVisitante) return Veredicto.GANADO;
        if (golesLocal < golesVisitante) return Veredicto.PERDIDO;
        return Veredicto.EMPATADO;
    }

    private void validarDatos(LocalDate fecha, LocalTime hora, Equipo local, Equipo visitante, Fecha fechaRango) {
        if (fecha == null || hora == null || local == null || visitante == null || fechaRango == null) {
            throw new IllegalArgumentException("Todos los campos del partido son obligatorios.");
        }

        if (Objects.equals(local.getId(), visitante.getId())) {
            throw new IllegalArgumentException("El equipo local y visitante no pueden ser el mismo.");
        }

        if (fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha del partido no puede estar en el pasado.");
        }

        if (fecha.isBefore(fechaRango.getInicio()) || fecha.isAfter(fechaRango.getFin())) {
            throw new IllegalArgumentException("La fecha del partido no está dentro del rango de la jornada.");
        }
    }
}
