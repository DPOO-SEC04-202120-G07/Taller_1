package uniandes.dpoo.consola;
import uniandes.dpoo.modelo.*;
import uniandes.dpoo.procesamiento.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsolaHamburguesas {
	//Atributos
	private Restaurante restaurante;
	
	//Metodo constructor
	
	public ConsolaHamburguesas() {
		restaurante = new Restaurante();
	}
	
	//Metodo main
	public static void main(String[] args) {
		System.out.println("BIENVENIDO A LA HAMBURGUESERIA 'Hola Mundo'.");
		ConsolaHamburguesas interfaz = new ConsolaHamburguesas();
		interfaz.restaurante.cargarInformacionRestaurante(new File("data/ingredientes.txt"),new File("data/menu.txt"), new File("data/combos.txt"));
		while (true) {
			interfaz.mostrarOpciones();
			Scanner input = new Scanner(System.in);
			int opcionSeleccionada = input.nextInt();
			interfaz.ejecutarOpcion(opcionSeleccionada);
		}
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
		ArrayList<ProductoMenu> menuBase = restaurante.getMenuBase();
		ArrayList<Combo> combos = restaurante.getCombos();
		
		for (int i=0; i<menuBase.size(); i++) {
			System.out.println(menuBase.get(i).getNombre()+" --- $"+menuBase.get(i).getPrecio()+"\n");
		}
		
		for (int i=0; i<combos.size(); i++) {
			System.out.println(combos.get(i).getNombre()+" --- $"+combos.get(i).getPrecio()+"\n");
		}
		
	}
	
	public void ejecutarOpcion(int opcionSeleccionada) {
		switch (opcionSeleccionada) {		
		case 1: mostrarMenu();
		case 2: ;
		case 3: ;
		case 4: ;
		case 5: ;
		default: System.out.println("Ingrese una opcion valida.");
		}
		
	}
}
