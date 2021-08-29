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
			if (opcionSeleccionada==6) break;
			interfaz.ejecutarOpcion(opcionSeleccionada);
		}
	}

	//Metodos
	public void mostrarOpciones() {
		System.out.println("\n1. Mostrar el menú.");
		System.out.println("2. Iniciar un nuevo pedido.");
		System.out.println("3. Agregar un elemento a un pedido.");
		System.out.println("4. Cerrar un pedido y guardar la factura.");
		System.out.println("5. Consultar informacion de un pedido.");
		System.out.println("6. Salir.");
		System.out.println("Seleccione una de las anteriores opciones: ");
		
	}
	
	public void mostrarMenu() {
		ArrayList<ProductoMenu> menuBase = restaurante.getMenuBase();
		ArrayList<Combo> combos = restaurante.getCombos();
		
		for (int i=0; i<menuBase.size(); i++) {

			System.out.println("Id:" + menuBase.get(i).getId()+ ") " + menuBase.get(i).getNombre()+" --- $"+menuBase.get(i).getPrecio()+"\n");

		}
		
		for (int i=0; i<combos.size(); i++) {

			System.out.println("Id:" + combos.get(i).getId()+ ") " + combos.get(i).getNombre()+" --- $"+combos.get(i).getPrecio()+"\n");

		}
		
	}
	
	public void ejecutarOpcion(int opcionSeleccionada) {
		switch (opcionSeleccionada) {		
		case 1: mostrarMenu();
				break;
		
		case 2: Scanner input_2 = new Scanner(System.in);
				System.out.println("Ingrese su nombre: ");
				String nombre = input_2.nextLine();
				System.out.println("Ingrese su direccion: ");
				String direccion = input_2.nextLine();
				restaurante.IniciarPedido(nombre, direccion);
				System.out.println("La ID de su pedido es: "+restaurante.getPedidoEnCurso().getIdPedido());
				break;
				
		case 3: Scanner input_3 = new Scanner(System.in);
				System.out.println("Ingrese el ID del producto/combo que desea ordenar: ");
				int producto_pedido_id = input_3.nextInt();
				String producto_agregado = restaurante.agregarProductoAlPedido(producto_pedido_id);
				if (producto_agregado.equals("N/A")) System.out.println("Ingrese un ID valido.");
				else System.out.println("Has agregado el producto: " + producto_agregado);
				break;
				
		case 4: int id_factura = restaurante.getPedidoEnCurso().getIdPedido();
				System.out.println("Felicitaciones, ha completado su orden. Puede buscar su factura con el ID único de pedido #"+ id_factura + ".");
				restaurante.cerrarYGuardarPedido();
				break;
				
		case 5: ;
		default: System.out.println("Ingrese una opcion valida.");
		}
		
	}
}