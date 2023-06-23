package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Clientes;
import clases.ProductoStock;
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
    private ResultSet clientes = null;
    private ResultSet productos = null;
    private ResultSet stock = null;
    private ResultSet ventas = null;
    private ResultSet tipos = null;
    private ResultSet consulta = null;
    private String url = "jdbc:mysql://localhost/supermercado";
    private int insertRequest;
    private int updateRequest;
    private int deleteRequest;
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
                aux.add(new Clientes(dni, nombre, apellido, direccion, fnacimiento));
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
                aux.add(new ProductoStock(codigo, nombre, empresa, precio, fechaVecimiento, tipo, cantidad));
            }
        }catch (SQLException e) {
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
     * @throws SQLException
     */
    public ArrayList<String> obtenerTipo(){
        ArrayList<String> tiposArray= new ArrayList<String>();
        try {
            tipos = stmt.executeQuery("SELECT * FROM tipo ORDER BY id_tipo DESC;");
            while (tipos.next()) {
                tiposArray.add(tipos.getString("id_tipo") +","+tipos.getString("nombre"));
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
    public boolean agregarClientes(Clientes c) {
		try {
			insertRequest = stmt.executeUpdate("INSERT INTO clientes VALUES ("+c.getDni()+", '"+c.getNombre()+"', '"+c.getApellido()+"','"+c.getDireccion()+"', '"+c.getFnacimiento()+"');");
			return (insertRequest != 0)? true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
    public boolean agregarProducto(Productos p1) {
         ArrayList<String> tipos = obtenerTipo();
         int numTipo=0;
         for (String tipo : tipos) {
        	 String[] tipoCampos = tipo.split(",");
             if (tipoCampos[1].equals(p1.getTipo())) {
                 numTipo = Integer.parseInt(tipoCampos[0]);
             }
         }
        try {
            insertRequest = stmt.executeUpdate("INSERT INTO productos VALUES ('"+p1.getCodigo()+"', '"+p1.getNombre()+"', '"+p1.getEmpresa()+"',"+p1.getPrecio()+",'"+p1.getFvecimiento()+"',"+numTipo+");");
            return (insertRequest==1)? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean agregarStock(Productos p, int cantidad) {
		try {
			insertRequest = stmt.executeUpdate("INSERT INTO stock VALUES (NULL, '"+p.getCodigo()+"', '03/20/2023', "+cantidad+");");
			return (insertRequest!=0)? true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
    //-----------------------------
    //-----METODOS DE UPDATE-------
    //-----------------------------
    public boolean modificarProductos(Productos p, String codigoProducto) {
		try {
			updateRequest = stmt.executeUpdate("UPDATE productos SET nombre = '"+p.getNombre()+"', empresa='"+p.getEmpresa()+"', precio= "+p.getPrecio()+", fvencimiento='"+p.getFvecimiento()+"', tipo="+p.getTipo()+" WHERE codigo='"+codigoProducto+"';");
			return (updateRequest!= 0)? true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en la consulta");
			return false;
		}
	}
    //-----------------------------
    //-----METODOS DE DELETE-------
    //-----------------------------
    public boolean eliminarClientes(int dni) {
		try {
			deleteRequest = stmt.executeUpdate("DELETE FROM clientes WHERE dni="+dni+";");
			return (deleteRequest != 0)? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
    public boolean eliminarProducto(String codigo) {
		try {
			deleteRequest = stmt.executeUpdate("DELETE FROM productos WHERE codigo='"+codigo+"';");
			return (deleteRequest!=0)? true: false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
