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
	private int id_asignado;
	private int id_asignadoIngrediente;
	private int id_asignadoBebida;
	
	private Pedido pedidoEnCurso = null;
	
	private ArrayList<Pedido> pedidos;
	private ArrayList<Combo> combos;
	
	private HashMap<String,Ingrediente> ingredientes;
	private HashMap<String,Bebida> bebidas;
	private HashMap<String,Producto> menuBase;
	private HashMap<Integer,Producto> productosId;
	private HashMap<Integer, Ingrediente> productosIdIngrediente;
	private HashMap<Integer, Bebida> productosIdBebida;
	
	

	//Metodo Constructor
	public Restaurante() {

		this.pedidos = new ArrayList<Pedido>();
		this.ingredientes = new HashMap<String,Ingrediente>();
		this.menuBase = new HashMap<String, Producto>();
		this.combos = new ArrayList<Combo>();
		this.bebidas = new HashMap<String, Bebida>();
		this.productosId = new HashMap<Integer,Producto>();
		this.productosIdIngrediente= new HashMap<Integer, Ingrediente>();
		this.productosIdBebida= new HashMap<Integer, Bebida>();
		this.id_asignado=0;
		this.id_asignadoIngrediente=0;
		
	}


	//Métodos públicos
	public void IniciarPedido(String nombreCliente, String direccionCliente) {
		
		pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
		pedidos.add(pedidoEnCurso);

	}
	
	public boolean verificarPedido() {
		
		boolean pedido_abierto = true;
		
		if (this.pedidoEnCurso == null) {
			pedido_abierto = false;
		}
		
		return pedido_abierto;
	}

	public void cerrarYGuardarPedido() {

		String nombre_factura = "" + pedidoEnCurso.getIdPedido() + ".txt";
		File factura = new File(nombre_factura);
		this.pedidoEnCurso.guardarFactura(factura);
		
	}
	
	


	public Pedido getPedidoEnCurso() {

		return this.pedidoEnCurso;

	}


	public ArrayList<Producto> getMenuBase(){

		return new ArrayList<Producto>(this.menuBase.values());

	}
	

	public ArrayList<Producto> getBebidas(){

		return new ArrayList<Producto>(this.bebidas.values());

	}
	
	
	public ArrayList<Ingrediente> getIngredientes(){

		return new ArrayList<Ingrediente>(this.ingredientes.values());

	}


	public ArrayList<Combo> getCombos(){

		return this.combos;

	}



	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos, File archivoBebidas) {

		this.cargarIngredientes(archivoIngredientes);
		this.cargarMenu(archivoMenu);
		this.cargarBebidas(archivoBebidas);
		this.cargarCombos(archivoCombos);

	}
	

	public String informacionPedido(int id_pedido) {
		String info="N/A";
		for (int i=0; i<this.pedidos.size(); i++) {
			if (pedidos.get(i).getIdPedido()==id_pedido) {
				info= pedidos.get(i).generarTextoFactura();
				break;
			}
		}
			
		return info;
	}


	public String agregarProductoAlPedido(int id_producto) {
		Producto producto_agregado = this.productosId.get(id_producto);
		
		//Excepción producto no existente (id inválido)
		if (producto_agregado == null) {return ("N/A");}
		
		//Excepción pedido no iniciado.
		if (this.pedidoEnCurso == null) {return ("N/P");}
		
		this.pedidoEnCurso.agregarProducto(producto_agregado);
		String nombre_producto = producto_agregado.getNombre();
		
		return nombre_producto;
	}
	
	
	
	public String agregarBebidaAlPedido(int id_bebida) {
		Bebida bebida_agregada = this.productosIdBebida.get(id_bebida);
		
		//Excepción producto no existente (id inválido)
		if (bebida_agregada == null) {return ("N/A");}
		
		//Excepción pedido no iniciado.
		if (this.pedidoEnCurso == null) {return ("N/P");}
		
		this.pedidoEnCurso.agregarBebida(bebida_agregada);
		String nombre_bebida = bebida_agregada.getNombre();
		
		return nombre_bebida;
	}
	
	
	
	public String eliminarIngrediente(int producto_id, int eliminar) {
		ProductoAjustado nuevoProducto;
		if (this.pedidoEnCurso.ultimoProducto().getClass().getName().equals("uniandes.dpoo.procesamiento.ProductoAjustado")) {
			nuevoProducto = (ProductoAjustado)this.pedidoEnCurso.ultimoProducto();
		}else {
			nuevoProducto = new ProductoAjustado((ProductoMenu)this.pedidoEnCurso.ultimoProducto());
		}
		if (this.productosIdIngrediente.get(eliminar)==null) return "N/A";
		nuevoProducto.eliminarIngrediente(this.productosIdIngrediente.get(eliminar));
		this.pedidoEnCurso.modificarUltimoProducto(nuevoProducto);
		return nuevoProducto.getNombre();
	}
	
	public String ultimoProductoType() {
		return this.pedidoEnCurso.ultimoProducto().getClass().getName();
	}
	public String agregarIngrediente(int producto_id, int agregar) {
		ProductoAjustado NuevoProducto;
		if (this.pedidoEnCurso.ultimoProducto().getClass().getName().equals("uniandes.dpoo.procesamiento.ProductoAjustado")) {
			NuevoProducto = (ProductoAjustado)this.pedidoEnCurso.ultimoProducto();
		}else {
			NuevoProducto = new ProductoAjustado((ProductoMenu)this.pedidoEnCurso.ultimoProducto());
		}
		if (this.productosIdIngrediente.get(agregar)==null) return "N/A";
		NuevoProducto.agregarIngrediente(this.productosIdIngrediente.get(agregar));
		this.pedidoEnCurso.modificarUltimoProducto(NuevoProducto);
		return NuevoProducto.getNombre();
	}
	
	public String verificarPedidoRepetido() {

		ArrayList<Integer> pedidos_repetidos = new ArrayList<Integer>();
		String mensaje_repetidos = null;

		for (int i = 0; i < this.pedidos.size(); i++)
		{
			Pedido iteracion_pedido = pedidos.get(i);
			
			int id_pedido_actual = this.pedidoEnCurso.getIdPedido();
			int id_iteracion_pedido = iteracion_pedido.getIdPedido();
			
			ArrayList<Producto> items_pedido_actual = this.pedidoEnCurso.getItemsPedido();
			ArrayList<Producto> items_iteracion_pedido = iteracion_pedido.getItemsPedido();

			if (items_pedido_actual.containsAll(items_iteracion_pedido) && (id_pedido_actual != id_iteracion_pedido)) {

				int id_pedido_repetido = iteracion_pedido.getIdPedido();
				pedidos_repetidos.add(id_pedido_repetido);
			}
		}
		
		if (pedidos_repetidos.size() != 0) {
			
			mensaje_repetidos = "Este pedido idéntico ya ha sido ordenado anteriormente bajo el/los ID: ";
		
			for (int i: pedidos_repetidos) {
				mensaje_repetidos += "" + i + " "; 
			}
		
		}
		
		this.pedidoEnCurso = null;

		return mensaje_repetidos;
	}

	
	public int asignarId() {
		this.id_asignado += 1;
		return this.id_asignado;
		
	}
	
	public int asignarIdIngrediente() {
		this.id_asignadoIngrediente += 1;
		return this.id_asignadoIngrediente;
		
	}

	public int asignarIdBebida() {
		this.id_asignadoBebida += 1;
		return this.id_asignadoBebida;
		
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
			int calorias_producto = Integer.parseInt(data_menu[2]);

			Producto producto = new ProductoMenu(nombre_producto, valor_producto, asignarId(), calorias_producto);
			this.menuBase.put(nombre_producto, producto);
			this.productosId.put(this.id_asignado, producto);
			
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

			for (int i = 2; i < (size_data_combo); i++) {

				String combo_item_name = data_combo[i];
				Producto combo_item = menuBase.get(combo_item_name);
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

			String nombre_ingrediente = data_ingrediente[0];
			int precio_ingredient = Integer.parseInt(data_ingrediente[1]);
			int calorias_ingrediente = Integer.parseInt(data_ingrediente[2]);

			Ingrediente ingrediente = new Ingrediente(nombre_ingrediente, precio_ingredient, asignarIdIngrediente(), calorias_ingrediente);
			this.ingredientes.put(nombre_ingrediente, ingrediente);
			this.productosIdIngrediente.put(this.id_asignadoIngrediente, ingrediente);
			try {
				linea=br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	private void cargarBebidas(File archivoBebidas) {

		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(archivoBebidas));
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
			String[] data_bebida = linea.split(";");

			String nombre_bebida = data_bebida[0];
			int precio_bebida = Integer.parseInt(data_bebida[1]);
			int calorias_bebida = Integer.parseInt(data_bebida[2]);

			Bebida bebida = new Bebida(nombre_bebida, precio_bebida, asignarIdBebida(), calorias_bebida);
			this.bebidas.put(nombre_bebida, bebida);
			this.productosIdBebida.put(this.id_asignadoBebida, bebida);
			try {
				linea=br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	
	
}




