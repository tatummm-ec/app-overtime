package cc.edu.unl.exception;

public class CredentialInvalidException extends RuntimeException {
    public CredentialInvalidException() {
        super("Credenciales invalidas");
    }
    public CredentialInvalidException(String message) {
        super(message);
    }
}
