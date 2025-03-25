package codecrafters.vista;

import codecrafters.controlador.Controlador;
import codecrafters.modelo.*;
import codecrafters.excepciones.ArticuloNoEncontradoException;
import codecrafters.excepciones.ClienteNoEncontradoException;
import java.util.Date;
import java.util.Scanner;

public class Vista {
    private Controlador controlador;
    private Scanner scanner;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n--- Menú de Gestión de Tienda ---");
            System.out.println("1. Añadir Artículo");
            System.out.println("2. Mostrar Artículos");
            System.out.println("3. Añadir Cliente");
            System.out.println("4. Mostrar Clientes");
            System.out.println("5. Añadir Pedido");
            System.out.println("6. Eliminar Pedido");
            System.out.println("7. Mostrar Pedidos Pendientes");
            System.out.println("8. Mostrar Pedidos Enviados");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    this.agregarArticulo();
                    break;
                case 2:
                    this.mostrarArticulos();
                    break;
                case 3:
                    this.agregarCliente();
                    break;
                case 4:
                    this.mostrarClientes();
                    break;
                case 5:
                    this.agregarPedido();
                    break;
                case 6:
                    this.eliminarPedido();
                    break;
                case 7:
                    this.mostrarPedidosPendientes();
                    break;
                case 8:
                    this.mostrarPedidosEnviados();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void agregarArticulo() {
        System.out.print("Código del artículo: ");
        String codigo = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Precio de venta: ");
        double precio = scanner.nextDouble();
        System.out.print("Gastos de envío: ");
        double gastosEnvio = scanner.nextDouble();
        System.out.print("Tiempo de preparación (minutos): ");
        int tiempoPreparacion = scanner.nextInt();
        scanner.nextLine();

        Articulo articulo = new Articulo(codigo, descripcion, precio, gastosEnvio, tiempoPreparacion);
        controlador.agregarArticulo(articulo);
        System.out.println("Artículo añadido con éxito.");
    }

    private void mostrarArticulos() {
        System.out.println("Lista de artículos:");
        for (Articulo art : controlador.getArticulos()) {
            System.out.println(art);
        }
    }

    private void agregarCliente() {
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("NIF: ");
        String nif = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("¿Es cliente premium? (S/N): ");
        String tipoCliente = scanner.nextLine();

        Cliente cliente;
        if (tipoCliente.equalsIgnoreCase("S")) {
            cliente = new ClientePremium(nombre, domicilio, nif, email);
        } else {
            cliente = new Cliente(nombre, domicilio, nif, email);
        }
        controlador.agregarCliente(cliente);
        System.out.println("Cliente añadido con éxito.");
    }

    private void mostrarClientes() {
        System.out.println("Lista de clientes:");
        for (Cliente cli : controlador.getClientes()) {
            System.out.println(cli);
        }
    }

    private void agregarPedido() {
        System.out.print("Email del cliente: ");
        String email = scanner.nextLine();
        Cliente clienteExistente;
        try {
            clienteExistente = controlador.buscarClientePorEmail(email);
        } catch (ClienteNoEncontradoException e) {
            System.out.println(e.getMessage());
            return; // Si el cliente no existe, detenemos la ejecución
        }

        System.out.print("Código del artículo: ");
        String codigo = scanner.nextLine();
        Articulo articuloSeleccionado;
        try {
            articuloSeleccionado = controlador.buscarArticuloPorCodigo(codigo);
        } catch (ArticuloNoEncontradoException e) {
            System.out.println(e.getMessage());
            return; // Si el artículo no existe, detenemos la ejecución
        }

        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = new Pedido(controlador.generarNumeroPedido(), clienteExistente, articuloSeleccionado, cantidad, new Date());
        controlador.realizarPedido(pedido);
        System.out.println("Pedido realizado con éxito.");
    }

    private void eliminarPedido() {
        System.out.print("Número de pedido a eliminar: ");
        int numPedido = scanner.nextInt();
        scanner.nextLine();

        Pedido pedidoEliminar = controlador.buscarPedidoPorNumero(numPedido);
        if (pedidoEliminar != null) {
            // Verificamos si el pedido ya fue enviado
            if (pedidoEliminar.estaEnviado()) {
                System.out.println("No se puede eliminar el pedido porque ya ha sido enviado.");
            } else {
                if (controlador.eliminarPedido(pedidoEliminar)) {
                    System.out.println("Pedido eliminado con éxito.");
                } else {
                    System.out.println("No se puede eliminar el pedido.");
                }
            }
        } else {
            System.out.println("No se encontró un pedido con ese número.");
        }
    }

    private void mostrarPedidosPendientes() {
        System.out.println("Pedidos pendientes:");
        controlador.mostrarPedidosPendientes();
    }

    private void mostrarPedidosEnviados() {
        System.out.println("Pedidos enviados:");
        controlador.mostrarPedidosEnviados();
    }
}
