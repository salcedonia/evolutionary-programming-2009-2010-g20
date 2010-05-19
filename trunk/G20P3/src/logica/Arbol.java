package logica;

import java.util.ArrayList;

import gui.tipos.TipoInicializacion;

import logica.simbolo.Funcion;
import logica.simbolo.Simbolo;
import logica.simbolo.Terminal;
import logica.simbolo.TipoSimbolo;

import utils.Aleatorio;

/**
 * Clase que implementa los metodos necesarios para la gestion del arbol del
 * individuo.
 * 
 * @author Grupo20.
 */
public class Arbol {

	/**
	 * Simbolo del arbol.
	 */
	private Simbolo _simbolo;
	/**
	 * Array de hijos del arbol.
	 */
	private ArrayList<Arbol> _hijos;
	/**
	 * Padre del nodo.
	 */
	private Arbol _padre;
	/**
	 * Profundidad actual del arbol.
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
	 * Profundidad total del arbol.
	 */
	private int _profundidadMaxima;	

	/**
	 * Constructora por defecto de la clase Arbol.
	 */
	public Arbol() {

		_profundidad = 0;
		_numNodos = 1;
		_esHoja = false;
		_esRaiz = true;
		_simbolo = null;
		_hijos = new ArrayList<Arbol>();
	}

	/**
	 * Constructora por valor de la clase Arbol.
	 * 
	 * @param padre
	 *            Padre del arbol.
	 * @param esHijoIzquierdo
	 *            Indica si es hijo izquierdo o no.
	 * @param profundidad
	 *            Profundidad del arbol.
	 * @param profundidadMaxima
	 *            Profundidad maxima del arbol.
	 */
	public Arbol(Arbol padre, int profundidad, int profundidadMaxima) {

		_padre = padre;
		if (padre == null)
			_esRaiz = true;
		else
			_esRaiz = false;
		_profundidad = profundidad;
		_profundidadMaxima = profundidadMaxima;
		_hijos = new ArrayList<Arbol>();
		_numNodos = 1;
	}

	/**
	 * Inicializa el arbol. Se invoca de forma estatica para ahorrarnos el
	 * metodo Inicializa de la clase individuo.
	 * 
	 * @param tipoInicializacion
	 *            Tipo de inicializacion a realizar sobre el arbol.
	 * @param profundidadMaxima
	 *            Profundidad maxima del arbol.
	 * @param ifSeleccionado
	 *            Indica si el if se selecciona o no.
	 * 
	 * @return El arbol inicializado.
	 */
	public static Arbol inicializaArbol(TipoInicializacion tipoInicializacion,
			int profundidadMaxima, boolean ifSeleccionado) {

		Arbol nodo = new Arbol(null, 0, profundidadMaxima);
		nodo._simbolo = new Funcion(ifSeleccionado);
		nodo._esRaiz = true;
		nodo._esHoja = false;

		switch (tipoInicializacion) {

		case CRECIENTE:

			nodo._hijos.add(nodo.inicializaCrecienteRecursivo(nodo,
					ifSeleccionado, 1, profundidadMaxima));

			// Si la funcion es OR o AND tiene un arbol como hijo derecho
			if (nodo._simbolo.getValor().matches("OR")
					|| nodo._simbolo.getValor().matches("AND"))
				nodo._hijos.add(nodo.inicializaCrecienteRecursivo(nodo,
						ifSeleccionado, 1, profundidadMaxima));
			else
				// Si es IF creo otros dos hijos mas
				if (nodo._simbolo.getValor().matches("IF")) {
	
					nodo._hijos.add(nodo.inicializaCrecienteRecursivo(nodo,
							ifSeleccionado, 1, profundidadMaxima));
					nodo._hijos.add(nodo.inicializaCrecienteRecursivo(nodo,
							ifSeleccionado, 1, profundidadMaxima));
				}
			
			break;

		case COMPLETA:

			nodo._hijos.add(nodo.inicializaCompletaRecursivo(nodo,
					ifSeleccionado, 1, profundidadMaxima));

			// Si la funcion es OR o AND tiene un arbol como hijo derecho
			if (nodo._simbolo.getValor().matches("OR")
					|| nodo._simbolo.getValor().matches("AND"))
				nodo._hijos.add(nodo.inicializaCompletaRecursivo(nodo,
						ifSeleccionado, 1, profundidadMaxima));
			else 
				// Si es IF creo otros dos hijos mas
				if (nodo._simbolo.getValor().matches("IF")) {

				nodo._hijos.add(nodo.inicializaCompletaRecursivo(nodo,
						ifSeleccionado, 1, profundidadMaxima));
				nodo._hijos.add(nodo.inicializaCompletaRecursivo(nodo,
						ifSeleccionado, 1, profundidadMaxima));
			}
			
			break;
			
		case RAMPED_AND_HALF:
			
			nodo._hijos.add(nodo.inicializaRampedAndHalfRecursivo(nodo,
					ifSeleccionado, 1, profundidadMaxima));
			
			// Si la funcion es OR o AND tiene un arbol como hijo derecho
			if (nodo._simbolo.getValor().matches("OR")
					|| nodo._simbolo.getValor().matches("AND"))
				nodo._hijos.add(nodo.inicializaRampedAndHalfRecursivo(nodo,
						ifSeleccionado, 1, profundidadMaxima));
			else 
				// Si es IF creo otros dos hijos mas
				if (nodo._simbolo.getValor().matches("IF")) {

				nodo._hijos.add(nodo.inicializaRampedAndHalfRecursivo(nodo,
						ifSeleccionado, 1, profundidadMaxima));
				nodo._hijos.add(nodo.inicializaRampedAndHalfRecursivo(nodo,
						ifSeleccionado, 1, profundidadMaxima));
			}
			
			break;
		}
		
		// Calculamos el numero de nodos
		for (int i = 0; i < nodo._hijos.size(); i++)
			nodo._numNodos += nodo._hijos.get(i)._numNodos;

		return nodo;
	}

