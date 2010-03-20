package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import cromosoma.CromosomaFuncion1;
import cromosoma.CromosomaFuncion2;
import cromosoma.CromosomaFuncion3;
import cromosoma.CromosomaFuncion4;
import cromosoma.CromosomaFuncion5;
import cromosoma.TipoCromosoma;
import logica.AG;
import logica.TipoProblema;

/**
 * Clase que implementa los mÈtodos necesarios para la gestiÛn de la ventana
 * gr·fica del usuario.
 * 
 * @author Grupo20.
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
	 * Clase que se encarga de validar los datos introducidos por el usuario.
	 */
	private ValidadorDatos _validadorDatos;
	
	// ---------------INTERFAZ ----------------//
	
	/**
	 * Panel Principal
	 */
	private JPanel _panelPrincipal;
	/**
	 * Panel de opciones.
	 */
	private JPanel _panelOpciones;
	
	/**
	 * Scroll Panel que incluye a la caja de texo de Informe de datos.
	 */
	private JScrollPane _scrInforme;
	
	/**
	 * Caja de texto de la derecha.
	 */
	private JTextArea _txtInforme;

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
	 * Etiqueta de Seleccion de elitismo.
	 */
	private JLabel _lblSeleccionEscaladoSimple;

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
	 * Lista de seleccion de Elitismo.
	 */
	private JComboBox _cmbSeleccionElitismo;

	/**
	 * Lista de seleccion de Escalado Simple.
	 */
	private JComboBox _cmbSeleccionEscaladoSimple;
	
	/**
	 * Boton de comienzo del AGS.
	 */
	private JButton _btnComenzar;
	
	/**
	 * Panel de la grafica de aptitudes.
	 */
	private PanelAptitud _panelAptitud;
	
	/**
	 * Panel de la grafica de funcion.
	 */
	private PanelFuncion _panelFuncion;
	
	/**
	 * Panel de pestañas.
	 */
	private JTabbedPane _panelPestanas;

	/**
	 * Tipo de cromosoma a crear. Por defecto es la FunciÛn 1.
	 */
	private TipoCromosoma _tipoCromosoma = TipoCromosoma.FUNCION1;
	
	/**
	 * Tipo de problema a resolver.
	 */
	private TipoProblema _tipoProblema = TipoProblema.MAXIMIZACION;

	/**
	 * Indica si se aplica elitismo en el algoritmo o no.
	 */
	private boolean _elitismo = false;
	
	/**
	 * Indica si se aplicac Escalado Simple en el algoritmo o no.
	 */
	private boolean _escaladoSimple = false;

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
		
		// Creamos el validador de datos
		_validadorDatos = new ValidadorDatos(this);
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

		_panelFuncion = new PanelFuncion();
		
		return _panelFuncion;
	}

	/**
	 * Crea el panel para mostrar las gráficas correspondientes a la aptitud.
	 * 
	 * @return El panel para mostrar las gráficas correspondientes a la aptitud.
	 */
	private Component creaPanelAptitud() {

		_panelAptitud = new PanelAptitud();
		
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
		_txtInforme = new JTextArea(5,40);
		_txtInforme.setEditable(false);
		_scrInforme = new JScrollPane(_txtInforme);
		_scrInforme.setAutoscrolls(true);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 0.7;
		constraints.weightx = 0.4;
		_panelPrincipal.add(_scrInforme, constraints);

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

		_lblSeleccionEscaladoSimple = new JLabel("Escalado Simple:");
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblSeleccionEscaladoSimple, constraints);
		
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

		String[] escaladoSimpleStrings = { "Si", "No" };

		_cmbSeleccionEscaladoSimple = new JComboBox(escaladoSimpleStrings);
		_cmbSeleccionEscaladoSimple.setSelectedIndex(1);
		_cmbSeleccionEscaladoSimple
				.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Si"))
							_escaladoSimple = true;
						else if (seleccion.matches("No"))
							_escaladoSimple = false;
					}
				});

		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_cmbSeleccionEscaladoSimple, constraints);
		
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

				if (_validadorDatos.parametrosOk())
					comenzarAGS();
			}
		});
		_panelBotonesOpciones.add(_btnComenzar, constraints);
	}

	/**
	 * Configura todo lo necesario para la evaluacion del AGS.
	 */
	private void comenzarAGS() {
		
		// Inicializamos el tipo de problema a resolver
		setTipoProblema();

		// Creamos el objeto encargado del algoritmo genetico simple
		_AG = new AG(_validadorDatos.getNumGeneraciones(), 
				_validadorDatos.getTamPoblacion(), 
				_validadorDatos.getProbCruce(), 
				_validadorDatos.getProbMutacion(), 
				_validadorDatos.getPrecision(),
				_validadorDatos.getValorN(), 
				_elitismo, 
				_escaladoSimple,
				_tipoCromosoma,
				_tipoProblema);

		// Crea poblaciÛn inicial de cromosomas
		_AG.inicializa();
		
		// Inicializa las componentes de las gr�ficas
		_panelAptitud.inicializaGraficas(_validadorDatos.getNumGeneraciones());

		// Eval˙a los individuos y coge el mejor
		_AG.evaluarPoblacion();

		while (!_AG.terminado()) {
			_AG.aumentarGeneracion();
			_AG.seleccion();
			_AG.reproduccion();
			_AG.mutacion();
			_AG.evaluarPoblacion();
			_panelAptitud.guardaDatosGraficas(_AG);
		}

		// Actualizamos las graficas
		_panelAptitud.imprimeDatosGraficas(_validadorDatos.getNumGeneraciones());
		// TODO: Poner aqui el otro panel
		
		// Mostramos el resultado en el cuadro de texto de informe.
		switch (_tipoCromosoma){
		
			case FUNCION1: 
				// Mostramos el mejor individuo
				_txtInforme.setText("El Mejor Valor es "+_AG.getElMejor().toString()+"\n");
				_txtInforme.append("Alcanza un Maximo de: "+_AG.getElMejor().f());
				break;
			case FUNCION2: 
				_txtInforme.setText("Los Mejores Valores son "+_AG.getElMejor().toString()+"\n");
				_txtInforme.append("Alcanza un Maximo de: "+_AG.getElMejor().f());
				break;
			case FUNCION3: break;
			case FUNCION4: break;
			case FUNCION5: break;
		}
	}
	
	/**
	 * Devuelve el tipo de problema seg�n la funci�n seleccionada actualmente.
	 */
	private void setTipoProblema() {
		
		// Asignaci�n del tipo de problema
		switch (_tipoCromosoma) {

		case FUNCION1:
		case FUNCION2:
			_tipoProblema = TipoProblema.MAXIMIZACION;
			break;
		case FUNCION3:
		case FUNCION4:
		case FUNCION5:
			_tipoProblema = TipoProblema.MINIMIZACION;
			break;
		}
		
	}
	
	/**
	 * Devuelve el campo de texto de Numero de Generaciones.
	 * 
	 * @return El campo de texto de Numero de Generaciones.
	 */
	public JTextField getTxtNumGeneraciones() {
		
		return _txtNumGeneraciones;
	}
	
	/**
	 * Devuelve el campo de texto de Tamanio de Poblacion.
	 * 
	 * @return El campo de texto de Tamanio de Poblacion.
	 */
	public JTextField getTxtTamPoblacion() {
		
		return _txtTamPoblacion;
	}

	/**
	 * Devuelve el campo de texto de Probabilidad de Cruce.
	 * 
	 * @return El campo de texto de Probabilidad de Cruce.
	 */
	public JTextField getTxtProbCruce() {
		
		return _txtProbCruce;
	}

	/**
	 * Devuelve el campo de texto de Probabilidad de Mutacion.
	 * 
	 * @return El campo de texto de Probabilidad de Mutacion.
	 */
	public JTextField getTxtProbMutacion() {
		
		return _txtProbMutacion;
	}
	
	/**
	 * Devuelve el campo de texto de Precision.
	 * 
	 * @return El campo de texto de Precision.
	 */
	public JTextField getTxtPrecision() {
		
		return _txtPrecision;
	}

	/**
	 * Devuelve el campo de texto de Valor de N.
	 * 
	 * @return El campo de texto de Valor de N.
	 */
	public JTextField getTxtValorN() {
		
		return _txtValorN;
	}
}
