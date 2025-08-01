package cc.edu.unl.converter; // Se recomienda tener los conversores en un paquete aparte

import cc.edu.unl.business.EquipoService;
import cc.edu.unl.domain.Equipo;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject; // Para inyectar el EquipoService
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;


@FacesConverter(value = "equipoConverter")
public class EquipoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("Converter " + (component instanceof PickList ));
        if (component instanceof PickList) {
            System.out.println("PCKLIST");
            Object dualList = ((PickList) component).getValue();
            DualListModel<Equipo> dl = (DualListModel<Equipo>) dualList;
            System.out.println("source " +dl.getSource());
            for (Equipo equipo : dl.getSource()) {
                if (equipo.getId().toString().equals(value)) {
                    return equipo;
                }
            }
            System.out.println("Target "+dl.getTarget());
            for (Equipo equipo : dl.getTarget()) {
                if (equipo.getId().toString().equals(value)) {
                    return equipo;
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Equipo) {
            return ((Equipo) value).getId().toString();
        }
        return "";
    }
}
