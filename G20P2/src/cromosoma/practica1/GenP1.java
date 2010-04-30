package cromosoma.practica1;

import java.util.Random;

import cromosoma.Gen;

/**
 * Clase que implementa la clase gen para las funciones de la práctica 1.
 * 
 * @author Grupo20.
 */
public class GenP1 extends Gen {
	
	private boolean[] _gen;
	
	/**
	 * Constructora por defecto.
	 */
	public GenP1() {
		
	}
	
	/**
	 * Constructora del gen como array de booleanos.
	 * 
	 * @param tam Número de bits del gen.
	 */
	public GenP1(int tam) {
		
		_gen = new boolean[tam];
		
	}
	
	/**
	 * Obtiene la longitud del gen.
	 * 
	 * @return Longitud del gen.
	 */
	public int getLongitudGen() {
		
		if (_gen == null) return -1;
		else return ((boolean[])_gen).length;
	}
	
	/**
	 * Modifica el bit pedido contenido en el gen.
	 * 
	 * @param numBit Numero de bit a modificar.
	 * @ bit Nuevo valor para el bit.
	 */
	public void setBit(int numBit, boolean bit) {
		
		((boolean[])_gen)[numBit] = bit;
	}
	
	/**
	 * Obtiene el bit pedido contenido en el gen.
	 * 
	 * @param Numero del bit a obtener.
	 * 
	 * @return El bit numero numBit.
	 */
	public boolean getBit(int numBit) {
		
		return ((boolean[])_gen)[numBit];
	}
	
	/**
	 * Halla el valor decimal de un número binario a partir de un vector de 
	 * booleanos.
	 * 
	 * @return El valor decimal del numero binario codificado en el vector de 
	 * booleanos.
	 */
	public double bin_dec() {
		
		double valorDecimal = 0, potencia_2 = 1;
		for (int i = 0; i < getLongitudGen(); i++) {
			if (getBit(i)) {
				valorDecimal += potencia_2;
			}
			potencia_2 *= 2;
		}
		return valorDecimal;
	}
		
	@Override
	public void inicializaGen() {

		boolean[] genP1 = (boolean[])_gen;
		
		Random generador = new Random();
		   
		for (int i = 0; i < genP1.length; i++) {
			
			// Generamos un numero aleatorio entre 0.0 y 0.1
			double aleatorio = generador.nextDouble();
			
			if(aleatorio < 0.5)
				genP1[i] = false;
		    else
				genP1[i] = true;	
		}
	}
	
	@Override
	public Object clone() {
		
		boolean[] genP1 = (boolean[])_gen;
		
		GenP1 copia = new GenP1(genP1.length);
		
		for (int i = 0; i < genP1.length; i++)
			((boolean[])copia._gen)[i] = genP1[i];
		
		return copia;
	}

	@Override
	public Object getGen() {
		// TODO Auto-generated method stub
		return _gen;
	}

	@Override
	public void setGen(Object gen) {
		
		_gen = (boolean[]) gen;
	}
	
}
