package uniandes.dpoo.modelo;
import uniandes.dpoo.procesamiento.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurante {

	//Atributos
	private Pedido pedidoEnCurso;
	private ArrayList<Pedido> pedidos;
	private ArrayList<Ingrediente> ingredientes;
	private HashMap<String,ProductoMenu> menuBase;
	private ArrayList<Combo> combos;

	//Metodo Constructor
	public Restaurante() {

		this.pedidos = new ArrayList<Pedido>();
		this.ingredientes = new ArrayList<Ingrediente>();
		this.menuBase = new HashMap<String, ProductoMenu>();
		this.combos = new ArrayList<Combo>();
	}


	//Métodos públicos
	public void IniciarPedido(String nombreCliente, String direccionCliente) {

		pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
		pedidos.add(pedidoEnCurso);

	}

	public void cerrarYGuardarPedido() {

		String nombre_factura = "" + pedidoEnCurso.getIdPedido();
		File factura = new File(nombre_factura);

		this.pedidoEnCurso.guardarFactura(factura);
		this.pedidoEnCurso = null;

	}


	public Pedido getPedidoEnCurso() {

		return this.pedidoEnCurso;

	}


	public HashMap<String, ProductoMenu> getMenuBase(){

		return this.menuBase;

	}


	public ArrayList<Combo> getCombos(){

		return this.combos;

	}


	public ArrayList<Ingrediente> getIngredientes(){

		return this.ingredientes;

	}

	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) {

		this.cargarCombos(archivoCombos);
		this.cargarIngredientes(archivoIngredientes);
		this.cargarMenu(archivoMenu);

	}






	//Métodos privados


	private void cargarMenu(File archivoMenu) {




		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(archivoMenu));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String linea = null;
		try {
			linea = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		while (!linea.equals(null)) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			String[] data_menu = linea.split(";");

			String nombre_producto = data_menu[0];
			int valor_producto = Integer.parseInt(data_menu[1]);

			ProductoMenu productoMenu = new ProductoMenu(nombre_producto, valor_producto);
			this.menuBase.put(nombre_producto, productoMenu);

		}




	}






	private void cargarCombos(File archivoCombos) {

		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(archivoCombos));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String linea = null;
		try {
			linea = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		while (!linea.equals(null)) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			String[] data_combo = linea.split(";");
			int size_data_combo = data_combo.length;

			String nombre_combo = data_combo[0];
			double descuento_combo = Double.parseDouble(data_combo[1].replace("","%")) /100;

			Combo combo = new Combo(nombre_combo, descuento_combo);

			//FALTA CARGAR ITEMS AL COMBO
			for (int i = 2; i < (size_data_combo); i++) {

				String combo_item_name = data_combo[i];
				ProductoMenu combo_item = menuBase.get(combo_item_name);

				combo.agregarItemACombo(combo_item);

			}

			this.combos.add(combo);


		}

	}

	private void cargarIngredientes(File archivoIngredientes) {

		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(archivoIngredientes));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String linea = null;
		try {
			linea = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		while (!linea.equals(null)) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			String[] data_ingrediente = linea.split(";");
			int size_data_ingrediente = data_ingrediente.length;

			String nombre_ingrediente = data_ingrediente[0];
			int precio_ingredient = Integer.parseInt(data_ingrediente[1]);

			Ingrediente ingrediente = new Ingrediente(nombre_ingrediente, precio_ingredient);
			this.ingredientes.add(ingrediente);

		}
	}
}




