package cc.edu.unl.business;

import cc.edu.unl.domain.Equipo;
import cc.edu.unl.domain.Inscripcion;
import cc.edu.unl.domain.Torneo;
import cc.edu.unl.repository.InscripcionRepository;
import jakarta.ejb.Stateless;

import java.time.LocalDate;
import java.util.List;
@Stateless
public class InscripcionService {

    private InscripcionRepository inscripcionRepo;

    public InscripcionService(){

    }

    public InscripcionService(InscripcionRepository inscripcionRepo) {
        this.inscripcionRepo = inscripcionRepo;
    }

    /**
     * Inscribe un equipo en el torneo seleccionado.
     *
     * @param equipo Equipo a inscribir
     * @param torneo Torneo donde se va a inscribir
     */
    public void inscribirEquipo(Equipo equipo, Torneo torneo) {
        validarDatos(equipo, torneo);

        boolean yaInscrito = inscripcionRepo.estaInscrito(equipo, torneo);
        if (yaInscrito) {
            throw new IllegalArgumentException("El equipo ya está inscrito en este torneo.");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEquipo(equipo);
        inscripcion.setTorneo(torneo);
        inscripcion.setFechaRegistro(LocalDate.now());

        inscripcionRepo.create(inscripcion);
    }

    private void validarDatos(Equipo equipo, Torneo torneo) {
        if (equipo == null || equipo.getId() == null) {
            throw new IllegalArgumentException("Debe seleccionar un equipo válido.");
        }

        if (torneo == null || torneo.getId() == null) {
            throw new IllegalArgumentException("Debe seleccionar un torneo válido.");
        }
    }

}
