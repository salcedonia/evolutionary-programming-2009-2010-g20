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
	 * Conjunto de terminos del individuo.
	 */
	private final String[] _conjuntoTerminos = { "A0", "A1", "D0", "D1", "D2", "D3" };
	/**
	 * Conjunto de funciones del individuo.
	 */
	private final String[] _conjuntoFunciones = { "AND", "OR", "NOT", "IF" };
	/**
	 * Nodos funcion que tiene el Arbol del inidividuo.
	 */
	private ArrayList<Arbol> _nodosFuncion;
	/**
	 * Nodos terminales que tiene el Arbol del individuo.
	 */
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
	 */
	public Individuo(TipoInicializacion tipoInicializacion, boolean ifSeleccionado) {
		
		_arbol = new Arbol();
		_alturaMaxima = 10;
		_aptitud = 0;
		_evaluacion = 0;
		_puntuacion = 0;
		_puntuacionAcumulada = 0;
		_nodosFuncion = new ArrayList<Arbol>();
		_nodosTerminales = new ArrayList<Arbol>();
		_tipoInicializacion = tipoInicializacion;
		_ifSeleccionado = ifSeleccionado;
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
		_nodosFuncion = individuo._nodosFuncion;
		_nodosTerminales = individuo._nodosTerminales;
		_tipoInicializacion = individuo._tipoInicializacion;
		_ifSeleccionado = individuo._ifSeleccionado;
	}

	// ---------------- INICIALIZACIONES -------------------//
	
	/**
	 * Inicializa el individuo con el metodo correspondiente.
	 */
	public void inicializaIndividuo() {
		switch (_tipoInicializacion) {

		case CRECIENTE:
			inicializaCreciente();
			break;
		case COMPLETA:
			inicializaCompleta();
			break;
		case RAMPED_AND_HALF:
			inicializaRampedAndHalf();
			break;
		}
	}
	
	/**
	 * Metodo de inicializacion de Ramped And Half.
	 */
	private void inicializaRampedAndHalf() {
		_arbol.inicializaRampedAndHalf(_conjuntoFunciones, _conjuntoTerminos);
	}

	/**
	 * Metodo de inicializacion Completo.
	 */
	private void inicializaCompleta() {
		_arbol.inicializaCompleta(_conjuntoFunciones, _conjuntoTerminos);
	}

	/**
	 * Metodo de inicializacion Creciente.
	 */
	private void inicializaCreciente() {
		_arbol.inicializaCreciente(_conjuntoFunciones, _conjuntoTerminos);
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
		
		Arbol arbol1, arbol2;
		boolean esHi1 = false, esHi2 = false, raiz1 = false, raiz2 = false;
		Arbol nodo1 = null;
		Arbol nodo2 = null;
		Arbol nodo_aux1 = null;
		Arbol pater1 = null;
		Arbol pater2 = null;
		boolean stop = false;
		int nAlt1, nAlt2;
		hijo1 = new Individuo(this);
		hijo2 = new Individuo(padre2);
		arbol1 = this.getArbol();
		arbol2 = padre2.getArbol();

		while (!stop) {
			nodo1 = arbol1.nodoAleatorio();
			nodo2 = arbol2.nodoAleatorio();
			nodo_aux1 = nodo1;
			raiz1 = nodo1.esRaiz();
			raiz2 = nodo2.esRaiz();
			esHi1 = nodo1.esHijoIzquierdo();
			esHi2 = nodo2.esHijoIzquierdo();
			if (!raiz1) {
				pater1 = nodo1.getPadre();
				nAlt1 = pater1.getProfundidad() + 1;
			} else
				nAlt1 = 0;
			if (!raiz2) {
				pater2 = nodo2.getPadre();
				nAlt2 = pater2.getProfundidad() + 1;
			} else
				nAlt2 = 0;
			nAlt1 = nAlt1 + nodo2.altura();
			nAlt2 = nAlt2 + nodo1.altura();

			stop = ((nAlt1 <= alturaMax) && (nAlt2 <= alturaMax));
		}
		if (raiz1)
			arbol1 = new Arbol(nodo2, null);
		else {
			if (esHi1)
				pater1.setHijoIzquierdo(nodo2);
			else
				pater1.setHijoDerecho(nodo2);
		}

		if (raiz2)
			arbol2 = new Arbol(nodo_aux1, null);
		else {
			if (esHi2)
				pater2.setHijoIzquierdo(nodo_aux1);
			else
				pater2.setHijoDerecho(nodo_aux1);
		}

		arbol1.getProfundidad(arbol1.getProfundidad());
		arbol2.getProfundidad(arbol2.getProfundidad());
		hijo1.setArbol(arbol1);
		hijo2.setArbol(arbol2);
		hijo1.setAptitud(2);
		hijo2.setAptitud(2);
	}

	/**
	 * Evalua la calidad del individuo.
	 * 
	 * @return La calidad del individuo.
	 */
	public double evalua() {
		return 0;
	}

	public double f() {
		// TODO Auto-generated method stub
		return 0;
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
		
		individuo._arbol = (Arbol) _arbol.clone();
		individuo._alturaMaxima = _alturaMaxima;
		individuo._aptitud = _aptitud;
		individuo._evaluacion = _evaluacion;
		individuo._puntuacion = _puntuacion;
		individuo._puntuacionAcumulada = _puntuacionAcumulada;
		
		individuo._nodosFuncion = new ArrayList<Arbol>();
		for(int i=0; i<_nodosFuncion.size(); i++)
			individuo._nodosFuncion.set(i, (Arbol)_nodosFuncion.get(i).clone());
		
		individuo._nodosTerminales = new ArrayList<Arbol>();
		for(int i=0; i<_nodosTerminales.size(); i++)
			individuo._nodosTerminales.set(i, (Arbol)_nodosTerminales.get(i).clone());
		
		individuo._tipoInicializacion = _tipoInicializacion;
		individuo._ifSeleccionado = _ifSeleccionado;
		
		return individuo;
	}
	
	@Override
	public int compareTo(Object obj) {
		Individuo individuo = (Individuo) obj;
		
		if (this == individuo) return 0;
		
		if (this._aptitud < individuo._aptitud) return -1;
		else if (this._aptitud > individuo._aptitud) return 1;
		else return 1;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		Individuo individuo = (Individuo) obj;
		
		return individuo == this;
	}
}
