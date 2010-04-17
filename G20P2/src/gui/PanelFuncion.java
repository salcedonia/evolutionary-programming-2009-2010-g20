package gui;

import java.awt.Color;
import java.awt.Font;

import logica.AG;

import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

/**
 * Panel que implementa los metodos necesarios para la visualizacion de "funciones" mediante 
 * graficas.
 * 
 * @author Grupo20.
 */
public class PanelFuncion extends Plot2DPanel{

	/**
	 * Constante de ID de la clase.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Valores para la aptitud de El Mejor de cada ejecucion.
	 */
	private double[] _yAptitudMejor;
	
	/**
	 * Valores de las aptitudes medias de cada ejecucion.
	 */
	private double[] _yAptitudMedia;
	
	/**
	 * Valores del parametro variable.
	 */
	private double[] _xParametroVariable;
	
	/**
	 * Numero de paso del parametro variable.
	 */
	private int _numPaso;
	
	/**
	 * Constructora del panel de la grafica.
	 */
	public PanelFuncion() {
		super();
		
		// Pone una leyenda en la parte baja de la grafica
		addLegend("SOUTH");
	}
	
	/**
	 * Inicia el panel para la variacion de parametros y los vectores para sus
	 * graficas.
	 * 
	 * @param inicio Valor de inicio para el parametro variable.
	 * @param paso El avance del parametro variable.
	 * @param limite Limite para la variacion de parametros.
	 */
	public void inicializaPanelFuncion(double inicio, double paso, double limite) {
		
		// Calculamos el numero de pasos
		
		_numPaso = 0;
		
		int numPasos = (int) ((limite - inicio) / paso);
		
		if (numPasos == 0) numPasos = 1;
		
		// Imprime en la grafica de parametros variables las componentes X 
		// para el parametro variable
		_xParametroVariable = new double[numPasos];
		
		_xParametroVariable[0] = inicio;
		
		for (int i = 1; i < numPasos; i++) {
			_xParametroVariable[i] = _xParametroVariable[i-1] + paso;
		}
		
		// Inicializa un nuevo panel borrando los anteriores resultados
		removeAllPlots();
		
		// Fuente general para la grafica
		setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
		
		// Cambia los nombres a los ejes
		setAxisLabels("Parametro Variable","Aptitud");
				
		// Título
		BaseLabel titulo = new BaseLabel("Aptitud con Variacion de Parametros", Color.BLACK, 0.5, 1.1);
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
		_yAptitudMedia = new double[numPasos];
		_yAptitudMejor = new double[numPasos];
	}
	
	/**
	 * Completa la informacion de la ejecucion con el valor del parametro.
	 * 
	 * @param AGenetico Algoritmo genetico.
	 */
	public void guardaDatosEjecucion(AG AGenetico) {
		
		if (_numPaso < _yAptitudMejor.length) {
			_yAptitudMejor[_numPaso] = AGenetico.getElMejor().getAptitud();
			_yAptitudMedia[_numPaso] = AGenetico.getAptitudMedia();
			
			_numPaso++;
		}
		
	}
	
	/**
	 * Imprime los resultados de los vectores en la grafica.
	 */
	public void imprimeDatosPanelFuncion() {
		
		addLinePlot("El Mejor de Ejec.", Color.BLUE, _xParametroVariable, _yAptitudMejor);
		addLinePlot("Media de Ejec.", Color.GREEN, _xParametroVariable, _yAptitudMedia);
	}
	
}
