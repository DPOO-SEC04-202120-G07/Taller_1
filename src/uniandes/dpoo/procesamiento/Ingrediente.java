package uniandes.dpoo.procesamiento;

public class Ingrediente {
	//Atributos
	private String nombre;
	private int costoAdicional;
	private int id;

	//Método constructor
	public Ingrediente(String nombre, int costoAdicional, int id) {
		
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
		this.id=id;
	}
	
	//Métodos públicos
	public String getNombre() {
		
		return this.nombre;
	}
	
	public int getId() {
		
		return this.id;
	}
	
	public int getCostoAdicional() {
		
		return this.costoAdicional;
	}
	
	
}
