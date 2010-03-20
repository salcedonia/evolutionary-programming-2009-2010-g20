package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

import cromosoma.TipoCromosoma;

import logica.AG;

/**
 * Clase que implementa los mÈtodos necesarios para la gestiÛn de la ventana
 * gr·fica del usuario.
 * 
 * @author Grupo20.
 * 
 */
public class Ventana extends JFrame {

	// CONSTANTES
	private static final long serialVersionUID = 1L;
	private static final int ALTO = 640;
	private static final int ANCHO = 600;
	private static final String ICONO = "AGSIcono.gif";
	
	// Valores por defecto de las opciones
	private static final String NUM_POBLACION_DEF = "100";
	private static final String NUM_GENERACIONES_DEF = "100";
	private static final String PROB_CRUCE_DEF = "0.7";
	private static final String PROB_MUTACION_DEF = "0.1";
	private static final String TOLERANCIA_DEF = "0.0001";

	/**
	 * Clase que se encarga del AGS.
	 */
	private AG _AG;

	/**
	 * Panel Principal
	 */
	private JPanel _panelPrincipal = null;
	/**
	 * Panel de opciones.
	 */
	private JPanel _panelOpciones = null;
	
	/**
	 * Panel de la gr�fica de aptitudes.
	 */
	private Plot2DPanel _panelAptitud = null;
	
	/**
	 * Aptitudes para el eje de abscisas del Mejor de cada generaci�n.
	 */
	private double[] _yAptitudMejor = null;
	
	/**
	 * Aptitudes para el eje de abscisas de las medias de cada generaci�n.
	 */
	private double[] _yAptitudMedia = null;
	
	// ---------------INTERFAZ ----------------//

	/**
	 * Caja de texto de la derecha.
	 */
	private JTextField _txtInforme;

	/**
	 * Panel de cuerpo del panel de opciones.
	 */
	private JPanel _panelBodyOpciones;

	/**
	 * Panel de botones del panel de opciones.
	 */
	private JPanel _panelBotonesOpciones;

	/**
	 * Etiqueta de numero de generaciones.
	 */
	private JLabel _lblNumGeneraciones;

	/**
	 * Etiqueta de seleccion de funcion a evaluar.
	 */
	private JLabel _lblSeleccionFuncion;

	/**
	 * Seleccion de la funcion a evaluar.
	 */
	private JComboBox _cmbSeleccionFuncion;

	/**
	 * Etiqueta de tamanio de poblacion.
	 */
	private JLabel _lblTamPoblacion;

	/**
	 * Etiqueta de probabilidad de cruce.
	 */
	private JLabel _lblProbCruce;

	/**
	 * Etiqueta de probabilidad de nmutacion.
	 */
	private JLabel _lblProbMutacion;

	/**
	 * Etiqueta de precision.
	 */
	private JLabel _lblPrecision;

	/**
	 * Etiqueta de valor de N.
	 */
	private JLabel _lblValorN;

	/**
	 * Etiqueta de Seleccion de elitismo.
	 */
	private JLabel _lblSeleccionElitismo;

	/**
	 * Campo de texto de numero de generaciones.
	 */
	private JTextField _txtNumGeneraciones;

	/**
	 * Campo de texto de tamanio de poblacion.
	 */
	private JTextField _txtTamPoblacion;

	/**
	 * Campo de texto de probabilidad de cruce.
	 */
	private JTextField _txtProbCruce;

	/**
	 * Campo de texto de probabilidad de mutacion.
	 */
	private JTextField _txtProbMutacion;

	/**
	 * Campo de texto de precision.
	 */
	private JTextField _txtPrecision;

	/**
	 * Campo de texto de Valor de N.
	 */
	private JTextField _txtValorN;

	/**
	 * Campo de texto de seleccion de elitismo.
	 */
	private JComboBox _cmbSeleccionElitismo;

	/**
	 * Boton de comienzo del AGS.
	 */
	private JButton _btnComenzar;

	/**
	 * Tipo de cromosoma a crear. Por defecto es la FunciÛn 1.
	 */
	private TipoCromosoma _tipoCromosoma = TipoCromosoma.FUNCION1;

	/**
	 * Indica si se aplica elitismo en el algoritmo o no.
	 */
	private boolean _elitismo = false;

	/**
	 * Panel de pestañas.
	 */
	private JTabbedPane _panelPestanas;
	
