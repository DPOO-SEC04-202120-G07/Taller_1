package uniandes.dpoo.procesamiento;

public class Bebida implements Producto{
	
	//Atributos
	private String nombre;
	private int precioBase;
	private int id;
	
	
	//Método constructor
	public Bebida (String nombre, int precio, int id) {
		
		this.nombre = nombre;
		this.precioBase = precio;
		this.id = id;
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
	
	
	public String generarTextoFactura() {
		double iva=precioBase*0.19;
		double precioTotal=precioBase+iva;
		return nombre + " (Bebida)" +"| Precio neto base: "+ precioBase + "$" +"| IVA (19%): "+iva+  "$" +"| Precio total: " +precioTotal + "$" +"\n" + new String(new char[120]).replace("\0", "-") + "\n";
	}

	
	
}
