package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.JTextComponent;

import logica.AG;
import logica.TipoCruce;
import logica.TipoMutacion;
import logica.TipoProblema;
import logica.TipoSeleccion;
import logica.TipoVersion;

/**
 * Clase que implementa los metodos necesarios para la gestion de la ventana
 * grafica del usuario.
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
	private static final String NUM_ELITE_DEF = "0.01";
	private static final String NUM_ESTIMADO_COPIAS_MEJOR_DEF = "1";
	
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
	 * Etiqueta de seleccion de la version del problema.
	 */
	private JLabel _lblSeleccionVersion;

	/**
	 * Seleccion de la version del problema.
	 */
	private JComboBox _cmbSeleccionVersion;
	
	/**
	 * Etiqueta de seleccion del metodo de seleccion.
	 */
	private JLabel _lblSeleccionSeleccion;
	
	/**
	 * Etiqueta de seleccion del metodo de cruce.
	 */
	private JLabel _lblSeleccionCruce;
	
	/**
	 * Etiqueta de seleccion del metodo de mutacion.
	 */
	private JLabel _lblSeleccionMutacion;

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
	 * Etiqueta de Seleccion de elitismo.
	 */
	private JLabel _lblSeleccionElitismo;

	/**
	 * Etiqueta de tamanio de la elite.
	 */
	private JLabel _lblTamElite;

	/**
	 * Etiqueta de Seleccion de elitismo.
	 */
	private JLabel _lblSeleccionEscaladoSimple;

	/**
	 * Etiqueta de variacion de parametros.
	 */
	private JLabel _lblVariacionParametros;

	/**
	 * Etiqueta del paso de variacion de parametros.
	 */
	private JLabel _lblPasoVariacion;

	/**
	 * Etiqueta del limite de variacion de parametros.
	 */
	private JLabel _lblLimiteVariacion;
	
	/**
	 * Lista de seleccion del metodo de seleccion.
	 */
	private JComboBox _cmbSeleccionSeleccion;
	
	/**
	 * Lista de seleccion del metodo de cruce.
	 */
	private JComboBox _cmbSeleccionCruce;
	
	/**
	 * Lista de seleccion del metodo de mutacion.
	 */
	private JComboBox _cmbSeleccionMutacion;

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
	 * Lista de seleccion de Elitismo.
	 */
	private JComboBox _cmbSeleccionElitismo;

	/**
	 * Campo de texto para el % de tamanio de la elite.
	 */
	private JTextField _txtPorcentageElite;

	/**
	 * Lista de seleccion de Escalado Simple.
	 */
	private JComboBox _cmbSeleccionEscaladoSimple;

	/**
	 * Lista de seleccion de variacion de parametros.
	 */
	private JComboBox _cmbSeleccionVarParametros;

	/**
	 * Campo de texto para el paso de la variacion de parametros.
	 */
	private JTextField _txtPasoVariacion;

	/**
	 * Campo de texto para el limite de la variacion de parametros.
	 */
	private JTextField _txtLimiteVariacion;

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
	 * Panel de pestanias.
	 */
	private JTabbedPane _panelPestanas;

	/**
	 * Barra de Progreso.
	 */
	private JProgressBar _barraProgreso;

	/**
	 * Tipo de version de problema a usar.
	 */
	private TipoVersion _tipoVersion = TipoVersion.VERSION1;
	
	/**
	 * Tipo de seleccion a usar por el algoritmo genetico.
	 */
	private TipoSeleccion _tipoSeleccion = TipoSeleccion.RULETA;

	
	/**
	 * Tipo de cruce a usar por el algoritmo genetico.
	 */
	private TipoCruce _tipoCruce = TipoCruce.PMX;

	
	/**
	 * Tipo de mutacion a usar por el algoritmo genetico.
	 */
	private TipoMutacion _tipoMutacion = TipoMutacion.INSERCION;


	/**
	 * Tipo de problema a resolver.
	 */
	private TipoProblema _tipoProblema = TipoProblema.MAXIMIZACION;

	/**
	 * Tipo de variacion de parametros a realizar.
	 */
	private TipoVariacion _tipoVariacion = TipoVariacion.NINGUNA;

	/**
	 * Indica si se aplica elitismo en el algoritmo o no.
	 */
	private boolean _elitismo = false;

	/**
	 * Indica si se aplica Escalado Simple en el algoritmo o no.
	 */
	private boolean _escaladoSimple = false;
	private JLabel _lblNumEstimadoCopiasMejor;
	private JTextField _txtNumEstimadoCopiasMejor;

	/**
	 * Constructor de la clase Ventana.
	 */
	public Ventana() {

		// Set de las caracteristicas de la ventana
		setTitle("Practica 1: AGS");
		setIconImage(new ImageIcon(ICONO).getImage());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(10, 50, ANCHO, ALTO);
		setLocationRelativeTo(null);

		// Inicializacion de los elementos de la ventana
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

		// Se crea el panel de pestanias
		_panelPestanas = new JTabbedPane();
		_panelPestanas.add("Parametros", creaPanelPrincipal());
		_panelPestanas.add("Aptitud", creaPanelAptitud());
		_panelPestanas.add("Funcion", creaPanelFuncion());

		// Crea el panel de contenido
		setContentPane(_panelPestanas);
	}

	/**
	 * Crea el panel para mostrar las graficas correspondientes a la funcion.
	 * 
	 * @return El panel para mostrar las graficas correspondientes a la funcion.
	 */
	private Component creaPanelFuncion() {

		_panelFuncion = new PanelFuncion();

		return _panelFuncion;
	}

	/**
	 * Crea el panel para mostrar las graficas correspondientes a la aptitud.
	 * 
	 * @return El panel para mostrar las graficas correspondientes a la aptitud.
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

		// Aniadimos todas las opciones de la aplicacion.
		_panelPrincipal.add(creaPanelOpciones(), constraints);

		// Creamos el panel de texto que informa del analisis
		_txtInforme = new JTextArea(5, 40);
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
	 * Crea el panel con todas las opciones de la aplicacion.
	 * 
	 * @return El panel con todas las opciones de la aplicacion.
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

		// Aniade los botones de la aplicacion.
		creaBotonesOpciones();
		_panelOpciones.add(_panelBotonesOpciones, constraints);

		return _panelOpciones;
	}

	/**
	 * Crea las opciones del cuerpo del panel de opciones donde se situan todos
	 * los elementos de configuracion del AGS.
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
		_lblSeleccionVersion = new JLabel("Version del problema:");
		_panelBodyOpciones.add(_lblSeleccionVersion, constraints);
		
		_lblSeleccionSeleccion = new JLabel("Metodo de seleccion:");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblSeleccionSeleccion, constraints);
		
		_lblSeleccionCruce = new JLabel("Metodo de cruce:");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblSeleccionCruce, constraints);
		
		_lblSeleccionMutacion = new JLabel("Metodo de mutacion:");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblSeleccionMutacion, constraints);

		_lblNumGeneraciones = new JLabel("Numero de Generaciones:");
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblNumGeneraciones, constraints);

		_lblTamPoblacion = new JLabel("Tamanio de la Poblacion:");
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblTamPoblacion, constraints);

		_lblProbCruce = new JLabel("Probabilidad de Cruce:");
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblProbCruce, constraints);

		_lblProbMutacion = new JLabel("Probabilidad de Mutacion:");
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblProbMutacion, constraints);

		_lblSeleccionElitismo = new JLabel("Seleccion por Elitismo:");
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblSeleccionElitismo, constraints);
		
		_lblTamElite = new JLabel("Tamanio de la Elite:");
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblTamElite, constraints);
		
		_lblSeleccionEscaladoSimple = new JLabel("Escalado Simple:");
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblSeleccionEscaladoSimple, constraints);

		_lblNumEstimadoCopiasMejor = new JLabel("N¼ Estimado Copias del Mejor:");
		constraints.gridx = 0;
		constraints.gridy = 11;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblNumEstimadoCopiasMejor, constraints);

		_lblVariacionParametros = new JLabel("Variacion de parametros:");
		constraints.gridx = 0;
		constraints.gridy = 12;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblVariacionParametros, constraints);

		_lblPasoVariacion = new JLabel("Paso de la variacion:");
		constraints.gridx = 0;
		constraints.gridy = 13;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblPasoVariacion, constraints);

		_lblLimiteVariacion = new JLabel("Limite de la variacion:");
		constraints.gridx = 0;
		constraints.gridy = 14;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblLimiteVariacion, constraints);

		// Creamos todos los cuadros de texto correspondientes

		String[] versionesStrings = { "Version 1", "Version 2" };

		_cmbSeleccionVersion = new JComboBox(versionesStrings);
		_cmbSeleccionVersion
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Version 1")) {
							_tipoVersion = TipoVersion.VERSION1;
							
							_cmbSeleccionCruce.setEnabled(true);
							_cmbSeleccionMutacion.setEnabled(true);
							
						}
						if (seleccion.matches("Version 2")) {
							_tipoVersion = TipoVersion.VERSION2;
							
							_cmbSeleccionCruce.setEnabled(false);
							_cmbSeleccionCruce.setSelectedIndex(0);
							
							_cmbSeleccionMutacion.setEnabled(false);
							_cmbSeleccionMutacion.setSelectedIndex(0);
						}
						
					}
				});

		constraints.weightx = 1.4;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_cmbSeleccionVersion, constraints);
		
		String[] seleccionStrings = { "Ruleta", "Torneo", "Ranking" };

		_cmbSeleccionSeleccion = new JComboBox(seleccionStrings);
		_cmbSeleccionSeleccion
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Ruleta")) {
							_tipoSeleccion = TipoSeleccion.RULETA;
						}
						else if (seleccion.matches("Torneo")) {
							_tipoSeleccion = TipoSeleccion.TORNEO;
						}
						else if (seleccion.matches("Ranking")) {
							_tipoSeleccion = TipoSeleccion.RANKING;
						}
						
					}
				});

		constraints.weightx = 1.4;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_cmbSeleccionSeleccion, constraints);
		
		String[] cruceStrings = { "PMX", "OX", "Variante OX", "Ciclos CX", 
				"ERX", "Cod. Ordinal", "Propio" };

		_cmbSeleccionCruce = new JComboBox(cruceStrings);
		_cmbSeleccionCruce
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("PMX")) {
							_tipoCruce = TipoCruce.PMX;
						}
						else if (seleccion.matches("OX")) {
							_tipoCruce = TipoCruce.OX;
						}
						else if (seleccion.matches("Variante OX")) {
							_tipoCruce = TipoCruce.VARIANTE_OX;
						}
						else if (seleccion.matches("Ciclos CX")) {
							_tipoCruce = TipoCruce.CICLOS_CX;
						}
						else if (seleccion.matches("ERX")) {
							_tipoCruce = TipoCruce.ERX;
						}
						else if (seleccion.matches("Cod. Ordinal")) {
							_tipoCruce = TipoCruce.COD_ORDINAL;
						}
						else if (seleccion.matches("Propio")) {
							_tipoCruce = TipoCruce.PROPIO;
						}
						
					}
				});

		constraints.weightx = 1.4;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_cmbSeleccionCruce, constraints);
		
		String[] mutacionStrings = { "Insercion", "Intercambio", "Inversion", "Propio" };

		_cmbSeleccionMutacion = new JComboBox(mutacionStrings);
		_cmbSeleccionMutacion
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Insercion")) {
							_tipoMutacion = TipoMutacion.INSERCION;
						}
						else if (seleccion.matches("Intercambio")) {
							_tipoMutacion = TipoMutacion.INTERCAMBIO;
						}
						else if (seleccion.matches("Inversion")) {
							_tipoMutacion = TipoMutacion.INVERSION;
						}
						else if (seleccion.matches("Propio")) {
							_tipoMutacion = TipoMutacion.PROPIO;
						}
						
					}
				});

		constraints.weightx = 1.4;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_cmbSeleccionMutacion, constraints);

		_txtNumGeneraciones = new JTextField(NUM_GENERACIONES_DEF);
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtNumGeneraciones, constraints);

		_txtTamPoblacion = new JTextField(NUM_POBLACION_DEF);
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtTamPoblacion, constraints);

		_txtProbCruce = new JTextField(PROB_CRUCE_DEF);
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtProbCruce, constraints);

		_txtProbMutacion = new JTextField(PROB_MUTACION_DEF);
		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtProbMutacion, constraints);

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

						_txtPorcentageElite.setEnabled(_elitismo);
					}
				});

		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_cmbSeleccionElitismo, constraints);

		_txtPorcentageElite = new JTextField(NUM_ELITE_DEF);
		_txtPorcentageElite.setEnabled(false);
		constraints.gridx = 1;
		constraints.gridy = 9;
		_panelBodyOpciones.add(_txtPorcentageElite, constraints);
		
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

						_txtNumEstimadoCopiasMejor.setEnabled(_escaladoSimple);
					}
				});

		constraints.gridx = 1;
		constraints.gridy = 10;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_cmbSeleccionEscaladoSimple, constraints);
		
		_txtNumEstimadoCopiasMejor = new JTextField(NUM_ESTIMADO_COPIAS_MEJOR_DEF);
		_txtNumEstimadoCopiasMejor.setEnabled(false);
		constraints.gridx = 1;
		constraints.gridy = 11;
		_panelBodyOpciones.add(_txtNumEstimadoCopiasMejor, constraints);
		
		String[] variacionStrings = { "Ninguna",
				"Numero Maximo de Generaciones", "Tamanio de Poblacion",
				"Probabilidad de Cruce", "Probabilidad de Mutacion",
				"Precision", "Valor de N", "Elitismo" };

		_cmbSeleccionVarParametros = new JComboBox(variacionStrings);
		_cmbSeleccionVarParametros.setSelectedIndex(0);
		_cmbSeleccionVarParametros
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Ninguna")) {
							_tipoVariacion = TipoVariacion.NINGUNA;
							_txtPasoVariacion.setEnabled(false);
							_txtLimiteVariacion.setEnabled(false);
						} else {
							if (seleccion
									.matches("Numero Maximo de Generaciones"))
								_tipoVariacion = TipoVariacion.NUM_GENERACION;
							else if (seleccion.matches("Tamanio de Poblacion"))
								_tipoVariacion = TipoVariacion.NUM_POBLACION;
							else if (seleccion.matches("Probabilidad de Cruce"))
								_tipoVariacion = TipoVariacion.PROB_CRUCE;
							else if (seleccion
									.matches("Probabilidad de Mutacion"))
								_tipoVariacion = TipoVariacion.PROB_MUTACION;
							else if (seleccion.matches("Precision"))
								_tipoVariacion = TipoVariacion.PRECISION;
							else if (seleccion.matches("Valor de N"))
								_tipoVariacion = TipoVariacion.VALOR_N;
							else if (seleccion.matches("Elitismo"))
								_tipoVariacion = TipoVariacion.ELITISMO;
							_txtPasoVariacion.setEnabled(true);
							_txtLimiteVariacion.setEnabled(true);
						}
					}
				});

		constraints.gridx = 1;
		constraints.gridy = 12;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_cmbSeleccionVarParametros, constraints);

		_txtPasoVariacion = new JTextField(NUM_ELITE_DEF);
		_txtPasoVariacion.setEnabled(false);
		constraints.gridx = 1;
		constraints.gridy = 13;
		_panelBodyOpciones.add(_txtPasoVariacion, constraints);

		_txtLimiteVariacion = new JTextField(NUM_ELITE_DEF);
		_txtLimiteVariacion.setEnabled(false);
		constraints.gridx = 1;
		constraints.gridy = 14;
		_panelBodyOpciones.add(_txtLimiteVariacion, constraints);

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

		// 
		GridBagConstraints constraints = new GridBagConstraints();

		_btnComenzar = new JButton("Comenzar");
		_btnComenzar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				if (_validadorDatos.parametrosOk(_tipoVariacion)) {

					// Hacemos la ejecucion del algoritmo en otro hilo de
					// ejecucion
					// Para que no se congele la interfaz
					Thread t = new Thread(new Runnable() {
						public void run() {
							// Inicializamos la barra de progreso de nuevo
							_barraProgreso.setValue(0);

							// Desactivamos el boton mientras el analisis
							_btnComenzar.setEnabled(false);

							_barraProgreso.setMaximum(_validadorDatos
									.getNumGeneraciones());

							// Comenzamos el algortimo
							comenzarAGS();
						}
					});
					t.start();
				}
			}
		});

		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;

		_panelBotonesOpciones.add(_btnComenzar, constraints);

		// Barra de Progreso
		_barraProgreso = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		_barraProgreso.setValue(0);
		_barraProgreso.setStringPainted(true);
		_barraProgreso.setPreferredSize(new Dimension(350, 20));

		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;

		_panelBotonesOpciones.add(_barraProgreso, constraints);
	}

	/**
	 * Configura todo lo necesario para la evaluacion del AGS.
	 */
	private void comenzarAGS() {

		_txtInforme.setText("");

		if (_tipoVariacion == TipoVariacion.NINGUNA) {
			comienzaAGSNormal();
		} else {
			comienzaAGSVariacion();
		}
	}

	/**
	 * Ejecuta el algoritmo genetico simple con los datos de los parametros.
	 */
	private void comienzaAGSNormal() {
		/*
		// Inicializamos el tipo de problema a resolver
		setTipoProblema();

		// Creamos el objeto encargado del algoritmo genetico simple
		_AG = new AG(_validadorDatos.getNumGeneraciones(), _validadorDatos
				.getTamPoblacion(), _validadorDatos.getProbCruce(),
				_validadorDatos.getProbMutacion(), _validadorDatos
						.getPrecision(), _validadorDatos.getValorN(),
				_elitismo, _escaladoSimple, _tipoCromosoma, _tipoProblema,
				_validadorDatos.getPorcentageElite(), _validadorDatos
						.getNumEstimadoCopiasMejor());

		// Crea poblacion inicial de cromosomas
		_AG.inicializa();

		// Inicializa las componentes de las graficas
		_panelAptitud.inicializaGraficas(_validadorDatos.getNumGeneraciones());

		// Evalua los individuos y coge el mejor
		_AG.evaluarPoblacion();

		while (!_AG.terminado()) {

			_AG.aumentarGeneracion();

			// Actualizamos el progreso de la barra
			_barraProgreso.setValue(_AG.getNumGeneracion());

			// No hace nada si no la opcion elitismo esta inactiva
			_AG.separaElite();

			_AG.seleccion();
			_AG.reproduccion();
			_AG.mutacion();

			// No hace nada si no la opcion elitismo esta inactiva
			_AG.incluyeElite();

			_AG.evaluarPoblacion();

			// Guardamos los datos de las graficas
			_panelAptitud.guardaDatosGraficas(_AG);
		}

		// Actualizamos las graficas
		_panelAptitud
				.imprimeDatosGraficas(_validadorDatos.getNumGeneraciones());

		// Mostramos el resultado en el cuadro de texto de informe.
		imprimeResultadoConsola();

		// Volvemos a activar el boton
		_btnComenzar.setEnabled(true);
		*/
	}

	/**
	 * Ejecuta varias veces el algoritmo genetico simple con la variacion de
	 * parametros introducida en pasoVariacion y limiteVariacion.
	 */
	private void comienzaAGSVariacion() {

		/*
		double i = 0; // valor variable del parametro
		int nEjecucion = 1; // N de ejecucion

		switch (_tipoVariacion) {

		case NUM_GENERACION:
			i = _validadorDatos.getNumGeneraciones();
			break;
		case NUM_POBLACION:
			i = _validadorDatos.getTamPoblacion();
			break;
		case PROB_CRUCE:
			i = _validadorDatos.getProbCruce();
			break;
		case PROB_MUTACION:
			i = _validadorDatos.getProbMutacion();
			break;
		case PRECISION:
			i = _validadorDatos.getPrecision();
			break;
		case VALOR_N:
			i = _validadorDatos.getValorN();
			break;
		case ELITISMO:
			i = _validadorDatos.getPorcentageElite();
			break;
		}

		double paso = _validadorDatos.getPasoVariacion();
		double limite = _validadorDatos.getLimiteVariacion();

		// Inicializamos el tipo de problema a resolver
		setTipoProblema();

		_panelFuncion.inicializaPanelFuncion(i, paso, limite);

		while (i < limite) {

			switch (_tipoVariacion) {

			case NUM_GENERACION:
				// Creamos el objeto encargado del algoritmo genetico simple
				_AG = new AG((int) i, _validadorDatos.getTamPoblacion(),
						_validadorDatos.getProbCruce(), _validadorDatos
								.getProbMutacion(), _validadorDatos
								.getPrecision(), _validadorDatos.getValorN(),
						_elitismo, _escaladoSimple, _tipoCromosoma,
						_tipoProblema, _validadorDatos.getPorcentageElite(),
						_validadorDatos.getNumEstimadoCopiasMejor());
				break;
			case NUM_POBLACION:
				// Creamos el objeto encargado del algoritmo genetico simple
				_AG = new AG(_validadorDatos.getNumGeneraciones(), (int) i,
						_validadorDatos.getProbCruce(), _validadorDatos
								.getProbMutacion(), _validadorDatos
								.getPrecision(), _validadorDatos.getValorN(),
						_elitismo, _escaladoSimple, _tipoCromosoma,
						_tipoProblema, _validadorDatos.getPorcentageElite(),
						_validadorDatos.getNumEstimadoCopiasMejor());
				break;
			case PROB_CRUCE:
				// Creamos el objeto encargado del algoritmo genetico simple
				_AG = new AG(_validadorDatos.getNumGeneraciones(),
						_validadorDatos.getTamPoblacion(), i, _validadorDatos
								.getProbMutacion(), _validadorDatos
								.getPrecision(), _validadorDatos.getValorN(),
						_elitismo, _escaladoSimple, _tipoCromosoma,
						_tipoProblema, _validadorDatos.getPorcentageElite(),
						_validadorDatos.getNumEstimadoCopiasMejor());
				break;
			case PROB_MUTACION:
				// Creamos el objeto encargado del algoritmo genetico simple
				_AG = new AG(_validadorDatos.getNumGeneraciones(),
						_validadorDatos.getTamPoblacion(), _validadorDatos
								.getProbCruce(), i, _validadorDatos
								.getPrecision(), _validadorDatos.getValorN(),
						_elitismo, _escaladoSimple, _tipoCromosoma,
						_tipoProblema, _validadorDatos.getPorcentageElite(),
						_validadorDatos.getNumEstimadoCopiasMejor());
				break;
			case PRECISION:
				// Creamos el objeto encargado del algoritmo genetico simple
				_AG = new AG(_validadorDatos.getNumGeneraciones(),
						_validadorDatos.getTamPoblacion(), _validadorDatos
								.getProbCruce(), _validadorDatos
								.getProbMutacion(), i, _validadorDatos
								.getValorN(), _elitismo, _escaladoSimple,
						_tipoCromosoma, _tipoProblema, _validadorDatos
								.getPorcentageElite(), _validadorDatos
								.getNumEstimadoCopiasMejor());
				break;
			case VALOR_N:
				// Creamos el objeto encargado del algoritmo genetico simple
				_AG = new AG(_validadorDatos.getNumGeneraciones(),
						_validadorDatos.getTamPoblacion(), _validadorDatos
								.getProbCruce(), _validadorDatos
								.getProbMutacion(), _validadorDatos
								.getPrecision(), (int) i, _elitismo,
						_escaladoSimple, _tipoCromosoma, _tipoProblema,
						_validadorDatos.getPorcentageElite(), _validadorDatos
								.getNumEstimadoCopiasMejor());
				break;
			case ELITISMO:
				// Creamos el objeto encargado del algoritmo genetico simple
				_AG = new AG(_validadorDatos.getNumGeneraciones(),
						_validadorDatos.getTamPoblacion(), _validadorDatos
								.getProbCruce(), _validadorDatos
								.getProbMutacion(), _validadorDatos
								.getPrecision(), _validadorDatos.getValorN(),
						_elitismo, _escaladoSimple, _tipoCromosoma,
						_tipoProblema, i, _validadorDatos
								.getNumEstimadoCopiasMejor());
				break;
			}

			// Crea poblacion inicial de cromosomas
			_AG.inicializa();

			// Inicializa las componentes de las graficas
			// _panelAptitud.inicializaGraficas(_validadorDatos.getNumGeneraciones());

			// Evalua los individuos y coge el mejor
			_AG.evaluarPoblacion();

			while (!_AG.terminado()) {
				_AG.aumentarGeneracion();

				// Actualizamos el progreso de la barra
				_barraProgreso.setValue(_AG.getNumGeneracion());

				// No hace nada si no la opcion elitismo esta inactiva
				_AG.separaElite();

				_AG.seleccion();
				_AG.reproduccion();
				_AG.mutacion();

				// No hace nada si no la opcion elitismo esta inactiva
				_AG.incluyeElite();

				_AG.evaluarPoblacion();
				// _panelAptitud.guardaDatosGraficas(_AG);
			}

			_txtInforme.append("\nEjecucion " + nEjecucion
					+ " - Parametro Variable: " + i + "\n");
			imprimeResultadoConsola();

			_panelFuncion.guardaDatosEjecucion(_AG);

			nEjecucion++;
			i += paso;
		}

		_btnComenzar.setEnabled(true);
		_panelFuncion.imprimeDatosPanelFuncion();
*/
	}

	/**
	 * Devuelve el tipo de problema segun la funcion seleccionada actualmente.
	 */
	private void setTipoProblema() {

		/*
		// Asignacion del tipo de problema
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
		}*/
	}

	/**
	 * Imprime los resultados en el Area de Texto inferior.
	 */
	private void imprimeResultadoConsola() {
/*
		switch (_tipoCromosoma) {

		case FUNCION1:
			// Mostramos el mejor individuo
			_txtInforme.append("El Mejor Valor es:\n"
					+ _AG.getElMejor().toString() + "\n\n");
			_txtInforme.append("Alcanza un Maximo de: " + _AG.getElMejor().f());
			break;
		case FUNCION2:
			_txtInforme.append("Los Mejores Valores son:\n"
					+ _AG.getElMejor().toString() + "\n\n");
			_txtInforme.append("Alcanza un Maximo de: " + _AG.getElMejor().f());
			break;
		case FUNCION3:
			_txtInforme.append("El Mejor Valor es:\n"
					+ _AG.getElMejor().toString() + "\n\n");
			_txtInforme.append("Alcanza un Minimo de: " + _AG.getElMejor().f());
			break;
		case FUNCION4:
			_txtInforme.append("Los Mejores Valores son:\n"
					+ _AG.getElMejor().toString() + "\n\n");
			_txtInforme.append("Alcanza un Minimo de: " + _AG.getElMejor().f());
			break;
		case FUNCION5:
			_txtInforme.append("Los Mejores Valores son: \n\n"
					+ _AG.getElMejor().toString() + "\n");
			_txtInforme.append("Alcanza un Minimo de: " + _AG.getElMejor().f());
			break;
		}*/
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
	 * Devuelve el campo de texto del porcentage de elite.
	 * 
	 * @return El campo de texto del porcentage de elite.
	 */
	public JTextField getTxtPorcentageElite() {

		return _txtPorcentageElite;
	}

	/**
	 * Devuelve el campo de texto del paso de variacion de parametros.
	 * 
	 * @return El campo de texto del paso de variacion de parametros.
	 */
	public JTextField getTxtPasoVariacion() {

		return _txtPasoVariacion;
	}

	/**
	 * Devuelve el campo de texto del limite de variacion de parametros.
	 * 
	 * @return El campo de texto del limite de variacion de parametros.
	 */
	public JTextField getTxtLimiteVariacion() {

		return _txtLimiteVariacion;
	}

	/**
	 * Devuelve el campo de texto del numero estimado de copias del mejor
	 * individuo.
	 * 
	 * @return El campo de texto del numero estimado de copias del mejor
	 *         individuo.
	 */
	public JTextComponent getTxtNumEstimadoCopiasMejor() {

		return _txtNumEstimadoCopiasMejor;
	}
}
