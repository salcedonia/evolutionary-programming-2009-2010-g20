package logica;

import gui.tipos.TipoInicializacion;

public class Individuo implements Comparable {

	private Arbol arbol;
	private int hmax;
	private double aptitud;
	private double evaluacion;
	private double puntuacion;
	private double punt_acu;
	private final String[] cjtoTerms = { "A0", "A1", "D0", "D1", "D2", "D3" };
	private final String[] cjtoFuns = { "AND", "OR", "NOT", "IF" };
	private int numFunciones;
	private int numLiterales;
	private static TipoInicializacion _tipoInicializacion;

	public static void setTipoInicializacion(
			TipoInicializacion tipoInicializacion) {

		_tipoInicializacion = tipoInicializacion;
	}

	public Individuo(Individuo individuo) {
		// TODO Auto-generated constructor stub
	}

	public Individuo() {

	}

	private void inicializaRampedAndHalf() {
		// TODO Auto-generated method stub

	}

	private void inicializaCompleta() {
		// TODO Auto-generated method stub

	}

	private void inicializaCreciente() {
		// TODO Auto-generated method stub

	}

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
			raiz1 = nodo1.isRaiz();
			raiz2 = nodo2.isRaiz();
			esHi1 = nodo1.isEsHi();
			esHi2 = nodo2.isEsHi();
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
				pater1.setHi(nodo2);
			else
				pater1.setHd(nodo2);
		}

		if (raiz2)
			arbol2 = new Arbol(nodo_aux1, null);
		else {
			if (esHi2)
				pater2.setHi(nodo_aux1);
			else
				pater2.setHd(nodo_aux1);
		}

		arbol1.actualizar(arbol1.getProfundidad());
		arbol2.actualizar(arbol2.getProfundidad());
		hijo1.setArbol(arbol1);
		hijo2.setArbol(arbol2);
		hijo1.setAdaptacion(2);
		hijo2.setAdaptacion(2);
	}

	public void setAdaptacion(double d) {
		// TODO Auto-generated method stub

	}

	public int getAdaptacion() {
		// TODO Auto-generated method stub
		return 0;
	}

	void setArbol(Arbol arbol1) {
		// TODO Auto-generated method stub

	}

	Arbol getArbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getAptitud() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object clone() {
		Object obj = new Object();
		return obj;
	}

	public void setPuntuacion(double d) {
		// TODO Auto-generated method stub

	}

	public double getPuntuacion() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPuntAcumulada(double d) {
		// TODO Auto-generated method stub

	}

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

	public double evalua() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setAptitud(double evalua) {
		// TODO Auto-generated method stub

	}
}
