package logica;

import java.util.ArrayList;

import logica.simbolo.Simbolo;

import utils.Aleatorio;

/**
 * Clase que implementa los metodos necesarios para la gestion del arbol del
 * individuo.
 * 
 * @author Grupo20.
 */
public class Arbol {

	/**
	 * Nombre del nodo.
	 */
	private String _nombre;
	/**
	 * Hijo derecho del nodo.
	 */
	private Arbol _hijoDerecho;
	/**
	 * Hijo izquierdo del nodo.
	 */
	private Arbol _hijoIzquierdo;
	/**
	 * Padre del nodo.
	 */
	private Arbol _padre;
	/**
	 * Profundidad del arbol.
	 */
	private int _profundidad;
	/**
	 * Numero de nodos.
	 */
	private int _numNodos;
	/**
	 * Indica si es hoja.
	 */
	private boolean _esHoja;
	/**
	 * Indica si es el nodo raiz del arbol o no.
	 */
	private boolean _esRaiz;
	/**
	 * Indica si es hijo izquierdo o no.
	 */
	private boolean _esHijoIzquierdo;
	/**
	 * Profundidad total del arbol.
	 */
	private int _profundidadTotal;
	/**
	 * Simbolo del arbol.
	 */
	private Simbolo _simbolo;

	/**
	 * Constructora por defecto de la clase Arbol.
	 */
	public Arbol() {
		_nombre = null;
		_hijoDerecho = null;
		_hijoIzquierdo = null;
		_profundidad = 0;
		_numNodos = 1;
		_esHoja = false;
		_esRaiz = true;
		_esHijoIzquierdo = false;
	}

	/**
	 * Constructora por valor de la clase Arbol.
	 * 
	 * @param conjuntoFunciones
	 *            Conjunto de funciones disponibles.
	 * @param conjuntoTerminos
	 *            Conjunto de terminos disponibles.
	 * @param AlturaMaxima
	 *            Altura maxima.
	 * @param profundidad
	 *            Prundidad maxima del arbol.
	 * @param padre
	 *            Padre del arbol.
	 * @param esHijoIzquierdo
	 *            Indica si es hijo izquierdo o no.
	 * @param esRaiz
	 *            Indica si es raiz o no.
	 */
	public Arbol(ArrayList<String> conjuntoFunciones,
			ArrayList<String> conjuntoTerminos, int AlturaMaxima,
			int profundidad, Arbol padre, boolean esHijoIzquierdo,
			boolean esRaiz) {

		int nuevaProf = profundidad + 1;
		boolean random = Aleatorio.boolRandom();

		_profundidad = profundidad;
		_padre = padre;
		_esHijoIzquierdo = esHijoIzquierdo;
		_esRaiz = esRaiz;

		if (_padre == null)
			_esRaiz = true;

		_numNodos = 1;

		if ((random) || (_profundidad + 1 == AlturaMaxima)) {

			int intRand = Aleatorio.intRandom(0, conjuntoTerminos.size() - 1);
			_nombre = conjuntoTerminos.get(intRand);
			_esHoja = true;
		} else {

			int intRand2 = Aleatorio.intRandom(0, conjuntoFunciones.size() - 1);
			_nombre = conjuntoFunciones.get(intRand2);
			_hijoIzquierdo = new Arbol(conjuntoFunciones, conjuntoTerminos,
					AlturaMaxima, nuevaProf, this, true, false);
			_numNodos = _numNodos + _hijoIzquierdo.getNumNodos();
			_hijoDerecho = new Arbol(conjuntoFunciones, conjuntoTerminos,
					AlturaMaxima, nuevaProf, this, false, false);
			_numNodos = _numNodos + _hijoDerecho.getNumNodos();
			_esHoja = false;
		}
	}

