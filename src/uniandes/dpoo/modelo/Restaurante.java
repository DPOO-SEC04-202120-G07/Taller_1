package uniandes.dpoo.modelo;
import uniandes.dpoo.procesamiento.*;

import java.io.File;
import java.util.ArrayList;

public class Restaurante {
	
	//Atributos
	private Pedido pedidoEnCurso;
	private ArrayList<Pedido> pedidos;
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<ProductoMenu> menuBase;
	private ArrayList<Combo> combos;
	
	//Metodo Constructor
	public Restaurante() {
		
		this.pedidos = new ArrayList<Pedido>();
		this.ingredientes = new ArrayList<Ingrediente>();
		this.menuBase = new ArrayList<ProductoMenu>();
		this.combos = new ArrayList<Combo>();
	}
	
	
	//Métodos públicos
	public void IniciarPedido(String nombreCliente, String direccionCliente) {
		
		pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
		
	}
	
	public void cerrarYGuardarPedido() {
		
	}
	
	
	public Pedido getPedidoEnCurso() {
		
	}
	
	
	public ArrayList<Producto> getMenuBase(){
		
	}
	
	public ArrayList<Ingrediente> getIngredientes(){
		
	}
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) {
		
	}
	
	
	
	
	
	
	//Métodos privados
	private void cargarCombos(File archivoCombos) {
		
		
	}
	
	private void cargarIngredientes(File archivoIngredientes) {
		
	}
	
	private void cargarMenu(File archivoMenu) {
		
	}
	

}
