package uniandes.dpoo.procesamiento;
import java.util.ArrayList;
import java.util.Iterator;

public class Combo implements Producto{
	//Atributos
	private double descuento;
	private String nombreCombo;
	private ArrayList<Producto> itemsCombo;
	private int id;

	//Método constructor
	public Combo(String nombre, double descuento, int id) {

		this.nombreCombo = nombre;
		this.descuento = descuento;
		this.id = id;
		this.itemsCombo = new ArrayList<Producto>();
		
	}
	
	//Métodos públicos
	public void agregarItemACombo(Producto itemCombo) {
		
		if (itemCombo != null) { // Diferencia las bebidas
		itemsCombo.add(itemCombo);}

	}

	public int getPrecio() {

		Iterator<Producto> iter_products = itemsCombo.iterator();
		int precio_total_con_descuento = 0;

		while(iter_products.hasNext()) {
			
			Producto producto_actual = iter_products.next();
			int precio_producto = producto_actual.getPrecio();


			precio_total_con_descuento += (precio_producto - (precio_producto * descuento));
			}

		return precio_total_con_descuento;

		
	}
	

	public String getNombre() {

		return this.nombreCombo;
	}
	
	public String generarTextoFactura() {
		
		Iterator<Producto> iter_products = itemsCombo.iterator();
		String factura_combo = "";
		
		int precio_combo = getPrecio();
		double iva_combo = precio_combo*0.19;
		double precio_combo_total = precio_combo + iva_combo;

		while(iter_products.hasNext()) {

			Producto producto_actual = iter_products.next();
			
			int precio_base = producto_actual.getPrecio();
			double precio_descuento = precio_base - (precio_base * descuento);
			double iva= precio_descuento * 0.19;
			double precioTotal= precio_descuento + iva;
			
			String factura_producto = ("		" + producto_actual.getNombre() + "| Precio base: "+ precio_base +"$" + "| Precio neto con descuento: "+ precio_descuento +"$" +"| IVA (19%): "+iva+"$" +"| Precio total: " + precioTotal +"$" );

			factura_combo += factura_producto + "\n";
			}
		
		
		factura_combo += ("\n" + this.nombreCombo + "| Precio Combo (neto): " + precio_combo +"$" + "|Iva (19%): " + iva_combo +"$" + "| Precio total: " + precio_combo_total) + "$" + "\n" + new String(new char[120]).replace("\0", "-") + "\n";
		
		return factura_combo;
		
		
		
	}
	
	public int getId() {
		return this.id;
		
	}




}




