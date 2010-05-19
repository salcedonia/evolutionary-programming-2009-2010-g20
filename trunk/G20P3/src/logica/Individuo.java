package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import logica.simbolo.Funcion;
import logica.simbolo.Simbolo;
import logica.simbolo.Terminal;
import utils.Aleatorio;

import gui.tipos.TipoInicializacion;

/**
 * Clase que implementa los metodos necesarios para gestionar los individuos de
 * la poblacion en el Algoritmo Genetico Simple.
 * 
 * @author Grupo20
 */
public class Individuo implements Comparable<Object> {

	/**
	 * Arbol del individuo.
	 */
	private Arbol _arbol;
	/**
	 * Altura maxima del arbol.
	 */
	private int _alturaMaxima;
	/**
	 * Aptitud del individuo.
	 */
	private double _aptitud;
	/**
	 * Evaluacion del individuo.
	 */
	private double _evaluacion;
	/**
	 * Puntuacion del individuo.
	 */
	private double _puntuacion;
	/**
	 * Puntuacion acumulada del individuo.
	 */
	private double _puntuacionAcumulada;
	/**
	 * Tipo de inicializacion que se realiza sobre el individuo.
	 */
	private TipoInicializacion _tipoInicializacion;
	/**
	 * Indica si se incluye el if para el individuo o no.
	 */
	private boolean _ifSeleccionado;
	/**
	 * Porcentaje para el cruce en las funciones.
	 */
	@SuppressWarnings("unused")
	private double _porcentajeCruceFuncion;
	/**
	 * Porcentaje para el cruce en los terminales.
	 */
	@SuppressWarnings("unused")
	private double _porcentajeCruceTerminal;
	/**
	 * Nodos funcion del arbol.
	 */
	private ArrayList<Arbol> _nodosFuncion;
	/**
	 * Nodos terminales del arbol.
	 */
	private ArrayList<Arbol> _nodosTerminales;
	/**
	 * Tabla de casos de prueba para la evaluacion.
	 */
	private static boolean[][] _casos;
	
	/**
	 * Constructora por defecto de la clase Individuo.
	 */
	public Individuo() {

	}

	/**
	 * Constructora por defecto de la clase Individuo.
	 * 
	 * @param ifSeleccionado
	 *            Indica si se selecciona el if para los individuos o no.
	 * @param tipoInicializacion
	 *            Indica el tipo de inicializacion a usar.
	 * @param alturaMaxima
	 *            Altura maxima del arbol.
	 * @param porcentajeCruceFuncion
	 *            Porcentaje para las funciones en los cruces.
	 * @param porcentajeCruceTerminal
	 *            Porcentaje para los terminales en los cruces.
	 * 
	 */
	public Individuo(TipoInicializacion tipoInicializacion,
			boolean ifSeleccionado, int alturaMaxima,
			double porcentajeCruceFuncion, double porcentajeCruceTerminal) {
		_aptitud = 0;
		_evaluacion = 0;
		_puntuacion = 0;
		_puntuacionAcumulada = 0;
		_tipoInicializacion = tipoInicializacion;
		_ifSeleccionado = ifSeleccionado;
		_alturaMaxima = alturaMaxima;
		_porcentajeCruceFuncion = porcentajeCruceFuncion;
		_porcentajeCruceTerminal = porcentajeCruceTerminal;
		_nodosFuncion = new ArrayList<Arbol>();
		_nodosTerminales = new ArrayList<Arbol>();
		_arbol = Arbol.inicializaArbol(tipoInicializacion, alturaMaxima,
				ifSeleccionado);
	}

	/**
	 * Constructora por copia de la clase Individuo.
	 * 
	 * @param individuo
	 *            Inidividuo a copiar.
	 */
	public Individuo(Individuo individuo) {

		_arbol = individuo._arbol;
		_alturaMaxima = individuo._alturaMaxima;
		_aptitud = individuo._aptitud;
		_evaluacion = individuo._evaluacion;
		_puntuacion = individuo._puntuacion;
		_puntuacionAcumulada = individuo._puntuacionAcumulada;
		_tipoInicializacion = individuo._tipoInicializacion;
		_ifSeleccionado = individuo._ifSeleccionado;
		_nodosFuncion = individuo._nodosFuncion;
		_nodosTerminales = individuo._nodosTerminales;
	}

