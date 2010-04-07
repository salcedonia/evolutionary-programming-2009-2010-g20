package logica;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import utils.ListaOrdenada;

import cromosoma.Cromosoma;
import cromosoma.CromosomaFuncion1;
import cromosoma.CromosomaFuncion2;
import cromosoma.CromosomaFuncion3;
import cromosoma.CromosomaFuncion4;
import cromosoma.CromosomaFuncion5;
import cromosoma.TipoCromosoma;

/**
 * Clase que implementa los metodos necesarios para el algoritmo genetico
 * simple.
 * 
 * @author Grupo20.
 */
public class AG {

	/**
	 * Poblacion a evaluar.
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
	 * Especificacion del tipo de problema.
	 */
	private TipoProblema _tipoProblema;

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
	private boolean _elitismo;

	/**
	 * Tamanio para la elite.
	 */
	private int _tamElite;

	/**
	 * arbol con los cromosomas pertenecientes a la elite.
	 */
	private ListaOrdenada<Cromosoma> _elite;

	/**
	 * Indica si se usa Escalado Simple o no.
	 */
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
	private int _valorN;

	/**
	 * Numero estimado de copias del mejor individuo de la poblacion.
	 */
	private int _P;

	/**
	 * Constructor de la clase AG.
	 * 
	 * @param tamPoblacion
	 *            Tamanio de la poblacion.
	 * @param numMaxGeneraciones
	 *            Numero maximo de generaciones.
	 * @param probCruce
	 *            Probabilidad de Cruce.
	 * @param probMutacion
	 *            Probabilidad de Mutacion.
	 * @param tolerancia
	 *            Tolerancia del algoritmo.
	 * @param valorN
	 *            Valor de N.
	 * @param elitismo
	 *            Uso de elitismo en el algoritmo.
	 * @param escaladoSimple
	 *            Uso de escalado simple en el algoritmo.
	 * @param tipoCromosoma
	 *            Tipo de cromosoma empleado.
	 * @param tipoProblema
	 *            Tipo de problema empleado.
	 * @param tamElite
	 *            Tamanio maximo de miembros de elite.
	 * @param P
	 *            Numero estimado de copias del mejor individuo de la poblacion.
	 */
	public AG(int numMaxGeneraciones, int tamPoblacion, double probCruce,
			double probMutacion, double tolerancia, int valorN,
			boolean elitismo, boolean escaladoSimple,
			TipoCromosoma tipoCromosoma, TipoProblema tipoProblema,
			double tamElite, int P) {

		_numMaxGeneraciones = numMaxGeneraciones;
		_tamPoblacion = tamPoblacion;
		_probCruce = probCruce;
		_probMutacion = probMutacion;
		_tolerancia = tolerancia;
		_valorN = valorN;
		_elitismo = elitismo;
		_escaladoSimple = escaladoSimple;
		_tipoCromosoma = tipoCromosoma;
		_tipoProblema = tipoProblema;
		_P = P;

		// Calcula el numero de cromosomas de la elite
		if (_elitismo) {
			_tamElite = (int) (tamElite * _tamPoblacion);
		} else
			_tamElite = 0;
	}

	/**
	 * Realiza la seleccion de individuos de la poblacion.
	 */
	public void seleccion() {

		seleccionRuleta();
	}

