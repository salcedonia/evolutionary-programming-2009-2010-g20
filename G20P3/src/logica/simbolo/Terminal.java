package logica.simbolo;

/**
 * Clase que implementa los metodos necesarios para los simbolos de tipo terminal.
 * 
 * @author Grupo20.
 */
public class Terminal extends Simbolo{

	/**
	 * Constructor por defecto de la clase Terminal.
	 */
	public Terminal(){
		
		_aridad = 0;
		_valor = "";
	}
	
	/**
	 * Constructora por valor de la clase Terminal.
	 * 
	 * @param valor Nuevo valor a establecer.
	 */
	public Terminal(String valor){
	
		_aridad = 0;
		_valor = valor;
	}
	
	@Override
	public String toString() {
		
		return _valor;
	}
}
