package clases;

import java.util.ArrayList;
/**
 * Clase para contener las ventas de la base de datos
 *
 */
public class CatalogoVentas {
	private ArrayList <Ventas> catalogoVentas;
	/**
	 * Constructor de la clase
	 * @param catalogoVentas Arraylist con Ventas
	 */
	public CatalogoVentas(ArrayList<Ventas> catalogoVentas) {
		this.catalogoVentas = catalogoVentas;
	}
	/**
	 * Metodo que devulve el ArrayList con Ventas
	 * @return
	 */
	public ArrayList<Ventas> getCatalogoVentas() {
		return catalogoVentas;
	}
	/**
	 * Metodo que cambia el Arraylist por otro
	 * @param catalogoVentas ArrayList con Ventas
	 */
	public void setcatalogoVentas(ArrayList<Ventas> catalogoVentas) {
		this.catalogoVentas = catalogoVentas;
	}
	/**
	 * Metodo que agrega al ArrayList una Venta
	 * @param a Venta
	 */
	public void agregar(Ventas a) {
	     catalogoVentas.add(a);
	}
	/**
	 * Metodo que busca una Venta por su IDventa
	 * @param id ID venta
	 * @return Venta deseada
	 */
	public Ventas buscarid(int id) {
	    Ventas m1 = null;
	    for (Ventas m : catalogoVentas) {
	        if (m.getIdVenta() == id) {
	            m1 = m;
	            break;
	        }
	    }
	    return m1;
	}
	/**
	 * Metodo que eliminar una Venta del Arraylist  
	 * @param id IDventa
	 */
	public void eliminar(int id) {
	 catalogoVentas.remove(buscarid(id));
	}
}
