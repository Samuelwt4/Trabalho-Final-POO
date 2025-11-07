package Exceptions;

public class UsuarioSemPermissaoException extends RuntimeException {
    public UsuarioSemPermissaoException(String message) {
        super(message);
    }
}
