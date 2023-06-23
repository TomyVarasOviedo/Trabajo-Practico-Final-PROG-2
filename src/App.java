import clases.Productos;
import db.BaseDatos;

public class App {
    public static void main(String[] args) throws Exception {
        BaseDatos dBaseDatos = new BaseDatos();
        dBaseDatos.modificarProductos(new Productos("gk34", "wado","yarda",47.88,"03/08/2045","2"),"gk34");
    }
}
