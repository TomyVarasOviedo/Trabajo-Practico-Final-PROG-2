package clases;
/**
 * Clase que registra las ventas
 *
 */
public class Ventas {
	private int idVenta;
	private int dniCliente;
	private String codigoProducto;
	private String nombreProducto;
	private int cantidad;
	private String fechaCompra;
	private double monto;
	/**
	 * Constructor de la clase
	 * @param idVenta IDventa
	 * @param dniCliente Dni del cliente
	 * @param codigoProducto Codigo del producto
	 * @param nombreProducto Nombre del producto
	 * @param fechaCompra Fecha de compra
	 * @param monto Monto total
	 * @param cantidad Cantidad comprada
	 */
	public Ventas(int idVenta, int dniCliente, String codigoProducto, String nombreProducto,String fechaCompra, double monto, int cantidad) {
		this.idVenta = idVenta;
		this.dniCliente = dniCliente;
		this.codigoProducto = codigoProducto;
		this.nombreProducto = nombreProducto;
		this.fechaCompra = fechaCompra;
		this.monto = monto;
		this.cantidad = cantidad;
	}
	/**
	 * Metodo que devuelve el IDventa
	 * @return IDventa
	 */
	public int getIdVenta() {
		return idVenta;
	}
	/**
	 * Metodo para obtener el Dni del cliente
	 * @return
	 */
	public int getDniCliente() {
		return dniCliente;
	}
	/**
	 * Metodo para obtener el codigo del producto
	 * @return
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * Metodo para obtener el nombre del producto vendido
	 * @return Nombre producto vendido
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}
	/**
	 * Metodo para cambiar el nombre del producto vendido
	 * @param nombreProducto Nombre nuevo del producto
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	/**
	 * Metodo que devuelve la cantidad vendida
	 * @return Cantidad vendida
	 */
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * Metodo que cambia la cantidad vendida al cliente
	 * @param cantidad Cantidad nueva
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * Metodo para obtener la fecha de la compra
	 * @return Fecha de la compra
	 */
	public String getFechaCompra() {
		return fechaCompra;
	}
	/**
	 * Metodo para obtener el precio de la compra
	 * @return Precio de la compra
	 */
	public double getMonto() {
		return monto;
	}
	@Override
	public String toString() {
		return idVenta + "," + dniCliente + "," + codigoProducto+ "," + nombreProducto + "," + cantidad + "," +fechaCompra+ "," + monto;
	}

}
