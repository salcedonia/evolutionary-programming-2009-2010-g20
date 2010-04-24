package cromosoma.practica2;

import cromosoma.Gen;

/**
 * Clase que implementa la clase gen para las funciones de la pr�ctica 2.
 * 
 * @author Grupo20.
 */
public class GenP2 extends Gen {

	/**
	 * Constructora por defecto.
	 */
	public GenP2() {
		
		_gen = new Integer(0);
	}
	
	/**
	 * Constructora del gen para la ciudad.
	 */
	public GenP2(int ciudad) {
		
		_gen = new Integer(ciudad);
	}
	
	@Override
	public Object clone() {
		
		GenP2 copia = new GenP2();
		
		copia._gen = new Integer((Integer)_gen);
		
		return copia;
	}

	@Override
	public void inicializaGen() {
		
		// En este caso se inicializa con la constructora

	}

}
