package logica.simbolo;

import utils.Aleatorio;

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
		_valor = _conjuntoTerminales[Aleatorio.intRandom(_conjuntoTerminales.length)];	
		_tipo = TipoSimbolo.TERMINAL;
	}
	
	/**
	 * Constructora por valor de la clase Terminal.
	 * 
	 * @param valor Nuevo valor a establecer.
	 */
	public Terminal(String valor){
	
		_aridad = 0;
		_valor = valor;
		_tipo = TipoSimbolo.TERMINAL;
	}
	
	@Override
	public String toString() {		
		return _valor;
	}
	
	@Override
	public Terminal clone(){
		
		Terminal n = new Terminal();
		n._aridad = _aridad;
		n._valor = _valor;
		n._tipo = _tipo;
		
		return n;
	}

	/**
	 * Obtiene un terminal aleatorio diferente al terminal.
	 * @return Obtiene un terminal aleatorio diferente al terminal.
	 */
	public Simbolo getTerminalAleatorio() {
		
		int i;
		
		do {
			i = Aleatorio.intRandom(_conjuntoTerminales.length);
		} while (_conjuntoTerminales[i].equals(_valor));
		
		return new Terminal(_conjuntoTerminales[i]);
	}
}