	/**
	 * Constructora por copia de la clase Arbol.
	 * 
	 * @param arbol
	 * @param padre
	 */
	public Arbol(Arbol arbol, Arbol padre) {

		_nombre = arbol.getNombre();
		// posSimbolo = arbol->leerPosSimbolo();
		_esHoja = arbol.esHoja();

		if (padre == null)
			_esRaiz = true;
		else
			_esRaiz = false;

		_numNodos = arbol.getNumNodos();
		_profundidad = arbol.getProfundidad();
		_padre = padre;

		_esHijoIzquierdo = arbol.esHijoIzquierdo();
		if (!_esHoja) {

			_hijoIzquierdo = new Arbol(arbol._hijoIzquierdo, this);

/*			if ((_nombre != "sqrt") && (_nombre != "log"))
				_hijoDerecho = new Arbol(arbol._hijoDerecho, this);
			else
				_hijoDerecho = null;
	*/	}
	}

	/**
	 * Devuelve un nodo aleatorio del arbol.
	 * 
	 * @return Un nodo aleatorio del arbol.
	 */
	public Arbol nodoAleatorio() {

		return buscarNodo(Aleatorio.intRandom(1, getNumNodos()));
	}

	/**
	 * Devuelve el nodo n-esimo del arbol.
	 * 
	 * @return El nodo n-esimo del arbol.
	 */
	private Arbol buscarNodo(int n) {

		int medio;

		if (esHoja() && (n == 1))
			return this;
		else if (!esHoja()) {

			medio = (_hijoIzquierdo.getNumNodos() + 1);
			if (n < medio)
				return _hijoIzquierdo.buscarNodo(n);
			else if (n == medio)
				return this;
			else
				return _hijoDerecho.buscarNodo(n - medio);
		} else {
			return null;
		}
	}
	
	/**
	 * Devuelve el nombre del nodo.
	 * 
	 * @return El nombre del nodo.
	 */
	public String getNombre() {
		return _nombre;
	}

	/**
	 * Establece el nombre del nodo a valor nombre.
	 * 
	 * @param nombre
	 *            Nuevo valor a establecer.
	 */
	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	/**
	 * Devuelve el Hijo Izquierdo del arbol.
	 * 
	 * @return El Hijo Izquierdo del arbol.
	 */
	public Arbol getHijoIzquierdo() {
		return _hijoIzquierdo;
	}

	/**
	 * Establece el hijo izquierdo a valor nodo.
	 * 
	 * @param nodo
	 *            Nuevo valor a establecer.
	 */
	public void setHijoIzquierdo(Arbol nodo) {
		_hijoIzquierdo = nodo;
	}

	/**
	 * Devuelve el Hijo Derecho del arbol.
	 * 
	 * @return El Hijo Derecho del arbol.
	 */
	public Arbol getHijoDerecho() {
		return _hijoDerecho;
	}

	/**
	 * Establece el hijo derecho a valor nodo.
	 * 
	 * @param nodo
	 *            Nuevo valor a establecer.
	 */
	public void setHijoDerecho(Arbol nodo) {
		_hijoDerecho = nodo;
	}

	/**
	 * Devuelve la profundidad total del arbol.
	 * 
	 * @return La profundidad total del arbol.
	 */
	public int getProfundidadTotal() {
		return _profundidadTotal;
	}

	/**
	 * Establece la profundidad total del arbol a valor profundidadTotal.
	 * 
	 * @param profundidadTotal
	 *            Nuevo valor a establecer.
	 */
	public void setProfundidadTotal(int profundidadTotal) {
		_profundidadTotal = profundidadTotal;
	}

	/**
	 * Devuelve si es un nodo raiz o no.
	 * 
	 * @return Verdadero si es un nodo raiz y falso en caso contrario.
	 */
	public boolean esRaiz() {
		return _esRaiz;
	}

	/**
	 * Establece esRaiz a valor esRaiz.
	 * 
	 * @param esRaiz
	 *            Nuevo valor a establecer.
	 */
	public void setEsRaiz(boolean esRaiz) {
		_esRaiz = esRaiz;
	}

