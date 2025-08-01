package cc.edu.unl.business;

import cc.edu.unl.domain.Equipo;
import cc.edu.unl.repository.EquipoRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;
@Stateless
public class EquipoService implements Serializable {

    @Inject
    private  EquipoRepository equipoRepo;

    public EquipoService() {
        // Constructor sin argumentos requerido por el contenedor
    }

    public EquipoService(EquipoRepository equipoRepo) {
        this.equipoRepo = equipoRepo;
    }

    public Equipo crearEquipo(Equipo equipo) {
        return equipoRepo.create(equipo);
    }

    public List<Equipo> listarEquipos() {
        return equipoRepo.findAll();
    }

    public void editarEquipo(Equipo equipo) {
        equipoRepo.update(equipo);
    }

    private void validarDatosEquipo(String nombre, String ciudad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo es obligatorio.");
        }

        if (ciudad == null || ciudad.trim().isEmpty()) {
            throw new IllegalArgumentException("La ciudad del equipo es obligatoria.");
        }

        if (nombre.length() > 100) {
            throw new IllegalArgumentException("El nombre del equipo no puede exceder los 100 caracteres.");
        }

        if (ciudad.length() > 100) {
            throw new IllegalArgumentException("La ciudad del equipo no puede exceder los 100 caracteres.");
        }
    }
}
