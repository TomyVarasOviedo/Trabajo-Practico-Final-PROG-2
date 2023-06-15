package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Clientes;
import clases.Productos;
/**
 * Clase para acceder a la base de datos
 */
public class BaseDatos {
    //-----------------------------
    //-------VARIABLES-------------
    //-----------------------------
    private Connection conexion = null;
    private Statement stmt = null;
    ResultSet clientes = null;
    ResultSet productos = null;
    ResultSet stock = null;
    ResultSet ventas = null;
    ResultSet tipos = null;
    ResultSet consulta = null;
    String[] columnas = null;
    String url = "jdbc:mysql://localhost/supermercado";
    //-----------------------------
    //-----CONSTRUCTOR-------------
    //-----------------------------
    /**
     * Constructor de la clase
     */
    public BaseDatos() {
        try {
            conexion = DriverManager.getConnection(url, "root", "batman");
            stmt = conexion.createStatement();
            // stock = stmt.executeQuery("SELECT * FROM stock;");
            // ventas = stmt.executeQuery("SELECT * FROM ventas;");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode());
        }
    }
    //-----------------------------
    //-----METODOS DE SELECT-------
    //-----------------------------
    /**
     * Metodo para devolver un ArrayList con todos los clientes
     * @return ArrayList con todos los clientes
     */
    public ArrayList<Clientes> obtenerClientes(){
        try {
            clientes = stmt.executeQuery("SELECT * FROM clientes;");
        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        ArrayList<Clientes> aux = new ArrayList<Clientes>();
        try {
            while (clientes.next()) {
                // Recorre cada registro
                int dni = Integer.parseInt(clientes.getString("dni"));
                String nombre = clientes.getString("nombre");
                String apellido = clientes.getString("apellido");
                String direccion = clientes.getString("direccion");
                String fnacimiento = clientes.getString("fnacimiento");
                System.out.println(dni+"-"+nombre+"-"+apellido+"-"+direccion+"-"+fnacimiento);
                // aux.add(new Clientes(dni, nombre, apellido, direccion, fnacimiento));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (aux.isEmpty()) {
            aux = null;
        }
        return aux;
    }
    /**
     * Metodo que devuelve un ArrayList con el stock disponible
     * @return ArrayList con el stock disponible
     */
    public ArrayList<Productos> obtenerStock() {
        ArrayList<Productos> aux = new ArrayList<Productos>();
        try {
            consulta = stmt.executeQuery("SELECT productos.codigo, productos.nombre, productos.empresa, productos.precio, productos.fvencimiento, tipo.nombre, cant_stock, stock.fecha FROM productos INNER JOIN tipo ON productos.tipo = id_tipo INNER JOIN stock ON productos.codigo = stock.id_producto;");
            while (consulta.next()) {
                String codigo = consulta.getString("productos.codigo");
                String nombre = consulta.getString("productos.nombre");
                String empresa = consulta.getString("productos.empresa");
                Double precio = Double.parseDouble(consulta.getString("productos.precio"));
                String fechaVecimiento = consulta.getString("productos.fvencimiento");
                String tipo = consulta.getString("tipo.nombre");
                int cantidad = Integer.parseInt(consulta.getString("cant_stock"));
                String fechaStock = consulta.getString("stock.fecha");
                System.out.println(codigo+"-"+nombre+"-"+empresa+"-"+consulta.getString("productos.precio")+"-"+fechaVecimiento+"-"+tipo+"-"+cantidad+"-"+fechaStock);
                aux.add(new Productos(codigo, nombre, empresa, precio, fechaVecimiento, tipo, cantidad));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (aux.isEmpty()) {
            aux = null;
        }
        return aux;
    }
    /**
     * Metodo para obtener un Array con todos los tipos de productos que hay
     * @return Array de tipo String con todos los tipos
     */
    public String[] obtenerTipo() {
        String[] tiposArray={};
        try {
            tipos = stmt.executeQuery("SELECT * FROM tipo ORDER BY id_tipo DESC;");
            tiposArray = new String[Integer.parseInt(tipos.getString("id_tipo"))];
            int i=0;
            while (tipos.next()) {
                tiposArray[i] = tipos.getString("nombre");
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return tiposArray;
    }
    //-----------------------------
    //----METODOS DE INSERT--------
    //-----------------------------
    public boolean agregarProducto(Productos p1) {
        try {
            consulta = stmt.executeQuery("INSERT INTO stock VALUES ('"+p1.getCodigo()+"', '"+p1.getNombre()+"', '"+p1.getEmpresa()+"',"+p1.getPrecio()+",'"+p1.getFvecimiento()+"',);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //-----------------------------
    //-----METODOS DE UPDATE-------
    //-----------------------------
}
