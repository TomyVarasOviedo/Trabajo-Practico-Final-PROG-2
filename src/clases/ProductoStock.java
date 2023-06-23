package clases;

public class ProductoStock extends Productos{
	private int cantidad;
	public ProductoStock(String codigo, String nombre, String empresa, Double precio, String fvecimiento, String tipo, int cantidad) {
		super(codigo, nombre, empresa, precio, fvecimiento, tipo);
		this.setCantidad(cantidad);
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
