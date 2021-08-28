package uniandes.dpoo.procesamiento;
import java.util.ArrayList;

public class ProductoAjustado implements Producto{
	//Atributos
	private ProductoMenu base;
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	
	//Metodo constructor
	public ProductoAjustado(ProductoMenu base) {
		this.base=base;
	}

	//Metodos
	public int getPrecio() {
		return base.getPrecio();
	}
	
	public String getNombre() {
		return base.getNombre();
	}
	
	public String generarTextoFactura() {
		return base.generarTextoFactura();
	}

}
