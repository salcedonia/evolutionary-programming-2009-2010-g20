package gui.componentes;

import gui.Ventana;
import gui.tipos.TipoInicializacion;
import gui.tipos.TipoMutacion;
import gui.tipos.TipoSeleccion;
import gui.tipos.TipoVariacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Clase que crea el panel de opciones.
 * 
 * @author Grupo20.
 */
public class PanelOpciones extends JPanel{
	
	/**
	 * Identificador de la clase.
	 */
	private static final long serialVersionUID = 1L;
	
	// ----- CONSTANTES -----//
	private static final String NUM_GENERACIONES_DEF = "100";
	private static final String NUM_POBLACION_DEF = "100";
	private static final double PROB_CRUCE_DEF = 0.7;
	private static final double PROB_MUTACION_DEF = 0.1;
	private static final String NUM_ESTIMADO_COPIAS_MEJOR_DEF = "1";
	private static final String NUM_ELITE_DEF = "0.1";
	
	// ----- COMPONENTES -----//
	private JLabel _lblNumGeneraciones;
	private JLabel _lblTamPoblacion;
	private JLabel _lblProbCruce;
	private JLabel _lblProbMutacion;
	private JLabel _lblSeleccionTipoSeleccion;
	private JLabel _lblSeleccionInicializacion;
	private JLabel _lblSeleccionMutacion;
	private JLabel _lblSseleccionIf;
	private JLabel _lblSeleccionElitismo;
	private JLabel _lblNumEstimadoCopiasMejor;
	private JLabel _lblSeleccionEscaladoSimple;
	private JLabel _lblPorcentajeElite;
	private JLabel _lblVariacionParametros;
	private JLabel _lblPasoVariacion;
	private JLabel _lblLimiteVariacion;
	private JTextField _txtNumGeneraciones;
	private JTextField _txtTamPoblacion;
	private JTextField _txtNumEstimadoCopiasMejor;
	private JTextField _txtPorcentajeElite;
	private JTextField _txtPasoVariacion;
	private JTextField _txtLimiteVariacion;
	private JSpinner _spiProbCruce;
	private JSpinner _spiProbMutacion;
	private JCheckBox _chkSeleccionIf;
	private JCheckBox _chkSeleccionElitismo;
	private JCheckBox _chkSeleccionEscaladoSimple;
	private JComboBox _cmbSeleccionTipoSeleccion;
	private JComboBox _cmbSeleccionInicializacion;
	private JComboBox _cmbSeleccionMutacion;
	private JComboBox _cmbSeleccionVarParametros;
	
	// --------- STRINGS PARA LOS COMBOBOX -------------//
	
	private String[] _seleccionStrings = { "Ruleta", "Torneo", "Ranking"};
	
	private String[] _inicializacionStrings = { "Creciente", "Completa", "Ramped And Half"};
	
	private String[] _mutacionStrings = { "Terminal Simple", "Funcional Simple", "Arbol"};
	
	private String[] _variacionStrings = { "Ninguna",
			"Numero Maximo de Generaciones", "Tamanio de Poblacion",
			"Probabilidad de Cruce", "Probabilidad de Mutacion", "Elitismo" };

	/**
	 * Ventana grafica de la aplicacion.
	 */
	private Ventana _ventana;