	// VARIABLES PARA EL ALGORITMO
	/**
	 * Numero de generaciones.
	 */
	private int _numGeneraciones;
	
	/**
	 * Tamaño de la poblacion.
	 */
	private int _tamPoblacion;
	
	/**
	 * Probabilidad de cruce.
	 */
	private double _probCruce;
	
	/**
	 * Probabilidad de Mutacion.
	 */
	private double _probMutacion;
	
	/**
	 * Precision del algoritmo.
	 */
	private double _precision;
	
	/**
	 * Valor de N del algoritmo.
	 */
	private int _valorN;

	/**
	 * Constructor de la clase Ventana.
	 */
	public Ventana() {

		// Set de las caracterÌsticas de la ventana
		setTitle("Practica 1: AGS");
		setIconImage(new ImageIcon(ICONO).getImage());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(10, 50, ANCHO, ALTO);
		setLocationRelativeTo(null);

		// InicializaciÛn de los elementos de la ventana
		iniciaInterfaz();
		setEnabled(true);
		setVisible(true);
	}

	/**
	 * Crea los elementos de la ventana.
	 */
	private void iniciaInterfaz() {

		// Se crea el panel de pestañas
		_panelPestanas = new JTabbedPane();
		_panelPestanas.add("Parametros", creaPanelPrincipal());
		_panelPestanas.add("Aptitud", creaPanelAptitud());
		_panelPestanas.add("Funcion", creaPanelFuncion());

		// Crea el panel de contenido
		setContentPane(_panelPestanas);
	}

	/**
	 * Crea el panel para mostrar las gráficas correspondientes a la función.
	 * 
	 * @return El panel para mostrar las gráficas correspondientes a la función.
	 */
	private Component creaPanelFuncion() {

		// TODO Auto-generated method stub
		return new JPanel();
	}

	/**
	 * Crea el panel para mostrar las gráficas correspondientes a la aptitud.
	 * 
	 * @return El panel para mostrar las gráficas correspondientes a la aptitud.
	 */
	private Component creaPanelAptitud() {

		_panelAptitud = new Plot2DPanel();
		
		return _panelAptitud;
	}

	/**
	 * Crea el panel principal de la ventana.
	 * 
	 * @return El panel principa de la ventana con todos sus elementos.
	 */
	private JPanel creaPanelPrincipal() {

		_panelPrincipal = new JPanel();
		_panelPrincipal.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 0.7;
		constraints.weightx = 0.6;

		// Aniadimos todas las opciones de la aplicaciÛn.
		_panelPrincipal.add(creaPanelOpciones(), constraints);

		// Creamos el panel de texto que informa del analisis
		_txtInforme = new JTextField();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 0.7;
		constraints.weightx = 0.4;
		_panelPrincipal.add(_txtInforme, constraints);

		return _panelPrincipal;
	}

	/**
	 * Crea el panel con todas las opciones de la aplicaciÛn.
	 * 
	 * @return El panel con todas las opciones de la aplicaciÛn.
	 */
	private JPanel creaPanelOpciones() {

		_panelOpciones = new JPanel();
		_panelOpciones.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 1.0;
		constraints.weightx = 1.0;

		// Aniade las opciones del panel de opciones
		creaBodyOpciones();
		_panelOpciones.add(_panelBodyOpciones, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 0.1;
		constraints.weightx = 1.0;

		// Aniade los botones de la aplicaciÛn.
		creaBotonesOpciones();
		_panelOpciones.add(_panelBotonesOpciones, constraints);

		return _panelOpciones;
	}

	/**
	 * Crea las opciones del cuerpo del panel de opciones donde se situan todos
	 * los elementos de configuraciÛn del AGS.
	 */
	private void creaBodyOpciones() {

		_panelBodyOpciones = new JPanel();
		_panelBodyOpciones.setLayout(new GridBagLayout());

		// Creamos todas las etiquetas informativas
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weighty = 1.0;
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		_lblSeleccionFuncion = new JLabel("Funcion a Evaluar:");
		_panelBodyOpciones.add(_lblSeleccionFuncion, constraints);

		_lblNumGeneraciones = new JLabel("Numero de Generaciones:");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblNumGeneraciones, constraints);

		_lblTamPoblacion = new JLabel("Tamaño de la Poblacion:");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblTamPoblacion, constraints);

