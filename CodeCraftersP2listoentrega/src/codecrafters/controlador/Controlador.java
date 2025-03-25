package codecrafters.controlador;

import codecrafters.modelo.*;
import codecrafters.excepciones.ArticuloNoEncontradoException;
import codecrafters.excepciones.ClienteNoEncontradoException;

public class Controlador {
    private Datos datos;

    public Controlador(Datos datos) {
        this.datos = datos;
    }

    public void agregarArticulo(Articulo articulo) {
        datos.agregarArticulo(articulo);
    }

    public void agregarCliente(Cliente cliente) {
        datos.agregarCliente(cliente);
    }

    public void realizarPedido(Pedido pedido) {
        datos.realizarPedido(pedido);
    }

    public Articulo buscarArticuloPorCodigo(String codigo) throws ArticuloNoEncontradoException {
        Articulo articulo = datos.buscarArticuloPorCodigo(codigo);
        if (articulo == null) {
            throw new ArticuloNoEncontradoException("El artículo con código " + codigo + " no fue encontrado.");
        }
        return articulo;
    }

    public Cliente buscarClientePorEmail(String email) throws ClienteNoEncontradoException {
        Cliente cliente = datos.buscarClientePorEmail(email);
        if (cliente == null) {
            throw new ClienteNoEncontradoException("El cliente con email " + email + " no fue encontrado.");
        }
        return cliente;
    }

    public Pedido buscarPedidoPorNumero(int numeroPedido) {
        return datos.buscarPedidoPorNumero(numeroPedido);
    }

    public boolean eliminarPedido(Pedido pedido) {
        return datos.eliminarPedido(pedido);
    }

    public void mostrarPedidosPendientes() {
        datos.mostrarPedidosPendientes();
    }

    public void mostrarPedidosEnviados() {
        datos.mostrarPedidosEnviados();
    }

    public int generarNumeroPedido() {
        return datos.generarNumeroPedido();
    }

    // Método adicional para obtener la lista de artículoss
    public java.util.List<Articulo> getArticulos() {
        return datos.getArticulos();
    }

    // Método adicional para obtener la lista de clientes
    public java.util.List<Cliente> getClientes() {
        return datos.getClientes();
    }
}
