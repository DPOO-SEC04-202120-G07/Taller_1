package uniandes.dpoo.procesamiento;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
	public int getIdPedido() {
		return this.idPedido;
	}

	public void agregarProducto(Producto nuevoItem) {
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

	private String generarTextoFactura() {
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
			out_file.write(this.generarTextoFactura());
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
