package logica.simbolo.funcion;

import logica.Arbol;
import logica.simbolo.Simbolo;

/**
 * Clase que se encarga de implementar los metodos necesarios para la gestion de los simbolos de 
 * tipo funcion.
 * 
 * @author Grupo20.
 */
public abstract class Funcion extends Simbolo{

	@Override
	public abstract String toString();

	public abstract boolean evalua(Arbol nodo);
}
