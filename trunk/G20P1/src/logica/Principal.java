package logica;

import gui.Ventana;

/**
 * Clase principal de la aplicacion.
 * 
 * @author Grupo20.
 */
public class Principal {

	/**
	 * Metodo principal de la aplicacion.
	 * 
	 * @param args
	 *            Argumentos de entrada a la aplicacion.
	 */
	public static void main(String[] args) {

		try {
			
			new Ventana();
		} catch (Exception e) {
			
			// A veces muestra una excepcion en el pintado de las graficas en la funcion 5
			// Asi no muestra ninguna excepcion
		}
	}
}
