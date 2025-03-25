package codecrafters.modelo;

public class ClientePremium extends Cliente {
    private final double descuentoEnvio = 0.20;

    public ClientePremium(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    @Override
    public String toString() {
        return super.toString() + " (Cliente Premium)";
    }

    public double calcularDescuentoEnvio(double gastosEnvio) {
        return gastosEnvio * (1 - descuentoEnvio);
    }

    public double calcularDescuentoPrecio(double precio) {
        return precio * (1 - 0.10);
    }
}
