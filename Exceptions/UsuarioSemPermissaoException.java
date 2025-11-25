package Exceptions;

public class UsuarioSemPermissaoException extends Exception {
    public UsuarioSemPermissaoException(String mensagem) {
        super(mensagem);
    }
}
