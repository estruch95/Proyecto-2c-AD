
public class Main {
	//Atributos de la clase
	private static Parser parser;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Ejecución en bucle inicializador "main"
		//Creación de un nuevo objeto Parser
		parser = new Parser();
		//Llamada al método "parsearHTML()" del mismo (recibe por parámetro la URL de la web a parsear)
		parser.parsearHTML("http://www.pcComponentes.com/");
		//Llamada al método "escrituraFileSalida()" del mismo
		parser.escrituraFileSalida();
	}

}
