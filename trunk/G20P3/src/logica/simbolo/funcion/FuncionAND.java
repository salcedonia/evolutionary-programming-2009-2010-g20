package logica.simbolo.funcion;

import logica.Arbol;

/**
 * Clase que se encarga de la gestion del simbolo de tipo funcion AND.
 * 
 * @author Grupo20.
 */
public class FuncionAND extends Funcion{

	/**
	 * Constructora por defecto de la clase FuncionAND.
	 */
	public FuncionAND(){
		
		_aridad = 2;
		_valor = "AND";
	}
	
	@Override
	public boolean evalua(Arbol nodo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