	/**
	 * Inicia de forma completa recursiva el arbol.
	 * 
	 * @param padre
	 *            Nodo padre.
	 * @param ifSeleccionado
	 *            Indica si se selecciona el if o no.
	 * @param esHijoIzquierdo
	 *            Indica si es hijo izquierdo o no.
	 * @param profundidad
	 *            Profundidad actual del arbol.
	 * @param profundidadMaxima
	 *            Profundidad maxima del arbol.
	 * 
	 * @return El arbol iniciado recursiva de forma completa.
	 */
	public Arbol inicializaCompletaRecursivo(Arbol padre,
			boolean ifSeleccionado, int profundidad, int profundidadMaxima) {

		Arbol nodo = new Arbol(padre, profundidad, profundidadMaxima);

		if (nodo._profundidad < nodo._profundidadMaxima) {

			// Elegimos una funcion al azar de entre las disponibles
			nodo._simbolo = new Funcion(ifSeleccionado);
			nodo._esHoja = false;

			nodo._hijos.add(nodo.inicializaCompletaRecursivo(nodo,
					ifSeleccionado, profundidad + 1, profundidadMaxima));

			// Si la funcion es OR o AND tiene un arbol como hijo derecho
			if (nodo._simbolo.getValor().matches("OR")
					|| nodo._simbolo.getValor().matches("AND"))
				nodo._hijos.add(nodo.inicializaCompletaRecursivo(nodo,
						ifSeleccionado, profundidad + 1, profundidadMaxima));
			else if (nodo._simbolo.getValor().matches("IF")) {

				nodo._hijos.add(nodo.inicializaCompletaRecursivo(nodo,
						ifSeleccionado, profundidad + 1, profundidadMaxima));

				nodo._hijos.add(nodo.inicializaCompletaRecursivo(nodo,
						ifSeleccionado, profundidad + 1, profundidadMaxima));
			}

			// Calculamos el numero de nodos
			for (int i = 0; i < nodo._hijos.size(); i++)
				nodo._numNodos += nodo._hijos.get(i)._numNodos;
			
		} else {

			nodo._simbolo = new Terminal();
			nodo._esHoja = true;
			nodo._numNodos = 1;
		}

		return nodo;
	}

