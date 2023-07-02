import java.util.ArrayList;
import clases.CatalogoClientes;
import clases.CatalogoStock;
import clases.CatalogoVentas;
import clases.Clientes;
import clases.ProductoStock;
import clases.Productos;
import clases.Ventas;
import db.BaseDatos;
import gui.Interfaz;

public class App {
    public static void main(String[] args) throws Exception {
    	BaseDatos db = new BaseDatos();
        CatalogoClientes catalogoClientes = new CatalogoClientes(db.obtenerClientes());
        CatalogoStock catalogoStock = new CatalogoStock(db.obtenerStock());
        CatalogoVentas catalogoVentas = new CatalogoVentas(db.obtenerVentas());
        ArrayList<String> tipos = db.obtenerTipo();
        Interfaz inter = new Interfaz(catalogoStock.getCatalogoStock(), catalogoClientes.getCatalogoClientes(), catalogoVentas.getCatalogoVentas(), tipos);
        db.eliminarProducto("sh345");
    }
}
