package uniandes.dpoo.procesamiento;

public interface Producto {
	
	public int getPrecio();
	public String getNombre();
	public int getCalorias();
	public String generarTextoFactura();
	public int getId();

}
