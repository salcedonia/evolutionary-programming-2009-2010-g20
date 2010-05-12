package logica.simbolo.funcion;

import logica.Arbol;

/**
 * Clase que se encarga de la gestion del simbolo de tipo funcion IF.
 * 
 * @author Grupo20.
 */
public class FuncionIF extends Funcion{

	/**
	 * Constructora por defecto de la clase FuncionIF.
	 */
	public FuncionIF(){
		
		_aridad = 3;
		_valor = "IF";
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
