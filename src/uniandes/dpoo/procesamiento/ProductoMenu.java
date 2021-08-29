package uniandes.dpoo.procesamiento;

public class ProductoMenu implements Producto{
	//Atributos
	private String nombre;
	private int precioBase;
	
	//Metodo constructor
	public ProductoMenu(String nombre, int precioBase) {
		this.nombre=nombre;
		this.precioBase=precioBase;
		
	}
	//Metodos
	public int getPrecio() {
		return this.precioBase;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String generarTextoFactura() {
		double iva=precioBase*0.19;
		double precioTotal=precioBase+iva;
		return nombre +"| Precio base: "+ precioBase+"| IVA (19%): "+iva+"| Precio total: " +precioTotal ;
	}

}
