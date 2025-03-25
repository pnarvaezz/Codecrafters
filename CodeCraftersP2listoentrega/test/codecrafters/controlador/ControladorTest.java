package codecrafters.controlador;

import codecrafters.modelo.Articulo;
import codecrafters.modelo.Datos;
import codecrafters.excepciones.ArticuloNoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControladorTest {
    private Controlador controlador;

    @BeforeEach
    public void setUp() {
        Datos datos = new Datos();

        Articulo articulo = new Articulo("ABC123", "Teclado mecánico", 49.99, 5.99, 2);
        datos.agregarArticulo(articulo);

        controlador = new Controlador(datos);
    }

    @Test
    public void testBuscarArticuloExistente_devuelveArticuloCorrecto() throws ArticuloNoEncontradoException {
        Articulo resultado = controlador.buscarArticuloPorCodigo("ABC123");
        assertNotNull(resultado);
        assertEquals("ABC123", resultado.getCodigo());
        assertEquals("Teclado mecánico", resultado.getDescripcion());
    }

    @Test
    public void testBuscarArticuloInexistente_lanzaExcepcion() {
        assertThrows(ArticuloNoEncontradoException.class, () -> {
            controlador.buscarArticuloPorCodigo("XYZ999");
        });
    }
}
