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
	/**
	 * Tipo del simbolo.
	 */
	protected TipoSimbolo _tipo;
	/**
	 * Valor de prueba del simbolo con los casos de prueba.
	 */
	protected boolean _valorPrueba;
	
	/**
	 * Funciones disponibles.
	 */
	protected static final String[] _conjuntoFunciones = {"AND","OR","NOT","IF"};
	/**
	 * Terminales disponibles.
	 */
	protected static final String[] _conjuntoTerminales = {"A1","A0","D3","D2","D1","D0"};
	
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
	
	/**
	 * Devuelve el tipo de un simbolo.
	 * 
	 * @return El tipo de un simbolo.
	 */
	public TipoSimbolo getTipo() {
		return _tipo;
	}

	/**
	 * Establece el tipo de un simbolo a valor tipo.
	 * 
	 * @param valor Nuevo valor a establecer.
	 */
	public void setTipo(TipoSimbolo tipo) {
		_tipo = tipo;
	}
	
	public boolean getValorPrueba() {
		return _valorPrueba;
	}

	public void setValorPrueba(boolean prueba) {
		_valorPrueba = prueba;
	}
	
	/**
	 * Devuelve la posicion del terminal en el array de Terminales.
	 * 
	 * @return La posicion del terminal en el array de Terminales.
	 */
	public int devuelvePosTerminal(){
		
		for(int i=0 ; i < _conjuntoTerminales.length; i++){
			if(_conjuntoTerminales[i].matches(_valor))
				return i;
		}
		return -1;
	}
	
	@Override
	public abstract Simbolo clone();
}
