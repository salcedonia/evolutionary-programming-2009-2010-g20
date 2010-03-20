package logica;

import java.util.Random;

import cromosoma.Cromosoma;
import cromosoma.CromosomaFuncion1;
import cromosoma.CromosomaFuncion2;
import cromosoma.CromosomaFuncion3;
import cromosoma.CromosomaFuncion4;
import cromosoma.CromosomaFuncion5;
import cromosoma.TipoCromosoma;

/**
 * Clase que implementa los mï¿½todos necesarios para el algoritmo genï¿½tico
 * simple.
 * 
 * @author Grupo20.
 */
public class AG {

	/**
	 * Poblaciï¿½n a evaluar.
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
	private int _posMejor;

	/**
	 * Probabilidad de cruce.
	 */
	private double _probCruce;
	/**
	 * Probabilidad de mutacion.
	 */
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
	 * Indica si se usa Escalado Simple o no.
	 */
	@SuppressWarnings("unused")
	private boolean _escaladoSimple;
	
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
	@SuppressWarnings("unused")
	private int _valorN;

	/**
	 * Constructor de la clase AG.
	 * 
	 * @param tamPoblacion Tamanio de la poblacion.
	 * @param numMaxGeneraciones Numero maximo de generaciones.
	 * @param probCruce Probabilidad de Cruce.
	 * @param probMutacion Probabilidad de Mutacion.
	 * @param tolerancia Tolerancia del algoritmo.
	 * @param valorN Valor de N.
	 * @param elitismo Uso de elitismo en el algoritmo.
	 * @param escaladoSimple Uso de escalado simple en el algoritmo.
	 * @param tipoCromosoma Tipo de cromosoma empleado.
	 */
	public AG(int numMaxGeneraciones, int tamPoblacion, double probCruce,
			double probMutacion, double tolerancia, int valorN, boolean elitismo,
			boolean escaladoSimple, TipoCromosoma tipoCromosoma) {

		_numMaxGeneraciones = numMaxGeneraciones;
		_tamPoblacion = tamPoblacion;
		_probCruce = probCruce;
		_probMutacion = probMutacion;
		_tolerancia = tolerancia;
		_valorN = valorN;
		_elitismo = elitismo;
		_escaladoSimple = escaladoSimple;
		_tipoCromosoma = tipoCromosoma;
	}

	/**
	 * Aumenta el nï¿½mero de generaciones procesadas.
	 */
	public void aumentarGeneracion() {

		_numGeneracion++;
	}

	/**
	 * Realiza la selecciï¿½n de individuos de la poblaciï¿½n.
	 */
	public void seleccion() {
		seleccionRuleta(); // Por ahora solo tenemos este método
	}
	
	/**
	 * Método de seleccion por ruleta. Se seleccionan los cromosomas supervivientes
	 * para la reproducción.
	 */
	private void seleccionRuleta() {
		
		// Seleccionados para sobrevivir
		int[] sel_super = new int[_tamPoblacion];
		double prob; // probabilidad de seleccion
		int pos_super; // posición del superviviente
		Random generador = new Random();
		for (int i = 0; i < _tamPoblacion; i++) {
			prob = generador.nextDouble();
			pos_super = 0;
			while ((prob > _poblacion[pos_super].getPuntAcumulada())
					&& (pos_super < _tamPoblacion)) 
				pos_super++;
			sel_super[i] = pos_super;
		}
		
		// se genera la poblacion intermedia
		Cromosoma[] nuevaPoblacion = new Cromosoma[_tamPoblacion]; 
		for (int i = 0; i < _tamPoblacion; i++) {
			nuevaPoblacion[i] = (Cromosoma) _poblacion[sel_super[i]].clone();
		}
		
		// Ahora nuestra poblacion intermedia es nuestra poblacion.
		_poblacion = nuevaPoblacion;
	}

	/**
	 * Realiza la reproducciï¿½n de individuos de la poblaciï¿½n.
	 */
	public void reproduccion() {
		
		// Seleccionados para reproducir
		int[] sel_cruce = new int[_tamPoblacion];
		// Contador de seleccionados
		int num_sel_cruce = 0;;
		int punto_cruce;
		double prob;
		Random generador = new Random();

		// Se eligen los individuos a cruzar
		for (int i = 0; i < _tamPoblacion; i++) {
			// Se generan tam_pob números aleatorios en [0 1)
			prob = generador.nextDouble();
			// Se eligen los individuos de las posiciones i si prob <
			// prob_cruce
			if (prob < _probCruce) {
				sel_cruce[num_sel_cruce] = i;
				num_sel_cruce++;
			}
		}
		
		// El número de seleccionados se hace par
		if ((num_sel_cruce % 2) == 1)
			num_sel_cruce--;
		
		// Se cruzan los individuos elegidos en un punto al azar
		punto_cruce = (int) generador.nextDouble() * _poblacion[0].getLongitudCromosoma();
		for (int i = 0; i < num_sel_cruce; i += 2) {
			cruce(_poblacion[sel_cruce[i]], _poblacion[sel_cruce[i+1]],punto_cruce);
		}		
	}
	
