package clases;

import java.util.ArrayList;

public class CatalogoStock {
	private ArrayList <ProductoStock> catalogoStock;
	
	public CatalogoStock(ArrayList<ProductoStock> catalogoStock) {
		this.catalogoStock = catalogoStock;
	}

	public ArrayList<ProductoStock> getCatalogoStock() {
		return catalogoStock;
	}

	public void setcatalogoStock(ArrayList<ProductoStock> catalogoStock) {
		this.catalogoStock = catalogoStock;
	}
	
	   public void agregar(ProductoStock a) {
	        catalogoStock.add(a);
	    }
	    
	    public ProductoStock buscarid(int idStock) {
	        ProductoStock m1 = null;
	        for (ProductoStock m : catalogoStock) {
	            if (m.getIdStock() == idStock) {
	                m1 = m;
	                break;
	            }
	        }
	        return m1;
	    }
	    
	    public void eliminar(int idStock) {
	    	catalogoStock.remove(buscarid(idStock));
	    }
}

