package uniandes.dpoo.procesamiento;

public class Ingrediente {
	//Atributos
	private String nombre;
	private int costoAdicional;
	private int id;
	private int calorias;

	//Método constructor
	public Ingrediente(String nombre, int costoAdicional, int id, int calorias) {
		
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
		this.id=id;
		this.calorias = calorias;
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
	
	public int getCalorias() {
		return this.calorias;
	}
	
	
}
