package gui.componentes;

import gui.Ventana;
import gui.tipos.TipoCruce;
import gui.tipos.TipoMutacion;
import gui.tipos.TipoSeleccion;
import gui.tipos.TipoVariacion;
import gui.tipos.TipoVersion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Clase que crea el panel de opciones para la vista de la practica 2.
 * 
 * @author Grupo20.
 */
public class PanelOpcionesPractica2 extends JPanel{
	
	/**
	 * Identificador de la clase.
	 */
	private static final long serialVersionUID = 1L;
	
	// ----- CONSTANTES -----//
	private static final String NUM_POBLACION_DEF = "100";
	private static final String NUM_GENERACIONES_DEF = "100";
	private static final String PROB_CRUCE_DEF = "0.7";
	private static final String PROB_MUTACION_DEF = "0.1";
	private static final String NUM_ELITE_DEF = "0.01";
	private static final String NUM_ESTIMADO_COPIAS_MEJOR_DEF = "1";
	private static final String NUM_CIUDADES_MUT_INSERCION = "5";
	private static final String TAM_TORNEO = "50";
	private static final String BETA = "10";
	
	// ----- COMPONENTES ----- //
	private JLabel _lblSeleccionVersion;
	private JLabel _lblSeleccionSeleccion;
	private JLabel _lblSeleccionCruce;
	private JLabel _lblSeleccionMutacion;
	private JLabel _lblNumGeneraciones;
	private JLabel _lblTamPoblacion;
	private JLabel _lblProbCruce;
	private JLabel _lblTamTorneo;
	private JTextField _txtNumGeneraciones;
	private JTextField _txtTamPoblacion;
	private JTextField _txtProbCruce;
	private JTextField _txtProbMutacion;
	private JCheckBox _cmbSeleccionElitismo;
	private JTextField _txtPorcentajeElite;
	private JCheckBox _cmbSeleccionEscaladoSimple;
	private JTextField _txtNumEstimadoCopiasMejor;
	private JTextField _txtPasoVariacion;
	private JTextField _txtLimiteVariacion;
	private JTextField _txtNumCiudadesMutInsercion;
	private JTextField _txtTamTorneo;
	private JTextField _txtBeta;
	private JLabel _lblProbMutacion;
	private JLabel _lblSeleccionElitismo;
	private JLabel _lblPorcentajeElite;
	private JLabel _lblSeleccionEscaladoSimple;
	private JLabel _lblNumEstimadoCopiasMejor;
	private JLabel _lblVariacionParametros;
	private JLabel _lblPasoVariacion;
	private JLabel _lblLimiteVariacion;
	private JLabel _lblNumCiudadesMutInsercion;
	private JLabel _lblBeta;
	private JComboBox _cmbSeleccionVarParametros;
	private JComboBox _cmbSeleccionVersion;
	private JComboBox _cmbSeleccionCruce;
	private JComboBox _cmbSeleccionMutacion;
	private JComboBox _cmbSeleccionSeleccion;

	/**
	 * Ventana grafica de la aplicacion.
	 */
	private Ventana _ventana;
	
	/**
	 * Versiones disponibles para la lista desplegable correspondiente.
	 */
	String[] _versionesStrings = { "Version 1", "Version 2" };
	
	/**
	 * Metodos de seleccion disponibles para la lista desplegable correspondiente.
	 */
	String[] _seleccionStrings = { "Ruleta", "Torneo", "Ranking" };
	
	/**
	 * Metodos de cruce disponibles para la lista desplegable correspondiente.
	 */
	String[] _cruceStrings = { "PMX", "OX", "Variante OX", "Ciclos CX",
			"ERX", "Cod. Ordinal", "Propio" };

	/**
	 * Metodos de mutacion disponibles para la lista desplegable correspondiente.
	 */
	String[] _mutacionStrings = { "Insercion", "Intercambio", "Inversion",
	"Propio" };
	
	/**
	 * Variacion de parametros disponibles para la lista desplegable correspondiente.
	 */
	String[] _variacionStrings = { "Ninguna",
			"Numero Maximo de Generaciones", "Tamanio de Poblacion",
			"Probabilidad de Cruce", "Probabilidad de Mutacion", "Elitismo" };
	
