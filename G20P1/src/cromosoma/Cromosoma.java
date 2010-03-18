package cromosoma;

import java.util.Random;

/**
 * Clase que implementa los metodos comunes de un cromosoma.
 * 
 * @author Grupo20.
 */
public abstract class Cromosoma {

	/**
	 * Cadena de bits.
	 */
	protected boolean[] _genes;

	/**
	 * Fenotipo del cromosoma.
	 */
	protected double _fenotipo;

	/**
	 * Funcion de evaluacion fitness (adaptacion).
	 */
	protected double _aptitud;

	/**
	 * Puntuaci�n relativa (aptitud/suma).
	 */
	
	protected double _puntuacion;
	
	/**
	 * Puntuaci�n acumulada para la selecci�n.
	 */
	protected double _puntAcumulada;
	
	/**
	 * Inicializa aleatoriamente el vector de genes.
	 */
	public void inicializaCromosoma() {

		Random generador = new Random();
		   
		for (int i = 0; i < _genes.length; i++) {
			
			// Generamos un numero aleatorio entre 0.0 y 0.1
			double aleatorio = generador.nextDouble();
			
			if(aleatorio < 0.5)
				_genes[i] = false;
		    else
				_genes[i] = true;	
		}
	}

	/**
	 * Devuelve el array de genes.
	 * 
	 * @return El array de genes.
	 */
	public boolean[] getGenes() {
		
		return _genes;
	}

	/**
	 * Establece el array de genes a valor "genes".
	 * 
	 * @param genes Nuevo valor del array de genes a establecer.
	 */
	public void setGenes(boolean[] genes) {
		
		_genes = genes;
	}

	/**
	 * Devuelve el fenotipo del cromosoma.
	 * 
	 * @return El fenotipo del cromosoma.
	 */
	public double getFenotipo() {
		
		return _fenotipo;
	}

	/**
	 * Establece el fenotipo del cromosoma a valor "fenotipo".
	 * 
	 * @param fenotipo Nuevo valor del fenotipo a establecer.
	 */
	public void setFenotipo(double fenotipo) {
		
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
	 * Devuelve la puntuaci�n del cromosoma.
	 * 
	 * @return La puntuaci�n del cromosoma.
	 */
	public double getPuntuacion() {
		
		return _puntuacion;
	}

	/**
	 * Establece la puntuaci�n del cromosoma a valor "puntuacion".
	 * 
	 * @param puntuacion Nuevo valor para establecer en la puntuacion.
	 */
	public void setPuntuacion(double puntuacion) {
		
		_puntuacion = puntuacion;
	}

	/**
	 * Devuelve la puntuaci�n acumulada.
	 * 
	 * @return La puntuaci�n acumulada.
	 */
	public double getPuntAcumulada() {
		
		return _puntAcumulada;
	}

	/**
	 * Establece la puntuacion acumulada a valor "acumulada".
	 * 
	 * @param acumulada Nuevo valor de puntuaci�n acumulada a establecer.
	 */
	public void setPuntAcumulada(double acumulada) {
		
		_puntAcumulada = acumulada;
	}
	
	// ---------------METODOS ABSTRACTOS----------------//
	
	/**
	 * Evalua la calidad del cromosoma.
	 * 
	 * @return La calidad del cromosoma.
	 */
	public abstract double evalua();
	
	/**
	 * Calcula el fenotipo del cromosoma.
	 * 
	 * @return El fenotipo del cromosoma.
	 */
	public abstract double fenotipo();
	
	/**
	 * Calcula la longitud del cromosoma con la tolerancia.
	 * 
	 * @param tolerancia Tolerancia del algoritmo.
	 * 
	 * @return La longitud del cromosoma.
	 */
	public abstract int calcularLongCromosoma(double tolerancia);
}
