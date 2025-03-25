package codecrafters.modelo;

import java.util.Date;

public class Pedido {
    private int numeroPedido;
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private Date fechaHora;
    private double gastosEnvioConDescuento;

    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad, Date fechaHora) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;

        if (cliente instanceof ClientePremium) {
            ClientePremium clientePremium = (ClientePremium) cliente;
            this.gastosEnvioConDescuento = clientePremium.calcularDescuentoEnvio(articulo.getGastosEnvio());
        } else {
            this.gastosEnvioConDescuento = articulo.getGastosEnvio();
        }
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public double getGastosEnvioConDescuento() {
        return gastosEnvioConDescuento;
    }

    public boolean estaEnviado() {
        Date ahora = new Date();
        long tiempoTranscurrido = ahora.getTime() - fechaHora.getTime();
        return tiempoTranscurrido >= (articulo.getTiempoPreparacion() * 60 * 1000);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido=" + numeroPedido +
                ", cliente=" + cliente +
                ", articulo=" + articulo +
                ", cantidad=" + cantidad +
                ", fechaHora=" + fechaHora +
                ", gastosEnvioConDescuento=" + gastosEnvioConDescuento +
                '}';
    }
}
