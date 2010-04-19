package cromosoma.practica2;

import cromosoma.Cromosoma;

/**
 * Clase que implementa los metodos necesarios para la gestion del cromosoma para el problema 
 * del viajante.
 * 
 * @author Grupo20.
 */
public class CromosomaViajante extends Cromosoma {
	
	/**
	 * Longitud del gen
	 */
	@SuppressWarnings("unused")
	private int[] _longitudGen;
	
	/**
	 * Constructor de la clase CromosomaViajante.
	 */
	public CromosomaViajante(){
		
		// Establecemos el número de genes del problema del viajante
		_numGenes = 1;
		
		_longitudGen = new int[_numGenes];
		_fenotipo = new double[_numGenes];
		_genes = new int[_numGenes][];
		setLongitudCromosoma(0);
		
		// Calculamos la longitud de los genes
		//for (int i = 0; i < _numGenes; i++) {
			
	//		_longitudGen[i] = calcularLongGen(i,tolerancia);
//			setLongitudCromosoma(getLongitudCromosoma() + _longitudGen[i]);
			// Creamos el array de genes del tamanio adecuado
	//		_genes[i] = new int[_longitudGen[i]];
		//}
	}
	
	@Override
	public int calcularLongGen(int nGen, double tolerancia) {
		
		return 27;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double evalua() {
		
		for (int i = 0; i < _numGenes; i++) {
			_fenotipo[i] = fenotipo(_genes[i],i);
		}
		return f(); // valor de la funcion a optimizar
	}

	@Override
	public double f() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double fenotipo(int[] gen, int nGen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
