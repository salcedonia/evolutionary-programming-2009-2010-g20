package logica.simbolo.funcion;

import logica.Arbol;

/**
 * Clase que se encarga de la gestion del simbolo de tipo funcion NOT.
 * 
 * @author Grupo20.
 */
public class FuncionNOT  extends Funcion{

	/**
	 * Constructora por defecto de la clase FuncionNOT.
	 */
	public FuncionNOT(){
		
		_aridad = 2;
		_valor = "NOT";
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
