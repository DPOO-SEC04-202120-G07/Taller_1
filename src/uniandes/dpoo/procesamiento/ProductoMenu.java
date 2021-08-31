package uniandes.dpoo.procesamiento;

public class ProductoMenu implements Producto{
	//Atributos
	private String nombre;
	private int precioBase;
	private int id;
	private int calorias;
	
	//Metodo constructor
	public ProductoMenu(String nombre, int precioBase, int id, int calorias) {
		this.nombre=nombre;
		this.precioBase=precioBase;
		this.id = id;
		this.calorias = calorias;
		
	}
	//Metodos
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
		return nombre +  " (Calorias: " + this.calorias  +")| Precio neto base: "+ precioBase + "$" +"| IVA (19%): "+iva+  "$" +"| Precio total: " +precioTotal + "$" +"\n" + new String(new char[120]).replace("\0", "-") + "\n";
	}

}
