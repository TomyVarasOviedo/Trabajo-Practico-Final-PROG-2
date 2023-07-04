package clases;

import java.util.ArrayList;
/**
 *Clase que contiene a todos lso clientes en la base de datos
 */
public class CatalogoClientes {
	private ArrayList <Clientes> catalogoClientes;
	/**
	 * Constructor de la clase
	 * @param catalogoClientes ArrayList con todos los clientes
	 */
	public CatalogoClientes(ArrayList<Clientes> catalogoClientes) {
		this.catalogoClientes = catalogoClientes;
	}
	/**
	 * metodo para devolver el ArrayList con todos los clientes
	 * @return
	 */
	public ArrayList<Clientes> getCatalogoClientes() {
		return catalogoClientes;
	}
	/**
	 * Metodo para cambiar los clientes del ArrayList
	 * @param catalogoClientes
	 */
	public void setCatalogoClientes(ArrayList<Clientes> catalogoClientes) {
		this.catalogoClientes = catalogoClientes;
	}
	/**
	 * Metodo para agregar al ArrayList un cliente
	 * @param a Cliente para agregar
	 */
    public void agregar(Clientes a) {
        catalogoClientes.add(a);
    }
    /**
     * Metodo para buscar a un cliente por su dni
     * @param dni Dni del cliente
     * @return
     */
    public Clientes buscarDni(int dni) {
        Clientes m1 = null;
        for (Clientes m : catalogoClientes) {
            if (m.getDni() == dni) {
                m1 = m;
                break;
            }
        }
        return m1;
    }
    /**
     * Metodo para eliminar a un cliente del ArrayList
     * @param dni
     */
    public void eliminar(int dni) {
    	catalogoClientes.remove(buscarDni(dni));
    }

	
	

}