	/**
	 * Crea las opciones del cuerpo del panel de opciones donde se situan todos
	 * los elementos de configuracion del AGS para la Practica 2.
	 */
	public PanelOpcionesPractica2(final Ventana ventana) {

		_ventana = ventana;
		
		setLayout(new GridBagLayout());

		// Creamos todas las etiquetas informativas
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		_lblSeleccionVersion = new JLabel("Version del Problema:");
		add(_lblSeleccionVersion, constraints);

		_lblSeleccionSeleccion = new JLabel("Metodo de Seleccion:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(_lblSeleccionSeleccion, constraints);

		_lblTamTorneo = new JLabel("Tamanio del Torneo:");
		_lblTamTorneo.setVisible(false);
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(_lblTamTorneo, constraints);
		
		_lblBeta = new JLabel("Beta:");
		_lblBeta.setVisible(false);
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(_lblBeta, constraints);
		
		_lblSeleccionCruce = new JLabel("Metodo de Cruce:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(_lblSeleccionCruce, constraints);

		_lblSeleccionMutacion = new JLabel("Metodo de Mutacion:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 4;
		add(_lblSeleccionMutacion, constraints);

		_lblNumGeneraciones = new JLabel("Numero de Generaciones:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 5;
		add(_lblNumGeneraciones, constraints);

		_lblTamPoblacion = new JLabel("Tamanio de la Poblacion:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 6;
		add(_lblTamPoblacion, constraints);

		_lblProbCruce = new JLabel("Probabilidad de Cruce:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 7;
		add(_lblProbCruce, constraints);

		_lblProbMutacion = new JLabel("Probabilidad de Mutacion:");
		constraints.gridx = 0;
		constraints.gridy = 8;
		add(_lblProbMutacion, constraints);

		_lblNumCiudadesMutInsercion = new JLabel("Numero de Ciudades para la Mutacion:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 9;
		add(_lblNumCiudadesMutInsercion, constraints);
		
		_lblSeleccionElitismo = new JLabel("Seleccion por Elitismo:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 10;
		add(_lblSeleccionElitismo, constraints);

		_lblPorcentajeElite = new JLabel("Tamanio de la Elite:");
		_lblPorcentajeElite.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 10;
		constraints.insets = new Insets(0, 40, 0, 0);
		add(_lblPorcentajeElite, constraints);

		_lblSeleccionEscaladoSimple = new JLabel("Escalado Simple:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 11;
		add(_lblSeleccionEscaladoSimple, constraints);

		_lblNumEstimadoCopiasMejor = new JLabel("N¼ Estimado Copias del Mejor:");
		_lblNumEstimadoCopiasMejor.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 11;
		constraints.insets = new Insets(0, 40, 0, 0);
		add(_lblNumEstimadoCopiasMejor, constraints);

		_lblVariacionParametros = new JLabel("Variacion de Parametros:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 12;
		constraints.insets = new Insets(0, 0, 0, 0);
		add(_lblVariacionParametros, constraints);

		_lblPasoVariacion = new JLabel("Paso de la Variacion:");
		_lblPasoVariacion.setVisible(false);
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 13;
		add(_lblPasoVariacion, constraints);

		_lblLimiteVariacion = new JLabel("Limite de la Variacion:");
		_lblLimiteVariacion.setVisible(false);
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 14;
		add(_lblLimiteVariacion, constraints);

		_cmbSeleccionVersion = new JComboBox(_versionesStrings);
		_cmbSeleccionVersion
				.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Version 1")) {
							_ventana.setTipoVersion(TipoVersion.VERSION1);

							_cmbSeleccionCruce.setEnabled(true);
							_cmbSeleccionMutacion.setEnabled(true);

						}
						if (seleccion.matches("Version 2")) {
							_ventana.setTipoVersion(TipoVersion.VERSION2);

							_cmbSeleccionCruce.setEnabled(false);
							_cmbSeleccionCruce.setSelectedIndex(0);

							_cmbSeleccionMutacion.setEnabled(false);
							_cmbSeleccionMutacion.setSelectedIndex(0);
						}
					}
				});

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(_cmbSeleccionVersion, constraints);

		_cmbSeleccionSeleccion = new JComboBox(_seleccionStrings);
		_cmbSeleccionSeleccion
				.addActionListener(new java.awt.event.ActionListener() {
					
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Ruleta")) {
							_ventana.setTipoSeleccion(TipoSeleccion.RULETA);
							_lblTamTorneo.setVisible(false);
							_txtTamTorneo.setVisible(false);
							_lblBeta.setVisible(false);
							_txtBeta.setVisible(false);
						} else if (seleccion.matches("Torneo")) {
							ventana.setTipoSeleccion(TipoSeleccion.TORNEO);
							_lblTamTorneo.setVisible(true);
							_txtTamTorneo.setVisible(true);
							_lblBeta.setVisible(false);
							_txtBeta.setVisible(false);
							} else if (seleccion.matches("Ranking")) {
								_ventana.setTipoSeleccion(TipoSeleccion.RANKING);
								_lblTamTorneo.setVisible(false);
								_txtTamTorneo.setVisible(false);
								_lblBeta.setVisible(true);
								_txtBeta.setVisible(true);
						}

					}
				});

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 1;
		add(_cmbSeleccionSeleccion, constraints);

		_txtTamTorneo = new JTextField(TAM_TORNEO);
		_txtTamTorneo.setColumns(5);
		_txtTamTorneo.setVisible(false);
		constraints.gridx = 1;
		constraints.gridy = 2;
		add(_txtTamTorneo, constraints);
		
		_txtBeta = new JTextField(BETA);
		_txtBeta.setColumns(5);
		_txtBeta.setVisible(false);
		constraints.gridx = 1;
		constraints.gridy = 2;
		add(_txtBeta, constraints);
		
		_cmbSeleccionCruce = new JComboBox(_cruceStrings);
		_cmbSeleccionCruce
				.addActionListener(new java.awt.event.ActionListener() {
					
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("PMX")) {
							_ventana.setTipoCruce(TipoCruce.PMX);
						} else if (seleccion.matches("OX")) {
							_ventana.setTipoCruce(TipoCruce.OX);
						} else if (seleccion.matches("Variante OX")) {
							_ventana.setTipoCruce(TipoCruce.VARIANTE_OX);
						} else if (seleccion.matches("Ciclos CX")) {
							_ventana.setTipoCruce(TipoCruce.CICLOS_CX);
						} else if (seleccion.matches("ERX")) {
							_ventana.setTipoCruce(TipoCruce.ERX);
						} else if (seleccion.matches("Cod. Ordinal")) {
							_ventana.setTipoCruce(TipoCruce.COD_ORDINAL);
						} else if (seleccion.matches("Propio")) {
							_ventana.setTipoCruce(TipoCruce.PROPIO);
						}

					}
				});

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 3;
		add(_cmbSeleccionCruce, constraints);
		
		_cmbSeleccionMutacion = new JComboBox(_mutacionStrings);
		_cmbSeleccionMutacion
				.addActionListener(new java.awt.event.ActionListener() {
					
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Insercion")) {
							_ventana.setTipoMutacion(TipoMutacion.INSERCION);
							_lblNumCiudadesMutInsercion.setVisible(true);
							_txtNumCiudadesMutInsercion.setVisible(true);
						} else if (seleccion.matches("Intercambio")) {
							_ventana.setTipoMutacion(TipoMutacion.INTERCAMBIO);
							_lblNumCiudadesMutInsercion.setVisible(false);
							_txtNumCiudadesMutInsercion.setVisible(false);
						} else if (seleccion.matches("Inversion")) {
							_ventana.setTipoMutacion(TipoMutacion.INVERSION);
							_lblNumCiudadesMutInsercion.setVisible(false);
							_txtNumCiudadesMutInsercion.setVisible(false);
						} else if (seleccion.matches("Propio")) {
							_ventana.setTipoMutacion(TipoMutacion.PROPIO);
							_lblNumCiudadesMutInsercion.setVisible(false);
							_txtNumCiudadesMutInsercion.setVisible(false);
						}
					}
				});

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 4;
		add(_cmbSeleccionMutacion, constraints);

		_txtNumGeneraciones = new JTextField(NUM_GENERACIONES_DEF);
		_txtNumGeneraciones.setColumns(5);
		constraints.gridx = 1;
		constraints.gridy = 5;
		add(_txtNumGeneraciones, constraints);

		_txtTamPoblacion = new JTextField(NUM_POBLACION_DEF);
		_txtTamPoblacion.setColumns(5);
		constraints.gridx = 1;
		constraints.gridy = 6;
		add(_txtTamPoblacion, constraints);

		_txtProbCruce = new JTextField(PROB_CRUCE_DEF);
		_txtProbCruce.setColumns(3);
		constraints.gridx = 1;
		constraints.gridy = 7;
		add(_txtProbCruce, constraints);

		_txtProbMutacion = new JTextField(PROB_MUTACION_DEF);
		_txtProbMutacion.setColumns(3);
		constraints.gridx = 1;
		constraints.gridy = 8;
		add(_txtProbMutacion, constraints);


		_txtNumCiudadesMutInsercion = new JTextField(NUM_CIUDADES_MUT_INSERCION);
		_txtNumCiudadesMutInsercion.setColumns(3);
		constraints.gridx = 1;
		constraints.gridy = 9;
		add(_txtNumCiudadesMutInsercion, constraints);
		
		_cmbSeleccionElitismo = new JCheckBox();
		_cmbSeleccionElitismo.setSelected(false);
		_cmbSeleccionElitismo
				.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(java.awt.event.ActionEvent e) {

						JCheckBox cb = (JCheckBox) e.getSource();

						// Guardamos la decision correspondiente
						_ventana.setElitismo(cb.isSelected());

						_txtPorcentajeElite.setVisible(_ventana.getElitismo());
						_lblPorcentajeElite.setVisible(_ventana.getElitismo());
					}
				});

		constraints.gridx = 1;
		constraints.gridy = 10;
		constraints.insets = new Insets(0, 10, 0, 0);
		add(_cmbSeleccionElitismo, constraints);

		_txtPorcentajeElite = new JTextField(NUM_ELITE_DEF);
		_txtPorcentajeElite.setColumns(5);
		_txtPorcentajeElite.setVisible(false);
		constraints.gridx = 1;
		constraints.gridy = 10;
		constraints.insets = new Insets(0, 250, 0, 0);
		add(_txtPorcentajeElite, constraints);

		_cmbSeleccionEscaladoSimple = new JCheckBox();
		_cmbSeleccionEscaladoSimple.setSelected(false);
		_cmbSeleccionEscaladoSimple
				.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(java.awt.event.ActionEvent e) {

						JCheckBox cb = (JCheckBox) e.getSource();

						// Guardamos la decision correspondiente
						_ventana.setEscaladoSimple(cb.isSelected());

						_txtNumEstimadoCopiasMejor.setVisible(_ventana.getEscaladoSimple());
						_lblNumEstimadoCopiasMejor.setVisible(_ventana.getEscaladoSimple());
					}
				});

		constraints.gridx = 1;
		constraints.gridy = 11;
		constraints.insets = new Insets(0, 10, 0, 0);
		add(_cmbSeleccionEscaladoSimple, constraints);

		_txtNumEstimadoCopiasMejor = new JTextField(
				NUM_ESTIMADO_COPIAS_MEJOR_DEF);
		_txtNumEstimadoCopiasMejor.setColumns(5);
		_txtNumEstimadoCopiasMejor.setVisible(false);
		constraints.gridx = 1;
		constraints.gridy = 11;
		constraints.insets = new Insets(0, 250, 0, 0);
		add(_txtNumEstimadoCopiasMejor, constraints);

		_cmbSeleccionVarParametros = new JComboBox(_variacionStrings);
		_cmbSeleccionVarParametros.setSelectedIndex(0);
		_cmbSeleccionVarParametros
				.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Ninguna")) {
							_ventana.setTipoVariacion(TipoVariacion.NINGUNA);
							_txtPasoVariacion.setVisible(false);
							_lblPasoVariacion.setVisible(false);
							_txtLimiteVariacion.setVisible(false);
							_lblLimiteVariacion.setVisible(false);
						} else {
							if (seleccion
									.matches("Numero Maximo de Generaciones"))
								_ventana.setTipoVariacion(TipoVariacion.NUM_GENERACION);
							else if (seleccion.matches("Tamanio de Poblacion"))
								_ventana.setTipoVariacion(TipoVariacion.NUM_POBLACION);
							else if (seleccion.matches("Probabilidad de Cruce"))
								_ventana.setTipoVariacion(TipoVariacion.PROB_CRUCE);
							else if (seleccion
									.matches("Probabilidad de Mutacion"))
								_ventana.setTipoVariacion(TipoVariacion.PROB_MUTACION);
							else if (seleccion.matches("Elitismo"))
								_ventana.setTipoVariacion(TipoVariacion.ELITISMO);
							
							_txtPasoVariacion.setVisible(true);
							_lblPasoVariacion.setVisible(true);
							_txtLimiteVariacion.setVisible(true);
							_lblLimiteVariacion.setVisible(true);
						}
					}
				});

		constraints.gridx = 1;
		constraints.gridy = 12;
		constraints.insets = new Insets(0, 0, 0, 0);
		add(_cmbSeleccionVarParametros, constraints);

		_txtPasoVariacion = new JTextField(NUM_ELITE_DEF);
		_txtPasoVariacion.setColumns(5);
		_txtPasoVariacion.setVisible(false);
		constraints.gridx = 1;
		constraints.gridy = 13;
		add(_txtPasoVariacion, constraints);

		_txtLimiteVariacion = new JTextField(NUM_ELITE_DEF);
		_txtLimiteVariacion.setColumns(5);
		_txtLimiteVariacion.setVisible(false);
		constraints.gridx = 1;
		constraints.gridy = 14;
		add(_txtLimiteVariacion, constraints);

		// Borde del panel
		setBorder(new CompoundBorder(BorderFactory
				.createTitledBorder("Panel de Parametros"), new EmptyBorder(0,
				10, 10, 10)));
	}

	// ----- GETTERS & SETTERS ------ //
	
	/**
	 * Devuelve el campo de texto de numero de generaciones de la interfaz grafica.
	 * 
	 * @return El campo de texto de numero de generaciones de la interfaz grafica.
	 */
	public JTextField getTxtNumGeneraciones() {
		return _txtNumGeneraciones;
	}

	/**
	 * Devuelve el campo de texto de tamanio de poblacion de la interfaz grafica.
	 * 
	 * @return El campo de texto de tamanio de la poblacion de la interfaz grafica.
	 */
	public JTextField getTxtTamPoblacion() {
		return _txtTamPoblacion;
	}
	
	/**
	 * Devuelve el campo de texto de probabilidad de cruce de la interfaz grafica.
	 * 
	 * @return El campo de texto de probabilidad de cruce de la interfaz grafica.
	 */
	public JTextField getTxtProbCruce() {
		return _txtProbCruce;
	}
	
	/**
	 * Devuelve el campo de texto de probabilidad de mutacion de la interfaz grafica.
	 * 
	 * @return El campo de texto de probabilidad de mutacion de la interfaz grafica.
	 */
	public JTextField getTxtProbMutacion() {
		return _txtProbMutacion;
	}

	/**
	 * Devuelve el campo de texto de porcentaje de elite de la interfaz grafica.
	 * 
	 * @return El campo de texto de porcentaje de elite de la interfaz grafica.
	 */
	public JTextField getTxtPorcentajeElite() {	
		return _txtPorcentajeElite;
	}

	/**
	 * Devuelve el campo de texto de paso de variacion de la interfaz grafica.
	 * 
	 * @return El campo de texto de paso de variacion de la interfaz grafica.
	 */
	public JTextField getTxtPasoVariacion() {
		return _txtPasoVariacion;
	}

	/**
	 * Devuelve el campo de texto del limite de la variacion de la interfaz grafica.
	 * 
	 * @return El campo de texto del limite de la variacion de la interfaz grafica.
	 */
	public JTextField getTxtLimiteVariacion() {
		return _txtLimiteVariacion;
	}
	
	/**
	 * Devuelve el campo de texto de numero estimado de copias del mejor de la interfaz grafica.
	 * 
	 * @return El campo de texto de numero estimado de copias del mejor de la interfaz grafica.
	 */
	public JTextField getTxtNumEstimadoCopiasMejor() {
		return _txtNumEstimadoCopiasMejor;
	}

	/**
	 * Devuelve el campo de texto de numero estimado de copias del mejor de la interfaz grafica.
	 * 
	 * @return El campo de texto de numero estimado de copias del mejor de la interfaz grafica.
	 */
	public JTextField getTxtNumCiudadesMutInsercion() {	
		return _txtNumCiudadesMutInsercion;
	}

	/**
	 * Devuelve el campo de texto de tamanio de Torneo de la interfaz grafica.
	 * 
	 * @return El campo de texto de tamanio de Torneo de la interfaz grafica.
	 */
	public JTextField getTxtTamTorneo() {
		return _txtTamTorneo;
	}
}
