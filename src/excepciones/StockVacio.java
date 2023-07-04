package excepciones;

public class StockVacio extends Exception{
	public StockVacio() {
		super("Se ha superado el limite del stock del producto");
	}
}
