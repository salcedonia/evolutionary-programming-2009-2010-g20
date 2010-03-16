package logica;

import cromosoma.Cromosoma;
import cromosoma.CromosomaFuncion1;
import cromosoma.CromosomaFuncion2;
import cromosoma.CromosomaFuncion3;
import cromosoma.CromosomaFuncion4;
import cromosoma.CromosomaFuncion5;
import cromosoma.TipoCromosoma;

/**
 * Clase que implementa los métodos necesarios para el algoritmo genético
 * simple.
 * 
 * @author Grupo20.
 */
public class AG {

	/**
	 * Población a evaluar.
	 */
	private Cromosoma[] _poblacion;
	/**
	 * Tamanio de la poblacion.
	 */
	private int _tamPoblacion;
	/**
	 * Numero maximo de generaciones.
	 */
	private int _numMaxGeneraciones;
	/**
	 * Mejor individuo.
	 */
	private Cromosoma _elMejor;
	/**
	 * Posicion del mejor cromosoma.
	 */
	@SuppressWarnings("unused")
	private int _posMejor;

	/**
	 * Probabilidad de cruce.
	 */
	@SuppressWarnings("unused")
	private double _probCruce;
	/**
	 * Probabilidad de mutacion.
	 */
	@SuppressWarnings("unused")
	private double _probMutacion;
	/**
	 * Tolerancia de la representacion.
	 */
	private double _tolerancia;

	/**
	 * Indica si se usa elitismo o no.
	 */
	@SuppressWarnings("unused")
	private boolean _elitismo;

	/**
	 * Numero de generaciones procesadas.
	 */
	private int _numGeneración = 0;

	/**
	 * Tipo de cromosoma a crear.
	 */
	private TipoCromosoma _tipoCromosoma;

	/**
	 * Constructor de la clase AG.
	 * 
	 * @param tamPoblacion
	 * @param numMaxGeneraciones
	 * @param probCruce
	 * @param probMutacion
	 * @param tolerancia
	 * @param elitismo
	 * @param tipoCromosoma
	 */
	public AG(int tamPoblacion, int numMaxGeneraciones, double probCruce,
			double probMutacion, double tolerancia, boolean elitismo,
			TipoCromosoma tipoCromosoma) {

		_tamPoblacion = tamPoblacion;
		_numMaxGeneraciones = numMaxGeneraciones;
		_probCruce = probCruce;
		_probMutacion = probMutacion;
		_tolerancia = tolerancia;
		_elitismo = elitismo;
		_tipoCromosoma = tipoCromosoma;
	}

	/**
	 * Aumenta el número de generaciones procesadas.
	 */
	public void aumentarGeneración() {

		_numGeneración++;
	}

	/**
	 * Realiza la selección de individuos de la población.
	 */
	public void seleccion() {

	}

	/**
	 * Realiza la reproducción de individuos de la población.
	 */
	public void reproduccion() {
		// TODO Auto-generated method stub

	}

	/**
	 * Realiza la mutación de los individuos seleccionados en la población.
	 */
	public void mutacion() {
		// TODO Auto-generated method stub

	}

	/**
	 * Asigna la calidad a los individuos de una población.
	 */
	public void evaluarPoblacion() {
		// TODO Auto-generated method stub
	}

	/**
	 * Indica el final del algoritmo.
	 * 
	 * @return Verdadero cuando termina y falso en caso contrario.
	 */
	public boolean terminado() {

		return _numGeneración == _numMaxGeneraciones;
	}

	/**
	 * Inicializa la población a evaluar. Crea los cromosomas y los inicializa
	 * aleatoriamente.
	 */
	public void inicializa() {

		for (int j = 0; j < _tamPoblacion; j++) {

			// Creamos el tipo de cromosoma según corresponda
			switch (_tipoCromosoma) {

			case FUNCION1:
				_poblacion[j] = new CromosomaFuncion1(_tolerancia);
				break;
			case FUNCION2:
				_poblacion[j] = new CromosomaFuncion2(_tolerancia);
				break;
			case FUNCION3:
				_poblacion[j] = new CromosomaFuncion3(_tolerancia);
				break;
			case FUNCION4:
				_poblacion[j] = new CromosomaFuncion4(_tolerancia);
				break;
			case FUNCION5:
				_poblacion[j] = new CromosomaFuncion5(_tolerancia);
				break;
			}

			// Inicializamos el cromosoma
			_poblacion[j].inicializaCromosoma();
			_poblacion[j].setAptitud(_poblacion[j].evalua());
		}
	}

	/**
	 * Devuelve el mejor individuo de una población.
	 * 
	 * @return El mejor individuo de una población.
	 */
	public Cromosoma getElMejor() {

		return _elMejor;
	}
}
