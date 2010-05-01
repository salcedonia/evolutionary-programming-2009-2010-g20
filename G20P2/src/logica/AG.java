package logica;

import gui.tipos.TipoCromosoma;
import gui.tipos.TipoCruce;
import gui.tipos.TipoMutacion;
import gui.tipos.TipoProblema;
import gui.tipos.TipoSeleccion;
import gui.tipos.TipoVersion;
import gui.tipos.TipoVista;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import utils.ListaOrdenada;
import utils.TablaConectividad;

import cromosoma.Cromosoma;
import cromosoma.practica1.CromosomaFuncion1;
import cromosoma.practica1.CromosomaFuncion2;
import cromosoma.practica1.CromosomaFuncion3;
import cromosoma.practica1.CromosomaFuncion4;
import cromosoma.practica1.CromosomaFuncion5;
import cromosoma.practica1.GenP1;
import cromosoma.practica2.CromosomaViajante;
import cromosoma.practica2.CromosomaViajanteV2;
import cromosoma.practica2.GenP2;
import cromosoma.practica2.GenP2V2;

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
	 * Mejor individuo global.
	 */
	private Cromosoma _elMejorGlobal;
	/**
	 * Mejor individuo local a una generacion.
	 */
	private Cromosoma _elMejorLocal;
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
	 * Tipo de version para el problema del viajante.
	 */
	private TipoVersion _tipoVersion;

	/**
	 * Tipo de metodo de seleccion del AGS.
	 */
	private TipoSeleccion _tipoSeleccion;

	/**
	 * Tipo de metodo de cruce del AGS.
	 */
	private TipoCruce _tipoCruce;

	/**
	 * Tipo de metodo de Mutacion del AGS.
	 */
	private TipoMutacion _tipoMutacion;

	/**
	 * Tipo de vista de la ventana grafica.
	 */
	private TipoVista _tipoVista;

	/**
	 * Numero de ciudades para la mutacion por insercion.
	 */
	private int _numCiudadesMutInsercion;
	/**
	 * Tamanio del Torneo para el metodo de seleccion.
	 */
	private int _tamTorneo;
	/**
	 * Parametro para el metodo de seleccion por Ranking.
	 */
	private double _beta;

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
			TipoCromosoma tipoCromosoma, TipoProblema tipoProblema,
			TipoVersion tipoVersion, TipoSeleccion tipoSeleccion,
			TipoCruce tipoCruce, TipoMutacion tipoMutacion,
			TipoVista tipoVista, double tamElite, int P,
			int numCiudadesMutInsercion, int tamTorneo, double beta) {

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
		_tipoVersion = tipoVersion;
		_tipoSeleccion = tipoSeleccion;
		_tipoCruce = tipoCruce;
		_tipoMutacion = tipoMutacion;
		_tipoVista = tipoVista;
		_P = P;
		_numCiudadesMutInsercion = numCiudadesMutInsercion;
		_tamTorneo = tamTorneo;
		_beta = beta;

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

		switch (_tipoVista) {

		case PRACTICA1:

			// El metodo de seleccion por defecto es el de ruleta
			seleccionRuleta();
			break;
		case PRACTICA2:

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
			break;

		case PRACTICA3:
			// Por hacer
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
	 * Metodo de seleccion por Torneo. Se seleccionan los cromosomas
	 * supervivientes para la reproduccion.
	 */
	private void seleccionTorneo() {

		int[] random;// , random2, random3;
		Cromosoma[] nuevo = new Cromosoma[_tamPoblacion];
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

			nuevo[i] = (Cromosoma) _poblacion[mejor].clone();
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

		Cromosoma[] futurosPadres = new Cromosoma[_tamPoblacion];
		futurosPadres[0] = (Cromosoma) _poblacion[0].clone();
		futurosPadres[1] = (Cromosoma) _poblacion[1].clone();
		int numPadres = 2;

		double[] segmentosFitness = rankPoblacion();
		double segmentoEntero = segmentosFitness[segmentosFitness.length - 1];

		while (numPadres < _tamPoblacion) {

			double x = (double) (Math.random() * segmentoEntero);
			if (x <= segmentosFitness[0]) {

				// El primer individuo fue seleccionado
				futurosPadres[numPadres] = (Cromosoma) _poblacion[0].clone();
				numPadres++;
			} else
				for (int i = 1; i < _tamPoblacion; i++)
					if (x > segmentosFitness[i - 1] && x <= segmentosFitness[i]) {

						// El i-esimo individuo fue seleccionado
						futurosPadres[numPadres] = (Cromosoma) _poblacion[i]
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
	public void quickSort(Cromosoma[] a, int izquierda, int derecha) {
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
	private int particion(Cromosoma[] a, int izquierda, int derecha) {
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
	 *            Cromosoma x.
	 * @param y
	 *            Cromosoma y.
	 * 
	 * @return Verdadero si x < y y falso en caso contrario.
	 */
	private boolean menor(Cromosoma x, Cromosoma y) {
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
	private void intercambio(Cromosoma[] a, int i, int j) {
		Cromosoma swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	// -------------------- METODOS DE CRUCE ----------------------//

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
			cruce(_poblacion[sel_cruce[i]], _poblacion[sel_cruce[i + 1]],
					generador);
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
	private void cruce(Cromosoma padre, Cromosoma madre, Random generador) {

		switch (_tipoVista) {

		case PRACTICA1:

			// Se cruzan los individuos elegidos en un punto al azar
			int punto_cruce = (int) (generador.nextDouble() * _poblacion[0]
					.getLongitudCromosoma());
			cruceSimple(padre, madre, punto_cruce);
			break;

		case PRACTICA2:

			int punto_cruce1 = 0,
			punto_cruce2 = 0;

			do {
				// Se cruzan los individuos en funcion de dos puntos al azar
				// Donde el primero siempre es menor que el segundo
				punto_cruce1 = generador.nextInt(_poblacion[0]
						.getLongitudCromosoma());
				punto_cruce2 = generador.nextInt(_poblacion[0]
						.getLongitudCromosoma());
			} while (punto_cruce1 >= punto_cruce2);

			switch (_tipoVersion) {

			case VERSION1:

				switch (_tipoCruce) {

				case PMX:
					crucePMX(padre, madre, punto_cruce1, punto_cruce2);
					break;
				case OX:
					cruceOX(padre, madre, punto_cruce1, punto_cruce2);
					break;
				case VARIANTE_OX:
					cruceVarianteOX(padre, madre, punto_cruce1, punto_cruce2);
					break;
				case CICLOS_CX:
					cruceCiclosCX(padre, madre, punto_cruce1, punto_cruce2);
					break;
				case ERX:
					cruceERX(padre, madre, punto_cruce1, punto_cruce2);
					break;
				case COD_ORDINAL:
					cruceCodificacionOrdinal(padre, madre, punto_cruce1,
							punto_cruce2);
					break;
				case PROPIO:
					crucePropio(padre, madre, punto_cruce1, punto_cruce2);
					break;
				}
				break;

			case VERSION2:
				cruceVersion2(padre, madre, punto_cruce1, punto_cruce2);
				break;
			}
			break;

		case PRACTICA3:
			// Por hacer
			break;
		}
	}

	/**
	 * Cruza los cromosomas padre y madre por el punto de cruce segun la
	 * practica 1.
	 * 
	 * @param padre
	 *            Uno de los cromosomas a cruzar.
	 * @param madre
	 *            Uno de los cromosomas a cruzar.
	 * @param punto_cruce
	 *            El punto de cruce para cruzar los cromosomas.
	 */
	private void cruceSimple(Cromosoma padre, Cromosoma madre, int punto_cruce) {

		int nBit = 0; // contador para el numero de bit recorrido

		// se inicializan los hijos
		GenP1[] hijo1 = new GenP1[padre.getNumGenes()];
		GenP1[] hijo2 = new GenP1[madre.getNumGenes()];
		for (int i = 0; i < padre.getNumGenes(); i++) {
			hijo1[i] = new GenP1(((GenP1) padre.getGen(i)).getLongitudGen());
			hijo2[i] = new GenP1(((GenP1) madre.getGen(i)).getLongitudGen());
		}

		int i = 0, j = 0;

		while ((nBit != punto_cruce) && (nBit < padre.getLongitudCromosoma())) {

			hijo1[i].setBit(j, ((GenP1) padre.getGen(i)).getBit(j));
			hijo2[i].setBit(j, ((GenP1) madre.getGen(i)).getBit(j));
			nBit++;
			j++;
			if (j >= ((GenP1) padre.getGen(i)).getLongitudGen()) {
				i++;
				j = 0;
			}
		}

		while ((nBit < padre.getLongitudCromosoma())) {

			hijo1[i].setBit(j, ((GenP1) madre.getGen(i)).getBit(j));
			hijo2[i].setBit(j, ((GenP1) padre.getGen(i)).getBit(j));
			nBit++;
			j++;
			if (j >= ((GenP1) padre.getGen(i)).getLongitudGen()) {
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
	 * Cruza los cromosomas padre y madre por el punto de cruce segun el metodo
	 * PMX.
	 * 
	 * @param padre
	 *            Uno de los cromosomas a cruzar.
	 * @param madre
	 *            Uno de los cromosomas a cruzar.
	 * @param punto_cruce1
	 *            El 1er punto de cruce para cruzar los cromosomas.
	 * @param punto_cruce2
	 *            El 2do punto de cruce para cruzar los cromosomas.
	 */
	private void crucePMX(Cromosoma padre, Cromosoma madre, int punto_cruce1,
			int punto_cruce2) {
		
		// Descendientes
		GenP2[] hijo1 = new GenP2[padre.getNumGenes()];
		GenP2[] hijo2 = new GenP2[madre.getNumGenes()];

		// Arrays de parejas
		ArrayList<Integer> fijosPadre = new ArrayList<Integer>();
		ArrayList<Integer> fijosMadre = new ArrayList<Integer>();

		// Se intercambian los posiciones dentro del intervalo
		for (int i = punto_cruce1; i < punto_cruce2; i++) {

			hijo1[i] = (GenP2) madre.getGen(i).clone();
			hijo2[i] = (GenP2) padre.getGen(i).clone();
			fijosPadre.add( (Integer) madre.getGen(i).getGen());
			fijosMadre.add( (Integer) padre.getGen(i).getGen());
		}

		for (int i = 0; i < padre.getNumGenes(); i++) {

			// Se eliminan los repetidos fuera del intervalo
			if (i < punto_cruce1 || i >= punto_cruce2) {

				// Si un valor no está en la subcadena
				// intercambiada, se copia igual
				int aux = (Integer) padre.getGen(i).getGen();
				
				while (fijosPadre.contains(aux))					
					aux = (Integer) padre.getGen( devuelvePos( (GenP2[])madre.getGenes(), aux) ).getGen();
				
				hijo1[i] = new GenP2(aux);
				
				// Se sustituye por el valor que tenga dicha
				// subcadena en el otro padre.
				
				aux = (Integer) madre.getGen(i).getGen();
				
				while (fijosMadre.contains(aux))
					aux = (Integer) madre.getGen( devuelvePos( (GenP2[])padre.getGenes(), aux) ).getGen();
				
				hijo2[i] = new GenP2(aux);
			}
		}

		// se evaluan y sustituyen a los padres
		padre.setGenes(hijo1);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2);
		madre.setAptitud(madre.evalua());
	}

	/**
	 * Cruza los cromosomas padre y madre por el punto de cruce segun el metodo
	 * OX.
	 * 
	 * @param padre
	 *            Uno de los cromosomas a cruzar.
	 * @param madre
	 *            Uno de los cromosomas a cruzar.
	 * @param punto_cruce1
	 *            El 1er punto de cruce para cruzar los cromosomas.
	 * @param punto_cruce2
	 *            El 2do punto de cruce para cruzar los cromosomas.
	 */
	private void cruceOX(Cromosoma padre, Cromosoma madre, int punto_cruce1,
			int punto_cruce2) {

		// se inicializan los hijos copiando los valores en los dos hijos
		GenP2[] hijo1 = new GenP2[padre.getNumGenes()];
		GenP2[] hijo2 = new GenP2[madre.getNumGenes()];

		int j1, j2;

		ArrayList<Integer> fijosPadre = new ArrayList<Integer>();
		ArrayList<Integer> fijosMadre = new ArrayList<Integer>();

		for (int i = punto_cruce1; i < punto_cruce2; i++) {

			// Intercambiamos los elementos en el segmento
			hijo1[i] = (GenP2) madre.getGen(i).clone();
			hijo2[i] = (GenP2) padre.getGen(i).clone();

			// Rellenamos los elementos fijos para no repetir
			fijosPadre.add((Integer) madre.getGen(i).getGen());
			fijosMadre.add((Integer) padre.getGen(i).getGen());
		}

		// Para los valores que faltan en los hijos se copian los
		// valores de los padres comenzando a partir de la zona
		// copiada y respetando el orden:

		j1 = (punto_cruce2) % padre.getNumGenes();

		// Partimos del segundo punto de cruce por ejemplo
		for (int pos = punto_cruce2 % padre.getNumGenes(); pos < punto_cruce1
				|| pos >= punto_cruce2; pos = (pos + 1) % padre.getNumGenes()) {

			// Buscamos el siguiente que no este en el intervalo o que no haya
			// sido cambiado
			int aux = (Integer) padre.getGen(j1).getGen();
			while (fijosPadre.contains(aux)) {
				j1 = (j1 + 1) % padre.getNumGenes();
				aux = (Integer) padre.getGen(j1).getGen();
			}
				

			// Ponemos el siguiente disponible
			hijo1[pos] = (GenP2) padre.getGen(j1).clone();
			// Aniadimos al vector para que sepa que ya no esta disponible
			fijosPadre.add( (Integer) padre.getGen(j1).getGen());
		}

		j2 = (punto_cruce2) % madre.getNumGenes();

		for (int pos = punto_cruce2 % madre.getNumGenes(); pos < punto_cruce1
				|| pos >= punto_cruce2; pos = (pos + 1) % madre.getNumGenes()) {
			// Buscamos el siguiente que no este en el intervalo o que no haya
			// sido cambiado
			int aux = (Integer) madre.getGen(j2).getGen();
			while (fijosMadre.contains(aux)) {
				j2 = (j2 + 1) % madre.getNumGenes();
				aux = (Integer) madre.getGen(j2).getGen();
			}
				

			// Ponemos el siguiente disponible
			hijo2[pos] = (GenP2) madre.getGen(j2).clone();
			// Aniadimos al vector para que sepa que ya no esta disponible
			fijosMadre.add( (Integer) madre.getGen(j2).getGen());
		}

		System.out.println("Cruce OX: \n");

		// se evaluan y sustituyen a los padres
		padre.setGenes(hijo1);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2);
		madre.setAptitud(madre.evalua());
	}

	/**
	 * Cruza los cromosomas padre y madre por el punto de cruce segun el metodo
	 * para la variante de OX.
	 * 
	 * @param padre
	 *            Uno de los cromosomas a cruzar.
	 * @param madre
	 *            Uno de los cromosomas a cruzar.
	 * @param punto_cruce1
	 *            El 1er punto de cruce para cruzar los cromosomas.
	 * @param punto_cruce2
	 *            El 2¼ punto de cruce para cruzar los cromosomas.
	 */
	private void cruceVarianteOX(Cromosoma padre, Cromosoma madre,
			int punto_cruce1, int punto_cruce2) {

		// Descendientes
		GenP2[] hijo1 = new GenP2[padre.getNumGenes()];
		GenP2[] hijo2 = new GenP2[madre.getNumGenes()];

		// Se eligen un numero n aleatorio de cantidad de posiciones a
		// intercambiar
		Random generador = new Random();
		int n = generador.nextInt(padre.getNumGenes());

		// Array de posiciones aleatorias seleccionadas ordenadas para el padre
		SortedSet<Integer> orden = new TreeSet<Integer>();
		ArrayList<Integer> posOrdenadasPadre = new ArrayList<Integer>();
		ArrayList<Integer> posOrdenadasMadre = new ArrayList<Integer>();
		
		// Generamos dicho array
		for (int i = 0; i < n; i++) {

			int num = 0;

			// Seleccionamos posiciones al azar y sin repetidos
			do {
				num = generador.nextInt(padre.getNumGenes());
			} while (orden.contains(num));

			orden.add(num);
		}
		
		// Generamos el orden con respecto al otro progenitor
		Iterator<Integer> iterador = orden.iterator();
		while (iterador.hasNext()){
			
			int valor = (Integer) iterador.next();
			posOrdenadasPadre.add(devuelvePos( (GenP2[]) madre.getGenes(), (Integer) padre.getGen(valor).getGen()));
			posOrdenadasMadre.add(devuelvePos( (GenP2[]) padre.getGenes(), (Integer) madre.getGen(valor).getGen()));
		}
		
		// Ahora recorremos las ordenadas
		Iterator<Integer> iteradorP = posOrdenadasPadre.iterator();
		Iterator<Integer> iteradorM = posOrdenadasMadre.iterator();
		
		// Generamos los hijos
		for (int pos = 0; pos < padre.getNumGenes(); pos++) {

			// Si no esta entre las seleccionadas se copia lo que tenga la madre
			if (!posOrdenadasPadre.contains(pos))
				hijo1[pos] = (GenP2) madre.getGen(pos).clone();
			else {
				// Respetando el orden, recuperamos siempre el primer elemento
				// de la lista
				if(iteradorP.hasNext())
					hijo1[pos] = (GenP2) madre.getGen((Integer) iteradorP.next()).clone();
			}
			
			// Si no esta entre las seleccionadas se copia lo que tenga la madre
			if (!posOrdenadasMadre.contains(pos))
				hijo2[pos] = (GenP2) padre.getGen(pos).clone();
			else {
				// Respetando el orden, recuperamos siempre el primer elemento
				// de la lista
				if(iteradorM.hasNext())
					hijo2[pos] = (GenP2) padre.getGen((Integer) iteradorM.next()).clone();
			}
		}
		
		System.out.println("Cruce OX Con Variacion: \n");

		// se evaluan y sustituyen a los padres
		padre.setGenes(hijo1);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2);
		madre.setAptitud(madre.evalua());
	}

	/**
	 * Cruza los cromosomas padre y madre por el punto de cruce segun el metodo
	 * con ciclos CX.
	 * 
	 * @param padre
	 *            Uno de los cromosomas a cruzar.
	 * @param madre
	 *            Uno de los cromosomas a cruzar.
	 * @param punto_cruce1
	 *            El 1er punto de cruce para cruzar los cromosomas.
	 * @param punto_cruce2
	 *            El 2¼ punto de cruce para cruzar los cromosomas.
	 */
	private void cruceCiclosCX(Cromosoma padre, Cromosoma madre,
			int punto_cruce1, int punto_cruce2) {

		// se inicializan los hijos copiando los valores en los dos hijos
		GenP2[] hijo1 = new GenP2[padre.getNumGenes()];
		GenP2[] hijo2 = new GenP2[madre.getNumGenes()];

		// Copiamos los padres en los hijos
		for (int i = 0; i < padre.getNumGenes(); i++) {
			hijo1[i] = (GenP2) padre.getGen(i).clone();
			hijo2[i] = (GenP2) madre.getGen(i).clone();
		}

		boolean encontrado = false;
		ArrayList<Integer> fijosPadre = new ArrayList<Integer>();
		ArrayList<Integer> fijosMadre = new ArrayList<Integer>();

		int inicioCiclo = 0;
		// Fijamos el primer elemento del primer hijo
		fijosPadre.add((Integer) padre.getGen(inicioCiclo).getGen());
		fijosPadre.add((Integer) madre.getGen(inicioCiclo).getGen());

		// Empezamos a buscar por la posicion del primer fijo en el padre
		int posHijo1 = devuelvePos( (GenP2[]) padre.getGenes(), (Integer) madre.getGen(inicioCiclo).getGen());

		// Se repite hasta el primer ciclo
		while (!encontrado) {

			if (madre.getGen(posHijo1).getGen().equals(padre.getGen(inicioCiclo).getGen()))
				encontrado = true;
			else {
				// Fijamos los siguientes
				fijosPadre.add((Integer) madre.getGen(posHijo1).getGen());

				// Calculamos el siguiente valor a evaluar
				posHijo1 = devuelvePos( (GenP2[]) padre.getGenes(), (Integer) madre.getGen(posHijo1).getGen());
			}
		}

		// Empezamos a buscar por la posicion del primer fijo en el padre
		int posHijo2 = devuelvePos( (GenP2[]) madre.getGenes(), (Integer) padre.getGen(inicioCiclo).getGen());

		// Fijamos el primer elemento del segundo hijo
		fijosMadre.add((Integer) madre.getGen(inicioCiclo).getGen());
		fijosMadre.add((Integer) padre.getGen(inicioCiclo).getGen());

		encontrado = false;

		// Se repite hasta el primer ciclo
		while (!encontrado) {

			if (padre.getGen(posHijo2).getGen().equals(madre.getGen(inicioCiclo).getGen()))
				encontrado = true;
			else {
				// Fijamos los siguientes
				fijosMadre.add((Integer) padre.getGen(posHijo2).getGen());

				// Calculamos el siguiente valor a evaluar
				posHijo2 = devuelvePos( (GenP2[]) madre.getGenes(), (Integer) padre.getGen(posHijo2).getGen());
			}
		}

		// Ahora recorremos el padre para generar los dos hijos
		for (int i = 0; i < padre.getNumGenes(); i++) {

			// Ponemos el que tenga la madre, si no, significa que esta fijo
			// y no lo tocamos
			int aux = (Integer) hijo1[i].getGen();
			if (!fijosPadre.contains(aux))
				hijo1[i] = (GenP2) madre.getGen(i).clone();

			aux = (Integer) hijo2[i].getGen();
			if (!fijosMadre.contains(aux))
				hijo2[i] = (GenP2) padre.getGen(i).clone();
		}
		
		// se evaluan y sustituyen a los padres
		padre.setGenes(hijo1);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2);
		madre.setAptitud(madre.evalua());
	}

	/**
	 * 
	 * @param a
	 * @param elem
	 * @return
	 */
	private int devuelvePos(GenP2[] a, int elem){
		
		boolean encontrado = false;
		int pos=0;
		
		while(!encontrado){
			
			if(elem == (Integer)(a[pos].getGen()))
				encontrado = true;
			else
				pos++;
		}
		
		return pos;
	}
	
	/**
	 * Cruza los cromosomas padre y madre por el punto de cruce segun el metodo
	 * ERX.
	 * 
	 * @param padre
	 *            Uno de los cromosomas a cruzar.
	 * @param madre
	 *            Uno de los cromosomas a cruzar.
	 * @param punto_cruce1
	 *            El 1er punto de cruce para cruzar los cromosomas.
	 * @param punto_cruce2
	 *            El 2¼ punto de cruce para cruzar los cromosomas.
	 */
	private void cruceERX(Cromosoma padre, Cromosoma madre, int punto_cruce1,
			int punto_cruce2) {
		
		TablaConectividad tablaC = new TablaConectividad((GenP2[])padre.getGenes(),(GenP2[])madre.getGenes());
		
		// Genera hijo 1
		GenP2[] hijo1 = combinaRuta((GenP2[])padre.getGenes(),tablaC, (Integer) madre.getGen(0).getGen());
		
		// Genera hijo 2
		GenP2[] hijo2 = combinaRuta((GenP2[])madre.getGenes(),tablaC, (Integer) padre.getGen(0).getGen());
		
		// se evaluan y sustituyen a los padres
		padre.setGenes(hijo1);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2);
		madre.setAptitud(madre.evalua());
	}

	/**
	 * Realiza la combinacion de rutas a partir de los genes de un progenitor.
	 * 
	 * @param progenitor Genes a partir de los cuales hacer la recombinacion.
	 * @return La ruta recombinada.
	 */
	private GenP2[] combinaRuta(GenP2[] progenitor,TablaConectividad tablaC, int primeroPareja) {
		
		GenP2[] hijo = new GenP2[progenitor.length];
		Random generador = new Random();
		
		boolean conMinimoCamino = false;
		boolean genesValidos = false;
		int numIntentos = 0;
		
		while (!genesValidos) {
			
			int ciudadActual = primeroPareja;
			hijo[0] = new GenP2(ciudadActual);
			ArrayList<Integer> listaDinamica = new ArrayList<Integer>();
			listaDinamica.add(ciudadActual);
			
			int i = 1;
			genesValidos = true;
			
			while ((genesValidos) && (i < progenitor.length)) {
				
				ArrayList<Integer> vecinosCercanos = tablaC.calculaMenorRutaPosible(ciudadActual, listaDinamica, conMinimoCamino);
				
				if (vecinosCercanos.size() == 0) {
					genesValidos = false; // generación inválida
				}
				else {
					if (vecinosCercanos.size() > 1) {
						// random para escoger
						int ciudadAzar = generador.nextInt(vecinosCercanos.size());
						
						ciudadActual = vecinosCercanos.get(ciudadAzar);
					}
					else ciudadActual = vecinosCercanos.get(0);
					

					hijo[i] = new GenP2(ciudadActual);
					listaDinamica.add(ciudadActual);
					
					i++;
					
				}
				
				
			}
			
			numIntentos++;
			
			if (numIntentos == 300) {
				
				// Hace lo mismo, pero no coge el camino con menos conectividad (que da mas porcentage de conseguir
				// una ruta exitosa, que en algunos casos le hace bloquearse. De esta manera elige al azar entre los
				// camino disponibles y consigue encontrar una combinacion correcta.
				conMinimoCamino = false;
				
			}
			
		}
		
		// System.out.println("Número de pasos: "+ numIntentos);
		
		return hijo;
	}
		
	/**
	 * Cruza los cromosomas padre y madre por el punto de cruce segun el metodo
	 * con condificacion ordinal.
	 * 
	 * @param padre
	 *            Uno de los cromosomas a cruzar.
	 * @param madre
	 *            Uno de los cromosomas a cruzar.
	 * @param punto_cruce1
	 *            El 1er punto de cruce para cruzar los cromosomas.
	 * @param punto_cruce2
	 *            El 2¼ punto de cruce para cruzar los cromosomas.
	 */
	private void cruceCodificacionOrdinal(Cromosoma padre, Cromosoma madre,
			int punto_cruce1, int punto_cruce2) {

		Random generador = new Random();
		
		// Obtener codificación de los cromosomas
		GenP2[] padreCodificado = codifica((GenP2[])padre.getGenes());
		GenP2[] madreCodificada = codifica((GenP2[])madre.getGenes());
		
		
		// se inicializan los hijos copiando los valores en los dos hijos
		GenP2[] hijo1 = new GenP2[padre.getNumGenes()];
		GenP2[] hijo2 = new GenP2[madre.getNumGenes()];
		for (int i = 0; i < padre.getNumGenes(); i++) {
			hijo1[i] = (GenP2) ((GenP2) padreCodificado[i]).clone();
			hijo2[i] = (GenP2) ((GenP2) madreCodificada[i]).clone();
		}
		
		// Cruzar en un punto de cruce
		int punto_cruce = (int) (generador.nextDouble() * _poblacion[0]
		                                         			.getLongitudCromosoma());
		int nGen = punto_cruce; // contador para el numero de gen recorrido

		while ((nGen < padre.getNumGenes())) {

			hijo1[nGen].setGen(madreCodificada[nGen].getGen());
			hijo2[nGen].setGen(padreCodificado[nGen].getGen());
			nGen++;
		}
		
		// Traducir la codificacion
		GenP2[] hijo1Traducido = descodifica(hijo1);
		GenP2[] hijo2Traducido = descodifica(hijo2);
		
		// se evaluan y sustituyen a los padres
		padre.setGenes(hijo1Traducido);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2Traducido);
		madre.setAptitud(madre.evalua());
	}
	
	/**
	 * Codifica segun la posicion de cada elemento en una lista dinamica
	 * con los numeros de cada ciudad, eliminando los numeros segun se asignan.
	 * 
	 * @param c Cromosoma a codificar.
	 * @return Los genes codificados.
	 */
	private GenP2[] codifica(GenP2[] c) {
		
		GenP2[] codificado = new GenP2[c.length];
		
		// Crea la lista con los numeros de las ciudades en orden creciente
		ArrayList<Integer> listaDinamica = new ArrayList<Integer>();
		for (int i = 1; i <= c.length; i++)
			listaDinamica.add(i);
			
		GenP2[] padre = c;
		for (int i = 0; i < c.length; i++) {
			
			int ciudad = (Integer) padre[i].getGen();
			
			boolean encontrado = false;
			Iterator<Integer> it = listaDinamica.iterator();
			int j = 1;
			while ((!encontrado) && it.hasNext()) {
				
				int ciudadLista = it.next();
				if (ciudadLista == ciudad) {
					encontrado = true;
					it.remove();
					codificado[i] = new GenP2(j);

				}
				j++;
				
			}
			
		}
		
		return codificado;
	}

	/**
	 * Descodifica segun la posicion de cada elemento en una lista dinamica
	 * con los numeros de cada ciudad, eliminando los numeros segun se asignan.
	 * 
	 * @param c Cromosoma a codificar.
	 * @return Los genes codificados.
	 */
	private GenP2[] descodifica(GenP2[] c) {
		
		GenP2[] descodificado = new GenP2[c.length];
		
		// Crea la lista con los numeros de las ciudades en orden creciente
		ArrayList<Integer> listaDinamica = new ArrayList<Integer>();
		for (int i = 1; i <= c.length; i++)
			listaDinamica.add(i);
			
		GenP2[] padre = c;
		for (int i = 0; i < c.length; i++) {
			
			int ciudad = (Integer) padre[i].getGen();
			
			descodificado[i] = new GenP2(listaDinamica.get(ciudad-1));
			
			listaDinamica.remove(ciudad-1);		
		}
		
		return descodificado;
	}
	
	/**
	 * Cruza los cromosomas padre y madre por el punto de cruce segun el metodo
	 * nuestro.
	 * 
	 * @param padre
	 *            Uno de los cromosomas a cruzar.
	 * @param madre
	 *            Uno de los cromosomas a cruzar.
	 * @param punto_cruce1
	 *            El 1er punto de cruce para cruzar los cromosomas.
	 * @param punto_cruce2
	 *            El 2¼ punto de cruce para cruzar los cromosomas.
	 */
	private void crucePropio(Cromosoma padre, Cromosoma madre,
			int punto_cruce1, int punto_cruce2) {

		// se inicializan los hijos copiando los valores en los dos hijos
		GenP2[] hijo1 = new GenP2[padre.getNumGenes()];
		GenP2[] hijo2 = new GenP2[madre.getNumGenes()];

		// ArrayList auxiliar para almacenar la ruta entre los puntos de cruce
		ArrayList<Integer> rutaCruce = new ArrayList<Integer>();
		
		for (int i = punto_cruce1; i < punto_cruce2; i++)
			rutaCruce.add((Integer) padre.getGen(i).getGen());
		
		
		// Se crea el hijo saltando las ciudades de rutaCruce y poniendo las demas en el orden
		// en que aparecen. Cuando encuentra el primer elemento de la ruta de cruce,
		// pone toda la ruta de cruce seguida en el hijo.
		
		int i = 0;
		int elemHijo1 = 0;
		while (i < padre.getNumGenes()) {
			
			int ciudadContador = (Integer) madre.getGen(i).getGen();
			if (!rutaCruce.contains(ciudadContador)) {
				hijo1[elemHijo1] = new GenP2(ciudadContador);
				elemHijo1++;
			}
			else {
				if ((rutaCruce.size() > 0) && (ciudadContador == rutaCruce.get(0))) {
					
					Iterator<Integer> itRC = rutaCruce.iterator();
					while (itRC.hasNext()) {
						
						hijo1[elemHijo1] = new GenP2(itRC.next());
						elemHijo1++;
						
					}
					
				}
			}
			i++;
		}
		
		// se hace lo mismo con el otro cromosoma
		rutaCruce = new ArrayList<Integer>();
		for (int j = punto_cruce1; j < punto_cruce2; j++)
			rutaCruce.add((Integer) madre.getGen(j).getGen());
		
		i = 0;
		int elemHijo2 = 0;
		while (i < madre.getNumGenes()) {
			
			int ciudadContador = (Integer) padre.getGen(i).getGen();
			if (!rutaCruce.contains(ciudadContador)) {
				hijo2[elemHijo2] = new GenP2(ciudadContador);
				elemHijo2++;
			}
			else {
				if ((rutaCruce.size() > 0) && (ciudadContador == rutaCruce.get(0))) {
					
					Iterator<Integer> itRC = rutaCruce.iterator();
					while (itRC.hasNext()) {
						
						hijo2[elemHijo2] = new GenP2(itRC.next());
						elemHijo2++;
						
					}
					
				}
			}
			i++;
		}
		
		// se evaluan y sustituyen a los padres
		padre.setGenes(hijo1);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2);
		madre.setAptitud(madre.evalua());
	}
	
	/**
	 * Cruza los cromosomas padre y madre por el punto de cruce segun el metodo
	 * nuestro para la version 2.
	 * 
	 * @param padre
	 *            Uno de los cromosomas a cruzar.
	 * @param madre
	 *            Uno de los cromosomas a cruzar.
	 * @param punto_cruce1
	 *            El 1er punto de cruce para cruzar los cromosomas.
	 * @param punto_cruce2
	 *            El 2do punto de cruce para cruzar los cromosomas.
	 */
	private void cruceVersion2(Cromosoma padre, Cromosoma madre,
			int punto_cruce1, int punto_cruce2) {

		TablaConectividad tablaC = new TablaConectividad((GenP2[])padre.getGenes(),(GenP2[])madre.getGenes());
		
		// Genera hijo 1
		GenP2V2[] hijo1 = combinaRutaV2((GenP2V2[])padre.getGenes(),tablaC, (Integer) madre.getGen(0).getGen());
		
		// Genera hijo 2
		GenP2V2[] hijo2 = combinaRutaV2((GenP2V2[])madre.getGenes(),tablaC, (Integer) padre.getGen(0).getGen());
		
		// se evaluan y sustituyen a los padres
		padre.setGenes(hijo1);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2);
		madre.setAptitud(madre.evalua());
	}
	

	/**
	 * Realiza la combinacion de rutas a partir de los genes de un progenitor.
	 * 
	 * @param progenitor Genes a partir de los cuales hacer la recombinacion.
	 * @return La ruta recombinada.
	 */
	private GenP2V2[] combinaRutaV2(GenP2V2[] progenitor,TablaConectividad tablaC, int primeroPareja) {
		
		GenP2V2[] hijo = new GenP2V2[progenitor.length];
		Random generador = new Random();
		
		boolean conMinimoCamino = false;
		boolean genesValidos = false;
		int numIntentos = 0;
		
		while (!genesValidos) {
			
			int ciudadActual = primeroPareja;
			int diaAux = progenitor[devuelvePos(progenitor, ciudadActual)].getDia();
			hijo[0] = new GenP2V2(ciudadActual, diaAux);
			ArrayList<Integer> listaDinamica = new ArrayList<Integer>();
			listaDinamica.add(ciudadActual);
			
			int i = 1;
			genesValidos = true;
			
			while ((genesValidos) && (i < progenitor.length)) {
				
				ArrayList<Integer> vecinosCercanos = tablaC.calculaMenorRutaPosible(ciudadActual, listaDinamica, conMinimoCamino);
				
				if (vecinosCercanos.size() == 0) {
					genesValidos = false; // generación inválida
				}
				else {
					if (vecinosCercanos.size() > 1) {
						// random para escoger
						int ciudadAzar = generador.nextInt(vecinosCercanos.size());
						
						ciudadActual = vecinosCercanos.get(ciudadAzar);
					}
					else ciudadActual = vecinosCercanos.get(0);
					
					diaAux = progenitor[devuelvePos(progenitor, ciudadActual)].getDia();
					hijo[i] = new GenP2V2(ciudadActual,diaAux);
					listaDinamica.add(ciudadActual);
					
					i++;
					
				}
				
				
			}
			
			numIntentos++;
			
			if (numIntentos == 300) {
				
				// Hace lo mismo, pero no coge el camino con menos conectividad (que da mas porcentage de conseguir
				// una ruta exitosa, que en algunos casos le hace bloquearse. De esta manera elige al azar entre los
				// camino disponibles y consigue encontrar una combinacion correcta.
				conMinimoCamino = false;
				
			}
			
		}
		
		// System.out.println("Número de pasos: "+ numIntentos);
		
		return hijo;
	}
	
	// ------------------- METODOS DE MUTACION --------------------//

	/**
	 * Realiza la mutacion de los individuos seleccionados en la poblacion,
	 * segun el metodo elegido.
	 */
	public void mutacion() {

		switch (_tipoVista) {

		case PRACTICA1:
			mutacionSimple();
			break;

		case PRACTICA2:
			switch (_tipoVersion) {

			case VERSION1:
				switch (_tipoMutacion) {

				case INSERCION:
					mutacionInsercion();
					break;
				case INTERCAMBIO:
					mutacionIntercambio();
					break;
				case INVERSION:
					mutacionInversion();
					break;
				case PROPIO:
					mutacionPropio();
					break;
				}
				break;

			case VERSION2:
				mutacionVersion2();
				break;
			}
			break;

		case PRACTICA3:
			// Por hacer
			break;
		}
	}

	/**
	 * Realiza la mutacion de los individuos seleccionados en la poblacion segun
	 * la practica 1.
	 */
	public void mutacionSimple() {

		boolean mutado;
		double prob;
		Random generador = new Random();

		// para cada cromosoma de la poblacion
		for (int i = 0; i < _tamPoblacion; i++) {
			mutado = false;
			// para cada gen del cromosoma se prueba la mutacion
			GenP1[] genes = (GenP1[]) _poblacion[i].getGenes();
			for (int j = 0; j < genes.length; j++) {
				for (int k = 0; k < genes[j].getLongitudGen(); k++) {
					// se genera un numero aleatorio en [0 1)
					prob = generador.nextDouble();

					// mutan los genes con prob<prob_mut
					if (prob < _probMutacion) {

						if (!genes[j].getBit(k))
							genes[j].setBit(k, true);
						else if (genes[j].getBit(k))
							genes[j].setBit(k, false);

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
	 * Realiza la mutacion de los individuos seleccionados en la poblacion segun
	 * el metodo de insercion.
	 */
	public void mutacionInsercion() {

		boolean mutado;
		double prob;
		Random generador = new Random();

		// para cada cromosoma de la poblacion
		for (int nPob = 0; nPob < _tamPoblacion; nPob++) {
			mutado = false;
			// para cada gen del cromosoma se prueba la mutacion
			GenP2[] genes = (GenP2[]) _poblacion[nPob].getGenes();

			for (int pos = 0; pos < genes.length; pos++) {
				// se genera un numero aleatorio en [0 1)
				prob = generador.nextDouble();

				// mutan los genes con prob<prob_mut
				if (prob < _probMutacion) {

					ArrayList<Integer> posiciones = new ArrayList<Integer>();
					ArrayList<Integer> posCiudades = new ArrayList<Integer>();

					for (int i = 0; i < _numCiudadesMutInsercion; i++) {
						posiciones.add(generador.nextInt(_poblacion[0]
								.getLongitudCromosoma() - 1));
						posCiudades.add(generador.nextInt(_poblacion[0]
								.getLongitudCromosoma() - 1));
					}

					int ciudadReemplazada;
					int j = 0;
					int aux;
					for (int i = 0; i < posiciones.size(); i++) {
						ciudadReemplazada = (Integer) genes[posiciones.get(i)]
								.getGen();
						genes[posiciones.get(i)] = (GenP2) genes[posCiudades
								.get(i)].clone();
						j = posiciones.get(i) + 1;
						while (!((Integer) genes[j].getGen())
								.equals((Integer) genes[posCiudades.get(i)]
										.getGen())) {
							aux = ciudadReemplazada;
							ciudadReemplazada = (Integer) genes[j].getGen();
							genes[j].setGen(aux);
							j = (j + 1) % genes.length;
						}
						genes[j].setGen(ciudadReemplazada);
					}
					mutado = true;
				}
			}
			// Si ha cambiado entonces lo volvemos a evaluar
			if (mutado)
				_poblacion[nPob].setAptitud(_poblacion[nPob].evalua());
		}
	}

	/**
	 * Realiza la mutacion de los individuos seleccionados en la poblacion segun
	 * el metodo de intercambio.
	 * 
	 * Se eligen dos posiciones al azar y se intercambian sus valores.
	 */
	public void mutacionIntercambio() {

		boolean mutado;
		double prob;
		Random generador = new Random();

		// para cada cromosoma de la poblacion
		for (int nPob = 0; nPob < _tamPoblacion; nPob++) {
			mutado = false;
			// para cada gen del cromosoma se prueba la mutacion
			GenP2[] genes = (GenP2[]) _poblacion[nPob].getGenes();

			for (int pos = 0; pos < genes.length; pos++) {
				// se genera un numero aleatorio en [0 1)
				prob = generador.nextDouble();

				// mutan los genes con prob<prob_mut
				if (prob < _probMutacion) {

					int pos1, pos2, aux;

					pos1 = generador.nextInt(_poblacion[0]
							.getLongitudCromosoma());
					pos2 = generador.nextInt(_poblacion[0]
							.getLongitudCromosoma());

					aux = (Integer) genes[pos1].getGen();
					genes[pos1].setGen((Integer) genes[pos2].getGen());
					genes[pos2].setGen(aux);

					mutado = true;
				}
			}

			// Si ha cambiado entonces lo volvemos a evaluar
			if (mutado)
				_poblacion[nPob].setAptitud(_poblacion[nPob].evalua());
		}
	}

	/**
	 * Realiza la mutacion de los individuos seleccionados en la poblacion según
	 * el metodo de inversion.
	 */
	public void mutacionInversion() {

		boolean mutado;
		double prob;
		Random generador = new Random();

		// para cada cromosoma de la poblacion
		for (int nPob = 0; nPob < _tamPoblacion; nPob++) {
			mutado = false;
			// para cada gen del cromosoma se prueba la mutacion
			GenP2[] genes = (GenP2[]) _poblacion[nPob].getGenes();

			for (int pos = 0; pos < genes.length; pos++) {
				// se genera un numero aleatorio en [0 1)
				prob = generador.nextDouble();

				// mutan los genes con prob<prob_mut
				if (prob < _probMutacion) {

					int punto_cruce1 = 0, punto_cruce2 = 0, aux;

					do {
						// Se cruzan los individuos en funcion de dos puntos al
						// azar
						// Donde el primero siempre es menor que el segundo
						punto_cruce1 = generador.nextInt(_poblacion[0]
								.getLongitudCromosoma());
						punto_cruce2 = generador.nextInt(_poblacion[0]
								.getLongitudCromosoma());
					} while (punto_cruce1 >= punto_cruce2);

					for (int i = punto_cruce1; i <= punto_cruce1
							+ ((punto_cruce2 - punto_cruce1) / 2); i++) {

						aux = (Integer) genes[i].getGen();
						genes[i] = (GenP2) genes[punto_cruce2 + punto_cruce1
								- i].clone();
						genes[punto_cruce2 + punto_cruce1 - i].setGen(aux);
					}

					mutado = true;
				}
			}

			// Si ha cambiado entonces lo volvemos a evaluar
			if (mutado)
				_poblacion[nPob].setAptitud(_poblacion[nPob].evalua());
		}
	}

	/**
	 * Realiza la mutacion de los individuos seleccionados en la poblacion segun
	 * nuestro propio metodo.
	 */
	public void mutacionPropio() {
		
		boolean mutado;
		double prob;
		Random generador = new Random();

		// para cada cromosoma de la poblacion
		for (int nPob = 0; nPob < _tamPoblacion; nPob++) {
			mutado = false;
			// para cada gen del cromosoma se prueba la mutacion
			GenP2[] genes = (GenP2[])_poblacion[nPob].getGenes();
			
			prob = generador.nextDouble();
			
			// mutan los genes con prob<prob_mut
			if (prob < _probMutacion) {
				
				// Selecciona dos rutas (las rutas son solo entre 2 ciudades)
				int[] rutas = seleccionaRutas(); // contienen los indices de comienzo de cada ruta
				
				// Genera los 4 cromosomas
				GenP2[] mejor = obtenMejor(genes,rutas);
				
				// Se queda con el mejor y sustituye al original
				_poblacion[nPob].setGenes(mejor);
				
				mutado = true;
			}

			// Si ha cambiado entonces lo volvemos a evaluar
			if (mutado)
				_poblacion[nPob].setAptitud(_poblacion[nPob].evalua());
		}
	}
	
	/**
	 * Selecciona al azar dos rutas entre 2 ciudades, disjuntas.
	 * 
	 * @param genes Cromosoma sobre el que elegir las rutas.
	 * @param rutas Las rutas 
	 */
	private int[] seleccionaRutas() {
		
		int[] rutas = new int[2]; // contienen los indices de comienzo de cada ruta
		
		Random generador = new Random();
		
		// Comienzo de la ruta 1
		rutas[0] = generador.nextInt(_poblacion[0].getLongitudCromosoma()-1);
		
		// Comienzo de la ruta 2 pero con ciudades diferentes de la primera
		do {
			rutas[1] = generador.nextInt(_poblacion[0].getLongitudCromosoma() - 1);
		} while (rutas[1] == rutas[0] || rutas[1] == rutas[0] +1 || 
				rutas[1]+1 == rutas[0] || rutas[1] + 1 == rutas[0]);
				
		return rutas;
	}
	
	/**
	 * Intercambia el par de ciudades de dos rutas.
	 * 
	 * @param rutas El comienzo de cada ruta.
	 */
	private GenP2[] obtenMejor(GenP2[] genes, int[] rutas) {
		
		CromosomaViajante[] cromosomas = new CromosomaViajante[4];
		for (int i = 0; i < 4; i++) {
			cromosomas[i] = new CromosomaViajante();
		}
		
		// Crea los cuatro cromosomas posibles de variar cada par
		// de ciudades entre si
		GenP2[] genes1 = genes.clone();
		intercambiaGen(genes1,rutas[0],rutas[1]);
		intercambiaGen(genes1,rutas[0]+1,rutas[1]+1);
		cromosomas[0].setGenes(genes1);

		GenP2[] genes2 = genes1.clone();
		intercambiaGen(genes2,rutas[0],rutas[0]+1);
		cromosomas[1].setGenes(genes2);
		
		GenP2[] genes3 = genes1.clone();
		intercambiaGen(genes2,rutas[1],rutas[1]+1);
		cromosomas[2].setGenes(genes3);
		
		GenP2[] genes4 = genes2.clone();
		intercambiaGen(genes2,rutas[1],rutas[1]+1);
		cromosomas[3].setGenes(genes4);
		
		CromosomaViajante elMejor = cromosomas[0];
		double mejor = cromosomas[0].evalua();
		
		for (int i = 1; i < 4; i++) {
			
			 if ( cromosomas[i].evalua() < mejor) {
				 elMejor = cromosomas[i];
			 }
		}
		
		return (GenP2[])elMejor.getGenes();
			
	}
	
	/**
	 * Intercambia los genes de 2 posiciones.
	 * 
	 * @param genes Cromosoma sobre cuyos genes cambiar.
	 * @param pos1 Posicion del gen 1.
	 * @param pos2 Posicion del gen 2.
	 */
	private void intercambiaGen(GenP2[] genes, int pos1, int pos2) {
		
		GenP2 aux = genes[pos1];
		
		genes[pos1] = genes[pos2];
		genes[pos2] = aux;
		
	}
	
	/**
	 * Realiza la mutacion de los individuos seleccionados en la poblacion 
	 * para la version 2 de la practica 2. Mutacion por inversion modificada.
	 */
	public void mutacionVersion2() {
		
		boolean mutado;
		double prob;
		Random generador = new Random();

		// para cada cromosoma de la poblacion
		for (int nPob = 0; nPob < _tamPoblacion; nPob++) {
			mutado = false;
			// para cada gen del cromosoma se prueba la mutacion
			GenP2V2[] genes = (GenP2V2[])_poblacion[nPob].getGenes();

			for (int pos = 0; pos < genes.length; pos++) {
				// se genera un numero aleatorio en [0 1)
				prob = generador.nextDouble();

				// mutan los genes con prob<prob_mut
				if (prob < _probMutacion) {

					int punto_cruce1 = 0, punto_cruce2 = 0, aux;

					do {
						// Se cruzan los individuos en funcion de dos puntos al azar
						// Donde el primero siempre es menor que el segundo
						punto_cruce1 = generador.nextInt(_poblacion[0]
						                                            .getLongitudCromosoma());
						punto_cruce2 = generador.nextInt(_poblacion[0]
						                                            .getLongitudCromosoma());
					} while (punto_cruce1 >= punto_cruce2);

					for (int i = punto_cruce1; i <= punto_cruce1
					+ ((punto_cruce2 - punto_cruce1) / 2); i++) {
						
						double prob2 = generador.nextDouble();
						if (prob2 < _probMutacion) mutaDia(genes[i]);
						
						aux = (Integer) genes[i].getGen();

						genes[i] = (GenP2V2) genes[punto_cruce2 + punto_cruce1 - i].clone();
						genes[punto_cruce2 + punto_cruce1 - i].setGen(aux);
					}

					mutado = true;
				}
			}

			// Si ha cambiado entonces lo volvemos a evaluar
			if (mutado)
				_poblacion[nPob].setAptitud(_poblacion[nPob].evalua());
		}
	}
	
	private void mutaDia(GenP2V2 gen) {
		
		Random generador = new Random();
		int nuevoDia = 0;
		
		do {
			nuevoDia = generador.nextInt(6)+1;
		} while (!CromosomaViajanteV2.diaValido((Integer)gen.getGen(),nuevoDia));
		
		gen.setDia(nuevoDia);
		
	}

	/**
	 * Asigna la calidad a los individuos de una poblacion. Calcula la
	 * adaptacion, la aptitud y el Mejor individuo.
	 */
	public void evaluarPoblacion() {

		double punt_acu = 0; // puntuacion acumulada
		double aptitud_mejor = 0;

		// Dependiendo del tipo de practica se inicializa con un valor u otro
		switch (_tipoVista) {

		case PRACTICA1:
			aptitud_mejor = 0;
			break;
		case PRACTICA2:

			// Es un problema de minimizacion y lo inicializamos con el primer
			// valor de aptitud del primer individuo de la poblacion para luego
			// ir quedandonos con los mas bajos pero siempre positivos.
			aptitud_mejor = _poblacion[0].getAptitud();
		case PRACTICA3:
			break;
		}

		double sumadaptacion = 0; // suma de la adaptacion

		// Actualizamos la adaptacion de cada cromosoma segun el tipo de
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
					_elMejorLocal = (Cromosoma) _poblacion[_posMejor].clone();
				}
				break;
			case MAXIMIZACION:
				if (_poblacion[i].getAptitud() > aptitud_mejor) {
					_posMejor = i;
					aptitud_mejor = _poblacion[i].getAptitud();
					_elMejorLocal = (Cromosoma) _poblacion[_posMejor].clone();
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
			if ((_elMejorGlobal == null)
					|| (aptitud_mejor < _elMejorGlobal.getAptitud())) {
				_elMejorGlobal = (Cromosoma) _poblacion[_posMejor].clone();
			}

			// La adaptacion del mejor debe ser 0
			if (_escaladoSimple)
				_poblacion[_posMejor].setAdaptacion(0);

			break;
		case MAXIMIZACION:
			if ((_elMejorGlobal == null)
					|| (aptitud_mejor > _elMejorGlobal.getAptitud())) {
				_elMejorGlobal = (Cromosoma) _poblacion[_posMejor].clone();
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

			// ((P-1) * Media) / (fmax - Media)
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

			switch (_tipoVista) {

			case PRACTICA1:
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

				break;

			case PRACTICA2:
				
				// Creamos el cromosoma para el tipo
				
				switch (_tipoVersion) {

				case VERSION1:
					_poblacion[j] = new CromosomaViajante();
					break;
				case VERSION2:
					_poblacion[j] = new CromosomaViajanteV2();
					break;
				}
				break;	

			case PRACTICA3:
				break;
			}

			_poblacion[j].inicializaCromosoma();
			_poblacion[j].setAptitud(_poblacion[j].evalua());
		}
	}

	/**
	 * Devuelve el mejor individuo de una poblacion.
	 * 
	 * @return El mejor individuo de una poblacion.
	 */
	public Cromosoma getElMejorGlobal() {

		return _elMejorGlobal;
	}

	/**
	 * Devuelve el mejor individuo de una poblacion.
	 * 
	 * @return El mejor individuo de una poblacion.
	 */
	public Cromosoma getElMejorLocal() {

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
