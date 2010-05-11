package logica.simbolo.funcion;

import logica.Arbol;
import logica.simbolo.Simbolo;

public abstract class Funcion extends Simbolo{

	@Override
	public abstract String toString();

	public abstract boolean evalua(Arbol nodo);
}
