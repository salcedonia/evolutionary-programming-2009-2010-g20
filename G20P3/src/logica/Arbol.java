package logica;

import java.util.ArrayList;

import utils.Aleatorio;

public class Arbol {
	
	 private String _nombre;
	 private Arbol _hijoDerecho;
	 private Arbol _hijoIzquierdo;
	 private Arbol _padre;
	 private int _profundidad;
	 private int _numNodos;
	 private boolean _esHoja;
	 private boolean _esRaiz;
	 private boolean _esHijoIzquierdo;
	 private boolean _esHijoDerecho;
	 private int profTotal;
	 private int numInstrucciones;

	public int getProfTotal() {
		return profTotal;
	}
	public void setProfTotal(int profTotal) {
		this.profTotal = profTotal;
	}
	//----------------------------------------------------------------
	public Arbol(){
		_nombre=null;
		_hijoDerecho = null;
		_hijoIzquierdo = null;
		_profundidad = 0;
		_numNodos = 1;
		_esHoja = false;
		_esRaiz = true;
		_esHijoIzquierdo = false;
	}
	//----------------------------------------------------------------

	public Arbol(ArrayList<String> cjtoFuns,  ArrayList<String> cjtoTerms,
			  int hmax, int prof, Arbol pater, boolean esHizq, boolean esRaiz){
			    
	           int nuevaProf = prof+1;
			    boolean  rnd = Aleatorio.boolRandom();
			    _profundidad=prof;
			    _padre = pater;
			    _esHijoIzquierdo = esHizq;
			    _esRaiz = esRaiz;
			    if (_padre == null) _esRaiz = true;
			    _numNodos = 1;
			    if ((rnd) || (_profundidad + 1 == hmax))
			    {
			      int intRand = Aleatorio.intRandom(0,cjtoTerms.size()-1);
			      _nombre = cjtoTerms.get(intRand);
			      _esHoja = true;
			    }
			    else
			    {
			      int intRand2 = Aleatorio.intRandom(0,cjtoFuns.size()-1);	
			      _nombre = cjtoFuns.get(intRand2);
			      _hijoIzquierdo = new Arbol(cjtoFuns, cjtoTerms, hmax, nuevaProf, this, true, false);
			      _numNodos = _numNodos + _hijoIzquierdo.getNumNodos();
			      _hijoDerecho = new Arbol(cjtoFuns, cjtoTerms, hmax, nuevaProf, this, false, false);
			      _numNodos = _numNodos + _hijoDerecho.getNumNodos();
			      _esHoja = false;
			  
			    }
	}

	public Arbol(Arbol nodo2, Object object) {
		// TODO Auto-generated constructor stub
	}
	private int getNumNodos() {
		
		return _numNodos;
	}

	public Arbol nodoAleatorio() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isRaiz() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isEsHi() {
		// TODO Auto-generated method stub
		return false;
	}
	public Arbol getPadre() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getProfundidad() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int altura() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setHi(Arbol nodo2) {
		// TODO Auto-generated method stub
		
	}
	public void setHd(Arbol nodo2) {
		// TODO Auto-generated method stub
		
	}
	public void actualizar(int profundidad2) {
		// TODO Auto-generated method stub
		
	}
	public int getNumInstrucciones() {
		// TODO Auto-generated method stub
		return numInstrucciones;
	}
}

