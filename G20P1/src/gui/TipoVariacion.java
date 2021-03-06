package gui;

/**
 * Enumerado que indica la variación sobre la que se va a realizar el algoritmo.
 * 
 * @author Grupo20.
 */
public enum TipoVariacion {
	
	/**
	 * Ninguna
	 */
	NINGUNA, 
	/**
	 * Sobre el Numero de generaciones.
	 */
	NUM_GENERACION, 
	/**
	 * Sobre el Numero de la Poblacion.
	 */
	NUM_POBLACION, 
	/**
	 * Sobre la Probabilidad de Cruce.
	 */
	PROB_CRUCE, 
	/**
	 * Sobre la Probabilidad de Mutacion.
	 */
	PROB_MUTACION,
	/**
	 * Sobre la Precision.
	 */
	PRECISION, 
	/**
	 * Sobre el valor del parametro N.
	 */
	VALOR_N, 
	/**
	 * Sobre el elitismo.
	 */
	ELITISMO
}
