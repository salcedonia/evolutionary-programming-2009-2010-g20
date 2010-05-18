package gui.componentes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import gui.Ventana;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Clase que gestiona los metodos necesarios para la visualizacion del panel de
 * resultados de la aplicacion.
 * 
 * @author Grupo20.
 */
public class PanelResultados extends JPanel {

	/**
	 * Identificador de la clase.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ventana grafica de la aplicacion.
	 */
	private Ventana _ventana;

	/**
	 * Campo de texto donde se informa del resultado de la ejecucion.
	 */
	private JTextArea _txtInforme;

	/**
	 * ScrollPanel para el area de texto del informe.
	 */
	private JScrollPane _scrPanelInforme;

	/**
	 * Constructor de la clase PanelResultados.
	 * 
	 * @param ventana
	 *            Ventana grafica de la aplicacion.
	 */
	public PanelResultados(Ventana ventana) {

		_ventana = ventana;
		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		
		_txtInforme = new JTextArea(5, 40);
		_txtInforme.setEditable(false);
		_scrPanelInforme = new JScrollPane(_txtInforme);
		_scrPanelInforme.setAutoscrolls(true);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;
		add(_scrPanelInforme, constraints);

		// Borde del panel
		setBorder(new CompoundBorder(BorderFactory
				.createTitledBorder("Panel de Resultados"), new EmptyBorder(0,
				0, 0, 0)));
	}

	/**
	 * Imprime los resultados en el Area de Texto inferior.
	 * 
	 * @param tipoVista
	 *            Tipo de vista
	 * @param tipoCromosoma
	 *            Tipo de cromosoma.
	 * @param elMejor
	 *            El mejor individuo.
	 * @param valor
	 *            El valor del mejor individuo.
	 */
	public void imprimeResultadoConsola(String elMejor, double valor) {

		// Mostramos el mejor individuo
		_txtInforme.append("El Mejor Individuo es:\n" + elMejor + "\n\n");
		_txtInforme.append("Alcanza un valor de: " + valor);
	    _ventana.repaint();
	}

	/**
	 * Vacia el contenido del area de texto.
	 */
	public void limpiar() {

		_txtInforme.setText("");
	}

	/**
	 * Aniade la cadena al area de texto.
	 */
	public void aniadirTexto(String cadena) {

		_txtInforme.append(cadena);
	}
}
