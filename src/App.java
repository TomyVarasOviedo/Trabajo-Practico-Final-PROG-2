import db.BaseDatos;

public class App {
    public static void main(String[] args) throws Exception {
        BaseDatos dBaseDatos = new BaseDatos();
        dBaseDatos.obtenerClientes();
    }
}
