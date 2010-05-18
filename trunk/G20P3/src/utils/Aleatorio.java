package utils;

import java.util.Random;

/**
 * Clase que implementa los metodos necesarios para la generacion de numeros aleatorios.
 * 
 * @author Grupo20.
 */
public class Aleatorio {

	/**
	 * Genera un numero aleatorio entre 0 y 1 de forma que si es mayor o igual que 0.5 es true y falso en 
	 * caso contrario.
	 * 
	 * @return Verdadero o falso de forma aleatoria.
	 */
	public static boolean boolRandom(){
		
		Random generador = new Random();
		if(generador.nextDouble() < 0.5)
			return false;
		else
			return true;
	}
	
	/**
	 * Genera un numero entero aleatorio entre min y max.
	 * 
	 * @param min Minimo valor del rango.
	 * @param max Maximo valor del rango.
	 * 
	 * @return Un numero entero aleatorio entre min y max.
	 */
	public static int intRandom(int min, int max){
		
		Random generador = new Random();
		return generador.nextInt(max - min) + min;
	}
	
	/**
	 * Genera un numero entero aleatorio entre 0 y max - 1.
	 * 
	 * @param min Minimo valor del rango.
	 * @param max Maximo valor del rango.
	 * 
	 * @return Un numero entero aleatorio entre min y max.
	 */
	public static int intRandom(int max){
		
		Random generador = new Random();
		return generador.nextInt(max);
	}
}
