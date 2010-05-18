package logica;

import java.util.ArrayList;

import gui.tipos.TipoInicializacion;

/**
 * Clase que implementa los metodos necesarios para gestionar los individuos de la poblacion en el
 * Algoritmo Genetico Simple.
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
	
	private ArrayList<Arbol> _nodosFuncion;
	private ArrayList<Arbol> _nodosTerminales;
	
	/**
	 * Constructora por defecto de la clase Individuo.
	 */
	public Individuo() {

	}
	
	/**
	 * Constructora por defecto de la clase Individuo.
	 * 
	 * @param ifSeleccionado Indica si se selecciona el if para los individuos o no.
	 * @param tipoInicializacion Indica el tipo de inicializacion a usar.
	 * @param alturaMaxima Altura maxima del arbol.
	 * @param porcentajeCruceFuncion Porcentaje para las funciones en los cruces.
	 * @param porcentajeCruceTerminal Porcentaje para los terminales en los cruces.
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
		_arbol = Arbol.inicializaArbol(this, tipoInicializacion, alturaMaxima, ifSeleccionado);
	}
		
	/**
	 * Constructora por copia de la clase Individuo.
	 * 
	 * @param individuo Inidividuo a copiar.
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
		//TODO
	}

	/**
	 * Evalua la calidad del individuo.
	 * 
	 * @return La calidad del individuo.
	 */
	public double evalua() {
		
		return _arbol.evalua();
	}

	/**
	 * Establece la aptitud del individuo a valor aptitud.
	 * 
	 * @param aptitud Nuevo valor a establecer.
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
	 * @param arbol Nuevo valor a establecer.
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
	 * @param puntuacion Nuevo valor a establecer.
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
	 * @param puntuacionAcumulada Nuevo valor a establecer.
	 */
	public void setPuntAcumulada(double puntuacionAcumulada) {
		_puntuacionAcumulada = puntuacionAcumulada;
	}

	/**
	 * Devuelve la puntuacion acumulada del individuo.
	 * 
	 * @return La puntuacion acumulada del individuo.
	 */
	public double getPuntuacionAcumulada(){
		return _puntuacionAcumulada;
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
		
		if (this == individuo) return 0;
		
		if (_aptitud < individuo._aptitud) return -1;
		else if (_aptitud > individuo._aptitud) return 1;
		else return 1;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		Individuo individuo = (Individuo) obj;
		
		return individuo == this;
	}

	@Override
	public String toString() {
		
		return _arbol.toString();
	}

	public ArrayList<Arbol> getNodosTerminales() {
		// TODO Auto-generated method stub
		return _nodosTerminales;
	}
	
	public ArrayList<Arbol> getNodosFuncion() {
		// TODO Auto-generated method stub
		return _nodosFuncion;
	}
}
