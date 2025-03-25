// ArticuloNoEncontradoException.java
package codecrafters.excepciones;

public class ArticuloNoEncontradoException extends Exception {
    public ArticuloNoEncontradoException(String message) {
        super(message);  // Pasa el mensaje a la clase base Exception
    }
}
