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
			@SuppressWarnings("resource")
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

			System.out.println("ID:" + menuBase.get(i).getId()+ ") " + menuBase.get(i).getNombre()+" --- $"+menuBase.get(i).getPrecio()+"\n");

		}
		
		for (int i=0; i<combos.size(); i++) {

			System.out.println("ID:" + combos.get(i).getId()+ ") " + combos.get(i).getNombre()+" --- $"+combos.get(i).getPrecio()+"\n");

		}
		
	}
	
	public void mostrarIngredientes() {
		ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
		for (int i=0; i<ingredientes.size(); i++) {
			System.out.println("ID:" + ingredientes.get(i).getId()+ ") " + ingredientes.get(i).getNombre()+" --- $"+ingredientes.get(i).getCostoAdicional()+"\n");
		}
	}
	
	public void ejecutarOpcion(int opcionSeleccionada) {
		switch (opcionSeleccionada) {		
		case 1: mostrarMenu();
				break;
		
		case 2: 
			if(restaurante.verificarPedido()) {System.out.println("En este momento ya existe un pedido abierto, cierre el pedido antes de iniciar uno nuevo.");}
			
			else {
				
				@SuppressWarnings("resource")
				Scanner input_2 = new Scanner(System.in);
				System.out.println("Ingrese su nombre: ");
				String nombre = input_2.nextLine();
				System.out.println("Ingrese su direccion: ");
				String direccion = input_2.nextLine();
				
				restaurante.IniciarPedido(nombre, direccion);
				System.out.println("La ID de su pedido es: "+restaurante.getPedidoEnCurso().getIdPedido());	}
			
			break;		

				
		case 3: Scanner input_3 = new Scanner(System.in);
				System.out.println("Ingrese el ID del producto/combo que desea ordenar: ");
				int producto_pedido_id = input_3.nextInt();			
				String producto_agregado = restaurante.agregarProductoAlPedido(producto_pedido_id);
				if (producto_agregado.equals("N/A")) { System.out.println("Ingrese un ID valido.");}
				else if (producto_agregado.equals("N/P")) {System.out.println("No se encuentra un pedido activo. !Recuerde iniciar un nuevo pedido!");}
				else {System.out.println("Has agregado el producto: " + producto_agregado);
				while (true && !restaurante.ultimoProductoType().equals("uniandes.dpoo.procesamiento.Combo")) {
					System.out.println("Desea modificar el producto?");
					System.out.println("1. Eliminar ingredientes.");
					System.out.println("2. Agregar ingredientes.");
					System.out.println("3. No modificar.");
					int modificacion = input_3.nextInt();
					if (modificacion==1) {
						mostrarIngredientes();
						System.out.println("Ingrese el ID del ingrediente que quiere eliminar: ");
						int eliminar = input_3.nextInt();
						String nombreActual=restaurante.eliminarIngrediente(producto_pedido_id, eliminar);
						if (nombreActual == "N/A") System.out.println("Ingrese una opcion valida.");
						else System.out.println("Tu producto actualmente es: "+nombreActual);
					}else if (modificacion==2) {
						mostrarIngredientes();
						System.out.println("Ingrese el ID del ingrediente que quiere agregar: ");
						int agregar = input_3.nextInt();
						String nombreActual=restaurante.agregarIngrediente(producto_pedido_id, agregar);
						if (nombreActual == "N/A") System.out.println("Ingrese una opcion valida.");
						else System.out.println("Tu producto actualmente es: "+nombreActual);
					}else if (modificacion == 3) {
						break;
					}else {
						System.out.println("Ingrese una opcion valida.");
					}
				}}
				break;
				
		case 4: int id_factura = restaurante.getPedidoEnCurso().getIdPedido();
				System.out.println("Felicitaciones, ha completado su orden. Puede buscar su factura con el ID único de pedido #"+ id_factura + ".");
				restaurante.cerrarYGuardarPedido();
				break;
				
		case 5: Scanner input_4 = new Scanner (System.in);
				System.out.println("Ingrese el ID del pedido que quiere consultar: ");
				int pedido_id = input_4.nextInt();
				String pedido_info=restaurante.informacionPedido(pedido_id);
				if (pedido_info.equals("N/A")) System.out.println("No se ha encontrado dicho pedido, ingrese una ID valida.");
				else System.out.println(pedido_info);
				break;
				
		default: System.out.println("Ingrese una opcion valida.");
		}
		
	}
}