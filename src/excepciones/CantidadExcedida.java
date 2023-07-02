package excepciones;

import javax.swing.JOptionPane;

public class CantidadExcedida extends Exception{
	public CantidadExcedida() {
		super("Cantidad Excedida");
		JOptionPane.showConfirmDialog(null, "El cliente ha sobrepasado la cantidad del stock","",JOptionPane.DEFAULT_OPTION);
	}
}
