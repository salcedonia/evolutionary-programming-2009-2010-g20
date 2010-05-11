package logica;

import gui.tipos.TipoInicializacion;
import gui.tipos.TipoMutacion;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import utils.Aleatorio;
import utils.ListaOrdenada;

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
	private Individuo[] _poblacion;
	/**
	 * Tamanio de la poblacion.
	 */
	private int _tamPoblacion;
	/**
	 * Numero maximo de generaciones.
	 */
	private int _numMaxGeneraciones;
	/**
	 * Mejor individuo global.
	 */
	private Individuo _elMejorGlobal;
	/**
	 * Mejor individuo local a una generacion.
	 */
	private Individuo _elMejorLocal;
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
	private ListaOrdenada<Individuo> _elite;

	/**
	 * Indica si se usa Escalado Simple o no.
	 */
	private boolean _escaladoSimple;

	/**
	 * Numero de generaciones procesadas.
	 */
	private int _numGeneracion = 0;

	/**
	 * Numero estimado de copias del mejor individuo de la poblacion.
	 */
	private int _P;

	/**
	 * Tipo de metodo de Mutacion del AGS.
	 */
	private TipoMutacion _tipoMutacion;

	/**
	 * Porcentaje de cruce para los nodos funcion.
	 */
	private double _porcentajeCruceFuncion;

	/**
	 * Porcentaje de cruce para los nodos terminales.
	 */
	private double _porcentajeCruceTerminal;

	/**
	 * Indica si se aplica la funcion if o no.
	 */
	private boolean _ifSeleccionado;

	/**
	 * Profundidad Inicial de los arboles.
	 */
	private int _profundidadInicial;

	/**
	 * Profundidad del cruce de los arboles.
	 */
	private int _profundidadCruces;

	private TipoInicializacion _tipoInicializacion;
	
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
	 * @param tipoVersion
	 *            Tipo de version empleada.
	 * @param tipoSeleccion
	 *            Tipo de seleccion empleada.
	 * @param tipoCruce
	 *            Tipo de cruce empleado.
	 * @param tipoMutacion
	 *            Tipo de mutacion empleada.
	 * @param tipoVista
	 *            Tipo de vista empleada en la ventana grafica.
	 * @param tamElite
	 *            Tamanio maximo de miembros de elite.
	 * @param P
	 *            Numero estimado de copias del mejor individuo de la poblacion.
	 * @param numCiudadesMutInsercion
	 *            Numero de ciudades seleccionadas para la mutacion por
	 *            insercion.
	 * @param tamTorneo
	 *            Tamanio para el metodo de seleccion por Torneo.
	 * @param beta
	 *            Parametro para el metodo de seleccion por Ranking.
	 */
	public AG(int numMaxGeneraciones, int tamPoblacion, double probCruce,
			double probMutacion, double tolerancia, int valorN,
			boolean elitismo, boolean escaladoSimple,
			TipoMutacion tipoMutacion, double tamElite, int P,
			double porcentajeCruceFuncion, double porcentajeCruceTerminal,
			boolean ifSeleccionado, int profundidadInicial,
			int profundidadCruces, TipoInicializacion tipoInicializacion) {

		_numMaxGeneraciones = numMaxGeneraciones;
		_tamPoblacion = tamPoblacion;
		_probCruce = probCruce;
		_probMutacion = probMutacion;
		_elitismo = elitismo;
		_escaladoSimple = escaladoSimple;
		_tipoMutacion = tipoMutacion;
		_P = P;
		_porcentajeCruceFuncion = porcentajeCruceFuncion;
		_porcentajeCruceTerminal = porcentajeCruceTerminal;
		_ifSeleccionado = ifSeleccionado;
		_profundidadInicial = profundidadInicial;
		_profundidadCruces = profundidadCruces;
		_tipoInicializacion = tipoInicializacion;

		// Calcula el numero de cromosomas de la elite
		if (_elitismo) {
			_tamElite = (int) (tamElite * _tamPoblacion);
		} else
			_tamElite = 0;
	}

	// -------------------- METODOS DE CRUCE ----------------------//

	/**
	 * Realiza la seleccion de individuos de la poblacion.
	 */
	public void seleccion() {

		// TODO: Hacerlo
	}

	/**
	 * Realiza la reproduccion de individuos de la poblacion.
	 */
	public void reproduccion() {

		// Seleccionados para reproducir
		int[] sel_cruce = new int[_tamPoblacion];
		// Contador de seleccionados
		int num_sel_cruce = 0;

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

		// Cruzamos los indiduos seleccionados
		for (int i = 0; i < num_sel_cruce; i += 2) {
			cruce(_poblacion[sel_cruce[i]], _poblacion[sel_cruce[i + 1]]);
		}
	}

	/**
	 * Cruza los cromosomas padre y madre por el punto de cruce segun el metodo
	 * elegido.
	 * 
	 * @param padre
	 *            Uno de los cromosomas a cruzar.
	 * @param madre
	 *            Uno de los cromosomas a cruzar.
	 * @param generador
	 *            Generador de numeros aleatorios.
	 */
	private void cruce(Individuo padre, Individuo madre) {

		ArrayList<Individuo> hijos;
		Individuo hijo1 = new Individuo(padre);
		Individuo hijo2 = new Individuo(madre);

		int puntoCruce1;
		int puntoCruce2;

		puntoCruce1 = 2 + Aleatorio.intRandom(0, hijo1.getArbol()
				.getNumInstrucciones() - 1);
		puntoCruce2 = 2 + Aleatorio.intRandom(0, hijo2.getArbol()
				.getNumInstrucciones() - 1);

		corta(puntoCruce1, puntoCruce2, hijo1.getArbol(), hijo2.getArbol());

		// Calculamos el valor de los nuevos hijos.
		hijo1.setAdaptacion(2);
		hijo2.setAdaptacion(2);

		padre.setArbol(hijo1.getArbol());
		madre.setArbol(hijo2.getArbol());
	}

	private void corta(int puntoCruce1, int puntoCruce2, Arbol arbol,
			Arbol arbol2) {
		// TODO Auto-generated method stub

	}

	// ------------------- METODOS DE MUTACION --------------------//

	/**
	 * Realiza la mutacion de los individuos seleccionados en la poblacion,
	 * segun el metodo elegido.
	 */
	public void mutacion() {

		switch(_tipoMutacion){
		
		case TERMINAL_SIMPLE: mutacionTerminalSimple(); break;
		case FUNCIONAL_SIMPLE: mutacionFuncionalSimple(); break;
		case ARBOL: mutacionArbol(); break;
		
		}
	}

	private void mutacionArbol() {
		// TODO Auto-generated method stub
		
	}

	private void mutacionFuncionalSimple() {
		// TODO Auto-generated method stub
		
	}

	private void mutacionTerminalSimple() {
		String[] terminales = { "A0", "A1", "D0", "D1", "D2", "D3" };

		for (int i = 0; i < _tamPoblacion; i++) {
			Individuo c = _poblacion[i];

			double numAle = Math.random();

			if (numAle < _probMutacion) {

				int numAle2 = (int) (Math.random() * 4);

				// TODO
				// Simbolo s = getTerminalAleatorio(c);
				// s.setTerminal(terminales[numAle2]);
			}
		}
	}

	/**
	 * Asigna la calidad a los individuos de una poblacion. Calcula la
	 * adaptacion, la aptitud y el Mejor individuo.
	 */
	public void evaluarPoblacion() {

		double punt_acu = 0; // puntuacion acumulada
		double aptitud_mejor = 0;
		double sumadaptacion = 0; // suma de la adaptacion

		revisaAdaptacionMaximiza();

		// Escalamos la adaptacion segun la formula f(x)=a*g()+b
		if (_escaladoSimple)
			for (int i = 0; i < _tamPoblacion; i++)
				_poblacion[i].setAdaptacion(a() * _poblacion[i].getAptitud()
						+ b());

		// Obtenemos el cromosoma con mejor aptitud y la suma de adaptacion
		for (int i = 0; i < _tamPoblacion; i++) {

			sumadaptacion = sumadaptacion + _poblacion[i].getAdaptacion();

			if (_poblacion[i].getAptitud() > aptitud_mejor) {
				_posMejor = i;
				aptitud_mejor = _poblacion[i].getAptitud();
				_elMejorLocal = (Individuo) _poblacion[_posMejor].clone();

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

		if ((_elMejorGlobal == null)
				|| (aptitud_mejor > _elMejorGlobal.getAptitud())) {
			_elMejorGlobal = (Individuo) _poblacion[_posMejor].clone();

			// La adapacion del mejor debe ser P * Media
			if (_escaladoSimple)
				_poblacion[_posMejor].setAdaptacion(_P * getAptitudMedia());

		}
	}

	/**
	 * Calculo del parametro a de la formula del Escalado Lineal.
	 * 
	 * @return El valor del parametro a de la formula del Escalado Lineal.
	 */
	private double a() {

		// ((P-1) * Media) / (fmax - Media)
		return ((_P - 1) * getAptitudMedia())
				/ (_poblacion[_posMejor].getAptitud() - getAptitudMedia());
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
		_poblacion = new Individuo[_tamPoblacion];
		
		// Establecemos el tipo de inicializacion
		Individuo.setTipoInicializacion(_tipoInicializacion);
		
		for (int j = 0; j < _tamPoblacion; j++) {

			_poblacion[j] = new Individuo();
			_poblacion[j].inicializaIndividuo();
			_poblacion[j].setAptitud(_poblacion[j].evalua());
		}
	}

	/**
	 * Devuelve el mejor individuo de una poblacion.
	 * 
	 * @return El mejor individuo de una poblacion.
	 */
	public Individuo getElMejorGlobal() {

		return _elMejorGlobal;
	}

	/**
	 * Devuelve el mejor individuo de una poblacion.
	 * 
	 * @return El mejor individuo de una poblacion.
	 */
	public Individuo getElMejorLocal() {

		return _elMejorLocal;
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
			Comparator<Individuo> miComparador = new Comparator<Individuo>() {
				public int compare(Individuo n1, Individuo n2) {
					return (n1.compareTo(n2));
				}
			};

			_elite = new ListaOrdenada<Individuo>(miComparador);

			// Introduce los primeros tamElite cromosomas de la poblacion
			// en la lista ordenada
			for (int i = 0; i < _tamElite; i++)
				_elite.add((Individuo) _poblacion[i].clone());

			// Para cada cromosoma restante de la poblacion, se introduce en
			// la elite si es mejor que el peor de la elite
			for (int i = _tamElite; i < _tamPoblacion; i++) {

				if (_poblacion[i].getAptitud() > _elite.getMin().getAptitud()) {
					_elite.removeMin();
					_elite.add((Individuo) _poblacion[i].clone());
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

				if (_poblacion[i].getAptitud() < _poblacion[peores.getMax()]
						.getAptitud()) {
					peores.removeMax();
					peores.add(i);
				}
			}

			// Reemplazo de los peores cromosomas de la poblacion por los
			// cromosomas de la elite
			Iterator<Integer> itSelElite = peores.iterator();
			Iterator<Individuo> itElite = _elite.iterator();
			while (itSelElite.hasNext()) {
				Integer posIndividuo = (Integer) itSelElite.next();
				Individuo individuo = (Individuo) itElite.next();
				_poblacion[posIndividuo] = (Individuo) individuo.clone();
			}
		}
	}
}
