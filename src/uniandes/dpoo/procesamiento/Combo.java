package uniandes.dpoo.procesamiento;
import java.util.ArrayList;
import java.util.Iterator;

public class Combo implements Producto{
	//Atributos
	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> itemsCombo;

	//Método constructor
	public Combo(String nombre, double descuento) {

		this.nombreCombo = nombre;
		this.descuento = descuento;

	}


	public void agregarItemACombo(ProductoMenu itemCombo) {
	
		itemsCombo.add(itemCombo);

	}
	
	public int getPrecio() {
		
		Iterator<ProductoMenu> iter_products = itemsCombo.iterator();
		
		while(iter_products.hasNext()) {
			
			ProductoMenu producto_actual = iter_products.next();
			int precio_producto = producto_actual.getPrecio();
			
			return precio_producto;
			
		}
		
	}

	

}
