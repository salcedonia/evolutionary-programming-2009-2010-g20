package cromosoma;

import java.util.Random;

/**
 * Clase que implementa los metodos comunes de un cromosoma.
 * 
 * @author Grupo20.
 */
public abstract class Cromosoma implements Comparable<Object> {

	/**
	 * Genes del cromosoma.
	 * Un array de booleanos para cada gen.
	 */
	protected boolean[][] _genes;
	
	/**
	 * Numero de genes del problema.
	 */
	protected int _numGenes;

	/**
	 * Decodificacion de los genes del cromosoma.
	 */
	protected double[] _fenotipo;

	/**
	 * Valor de la funcion de evaluacion.
	 */
	protected double _aptitud;
	
	/**
	 * Valor de la funcion de adaptacion.
	 */
	protected double _adaptacion;

	/**
	 * Puntuacion relativa (aptitud/suma).
	 */
	
	protected double _puntuacion;
	
	/**
	 * Puntuacion acumulada para la seleccion.
	 */
	protected double _puntAcumulada;
	
	/**
	 * Longitud total en bits del cromosoma.
	 */
	protected int _longitudCromosoma;
	
	/**
	 * Inicializa los genes del cromosoma.
	 */
	public void inicializaCromosoma() {
		
		for (int i = 0; i < _numGenes; i++) {
			
			// Inicializamos el gen
			inicializaGen(i);
		}
	}
	
	/**
	 * Inicializa aleatoriamente el vector de genes.
	 */
	private void inicializaGen(int nGen) {

		Random generador = new Random();
		   
		for (int i = 0; i < _genes[nGen].length; i++) {
			
			// Generamos un numero aleatorio entre 0.0 y 0.1
			double aleatorio = generador.nextDouble();
			
			if(aleatorio < 0.5)
				_genes[nGen][i] = false;
		    else
				_genes[nGen][i] = true;	
		}
	}

	/**
	 * Devuelve el array de genes.
	 * 
	 * @return El array de genes.
	 */
	public boolean[][] getGenes() {
		
		return _genes;
	}

	/**
	 * Establece el array de genes a valor "genes".
	 * 
	 * @param genes Nuevo valor del array de genes a establecer.
	 */
	public void setGenes(boolean[][] genes) {
		
		_genes = genes;
	}

	/**
	 * Devuelve el numero de genes del cromosoma.
	 * 
	 * @return El numero de genes del cromosoma.
	 */
	public int getNumGenes() {
		
		return _numGenes;
	}

	/**
	 * Establece el numero de genes del cromosoma.
	 * 
	 * @param fenotipo Nuevo valor del numero de genes.
	 */
	public void setNumGenes(int numGenes) {
		
		_numGenes = numGenes;
	}
	
	/**
	 * Devuelve el fenotipo del cromosoma.
	 * 
	 * @return El fenotipo del cromosoma.
	 */
	public double[] getFenotipo() {
		
		return _fenotipo;
	}

	/**
	 * Establece el fenotipo del cromosoma a valor "fenotipo".
	 * 
	 * @param fenotipo Nuevo valor del fenotipo a establecer.
	 */
	public void setFenotipo(double[] fenotipo) {
		
		_fenotipo = fenotipo;
	}

	/**
	 * Devuelve la aptitud del cromosoma.
	 * 
	 * @return La aptitud del cromosoma.
	 */
	public double getAptitud() {
		
		return _aptitud;
	}

	/**
	 * Establece la aptitud a valor "aptitud".
	 * 
	 * @param aptitud Nuevo valor de aptitud a establecer.
 	 */
	public void setAptitud(double aptitud) {
		
		_aptitud = aptitud;
	}
	
	/**
	 * Devuelve la adaptacion del cromosoma.
	 * 
	 * @return La adaptacion del cromosoma.
	 */
	public double getAdaptacion() {
		
		return _adaptacion;
	}

	/**
	 * Establece la adaptacion a valor "adaptacion".
	 * 
	 * @param aptitud Nuevo valor de adaptacion a establecer.
 	 */
	public void setAdaptacion(double adaptacion) {
		
		_adaptacion = adaptacion;
	}