	/**
	 * Metodo de cruce del individuo.
	 * 
	 * @param padre2
	 * @param hijo1
	 * @param hijo2
	 * @param alturaMax
	 * @param iter
	 * @param nmax
	 */
	public void cruce(Individuo padre2, Individuo hijo1, Individuo hijo2,
			int alturaMax, int iter, int nmax) {
		// TODO
	}

	/**
	 * Muta siguiendo el metodo terminal simple.
	 */
	public void mutacionTerminalSimple() {

		// Volvemos a calcular los nodos funcion
		_nodosTerminales.clear();
		_arbol.extraerNodosFuncion(_nodosTerminales);
		
		// Elegimos un nodo terminal al azar
		int posAMutar = Aleatorio.intRandom(_nodosTerminales.size());
		Terminal nodoAMutar = (Terminal) _nodosTerminales.get(posAMutar)
				.getSimbolo();

		// Elegimos un nuevo valor para el terminal de forma aleatoria y
		// distinto
		Simbolo nuevoValor = nodoAMutar.getTerminalAleatorio();
		nodoAMutar.setValor(nuevoValor.getValor());

	}

	/**
	 * Muta siguiendo el metodo funcional simple.
	 */
	public void mutacionFuncionalSimple() {

		// Volvemos a calcular los nodos funcion
		_nodosFuncion.clear();
		_arbol.extraerNodosFuncion(_nodosFuncion);
		
		// Elegimos un funcion nodo al azar
		int nodoSeleccionado = Aleatorio.intRandom(_nodosFuncion.size());
		Funcion nodoAMutar = (Funcion) _nodosFuncion.get(nodoSeleccionado)
				.getSimbolo();

		// Elegimos un nuevo valor de la misma aridad
		Simbolo nuevoValor = nodoAMutar.getFuncionAleatoria();
		nodoAMutar.setValor(nuevoValor.getValor());
	}

	/**
	 * Muta siguiendo el metodo de arbol.
	 */
	public void mutacionArbol() {

		// Volvemos a calcular los nodos funcion
		_nodosFuncion.clear();
		_arbol.extraerNodosFuncion(_nodosFuncion);
		
		// Elegimos al azar un nodo funcion del arbol
		int indice = Aleatorio.intRandom(_nodosFuncion.size());
		Arbol elementoAMutar = _nodosFuncion.get(indice);

		if (_arbol.getProfundidadMaxima() - elementoAMutar.getProfundidad() > 0) {
				
			// Generamos el nuevo árbol
			Arbol a = Arbol.inicializaArbol(_tipoInicializacion, _arbol.getProfundidadMaxima()
					- elementoAMutar.getProfundidad() - 1, _ifSeleccionado);

			// Colocamos sus parámetros correctamente
			a.setEsRaiz(false);
			a.setPadre(elementoAMutar.getPadre());
				
			elementoAMutar = a;
		}
	}

	/**
	 * Evalua la calidad del individuo.
	 * 
	 * @return La calidad del individuo.
	 */
	public double evalua() {

		double evaluacion = 0;
		
		// Probamos con todos los casos de prueba
		for (int i = 0; i < _casos.length; i++)
			evaluacion += _arbol.evalua(_casos[i], _casos[i][6]);
			
		return evaluacion;
	}

	/**
	 * Establece la aptitud del individuo a valor aptitud.
	 * 
	 * @param aptitud
	 *            Nuevo valor a establecer.
	 */
	public void setAptitud(double aptitud) {
		_aptitud = aptitud;
	}

