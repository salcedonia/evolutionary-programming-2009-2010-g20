package cromosoma;

import utils.Matematicas;

/**
 * Clase que implementa la clase cromosoma para la funci�n 1 a evaluar.
 * 
 * @author Grupo20.
 */
public class CromosomaFuncion1 extends Cromosoma{

	// Extremos del intervalo de la funci�n.
	private double[] _xMin;
	private double[] _xMax;

	// Longitud del gen
	private int[] _longitudGen;
	
	/**
	 * Constructora por defecto.
	 */
	public CromosomaFuncion1() {
		
	}
	
	/**
	 * Constructor de la clase CromosomaFuncion1.
	 * 
	 * @param tolerancia del algoritmo.
	 */
	public CromosomaFuncion1(double tolerancia){
		
		// Establecemos el n�mero de genes del problema 1
		_numGenes = 1;
		
		// Establecemos los intervalos para cada gen
		_xMin = new double[_numGenes];
		_xMax = new double[_numGenes];
		_xMin[0] = 0;
		_xMax[0] = 1;
				
		_longitudGen = new int[_numGenes];
		_fenotipo = new double[_numGenes];
		_genes = new boolean[_numGenes][];
		setLongitudCromosoma(0);
		// Calculamos la longitud de los genes
		for (int i = 0; i < _numGenes; i++) {
			
			_longitudGen[i] = calcularLongGen(i,tolerancia);
			setLongitudCromosoma(getLongitudCromosoma() + _longitudGen[i]);
			// Creamos el array de genes del tama�o adecuado
			_genes[i] = new boolean[_longitudGen[i]];
		}
	}
	
	@Override
	public double fenotipo(boolean[] gen, int nGen) {
		return _xMin[nGen] + (_xMax[nGen] - _xMin[nGen]) * 
		bin_dec(gen) / (Math.pow(2, gen.length) - 1);
	} 
	
	@Override
	public double evalua() {
		for (int i = 0; i < _numGenes; i++) {
			_fenotipo[i] = fenotipo(_genes[i],i);
		}
		return f(); // valor de la funci�n a optimizar
	}

	@Override
	public int calcularLongGen(int nGen, double tolerancia) {
		
		return (int) Matematicas.log2(1 + ((_xMax[nGen] - _xMin[nGen])/tolerancia));
	}

	@Override
	public double f() {
		double x = _fenotipo[0];
		return x + Math.abs(Math.sin(32 * Math.PI * x));
	}
	
	@Override
	public Object clone() {
		CromosomaFuncion1 copia = new CromosomaFuncion1();
		
		copia._numGenes = this._numGenes;
		
		// Copia de atributos de esta clase
		copia._xMax = new double[_numGenes];
		copia._xMin = new double[_numGenes];
		copia._longitudGen = new int[_numGenes];
		for (int i = 0; i < _numGenes; i++) {
			copia._xMax[i] = this._xMax[i];
			copia._xMin[i] = this._xMin[i];
			copia._longitudGen[i] = this._longitudGen[i];
		}		
		
		// Copia de atributos de la clase padre
		copia.setAptitud(this._aptitud);
		copia.setPuntAcumulada(this._puntAcumulada);
		copia.setPuntuacion(this._puntuacion);
		copia.setLongitudCromosoma(this._longitudCromosoma);
		
		// Copia del fenotipo y los genes
		copia._fenotipo = new double[_numGenes];
		copia._genes = new boolean[_numGenes][];
		for (int i = 0; i < _numGenes; i++) {
			copia._fenotipo[i] = this._fenotipo[i];
			copia._genes[i] = new boolean[_longitudGen[i]];
			for (int j = 0; j < _longitudGen[i]; j++) {
				copia._genes[i][j] = this._genes[i][j];
			}
		}		
		
		return copia;
	}

	@Override
	public String toString() {
		String txtFenotipo = "X: "+_fenotipo[0];
		return txtFenotipo;
	}
}