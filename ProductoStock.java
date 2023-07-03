package clases;
/**
 * Clase de producto en el stock, hereda de Productos
 * @author Usuario01
 *
 */
public class ProductoStock extends Productos{
	private int idStock;
	private int cantidad;
	/**
	 * Cosntructor de la clase
	 * @param idStock ID de stock
	 * @param codigo Codigo del producto
	 * @param nombre Nombre del producto
	 * @param empresa Empresa del producto
	 * @param precio Precio del producto
	 * @param fvecimiento Fecha de vencimiento del producto
	 * @param tipo Tipo de producto
	 * @param cantidad Cantidad en stock del producto
	 */
	public ProductoStock(int idStock,String codigo, String nombre, String empresa, Double precio, String fvecimiento, String tipo, int cantidad) {
		super(codigo, nombre, empresa, precio, fvecimiento, tipo);
		this.idStock = idStock;
		this.setCantidad(cantidad);
	}
	@Override
	public String toString() {
		return super.toString()+"," + cantidad;
	}
	/**
	 * Metodo que devuelve la cantidad de stock que hay
	 * @return Cantidad de stock disponible
	 */
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * Metodo que cambia la cantidad de stock
	 * @param cantidad Cantidad nueva de stock
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * Metodo que devuelve el IDstock
	 * @return IDstock
	 */
	public int getIdStock() {
		return this.idStock;
	}
	
	
}
