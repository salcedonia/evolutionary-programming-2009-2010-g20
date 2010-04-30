package utils;

import java.util.ArrayList;

import cromosoma.practica2.GenP2;

/**
 * Implementa una tabla de conectividad para un grafo.
 * En concreto se usa para el cruce ERX.
 * 
 * @author Grupo20.
 */
public class TablaConectividad {

	private int[][] _tabla;
	
	public TablaConectividad() {
	
	}
	
	public TablaConectividad(GenP2[] padre, GenP2[] madre) {
		
		_tabla = new int[padre.length][];
				
		for (int i = 0; i < padre.length; i++) {
			
			_tabla[i] = new int[4];
			
			if (i+1 < padre.length)
				_tabla[i][0] = (Integer) padre[i+1].getGen();
			else 
				_tabla[i][0] = (Integer) padre[0].getGen();
				
			if (i-1 >= 0)
				_tabla[i][1] = (Integer) padre[i-1].getGen();
			else
				_tabla[i][1] = (Integer) padre[padre.length].getGen();
			
			// Se mira si es diferente la tercera conectividad (desde madre)
			int temp;
			if (i+1 < padre.length)
				temp = (Integer) madre[i+1].getGen();
			else 
				temp = (Integer) madre[0].getGen();
			
			if ((temp != _tabla[i][0]) && (temp != _tabla[i][1]))
				_tabla[i][2] = temp;
			else _tabla[i][2] = -1;
			
			// Se mira si es diferente la cuarta conectividad (desde madre)
			if (i-1 >= 0)
				temp = (Integer) madre[i-1].getGen();
			else
				temp = (Integer) madre[padre.length].getGen();
				
			if (temp != _tabla[i][0] && temp != _tabla[i][1] && temp != _tabla[i][2])
				_tabla[i][3] = temp;
			else _tabla[i][3] = -1;
			
		}
		
	}
	
	public ArrayList<Integer> calculaMenorRuta(int ciudad) {
		
		int[] rutas = _tabla[ciudad];
		ArrayList<Integer> retVal = new ArrayList<Integer>();		
		int minimo = numConexiones(rutas[0]);
		
		for (int i = 1; i < rutas.length; i++) {
			
			int nC = numConexiones(rutas[i]);
			
			if (minimo > nC) {
				
				minimo = nC;
				retVal = new ArrayList<Integer>();
				retVal.add(i);
			}
			else if (minimo == nC) {
				retVal.add(i);
			}
		}
		
		return retVal;
	}
	
	public int numConexiones(int ciudad) {
		
		int numConexiones = 0;
		
		for (int i = 0; i < _tabla[ciudad].length; i++) {
			
			if (_tabla[ciudad][i] != -1) numConexiones++;
		}
		
		return numConexiones;
	}
	
}
