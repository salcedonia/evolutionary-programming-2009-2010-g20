package gui.componentes;

import gui.Ventana;
import gui.tipos.TipoCromosoma;
import gui.tipos.TipoVariacion;

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
 * Clase que crea el panel de opciones para la vista de la practica 1.
 * 
 * @author Grupo20.
 */
public class PanelOpcionesPractica1 extends JPanel{
	
	/**
	 * Identificador de la clase.
	 */
	private static final long serialVersionUID = 1L;
	
	// ----- CONSTANTES -----//
	private static final String NUM_GENERACIONES_DEF = "100";
	private static final String NUM_POBLACION_DEF = "100";
	private static final String PROB_CRUCE_DEF = "0.7";
	private static final String PROB_MUTACION_DEF = "0.1";
	private static final String TOLERANCIA_DEF = "0.001";
	private static final String NUM_ESTIMADO_COPIAS_MEJOR_DEF = "1";
	private static final String NUM_ELITE_DEF = "0.1";
	
	// ----- COMPONENTES -----//
	private JLabel _lblSeleccionFuncion;
	private JLabel _lblNumGeneraciones;
	private JLabel _lblTamPoblacion;
	private JLabel _lblProbCruce;
	private JLabel _lblProbMutacion;
	private JLabel _lblPrecision;
	private JLabel _lblValorN;
	private JLabel _lblSeleccionElitismo;
	private JLabel _lblNumEstimadoCopiasMejor;
	private JLabel _lblSeleccionEscaladoSimple;
	private JLabel _lblPorcentajeElite;
	private JLabel _lblVariacionParametros;
	private JLabel _lblPasoVariacion;
	private JLabel _lblLimiteVariacion;
	private JComboBox _cmbSeleccionFuncion;
	private JTextField _txtNumGeneraciones;
	private JTextField _txtTamPoblacion;
	private JTextField _txtProbCruce;
	private JTextField _txtProbMutacion;
	private JTextField _txtPrecision;
	private JTextField _txtValorN;
	private JCheckBox _cmbSeleccionElitismo;
	private JTextField _txtNumEstimadoCopiasMejor;
	private JCheckBox _cmbSeleccionEscaladoSimple;
	private JTextField _txtPorcentajeElite;
	private JComboBox _cmbSeleccionVarParametros;
	private JTextField _txtPasoVariacion;
	private JTextField _txtLimiteVariacion;
	
	/**
	 * Ventana grafica de la aplicacion.
	 */
	private Ventana _ventana;

