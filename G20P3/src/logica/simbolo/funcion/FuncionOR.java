package logica.simbolo.funcion;

import logica.Arbol;

/**
 * Clase que se encarga de la gestion del simbolo de tipo funcion OR.
 * 
 * @author Grupo20.
 */
public class FuncionOR extends Funcion {

	/**
	 * Constructora por defecto de la clase FuncionOR.
	 */
	public FuncionOR(){
		
		_aridad = 2;
		_valor = "OR";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean evalua(Arbol nodo) {
		// TODO Auto-generated method stub
		return false;
	}
}
