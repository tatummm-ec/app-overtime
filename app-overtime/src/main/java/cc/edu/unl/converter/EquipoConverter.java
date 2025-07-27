package cc.edu.unl.converter; // Se recomienda tener los conversores en un paquete aparte

import cc.edu.unl.business.EquipoService;
import cc.edu.unl.domain.Equipo;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject; // Para inyectar el EquipoService


@FacesConverter(value = "equipoConverter") // Define el nombre del conversor
public class EquipoConverter implements Converter<Equipo> {

    @Inject // Inyecta el EquipoService para buscar el equipo por ID
    private EquipoService equipoService;

    // Convierte un objeto Equipo a su representación String (el ID)
    @Override
    public String getAsString(FacesContext context, UIComponent component, Equipo equipo) {
        if (equipo == null) {
            return "";
        }
        // Devuelve el ID del equipo como String
        return equipo.getId() != null ? equipo.getId().toString() : "";
    }

    // Convierte un String (el ID) de vuelta a un objeto Equipo
    @Override
    public Equipo getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            // Busca el equipo completo usando el servicio
            return equipoService.buscarEquipoPorId(id);
        } catch (NumberFormatException e) {
            // Maneja el error si el valor no es un número válido
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "ID de equipo inválido."));
            return null;
        }
    }
}