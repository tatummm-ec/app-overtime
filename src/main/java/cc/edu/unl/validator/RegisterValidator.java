package cc.edu.unl.validator;

import java.util.*;

public class RegisterValidator {

    public static Optional<String> validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return Optional.of("El nombre es obligatorio.");
        }
        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            return Optional.of("El nombre no debe contener números ni caracteres especiales.");
        }
        return Optional.empty();
    }

    public static Optional<String> validarCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            return Optional.of("El correo es obligatorio.");
        }
        if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            return Optional.of("El correo no es válido.");
        }
        return Optional.empty();
    }
}
