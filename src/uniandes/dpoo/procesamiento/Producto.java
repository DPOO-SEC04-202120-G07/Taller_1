package uniandes.dpoo.procesamiento;

public interface Producto {
	
	public int getPrecio();
	public String getNombre();
	public String generarTextoFactura();
	public int getId();

}
