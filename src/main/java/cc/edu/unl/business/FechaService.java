package cc.edu.unl.business;

import cc.edu.unl.domain.Fecha;
import cc.edu.unl.repository.FechaRepository;
import jakarta.ejb.Stateless;

import java.time.LocalDate;
import java.util.List;
@Stateless
public class FechaService {

    private FechaRepository fechaRepo;

    public FechaService() {

    }

    public FechaService(FechaRepository fechaRepo) {
        this.fechaRepo = fechaRepo;
    }

    /**
     * Crea y guarda una nueva fecha (jornada o fase) del torneo.
     *
     * @param inicio Fecha de inicio
     * @param fin    Fecha de fin
     * @return Fecha creada
     */
    public Fecha crearFecha(LocalDate inicio, LocalDate fin) {
        validarFechas(inicio, fin);

        Fecha nuevaFecha = new Fecha();
        nuevaFecha.setInicio(inicio);
        nuevaFecha.setFin(fin);

        return fechaRepo.create(nuevaFecha);
    }

    public List<Fecha> listarFechas() {
        return fechaRepo.findAll();
    }

    private void validarFechas(LocalDate inicio, LocalDate fin) {
        if (inicio == null || fin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias.");
        }

        if (inicio.isAfter(fin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }

        if (inicio.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede estar en el pasado.");
        }
    }
}
