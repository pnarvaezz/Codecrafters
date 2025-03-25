package codecrafters.controlador;

import codecrafters.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EliminarPedidoTest {
    private Controlador controlador;
    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        Datos datos = new Datos();

        Cliente cliente = new Cliente("Juan", "Calle A", "12345678A", "juan@mail.com");
        Articulo articulo = new Articulo("R1", "Ratón", 20.0, 2.0, 1);

        datos.agregarCliente(cliente);
        datos.agregarArticulo(articulo);

        pedido = new Pedido(1, cliente, articulo, 1, new Date());
        datos.realizarPedido(pedido);

        controlador = new Controlador(datos);
    }

    @Test
    public void testEliminarPedidoExistente_devuelveTrue() {
        boolean eliminado = controlador.eliminarPedido(pedido);
        assertTrue(eliminado, "El pedido debería haberse eliminado correctamente.");
    }

    @Test
    public void testEliminarPedidoInexistente_devuelveFalse() {
        // Artículo y cliente ficticios pero válidos
        Cliente dummyCliente = new Cliente("Fake", "Ahora 123", "00000000X", "fake@mail.com");
        Articulo dummyArticulo = new Articulo("F1", "Ficticio", 0.0, 0.0, 0);

        // Pedido con número inexistente
        Pedido pedidoFalso = new Pedido(999, dummyCliente, dummyArticulo, 1, new Date());

        boolean eliminado = controlador.eliminarPedido(pedidoFalso);
        assertFalse(eliminado, "Un pedido inexistente no debería eliminarse.");
    }
}
