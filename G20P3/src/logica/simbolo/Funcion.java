package logica.simbolo;

import utils.Aleatorio;

/**
 * Clase que se encarga de implementar los metodos necesarios para la gestion de los simbolos de 
 * tipo funcion.
 * 
 * @author Grupo20.
 */
public class Funcion extends Simbolo{

	/**
	 * Constructor por defecto de la clase Funcion.
	 */
	public Funcion(){
		
		_valor = "";
		_aridad = 0;
		_tipo = TipoSimbolo.FUNCION;
	}
	
	/**
	 * Constructor de la clase Funcion.
	 * 
	 * @param ifSeleccionado Indica si el if se puede seleccionar.
	 */
	public Funcion(boolean ifSeleccionado){
		
		// Si el if esta seleccionado seleccionamos hasta el ultimo elemento del array que es el IF
		if(ifSeleccionado)
			_valor = _conjuntoFunciones[Aleatorio.intRandom(_conjuntoFunciones.length)];	
		else
			_valor = _conjuntoFunciones[Aleatorio.intRandom(_conjuntoFunciones.length - 1)];	
		
		calculaAridad(_valor);	
		_tipo = TipoSimbolo.FUNCION;
	}
	
	/**
	 * Constructora por valor de la clase Funcion.
	 * 
	 * @param valor Nuevo valor a establecer.
	 */
	public Funcion(String valor){
	
		calculaAridad(valor);
		_valor = valor;
		_tipo = TipoSimbolo.FUNCION;
	}
	
	/**
	 * Devuelve la aridad asociada a un simbolo.
	 * 
	 * @param simbolo
	 * 
	 * @return la aridad asociada a un simbolo.
	 */
	public void calculaAridad(String simbolo){
			
		if(simbolo.matches("AND") || simbolo.matches("OR"))
			_aridad = 2;
		else
			if(simbolo.matches("NOT"))
				_aridad = 1;
			else
				if(simbolo.matches("IF"))
					_aridad = 3;
	}
	
	@Override
	public String toString() {		
		return _valor;
	}
	
	@Override
	public Funcion clone(){
		
		Funcion n = new Funcion();
		n._aridad = _aridad;
		n._valor = _valor;
		n._tipo = _tipo;
		
		return n;
	}
	
	/**
	 * Obtiene una funcion aleatoria diferente a la funcion.
	 * @return Obtiene una funcion aleatoria diferente a la funcion.
	 */
	public Funcion getFuncionAleatoria() {
		
		if (_valor.matches("OR")) return new Funcion("AND");
		else
			if (_valor.matches("AND")) 
				return new Funcion("OR");
		
		return this;
	}
}