	/**
	 * Constructor de la clase PanelOpcionesPractica1.
	 * 
	 * @param ventana Ventana grafica de la aplicacion.
	 */
	public PanelOpciones(final Ventana ventana){
		
		_ventana = ventana;
		
		setLayout(new GridBagLayout());

		// Creamos todas las etiquetas informativas
		GridBagConstraints constraints = new GridBagConstraints();

		_lblNumGeneraciones = new JLabel("Numero de Generaciones:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 0.5;
		add(_lblNumGeneraciones, constraints);

		_lblTamPoblacion = new JLabel("Tamanio de la Poblacion:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(_lblTamPoblacion, constraints);

		_lblProbCruce = new JLabel("Probabilidad de Cruce:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(_lblProbCruce, constraints);

		_lblProbMutacion = new JLabel("Probabilidad de Mutacion:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(_lblProbMutacion, constraints);

		_lblSeleccionTipoSeleccion = new JLabel("Metodo de Seleccion:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 4;
		add(_lblSeleccionTipoSeleccion, constraints);
		
		_lblSeleccionInicializacion = new JLabel("Tipo de Inicializacion:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 5;
		add(_lblSeleccionInicializacion, constraints);
		
		_lblSeleccionMutacion = new JLabel("Tipo de Mutacion:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 6;
		add(_lblSeleccionMutacion, constraints);
				
		_lblSseleccionIf = new JLabel("IF:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 7;
		add(_lblSseleccionIf, constraints);
		
		_lblSeleccionElitismo = new JLabel("Seleccion por Elitismo:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 8;
		add(_lblSeleccionElitismo, constraints);

		_lblPorcentajeElite = new JLabel("Tamanio de la Elite:");
		_lblPorcentajeElite.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.insets = new Insets(0,30,0,0);
		add(_lblPorcentajeElite, constraints);
		
		_lblSeleccionEscaladoSimple = new JLabel("Escalado Simple:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.insets = new Insets(0,0,0,0);
		add(_lblSeleccionEscaladoSimple, constraints);
		
		_lblNumEstimadoCopiasMejor = new JLabel("N¼ Estimado Copias del Mejor:");
		_lblNumEstimadoCopiasMejor.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 9;
		constraints.insets = new Insets(0,30,0,0);
		add(_lblNumEstimadoCopiasMejor, constraints);

		_lblVariacionParametros = new JLabel("Variacion de parametros:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.insets = new Insets(0,0,0,0);
		add(_lblVariacionParametros, constraints);

		_lblPasoVariacion = new JLabel("Paso de la variacion:");
		_lblPasoVariacion.setVisible(false);
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 11;
		add(_lblPasoVariacion, constraints);

		_lblLimiteVariacion = new JLabel("Limite de la variacion:");
		_lblLimiteVariacion.setVisible(false);
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 12;
		add(_lblLimiteVariacion, constraints);

		_txtNumGeneraciones = new JTextField(NUM_GENERACIONES_DEF);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.ipadx = 20;
		add(_txtNumGeneraciones, constraints);

		_txtTamPoblacion = new JTextField(NUM_POBLACION_DEF);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.ipadx = 20;
		add(_txtTamPoblacion, constraints);

		SpinnerNumberModel modelProbCruce = new SpinnerNumberModel(PROB_CRUCE_DEF, 0.0, 1.0, 0.1); 
		_spiProbCruce = new JSpinner(modelProbCruce); 
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.ipadx = 10;
		add(_spiProbCruce, constraints);

		SpinnerNumberModel modelProbMutacion = new SpinnerNumberModel(PROB_MUTACION_DEF, 0.0, 1.0, 0.1); 
		_spiProbMutacion = new JSpinner(modelProbMutacion); 
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.ipadx = 10;
		add(_spiProbMutacion, constraints);

		_cmbSeleccionTipoSeleccion = new JComboBox(_seleccionStrings);
		_cmbSeleccionTipoSeleccion.setSelectedIndex(0);
		_cmbSeleccionTipoSeleccion
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Ruleta"))
							_ventana.setTipoSeleccion(TipoSeleccion.RULETA);
						else if (seleccion.matches("Torneo"))
								_ventana.setTipoSeleccion(TipoSeleccion.TORNEO);
							else if (seleccion.matches("Ranking"))
								_ventana.setTipoSeleccion(TipoSeleccion.RANKING);
					}
				});

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.insets = new Insets(0,0,0,0);
		add(_cmbSeleccionTipoSeleccion, constraints);
		
		_cmbSeleccionInicializacion = new JComboBox(_inicializacionStrings);
		_cmbSeleccionInicializacion.setSelectedIndex(0);
		_cmbSeleccionInicializacion
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Creciente"))
							_ventana.setTipoInicializacion(TipoInicializacion.CRECIENTE);
						else if (seleccion.matches("Completa"))
								_ventana.setTipoInicializacion(TipoInicializacion.COMPLETA);
							else if (seleccion.matches("Ramped And Half"))
								_ventana.setTipoInicializacion(TipoInicializacion.RAMPED_AND_HALF);
					}
				});

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.insets = new Insets(0,0,0,0);
		add(_cmbSeleccionInicializacion, constraints);
		
		_cmbSeleccionMutacion = new JComboBox(_mutacionStrings);
		_cmbSeleccionMutacion.setSelectedIndex(0);
		_cmbSeleccionMutacion
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JComboBox cb = (JComboBox) e.getSource();
						String seleccion = (String) cb.getSelectedItem();

						// Guardamos la decision correspondiente
						if (seleccion.matches("Terminal Simple"))
							_ventana.setTipoMutacion(TipoMutacion.TERMINAL_SIMPLE);
						else if (seleccion.matches("Funcional Simple"))
								_ventana.setTipoMutacion(TipoMutacion.FUNCIONAL_SIMPLE);
							else if (seleccion.matches("Arbol"))
								_ventana.setTipoMutacion(TipoMutacion.ARBOL);
					}
				});

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.insets = new Insets(0,0,0,0);
		add(_cmbSeleccionMutacion, constraints);
						
		_chkSeleccionIf = new JCheckBox();
		_chkSeleccionIf.setSelected(false);
		_chkSeleccionIf
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JCheckBox cb = (JCheckBox) e.getSource();
						
						_ventana.setIfSeleccionado(cb.isSelected());
					}
				});

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.ipadx = 0;
		add(_chkSeleccionIf, constraints);
		