	/**
	 * Inicia de forma creciente recursiva el arbol.
	 * 
	 * @param padre
	 *            Nodo padre.
	 * @param ifSeleccionado
	 *            Indica si se selecciona el if o no.
	 * @param esHijoIzquierdo
	 *            Indica si es hijo izquierdo o no.
	 * @param profundidad
	 *            Profundidad actual del arbol.
	 * @param profundidadMaxima
	 *            Profundidad maxima del arbol.
	 * 
	 * @return El arbol iniciado recursiva de forma creciente.
	 */
	public Arbol inicializaCrecienteRecursivo(Arbol padre,
			boolean ifSeleccionado, int profundidad, int profundidadMaxima) {

		Arbol nodo = new Arbol(padre, profundidad, profundidadMaxima);

		if (nodo._profundidad < nodo._profundidadMaxima) {

			boolean aleatorio = Aleatorio.boolRandom();

			// Si es verdad entonces generamos un nodo con un simbolo funcion
			if (aleatorio) {

				nodo._simbolo = new Funcion(ifSeleccionado);
				nodo._esHoja = false;

				nodo._hijos.add(nodo.inicializaCrecienteRecursivo(nodo,
						ifSeleccionado, profundidad + 1, profundidadMaxima));

				// Si la funcion es OR o AND tiene un arbol como hijo derecho
				if (nodo._simbolo.getValor().matches("OR")
						|| nodo._simbolo.getValor().matches("AND"))
					nodo._hijos.add(nodo.inicializaCrecienteRecursivo(nodo,
							ifSeleccionado, profundidad + 1, profundidadMaxima));
				else if (nodo._simbolo.getValor().matches("IF")) {

					nodo._hijos.add(nodo.inicializaCrecienteRecursivo(nodo,
							ifSeleccionado, profundidad + 1, profundidadMaxima));

					nodo._hijos.add(nodo.inicializaCrecienteRecursivo(nodo,
							ifSeleccionado, profundidad + 1, profundidadMaxima));
				}

				// Calculamos el numero de nodos
				for (int i = 0; i < nodo._hijos.size(); i++)
					nodo._numNodos += nodo._hijos.get(i)._numNodos;

			} else {

				// Si no generamos un nodo de tipo hoja
				nodo._simbolo = new Terminal();
				nodo._esHoja = true;
				nodo._numNodos = 1;
			}
		} else {

			nodo._simbolo = new Terminal();
			nodo._esHoja = true;
			nodo._numNodos = 1;
		}
		return nodo;
	}

	/**
	 * Inicia de forma Ramped and Half recursiva el arbol.
	 * 
	 * @param padre
	 *            Nodo padre.
	 * @param ifSeleccionado
	 *            Indica si se selecciona el if o no.
	 * @param esHijoIzquierdo
	 *            Indica si es hijo izquierdo o no.
	 * @param profundidad
	 *            Profundidad actual del arbol.
	 * @param profundidadMaxima
	 *            Profundidad maxima del arbol.
	 * @param raiz
	 *            nodo raiz del nodo.
	 * 
	 * @return El arbol iniciado recursiva de forma Ramped and Half.
	 */
	public Arbol inicializaRampedAndHalfRecursivo(Arbol padre,
			boolean ifSeleccionado, int profundidad, int profundidadMaxima) {

		// TODO: Por hacer

		Arbol nodo = new Arbol(padre, profundidad, profundidadMaxima);
		return nodo;
	}

	/**
	 * Devuelve la profundidad maxima del arbol.
	 * 
	 * @return La profundidad maxima del arbol.
	 */
	public int getProfundidadMaxima() {
		return _profundidadMaxima;
	}

