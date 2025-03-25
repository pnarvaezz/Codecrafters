import codecrafters.modelo.Datos;
import codecrafters.controlador.Controlador;
import codecrafters.vista.Vista;

public class Main {
    public static void main(String[] args) {
        Datos datos = new Datos();
        Controlador controlador = new Controlador(datos);
        Vista vista = new Vista(controlador);
        vista.mostrarMenu();
    }
}
