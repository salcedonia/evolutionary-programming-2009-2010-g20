package cromosoma;

import utils.Matematicas;

/**
 * Clase que implementa la clase cromosoma para la funcion 3 a evaluar.
 * 
 * @author Grupo20.
 */
public class CromosomaFuncion3 extends Cromosoma {

	// Extremos del intervalo de la funcion.
	private double[] _xMin;
	private double[] _xMax;

	// Longitud del gen
	private int[] _longitudGen;
	
	/**
	 * Constructor por defecto de la clase CromosomaFuncion3.
	 */
	public CromosomaFuncion3() {
		
	}
	
	public CromosomaFuncion3(double tolerancia) {
		// Establecemos el numero de genes del problema 3
		_numGenes = 1;
		
		// Establecemos los intervalos para cada gen
		_xMin = new double[_numGenes];
		_xMax = new double[_numGenes];
		_xMin[0] = 0;
		_xMax[0] = 25;
				
		_longitudGen = new int[_numGenes];
		_fenotipo = new double[_numGenes];
		_genes = new boolean[_numGenes][];
		setLongitudCromosoma(0);
		
		// Calculamos la longitud de los genes
		for (int i = 0; i < _numGenes; i++) {
			
			_longitudGen[i] = calcularLongGen(i,tolerancia);
			setLongitudCromosoma(getLongitudCromosoma() + _longitudGen[i]);
			// Creamos el array de genes del tamanio adecuado
			_genes[i] = new boolean[_longitudGen[i]];
		}
	}

	@Override
	public double evalua() {
		for (int i = 0; i < _numGenes; i++) {
			_fenotipo[i] = fenotipo(_genes[i],i);
		}
		return f(); // valor de la funcion a optimizar
	}

	@Override
	public int calcularLongGen(int nGen, double tolerancia) {
		return (int) Matematicas.log2(1 + ((_xMax[nGen] - _xMin[nGen])/tolerancia));
	}

	@Override
	public double f() {
		double x = _fenotipo[0];
		return Math.sin(x)/(1 + Math.sqrt(x) + (Math.cos(x)/(1+x)));
	}

	@Override
	public double fenotipo(boolean[] gen, int nGen) {
		return _xMin[nGen] + (_xMax[nGen] - _xMin[nGen]) * 
		bin_dec(gen) / (Math.pow(2, gen.length) - 1);
	}

	@Override
	public Object clone() {
		CromosomaFuncion3 copia = new CromosomaFuncion3();
		
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
		copia._genes = new boolean[_numGenes][];
		for (int i = 0; i < _numGenes; i++) {
			copia._fenotipo[i] = _fenotipo[i];
			copia._genes[i] = new boolean[_longitudGen[i]];
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