	/**
	 * Cruza los cromosomas padre y madre por el punto de cruce.
	 * 
	 * @param padre Uno de los cromosomas a cruzar.
	 * @param madre Uno de los cromosomas a cruzar.
	 * @param punto_cruce El punto de cruce para cruzar los cromosomas.
	 */
	private void cruce(Cromosoma padre, Cromosoma madre, int punto_cruce) {
		
		int nBit = 0; // contador para el número de bit recorrido
		
		// se inicializan los hijos
		boolean[][] hijo1 = new boolean[padre.getNumGenes()][];
		boolean[][] hijo2 = new boolean[madre.getNumGenes()][];
		for (int i = 0; i < padre.getNumGenes(); i++) {
			hijo1[i] = new boolean[padre.getGenes()[i].length];
			hijo2[i] = new boolean[madre.getGenes()[i].length];
		}
		
		int i = 0, j = 0;

		// primera parte del intercambio: 1 a 1 y 2 a 2
		for (i =0 ; i < padre.getNumGenes() && nBit < punto_cruce; i++) {
			for (j = 0; j < hijo1[i].length && nBit < punto_cruce; j++ ) {
				hijo1[i][j] = padre.getGenes()[i][j];
				hijo2[i][j] = madre.getGenes()[i][j];
				nBit++;
			}
		}
		
		// segunda parte: 1 a 2 y 2 a 1
		for ( ; i < padre.getNumGenes(); i++) {
			for ( ; j < hijo1[i].length; j++ ) {
				hijo1[i][j] = madre.getGenes()[i][j];
				hijo2[i][j] = padre.getGenes()[i][j];
				nBit++;
			}
		}
		
		// se evalúan y sustituyen a los padres
		padre.setGenes(hijo1);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2);
		madre.setAptitud(madre.evalua());		
	}

	/**
	 * Realiza la mutaciï¿½n de los individuos seleccionados en la poblaciï¿½n.
	 */
	public void mutacion() {
		
		boolean mutado;
		double prob;
		Random generador = new Random();
		
		// para cada cromosoma de la población
		for (int i = 0; i < _tamPoblacion; i++) {
			mutado = false;
			// para cada gen del cromosoma se prueba la mutación
			boolean[][] genes = _poblacion[i].getGenes();
			for (int j = 0; j < genes.length; j++) {
				for (int k = 0; k < genes[j].length; k++) {
					// se genera un numero aleatorio en [0 1)
					prob = generador.nextDouble();
					
					// mutan los genes con prob<prob_mut
					if (prob<_probMutacion) {
						genes[j][k] = !( genes[j][k]);
						mutado = true;
					}
				}
			}
			if (mutado) 
				_poblacion[i].setAptitud(_poblacion[i].evalua());
		}
	}

	/**
	 * Asigna la calidad a los individuos de una poblaciï¿½n.
	 */
	public void evaluarPoblacion() {
		
		double punt_acu = 0; // puntuación acumulada
		double aptitud_mejor = 0; // mejor aptitud
		double sumaptitud = 0; // suma de la aptitud
		
		for (int i = 0; i < _tamPoblacion; i++) {
			sumaptitud = sumaptitud + _poblacion[i].getAptitud();
			if (_poblacion[i].getAptitud() > aptitud_mejor) {
				_posMejor = i;
				aptitud_mejor = _poblacion[i].getAptitud();
			}
		}
		
		for (int i = 0; i < _tamPoblacion; i++) {
			_poblacion[i].setPuntuacion(_poblacion[i].getAptitud()/sumaptitud);
			_poblacion[i].setPuntAcumulada(_poblacion[i].getPuntuacion() + punt_acu);
			punt_acu = punt_acu + _poblacion[i].getPuntuacion();
		}
		
		// Si el mejor de esta generación es mejor que el mejor que
		// tenia antes, se actualiza
		if ((_elMejor == null) || (aptitud_mejor > _elMejor.getAptitud())) {
			_elMejor = (Cromosoma) _poblacion[_posMejor].clone();
		}
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
	 * Inicializa la poblaciï¿½n a evaluar. Crea los cromosomas y los inicializa
	 * aleatoriamente.
	 */
	public void inicializa() {

		// Creamos la poblacion del tamaÃ±o especificado
		_poblacion = new Cromosoma[_tamPoblacion];
		
		for (int j = 0; j < _tamPoblacion; j++) {

			// Creamos el tipo de cromosoma segï¿½n corresponda
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
	 * Devuelve el mejor individuo de una poblaciï¿½n.
	 * 
	 * @return El mejor individuo de una poblaciï¿½n.
	 */
	public Cromosoma getElMejor() {

		return _elMejor;
	}
	
	/**
	 * Devuelve el número de la generación actual.
	 * @return El número de la generación actual.
	 */
	public int getNumGeneracion() {
		
		return _numGeneracion;
	}
	
	/**
	 * Calcula la aptitud media de la población.
	 * 
	 * @return La aptitud media de la población.
	 */
	public double getAptitudMedia() {
		
		double media = 0;
		
		for (int i = 0; i < _tamPoblacion; i++) {
			media += _poblacion[i].getAptitud();
		}
		
		media /= _tamPoblacion;
		
		return media;
	}
}
