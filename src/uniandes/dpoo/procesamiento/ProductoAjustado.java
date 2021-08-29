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
		agregados = new ArrayList<Ingrediente>();
		eliminados = new ArrayList<Ingrediente>();
	}

	//Metodos
	public int getPrecio() {
		int precio=base.getPrecio();
		for (int i=0; i<agregados.size();i++) {
			precio+=agregados.get(i).getCostoAdicional();
		}
		return precio;
	}
	
	public String getNombre() {
		String nombre=base.getNombre();
		for (int i=0; i<agregados.size();i++) {
			nombre+=" con "+agregados.get(i).getNombre();
		}
		for (int i=0; i<eliminados.size();i++) {
			nombre+=" sin "+eliminados.get(i).getNombre();
		}
		return nombre;
	}
	
	public String generarTextoFactura() {
		double iva=this.getPrecio()*0.19;
		double precioTotal=this.getPrecio()+iva;
		return this.getNombre() +"| Precio neto base: "+ this.getPrecio()+"| IVA (19%): "+iva+"| Precio total: " +precioTotal ;
	}

}
