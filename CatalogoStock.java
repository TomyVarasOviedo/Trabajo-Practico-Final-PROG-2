package clases;

import java.util.ArrayList;
/**
 * Clase que contiene al ArrayList de productos en el Stock
 *
 */
public class CatalogoStock {
	private ArrayList <ProductoStock> catalogoStock;
	/**
	 * Constructor de la clase
	 * @param catalogoStock ArrayList con los productos de la tabla Stock
	 */
	public CatalogoStock(ArrayList<ProductoStock> catalogoStock) {
		this.catalogoStock = catalogoStock;
	}
	/**
	 * Metodo que devuelve el ArrayList con los productos en el Stock
	 * @return ArrayList del Stock
	 */
	public ArrayList<ProductoStock> getCatalogoStock() {
		return catalogoStock;
	}
	/**
	 * Metodo que cambia el ArrayList de productos
	 * @param catalogoStock ArrayList con productos de Stock
	 */
	public void setcatalogoStock(ArrayList<ProductoStock> catalogoStock) {
		this.catalogoStock = catalogoStock;
	}
	/**
	 * Metodo para agregar al ArrayList productoStock
	 * @param a
	 */
	public void agregar(ProductoStock a) {
		catalogoStock.add(a);
	}
	/**
	 * Metodo para buscar un producto dentro del ArrayList
	 * @param idStock ID de stock del producto
	 * @return ProductoStock deseado
	 */
	public ProductoStock buscarid(int idStock) {
		ProductoStock m1 = null;
		for (ProductoStock m : catalogoStock) {
	    	if (m.getIdStock() == idStock) {
	        	m1 = m;
	        	break;
	    	}
		}
		return m1;
	}
	/**
	 * Metodo para buscar un producto en Stock por su codigo de producto
	 * @param codigo Codigo del producto
	 * @return ProductoStock deseado
	 */
	public ProductoStock buscarCodigoProducto(String codigo) {
	    ProductoStock m1 = null;
	    for (ProductoStock m : catalogoStock) {
	        if (m.getCodigo().equalsIgnoreCase(codigo)) {
	            m1 = m;
	            break;
	        }
	    }
	    return m1;
	}
	/**
	 * Metodo para elimar del ArrayList un producto del Stock
	 * @param idStock ID de stock
	 */
	public void eliminar(int idStock) {
	 catalogoStock.remove(buscarid(idStock));
	}
	/**
	 * Metodo para eliminar un producto del ArrayList
	 * @param codigo Codigo del producot en Stock
	 */
	public void eliminarProducto(String codigo) {
	 catalogoStock.remove(buscarCodigoProducto(codigo));
	}
}

