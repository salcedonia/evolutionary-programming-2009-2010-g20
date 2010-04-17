package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Implementa una lista ordenada de menor a mayor,bcon elementos duplicados. 
 * Accede y borra el maximo y el minimo en tiempo constante.
 * 
 * @author Grupo20.
 */
public class ListaOrdenada<E> {
	
	/**
	 * Contenedor de los elementos de la lista.
	 */
	private ArrayList<E> _lista;
	
	/**
	 * Comparador para ordenar los elementos.
	 */
	private Comparator<E> _comparador;
	
	/**
	 * Constructora para la lista ordenada.
	 * 
	 * @param comparador El comparador para los elementos.
	 */
	public ListaOrdenada(Comparator<E> comparador) {
		
		_lista = new ArrayList<E>();
		_comparador = comparador;
	}
	
	/**
	 * Inserta ordenadamente el elemento.
	 * 
	 * @param elem Cromosoma a insertar
	 */
	public void add(E elem) {
		
		int posicion = getPos(elem);
		
		_lista.add(posicion, elem);
		
	}
	
	/**
	 * Posicion que ocuparia el elemento insertado.
	 * 
	 * @param elem El elemento a buscar su posicion.
	 * @return La posicion que ocuparía el elemento insertado.
	 */
	private int getPos(E elem) {
		
		int d = _lista.size() - 1;
		int i = 0;
		int retVal = 0;
		boolean encontrado = false;
		
		while ((i <= d) && (!encontrado)) {
			
			int m = (d + i) / 2;
			
			int comparacion = _comparador.compare(elem,_lista.get(m));
			
			if (comparacion == 0) {
				encontrado = true; 
				retVal = m;
			}
			else if (comparacion == -1) {
				d = m - 1;
			}
			else if (comparacion == 1) {
				i = m + 1;
			}
		}
		if (encontrado) return retVal;
		else return i;
		
	}
	
	/**
	 * Devuelve el menor elemento.
	 * 
	 * @return El menor elemento de la lista.
	 */
	public E getMin() {
		return _lista.get(0);
	}
	
	/**
	 * Devuelve el mayor elemento.
	 * 
	 * @return El mayor elemento de la lista.
	 */
	public E getMax() {
		
		return _lista.get(_lista.size()-1);
	}
	
	/**
	 * Quita el menor elemento.
	 */
	public void removeMin() {
		
		_lista.remove(0);
	}
	
	/**
	 * Quita el maximo elemento.
	 */
	public void removeMax() {
		
		_lista.remove(_lista.size()-1);
	}
	
	/**
	 * Devuelve un iterador para recorrer la lista.
	 * 
	 * @return El iterador de la clase.
	 */
	public Iterator<E> iterator() {
		
		return _lista.iterator();
	}
	
}
