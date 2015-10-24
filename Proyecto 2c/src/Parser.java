import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Parser {
	//Atributos de la clase
	private Document htmlFile = null;
	private ArrayList<String> contenidos;
	
	//Atributos empleados para realizar la escritura de datos obtenidos en un fichero de salida
	private FileWriter fw;
	private BufferedWriter bfw;
	
	public Parser() {
		// TODO Auto-generated constructor stub
		//Constructor de la clase
		//Declaración de atributos
		try {
			fw = new FileWriter(new File("Info_PcComponentes.txt"));
			bfw = new BufferedWriter(fw);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Método el cual permite extraer información de un HTML (recibe la URL de la web a parsear) */
	public void parsearHTML(String URL){
		//Encapsulado de errores try/catch
		try {
			//Conectamos con la página web recibida por parámetro y la almacenamos en un objeto Document
			htmlFile = Jsoup.connect(URL).get();
			//Recogemos el titulo
			String titulo = htmlFile.title();
			//Imprimimos el titulo
			System.out.println("Titulo: "+titulo+"\n");
			//Llamada al método "mostrarResultados()"
			mostrarResultados();
				
		}
		catch (IOException errorConexion) {
			// TODO Auto-generated catch block
			errorConexion.printStackTrace();
			System.err.println("No se pudo conectar a la página web deseada.");
		}
	}
	/* Método cuya función es imprimir por pantalla los elementos recogidos del HTML especificado*/
	public void mostrarResultados(){
		//Impreso por pantalla
		System.out.println("OFERTAS DESTACADAS DE LA SEMANA");
		
		//Bucle de recorrido (for) para recorrer todos y cada uno de los elementos con la clase "nombre"
		for(int a=0; a<htmlFile.body().getElementsByClass("nombre").size(); a++){
			//En cada iteración se recoge y se muestra tanto el nombre como el precio de cada producto.
			//Se realiza impreso por pantalla de ello
			String nombreElemento = htmlFile.body().getElementsByClass("nombre").get(a).text();
			String precioElemento = htmlFile.body().getElementsByClass("preciosProductos").get(a).text();
			System.out.println("Contenido: "+nombreElemento+" - Precio: "+precioElemento);
		}
		
		//PD: "htmlFile.body().getElementsByClass("nombre").get(a).text()" : RECOGE LOS ELEMENTOS CON LA CLASE "nombre"
		//PD: "htmlFile.body().getElementsByClass("preciosProductos").get(a).text()" : RECOGE ELEMENTOS CON LA CLASE "preciosProductos"
	}
	
	/* Método cuya función es realizar la escritura del HTML en un fichero de salida*/
	public void escrituraFileSalida(){
		//Escribimos sobre el fichero salida
		try {
			bfw.write(htmlFile.title()+"\n\n");
			bfw.write("OFERTAS DESTACADAS DE LA SEMANA"+"\n");
			
			for(int a=0; a<htmlFile.body().getElementsByClass("nombre").size(); a++){
				//""
				String nombreElemento = htmlFile.body().getElementsByClass("nombre").get(a).text();
				String precioElemento = htmlFile.body().getElementsByClass("preciosProductos").get(a).text();
				bfw.write("Contenido: "+nombreElemento+" - Precio: "+precioElemento+"\n");
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				//Cerrado del Buffer de escritura
				bfw.close();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
