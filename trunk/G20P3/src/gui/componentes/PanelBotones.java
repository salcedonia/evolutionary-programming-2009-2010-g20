package gui.componentes;

import gui.Ventana;
import gui.logica.ValidadorDatos;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Clase que se encarga de implementar los metodos necesarios para la gestion de
 * los componentes que forman el panel de los botones de acciones y la barra de
 * progreso.
 * 
 * @author Grupo20.
 */
public class PanelBotones extends JPanel {

	/**
	 * Indentificacion de la clase.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ventana grafica de la aplicacion.
	 */
	private Ventana _ventana;

	/**
	 * Barra de progreso.
	 */
	private JProgressBar _barraProgreso;

	/**
	 * Boton de comienzo de ejecucion del algoritmo simple.
	 */
	private JButton _btnComenzar;

	/**
	 * Clase validadora de datos.
	 */
	private ValidadorDatos _validadorDatos;

	/**
	 * Constructor de la clase PanelBotones.
	 * 
	 * @param ventana
	 *            Ventana grafica de la aplicacion.
	 */
	public PanelBotones(Ventana ventana, ValidadorDatos validadorDatos) {

		_ventana = ventana;
		_validadorDatos = validadorDatos;

		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		_btnComenzar = new JButton("Comenzar");
		_btnComenzar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				if (_validadorDatos.parametrosOk(_ventana.getTipoVariacion())) {

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
							_ventana.comenzarAGS();
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
		constraints.weightx = 1.0;
		constraints.weighty = 0.5;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.NONE;
		add(_btnComenzar, constraints);

		// Barra de Progreso
		_barraProgreso = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		_barraProgreso.setValue(0);
		_barraProgreso.setStringPainted(true);
		_barraProgreso.setPreferredSize(new Dimension(350, 20));
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 0.5;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;
		add(_barraProgreso, constraints);

		// Borde del panel
		setBorder(new CompoundBorder(BorderFactory
				.createTitledBorder("Panel de Ejecucion"), new EmptyBorder(0,
				10, 10, 10)));
	}

	/**
	 * Actualiza la barra de progreso a valor valor.
	 * 
	 * @param valor
	 *            Nuevo valor a establecer.
	 */
	public void actualizaBarraProgreso(int valor) {

		_barraProgreso.setValue(valor);
	}

	/**
	 * Habilita el boton de comenzar.
	 */
	public void activarBotonComenzar() {

		_btnComenzar.setEnabled(true);
	}

	/**
	 * Deshabilita el boton de comenzar.
	 */
	public void desactivarBotonComenzar() {

		_btnComenzar.setEnabled(true);
	}
}
