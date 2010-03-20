package gui;

import java.awt.Color;
import java.awt.Font;

import logica.AG;

import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

/**
 * Clase que implementa los metodos necesarios para gestionar el panel de Aptitud que muestra las 
 * graficas de la aptitud de las generaciones del algoritmo genetico simple.
 * 
 * @author Grupo20.
 */
public class PanelAptitud extends Plot2DPanel{

	/**
	 * Constante de ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Aptitudes para el eje de abscisas del Mejor de cada generación.
	 */
	private double[] _yAptitudMejor;
	
	/**
	 * Aptitudes para el eje de abscisas de las medias de cada generación.
	 */
	private double[] _yAptitudMedia;
	
	/**
	 * Inicia el panel de las gráficas y los vectores para sus funciones.
	 *
	 * @param numGeneraciones Numero de generaciones del algoritmo genetico.
	 */
	public void inicializaGraficas(int numGeneraciones) {
		
		// Inicializa un nuevo panel borrando los anteriores resultados
		removeAllPlots();
		
		// Fuente general para la gráfica
		setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
				
		// Cambia los nombres a los ejes
		setAxisLabels("Número de Generación","Aptitud");
		
		// Pone una leyenda en la parte baja de la gráfica
		addLegend("SOUTH");
				
		// Título
		BaseLabel titulo = new BaseLabel("Comparativa de Aptitud", Color.BLACK, 0.5, 1.1);
		titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20)); // fuente para el título
		addPlotable(titulo);
		
		// Eje de abscisas (Número de Generación)
		// Cambios en la posición y el ángulo
		getAxis(0).setLightLabelAngle(-Math.PI / 4);
		getAxis(0).setLabelPosition(0.5, -0.15);
		// fuente para el nombre del eje
		getAxis(0).setLabelFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
		// fuente para la numeración del eje
		getAxis(0).setLightLabelFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
 
		// Eje de ordenadas (Aptitud)
		// Cambios en la posición y el ángulo
		getAxis(1).setLightLabelAngle(-Math.PI / 4);
		getAxis(1).setLabelPosition(-0.15, 0.5);
		getAxis(1).setLabelAngle(-Math.PI / 2);
		// fuente para el nombre del eje
		getAxis(1).setLabelFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
		// fuente para la numeración del eje
		getAxis(1).setLightLabelFont(new Font(Font.SANS_SERIF,Font.BOLD,14));

		// Creación de los vectores para almacenar las y
		_yAptitudMedia = new double[numGeneraciones];
		_yAptitudMejor = new double[numGeneraciones];
	}
	
	/**
	 * Almacena para la generación actual los datos a recoger para las graficas.
	 * 
	 * @param AGenetico Algoritmo genetico.
	 */
	public void guardaDatosGraficas(AG AGenetico) {
		
		// Guarda la aptitud media
		_yAptitudMedia[AGenetico.getNumGeneracion()-1] = AGenetico.getAptitudMedia();
		
		// Guarda la aptitud del mejor
		_yAptitudMejor[AGenetico.getNumGeneracion()-1] = AGenetico.getElMejor().getAptitud();
	}
	
	/**
	 * Imprime los resultados guardados para las graficas.
	 * 
	 * @param numGeneraciones Numero de generaciones del algoritmo genetico
	 */
	public void imprimeDatosGraficas(int numGeneraciones) {
		
		// Imprime en la grafica de aptitudes las componentes X para
		// aptitudes media de la poblacion y del mejor de cada generacion
		double[] xGeneracion = new double[numGeneraciones];
		
		for (int i = 0; i < numGeneraciones; i++) {
			xGeneracion[i] = i + 1;
		}
		
		addLinePlot("El Mejor", Color.BLUE, xGeneracion, _yAptitudMejor);
		addLinePlot("Media", Color.GREEN, xGeneracion, _yAptitudMedia);		
	}
}
