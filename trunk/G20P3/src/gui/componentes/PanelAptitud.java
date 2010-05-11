package gui.componentes;

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
public class PanelAptitud extends Plot2DPanel {

	/**
	 * Constante de ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Aptitudes para el eje de abscisas del Mejor Global de cada generacion.
	 */
	private double[] _yAptitudMejorGlobal;
	
	/**
	 * Aptitudes para el eje de abscisas del Mejor Local de cada generaci—n.
	 */
	private double[] _yAptitudMejorLocal;

	/**
	 * Aptitudes para el eje de abscisas de las medias de cada generacion.
	 */
	private double[] _yAptitudMedia;
	
	/**
	 * Constructora del panel de la grafica.
	 */
	public PanelAptitud() {
		
		super();
		
		// Pone una leyenda en la parte baja de la grafica
		addLegend("SOUTH");
		
	}
	
	/**
	 * Inicia el panel de las graficas y los vectores para sus funciones.
	 *
	 * @param numGeneraciones Numero de generaciones del algoritmo genetico.
	 */
	public void inicializaGraficas(int numGeneraciones) {
		
		// Inicializa un nuevo panel borrando los anteriores resultados
		removeAllPlots();
		
		// Fuente general para la gráfica
		setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
				
		// Cambia los nombres a los ejes
		setAxisLabels("Numero de Generacion","Aptitud");
				
		// Titulo
		BaseLabel titulo = new BaseLabel("Comparativa de Aptitud", Color.BLACK, 0.5, 1.1);
		titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20)); // fuente para el titulo
		addPlotable(titulo);
		
		// Eje de abscisas (Numero de Generacion)
		// Cambios en la posicion y el angulo
		getAxis(0).setLightLabelAngle(-Math.PI / 4);
		getAxis(0).setLabelPosition(0.5, -0.15);
		// fuente para el nombre del eje
		getAxis(0).setLabelFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
		// fuente para la numeracion del eje
		getAxis(0).setLightLabelFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
 
		// Eje de ordenadas (Aptitud)
		// Cambios en la posicion y el angulo
		getAxis(1).setLightLabelAngle(-Math.PI / 4);
		getAxis(1).setLabelPosition(-0.15, 0.5);
		getAxis(1).setLabelAngle(-Math.PI / 2);
		// fuente para el nombre del eje
		getAxis(1).setLabelFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
		// fuente para la numeracion del eje
		getAxis(1).setLightLabelFont(new Font(Font.SANS_SERIF,Font.BOLD,14));

		// Creacion de los vectores para almacenar las y
		_yAptitudMedia = new double[numGeneraciones];
		_yAptitudMejorGlobal = new double[numGeneraciones];
		_yAptitudMejorLocal = new double[numGeneraciones];
     }
	
	/**
	 * Almacena para la generacion actual los datos a recoger para las graficas.
	 * 
	 * @param AGenetico Algoritmo genetico.
	 */
	public void guardaDatosGraficas(AG AGenetico) {
		
		// Guarda la aptitud media
		_yAptitudMedia[AGenetico.getNumGeneracion()-1] = AGenetico.getAptitudMedia();
		
		// Guarda la aptitud del mejor global de todas las generaciones
		_yAptitudMejorGlobal[AGenetico.getNumGeneracion()-1] = AGenetico.getElMejorGlobal().getAptitud();
		
		// Guarda la aptitud del mejor local de cada generacion
		_yAptitudMejorLocal[AGenetico.getNumGeneracion()-1] = AGenetico.getElMejorLocal().getAptitud();
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
		
		addLinePlot("El Mejor Global", Color.RED, xGeneracion, _yAptitudMejorGlobal);
		addLinePlot("El Mejor Local", Color.GREEN, xGeneracion, _yAptitudMejorLocal);
		addLinePlot("Media", Color.BLUE, xGeneracion, _yAptitudMedia);		
	}
}
