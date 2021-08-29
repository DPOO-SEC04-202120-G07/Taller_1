package uniandes.dpoo.procesamiento;

public class ProductoMenu implements Producto{
	//Atributos
	private String nombre;
	private int precioBase;
	private int id;
	
	//Metodo constructor
	public ProductoMenu(String nombre, int precioBase, int id) {
		this.nombre=nombre;
		this.precioBase=precioBase;
		this.id = id;
		
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
	
	public String generarTextoFactura() {
		double iva=precioBase*0.19;
		double precioTotal=precioBase+iva;
		return nombre +"| Precio neto base: "+ precioBase+"| IVA (19%): "+iva+"| Precio total: " +precioTotal ;
	}

}