		_lblProbCruce = new JLabel("Probabilidad de Cruce:");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblProbCruce, constraints);

		_lblProbMutacion = new JLabel("Probabilidad de Mutacion:");
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblProbMutacion, constraints);

		_lblPrecision = new JLabel("Precision:");
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblPrecision, constraints);

		_lblValorN = new JLabel("Valor de N:");
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblValorN, constraints);

		_lblSeleccionElitismo = new JLabel("Seleccion por elitismo:");
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblSeleccionElitismo, constraints);

		// Creamos todos los cuadros de texto correspondientes

		String[] funcionesStrings = { "Funcion1", "Funcion2", "Funcion3",
				"Funcion4", "Funcion5" };

		_cmbSeleccionFuncion = new JComboBox(funcionesStrings);
		_cmbSeleccionFuncion
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Funcion1"))
							_tipoCromosoma = TipoCromosoma.FUNCION1;
						if (seleccion.matches("Funcion2"))
							_tipoCromosoma = TipoCromosoma.FUNCION2;
						if (seleccion.matches("Funcion3"))
							_tipoCromosoma = TipoCromosoma.FUNCION3;
						if (seleccion.matches("Funcion4"))
							_tipoCromosoma = TipoCromosoma.FUNCION4;
						if (seleccion.matches("Funcion5"))
							_tipoCromosoma = TipoCromosoma.FUNCION5;
					}
				});

		constraints.weightx = 1.4;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_cmbSeleccionFuncion, constraints);

		_txtNumGeneraciones = new JTextField(NUM_GENERACIONES_DEF);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtNumGeneraciones, constraints);

		_txtTamPoblacion = new JTextField(NUM_POBLACION_DEF);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtTamPoblacion, constraints);

		_txtProbCruce = new JTextField(PROB_CRUCE_DEF);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtProbCruce, constraints);

		_txtProbMutacion = new JTextField(PROB_MUTACION_DEF);
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtProbMutacion, constraints);

		_txtPrecision = new JTextField(TOLERANCIA_DEF);
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtPrecision, constraints);

		_txtValorN = new JTextField();
		_txtValorN.setText("1");
		_txtValorN.setEnabled(false);
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtValorN, constraints);

		String[] elitismoStrings = { "Si", "No" };

		_cmbSeleccionElitismo = new JComboBox(elitismoStrings);
		_cmbSeleccionElitismo.setSelectedIndex(1);
		_cmbSeleccionElitismo
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Si"))
							_elitismo = true;
						else if (seleccion.matches("No"))
							_elitismo = false;
					}
				});

		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_cmbSeleccionElitismo, constraints);

		// Borde del panel
		_panelBodyOpciones.setBorder(new CompoundBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(0,
				10, 10, 10)));
	}

	/**
	 * Crea la parte de los botones del panel de opciones.
	 */
	private void creaBotonesOpciones() {

		_panelBotonesOpciones = new JPanel();
		_panelBotonesOpciones.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;

		_btnComenzar = new JButton("Comenzar");
		_btnComenzar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				if (parametrosOk())
					comenzarAGS();
			}
		});
		_panelBotonesOpciones.add(_btnComenzar, constraints);
	}

	/**
	 * Configura todo lo necesario para la evaluacion del AGS.
	 */
	private void comenzarAGS() {

		// Creamos el objeto encargado del algoritmo genetico simple
		_AG = new AG(_numGeneraciones, _tamPoblacion, _probCruce, _probMutacion, 
				_precision, _valorN, _elitismo, _tipoCromosoma);

		// Crea poblaciÛn inicial de cromosomas
		_AG.inicializa();
		
		// Inicializa las componentes de las gr�ficas
		inicializaGraficas();

		// Eval˙a los individuos y coge el mejor
		_AG.evaluarPoblacion();

		while (!_AG.terminado()) {
			_AG.aumentarGeneracion();
			_AG.seleccion();
			_AG.reproduccion();
			_AG.mutacion();
			_AG.evaluarPoblacion();
			guardaDatosGraficas();
		}

		// Actualizamos las gr�ficas
		imprimeDatosGraficas();
		
		// Mostramos el mejor individuo
		if(_AG.getElMejor() != null)
		   _txtInforme.setText("El mejor valor es "+_AG.getElMejor().toString()+"\n "+"Alcanza un m�ximo de: "+_AG.getElMejor().f());
	}
	
	/**
	 * Inicia el panel de las gr�ficas y los vectores para sus funciones.
	 */
	private void inicializaGraficas() {
		
		// Inicializa un nuevo panel borrando los anteriores resultados
		_panelAptitud.removeAllPlots();
		
		// Fuente general para la gr�fica
		_panelAptitud.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
				
		// Cambia los nombres a los ejes
		_panelAptitud.setAxisLabels("N�mero de Generaci�n","Aptitud");
		
		// Pone una leyenda en la parte baja de la gr�fica
		_panelAptitud.addLegend("SOUTH");
				
		// T�tulo
		BaseLabel titulo = new BaseLabel("Comparativa de Aptitud", Color.BLACK, 0.5, 1.1);
		titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20)); // fuente para el t�tulo
		_panelAptitud.addPlotable(titulo);
		
		// Eje de abscisas (N�mero de Generaci�n)
		// Cambios en la posici�n y el �ngulo
		_panelAptitud.getAxis(0).setLightLabelAngle(-Math.PI / 4);
		_panelAptitud.getAxis(0).setLabelPosition(0.5, -0.15);
		// fuente para el nombre del eje
		_panelAptitud.getAxis(0).setLabelFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
		// fuente para la numeraci�n del eje
		_panelAptitud.getAxis(0).setLightLabelFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
 
		// Eje de ordenadas (Aptitud)
		// Cambios en la posici�n y el �ngulo
		_panelAptitud.getAxis(1).setLightLabelAngle(-Math.PI / 4);
		_panelAptitud.getAxis(1).setLabelPosition(-0.15, 0.5);
		_panelAptitud.getAxis(1).setLabelAngle(-Math.PI / 2);
		// fuente para el nombre del eje
		_panelAptitud.getAxis(1).setLabelFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
		// fuente para la numeraci�n del eje
		_panelAptitud.getAxis(1).setLightLabelFont(new Font(Font.SANS_SERIF,Font.BOLD,14));

		// Creaci�n de los vectores para almacenar las y
		_yAptitudMedia = new double[_numGeneraciones];
		_yAptitudMejor = new double[_numGeneraciones];
	}
	
	/**
	 * Almacena para la generaci�n actual los datos a recoger para las gr�ficas.
	 */
	private void guardaDatosGraficas() {
		
		// Guarda la aptitud media
		_yAptitudMedia[_AG.getNumGeneracion()-1] = _AG.getAptitudMedia();
		
		// Guarda la aptitud del mejor
		_yAptitudMejor[_AG.getNumGeneracion()-1] = _AG.getElMejor().getAptitud();
	}
	
	/**
	 * Imprime los resultados guardados para las gr�ficas.
	 */
	private void imprimeDatosGraficas() {
		
		// Imprime en la gr�fica de aptitudes las componentes X para
		// aptitudes media de la poblaci�n y del mejor de cada generaci�n
		double[] xGeneracion = new double[_numGeneraciones];
		
		for (int i = 0; i < _numGeneraciones; i++) {
			xGeneracion[i] = i + 1;
		}
		
		_panelAptitud.addLinePlot("El Mejor", Color.BLUE, xGeneracion, _yAptitudMejor);
		_panelAptitud.addLinePlot("Media", Color.GREEN, xGeneracion, _yAptitudMedia);
		
	}

	/**
	 * Comprueba que todos los valores introducidos por el usuario son validos
	 * para proceder a la evaluaciÛn de la funciÛn correspondiente.
	 * 
	 * @return Verdadero si todos los par·metros son v·lidos y falso en caso
	 *         contrario.
	 */
	private boolean parametrosOk() {

		return numGeneracionesOk() && tamPoblacionOk() && probCruceOk() && probMutacionOk() && precisionOk() && valorNOk();
	}

	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de numero de Generaciones. Cuando
	 * ha comprobado que el resultado es optimo entonces guarda el valor en la variable entera correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	private boolean numGeneracionesOk() {
		
		int numGeneraciones;
		
		try{
			
			if(_txtNumGeneraciones.getText().matches("")){
				JOptionPane.showMessageDialog(this, "!Debe introducir el Numero de Generaciones!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				numGeneraciones = Integer.parseInt(_txtNumGeneraciones.getText());
			
				if(numGeneraciones < 0){
					JOptionPane.showMessageDialog(this, "!El Numero de Generaciones tiene que ser un numero entero positivo!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}else
					// Guardamos el resultado de la validación
					_numGeneraciones = numGeneraciones;
			}
		}catch(NumberFormatException e){
			
			JOptionPane.showMessageDialog(this, "!Numero de Generaciones tiene que ser un numero entero!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de Tamaño de Poblacion. Cuando
	 * ha comprobado que el resultado es optimo entonces guarda el valor en la variable entera correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	private boolean tamPoblacionOk() {
		
		int tamPoblacion;
		
		try{
			
			if(_txtTamPoblacion.getText().matches("")){
				JOptionPane.showMessageDialog(this, "!Debe introducir el Tamaño de la poblacion!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				tamPoblacion = Integer.parseInt(_txtTamPoblacion.getText());
			
				if(tamPoblacion < 0){
					JOptionPane.showMessageDialog(this, "!El Tamaño de la Poblacion tiene que ser un numero entero positivo!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}else
					// Guardamos el resultado de la validación
					_tamPoblacion = tamPoblacion;
			}
		}catch(NumberFormatException e){
			
			JOptionPane.showMessageDialog(this, "!El Tamaño de la Poblacion tiene que ser un numero entero!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de Probablidad de Cruce. Cuando
	 * ha comprobado que el resultado es optimo entonces guarda el valor en la variable real correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	private boolean probCruceOk() {
		
		double probCruce;
		
		try{
			
			if(_txtProbCruce.getText().matches("")){
				JOptionPane.showMessageDialog(this, "!Debe introducir la Probabilidad de Cruce!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				probCruce = Double.parseDouble(_txtProbCruce.getText());
			
				if(probCruce >= 0 && probCruce <= 1){
					// Guardamos el resultado de la validación
					_probCruce = probCruce;
				}else{
					JOptionPane.showMessageDialog(this, "!La Probabilidad de Cruce tiene que ser un numero entero positivo entre 0 y 1!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}catch(NumberFormatException e){
			
			JOptionPane.showMessageDialog(this, "!La Probabilidad de Cruce tiene que ser un numero entero!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de Probablidad de Mutacion. Cuando
	 * ha comprobado que el resultado es optimo entonces guarda el valor en la variable real correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	private boolean probMutacionOk() {
		
		double probMutacion;
		
		try{
			
			if(_txtProbMutacion.getText().matches("")){
				JOptionPane.showMessageDialog(this, "!Debe introducir la Probabilidad de Mutacion!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				probMutacion = Double.parseDouble(_txtProbMutacion.getText());
			
				if(probMutacion >= 0 && probMutacion <= 1){
					// Guardamos el resultado de la validación
					_probMutacion = probMutacion;
				}else{
					JOptionPane.showMessageDialog(this, "!La Probabilidad de Mutacion tiene que ser un numero entero positivo entre 0 y 1!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}catch(NumberFormatException e){
			
			JOptionPane.showMessageDialog(this, "!La Probabilidad de Mutacion tiene que ser un numero entero!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de Precision. Cuando
	 * ha comprobado que el resultado es optimo entonces guarda el valor en la variable real correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	private boolean precisionOk() {
		
		double precision;
		
		try{
			
			if(_txtPrecision.getText().matches("")){
				JOptionPane.showMessageDialog(this, "!Debe introducir la Precision!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				precision = Double.parseDouble(_txtPrecision.getText());
			
				if(precision >= 0 && precision <= 1){
					// Guardamos el resultado de la validación
					_precision = precision;
				}else{
					JOptionPane.showMessageDialog(this, "!La Precision tiene que ser un numero entero positivo entre 0 y 1!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}catch(NumberFormatException e){
			
			JOptionPane.showMessageDialog(this, "!La Precision tiene que ser un numero entero!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de Valor de N. Cuando
	 * ha comprobado que el resultado es optimo entonces guarda el valor en la variable entera correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	private boolean valorNOk() {
		
		int valorN;
		
		try{
			
			if(_txtValorN.getText().matches("")){
				JOptionPane.showMessageDialog(this, "!Debe introducir el Valor de N!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				valorN = Integer.parseInt(_txtTamPoblacion.getText());
			
				if(valorN < 0){
					JOptionPane.showMessageDialog(this, "!El Valor de N tiene que ser un numero entero positivo!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}else
					// Guardamos el resultado de la validación
					_valorN = valorN;
			}
		}catch(NumberFormatException e){
			
			JOptionPane.showMessageDialog(this, "!El Valor de N tiene que ser un numero entero!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
}