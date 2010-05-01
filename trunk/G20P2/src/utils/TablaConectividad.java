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
			
			int ciudadContador = (Integer) padre[i].getGen() - 1;
			
			_tabla[ciudadContador] = new int[4];
			
			if (i+1 < padre.length)
				_tabla[ciudadContador][0] = (Integer) padre[i+1].getGen();
			else 
				_tabla[ciudadContador][0] = (Integer) padre[0].getGen();
				
			if (i-1 >= 0)
				_tabla[ciudadContador][1] = (Integer) padre[i-1].getGen();
			else
				_tabla[ciudadContador][1] = (Integer) padre[padre.length-1].getGen();
			
			// Se mira si es diferente la tercera conectividad (desde madre)
			int temp;
			int ciudadContadorMadre = devuelvePos(madre, ciudadContador + 1);
			
			if (ciudadContadorMadre+1 < padre.length)
				temp = (Integer) madre[ ciudadContadorMadre + 1].getGen();
			else 
				temp = (Integer) madre[0].getGen();
			
			if ((temp != _tabla[ciudadContador][0]) && (temp != _tabla[ciudadContador][1]))
				_tabla[ciudadContador][2] = temp;
			else _tabla[ciudadContador][2] = -1;
			
			// Se mira si es diferente la cuarta conectividad (desde madre)
			if (ciudadContadorMadre-1 >= 0)
				temp = (Integer) madre[ciudadContadorMadre-1].getGen();
			else
				temp = (Integer) madre[padre.length-1].getGen();
				
			if (temp != _tabla[ciudadContador][0] && temp != _tabla[ciudadContador][1] && temp != _tabla[ciudadContador][2])
				_tabla[ciudadContador][3] = temp;
			else _tabla[ciudadContador][3] = -1;
			
		}
		
	}
	
	public ArrayList<Integer> calculaMenorRutaPosible(int ciudad, ArrayList<Integer> ciudadesIncluidas, boolean conMinimoCamino) {
		
		int[] rutas = _tabla[ciudad-1];
		ArrayList<Integer> retVal = new ArrayList<Integer>();	
		
		if (!conMinimoCamino) {
			
			for (int i = 0; i < rutas.length; i++) {
				
				if ((rutas[i] != -1) && (!ciudadesIncluidas.contains(rutas[i]))) 
					retVal.add(rutas[i]);
			}
		}
		else {
			
				
			int minimo = Integer.MAX_VALUE;
			
			for (int i = 0; i < rutas.length; i++) {
				
				if ((rutas[i] != -1) && (!ciudadesIncluidas.contains(rutas[i]))) {
					int nC = numConexiones(rutas[i]);
					
					if (minimo > nC) {
						
						minimo = nC;
						retVal = new ArrayList<Integer>();
						retVal.add(rutas[i]);
					}
					else if (minimo == nC) {
						retVal.add(rutas[i]);
					}
				}
			}
		
			
		}
		
		return retVal;
	}
	
	public int numConexiones(int ciudad) {
		
		int numConexiones = 0;
		
		for (int i = 0; i < _tabla[ciudad-1].length; i++) {
			
			if (_tabla[ciudad-1][i] != -1) numConexiones++;
		}
		
		return numConexiones;
	}
	
	/**
	 * 
	 * @param a
	 * @param elem
	 * @return
	 */
	private int devuelvePos(GenP2[] a, int elem){
		
		boolean encontrado = false;
		int pos=0;
		
		while(!encontrado){
			
			if(elem == (Integer)(a[pos].getGen()))
				encontrado = true;
			else
				pos++;
		}
		
		return pos;
	}
	
}
