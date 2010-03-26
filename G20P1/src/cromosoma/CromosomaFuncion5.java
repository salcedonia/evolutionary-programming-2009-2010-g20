package cromosoma;

import utils.Matematicas;

/**
 * Clase que implementa la clase cromosoma para la función 5 a evaluar.
 * 
 * @author Grupo20.
 */
public class CromosomaFuncion5 extends Cromosoma {

	// Extremos del intervalo de la función.
	private double[] _xMin;
	private double[] _xMax;

	// Longitud del gen
	private int[] _longitudGen;
	
	public CromosomaFuncion5() {
		// TODO Auto-generated constructor stub
	}
	
	public CromosomaFuncion5(double tolerancia, int valorN) {
		
		// Establecemos el número de genes del problema 5
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
		_genes = new boolean[_numGenes][];
		setLongitudCromosoma(0);
		
		// Calculamos la longitud de los genes
		for (int i = 0; i < _numGenes; i++) {
			
			_longitudGen[i] = calcularLongGen(i,tolerancia);
			setLongitudCromosoma(getLongitudCromosoma() + _longitudGen[i]);
			// Creamos el array de genes del tamaño adecuado
			_genes[i] = new boolean[_longitudGen[i]];
		}	
	}

	@Override
	public double evalua() {
		for (int i = 0; i < _numGenes; i++) {
			_fenotipo[i] = fenotipo(_genes[i],i);
		}
		return f(); // valor de la función a optimizar
	}

	@Override
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
	public double fenotipo(boolean[] gen, int nGen) {
		return _xMin[nGen] + (_xMax[nGen] - _xMin[nGen]) * 
		bin_dec(gen) / (Math.pow(2, gen.length) - 1);
	}

	@Override
	public Object clone() {
		CromosomaFuncion5 copia = new CromosomaFuncion5();
		
		copia._numGenes = this._numGenes;
		copia._adaptacion = this._adaptacion;
		
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
		
		String txtFenotipo="";
		
		for(int i=0; i<_numGenes; i++)		
			txtFenotipo += "X" + (i+1) + ": " + _fenotipo[i] + "\n";
		
		return txtFenotipo;
	}
}
