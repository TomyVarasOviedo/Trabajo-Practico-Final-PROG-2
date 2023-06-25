package clases;

public class ProductoStock extends Productos{
	private int idStock;
	private int cantidad;
	public ProductoStock(int idStock,String codigo, String nombre, String empresa, Double precio, String fvecimiento, String tipo, int cantidad) {
		super(codigo, nombre, empresa, precio, fvecimiento, tipo);
		this.idStock = idStock;
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
	public int getIdStock() {
		return this.idStock;
	}
	public void setIdStock(int Id) {
		this.idStock = Id;
	}
}
