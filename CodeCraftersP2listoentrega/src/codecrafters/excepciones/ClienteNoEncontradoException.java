// ClienteNoEncontradoException.java
package codecrafters.excepciones;

public class ClienteNoEncontradoException extends Exception {
    public ClienteNoEncontradoException(String message) {
        super(message);  // Pasa el mensaje a la clase base Exception
    }
}
