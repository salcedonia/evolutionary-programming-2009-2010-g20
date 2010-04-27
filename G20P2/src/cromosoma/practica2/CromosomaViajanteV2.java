package cromosoma.practica2;

import java.util.Random;

import cromosoma.Cromosoma;
import cromosoma.Gen;

public class CromosomaViajanteV2 extends Cromosoma {

	
	/**
	 * Array de restricciones de visita de dias de las ciudades.
	 */
	private final static int[][] DIAS_NO_VISITABLES = {
			{ 6, 7 },
			{ 5, 6, 7 },
			{ 4, 5, 6, 7 },
			{ 6, 7 },
			{ 6, 7 },
			{ 2, 6, 7 },
			{ 3, 5, 6, 7 },
			{ 1, 5, 6, 7 },
			{ 1, 6, 7 },
			{ 4, 6, 7 },
			{ 4, 5, 6, 7 },
			{ 5, 6, 7 },
			{ 1, 2, 6, 7 },
			{ 3, 6, 7 },
			{ 6, 7 },
			{ 4, 5, 6, 7 },
			{ 3, 4, 5, 6, 7 },
			{ 6, 7 },
			{ 6, 7 },
			{ 1, 6, 7 },
			{ 6, 7 },
			{ 6, 7 },
			{ 6, 7 },
			{ 1, 2, 3, 6, 7 },
			{ 3, 6, 7 },
			{ 6, 7 },
			{ 6, 7 },
			{ 1, 6, 7 } };
	
	/**
	 * Constructor de la clase CromosomaViajante.
	 */
	public CromosomaViajanteV2() {

		// Cada gen tiene un fenotipo
		_numGenes = 27;

		// Establecemos la longitud del cromosoma
		_longitudCromosoma = 27;

		// Creamos el fenotipo que en este caso coincide con el valor del gen
		_fenotipo = new double[_numGenes];

		// Creamos el array de genes
		_genes = new GenP2V2[_numGenes];
		
		for (int i = 0; i < _numGenes; i++) {
			
			_genes[i] = new GenP2V2();
		}
		
	}
	
	@Override
	public void inicializaCromosoma() {

		Random generador = new Random();
		
		int ultimoDia = 1;

		for (int i = 0; i < _numGenes; i++) {

			int aleatorio;

			do{
				// Generamos un numero aleatorio entre 0 y 27, pero descartamos el 0
				aleatorio = generador.nextInt(_longitudCromosoma + 1);	
			}while (repetido(aleatorio, _genes) || aleatorio == 0);
			
			
			_genes[i] = new GenP2V2(aleatorio,getPrimerDia(aleatorio,ultimoDia));
			
			int diferenciaDias = GenP2V2.numDias( ultimoDia, ((GenP2V2)_genes[i]).getDia() );
			
			ultimoDia = GenP2V2.sumaDias(ultimoDia, diferenciaDias);
		}
	}
	
	/**
	 * Indica si un valor se encuentra en el array de genes o no.
	 * 
	 * @param num
	 *            Numero a comprobar.
	 * @param genes
	 * 		      Array de genes a comprobar.
	 * 
	 * @return Verdadero si ya esta y falso en caso contrario.
	 */
	private boolean repetido(int num, Gen[] genes) {

		boolean esta = false;
		int i = 0;

		// Mientras que no este y hayamos procesado todos los rellenos
		while (!esta && i < genes.length && ((Integer)genes[i].getGen() != 0)) {

			if (num == (Integer)genes[i].getGen())
				esta = true;
			i++;
		}

		return esta;
	}
	
	/**
	 * Obtiene el primer dia disponible para visitar una ciudad.
	 * 
	 * @param ciudad La ciudad a visitar.
	 * @return El primer dia disponible para visitar una ciudad.
	 */
	private int getPrimerDia(int ciudad, int ultimoDia) {
		
		int dia;
		
		if (ultimoDia == 7) dia = 1;
		else dia = ultimoDia + 1;
		
		if (DIAS_NO_VISITABLES[ciudad] == null) return dia; 
		else {
			
			while (!diaValido(ciudad,dia)) {
				if (dia == 7) dia = 1;
				else dia++;
			}
			
			
		}
		
		return dia;
	}
		
