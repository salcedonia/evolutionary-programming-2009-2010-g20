package cromosoma.practica2;

import java.util.Random;

import cromosoma.Cromosoma;

/**
 * Clase que implementa los metodos necesarios para la gestion del cromosoma
 * para el problema del viajante.
 * 
 * @author Grupo20.
 */
public class CromosomaViajante extends Cromosoma {

	/**
	 * Array de distancias entre las ciudades
	 */
	private final static int[][] DISTANCIAS = {
			{},
			{ 251 },
			{ 422, 171 },
			{ 563, 369, 294 },
			{ 115, 366, 537, 663 },
			{ 401, 525, 696, 604, 318 },
			{ 621, 540, 515, 809, 717, 1022 },
			{ 395, 646, 817, 958, 401, 694, 620 },
			{ 237, 488, 659, 800, 243, 536, 583, 158 },
			{ 297, 504, 675, 651, 229, 89, 918, 605, 447 },
			{ 663, 617, 688, 484, 618, 342, 1284, 1058, 900, 369 },
			{ 417, 256, 231, 525, 532, 805, 284, 607, 524, 701, 873 },
			{ 190, 207, 378, 407, 256, 318, 811, 585, 427, 324, 464, 463 },
			{ 400, 354, 525, 332, 457, 272, 908, 795, 637, 319, 263, 610, 201 },
			{ 609, 860, 1031, 1172, 538, 772, 1118, 644, 535, 683, 1072, 1026,
					799, 995 },
			{ 167, 142, 313, 511, 282, 555, 562, 562, 404, 451, 708, 305, 244,
					445, 776 },
			{ 721, 640, 615, 909, 817, 1122, 100, 720, 683, 1018, 1384, 384,
					911, 1008, 1218, 662 },
			{ 434, 363, 353, 166, 534, 438, 868, 829, 671, 485, 335, 584, 278,
					166, 1043, 479, 968 },
			{ 58, 309, 480, 621, 173, 459, 563, 396, 238, 355, 721, 396, 248,
					458, 667, 486, 663, 492 },
			{ 632, 506, 703, 516, 552, 251, 1140, 939, 781, 323, 219, 856, 433,
					232, 1006, 677, 1240, 350, 690 },
			{ 397, 495, 570, 830, 490, 798, 274, 322, 359, 694, 1060, 355, 587,
					797, 905, 406, 374, 831, 339, 1029 },
			{ 335, 264, 415, 228, 435, 376, 804, 730, 572, 423, 367, 520, 179,
					104, 944, 380, 904, 99, 393, 336, 732 },
			{ 333, 584, 855, 896, 255, 496, 784, 359, 201, 407, 796, 725, 511,
					733, 334, 500, 884, 761, 391, 730, 560, 668 },
			{ 465, 515, 490, 802, 558, 866, 156, 464, 427, 762, 1128, 259, 655,
					865, 973, 472, 256, 861, 407, 1097, 118, 779, 628 },
			{ 336, 578, 653, 899, 358, 676, 468, 152, 115, 595, 999, 455, 526,
					736, 650, 464, 568, 770, 278, 968, 244, 671, 316, 312 },
			{ 511, 762, 933, 1074, 440, 674, 1020, 546, 437, 585, 974, 928,
					696, 897, 98, 678, 1120, 945, 569, 908, 807, 846, 236, 875,
					352 },
			{ 544, 473, 482, 219, 644, 436, 997, 939, 781, 506, 265, 713, 388,
					187, 1153, 615, 1097, 129, 602, 313, 941, 209, 877, 1009,
					880, 1055 },
			{ 401, 150, 75, 219, 516, 675, 590, 796, 638, 654, 613, 306, 357,
					444, 1010, 292, 690, 278, 459, 628, 611, 340, 734, 583,
					694, 912, 407 } };

	/**
	 * Array de Ciudades.
	 */
	final static String[] CIUDADES = { "Madrid", "Albacete", "Alicante",
			"Almeria", "Avila", "Badajoz", "Barcelona", "Bilbao", "Burgos",
			"Caceres", "Cadiz", "Castellon", "Ciudad Real", "Cordoba",
			"A Corunia", "Cuenca", "Gerona", "Granada", "Guadalajara",
			"Huelva", "Huesca", "Jaen", "Leon", "Lerida", "Logronio", "Lugo",
			"Malaga", "Murcia" };

	/**
	 * Constructor de la clase CromosomaViajante.
	 */
	public CromosomaViajante() {

		// Cada gen tiene un fenotipo
		_numGenes = 1;

		// Establecemos la longitud del cromosoma
		_longitudCromosoma = 27;

		// Creamos el fenotipo que en este caso coincide con el valor del gen
		_fenotipo = new double[_numGenes];

		// Creamos el array de genes
		_genes = new int[_numGenes][_longitudCromosoma];
	}

	@Override
	public void inicializaCromosoma() {

		Random generador = new Random();

		for (int i = 0; i < _numGenes; i++) {
			for (int j = 0; j < _longitudCromosoma; j++) {

				int aleatorio;
				
				do{
					// Generamos un numero aleatorio entre 0 y 27, pero descartamos el 0
					aleatorio = generador.nextInt(_longitudCromosoma + 1);	
				}while (repetido(aleatorio, _genes[i]) || aleatorio == 0);
				
				_genes[i][j] = aleatorio;
			}
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
	private boolean repetido(int num, int[] genes) {

		boolean esta = false;
		int i = 0;

		// Mientras que no este y hayamos procesado todos los rellenos
		while (!esta && genes[i] != 0) {

			if (num == genes[i])
				esta = true;
			i++;
		}

		return esta;
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
		int distancia = getDist(0, _genes[0][0]);

		// Calculamos las distancias entre las ciudades seleccionadas
		for(int i = 0; i < _numGenes ; i++)
			for (int nCiudad = 0; nCiudad < _longitudCromosoma; nCiudad++)
				distancia += getDist(_genes[i][nCiudad], nCiudad + 1);
			
		// Sumamos la distancia de la ultima ciudad elegida con Madrid
		distancia += getDist(_genes[0][_longitudCromosoma-1], 0);
		
		return distancia;
	}

	@Override
	public double fenotipo(int[] gen, int nGen) {

		return gen[nGen];
	}

	@Override
	public Object clone() {

		CromosomaViajante copia = new CromosomaViajante();
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
		copia._genes = new int[_numGenes][_longitudCromosoma];
		for (int i = 0; i < _numGenes; i++) {
			copia._fenotipo[i] = _fenotipo[i];
			for (int j = 0; j < _longitudCromosoma; j++) {
				copia._genes[i][j] = _genes[i][j];
			}
		}

		return copia;
	}

	@Override
	public String toString() {

		String mensaje = "Madrid\n";

		for (int i = 0; i < _numGenes; i++) {
			for (int j = 0; j < _longitudCromosoma; j++)
				mensaje += CIUDADES[_genes[i][j]] + "\n";
		}
		mensaje += "Madrid\n";

		return mensaje;
	}

	/**
	 * Devuelve la distancia que hay desde la ciudad i hasta la ciudad j.
	 * 
	 * @param i
	 *            Ciudad i
	 * @param j
	 *            Ciudad j
	 * 
	 * @return La distancia que hay desde la ciudad i hasta la ciudad j.
	 */
	private static int getDist(int i, int j) {
		if (i == j)
			return 0;
		if (j > i)
			return getDist(j, i);
		return DISTANCIAS[i][j];
	}
}