	/**
	 * Devuelve si es un nodo hoja o no.
	 * 
	 * @return Verdadero si es un nodo hoja y falso en caso contrario.
	 */
	public boolean esHoja() {
		return _esHoja;
	}

	/**
	 * Establece esHoja a valor esHoja.
	 * 
	 * @param esHoja
	 *            Nuevo valor a establecer.
	 */
	public void setEsHoja(boolean esHoja) {
		_esHoja = esHoja;
	}

	/**
	 * Devuelve si es hijo izquierdo de un nodo o no.
	 * 
	 * @return Verdadero si es hijo izquierdo de un nodo o no y falso en caso
	 *         contrario.
	 */
	public boolean esHijoIzquierdo() {
		return _esHijoIzquierdo;
	}

	/**
	 * Establece esHijoIzquierdo a valor esHijoIzquierdo.
	 * 
	 * @param esHijoIzquierdo
	 *            Nuevo valor a establecer.
	 */
	public void setEsHijoIzquierdo(boolean esHijoIzquierdo) {
		_esHijoIzquierdo = esHijoIzquierdo;
	}

	/**
	 * Devuelve el nodo padre del arbol.
	 * 
	 * @return El nodo padre del arbol.
	 */
	public Arbol getPadre() {
		return _padre;
	}

	/**
	 * Establece el nodo padre a valor padre.
	 * 
	 * @param padre
	 *            Nuevo valor a establecer.
	 */
	public void setPadre(Arbol padre) {
		_padre = padre;
	}

	/**
	 * Actualiza el arbol con la nueva profundidad.
	 * 
	 * @param profundidad
	 *            Nueva profundidad a establecer.
	 */
	public void getProfundidad(int profundidad) {
		_profundidad = profundidad;
	}

	/**
	 * Devuelve la profundidad del arbol.
	 * 
	 * @return La profundidad del arbol.
	 */
	public int getProfundidad() {
		return _profundidad;
	}

	/**
	 * Devuelve el numero de nodos del arbol.
	 * 
	 * @return El numero de nodos del arbol.
	 */
	public int getNumNodos() {
		return _numNodos;
	}

	/**
	 * Establece el numero de nodos a valor numNodos.
	 * 
	 * @param numNodos
	 *            Nuevo valor a establecer.
	 */
	public void setNumNodos(int numNodos) {
		_numNodos = numNodos;
	}

	/**
	 * Devuelve la altura del arbol.
	 * 
	 * @return La altura del arbol.
	 */
	public int altura() {

		int altura = 0;
		int alturaHijoIzquierdo = 0;
		int alturaHijoDerecho = 0;

		if (!esHoja()) {

			alturaHijoIzquierdo = getHijoIzquierdo().altura();

			if (!esHijoIzquierdo())
				alturaHijoDerecho = getHijoDerecho().altura();

			if (alturaHijoIzquierdo > alturaHijoDerecho)
				altura = 1 + alturaHijoIzquierdo;
			else
				altura = 1 + alturaHijoDerecho;
		} else
			altura = 1;

		return altura;
	}

	@Override
	protected Object clone() {
		// TODO Auto-generated method stub
		return new Arbol();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {

		String cadena;

		cadena = _nombre;

		if (!esHoja()) {

			cadena = cadena + "(" + _hijoIzquierdo.toString();
			if (_hijoDerecho != null)
				cadena = cadena + "," + _hijoDerecho.toString();
			cadena = cadena + ")";
		}
		return cadena;

	}

	public void inicializaCompleta(String[] conjuntoFunciones, String[] conjuntoTerminos) {
		// TODO Auto-generated method stub
		
	}

	public void inicializaRampedAndHalf(String[] conjuntoFunciones, String[] conjuntoTerminos) {
		// TODO Auto-generated method stub
		
	}

	public void inicializaCreciente(String[] conjuntoFunciones, String[] conjuntoTerminos) {
		// TODO Auto-generated method stub
		
	}
}
