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
            clientes = stmt.executeQuery("SELECT * FROM clientes;");
            // productos = stmt.executeQuery("SELECT * FROM productos;");
            // stock = stmt.executeQuery("SELECT * FROM stock;");
            // ventas = stmt.executeQuery("SELECT * FROM ventas;");
            // tipos = stmt.executeQuery("SELECT * FROM tipos;");
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
        ArrayList<Clientes> aux = new ArrayList<Clientes>();
        try {
            while (clientes.next()) {
                // Recorre cada registro
                int dni = Integer.parseInt(clientes.getString(0));
                String nombre = clientes.getString(1);
                String apellido = clientes.getString(2);
                String direccion = clientes.getString(3);
                String fnacimiento = clientes.getString(4);
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
                String codigo = consulta.getString(0);
                String nombre = consulta.getString(1);
                String empresa = consulta.getString(2);
                Double precio = Double.parseDouble(consulta.getString(3));
                String fechaVecimiento = consulta.getString(4);
                String tipo = consulta.getString(5);
                int cantidad = Integer.parseInt(consulta.getString(6));
                String fechaStock = consulta.getString(7);
                System.out.println(codigo+"-"+nombre+"-"+empresa+"-"+consulta.getString(3)+"-"+fechaVecimiento+"-"+tipo+"-"+cantidad+"-"+fechaStock);
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
    //-----------------------------
    //----METODOS DE INSERT--------
    //-----------------------------

    //-----------------------------
    //-----METODOS DE UPDATE-------
    //-----------------------------
}
