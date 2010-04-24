package cromosoma;

/**
 * Clase que implementa los metodos comunes de un gen.
 * 
 * @author Grupo20.
 */
public abstract class Gen {

	/**
	 * Gen para las clases que implementen esta clase.
	 */
	protected Object _gen;
	
	/**
	 * Modifica el array booleano del gen.
	 * 
	 * @param gen Array de booleanos del gen.
	 */
	public void setGen(Object gen) {
		_gen = gen;
	}

	/**
	 * Obtiene el array del gen.
	 * 
	 * @return El array de booleanos del gen.
	 */
	public Object getGen() {
		return _gen;
	}
	
	// ---------------METODOS ABSTRACTOS----------------//
	/**
	 * Inicializa aleatoriamente gen.
	 */
	public abstract void inicializaGen();
	
	/**
	 * Devuelve una copia del objeto que invoca este metodo.
	 */
	public abstract Object clone();
	
}