	/**
	 * Comprueba si una ciudad tiene ese dia disponible para su visita.
	 * 
	 * @param ciudad Ciudad a comprobar el dia de visita.
	 * @return Cierto si se puede visitar la ciudad ese dia.
	 *         Falso en caso contrario.
	 */
	public static boolean diaValido(int ciudad, int dia) {
		
		// Depende de las ciudades
		boolean esValido = true;
		
		if (DIAS_NO_VISITABLES[ciudad] == null) return true;
		else {
			
			
			int i = 0;
			while ((esValido) && (i < DIAS_NO_VISITABLES[ciudad].length)) {
				
				esValido = DIAS_NO_VISITABLES[ciudad][i] != dia;
				i++;
			}
		}
		return esValido;
		
	}
	
	@Override
	public Object clone() {

		CromosomaViajanteV2 copia = new CromosomaViajanteV2();
		copia._adaptacion = _adaptacion;
		copia._numGenes = _numGenes;
		;

		// Copia de atributos de la clase padre
		copia.setAptitud(_aptitud);
		copia.setPuntAcumulada(_puntAcumulada);
		copia.setPuntuacion(_puntuacion);
		copia.setLongitudCromosoma(_longitudCromosoma);

		// Copia de los genes

		// Copia del fenotipo y los genes
		copia._fenotipo = new double[_numGenes];
		copia._genes = new GenP2V2[_numGenes];
		for (int i = 0; i < _numGenes; i++) {
			copia._fenotipo[i] = _fenotipo[i];
			for (int j = 0; j < _longitudCromosoma; j++) {
				copia._genes[i] = (GenP2V2)_genes[i].clone();
			}
		}

		return copia;
	}

	@Override
	public double evalua() {

		for (int i = 0; i < _numGenes; i++) {
			_fenotipo[i] = fenotipo(_genes[i], i);
		}
		return f();
	}

	@Override
	public double f() {

		// Calculamos la distancia de Madrid a la primera ciudad elegida
		int distancia = CromosomaViajante.getDist(0, (Integer)_genes[0].getGen());
		int dias = GenP2V2.numDias( 1,((GenP2V2)_genes[0]).getDia());

		// Calculamos las distancias entre las ciudades seleccionadas
		for (int nCiudad = 0; nCiudad < _numGenes; nCiudad++) {
			if(nCiudad == _numGenes-1)
				// Sumamos la distancia de la ultima ciudad elegida con Madrid
				distancia += CromosomaViajante.getDist((Integer)_genes[_longitudCromosoma-1].getGen(), 0);
			else
				distancia += CromosomaViajante.getDist((Integer)_genes[nCiudad].getGen(), (Integer)_genes[nCiudad + 1].getGen());
			if (nCiudad > 0) dias += GenP2V2.numDias( ((GenP2V2)_genes[nCiudad]).getDia(), ((GenP2V2)_genes[nCiudad-1]).getDia());
		}
			
		// Sumamos la distancia de la ultima ciudad elegida con Madrid
		distancia += CromosomaViajante.getDist((Integer)_genes[_longitudCromosoma-1].getGen(), 0);
		
		// Para ir a Madrid de nuevo
		if ( ((GenP2V2)_genes[_numGenes -1]).getDia() == 5) {
			dias += 3;
		}
		else dias++;
				
		return 60 * dias + (0.5 * distancia);
	}

	@Override
	public double fenotipo(Gen gen, int nGen) {

		return (Integer)gen.getGen();
	}

	@Override
	public String toString() {

		String mensaje = "Madrid - Lunes - Dia: 0\n";
		int dias = GenP2V2.numDias( ((GenP2V2)_genes[0]).getDia(), 1);

		for (int i = 0; i < _numGenes; i++) {
			if (i > 0) dias+= GenP2V2.numDias( ((GenP2V2)_genes[i]).getDia(), ((GenP2V2)_genes[i-1]).getDia());
			mensaje += CromosomaViajante.CIUDADES[(Integer)_genes[i].getGen()] +
			" - " + GenP2V2.DIAS[((GenP2V2)_genes[i]).getDia()] +
			" - Dia: " + dias + "\n";
		}
		mensaje += "Madrid\n";

		return mensaje;
	}

}
