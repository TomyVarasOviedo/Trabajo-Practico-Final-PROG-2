package clases;

import java.util.ArrayList;

public class CatalogoVentas {
	private ArrayList <Ventas> catalogoVentas;
	
	public CatalogoVentas(ArrayList<Ventas> catalogoVentas) {
		this.catalogoVentas = catalogoVentas;
	}

	public ArrayList<Ventas> getCatalogoVentas() {
		return catalogoVentas;
	}

	public void setcatalogoVentas(ArrayList<Ventas> catalogoVentas) {
		this.catalogoVentas = catalogoVentas;
	}
	
	   public void agregar(Ventas a) {
	        catalogoVentas.add(a);
	    }
	    
	    public Ventas buscarid(int id) {
	        Ventas m1 = null;
	        for (Ventas m : catalogoVentas) {
	            if (m.getIdVenta() == id) {
	                m1 = m;
	                break;
	            }
	        }
	        return m1;
	    }
	    
	    public void eliminar(int id) {
	    	catalogoVentas.remove(buscarid(id));
	    }
}
