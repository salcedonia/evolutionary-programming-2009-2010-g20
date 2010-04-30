package cromosoma;

/**
 * Clase que implementa los metodos comunes de un gen.
 * 
 * @author Grupo20.
 */
public abstract class Gen {

	// ---------------METODOS ABSTRACTOS----------------//
	
	/**
	 * Modifica el array booleano del gen.
	 * 
	 * @param gen Array de booleanos del gen.
	 */
	public abstract void setGen(Object gen);

	/**
	 * Obtiene el array del gen.
	 * 
	 * @return El array de booleanos del gen.
	 */
	public abstract Object getGen();
	
	/**
	 * Inicializa aleatoriamente gen.
	 */
	public abstract void inicializaGen();
	
	/**
	 * Devuelve una copia del objeto que invoca este metodo.
	 */
	public abstract Object clone();
	
}
