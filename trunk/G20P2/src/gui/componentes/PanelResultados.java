package gui.componentes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import gui.Ventana;
import gui.tipos.TipoCromosoma;
import gui.tipos.TipoVista;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
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
	 * Etiqueta informativa acerca del resultado del AGS.
	 */
	private JLabel _lblInforme;

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
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.BOTH;
		_lblInforme = new JLabel("Resultado de la Ejecucion: ");
		add(_lblInforme, constraints);

		_txtInforme = new JTextArea(5, 40);
		_txtInforme.setEditable(false);

		_scrPanelInforme = new JScrollPane(_txtInforme);
		_scrPanelInforme.setAutoscrolls(true);
		constraints.gridx = 0;
		constraints.gridy = 1;
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
	public void imprimeResultadoConsola(TipoVista tipoVista,
			TipoCromosoma tipoCromosoma, String elMejor, double valor) {

		switch (tipoVista) {

		case PRACTICA1:
			switch (tipoCromosoma) {

			case FUNCION1:
				// Mostramos el mejor individuo
				_txtInforme.append("El Mejor Valor es:\n" + elMejor + "\n\n");
				_txtInforme.append("Alcanza un Maximo de: " + valor);
				break;
			case FUNCION2:
				_txtInforme.append("Los Mejores Valores son:\n" + elMejor
						+ "\n\n");
				_txtInforme.append("Alcanza un Maximo de: " + valor);
				break;
			case FUNCION3:
				_txtInforme.append("El Mejor Valor es:\n" + elMejor + "\n\n");
				_txtInforme.append("Alcanza un Minimo de: " + valor);
				break;
			case FUNCION4:
				_txtInforme.append("Los Mejores Valores son:\n" + elMejor
						+ "\n\n");
				_txtInforme.append("Alcanza un Minimo de: " + valor);
				break;
			case FUNCION5:
				_txtInforme.append("Los Mejores Valores son: \n\n" + elMejor
						+ "\n");
				_txtInforme.append("Alcanza un Minimo de: " + valor);
				break;
			}
			break;

		case PRACTICA2:
			
			_txtInforme.append("La minima ruta es " + valor + " km " +
					"con la siguiente ruta: \n" + elMejor);

			break;

		case PRACTICA3:
			break;
		}
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
