package cc.edu.unl.business;

import cc.edu.unl.domain.Equipo;
import cc.edu.unl.repository.EquipoRepository;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.util.List;
@Stateless
public class EquipoService implements Serializable {

    private  EquipoRepository equipoRepo;

    public EquipoService() {
        // Constructor sin argumentos requerido por el contenedor
    }

    public EquipoService(EquipoRepository equipoRepo) {
        this.equipoRepo = equipoRepo;
    }

    /**
     * Crea un nuevo equipo validando los datos de entrada.
     *
     * @param nombre nombre del equipo
     * @param ciudad ciudad de origen
     * @return equipo creado
     * @throws IllegalArgumentException si los datos son inválidos
     */
    public Equipo crearEquipo(String nombre, String ciudad) {
        validarDatosEquipo(nombre, ciudad);

        Equipo equipo = new Equipo();
        equipo.setNombre(nombre);
        equipo.setCiudad(ciudad);

        return equipoRepo.create(equipo);
    }

    public List<Equipo> listarEquipos() {
        return equipoRepo.findAll();
    }

    public Equipo obtenerEquipoPorId(Long id) {
        Equipo equipo = equipoRepo.find(id);
        if (equipo == null) {
            throw new IllegalArgumentException("Equipo no encontrado con ID: " + id);
        }
        return equipo;
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
