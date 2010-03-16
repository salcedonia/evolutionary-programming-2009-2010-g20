package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import cromosoma.TipoCromosoma;

import logica.AG;

/**
 * Clase que implementa los métodos necesarios para la gestión de la
 * ventana gráfica del usuario.
 * 
 * @author Grupo20.
 *
 */
public class Ventana extends JFrame {

	// CONSTANTES
	private static final long serialVersionUID = 1L;
	private static final int ALTO = 640;
	private static final int ANCHO = 800;
	private static final String ICONO = "AGSIcono.gif";
	
	/**
	 * Clase que se encarga del AGS.
	 */
	private AG _AG;
	
	/** 
	 * Barra de Menú. 
	 */
	private JMenuBar _barraMenu = null;
	/**
	 * Funciones del menú.
	 */
	private JMenu _funcionesMenu = null;
	
	/**
	 * Subfunciones del menú.
	 */
	private JMenuItem _fMenuItem[] = null;
	
	/**
	 * Panel Principal 
	 */
	private JPanel _panelPrincipal = null;
	/**
	 * Panel de opciones.
	 */
	private JPanel _panelOpciones = null;
	
	// ---------------INTERFAZ ----------------//
	
	/**
	 * Etiqueta que muestra la funcion a evaluar.
	 */
	private JLabel _lblFuncion;
	/**
	 * Caja de texto de la derecha.
	 */
	private JTextField _txtDerecha;
	/**
	 * Caja de texto inferior.
	 */
	private JTextField _txtInferior;
	
	/**
	 * Panel de cabecera del panel de opciones.
	 */
	private JPanel _panelCabeceraOpciones;
	
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
	private JTextField _txtSeleccionElitismo;
	
	/**
	 * Boton de comienzo del AGS.
	 */
	private JButton _btnComenzar;
	
	/**
	 * Tipo de cromosoma a crear. Por defecto es la Función 1.
	 */
	private TipoCromosoma _tipoCromosoma = TipoCromosoma.FUNCION1;
	
	/**
	 * Constructor de la clase Ventana.
	 */
	public Ventana() {
		
		// Set de las características de la ventana
		setTitle("Práctica 1: AGS");
		setIconImage(new ImageIcon(ICONO).getImage());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(10, 50, ANCHO, ALTO);
		setLocationRelativeTo(null);
		
		// Inicialización de los elementos de la ventana
		iniciaInterfaz();
		setEnabled(true);
		setVisible(true);
	}
		
	/**
	 * Crea los elementos de la ventana.
	 */
	private void iniciaInterfaz() {
		
		// Crea el menu principal
		setJMenuBar(creaMenuPrincipal());
		
		// Crea el panel de contenido
		setContentPane(creaPanelPrincipal());
	}
	
	/**
	 * Crea el menu principal.
	 * 
	 * @return La barra del menú principal.
	 */
	private JMenuBar creaMenuPrincipal() {

		// Crea el menu y las funciones pertinentes
		_barraMenu = new JMenuBar();
		_barraMenu.add(getMenuFunciones());
		
		return _barraMenu;
	}
	
    /**
     * Crea las funciones del menu.
     * 
     * @return Las funciones del menu.
     */
	private JMenu getMenuFunciones() {
		if (_funcionesMenu == null) {
			_funcionesMenu = new JMenu();
			_funcionesMenu.setText("Funciones");
			_fMenuItem = new JMenuItem[5];
			for (int i = 0; i < 5; i++) 
				_funcionesMenu.add(getFItem(i));
		}
		return _funcionesMenu;
	}
	
