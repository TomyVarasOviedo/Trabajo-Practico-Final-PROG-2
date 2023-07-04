package excepciones;

import javax.swing.JOptionPane;

public class StockVacio extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StockVacio() {
		super("Se ha superado el limite del stock del producto");
	}
	public void mesaje() {
		JOptionPane.showConfirmDialog(null, "El cliente ha sobrepasado la cantidad del stock","",JOptionPane.DEFAULT_OPTION);
	}
}
