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

import utils.ListaOrdenada;

import cromosoma.Cromosoma;
import cromosoma.practica1.CromosomaFuncion1;
import cromosoma.practica1.CromosomaFuncion2;
import cromosoma.practica1.CromosomaFuncion3;
import cromosoma.practica1.CromosomaFuncion4;
import cromosoma.practica1.CromosomaFuncion5;
import cromosoma.practica1.GenP1;
import cromosoma.practica2.CromosomaViajante;
import cromosoma.practica2.GenP2;

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
	 */
	public AG(int numMaxGeneraciones, int tamPoblacion, double probCruce,
			double probMutacion, double tolerancia, int valorN,
			boolean elitismo, boolean escaladoSimple,
			TipoCromosoma tipoCromosoma, TipoProblema tipoProblema,
			TipoVersion tipoVersion, TipoSeleccion tipoSeleccion,
			TipoCruce tipoCruce, TipoMutacion tipoMutacion,
			TipoVista tipoVista, double tamElite, int P, int numCiudadesMutInsercion) {

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

		// Por hacer
	}

	/**
	 * Metodo de seleccion por Ranking. Se seleccionan los cromosomas
	 * supervivientes para la reproduccion.
	 */
	private void seleccionRanking() {

		// Por hacer
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
			// Se generan tam_pob n�meros aleatorios en [0 1)
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
				crucePMX(padre, madre, punto_cruce1, punto_cruce2);
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
			hijo1[i] = new GenP1(( (GenP1) padre.getGen(i) ).getLongitudGen());
			hijo2[i] = new GenP1(( (GenP1) madre.getGen(i) ).getLongitudGen());
		}

		int i = 0, j = 0;

		while ((nBit != punto_cruce) && (nBit < padre.getLongitudCromosoma())) {

			hijo1[i].setBit(j, ( (GenP1) padre.getGen(i) ).getBit(j));
			hijo2[i].setBit(j, ( (GenP1) madre.getGen(i) ).getBit(j));
			nBit++;
			j++;
			if (j >= ( (GenP1) padre.getGen(i) ).getLongitudGen() ) {
				i++;
				j = 0;
			}
		}

		while ((nBit < padre.getLongitudCromosoma())) {

			hijo1[i].setBit(j, ( (GenP1) madre.getGen(i) ).getBit(j));
			hijo2[i].setBit(j, ( (GenP1) padre.getGen(i) ).getBit(j));
			nBit++;
			j++;
			if (j >= ( (GenP1) padre.getGen(i) ).getLongitudGen() ) {
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

		boolean encontrado;
		int aux, j;

		// Se intercambian los posiciones dentro del intervalo
		for (int i = punto_cruce1; i <= punto_cruce2; i++) {

			GenP2 genPadre = (GenP2) padre.getGen(i);
			GenP2 genMadre = (GenP2) madre.getGen(i);

			aux = (Integer) genPadre.getGen();
			genPadre.setGen(genMadre.getGen());
			genMadre.setGen(aux);

		}
		
		for (int i = 0; i < padre.getLongitudCromosoma(); i++) {

			// Se eliminan los repetidos fuera del intervalo
			if (i < punto_cruce1 || i > punto_cruce2) {
				encontrado = false;
				j = punto_cruce1;

				// Buscamos los repetidos por el cruce
				while (!encontrado && j <= punto_cruce2) {
					
					GenP2 genPadreI = (GenP2) padre.getGen(i);
					GenP2 genMadreI = (GenP2) madre.getGen(i);
					
					GenP2 genPadreJ = (GenP2) padre.getGen(j);
					GenP2 genMadreJ = (GenP2) madre.getGen(j);

					if (( (Integer)genPadreI.getGen() ).equals( (Integer)genPadreJ.getGen()) ) {
						genPadreI.setGen(genMadreJ.getGen());
						encontrado = true;
					}

					if ( ( (Integer)genMadreI.getGen()).equals((Integer)genMadreJ.getGen()) ) {
						genMadreI.setGen(genPadreJ.getGen());
						encontrado = true;
					}

					if (encontrado)
						i--;
					j++;
				}
			}
		}
		

		// se evaluan y sustituyen a los padres
		padre.setAptitud(padre.evalua());
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

		boolean encontrado, libre;
		int aux, j1, j2, k;

		// se inicializan los hijos	copiando los valores en los dos hijos	
		GenP2[] hijo1 = new GenP2[padre.getNumGenes()];
		GenP2[] hijo2 = new GenP2[madre.getNumGenes()];
		for (int i = 0; i < padre.getNumGenes(); i++) {
			hijo1[i] =  (GenP2) ((GenP2) padre.getGen(i)).clone();
			hijo2[i] = (GenP2) ((GenP2) madre.getGen(i)).clone();
		}

		// Intercambiamos los elementos del intervalo
			for (int pos = punto_cruce1; pos <= punto_cruce2; pos++) {

				GenP2 genPadre = (GenP2) padre.getGen(pos);
				GenP2 genMadre = (GenP2) madre.getGen(pos);

				aux = (Integer) genPadre.getGen();
				genPadre.setGen(genMadre.getGen());
				genMadre.setGen(aux);
			}

		j1 = (punto_cruce2 + 1) % padre.getLongitudCromosoma();
		j2 = (punto_cruce2 + 1) % madre.getLongitudCromosoma();

		for (int pos = (punto_cruce2 + 1) % padre.getLongitudCromosoma(); pos < punto_cruce1
					|| pos > punto_cruce2; pos = (pos + 1)
					% padre.getLongitudCromosoma()) {

				encontrado = false;
				while (!encontrado) {

					libre = true;
					k = punto_cruce1;
					while (libre && k <= punto_cruce2) {
						
						if ((Integer)hijo1[j1].getGen() == (Integer)hijo2[k].getGen())
							libre = false;
						k++;
					}

					if (libre) {
						( (GenP2) padre.getGen(pos) ).setGen(hijo1[j1].getGen());
						encontrado = true;
					}

					j1 = (j1 + 1) % padre.getLongitudCromosoma();
				}

				encontrado = false;
				while (!encontrado) {

					libre = true;
					k = punto_cruce1;
					while (libre && k <= punto_cruce2) {
						if ( (Integer)hijo2[j2].getGen() == (Integer)hijo1[k].getGen())
							libre = false;
						k++;
					}

					if (libre) {
						( (GenP2) madre.getGen(pos) ).setGen(hijo2[j2].getGen());
						encontrado = true;
					}

					j2 = (j2 + 1) % madre.getLongitudCromosoma();
				}
			}

		// se evaluan y sustituyen a los padres
		padre.setAptitud(padre.evalua());
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
	 *            El 2� punto de cruce para cruzar los cromosomas.
	 */
	private void cruceVarianteOX(Cromosoma padre, Cromosoma madre,
			int punto_cruce1, int punto_cruce2) {

		// se inicializan los hijos	copiando los valores en los dos hijos	
		GenP2[] hijo1 = new GenP2[padre.getNumGenes()];
		GenP2[] hijo2 = new GenP2[madre.getNumGenes()];
		for (int i = 0; i < padre.getNumGenes(); i++) {
			hijo1[i] =  (GenP2) ((GenP2) padre.getGen(i)).clone();
			hijo2[i] = (GenP2) ((GenP2) madre.getGen(i)).clone();
		}

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
	 *            El 2� punto de cruce para cruzar los cromosomas.
	 */
	private void cruceCiclosCX(Cromosoma padre, Cromosoma madre,
			int punto_cruce1, int punto_cruce2) {

//		boolean encontrado = false;
//		int posSig, pos;
//		int[][] hijo1 = new int[padre.getNumGenes()][padre
//				.getLongitudCromosoma()];
//		int[][] hijo2 = new int[padre.getNumGenes()][padre
//				.getLongitudCromosoma()];
//
//		for (int nGen = 0; nGen < padre.getNumGenes(); nGen++) {
//			for (int i = 0; i < hijo1.length; i++) {
//				hijo1[nGen][i] = -1;
//				hijo2[nGen][i] = -1;
//			}
//		}
//
//		for (int nGen = 0; nGen < padre.getNumGenes(); nGen++) {
//
//			hijo1[nGen][0] = padre.getGenes()[nGen][0];
//			posSig = hijo2[nGen][0];
//
//			while (posSig != hijo1[nGen][0]) {
//				pos = 0;
//				while (!encontrado) {
//					
//					if (padre.getGenes()[nGen][pos] == posSig) {
//						hijo1[nGen][pos] = padre.getGenes()[nGen][pos];
//						posSig = madre.getGenes()[nGen][pos];
//						encontrado = true;
//					}
//					pos++;
//				}
//			}
//
//			hijo2[nGen][0] = madre.getGenes()[nGen][0];
//			posSig = hijo1[nGen][0];
//			while (posSig != hijo2[nGen][0]) {
//				pos = 0;
//				while (!encontrado) {
//					if (madre.getGenes()[nGen][pos] == posSig) {
//						hijo2[nGen][pos] = madre.getGenes()[nGen][pos];
//						posSig = padre.getGenes()[nGen][pos];
//						encontrado = true;
//					}
//					pos++;
//				}
//			}
//
//			for (int j = 0; j < hijo1.length; j++) {
//				if (hijo1[nGen][j] == -1)
//					hijo1[nGen][j] = madre.getGenes()[nGen][j];
//				if (hijo2[nGen][j] == -1)
//					hijo2[nGen][j] = padre.getGenes()[nGen][j];
//			}
//		}
//
//		// se evaluan y sustituyen a los padres
//		padre.setGenes(hijo1);
//		padre.setAptitud(padre.evalua());
//		madre.setGenes(hijo2);
//		madre.setAptitud(madre.evalua());
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
	 *            El 2� punto de cruce para cruzar los cromosomas.
	 */
	private void cruceERX(Cromosoma padre, Cromosoma madre, int punto_cruce1,
			int punto_cruce2) {

		//TODO: Por hacer
		
		// se inicializan los hijos	copiando los valores en los dos hijos	
		GenP2[] hijo1 = new GenP2[padre.getNumGenes()];
		GenP2[] hijo2 = new GenP2[madre.getNumGenes()];
		for (int i = 0; i < padre.getNumGenes(); i++) {
			hijo1[i] =  (GenP2) ((GenP2) padre.getGen(i)).clone();
			hijo2[i] = (GenP2) ((GenP2) madre.getGen(i)).clone();
		}

		// se evaluan y sustituyen a los padres
		padre.setGenes(hijo1);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2);
		madre.setAptitud(madre.evalua());
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
	 *            El 2� punto de cruce para cruzar los cromosomas.
	 */
	private void cruceCodificacionOrdinal(Cromosoma padre, Cromosoma madre,
			int punto_cruce1, int punto_cruce2) {

		//TODO: Por hacer
		
		// se inicializan los hijos	copiando los valores en los dos hijos	
		GenP2[] hijo1 = new GenP2[padre.getNumGenes()];
		GenP2[] hijo2 = new GenP2[madre.getNumGenes()];
		for (int i = 0; i < padre.getNumGenes(); i++) {
			hijo1[i] =  (GenP2) ((GenP2) padre.getGen(i)).clone();
			hijo2[i] = (GenP2) ((GenP2) madre.getGen(i)).clone();
		}

		// se evaluan y sustituyen a los padres
		padre.setGenes(hijo1);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2);
		madre.setAptitud(madre.evalua());
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
	 *            El 2� punto de cruce para cruzar los cromosomas.
	 */
	private void crucePropio(Cromosoma padre, Cromosoma madre,
			int punto_cruce1, int punto_cruce2) {

		//TODO: Por hacer
		
		// se inicializan los hijos	copiando los valores en los dos hijos	
		GenP2[] hijo1 = new GenP2[padre.getNumGenes()];
		GenP2[] hijo2 = new GenP2[madre.getNumGenes()];
		for (int i = 0; i < padre.getNumGenes(); i++) {
			hijo1[i] =  (GenP2) ((GenP2) padre.getGen(i)).clone();
			hijo2[i] = (GenP2) ((GenP2) madre.getGen(i)).clone();
		}

		// se evaluan y sustituyen a los padres
		padre.setGenes(hijo1);
		padre.setAptitud(padre.evalua());
		madre.setGenes(hijo2);
		madre.setAptitud(madre.evalua());
	}

	
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
				mutacionInsercion();
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
			GenP1[] genes = (GenP1[])_poblacion[i].getGenes();
			for (int j = 0; j < genes.length; j++) {
				for (int k = 0; k < genes[j].getLongitudGen(); k++) {
					// se genera un numero aleatorio en [0 1)
					prob = generador.nextDouble();

					// mutan los genes con prob<prob_mut
					if (prob < _probMutacion) {

						if (!genes[j].getBit(k))
							genes[j].setBit(k,true);
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
			GenP2[] genes = (GenP2[])_poblacion[nPob].getGenes();
			
			for (int pos = 0; pos < genes.length; pos++) {
				// se genera un numero aleatorio en [0 1)
				prob = generador.nextDouble();

				// mutan los genes con prob<prob_mut
				if (prob < _probMutacion) {

					ArrayList<Integer> posiciones = new ArrayList<Integer>();
					ArrayList<Integer> posCiudades = new ArrayList<Integer>();

					for (int i = 0; i < _numCiudadesMutInsercion; i++) {
						posiciones.add(generador.nextInt(_poblacion[0].getLongitudCromosoma()-1));
						posCiudades.add(generador.nextInt(_poblacion[0].getLongitudCromosoma()-1));
					}

					int ciudadReemplazada;
					int j = 0;
					int aux;
					for (int i = 0; i < posiciones.size(); i++) {
						ciudadReemplazada = (Integer) genes[posiciones.get(i)].getGen();
						genes[posiciones.get(i)] = (GenP2) genes[posCiudades.get(i)].clone();
						j = posiciones.get(i) + 1;
						while ( !( (Integer) genes[j].getGen()).equals((Integer) genes[posCiudades.get(i)].getGen()) ) {
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
			GenP2[] genes = (GenP2[])_poblacion[nPob].getGenes();

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
					genes[pos1] = (GenP2) genes[pos2].clone();
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
	 * Realiza la mutacion de los individuos seleccionados en la poblacion seg�n
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
			GenP2[] genes = (GenP2[])_poblacion[nPob].getGenes();

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
						
						aux = (Integer) genes[i].getGen();
						genes[i] = (GenP2) genes[punto_cruce2 + punto_cruce1 - i].clone();
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

		//TODO: Por Hacer
		
		boolean mutado;
		double prob;
		Random generador = new Random();

		// para cada cromosoma de la poblacion
		for (int nPob = 0; nPob < _tamPoblacion; nPob++) {
			mutado = false;
			// para cada gen del cromosoma se prueba la mutacion
			GenP2[] genes = (GenP2[])_poblacion[nPob].getGenes();
			
			for (int nGen = 0; nGen < genes.length; nGen++) {
				for (int pos = 0; pos < genes.length; pos++) {
					// se genera un numero aleatorio en [0 1)
					prob = generador.nextDouble();

					// mutan los genes con prob<prob_mut
					if (prob < _probMutacion) {
						
						// Hacer lo que se nos ocurra
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
				_poblacion[j] = new CromosomaViajante();

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
