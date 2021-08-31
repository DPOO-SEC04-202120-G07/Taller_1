package uniandes.dpoo.procesamiento;

public class Bebida implements Producto{
	
	//Atributos
	private String nombre;
	private int precioBase;
	private int id;
	private int calorias;
	
	
	//Método constructor
	public Bebida (String nombre, int precio, int id, int calorias) {
		
		this.nombre = nombre;
		this.precioBase = precio;
		this.id = id;
		this.calorias = calorias;
	}
	
	//Métodos públicos
	
	public int getPrecio() {
		return this.precioBase;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getCalorias() {
		return this.calorias;
	}
	
	
	public String generarTextoFactura() {
		double iva=precioBase*0.19;
		double precioTotal=precioBase+iva;
	
		
		return nombre + " (Bebida)"+ " (Calorias: " + this.calorias  +")| Precio neto base: "+ precioBase + "$" +"| IVA (19%): "+iva+  "$" +"| Precio total: " +precioTotal + "$" +"\n" + new String(new char[120]).replace("\0", "-") + "\n";
	}

	
	
}
