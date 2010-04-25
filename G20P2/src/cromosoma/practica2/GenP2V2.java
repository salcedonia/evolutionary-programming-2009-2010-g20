package cromosoma.practica2;

/**
 * Clase que implementa la clase gen para la version 2 de la práctica 2.
 * 
 * @author Grupo20.
 */
public class GenP2V2 extends GenP2 {

	private int _dia = 0;
	
	/**
	 * Array de dias de la semana.
	 */
	public static String[] DIAS = { "Vacio", "Lunes", "Martes",
			"Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
	
	/**
	 * Constructora por defecto.
	 */
	public GenP2V2() {
		
		super(0);
		
		setDia(0);
	}
	
	/**
	 * Constructora del gen para la ciudad.
	 */
	public GenP2V2(int ciudad,int dia) {
		
		super(ciudad);
		
		setDia(dia);
	}

	/**
	 * Cambia el dia de la semana asignado a la ciudad.
	 * 
	 * @param dia El nuevo dia asignado de visita a la ciudad.
	 */
	public void setDia(int dia) {
		this._dia = dia;
	}

	/**
	 * Obtiene el dia de la semana asignado a la ciudad.
	 * 
	 * @return El dia asignado de visita a la ciudad.
	 */
	public int getDia() {
		return _dia;
	}
	
	/**
	 * Calcula el numero de dias entre dos dias de la semana
	 * 
	 * @param dia1 El dia de partida.
	 * @param dia2 El dia de llegada.
	 * @return Diferencia de dias entre 2 dias de la semana.
	 */
	public static int numDias(int dia1, int dia2) {
		
		if (dia2 > dia1) return dia2 - dia1;
		else if (dia2 < dia1) return dia2 + 7 - dia1;
		else return 7;
	}
	
	/**
	 * Suma una cantidad de dias al primer argumento.
	 * 
	 * @param dia El dia de partida.
	 * @param sumaDias Numero de dias a sumar.
	 * @return El dia de la semana resultante.
	 */
	public static int sumaDias(int dia, int sumaDias) {
		
		dia += sumaDias;
		
		dia--;
		
		dia %= 7;
		
		dia++;
		
		return dia;
	}
	
	@Override
	public Object clone() {
		
		GenP2V2 copia = new GenP2V2();
		
		copia._gen = new Integer((Integer)_gen);
		copia._dia = _dia;
		
		return copia;
	}
	
}
