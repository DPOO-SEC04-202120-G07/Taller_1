package uniandes.dpoo.procesamiento;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Pedido {
	//Atributos
	private static int numeroPedidos=0;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;

	//Metodo constructor
	public Pedido (String nombreCliente, String direccionCliente) {
		this.nombreCliente=nombreCliente;
		this.direccionCliente=direccionCliente;
		numeroPedidos++;
		this.idPedido=numeroPedidos;	
		itemsPedido= new ArrayList<Producto>();
	}

	//Metodos
	
	public Producto ultimoProducto() {
		return itemsPedido.get(itemsPedido.size()-1);
	}
	
	public void modificarUltimoProducto(Producto modificar) {
		itemsPedido.set(itemsPedido.size()-1, modificar);
	}
	
	public String getNombreCliente() {
		return this.nombreCliente;
	}
	
	public String getDireccionCliente() {
		return this.direccionCliente;
	}
	
	public int getIdPedido() {
		return this.idPedido;
	}

	public void agregarProducto(Producto nuevoItem) {
		itemsPedido.add(nuevoItem);		
	}
	
	public void agregarBebida(Bebida nuevoItem) {
		itemsPedido.add(nuevoItem);		
	}
	

	private int getPrecioNetoPedido() {
		int precioNeto=0;
		for (int i=0; i<itemsPedido.size(); i++) {
			precioNeto+=itemsPedido.get(i).getPrecio();
		}
		return precioNeto;
	}

	private int getPrecioIVAPedido() {
		int precioIVA=0;
		for (int i=0; i<itemsPedido.size(); i++) {
			precioIVA+=itemsPedido.get(i).getPrecio()*0.19;
		}
		return precioIVA;
	}

	private int getPrecioTotalPedido() {
		return getPrecioNetoPedido()+getPrecioIVAPedido();
	}

	public String generarTextoFactura() {
		String textoFactura="";
		for (int i=0; i<itemsPedido.size();i++) {
			textoFactura+=itemsPedido.get(i).generarTextoFactura()+"\n";
		}

		return textoFactura;
	}

	public void guardarFactura(File archivo) {
		FileWriter  out_file=null;

		try {
			out_file = new FileWriter(archivo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			out_file.write("Cliente: "+this.nombreCliente+" --- Direccion: "+this.direccionCliente+"\n"+  new String(new char[120]).replace("\0", "-") + "\n" + new String(new char[120]).replace("\0", "-") + "\n" + this.generarTextoFactura());
			out_file.write(new String(new char[120]).replace("\0", "#")  + "\n" +  "| Precio neto: " + getPrecioNetoPedido() + "$" + "| IVA del pedido: " + getPrecioIVAPedido() +"$" +"| Precio total (con IVA): " + getPrecioTotalPedido() +"$" + "\n" + new String(new char[120]).replace("\0", "#"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out_file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