	//---------------------------------------------------------------------------
	private JMenuItem getFItem(final int numFuncion) {
		if (_fMenuItem[numFuncion] == null) {
			_fMenuItem[numFuncion] = new JMenuItem();
			_fMenuItem[numFuncion].setText("Función "+String.valueOf(numFuncion+1));
			_fMenuItem[numFuncion].setEnabled(true);
			_fMenuItem[numFuncion].addActionListener( 
					new	java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							
							_lblFuncion.setText(_fMenuItem[numFuncion].getText());
							
							// Guardamos el tipo de cromosoma a crear
							switch(numFuncion){
							
								case 0 : _tipoCromosoma = TipoCromosoma.FUNCION1;break;
								case 1 : _tipoCromosoma = TipoCromosoma.FUNCION2;break;
								case 2 : _tipoCromosoma = TipoCromosoma.FUNCION3;break;
								case 3 : _tipoCromosoma = TipoCromosoma.FUNCION4;break;
								default : _tipoCromosoma = TipoCromosoma.FUNCION5;break;						
							}
						}
					}
				);
		}
		return _fMenuItem[numFuncion];
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
		
		// Aniadimos todas las opciones de la aplicación.
		_panelPrincipal.add(creaPanelOpciones(),constraints);
		
		// Creamos el panel de texto de la derecha
		_txtDerecha = new JTextField();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 0.7;
		constraints.weightx = 1.0;
		_panelPrincipal.add(_txtDerecha, constraints);
		
		// Creamos el panel de texto de abajo
		_txtInferior = new JTextField();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 0.3;
		constraints.weightx = 1.0;
		_panelPrincipal.add(_txtInferior,constraints);
		
		return _panelPrincipal;
	}
	
	/**
	 * Crea el panel con todas las opciones de la aplicación.
	 * 
	 * @return El panel con todas las opciones de la aplicación.
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
		constraints.weighty = 0.1;
		constraints.weightx = 1.0;
		
		// Crea la cabecera del panel de opciones
		creaCabeceraOpciones();
		_panelOpciones.add(_panelCabeceraOpciones,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 1.0;
		constraints.weightx = 1.0;
		
		// Aniade las opciones del panel de opciones
		creaBodyOpciones();
		_panelOpciones.add(_panelBodyOpciones,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 0.1;
		constraints.weightx = 1.0;
		
		// Aniade los botones de la aplicación.
		creaBotonesOpciones();
		_panelOpciones.add(_panelBotonesOpciones,constraints);
		
		return _panelOpciones;
	}
	
	/**
	 * Crea la cabecera del panel de opciones.
	 * 
	 * @return La cabecera del panel de opciones.
	 */
	private void creaCabeceraOpciones() {
		
		_panelCabeceraOpciones = new JPanel();
		_panelCabeceraOpciones.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();

		// Label título función
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
			
		// Creamos la etiqueta Funcion
		_panelCabeceraOpciones.add(new JLabel("Función"), constraints);
		
		// Label con la función concreta
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		
		// Creamos la etiqueta que muestra la funcion
		_lblFuncion = new JLabel("Aquí va la función");
		_panelCabeceraOpciones.add(_lblFuncion, constraints);
	}
	
	/**
	 * Crea las opciones del cuerpo del panel de opciones donde
	 * se situan todos los elementos de configuración del AGS.
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
		
		_lblNumGeneraciones = new JLabel("Número de generaciones:");
		_panelBodyOpciones.add(_lblNumGeneraciones,constraints);
		
		_lblTamPoblacion = new JLabel("Tamaño de la población:");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblTamPoblacion,constraints);
		
		_lblProbCruce = new JLabel("Probabilidad de cruce:");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblProbCruce,constraints);
		
		_lblProbMutacion = new JLabel("Probabilidad de mutación:");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblProbMutacion,constraints);
		
		_lblPrecision = new JLabel("Precisión:");
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblPrecision,constraints);
		
		_lblValorN = new JLabel("Valor de n:");
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblValorN,constraints);
		
		_lblSeleccionElitismo = new JLabel("Selección por elitismo:");
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.BOTH;
		_panelBodyOpciones.add(_lblSeleccionElitismo,constraints);
		
		// Creamos todos los cuadros de texto correspondientes
		
		_txtNumGeneraciones = new JTextField();		
		constraints.weightx = 1.4;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtNumGeneraciones, constraints);
		
		_txtTamPoblacion = new JTextField();
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtTamPoblacion, constraints);
		
		_txtProbCruce = new JTextField();
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtProbCruce, constraints);
		
		_txtProbMutacion = new JTextField();
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtProbMutacion, constraints);
		
		_txtPrecision = new JTextField();
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtPrecision, constraints);
		
		_txtValorN = new JTextField();
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtValorN, constraints);
		
		_txtSeleccionElitismo = new JTextField(); 
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		_panelBodyOpciones.add(_txtSeleccionElitismo, constraints);
		
		// Borde del panel
		_panelBodyOpciones.setBorder( new CompoundBorder(
        		BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
        		new EmptyBorder(0,10,10,10) ) );
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
		_btnComenzar.addActionListener( 
				new	java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						
						if (parametrosOk())
							comenzarAGS();
					}
				});
		_panelBotonesOpciones.add(_btnComenzar,constraints);
	}	
	
	/**
	 * Configura todo lo necesario para la evaluacion del AGS.
	 */
	private void comenzarAGS() {
		
		//TODO: Arreglar lo del elitismo. Cambiar por una radio button de si o no.
		_AG = new AG(Integer.parseInt(_txtTamPoblacion.getText()),
				Integer.parseInt(_txtNumGeneraciones.getText()),
				Double.parseDouble(_txtProbCruce.getText()),
				Double.parseDouble(_txtProbMutacion.getText()),
				Double.parseDouble(_txtPrecision.getText()),
				true,
				_tipoCromosoma);
		
		// Crea población inicial de cromosomas
		_AG.inicializa(); 
		
		// Evalúa los individuos y coge el mejor
		_AG.evaluarPoblacion();
		
		while (!_AG.terminado()) {
			_AG.aumentarGeneración();
			_AG.seleccion();
			_AG.reproduccion();
			_AG.mutacion();
			_AG.evaluarPoblacion();
		}
		
		// Mostramos el mejor individuo
		_txtInferior.setText(_AG.getElMejor().toString());	
	}
	
	/**
	 * Comprueba que todos los valores introducidos por el usuario son validos para
	 * proceder a la evaluación de la función correspondiente.
	 * 
	 * @return Verdadero si todos los parámetros son válidos y falso en caso contrario.
	 */
	private boolean parametrosOk() {
		
		// TODO: Validar todos los JTextFields y saltar un JPanel de alerta para los que 
		// estan mal.
		return true;
	}
}

