package logica;

import gui.tipos.TipoInicializacion;
import gui.tipos.TipoMutacion;
import gui.tipos.TipoSeleccion;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

import logica.simbolo.Funcion;
import logica.simbolo.Simbolo;
import logica.simbolo.Terminal;

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
	@SuppressWarnings("unused")
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
	private int _numEstimadoCopiasMejor;

	/**
	 * Tipo de metodo de Mutacion del AGS.
	 */
	private TipoMutacion _tipoMutacion;

	/**
	 * Porcentaje de cruce para los nodos funcion.
	 */
	private double _porcentajeCruceFuncion = 0.9;

	/**
	 * Porcentaje de cruce para los nodos terminales.
	 */
	private double _porcentajeCruceTerminal = 0.1;

	/**
	 * Profundidad maxima del arbol.
	 */
	private int _profundidadMaxima;

	/**
	 * Indica si se aplica la funcion if o no.
	 */
	private boolean _ifSeleccionado;

	/**
	 * Tipo de metodo de seleccion.
	 */
	private TipoSeleccion _tipoSeleccion;

	/**
	 * Tipo de inicializacion de los individuos.
	 */
	private TipoInicializacion _tipoInicializacion;

	/**
	 * Beta para el metodo de seleccion de ranking.
	 */
	private double _beta = 1.5;

	/**
	 * Por defecto le ponemos 3.
	 */
	private int _tamTorneo = 3;

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
	 * @param tipoInicializacion
	 *            Tipo de Inicializacion.
	 * @param tipoMutacion
	 *            Tipo de metodo de Mutacion.
	 * @param ifSeleccionado
	 *            Uso de IF.
	 * @param profundidadMaxima
	 *            Profundidad Maxima del arbol.
	 * @param elitismo
	 *            Uso de elitismo en el algoritmo.
	 * @param escaladoSimple
	 *            Uso de escalado simple en el algoritmo.
	 * @param tipoMutacion
	 *            Tipo de mutacion empleada.
	 * @param tipoVista
	 *            Tipo de vista empleada en la ventana grafica.
	 * @param tamElite
	 *            Tamanio maximo de miembros de elite.
	 * @param numEstimadoCopiasMejor
	 *            Numero estimado de copias del mejor individuo de la poblacion.
	 */
	public AG(int numMaxGeneraciones, int tamPoblacion, double probCruce,
			double probMutacion, TipoSeleccion tipoSeleccion,
			TipoInicializacion tipoInicializacion, TipoMutacion tipoMutacion,
			boolean ifSeleccionado, int profundidadMaxima, boolean elitismo,
			boolean escaladoSimple, double tamElite, int numEstimadoCopiasMejor) {

		// Guardamos todos los parametros
		_numMaxGeneraciones = numMaxGeneraciones;
		_tamPoblacion = tamPoblacion;
		_probCruce = probCruce;
		_probMutacion = probMutacion;
		_tipoSeleccion = tipoSeleccion;
		_tipoInicializacion = tipoInicializacion;
		_tipoMutacion = tipoMutacion;
		_ifSeleccionado = ifSeleccionado;
		_profundidadMaxima = profundidadMaxima;
		_elitismo = elitismo;
		_escaladoSimple = escaladoSimple;
		_numEstimadoCopiasMejor = numEstimadoCopiasMejor;

		// Calcula el numero de cromosomas de la elite
		calculaTamanioElite(tamElite);

		// Inicializamos los casos de prueba para la evaluacion
		Arbol.cargaCasosPrueba();
	}

	/**
	 * Inicializa la poblacion a evaluar. Crea los cromosomas y los inicializa
	 * aleatoriamente.
	 */
	public void inicializa() {

		// Creamos la poblacion del tamanio especificado
		_poblacion = new Individuo[_tamPoblacion];

		for (int j = 0; j < _tamPoblacion; j++) {

			_poblacion[j] = new Individuo(_tipoInicializacion, _ifSeleccionado,
					_profundidadMaxima, _porcentajeCruceFuncion,
					_porcentajeCruceTerminal);
			_poblacion[j].setAptitud(_poblacion[j].evalua());

			System.out.println(_poblacion[j].toString());
			System.out.println();
		}
	}

	/**
	 * Calcula el tamanio de la elite.
	 * 
	 * @param tamElite
	 *            Tamanio de la elite introducido por el usuario.
	 */
	private void calculaTamanioElite(double tamElite) {
		if (_elitismo) {
			_tamElite = (int) (tamElite * _tamPoblacion);
		} else
			_tamElite = 0;
	}

	/**
	 * Realiza la seleccion de individuos de la poblacion.
	 */
	public void seleccion() {

		// Para esta practica tenemos varios metodos de seleccion
		switch (_tipoSeleccion) {

		case RULETA:
			seleccionRuleta();
			break;
		case TORNEO:
			seleccionTorneo();
			break;
		case RANKING:
			seleccionRanking();
			break;
		}
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

			while ((prob > _poblacion[pos_super].getPuntuacionAcumulada())
					&& (pos_super < _tamPoblacion))
				pos_super++;
			sel_super[i] = pos_super;
		}

		// se genera la poblacion intermedia
		Individuo[] nuevaPoblacion = new Individuo[_tamPoblacion];
		for (int i = 0; i < _tamPoblacion; i++) {
			nuevaPoblacion[i] = (Individuo) _poblacion[sel_super[i]].clone();
		}

		// Ahora nuestra poblacion intermedia es nuestra poblacion.
		_poblacion = nuevaPoblacion;
	}

	/**
	 * Metodo de seleccion por Torneo. Se seleccionan los cromosomas
	 * supervivientes para la reproduccion.
	 */
	private void seleccionTorneo() {

		int[] random;// , random2, random3;
		Individuo[] nuevo = new Individuo[_tamPoblacion];
		Random generador = new Random();

		// int tamTorneo = valorNtorneo;
		random = new int[_tamTorneo];
		for (int i = 0; i < _tamPoblacion; i++) {

			for (int j = 0; j < _tamTorneo; j++)
				random[j] = generador.nextInt(_tamPoblacion);

			int mejor = random[0];
			for (int j = 1; j < _tamTorneo; j++) {

				if (_poblacion[random[j]].getAptitud() < _poblacion[mejor]
						.getAptitud()) {
					mejor = random[j];
				}
			}

			nuevo[i] = (Individuo) _poblacion[mejor].clone();
		}

		_poblacion = nuevo;
	}

	/**
	 * Metodo de seleccion por Ranking. Se seleccionan los cromosomas
	 * supervivientes para la reproduccion.
	 */
	private void seleccionRanking() {

		// Ordenamos por QuickSort la poblacion
		ordenaPoblacion();

		Individuo[] futurosPadres = new Individuo[_tamPoblacion];
		futurosPadres[0] = (Individuo) _poblacion[0].clone();
		futurosPadres[1] = (Individuo) _poblacion[1].clone();
		int numPadres = 2;

		double[] segmentosFitness = rankPoblacion();
		double segmentoEntero = segmentosFitness[segmentosFitness.length - 1];

		while (numPadres < _tamPoblacion) {

			double x = (double) (Math.random() * segmentoEntero);
			if (x <= segmentosFitness[0]) {

				// El primer individuo fue seleccionado
				futurosPadres[numPadres] = (Individuo) _poblacion[0].clone();
				numPadres++;
			} else
				for (int i = 1; i < _tamPoblacion; i++)
					if (x > segmentosFitness[i - 1] && x <= segmentosFitness[i]) {

						// El i-esimo individuo fue seleccionado
						futurosPadres[numPadres] = (Individuo) _poblacion[i]
								.clone();
						numPadres++;
					}
		}

		_poblacion = futurosPadres;
	}

	/**
	 * Devuelve el Ranking de la poblacion.
	 * 
	 * @return El ranking de la poblacion.
	 */
	private double[] rankPoblacion() {

		double[] segmentosFitness = new double[_tamPoblacion];

		for (int i = 0; i < segmentosFitness.length; i++) {
			double probIEsimo = (double) i / _tamPoblacion;
			probIEsimo = probIEsimo * 2 * (_beta - 1);
			probIEsimo = _beta - probIEsimo;
			probIEsimo = (double) probIEsimo * ((double) 1 / _tamPoblacion);
			if (i != 0)
				segmentosFitness[i] = segmentosFitness[i - 1] + probIEsimo;
			else
				segmentosFitness[i] = probIEsimo;
		}
		return segmentosFitness;
	}

	/**
	 * Ordena la poblacion por el metodo de QuickSort.
	 */
	private void ordenaPoblacion() {
		quickSort(_poblacion, 0, _poblacion.length - 1);
	}

	/**
	 * Ordena el array a por el metodo de QuickSort.
	 * 
	 * @param a
	 *            Array a ordenar.
	 * @param izquierda
	 *            Indice por la izquierda.
	 * @param derecha
	 *            Indice por la derecha.
	 */
	public void quickSort(Individuo[] a, int izquierda, int derecha) {
		if (derecha <= izquierda)
			return;
		int i = particion(a, izquierda, derecha);
		quickSort(a, izquierda, i - 1);
		quickSort(a, i + 1, derecha);
	}

	/**
	 * Realiza la particion del array para hacer el QuickSort. Va desde
	 * partition a[left] hasta a[right], asumiendo que left < right.
	 */
	private int particion(Individuo[] a, int izquierda, int derecha) {
		int i = izquierda - 1;
		int j = derecha;
		while (true) {

			while (menor(a[++i], a[derecha]))
				// Encuentra el elemento por la izquierda a intercambiar
				; // a[derecha] actua como centinela
			while (menor(a[derecha], a[--j]))
				// Encuentra el elemento por la derecha a intercambiar
				if (j == izquierda)
					break; // Para no salirnos del array
			if (i >= j)
				break; // Comprueba si se cruzan los indices

			intercambio(a, i, j);
		}

		intercambio(a, i, derecha);

		return i;
	}

	/**
	 * Comprueba si x < y.
	 * 
	 * @param x
	 *            Individuo x.
	 * @param y
	 *            Individuo y.
	 * 
	 * @return Verdadero si x < y y falso en caso contrario.
	 */
	private boolean menor(Individuo x, Individuo y) {
		return (x.getAptitud() < y.getAptitud());
	}

	/**
	 * Intercambia a[i] y a[j].
	 * 
	 * @param a
	 *            Array
	 * @param i
	 *            Posicion 1 a intercambiar.
	 * @param j
	 *            Posicion 2 a intercambiar.
	 */
	private void intercambio(Individuo[] a, int i, int j) {
		Individuo swap = a[i];
		a[i] = a[j];
		a[j] = swap;
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

		// TODO
	}

	// ------------------- METODOS DE MUTACION --------------------//

	/**
	 * Realiza la mutacion de los individuos seleccionados en la poblacion,
	 * segun el metodo elegido.
	 */
	public void mutacion() {

		switch (_tipoMutacion) {

		case TERMINAL_SIMPLE:
			mutacionTerminalSimple();
			break;
		case FUNCIONAL_SIMPLE:
			mutacionFuncionalSimple();
			break;
		case ARBOL:
			mutacionArbol();
			break;
		}
	}

	private void mutacionArbol() {
		
		for (int i = 0; i < _poblacion.length; i++) {

			Random random = new Random();
			
			double numAle = random.nextDouble();
			
			if (numAle < _probMutacion) {
				
				_poblacion[i] = new Individuo(_tipoInicializacion, _ifSeleccionado,
						_profundidadMaxima, _porcentajeCruceFuncion,
						_porcentajeCruceTerminal);
				_poblacion[i].setAptitud(_poblacion[i].evalua());
			}
		}

	}

	private void mutacionFuncionalSimple() {
		
		for (int i = 0; i < _poblacion.length; i++) {
			
			Individuo individuo = (Individuo) _poblacion[i];
			Random random = new Random();
			
			double numAle =random.nextDouble();
			
			if (numAle < _probMutacion) {
				
				int numAle2 = Aleatorio.intRandom(individuo.getNodosFuncion().size());
				
				Funcion funcion = (Funcion)individuo.getNodosFuncion().get(numAle2).getSimbolo();
				
				Simbolo s = funcion.getFuncionAleatoria();
				
				funcion.setValor(s.getValor());
			}
		}
	}

	private void mutacionTerminalSimple() {

		for (int i = 0; i < _poblacion.length; i++) {
			
			Individuo individuo = (Individuo) _poblacion[i];
			Random random = new Random();
			
			double numAle =random.nextDouble();
			
			if (numAle < _probMutacion) {
				
				int numAle2 = Aleatorio.intRandom(individuo.getNodosTerminales().size());
				
				Terminal terminal = (Terminal)individuo.getNodosTerminales().get(numAle2).getSimbolo();
				
				Simbolo s = terminal.getTerminalAleatorio();
				
				terminal.setValor(s.getValor());
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

		// Escalamos la adaptacion segun la formula f(x)=a*g()+b
		if (_escaladoSimple)
			for (int i = 0; i < _tamPoblacion; i++)
				_poblacion[i]
						.setAptitud(a() * _poblacion[i].getAptitud() + b());

		// Obtenemos el cromosoma con mejor aptitud y la suma de adaptacion
		for (int i = 0; i < _tamPoblacion; i++) {

			sumadaptacion = sumadaptacion + _poblacion[i].getAptitud();

			if (_poblacion[i].getAptitud() > aptitud_mejor) {
				_posMejor = i;
				aptitud_mejor = _poblacion[i].getAptitud();
				_elMejorLocal = (Individuo) _poblacion[_posMejor].clone();
			}
		}

		// Actualizamos los valores de puntuacion
		for (int i = 0; i < _tamPoblacion; i++) {
			_poblacion[i].setPuntuacion(_poblacion[i].getAptitud()
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

			if (_elMejorGlobal.getAptitud() > 500) {

				System.out.println("Trwkerw");
			}

			// La adapacion del mejor debe ser P * Media
			if (_escaladoSimple)
				_poblacion[_posMejor].setAptitud(_numEstimadoCopiasMejor
						* getAptitudMedia());
		}
	}

	/**
	 * Calculo del parametro a de la formula del Escalado Lineal.
	 * 
	 * @return El valor del parametro a de la formula del Escalado Lineal.
	 */
	private double a() {

		// ((P-1) * Media) / (fmax - Media)
		return ((_numEstimadoCopiasMejor - 1) * getAptitudMedia())
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
