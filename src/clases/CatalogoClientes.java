package clases;

import java.util.ArrayList;

public class CatalogoClientes {
	private ArrayList <Clientes> catalogoClientes;

	public CatalogoClientes(ArrayList<Clientes> catalogoClientes) {
		this.catalogoClientes = catalogoClientes;
	}

	public ArrayList<Clientes> getCatalogoClientes() {
		return catalogoClientes;
	}

	public void setCatalogoClientes(ArrayList<Clientes> catalogoClientes) {
		this.catalogoClientes = catalogoClientes;
	}
	 
    public void agregar(Clientes a) {
        catalogoClientes.add(a);
    }
    
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
    
    public void eliminar(int dni) {
    	catalogoClientes.remove(buscarDni(dni));
    }

	
	

}
