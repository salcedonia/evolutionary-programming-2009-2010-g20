package cromosoma;

import utils.Matematicas;

/**
 * Clase que implementa la clase cromosoma para la función 1 a evaluar.
 * 
 * @author Grupo20.
 */
public class CromosomaFuncion1 extends Cromosoma{

	// Extremos del intervalo de la función.
	private int _xMin = 0;
	private int _xMax = 1;

	// Longitud del cromosoma
	private int _longitudCromosoma;
	
	/**
	 * Constructor de la clase CromosomaFuncion1.
	 * 
	 * @param tolerancia del algoritmo.
	 */
	public CromosomaFuncion1(double tolerancia){
		
		// Calculamos la longitud del cromosoma
		_longitudCromosoma = calcularLongCromosoma(tolerancia);
		
		// Creamos el array de genes del tamanio adecuado
		_genes = new boolean[_longitudCromosoma];
	}
	
	@Override
	public double fenotipo() {
		
		// TODO: Por Hacer
		return 0;
	} 
	
	@Override
	public double evalua() {
	
		// TODO: Por hacer
		return 0;
	}

	@Override
	public int calcularLongCromosoma(double tolerancia) {
		
		return (int) Matematicas.log2(1 + ((_xMax - _xMin)/tolerancia));
	}	
}
