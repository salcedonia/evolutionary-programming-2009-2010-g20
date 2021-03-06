package cromosoma.practica1;

import cromosoma.Cromosoma;
import cromosoma.Gen;
import utils.Matematicas;

/**
 * Clase que implementa la clase cromosoma para la funcion 1 a evaluar.
 * 
 * @author Grupo20.
 */
public class CromosomaFuncion1 extends Cromosoma{
	
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
	 * Constructor por defecto de la clase CromosomaFuncion1.
	 */
	public CromosomaFuncion1() {
		
	}
	
	/**
	 * Constructor de la clase CromosomaFuncion1.
	 * 
	 * @param tolerancia del algoritmo.
	 */
	public CromosomaFuncion1(double tolerancia){
		
		// Establecemos el numero de genes del problema 1
		_numGenes = 1;
		
		// Establecemos los intervalos para cada gen
		_xMin = new double[_numGenes];
		_xMax = new double[_numGenes];
		_xMin[0] = 0;
		_xMax[0] = 1;
				
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
	public void inicializaCromosoma() {
		
		for (int i = 0; i < _numGenes; i++) {
			
			// Inicializamos el gen
			_genes[i].inicializaGen();
		}
	}
	
	@Override
	public double fenotipo(Gen gen, int nGen) {
		
		GenP1 genP1 = (GenP1) gen;
		
		return _xMin[nGen] + (_xMax[nGen] - _xMin[nGen]) * 
		genP1.bin_dec() / (Math.pow(2, genP1.getLongitudGen()) - 1);
	}
	
	/**
	 * Evalua la calidad del cromosoma.
	 * 
	 * @return La calidad del cromosoma.
	 */
	public double evalua() {
		for (int i = 0; i < _numGenes; i++) {
			_fenotipo[i] = fenotipo((GenP1)_genes[i],i);
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

	/**
	 * Calcula el valor de la funcion a partir del fenotipo actual. 
	 * (funcion de evaluacion).
	 * 
	 * @return El valor de la funcion usando el fenotipo actual.
	 */
	public double f() {
		double x = _fenotipo[0];
		return x + Math.abs(Math.sin(32 * Math.PI * x));
	}
	
	@Override
	public Object clone() {
		CromosomaFuncion1 copia = new CromosomaFuncion1();
		
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
		String txtFenotipo = "\n   - X: "+_fenotipo[0];
		return txtFenotipo;
	}
}
