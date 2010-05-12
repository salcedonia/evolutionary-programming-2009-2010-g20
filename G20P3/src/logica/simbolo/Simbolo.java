package logica.simbolo;

/**
 * Clase que implementa los metodos necesarios para la gestion de los simbolos del arbol de los
 * individuos.
 * 
 * @author Grupo20.
 */
public abstract class Simbolo {

	/**
	 * Aridad del simbolo.
	 */
	protected int _aridad;
	/**
	 * Valor del simbolo.
	 */
	protected String _valor;
	
	@Override
	public abstract String toString();

	/**
	 * Devuelve la aridad de un simbolo.
	 * 
	 * @return La aridad de un simbolo.
	 */
	public int getAridad() {
		return _aridad;
	}

	/**
	 * Establece la aridad de un simbolo a valor aridad.
	 * 
	 * @param aridad Nuevo valor a establecer.
	 */
	public void setAridad(int aridad) {
		_aridad = aridad;
	}

	/**
	 * Devuelve el valor de un simbolo.
	 * 
	 * @return El valor de un simbolo.
	 */
	public String getValor() {
		return _valor;
	}

	/**
	 * Establece el valor de un simbolo a valor valor.
	 * 
	 * @param valor Nuevo valor a establecer.
	 */
	public void setValor(String valor) {
		_valor = valor;
	}
}
