package codecrafters.modelo;

import java.util.*;

public class Datos {
    private List<Cliente> clientes;
    private List<Articulo> articulos;
    private List<Pedido> pedidos;

    public Datos() {
        this.clientes = new ArrayList<>();
        this.articulos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void agregarArticulo(Articulo articulo) {
        articulos.add(articulo);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void realizarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Articulo buscarArticuloPorCodigo(String codigo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigo().equalsIgnoreCase(codigo)) {
                return articulo;
            }
        }
        return null;
    }

    public Cliente buscarClientePorEmail(String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equalsIgnoreCase(email)) {
                return cliente;
            }
        }
        return null;
    }

    public Pedido buscarPedidoPorNumero(int numeroPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNumeroPedido() == numeroPedido) {
                return pedido;
            }
        }
        return null;
    }

    public boolean eliminarPedido(Pedido pedido) {
        if (!pedido.estaEnviado()) {
            pedidos.remove(pedido);
            return true;
        }
        return false;
    }

    public void mostrarPedidosPendientes() {
        for (Pedido pedido : pedidos) {
            if (!pedido.estaEnviado()) {
                System.out.println(pedido);
            }
        }
    }

    public void mostrarPedidosEnviados() {
        for (Pedido pedido : pedidos) {
            if (pedido.estaEnviado()) {
                System.out.println(pedido);
            }
        }
    }

    public int generarNumeroPedido() {
        return pedidos.size() + 1;
    }

    // Metodo genérico añadido para mostrar cualquier lista, no vamos a hacer uso de él porque no lo creemos necesario por el momento
    public <T> void mostrarListaGenerica(List<T> lista) {
        for (T elemento : lista) {
            System.out.println(elemento);
        }
    }
}