	/**
	 * Establece la profundidad maxima del arbol a valor profundidadMaxima.
	 * 
	 * @param profundidadMaxima
	 *            Nuevo valor a establecer.
	 */
	public void setProfundidadMaxima(int profundidadMaxima) {
		_profundidadMaxima = profundidadMaxima;
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
	 * Devuelve el simbolo del arbol.
	 * 
	 * @return El simbolo del arbol.
	 */
	public Simbolo getSimbolo() {
		return _simbolo;
	}
	
	/**
	 * Establece el valor del simbolo del arbol a valor simbolo.
	 * 
	 * @param simbolo Nuevo valor a establecer.
	 */
	public void setSimbolo(Simbolo simbolo){
		_simbolo = simbolo;
	}

	@Override
	protected Arbol clone() {

		Arbol a = new Arbol();
		a._simbolo = _simbolo.clone();
		a._esHoja = _esHoja;
		a._esRaiz = _esRaiz;
		a._numNodos = _numNodos;
		a._profundidad = _profundidad;
		a._profundidadMaxima = _profundidadMaxima;
		a._padre = null;
		a._hijos = new ArrayList<Arbol>();

		for (int i = 0; i < _hijos.size(); i++)
			a._hijos.add(_hijos.get(i).cloneRecursivo(a, a));

		return a;
	}

	/**
	 * Metodo para clonar recursivamente los subarboles.
	 * 
	 * @param raiz
	 *            Nodo raiz.
	 * @param padre
	 *            Nodo padre del subarbol.
	 * 
	 * @return El arbol clonado recursivamente.
	 */
	public Arbol cloneRecursivo(Arbol raiz, Arbol padre) {

		Arbol a = new Arbol();
		a._simbolo = _simbolo.clone();
		a._esHoja = _esHoja;
		a._esRaiz = _esRaiz;
		a._numNodos = _numNodos;
		a._profundidad = _profundidad;
		a._profundidadMaxima = _profundidadMaxima;
		a._padre = padre;
		a._hijos = new ArrayList<Arbol>();

		for (int i = 0; i < _hijos.size(); i++)
			if (a._simbolo.getTipo() == TipoSimbolo.FUNCION)
				a._hijos.add(_hijos.get(i).cloneRecursivo(raiz, a));

		return a;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {

		String cadena = "";
		cadena = "" + _simbolo.getValor();

		if (!esHoja()) {

			cadena = cadena + "(" + _hijos.get(0).toString();

			for (int i = 1; i < _hijos.size(); i++)
				cadena = cadena + "," + _hijos.get(i).toString();

			cadena += ")";
		}

		return cadena;

	}

	/**
	 * Evalua el arbol con los casos de prueba correspondientes. Se evaluan los
	 * nodos terminales con los valores de cada caso de prueba y se comprueban
	 * con sus funciones correspondientes sumando uno por cada acierto.
	 * 
	 * @return La evaluacion del arbol con los casos de prueba correspondientes.
	 */
	public double evalua(boolean[] caso, boolean salida) {

		double evaluacion = 0;

		// Si se corresponde con la salida del multiplexor
		if (evaluaRecursivo(caso) == salida)
			// Sumamos un acierto
			evaluacion++;

		return evaluacion;
	}

	/**
	 * Evalua el arbol de forma recursiva del arbol con el caso de prueba
	 * correspondiente.
	 * 
	 * @param caso
	 *            Caso de prueba actual.
	 * 
	 * @return La evaluacion recursiva del arbol con el caso de prueba
	 *         correspondiente.
	 */
	private boolean evaluaRecursivo(boolean[] caso) {

		boolean evaluacion = false;

		// Si es un Terminal
		if (_esHoja) {
			// Devolvemos su valor que le corresponde en el caso de prueba
			evaluacion = caso[_simbolo.devuelvePosTerminal()];
		}
		// Si es una Funcion
		else {

			// Si es la funcion NOT
			if (_simbolo.getValor().matches("NOT")) {

				// Devolvemos el valor negado que le corresponde al hijo de la
				// izquierda
				evaluacion = !_hijos.get(0).evaluaRecursivo(caso);
			} else
			// Si es la funcion OR
			if (_simbolo.getValor().matches("OR")) {
				// Devolvemos la OR de sus dos hijos
				evaluacion = _hijos.get(0).evaluaRecursivo(caso)
						|| _hijos.get(1).evaluaRecursivo(caso);

			} else
			// Si es la funcion AND
			if (_simbolo.getValor().matches("AND")) {
				// Devolvemos la AND de sus dos hijos
				evaluacion = _hijos.get(0).evaluaRecursivo(caso)
						&& _hijos.get(1).evaluaRecursivo(caso);
			} else
			// Si es un IF
			if (_simbolo.getValor().matches("IF")) {

				// Si el primer hijo es true
				if (_hijos.get(0).evaluaRecursivo(caso))
					// Evaluamos el segundo hijo
					evaluacion = _hijos.get(1).evaluaRecursivo(caso);
				else
					// Y si no evaluamos el tercer hijo
					evaluacion = _hijos.get(2).evaluaRecursivo(caso);
			}
		}
		return evaluacion;
	}

	/**
	 * Obtiene los nodos Funcion de un arbol.
	 * 
	 * @param nodosFuncion Los nodos funcion de un arbol.
	 */
	public void extraerNodosFuncion(ArrayList<Arbol> nodosFuncion){
		
		if (!_esHoja){
			
			_hijos.get(0).extraerNodosFuncion(nodosFuncion);
			
			if (getSimbolo().getValor().matches("AND") || getSimbolo().getValor().matches("OR"))
				_hijos.get(1).extraerNodosFuncion(nodosFuncion);
			else
				if(getSimbolo().getValor().matches("IF")){
					_hijos.get(1).extraerNodosFuncion(nodosFuncion);
					_hijos.get(2).extraerNodosFuncion(nodosFuncion);
				}
		}
	}
	
	/**
	 * Obtiene los nodos terminales del arbol.
	 * 
	 * @param terminales Nodos terminales del arbol.
	 */
	public void extraerNodosTerminales(ArrayList<Arbol> terminales){
		
		if (_esHoja)
			terminales.add(this);
		else
			extraerNodosTerminales(terminales);
	}
}
