import clases.Clientes;
import clases.Productos;
import db.BaseDatos;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            BaseDatos dBaseDatos = new BaseDatos();
            dBaseDatos.agregarClientes(new Clientes(4567890,"marcelo","montinela", "calle2255, hidalgo","05/07/2005"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
