package uniandes.dpoo.procesamiento;

public class Ingrediente {
	//Atributos
	private String nombre;
	private int costoAdicional;

	//Método constructor
	public Ingrediente(String nombre, int costoAdicional) {
		
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
	}
	
	//Métodos públicos
	public String getNombre() {
		
		return this.nombre;
	}
	
	public int getCostoAdicional() {
		
		return this.costoAdicional;
	}
	
	
}
