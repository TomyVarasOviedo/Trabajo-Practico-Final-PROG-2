package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import clases.Clientes;
import clases.ProductoStock;
import clases.Productos;
import clases.Ventas;
import excepciones.StockVacio;
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
    private ResultSet ventas = null;
    private ResultSet tipos = null;
    private ResultSet consulta = null;
    private String url = "jdbc:mysql://localhost/supermercado";
    private int insertRequest;
    private int updateRequest;
    private int deleteRequest;
    private ZonedDateTime datetime = ZonedDateTime.now();
    private String año;
    private String mes;
    private String dia;
    //-----------------------------
    //-----CONSTRUCTOR-------------
    //-----------------------------
    /**
     * Constructor de la clase
     */
    public BaseDatos() {
    	año = String.valueOf(datetime.getYear());
        mes = String.valueOf(datetime.getMonthValue());
        dia = String.valueOf(datetime.getDayOfMonth());
        try {
            conexion = DriverManager.getConnection(url, "root", "batman");
            stmt = conexion.createStatement();
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
        return aux;
    }
    /**
     * Metodo que devuelve un ArrayList con el stock disponible
     * @return ArrayList con el stock disponible
     */
    public ArrayList<ProductoStock> obtenerStock() {
        ArrayList<ProductoStock> aux = new ArrayList<ProductoStock>();
        try {
            consulta = stmt.executeQuery("SELECT stock.id_stock, productos.codigo, productos.nombre, productos.empresa, productos.precio, productos.fvencimiento, tipo.nombre, cant_stock, stock.fecha FROM productos INNER JOIN tipo ON productos.tipo = id_tipo INNER JOIN stock ON productos.codigo = stock.id_producto;");
            while (consulta.next()) {
            	int idStock = Integer.parseInt(consulta.getString("stock.id_stock"));
                String codigo = consulta.getString("productos.codigo");
                String nombre = consulta.getString("productos.nombre");
                String empresa = consulta.getString("productos.empresa");
                Double precio = Double.parseDouble(consulta.getString("productos.precio"));
                String fechaVecimiento = consulta.getString("productos.fvencimiento");
                String tipo = consulta.getString("tipo.nombre");
                int cantidad = Integer.parseInt(consulta.getString("cant_stock"));
                aux.add(new ProductoStock(idStock,codigo, nombre, empresa, precio, fechaVecimiento, tipo, cantidad));
            }
        }catch (SQLException e) {
            e.printStackTrace();
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
    /**
     * Metodo que devuelve un ArrayList con objetos de tipo Venta
     * @return ArrayList de Ventas
     */
    public ArrayList<Ventas> obtenerVentas() {
    	ArrayList<Ventas> aux = new ArrayList<Ventas>();
    	try {
			ventas = stmt.executeQuery("SELECT ventas.id_ventas,ventas.id_cliente, productos.codigo, productos.nombre, ventas.fecha, ventas.monto, ventas.cantidad FROM ventas INNER JOIN stock ON ventas.id_stock = stock.id_stock INNER JOIN productos ON productos.codigo = stock.id_producto;");
			while (ventas.next()) {
				int idVentas = Integer.parseInt(ventas.getString("ventas.id_ventas"));
				int cliente = Integer.parseInt(ventas.getString("ventas.id_cliente"));
				String codigo = ventas.getString("productos.codigo");
				String nombreProducto = ventas.getString("productos.nombre");
				String fecha = ventas.getString("ventas.fecha");
				Double monto = Double.parseDouble(ventas.getString("ventas.monto"));
				int cantidad = Integer.parseInt(ventas.getString("ventas.cantidad"));
				aux.add(new Ventas(idVentas,cliente, codigo, nombreProducto, fecha, monto,cantidad));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return aux;
	}
    /**
     * Metodo para buscar en la tabla Productos por el codigo
     * @param codigo Codigo del producto
     * @return Devuleve el producto si lo encuentra, sino devuel null
     */
    public Productos buscarProductoCodigo(String codigo) {
		try {
			consulta = stmt.executeQuery("SELECT productos.nombre, productos.empresa, productos.precio, productos.fvencimiento, tipo.nombre FROM productos INNER JOIN tipo ON productos.tipo = tipo.id_tipo WHERE productos.codigo = '"+codigo+"';");
			if(consulta.next()) {
				String nombre = consulta.getString("productos.nombre");
				String empresa = consulta.getString("productos.empresa");
				Double precio = Double.parseDouble(consulta.getString("productos.precio"));
				String fecha = consulta.getString("productos.fvencimiento");
				String tipo = consulta.getString("tipo.nombre");
				return new Productos(codigo, nombre,empresa, precio, fecha,tipo);
			}else {
				throw new StockVacio();
			}
		} catch(StockVacio e) {
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    /**
     * Metodo para buscar en la tabla Stock un producto por su codigo
     * @param codigo Codigo del producto
     * @return Devuelve el producto si lo encuentra, sino devuelve null
     */
    public ProductoStock buscarProductoStock(String codigo) {
		try {
			consulta = stmt.executeQuery("SELECT * FROM stock INNER JOIN productos ON stock.id_producto = productos.codigo INNER JOIN tipo ON productos.tipo = tipo.id_tipo WHERE productos.codigo = '"+codigo+"'");
			consulta.next();
			int idStock = Integer.parseInt(consulta.getString("stock.id_stock"));
			String nombre = consulta.getString("productos.nombre");
			String empresa = consulta.getString("productos.empresa");
			Double precio = Double.parseDouble(consulta.getString("productos.precio"));
			String fecha = consulta.getString("productos.fvencimiento");
			String tipo = consulta.getString("tipo.nombre");
			int cantidad = Integer.parseInt(consulta.getString("stock.cant_stock"));
			return new ProductoStock(idStock, codigo, nombre, empresa, precio, fecha, tipo, cantidad);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    //-----------------------------
    //----METODOS DE INSERT--------
    //-----------------------------
    /**
     * Metodo que agrega a la tabla clientees un cliente
     * @param c Clientes que se desea agregar
     * @return Devuelve la confirmacion de la consulta
     */
    public String agregarClientes(Clientes c) {
		try {
			insertRequest = stmt.executeUpdate("INSERT INTO clientes VALUES ("+c.getDni()+", '"+c.getNombre()+"', '"+c.getApellido()+"','"+c.getDireccion()+"', '"+c.getFnacimiento()+"');");
			return (insertRequest != 0)? "Enviado correctamente":"Ocurrio un error al enviar";
		}catch(SQLIntegrityConstraintViolationException e) {
			return "El DNI del cliente ya se encuentra ingresado";
		}
		catch (SQLException e) {
			e.printStackTrace();
			return "Error al agregar al cliente";
		}
	}
    /**
     * Metodo que agrega a la tabla Productos un producto
     * @param p1 Producto que se desea agregar
     * @return Devuelve la confirmacion de la consulta
     */
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
        }catch(SQLIntegrityConstraintViolationException e) {
        	return false;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Metodo para agregar a la tabla Stock el nuevo producto
     * @param p Producto que se desea agregar
     * @param cantidad Cantidad que se desea agregar
     * @return Devuelva la confirmacion de la consulta {a: agregado, m:cantidad modificada, e:error}
     */
    public String agregarStock(Productos p, int cantidad) {
		try {
			if(this.buscarProductoCodigo(p.getCodigo()) == null) {
				this.agregarProducto(p);
				insertRequest = stmt.executeUpdate("INSERT INTO stock VALUES (NULL, '"+p.getCodigo()+"', '"+dia+"/"+mes+"/"+año+"', "+cantidad+");");				
			}else {
				this.aumentarCantidadStock(cantidad, p.getCodigo());
				return "m";
			}
			return (insertRequest!=0)? "a":"e";
		} catch (SQLException e) {
			e.printStackTrace();
			return "e";
		}
	}
    /**
     * Metodo que agrega a la base de datos una venta realizada
     * @param IDcliente ID del cliente que desea realizar la compra
     * @param cantidadDeseada Cantidad que va a comprar el cliente
     * @param p Producto que se encuentra en el stock y el cliente desea comprar
     * @return Devuelve un texto con el resultado de la consulta
     */
    public String agregarVentas(int IDcliente,int cantidadDeseada, ProductoStock p) {
		try {
			if(cantidadDeseada < p.getCantidad()) {
				double precio = p.getPrecio() * cantidadDeseada;
				insertRequest = stmt.executeUpdate("INSERT INTO ventas VALUES(NULL, "+IDcliente+","+p.getIdStock()+",'"+dia+"/"+mes+"/"+año+"',"+precio+","+cantidadDeseada+");");
				this.restarCantidadStock(cantidadDeseada, p.getCodigo());
				return (insertRequest != 0)? "Compra realizada correctamente" : "Ocurrio un error en la compra";
			}else {
				throw new StockVacio();
			}
		} catch(StockVacio e) {
			e.mesaje();
			return e.getMessage();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return "Ocurrio un error al realizar la compra, compruebe los datos";
		}
	}
    //-----------------------------
    //-----METODOS DE UPDATE-------
    //-----------------------------
    /**
     * Metodo para modificar un producto de la tabla productos
     * @param p Productos que se desea modificar
     * @param codigoProducto Codigo del producto
     * @return Devuelve la confirmacion
     */
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
    /**
     * Metodo para restar la cantidad de un producto en el Stock
     * @param cantidad Cantidad que se desea restar
     * @param codigo Codigo del producto que se desea restar
     * @return Devuelve la confirmacion de si se pudo actualizar la cantidad
     */
    public boolean restarCantidadStock(int cantidad, String codigo) {
    	try {
			updateRequest = stmt.executeUpdate("UPDATE stock SET cant_stock = cant_stock - "+cantidad+" WHERE id_producto = '"+codigo+"';");
			return (updateRequest != 0)? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
    /**
     * Metodo para aumentar la cantidad de un producto en el Stock
     * @param cantidad Cantidad que se desea aumentar
     * @param codigo Codigo del producto que se desea aumentar
     * @return Devuelve la confirmacion de si se pudo actualizar la cantidad
     */
    public boolean aumentarCantidadStock(int cantidad, String codigo) {
    	try {
			updateRequest = stmt.executeUpdate("UPDATE stock SET cant_stock = cant_stock + "+cantidad+" WHERE id_producto = '"+codigo+"';");
			return (updateRequest != 0)? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
    //-----------------------------
    //-----METODOS DE DELETE-------
    //-----------------------------
    /**
     * Metodo para eliminar un cliente de la base de datos
     * @param dni Dni del cliente
     * @return devulve verdadero si la consulta se realizo correctamente y falso si ocurrio algun problema
     */
    public boolean eliminarClientes(int dni) {
		try {
			deleteRequest = stmt.executeUpdate("DELETE FROM clientes WHERE dni="+dni+";");
			return (deleteRequest != 0)? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
    /**
     * Metodo para eliminar un producto de la base de datos
     * @param codigo Codigo del producto
     * @return Devuelve verdadero si la consulta se realizo correctamente y falso si ocurrio algun problema
     */
    public boolean eliminarProducto(String codigo) {
		try {
			deleteRequest = stmt.executeUpdate("DELETE FROM productos WHERE codigo='"+codigo+"';");
			this.eliminarStock(codigo);
			return (deleteRequest!=0)? true: false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
    /**
     * Metodo para eliminar un producto del Stock
     * @param idProducto Id del producto en la tabla de Stock
     * @return Devuele la confirmacion de si se pudo eliminar o no
     */
    public boolean eliminarStock(String idProducto) {
    	try {
			deleteRequest = stmt.executeUpdate("DELETE FROM stock WHERE id_producto = '"+idProducto+"';");
			return (deleteRequest != 0)? true: false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    
    }
}
