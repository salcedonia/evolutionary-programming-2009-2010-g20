package logica;

import java.util.ArrayList;

import utils.Aleatorio;

public class Arbol {
	
	 private String nombre;
	 private Arbol hd;
	 private Arbol hi;
	 private Arbol padre;
	 private int profundidad;
	 private int numNodos;
	 private boolean hoja;
	 private boolean raiz;
	 private boolean esHi;
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
		nombre=null;
		hd = null;
		hi = null;
		profundidad = 0;
		numNodos = 1;
		hoja = false;
		raiz = true;
		esHi = false;
	}
	//----------------------------------------------------------------

	public Arbol(ArrayList<String> cjtoFuns,  ArrayList<String> cjtoTerms,
			  int hmax, int prof, Arbol pater, boolean esHizq, boolean esRaiz){
			    
	           int nuevaProf = prof+1;
			    boolean  rnd = Aleatorio.boolRandom();
			    profundidad=prof;
			    padre = pater;
			    esHi = esHizq;
			    raiz = esRaiz;
			    if (padre == null) raiz = true;
			    numNodos = 1;
			    if ((rnd) || (profundidad + 1 == hmax))
			    {
			      int intRand = Aleatorio.intRandom(0,cjtoTerms.size()-1);
			      nombre = cjtoTerms.get(intRand);
			      hoja = true;
			    }
			    else
			    {
			      int intRand2 = Aleatorio.intRandom(0,cjtoFuns.size()-1);	
			      nombre = cjtoFuns.get(intRand2);
			      hi = new Arbol(cjtoFuns, cjtoTerms, hmax, nuevaProf, this, true, false);
			      numNodos = numNodos + hi.getNumNodos();
			      hd = new Arbol(cjtoFuns, cjtoTerms, hmax, nuevaProf, this, false, false);
			      numNodos = numNodos + hd.getNumNodos();
			      hoja = false;
			  
			    }
	}

	public Arbol(Arbol nodo2, Object object) {
		// TODO Auto-generated constructor stub
	}
	private int getNumNodos() {
		
		return numNodos;
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

