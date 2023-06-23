package clases;
public class Clientes {
    private int dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String fnacimiento;
    public Clientes(int dni, String nombre, String apellido, String direccion, String fnacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.fnacimiento = fnacimiento;
    }
	@Override
	public String toString() {
		return "dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
				+ ", fnacimiiento=" + fnacimiento;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getFnacimiento() {
		return fnacimiento;
	}
	public void setFnacimiento(String fnacimiento) {
		this.fnacimiento = fnacimiento;
	}
    
}