	/**
	 * Constructor de la clase PanelOpcionesPractica1.
	 * 
	 * @param ventana Ventana grafica de la aplicacion.
	 */
	public PanelOpcionesPractica1(final Ventana ventana){
		
		_ventana = ventana;
		
		setLayout(new GridBagLayout());

		// Creamos todas las etiquetas informativas
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		_lblSeleccionFuncion = new JLabel("Funcion a Evaluar:");
		add(_lblSeleccionFuncion, constraints);

		_lblNumGeneraciones = new JLabel("Numero de Generaciones:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(_lblNumGeneraciones, constraints);

		_lblTamPoblacion = new JLabel("Tamanio de la Poblacion:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(_lblTamPoblacion, constraints);

		_lblProbCruce = new JLabel("Probabilidad de Cruce:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(_lblProbCruce, constraints);

		_lblProbMutacion = new JLabel("Probabilidad de Mutacion:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 4;
		add(_lblProbMutacion, constraints);

		_lblPrecision = new JLabel("Precision:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 5;
		add(_lblPrecision, constraints);

		_lblValorN = new JLabel("Valor de N:");
		_lblValorN.setVisible(false);
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 6;
		add(_lblValorN, constraints);

		_lblSeleccionElitismo = new JLabel("Seleccion por Elitismo:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 7;
		add(_lblSeleccionElitismo, constraints);

		_lblPorcentajeElite = new JLabel("Tamanio de la Elite:");
		_lblPorcentajeElite.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.insets = new Insets(0,30,0,0);
		add(_lblPorcentajeElite, constraints);
		
		_lblSeleccionEscaladoSimple = new JLabel("Escalado Simple:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.insets = new Insets(0,0,0,0);
		add(_lblSeleccionEscaladoSimple, constraints);
		
		_lblNumEstimadoCopiasMejor = new JLabel("N¼ Estimado Copias del Mejor:");
		_lblNumEstimadoCopiasMejor.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.insets = new Insets(0,30,0,0);
		add(_lblNumEstimadoCopiasMejor, constraints);

		_lblVariacionParametros = new JLabel("Variacion de parametros:");
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.insets = new Insets(0,0,0,0);
		add(_lblVariacionParametros, constraints);

		_lblPasoVariacion = new JLabel("Paso de la variacion:");
		_lblPasoVariacion.setVisible(false);
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 10;
		add(_lblPasoVariacion, constraints);

		_lblLimiteVariacion = new JLabel("Limite de la variacion:");
		_lblLimiteVariacion.setVisible(false);
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 11;
		add(_lblLimiteVariacion, constraints);

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
						if (seleccion.matches("Funcion1")) {
							_ventana.setTipoCromosoma(TipoCromosoma.FUNCION1);
							_lblValorN.setVisible(false);
							_txtValorN.setVisible(false);
						}
						if (seleccion.matches("Funcion2")) {
							_ventana.setTipoCromosoma(TipoCromosoma.FUNCION2);
							_lblValorN.setVisible(false);
							_txtValorN.setVisible(false);
						}
						if (seleccion.matches("Funcion3")) {
							_ventana.setTipoCromosoma(TipoCromosoma.FUNCION3);
							_lblValorN.setVisible(false);
							_txtValorN.setVisible(false);
						}
						if (seleccion.matches("Funcion4")) {
							_ventana.setTipoCromosoma(TipoCromosoma.FUNCION4);
							_lblValorN.setVisible(false);
							_txtValorN.setVisible(false);
						}
						if (seleccion.matches("Funcion5")) {
							_ventana.setTipoCromosoma(TipoCromosoma.FUNCION5);
							_lblValorN.setVisible(true);
							_txtValorN.setVisible(true);
						}
					}
				});

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(_cmbSeleccionFuncion, constraints);

		_txtNumGeneraciones = new JTextField(NUM_GENERACIONES_DEF);
		_txtNumGeneraciones.setColumns(5);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 1;
		add(_txtNumGeneraciones, constraints);

		_txtTamPoblacion = new JTextField(NUM_POBLACION_DEF);
		_txtTamPoblacion.setColumns(5);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 2;
		add(_txtTamPoblacion, constraints);

		_txtProbCruce = new JTextField(PROB_CRUCE_DEF);
		_txtProbCruce.setColumns(3);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 3;
		add(_txtProbCruce, constraints);

		_txtProbMutacion = new JTextField(PROB_MUTACION_DEF);
		_txtProbMutacion.setColumns(3);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 4;
		add(_txtProbMutacion, constraints);

		_txtPrecision = new JTextField(TOLERANCIA_DEF);
		_txtPrecision.setColumns(5);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 5;
		add(_txtPrecision, constraints);

		_txtValorN = new JTextField(NUM_ESTIMADO_COPIAS_MEJOR_DEF);
		_txtValorN.setColumns(3);		
		_txtValorN.setVisible(false);
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.anchor = GridBagConstraints.WEST;
		add(_txtValorN, constraints);

		_cmbSeleccionElitismo = new JCheckBox();
		_cmbSeleccionElitismo.setSelected(false);
		_cmbSeleccionElitismo
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
		constraints.gridy = 7;
		add(_cmbSeleccionElitismo, constraints);

		_txtPorcentajeElite = new JTextField(NUM_ELITE_DEF);
		_txtPorcentajeElite.setColumns(3);
		_txtPorcentajeElite.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.insets = new Insets(0,225,0,0);
		add(_txtPorcentajeElite, constraints);
		
		_cmbSeleccionEscaladoSimple = new JCheckBox();
		_cmbSeleccionEscaladoSimple.setSelected(false);
		_cmbSeleccionEscaladoSimple
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
		constraints.gridy = 8;
		constraints.insets = new Insets(0,0,0,0);
		add(_cmbSeleccionEscaladoSimple, constraints);
		
		_txtNumEstimadoCopiasMejor = new JTextField(
				NUM_ESTIMADO_COPIAS_MEJOR_DEF);
		_txtNumEstimadoCopiasMejor.setColumns(3);
		_txtNumEstimadoCopiasMejor.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.insets = new Insets(0,225,0,0);
		add(_txtNumEstimadoCopiasMejor, constraints);

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
							else if (seleccion.matches("Precision"))
								_ventana.setTipoVariacion(TipoVariacion.PRECISION);
							else if (seleccion.matches("Valor de N"))
								_ventana.setTipoVariacion(TipoVariacion.VALOR_N);
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
		constraints.gridy = 9;
		constraints.insets = new Insets(0,0,0,0);
		add(_cmbSeleccionVarParametros, constraints);

		_txtPasoVariacion = new JTextField(NUM_ELITE_DEF);
		_txtPasoVariacion.setColumns(5);
		_txtPasoVariacion.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 10;
		add(_txtPasoVariacion, constraints);

		_txtLimiteVariacion = new JTextField(NUM_ELITE_DEF);
		_txtLimiteVariacion.setColumns(5);
		_txtLimiteVariacion.setVisible(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 11;
		add(_txtLimiteVariacion, constraints);

		// Borde del panel
		setBorder(new CompoundBorder(BorderFactory
				.createTitledBorder("Panel de Parametros"), new EmptyBorder(0,
				10, 10, 10)));
	}


	public JTextField getTxtNumGeneraciones() {
		return _txtNumGeneraciones;
	}


	public JTextField getTxtTamPoblacion() {
		return _txtTamPoblacion;
	}


	public JTextField getTxtProbCruce() {
		return _txtProbCruce;
	}


	public JTextField getTxtProbMutacion() {
		return _txtProbMutacion;
	}


	public JTextField getTxtPrecision() {
		return _txtPrecision;
	}


	public JTextField getTxtValorN() {
		return _txtValorN;
	}


	public JTextField getTxtPorcentajeElite() {
		return _txtPorcentajeElite;
	}


	public JTextField getTxtPasoVariacion() {
		return _txtPasoVariacion;
	}


	public JTextField getTxtLimiteVariacion() {
		return _txtLimiteVariacion;
	}


	public JTextField getTxtNumEstimadoCopiasMejor() {
		return _txtNumEstimadoCopiasMejor;
	}
}
