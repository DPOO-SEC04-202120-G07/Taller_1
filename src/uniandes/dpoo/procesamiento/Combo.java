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
	

	//Métodos públicos
	public void agregarItemACombo(ProductoMenu itemCombo) {

		itemsCombo.add(itemCombo);

	}

	public int getPrecio() {

		Iterator<ProductoMenu> iter_products = itemsCombo.iterator();
		int precio_total_con_descuento = 0;

		while(iter_products.hasNext()) {

			ProductoMenu producto_actual = iter_products.next();
			int precio_producto = producto_actual.getPrecio();

			precio_total_con_descuento += (precio_producto - (precio_producto * descuento));
			}

		return precio_total_con_descuento;

		
	}
	

	public String getNombre() {

		return this.nombreCombo;
	}
	
	public String generarTextoFactura() {
		
		Iterator<ProductoMenu> iter_products = itemsCombo.iterator();
		String factura_combo;

		while(iter_products.hasNext()) {

			ProductoMenu producto_actual = iter_products.next();
			String factura_producto = producto_actual.generarTextoFactura(descuento);

			factura_combo += factura_producto + "\n";
			}
		
		return factura_combo;
		
		
		
	}




}




