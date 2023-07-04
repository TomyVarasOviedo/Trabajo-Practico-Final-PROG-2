package clases;
/**
 * Clase Clientes
 * 
 */
public class Clientes {
    private int dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String fnacimiento;
    /**
     * Constructor de la clase
     * @param dni Dni del cliente
     * @param nombre Nombre del cliente
     * @param apellido Apellido del cliente
     * @param direccion Dirreccion del cliente
     * @param fnacimiento Fecha de nacimiento del cliente
     */
    public Clientes(int dni, String nombre, String apellido, String direccion, String fnacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.fnacimiento = fnacimiento;
    }
	@Override
	public String toString() {
		return dni + "," + nombre + "," + apellido + "," + direccion+ "," + fnacimiento;
	}
	/**
	 * Metodo que devulve el Dni del cliente
	 * @return Dni del cliente
	 */
	public int getDni() {
		return dni;
	}
	/**
	 * Metodo que cambia el Dni del cliente
	 * @param dni
	 */
	public void setDni(int dni) {
		this.dni = dni;
	}
	/**
	 * Metodo que devuelve el nombre del cliente
	 * @return Nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo que cambia el nombre del cliente
	 * @param Nombre nuevo del cliente
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo que devuelve el apellido del cliente
	 * @return Apellido del cliente
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * Metodo que cambia el apellido del cliente
	 * @param Apellido nuevo del cleinte
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * Metodo que devuelve la dirrecion del cliente
	 * @return Dirreccion del cliente
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * Metodo que cambia la direccion del cliente
	 * @param direccion Dirreccion nueva del cliente
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * Metodo que devuelve la fecha de nacimiento del cliente
	 * @return Fecha de nacimiento del cliente
	 */
	public String getFnacimiento() {
		return fnacimiento;
	}
    
}