	/**
	 * Metodo de seleccion por ruleta. Se seleccionan los cromosomas
	 * supervivientes para la reproduccion.
	 */
	private void seleccionRuleta() {

		// Seleccionados para sobrevivir
		int[] sel_super = new int[_tamPoblacion];
		double prob; // probabilidad de seleccion
		int pos_super; // posicion del superviviente
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
	 * Realiza la reproduccion de individuos de la poblacion.
	 */
	public void reproduccion() {

		// Seleccionados para reproducir
		int[] sel_cruce = new int[_tamPoblacion];
		// Contador de seleccionados
		int num_sel_cruce = 0;
		;
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

		// El numero de seleccionados se hace par
		if ((num_sel_cruce % 2) == 1)
			num_sel_cruce--;

		// Se cruzan los individuos elegidos en un punto al azar
		punto_cruce = (int) (generador.nextDouble()
				* _poblacion[0].getLongitudCromosoma());
		for (int i = 0; i < num_sel_cruce; i += 2) {
			cruce(_poblacion[sel_cruce[i]], _poblacion[sel_cruce[i + 1]],
					punto_cruce);
		}
	}

	/**
	 * Cruza los cromosomas padre y madre por el punto de cruce.
	 * 
	 * @param padre
	 *            Uno de los cromosomas a cruzar.
	 * @param madre
	 *            Uno de los cromosomas a cruzar.
	 * @param punto_cruce
	 *            El punto de cruce para cruzar los cromosomas.
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

		/*// primera parte del intercambio: 1 a 1 y 2 a 2
		for (i = 0; i < padre.getNumGenes() && nBit < punto_cruce; i++) {
			for (j = 0; j < hijo1[i].length && nBit < punto_cruce; j++) {
				hijo1[i][j] = padre.getGenes()[i][j];
				hijo2[i][j] = madre.getGenes()[i][j];
				nBit++;
			}
		}
		
		

		// segunda parte: 1 a 2 y 2 a 1
		for (; i < padre.getNumGenes(); i++) {
			for (; j < hijo1[i].length; j++) {
				hijo1[i][j] = madre.getGenes()[i][j];
				hijo2[i][j] = padre.getGenes()[i][j];
				nBit++;
			}
		}*/
		
		while ( (nBit!=punto_cruce) && (nBit < padre.getLongitudCromosoma()) ) {
			
			hijo1[i][j] = padre.getGenes()[i][j];
			hijo2[i][j] = madre.getGenes()[i][j];
			nBit++;
			j++;
			if (j >= padre.getGenes()[i].length) {
				i++;
				j = 0;
			}
		}
		
		while ( (nBit < padre.getLongitudCromosoma()) ) {
			
			hijo1[i][j] = madre.getGenes()[i][j];
			hijo2[i][j] = padre.getGenes()[i][j];
			nBit++;
			j++;
			if (j >= padre.getGenes()[i].length) {
				i++;
				j = 0;
			}
		}

		// se evaluan y sustituyen a los padres
		padre.setGenes(hijo1);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2);
		madre.setAptitud(madre.evalua());
	}

	/**
	 * Realiza la mutacion de los individuos seleccionados en la poblacion.
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
					if (prob < _probMutacion) {
						genes[j][k] = !(genes[j][k]);
						mutado = true;
					}
				}
			}

			// Si ha cambiado entonces lo volvemos a evaluar
			if (mutado)
				_poblacion[i].setAptitud(_poblacion[i].evalua());
		}
	}

	/**
	 * Asigna la calidad a los individuos de una poblacion. Calcula la
	 * adaptacion, la aptitud y el Mejor individuo.
	 */
	public void evaluarPoblacion() {

		double punt_acu = 0; // puntuacion acumulada
		double aptitud_mejor = 0; // mejor aptitud
		double sumadaptacion = 0; // suma de la adaptacion

		// Actualizamos la adaptación de cada cromosoma segun el tipo de
		// problema
		switch (_tipoProblema) {

		case MINIMIZACION:
			revisaAdaptacionMinimiza();
			break;
		case MAXIMIZACION:
			revisaAdaptacionMaximiza();
			break;
		}

		// Escalamos la adaptacion segun la formula f(x)=a*g()+b
		if (_escaladoSimple)
			for (int i = 0; i < _tamPoblacion; i++)
				_poblacion[i].setAdaptacion(a() * _poblacion[i].getAptitud()
						+ b());

		// Obtenemos el cromosoma con mejor aptitud y la suma de adaptacion
		for (int i = 0; i < _tamPoblacion; i++) {
			sumadaptacion = sumadaptacion + _poblacion[i].getAdaptacion();

			switch (_tipoProblema) {

			case MINIMIZACION:
				if (_poblacion[i].getAptitud() < aptitud_mejor) {
					_posMejor = i;
					aptitud_mejor = _poblacion[i].getAptitud();
				}
				break;
			case MAXIMIZACION:
				if (_poblacion[i].getAptitud() > aptitud_mejor) {
					_posMejor = i;
					aptitud_mejor = _poblacion[i].getAptitud();
				}
				break;
			}
		}

		// Actualizamos los valores de puntuacion
		for (int i = 0; i < _tamPoblacion; i++) {
			_poblacion[i].setPuntuacion(_poblacion[i].getAdaptacion()
					/ sumadaptacion);
			_poblacion[i].setPuntAcumulada(_poblacion[i].getPuntuacion()
					+ punt_acu);
			punt_acu = punt_acu + _poblacion[i].getPuntuacion();
		}

		// Calculamos el mejor individuo
		calculoElMejor(aptitud_mejor);
	}

	/**
	 * Calcula el mejor individuo y su posicion.
	 * 
	 * @param aptitud_mejor
	 *            La mejor aptitud.
	 */
	private void calculoElMejor(double aptitud_mejor) {
		// Si el mejor de esta generacion es mejor que el mejor que
		// tenia antes, se actualiza
		switch (_tipoProblema) {

		case MINIMIZACION:
			if ((_elMejor == null) || (aptitud_mejor < _elMejor.getAptitud())) {
				_elMejor = (Cromosoma) _poblacion[_posMejor].clone();
			}

			// La adaptacion del mejor debe ser 0
			if (_escaladoSimple)
				_poblacion[_posMejor].setAdaptacion(0);

			break;
		case MAXIMIZACION:
			if ((_elMejor == null) || (aptitud_mejor > _elMejor.getAptitud())) {
				_elMejor = (Cromosoma) _poblacion[_posMejor].clone();
			}

			// La adapacion del mejor debe ser P * Media
			if (_escaladoSimple)
				_poblacion[_posMejor].setAdaptacion(_P * getAptitudMedia());

			break;
		}
	}

	/**
	 * Calculo del parametro a de la formula del Escalado Lineal.
	 * 
	 * @return El valor del parametro a de la formula del Escalado Lineal.
	 */
	private double a() {

		switch (_tipoProblema) {

		case MAXIMIZACION:

			// (P-1) * Media / fmax - Media
			return ((_P - 1) * getAptitudMedia())
					/ (_poblacion[_posMejor].getAptitud() - getAptitudMedia());

		case MINIMIZACION:

			// Media / Media - fmin
			return getAptitudMedia()
					/ (getAptitudMedia() - _poblacion[_posMejor].getAptitud());
		}

		return 0;
	}

	/**
	 * Calculo del parametro b de la formula del Escalado Lineal.
	 * 
	 * @return El valor del parametro b de la formula del Escalado Lineal.
	 */
	private double b() {

		// (1 - a) * Media
		return (1 - a()) * getAptitudMedia();
	}

	/**
	 * Ajusta el valor de adaptacion de cada cromosoma de la poblacion para el
	 * caso de minimizacion.
	 */
	private void revisaAdaptacionMinimiza() {

		double cmax = -Double.MAX_VALUE;
		// un valor por debajo de cualquiera que pueda
		// tomar la funcion objetivo
		for (int i = 0; i < _tamPoblacion; i++) {
			if (_poblacion[i].getAptitud() > cmax)
				cmax = _poblacion[i].getAptitud();
		}
		cmax = cmax * 1.05; // margen para evitar sumadaptacion = 0
		// si converge la poblacion

		// Hacemos el ajuste de la adaptacion segun la formula: f(x) = cmax -
		// g(x)
		for (int i = 0; i < _tamPoblacion; i++) {
			_poblacion[i].setAdaptacion(cmax - _poblacion[i].getAptitud());
		}
	}

	/**
	 * Ajusta el valor de adaptacion de cada cromosoma de la poblacion para el
	 * caso de maximizacion.
	 */
	private void revisaAdaptacionMaximiza() {

		double fmin = Double.MAX_VALUE;

		// calculamos en fmin el peor valor absoluto de las aptitudes
		for (int i = 0; i < _tamPoblacion; i++) {
			if (Math.abs(_poblacion[i].getAptitud()) < fmin)
				fmin = Math.abs(_poblacion[i].getAptitud());
		}

		// Hacemos el ajuste a la adaptacion segun la formula f(x) = g(x) + Fmin
		for (int i = 0; i < _tamPoblacion; i++) {
			_poblacion[i].setAdaptacion(fmin + _poblacion[i].getAptitud());
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
	 * Aumenta el numero de generaciones procesadas.
	 */
	public void aumentarGeneracion() {

		_numGeneracion++;
	}

	/**
	 * Inicializa la poblacion a evaluar. Crea los cromosomas y los inicializa
	 * aleatoriamente.
	 */
	public void inicializa() {

		// Creamos la poblacion del tamanio especificado
		_poblacion = new Cromosoma[_tamPoblacion];

		for (int j = 0; j < _tamPoblacion; j++) {

			// Creamos el tipo de cromosoma segun corresponda
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
				_poblacion[j] = new CromosomaFuncion5(_tolerancia, _valorN);
				break;
			}

			// Inicializamos el cromosoma
			_poblacion[j].inicializaCromosoma();
			_poblacion[j].setAptitud(_poblacion[j].evalua());
		}
	}

	/**
	 * Devuelve el mejor individuo de una poblacion.
	 * 
	 * @return El mejor individuo de una poblacion.
	 */
	public Cromosoma getElMejor() {

		return _elMejor;
	}

	/**
	 * Devuelve el numero de la generacion actual.
	 * 
	 * @return El numero de la generacion actual.
	 */
	public int getNumGeneracion() {

		return _numGeneracion;
	}

	/**
	 * Calcula la aptitud media de la poblacion.
	 * 
	 * @return La aptitud media de la poblacion.
	 */
	public double getAptitudMedia() {

		double media = 0;

		for (int i = 0; i < _tamPoblacion; i++) {
			media += _poblacion[i].getAptitud();
		}

		media /= _tamPoblacion;

		return media;
	}

	/**
	 * Coge los cromosomas que va a formar parte de la elite.
	 * 
	 * @return Array con los cromosomas de la elite.
	 */
	public void separaElite() {

		// Solo se realizan cambios si esta activo el flag de elitismo
		if (_elitismo) {

			// Comparador para el orden de los cromosomas en la lista ordenada
			// de la elite
			Comparator<Cromosoma> miComparador = new Comparator<Cromosoma>() {
				public int compare(Cromosoma n1, Cromosoma n2) {
					return (n1.compareTo(n2));
				}
			};

			_elite = new ListaOrdenada<Cromosoma>(miComparador);

			// Introduce los primeros tamElite cromosomas de la poblacion
			// en la lista ordenada
			for (int i = 0; i < _tamElite; i++)
				_elite.add((Cromosoma) _poblacion[i].clone());

			// Para cada cromosoma restante de la poblacion, se introduce en
			// la elite si es mejor que el peor de la elite
			for (int i = _tamElite; i < _tamPoblacion; i++) {

				switch (_tipoProblema) {

				case MINIMIZACION:
					if (_poblacion[i].getAptitud() < _elite.getMax()
							.getAptitud()) {
						_elite.removeMax();
						_elite.add((Cromosoma) _poblacion[i].clone());
					}
					break;
				case MAXIMIZACION:
					if (_poblacion[i].getAptitud() > _elite.getMin()
							.getAptitud()) {
						_elite.removeMin();
						_elite.add((Cromosoma) _poblacion[i].clone());
					}
					break;
				}
			}
		}
	}

	/**
	 * Incluye los cromosomas de la elite en la poblacion, sustituyendo a los
	 * peores.
	 */
	public void incluyeElite() {

		// Solo se realizan cambios si esta activo el flag de elitismo
		if (_elitismo) {

			// Comparador para los Integer de las posiciones de los cromosomas
			// de la poblacion en la lista ordenada de peores cromosomas
			Comparator<Integer> miComparador = new Comparator<Integer>() {
				public int compare(Integer n1, Integer n2) {
					return (_poblacion[n1].compareTo(_poblacion[n2]));
				}
			};

			// Variable local para almacenar las posiciones de los peores
			// cromosomas de la poblacion
			ListaOrdenada<Integer> peores = new ListaOrdenada<Integer>(
					miComparador);

			// Introduce los primeros tamElite cromosomas de la poblacion
			// en la lista de peores cromosomas
			for (int i = 0; i < _tamElite; i++)
				peores.add(i);

			// Para cada cromosoma restante de la poblacion, si el cromosoma
			// i es peor que el mejor cromosoma de la
			// lista de peores se intercambian
			for (int i = _tamElite; i < _tamPoblacion; i++) {

				switch (_tipoProblema) {

				case MINIMIZACION:
					if (_poblacion[i].getAptitud() > _poblacion[peores.getMin()]
							.getAptitud()) {
						peores.removeMin();
						peores.add(i);
					}
					break;
				case MAXIMIZACION:
					if (_poblacion[i].getAptitud() < _poblacion[peores.getMax()]
							.getAptitud()) {
						peores.removeMax();
						peores.add(i);
					}
					break;
				}
			}

			// Reemplazo de los peores cromosomas de la poblacion por los
			// cromosomas de la elite
			Iterator<Integer> itSelElite = peores.iterator();
			Iterator<Cromosoma> itElite = _elite.iterator();
			while (itSelElite.hasNext()) {
				Integer posCromosoma = (Integer) itSelElite.next();
				Cromosoma cromosoma = (Cromosoma) itElite.next();
				_poblacion[posCromosoma] = (Cromosoma) cromosoma.clone();
			}
		}
	}
}