	/**
	 * Devuelve la puntuacion del cromosoma.
	 * 
	 * @return La puntuacion del cromosoma.
	 */
	public double getPuntuacion() {
		
		return _puntuacion;
	}

	/**
	 * Establece la puntuacion del cromosoma a valor "puntuacion".
	 * 
	 * @param puntuacion Nuevo valor para establecer en la puntuacion.
	 */
	public void setPuntuacion(double puntuacion) {
		
		_puntuacion = puntuacion;
	}

	/**
	 * Devuelve la puntuacion acumulada.
	 * 
	 * @return La puntuacion acumulada.
	 */
	public double getPuntAcumulada() {
		
		return _puntAcumulada;
	}

	/**
	 * Establece la puntuacion acumulada a valor "acumulada".
	 * 
	 * @param acumulada Nuevo valor de puntuacion acumulada a establecer.
	 */
	public void setPuntAcumulada(double acumulada) {
		
		_puntAcumulada = acumulada;
	}
	
	/**
	 * Establece la Longitud del Cromosoma a valor "longitudCromosoma".
	 * 
	 * @param longitudCromosoma Nuevo valor a establecer.
	 */
	public void setLongitudCromosoma(int longitudCromosoma) {
		
		_longitudCromosoma = longitudCromosoma;
	}

	/**
	 * Devuelve la longitud del Cromosoma.
	 * 
	 * @return La longitud del cromosoma.
	 */
	public int getLongitudCromosoma() {
		
		return _longitudCromosoma;
	}
	
	/**
	 * Halla el valor decimal de un n�mero binario a partir de un vector de 
	 * booleanos.
	 * 
	 * @param gen Gen a convertir en decimal.
	 * 
	 * @return El valor decimal del n�mero binario codificado en el vector de 
	 * booleanos.
	 */
	public double bin_dec(boolean[] gen) {
		
		double valorDecimal = 0, potencia_2 = 1;
		for (int i = 0; i < gen.length; i++) {
			if (gen[i]) {
				valorDecimal += potencia_2;
			}
			potencia_2 *= 2;
		}
		return valorDecimal;
	}
	
	/**
	 * Indica en base a qu� atributos se compara el cromosoma.
	 * 
	 * @param obj El cromosoma a comparar con el this.
	 * @return +1 si this > objeto, -1 si this < objeto, 0 si son iguales.
	 */
	@Override
	public int compareTo(Object obj) {
		
		Cromosoma cromosoma = (Cromosoma) obj;
		
		if (this == cromosoma) return 0;
		
		if (this._aptitud < cromosoma._aptitud) return -1;
		else if (this._aptitud > cromosoma._aptitud) return 1;
		else return 1;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		Cromosoma crom = (Cromosoma) obj;
		
		return crom == this;
	}
	
	// ---------------METODOS ABSTRACTOS----------------//
	
	/**
	 * Evalua la calidad del cromosoma.
	 * 
	 * @return La calidad del cromosoma.
	 */
	public abstract double evalua();
	
	/**
	 * Calcula el fenotipo de un gen.
	 * 
	 * @param gen Gen a calcular su fenotipo.
	 * @param nGen Numero del gen para calcular su fenotipo.
	 * @return El fenotipo del gen.
	 */
	public abstract double fenotipo(boolean[] gen, int nGen);
	
	/**
	 * Calcula la longitud del gen con la tolerancia.
	 * 
	 * @param tolerancia Tolerancia del algoritmo.
	 * 
	 * @return La longitud del gen.
	 */
	public abstract int calcularLongGen(int nGen, double tolerancia);
	
	/**
	 * Calcula el valor de la funcion a partir del fenotipo actual. 
	 * (funcion de evaluacion).
	 * 
	 * @return El valor de la funcion usando el fenotipo actual.
	 */
	public abstract double f();
	
	
	/**
	 * Devuelve una copia del objeto que invoca este metodo.
	 */
	public abstract Object clone();

	/**
	 * Devuelve el string que contiene el fenotipo del cromosoma.
	 */
	public abstract String toString();
	
}