import clases.Clientes;
import clases.ProductoStock;
import clases.Productos;
import db.BaseDatos;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            BaseDatos dBaseDatos = new BaseDatos();
           System.out.println( dBaseDatos.agregarVentas(4567890,4, new ProductoStock(2,"sh345", "","",45.55,"","",20)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
