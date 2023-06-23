package clases;

public class ProductoStock extends Productos{
	private int cantidad;
	public ProductoStock(String codigo, String nombre, String empresa, Double precio, String fvecimiento, String tipo, int cantidad) {
		super(codigo, nombre, empresa, precio, fvecimiento, tipo);
		this.setCantidad(cantidad);
	}
	@Override
	public String toString() {
		return super.toString()+", cantidad=" + cantidad;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
