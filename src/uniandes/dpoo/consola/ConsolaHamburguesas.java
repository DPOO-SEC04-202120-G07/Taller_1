package uniandes.dpoo.consola;
import uniandes.dpoo.modelo.*;
import java.util.Scanner;

public class ConsolaHamburguesas {
	//Atributos
	private Restaurante restaurante; 
	
	//Metodo main
	public static void main(String[] args) {
		System.out.println("BIENVENIDO A LA HAMBURGUESERIA.");
		ConsolaHamburguesas interfaz = new ConsolaHamburguesas();
		interfaz.mostrarOpciones();
		Scanner input = new Scanner(System.in);
		int opcionSeleccionada = input.nextInt();
		interfaz.ejecutarOpcion(opcionSeleccionada);

	}

	//Metodos
	public void mostrarOpciones() {
		System.out.println("1. Mostrar el menú.");
		System.out.println("2. Iniciar un nuevo pedido.");
		System.out.println("3. Agregar un elemento a un pedido.");
		System.out.println("4. Cerrar un pedido y guardar la factura.");
		System.out.println("5. Consultar informacion de un pedido.");
		System.out.println("Seleccione una de las anteriores opciones: ");
		
	}
	
	public void mostrarMenu() {
		
	}
	
	public void ejecutarOpcion(int opcionSeleccionada) {
		
	}
}