	/**
	 * Devuelve la aptitud del individuo.
	 * 
	 * @return La aptitud del individuo.
	 */
	public double getAptitud() {
		return _aptitud;
	}

	/**
	 * Establece el arbol del individuo a valor arbol.
	 * 
	 * @param arbol
	 *            Nuevo valor a establecer.
	 */
	void setArbol(Arbol arbol) {
		_arbol = arbol;
	}

	/**
	 * Devuelve el arbol del individuo.
	 * 
	 * @return El arbol del individuo.
	 */
	Arbol getArbol() {
		return _arbol;
	}

	/**
	 * Establece la puntuacion a valor puntuacion.
	 * 
	 * @param puntuacion
	 *            Nuevo valor a establecer.
	 */
	public void setPuntuacion(double puntuacion) {
		_puntuacion = puntuacion;
	}

	/**
	 * Devuelve la puntuacion del individuo.
	 * 
	 * @return La puntuacion del individuo.
	 */
	public double getPuntuacion() {
		return _puntuacion;
	}

	/**
	 * Establece la puntuacion acumulada a valor puntuacionAcumulada.
	 * 
	 * @param puntuacionAcumulada
	 *            Nuevo valor a establecer.
	 */
	public void setPuntAcumulada(double puntuacionAcumulada) {
		_puntuacionAcumulada = puntuacionAcumulada;
	}

	/**
	 * Devuelve la puntuacion acumulada del individuo.
	 * 
	 * @return La puntuacion acumulada del individuo.
	 */
	public double getPuntuacionAcumulada() {
		return _puntuacionAcumulada;
	}

	/**
	 * Devuelve la lista de nodos terminales del arbol.
	 * 
	 * @return La lista de nodos terminales del arbol.
	 */
	public ArrayList<Arbol> getNodosTerminales() {
		return _nodosTerminales;
	}

	/**
	 * Devuelve la lista de nodos funcion del arbol.
	 * 
	 * @return La lista de nodos funcion del arbol.
	 */
	public ArrayList<Arbol> getNodosFuncion() {
		return _nodosFuncion;
	}

	/**
	 * Carga los casos de prueba desde un fichero con formato de "1" y "0"
	 * traduciendolo a false y true de forma estatica. El fichero viene separado
	 * por comas de tal forma que siga el orden "A1,A0,D3,D2,D1,D0,S".
	 */
	public static void cargaCasosPrueba() {

		BufferedReader br;
		try {
			br = new BufferedReader(
					new FileReader("ficheros/CasosDePrueba.txt"));

			String linea = "";
			int numLinea = 0;

			_casos = new boolean[Integer.parseInt(br.readLine())][];

			while ((linea = br.readLine()) != null) {

				String[] caso = linea.split(",");

				_casos[numLinea] = new boolean[caso.length];

				for (int i = 0; i < caso.length; i++)
					_casos[numLinea][i] = caso[i].matches("1");

				numLinea++;
			}

		} catch (Exception e) {

			System.err.println("Error al cargar los casos de prueba del fichero");
		}
	}
	
	@Override
	public Object clone() {
		Individuo individuo = new Individuo();

		individuo._arbol = _arbol.clone();
		individuo._alturaMaxima = _alturaMaxima;
		individuo._aptitud = _aptitud;
		individuo._evaluacion = _evaluacion;
		individuo._puntuacion = _puntuacion;
		individuo._puntuacionAcumulada = _puntuacionAcumulada;
		individuo._tipoInicializacion = _tipoInicializacion;
		individuo._ifSeleccionado = _ifSeleccionado;

		return individuo;
	}

	@Override
	public int compareTo(Object obj) {
		Individuo individuo = (Individuo) obj;

		if (this == individuo)
			return 0;

		if (_aptitud < individuo._aptitud)
			return -1;
		else if (_aptitud > individuo._aptitud)
			return 1;
		else
			return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Individuo individuo = (Individuo) obj;

		return individuo == this;
	}

	@Override
	public String toString() {

		return _arbol.toString();
	}
}
