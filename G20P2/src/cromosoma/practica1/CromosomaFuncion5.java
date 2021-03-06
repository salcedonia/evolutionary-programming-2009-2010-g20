package cromosoma.practica1;

import cromosoma.Cromosoma;
import cromosoma.Gen;
import utils.Matematicas;

/**
 * Clase que implementa la clase cromosoma para la funcion 5 a evaluar.
 * 
 * @author Grupo20.
 */
public class CromosomaFuncion5 extends Cromosoma {

	/**
	 * Decodificacion de los genes del cromosoma.
	 */
	private double[] _fenotipo;
	
	/**
	 * Minimo valor del intervalo.
	 */
	private double[] _xMin;
	
	/**
	 * Maximo valor del intervalo.
	 */
	private double[] _xMax;

	/**
	 * Array de longitudes para cada tipo de gen.
	 */
	private int[] _longitudGen;
	
	/**
	 * Constructor por defecto de la clase CromosomaFuncion5.
	 */
	public CromosomaFuncion5() {

	}
	
	/**
	 * Constructor de la clase CromosomaFuncion5.
	 * 
	 * @param tolerancia Tolerancia del algoritmo.
	 * @param valorN Numero de copias estimadas del mejor individuo.
	 */
	public CromosomaFuncion5(double tolerancia, int valorN) {
		
		// Establecemos el numero de genes del problema 5
		_numGenes = valorN;
		
		// Establecemos los intervalos para cada gen
		_xMin = new double[_numGenes];
		_xMax = new double[_numGenes];
		
		for(int i=0; i<_numGenes;i++){
			
			_xMin[i] = 0.0;
			_xMax[i] = Math.PI;
		}
				
		_longitudGen = new int[_numGenes];
		_fenotipo = new double[_numGenes];
		_genes = new GenP1[_numGenes];
		setLongitudCromosoma(0);
		
		// Calculamos la longitud de los genes
		for (int i = 0; i < _numGenes; i++) {
			
			_longitudGen[i] = calcularLongGen(i,tolerancia);
			setLongitudCromosoma(getLongitudCromosoma() + _longitudGen[i]);
			// Creamos el array de genes del tamanio adecuado
			_genes[i] = new GenP1(_longitudGen[i]);
		}	
	}
	
	@Override
	public double evalua() {
		for (int i = 0; i < _numGenes; i++) {
			_fenotipo[i] = fenotipo(_genes[i],i);
		}
		return f(); // valor de la funcion a optimizar
	}

	/**
	 * Calcula la longitud del gen con la tolerancia.
	 * 
	 * @param tolerancia Tolerancia del algoritmo.
	 * 
	 * @return La longitud del gen.
	 */
	public int calcularLongGen(int nGen, double tolerancia) {
		return (int) Matematicas.log2(1 + ((_xMax[nGen] - _xMin[nGen])/tolerancia));
	}

	@Override
	public double f() {
		
		double sumatorio = 0.0;
		double parteDerecha = 0;
		double parteIzq = 0;
		
		for (int i=1; i <= _numGenes; i++){
			parteIzq = Math.sin(_fenotipo[i-1]);
			parteDerecha = Math.pow((Math.sin((i+1)* Math.pow(_fenotipo[i-1],2)/Math.PI)), 20);
			sumatorio += parteIzq * parteDerecha;
		}
		return -sumatorio;
	}

	@Override
	public double fenotipo(Gen gen, int nGen) {
		
		GenP1 genP1 = (GenP1) gen;
		
		return _xMin[nGen] + (_xMax[nGen] - _xMin[nGen]) * 
		genP1.bin_dec() / (Math.pow(2, genP1.getLongitudGen()) - 1);
	}

	@Override
	public Object clone() {
		CromosomaFuncion5 copia = new CromosomaFuncion5();
		
		copia._numGenes = _numGenes;
		copia._adaptacion = _adaptacion;
		
		// Copia de atributos de esta clase
		copia._xMax = new double[_numGenes];
		copia._xMin = new double[_numGenes];
		copia._longitudGen = new int[_numGenes];
		for (int i = 0; i < _numGenes; i++) {
			copia._xMax[i] = _xMax[i];
			copia._xMin[i] = _xMin[i];
			copia._longitudGen[i] = _longitudGen[i];
		}		
		
		// Copia de atributos de la clase padre
		copia.setAptitud(_aptitud);
		copia.setPuntAcumulada(_puntAcumulada);
		copia.setPuntuacion(_puntuacion);
		copia.setLongitudCromosoma(_longitudCromosoma);
		
		// Copia del fenotipo y los genes
		copia._fenotipo = new double[_numGenes];
		copia._genes = new GenP1[_numGenes];
		for (int i = 0; i < _numGenes; i++) {
			copia._fenotipo[i] = _fenotipo[i];
			copia._genes[i] = (GenP1)_genes[i].clone();
		}		
		
		return copia;
	}

	@Override
	public String toString() {
		
		String txtFenotipo="";
		
		for(int i=0; i<_numGenes; i++)		
			txtFenotipo += "   - X" + (i+1) + ": " + _fenotipo[i] + "\n";
		
		return txtFenotipo;
	}

}
