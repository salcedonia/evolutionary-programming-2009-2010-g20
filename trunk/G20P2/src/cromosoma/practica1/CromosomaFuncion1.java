package cromosoma.practica1;

import java.util.Random;

import cromosoma.Cromosoma;
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
		_genes = new int[_numGenes][];
		setLongitudCromosoma(0);
		// Calculamos la longitud de los genes
		for (int i = 0; i < _numGenes; i++) {
			
			_longitudGen[i] = calcularLongGen(i,tolerancia);
			setLongitudCromosoma(getLongitudCromosoma() + _longitudGen[i]);
			// Creamos el array de genes del tamanio adecuado
			_genes[i] = new int[_longitudGen[i]];
		}
	}
	
	@Override
	public void inicializaCromosoma() {
		
		for (int i = 0; i < _numGenes; i++) {
			
			// Inicializamos el gen
			inicializaGen(i);
		}
	}

	/**
	 * Inicializa aleatoriamente el vector de genes de tipo binario.
	 * 
	 * @param nGen Numero de gen a inicializar.
	 */
	private void inicializaGen(int nGen) {

		Random generador = new Random();
		   
		for (int i = 0; i < _genes[nGen].length; i++) {
			
			// Generamos un numero aleatorio entre 0.0 y 0.1
			double aleatorio = generador.nextDouble();
			
			if(aleatorio < 0.5)
				_genes[nGen][i] = 0;
		    else
				_genes[nGen][i] = 1;	
		}
	}
	
	/**
	 * Halla el valor decimal de un número binario a partir de un vector de 
	 * booleanos.
	 * 
	 * @param gen Gen a convertir en decimal.
	 * 
	 * @return El valor decimal del numero binario codificado en el vector de 
	 * booleanos.
	 */
	public double bin_dec(int[] gen) {
		
		double valorDecimal = 0, potencia_2 = 1;
		for (int i = 0; i < gen.length; i++) {
			if (gen[i]==1) {
				valorDecimal += potencia_2;
			}
			potencia_2 *= 2;
		}
		return valorDecimal;
	}
	
	/**
	 * Calcula el fenotipo de un gen.
	 * 
	 * @param gen Gen a calcular su fenotipo.
	 * @param nGen Numero del gen para calcular su fenotipo.
	 * @return El fenotipo del gen.
	 */
	public double fenotipo(int[] gen, int nGen) {
		return _xMin[nGen] + (_xMax[nGen] - _xMin[nGen]) * 
		bin_dec(gen) / (Math.pow(2, gen.length) - 1);
	} 
	
	/**
	 * Evalua la calidad del cromosoma.
	 * 
	 * @return La calidad del cromosoma.
	 */
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
		copia._genes = new int[_numGenes][];
		for (int i = 0; i < _numGenes; i++) {
			copia._fenotipo[i] = _fenotipo[i];
			copia._genes[i] = new int[_longitudGen[i]];
			for (int j = 0; j < _longitudGen[i]; j++) {
				copia._genes[i][j] = _genes[i][j];
			}
		}		
		
		return copia;
	}

	@Override
	public String toString() {
		String txtFenotipo = "\n   - X: "+_fenotipo[0];
		return txtFenotipo;
	}
}
