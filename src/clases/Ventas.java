package clases;

public class Ventas {
	private int idVenta;
	private int dniCliente;
	private String codigoProducto;
	private String nombreProducto;
	private int cantidad;
	private String fechaCompra;
	private double monto;
	
	public Ventas(int idVenta, int dniCliente, String codigoProducto, String nombreProducto,String fechaCompra, double monto, int cantidad) {
		this.idVenta = idVenta;
		this.dniCliente = dniCliente;
		this.codigoProducto = codigoProducto;
		this.nombreProducto = nombreProducto;
		this.fechaCompra = fechaCompra;
		this.monto = monto;
		this.cantidad = cantidad;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(int dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
	@Override
	public String toString() {
		return idVenta + "," + dniCliente + "," + codigoProducto+ "," + nombreProducto + "," + cantidad + "," +fechaCompra+ "," + monto;
	}

}
