package logica;

import cromosoma.Cromosoma;
import cromosoma.CromosomaFuncion1;
import cromosoma.CromosomaFuncion2;
import cromosoma.CromosomaFuncion3;
import cromosoma.CromosomaFuncion4;
import cromosoma.CromosomaFuncion5;
import cromosoma.TipoCromosoma;

/**
 * Clase que implementa los m�todos necesarios para el algoritmo gen�tico
 * simple.
 * 
 * @author Grupo20.
 */
public class AG {

	/**
	 * Poblaci�n a evaluar.
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
	private int _numGeneracion = 0;

	/**
	 * Tipo de cromosoma a crear.
	 */
	private TipoCromosoma _tipoCromosoma;
	
	/**
	 * Valor de N.
	 */
	private int _valorN;

	/**
	 * Constructor de la clase AG.
	 * 
	 * @param tamPoblacion
	 * @param numMaxGeneraciones
	 * @param probCruce
	 * @param probMutacion
	 * @param tolerancia
	 * @param valorN
	 * @param elitismo
	 * @param tipoCromosoma
	 */
	public AG(int numMaxGeneraciones, int tamPoblacion, double probCruce,
			double probMutacion, double tolerancia, int valorN, boolean elitismo,
			TipoCromosoma tipoCromosoma) {

		_numMaxGeneraciones = numMaxGeneraciones;
		_tamPoblacion = tamPoblacion;
		_probCruce = probCruce;
		_probMutacion = probMutacion;
		_tolerancia = tolerancia;
		_valorN = valorN;
		_elitismo = elitismo;
		_tipoCromosoma = tipoCromosoma;
	}

	/**
	 * Aumenta el n�mero de generaciones procesadas.
	 */
	public void aumentarGeneracion() {

		_numGeneracion++;
	}

	/**
	 * Realiza la selecci�n de individuos de la poblaci�n.
	 */
	public void seleccion() {

	}

	/**
	 * Realiza la reproducci�n de individuos de la poblaci�n.
	 */
	public void reproduccion() {
		// TODO Auto-generated method stub

	}

	/**
	 * Realiza la mutaci�n de los individuos seleccionados en la poblaci�n.
	 */
	public void mutacion() {
		// TODO Auto-generated method stub

	}

	/**
	 * Asigna la calidad a los individuos de una poblaci�n.
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

		return _numGeneracion == _numMaxGeneraciones;
	}

	/**
	 * Inicializa la poblaci�n a evaluar. Crea los cromosomas y los inicializa
	 * aleatoriamente.
	 */
	public void inicializa() {

		// Creamos la poblacion del tamaño especificado
		_poblacion = new Cromosoma[_tamPoblacion];
		
		for (int j = 0; j < _tamPoblacion; j++) {

			// Creamos el tipo de cromosoma seg�n corresponda
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
	 * Devuelve el mejor individuo de una poblaci�n.
	 * 
	 * @return El mejor individuo de una poblaci�n.
	 */
	public Cromosoma getElMejor() {

		return _elMejor;
	}
}
