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
	
	private HashMap<Integer,Producto> productosId;
	
	private int id_asignado = 0;

	//Metodo Constructor
	public Restaurante() {

		this.pedidos = new ArrayList<Pedido>();
		this.ingredientes = new ArrayList<Ingrediente>();
		this.menuBase = new HashMap<String, ProductoMenu>();
		this.combos = new ArrayList<Combo>();
		this.productosId = new HashMap<Integer,Producto>();
		
	}


	//Métodos públicos
	public void IniciarPedido(String nombreCliente, String direccionCliente) {

		pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
		pedidos.add(pedidoEnCurso);

	}

	public void cerrarYGuardarPedido() {

		String nombre_factura = "" + pedidoEnCurso.getIdPedido() + ".txt";
		File factura = new File(nombre_factura);

		this.pedidoEnCurso.guardarFactura(factura);
		this.pedidoEnCurso = null;

	}


	public Pedido getPedidoEnCurso() {

		return this.pedidoEnCurso;

	}


	public ArrayList<ProductoMenu> getMenuBase(){

		return new ArrayList<ProductoMenu>(this.menuBase.values());

	}


	public ArrayList<Combo> getCombos(){

		return this.combos;

	}


	public ArrayList<Ingrediente> getIngredientes(){

		return this.ingredientes;

	}

	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) {

		this.cargarIngredientes(archivoIngredientes);
		this.cargarMenu(archivoMenu);
		this.cargarCombos(archivoCombos);

	}






	//Métodos privados


	private void cargarMenu(File archivoMenu) {




		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(archivoMenu));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String linea = null;
		try {
			linea = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			String[] data_menu = linea.split(";");

			String nombre_producto = data_menu[0];
			int valor_producto = Integer.parseInt(data_menu[1]);

			ProductoMenu productoMenu = new ProductoMenu(nombre_producto, valor_producto, asignarId());
			this.menuBase.put(nombre_producto, productoMenu);
			this.productosId.put(this.id_asignado, productoMenu);
			
			try {
				linea=br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}


		}




	}






	private void cargarCombos(File archivoCombos) {

		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(archivoCombos));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String linea = null;
		try {
			linea = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			String[] data_combo = linea.split(";");
			int size_data_combo = data_combo.length;

			String nombre_combo = data_combo[0];
			double descuento_combo = Double.parseDouble(data_combo[1].replace("%","")) /100;

			Combo combo = new Combo(nombre_combo, descuento_combo, asignarId());

			//FALTA CARGAR ITEMS AL COMBO
			for (int i = 2; i < (size_data_combo); i++) {

				String combo_item_name = data_combo[i];
				ProductoMenu combo_item = menuBase.get(combo_item_name);

				combo.agregarItemACombo(combo_item);

			}

			this.combos.add(combo);
			this.productosId.put(this.id_asignado, combo);
			try {
				linea=br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}



		}

	}

	private void cargarIngredientes(File archivoIngredientes) {

		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(archivoIngredientes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String linea = null;
		try {
			linea = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			String[] data_ingrediente = linea.split(";");
			int size_data_ingrediente = data_ingrediente.length;

			String nombre_ingrediente = data_ingrediente[0];
			int precio_ingredient = Integer.parseInt(data_ingrediente[1]);

			Ingrediente ingrediente = new Ingrediente(nombre_ingrediente, precio_ingredient);
			this.ingredientes.add(ingrediente);
			try {
				linea=br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	public String agregarProductoAlPedido(int id_producto) {
		
		
		Producto producto_agregado = this.productosId.get(id_producto);
		if (producto_agregado == null) return ("N/A");
		this.pedidoEnCurso.agregarProducto(producto_agregado);
		String nombre_producto = producto_agregado.getNombre();
		
		return nombre_producto;
	}
	
	public int asignarId() {
		
		this.id_asignado += 1;
		return this.id_asignado;
		
	}
}