		_chkSeleccionElitismo = new JCheckBox();
		_chkSeleccionElitismo.setSelected(false);
		_chkSeleccionElitismo
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {

						JCheckBox cb = (JCheckBox) e.getSource();
						
						_ventana.setElitismo(cb.isSelected());
						_txtPorcentajeElite.setVisible(ventana.getElitismo());
						_lblPorcentajeElite.setVisible(ventana.getElitismo());
					}
				});

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.ipadx = 0;
		add(_chkSeleccionElitismo, constraints);

		_txtPorcentajeElite = new JTextField(NUM_ELITE_DEF);
		_txtPorcentajeElite.setColumns(3);
		_txtPorcentajeElite.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.insets = new Insets(0,225,0,0);
		add(_txtPorcentajeElite, constraints);
		
		_chkSeleccionEscaladoSimple = new JCheckBox();
		_chkSeleccionEscaladoSimple.setSelected(false);
		_chkSeleccionEscaladoSimple
				.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(java.awt.event.ActionEvent e) {

						JCheckBox cb = (JCheckBox) e.getSource();
						
						_ventana.setEscaladoSimple(cb.isSelected());
						
						_txtNumEstimadoCopiasMejor.setVisible(ventana.getEscaladoSimple());
						_lblNumEstimadoCopiasMejor.setVisible(ventana.getEscaladoSimple());
					}
				});

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 9;
		constraints.insets = new Insets(0,0,0,0);
		add(_chkSeleccionEscaladoSimple, constraints);
		
		_txtNumEstimadoCopiasMejor = new JTextField(
				NUM_ESTIMADO_COPIAS_MEJOR_DEF);
		_txtNumEstimadoCopiasMejor.setColumns(3);
		_txtNumEstimadoCopiasMejor.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 9;
		constraints.insets = new Insets(0,225,0,0);
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
							_lblPasoVariacion.setVisible(false);
							_txtPasoVariacion.setVisible(false);
							_lblLimiteVariacion.setVisible(false);
							_txtLimiteVariacion.setVisible(false);
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
							
							_lblPasoVariacion.setVisible(true);
							_txtPasoVariacion.setVisible(true);
							_lblLimiteVariacion.setVisible(true);
							_txtLimiteVariacion.setVisible(true);
						}
					}
				});

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 10;
		constraints.insets = new Insets(0,0,0,0);
		add(_cmbSeleccionVarParametros, constraints);

		_txtPasoVariacion = new JTextField(NUM_ELITE_DEF);
		_txtPasoVariacion.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 11;
		constraints.ipadx = 20;
		add(_txtPasoVariacion, constraints);

		_txtLimiteVariacion = new JTextField(NUM_ELITE_DEF);
		_txtLimiteVariacion.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 12;
		constraints.ipadx = 20;
		add(_txtLimiteVariacion, constraints);

		// Borde del panel
		setBorder(new CompoundBorder(BorderFactory
				.createTitledBorder("Panel de Parametros"), new EmptyBorder(0,
				0, 0, 0)));
	}

    /**
     * Devuelve el campo de texto del numero de generaciones.
     * 
     * @return El campo de texto del numero de generaciones.
     */
	public JTextField getTxtNumGeneraciones() {
		return _txtNumGeneraciones;
	}

	/**
	 * Devuelve el campo de texto del tamanio de la poblacion.
	 * 
	 * @return El campo de texto del tamanio de la poblacion.
 	 */
	public JTextField getTxtTamPoblacion() {
		return _txtTamPoblacion;
	}

	/**
	 * Devuelve el Spinner de la probabilidad de Cruce.
	 * 
	 * @return El Spinner de la probabilidad de Cruce.
	 */
	public JSpinner getSpiProbCruce() {
		return _spiProbCruce;
	}

	/**
	 * Devuelve el Spinner de la probabilidad de Mutacion.
	 * 
	 * @return el Spinner de la probabilidad de Mutacion.
	 */
	public JSpinner getSpiProbMutacion() {
		return _spiProbMutacion;
	}

	/**
	 * Devuelve el campo de texto con el porcentaje de la elite.
	 * 
	 * @return El campo de texto con el porcentaje de la elite.
	 */
	public JTextField getTxtPorcentajeElite() {
		return _txtPorcentajeElite;
	}

	/**
	 * Devuelve el campo de texto con el paso de la variacion de parametros.
	 * 
	 * @return El campo de texto con el paso de la variacion de parametros.
	 */
	public JTextField getTxtPasoVariacion() {
		return _txtPasoVariacion;
	}

	/**
	 * Devuelve el campo de texto con el limite de la variacion de parametros.
	 * 
	 * @return El campo de texto con el limite de la variacion de parametros.
	 */
	public JTextField getTxtLimiteVariacion() {
		return _txtLimiteVariacion;
	}

	/**
	 * Devuelve el campo de texto con el numero de copias estimado del mejor para el
	 * escalado simple.
	 * 
	 * @return El campo de texto con el numero de copias estimado del mejor para el 
	 * escalado simple.
	 */
	public JTextField getTxtNumEstimadoCopiasMejor() {
		return _txtNumEstimadoCopiasMejor;
	}
}
