package clases;
/**
 * Clase de productos
 * @author Usuario01
 *
 */
public class Productos {
    String codigo;
    String nombre;
    String empresa;
    Double precio;
    String fvecimiento;
    String tipo;
    int cantidad;
    /**
     * Constructor de la clase
     * @param codigo Codigo del producto
     * @param nombre Nombre del producto
     * @param empresa Empresa del producto
     * @param precio Precio del producto
     * @param fvecimiento Fecha de vencimiento del producto
     * @param tipo Tipo de producto
     */
    public Productos(String codigo, String nombre, String empresa, Double precio, String fvecimiento, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.empresa = empresa;
        this.precio = precio;
        this.fvecimiento = fvecimiento;
        this.tipo = tipo;
    }
    /**
     * Metodo que devuelve el codigo del producto
     * @return Codigo del producto
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Metodo que devuelve el nombre del producto
     * @return Nombre del producto
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Metodo que devuelve la empresa del producto
     * @return Empresa del producto
     */
    public String getEmpresa() {
        return empresa;
    }
    /**
     * Metodo que devuelve el precio del producto
     * @return Precio del producto
     */
    public Double getPrecio() {
        return precio;
    }
    /**
     * Metodo que devuelve la fecha de vencimiento del producto
     * @return Fecha de vecimiento del producto
     */
    public String getFvecimiento() {
        return fvecimiento;
    }
    /**
     * Metodo que devuelve el tipo de producto
     * @return Tipo de producto
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * Metodo que cambia el tipo de producto
     * @param tipo Tipo nuevo de producto
     */
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
	@Override
	public String toString() {
		return codigo + "," + nombre + "," + empresa + "," + precio+ "," + fvecimiento + "," + tipo;
	}
}
