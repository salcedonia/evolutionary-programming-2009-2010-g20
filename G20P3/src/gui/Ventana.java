package gui;

import gui.componentes.PanelAptitud;
import gui.componentes.PanelBotones;
import gui.componentes.PanelFuncion;
import gui.componentes.PanelOpciones;
import gui.componentes.PanelResultados;
import gui.logica.ValidadorDatos;
import gui.tipos.TipoInicializacion;
import gui.tipos.TipoMutacion;
import gui.tipos.TipoSeleccion;
import gui.tipos.TipoVariacion;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import logica.AG;

/**
 * Clase que implementa los metodos necesarios para la gestion de la ventana
 * grafica del usuario.
 * 
 * @author Grupo20.
 */
public class Ventana extends JFrame {

	// CONSTANTES
	private static final long serialVersionUID = 1L;
	private static final int ALTO = 700;
	private static final int ANCHO = 550;
	private static final String TITULO = "Practica 3: Programacion Genetica";
	private static final String ICONO = "AGSIcono.gif";

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
	 * Panel de cuerpo del panel de opciones.
	 */
	private JPanel _panelBodyOpciones;

	/**
	 * Panel de botones del panel de opciones.
	 */
	private PanelBotones _panelBotonesOpciones;

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
	 * Panel donde se muestran los resultados de la ejecucion del AGS.
	 */
	private PanelResultados _panelResultados;

	// ----------TIPOS-------------//
	/**
	 * Tipo de metodo de seleccion usado.
	 */
	private TipoSeleccion _tipoSeleccion = TipoSeleccion.RULETA;
	/**
	 * Tipo de variacion de parametros
	 */
	private TipoVariacion _tipoVariacion = TipoVariacion.NINGUNA;
	/**
	 * Tipo de version del problema del viajante
	 */
	private TipoInicializacion _tipoInicializacion = TipoInicializacion.CRECIENTE;
	/**
	 * Tipo de Mutacion.
	 */
	private TipoMutacion _tipoMutacion = TipoMutacion.TERMINAL_SIMPLE;
	/**
	 * Elitismo.
	 */
	private boolean _elitismo = false;
	/**
	 * Escalado Simple.
	 */
	private boolean _escaladoSimple = false;
	/**
	 * Seleccion de IF.
	 */
	private boolean _ifSeleccionado = false;

	/**
	 * Constructor de la clase Ventana.
	 */
	public Ventana() {

		// Set de las caracteristicas de la ventana
		setTitle(TITULO);
		setIconImage(new ImageIcon(ICONO).getImage());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, ANCHO, ALTO);
		setLocationRelativeTo(null);

		// Inicializacion de los elementos de la ventana
		iniciaInterfaz();
		setEnabled(true);
		setVisible(true);
	}

	/**
	 * Crea los elementos de la ventana.
	 */
	public void iniciaInterfaz() {

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

		// Aniadimos todas las opciones de la aplicacion
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 0.7;
		constraints.fill = GridBagConstraints.BOTH;
		_panelPrincipal.add(creaPanelOpciones(), constraints);

		// Creamos el panel de texto que informa del analisis
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 0.3;
		constraints.fill = GridBagConstraints.BOTH;
		_panelPrincipal.add(creaPanelResultados(), constraints);

		return _panelPrincipal;
	}

	/**
	 * Crea el panel donde se muestran los resultados.
	 * 
	 * @return El panel de resultados creado y configurado.
	 */
	private PanelResultados creaPanelResultados() {

		_panelResultados = new PanelResultados(this);
		return _panelResultados;
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
		constraints.weightx = 1.0;
		constraints.weighty = 0.9;

		// Aniade las opciones del panel de opciones
		_panelBodyOpciones = new PanelOpciones(this);
		_panelOpciones.add(_panelBodyOpciones, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 0.1;

		// Aniade los botones de la aplicacion.
		// Creamos el validador de datos
		_validadorDatos = new ValidadorDatos(this);
		_panelBotonesOpciones = new PanelBotones(this, _validadorDatos);
		_panelOpciones.add(_panelBotonesOpciones, constraints);

		return _panelOpciones;
	}

	/**
	 * Configura todo lo necesario para la evaluacion del AGS.
	 */
	public void comenzarAGS() {

		_panelResultados.limpiar();

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

		// Creamos el objeto encargado del algoritmo genetico simple
		_AG = new AG(_validadorDatos.getNumGeneraciones(), _validadorDatos
				.getTamPoblacion(), _validadorDatos.getProbCruce(),
				_validadorDatos.getProbMutacion(), _tipoSeleccion,
				_tipoInicializacion, _tipoMutacion, _ifSeleccionado, 
				_validadorDatos.getProfundidadMaxima(), _elitismo,
				_escaladoSimple, _validadorDatos.getPorcentajeElite(),
				_validadorDatos.getNumEstimadoCopiasMejor());

		// Crea poblacion inicial de cromosomas
		_AG.inicializa();

		// Inicializa las componentes de las graficas
		_panelAptitud.inicializaGraficas(_validadorDatos.getNumGeneraciones());

		// Evalua los individuos y coge el mejor
		_AG.evaluarPoblacion();

		while (!_AG.terminado()) {

			_AG.aumentarGeneracion();

			// Actualizamos el progreso de la barra
			_panelBotonesOpciones
					.actualizaBarraProgreso(_AG.getNumGeneracion());

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
		if (_AG.getElMejorGlobal().getAptitud() > 500) {
			
			System.out.println("Trwkerw");
		}
		_panelResultados.imprimeResultadoConsola(_AG.getElMejorGlobal()
				.toString(), _AG.getElMejorGlobal().getAptitud());

		// Volvemos a activar el boton
		_panelBotonesOpciones.activarBotonComenzar();
	}

	/**
	 * Ejecuta varias veces el algoritmo genetico simple con la variacion de
	 * parametros introducida en pasoVariacion y limiteVariacion.
	 */
	private void comienzaAGSVariacion() {

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
		case ELITISMO:
			i = _validadorDatos.getPorcentajeElite();
			break;
		}

		double paso = _validadorDatos.getPasoVariacion();
		double limite = _validadorDatos.getLimiteVariacion();

		_panelFuncion.inicializaPanelFuncion(i, paso, limite);

		while (i < limite) {

			switch (_tipoVariacion) {

			case NUM_GENERACION:
				// Creamos el objeto encargado del algoritmo genetico simple
				_AG = new AG((int) i, _validadorDatos.getTamPoblacion(),
						_validadorDatos.getProbCruce(), _validadorDatos
								.getProbMutacion(), _tipoSeleccion, _tipoInicializacion,
						_tipoMutacion, _ifSeleccionado,_validadorDatos.getProfundidadMaxima(),
						_elitismo, _escaladoSimple, _validadorDatos
								.getPorcentajeElite(), _validadorDatos
								.getNumEstimadoCopiasMejor());
				break;
			case NUM_POBLACION:
				// Creamos el objeto encargado del algoritmo genetico simple
				_AG = new AG(_validadorDatos.getNumGeneraciones(), (int) i,
						_validadorDatos.getProbCruce(), _validadorDatos
								.getProbMutacion(), _tipoSeleccion, _tipoInicializacion,
						_tipoMutacion, _ifSeleccionado,_validadorDatos.getProfundidadMaxima(),
						_elitismo, _escaladoSimple, _validadorDatos
								.getPorcentajeElite(), _validadorDatos
								.getNumEstimadoCopiasMejor());
				break;
			case PROB_CRUCE:
				// Creamos el objeto encargado del algoritmo genetico simple
				_AG = new AG(_validadorDatos.getNumGeneraciones(),
						_validadorDatos.getTamPoblacion(), i, _validadorDatos
								.getProbMutacion(), _tipoSeleccion, _tipoInicializacion,
						_tipoMutacion, _ifSeleccionado,_validadorDatos.getProfundidadMaxima(),
						_elitismo, _escaladoSimple, _validadorDatos
								.getPorcentajeElite(), _validadorDatos
								.getNumEstimadoCopiasMejor());
				break;
			case PROB_MUTACION:
				// Creamos el objeto encargado del algoritmo genetico simple
				_AG = new AG(_validadorDatos.getNumGeneraciones(),
						_validadorDatos.getTamPoblacion(), _validadorDatos
								.getProbCruce(), i, _tipoSeleccion, _tipoInicializacion,
						_tipoMutacion, _ifSeleccionado,_validadorDatos.getProfundidadMaxima(),
						_elitismo, _escaladoSimple, _validadorDatos
								.getPorcentajeElite(), _validadorDatos
								.getNumEstimadoCopiasMejor());
				break;
			case ELITISMO:
				// Creamos el objeto encargado del algoritmo genetico simple
				_AG = new AG(_validadorDatos.getNumGeneraciones(),
						_validadorDatos.getTamPoblacion(), _validadorDatos
								.getProbCruce(), _validadorDatos
								.getProbMutacion(), _tipoSeleccion, _tipoInicializacion,
						_tipoMutacion, _ifSeleccionado,_validadorDatos.getProfundidadMaxima(),
						_elitismo, _escaladoSimple, i, _validadorDatos
								.getNumEstimadoCopiasMejor());
				break;
			}

			// Crea poblacion inicial de cromosomas
			_AG.inicializa();

			// Evalua los individuos y coge el mejor
			_AG.evaluarPoblacion();

			while (!_AG.terminado()) {
				_AG.aumentarGeneracion();

				// Actualizamos el progreso de la barra
				_panelBotonesOpciones.actualizaBarraProgreso(_AG
						.getNumGeneracion());

				// No hace nada si no la opcion elitismo esta inactiva
				_AG.separaElite();

				_AG.seleccion();
				_AG.reproduccion();
				_AG.mutacion();

				// No hace nada si no la opcion elitismo esta inactiva
				_AG.incluyeElite();

				_AG.evaluarPoblacion();
			}

			_panelResultados.aniadirTexto("\nEjecucion " + nEjecucion
					+ " - Parametro Variable: " + i + "\n");
			_panelResultados.imprimeResultadoConsola(_AG.getElMejorGlobal()
					.toString(), _AG.getElMejorGlobal().getAptitud());

			_panelFuncion.guardaDatosEjecucion(_AG);

			nEjecucion++;
			i += paso;
		}

		_panelBotonesOpciones.activarBotonComenzar();
		_panelFuncion.imprimeDatosPanelFuncion();
	}

	/**
	 * Establece el tipo de variacion de parametros.
	 * 
	 * @param tipoVariacion
	 *            Tipo de variacion de parametros.
	 */
	public void setTipoVariacion(TipoVariacion tipoVariacion) {

		_tipoVariacion = tipoVariacion;
	}

	/**
	 * Establece el tipo de inicializacion a usar en el AGS.
	 * 
	 * @param tipoInicializacion
	 *            Tipo de inicializacion a usar.
	 */
	public void setTipoInicializacion(TipoInicializacion tipoInicializacion) {

		_tipoInicializacion = tipoInicializacion;
	}

	/**
	 * Establece el tipo de mutacion a usar en el AGS.
	 * 
	 * @param tipoMutacion
	 *            Tipo de mutacion a usar.
	 */
	public void setTipoMutacion(TipoMutacion tipoMutacion) {

		_tipoMutacion = tipoMutacion;
	}

	/**
	 * Establece el tipo de metodo de seleccion a usar en el AGS.
	 * 
	 * @param tipoSeleccion
	 *            Tipo de metodo de seleccion a usar.
	 */
	public void setTipoSeleccion(TipoSeleccion tipoSeleccion) {

		_tipoSeleccion = tipoSeleccion;
	}
	
	/**
	 * Establece el etilismo a valor elitismo.
	 * 
	 * @param elitismo
	 *            Nuevo valor a establecer.
	 */
	public void setElitismo(boolean elitismo) {

		_elitismo = elitismo;
	}

	/**
	 * Devuelve el valor de elitismo.
	 * 
	 * @return Verdadero o falso dependiendo del valor de elitismo.
	 */
	public boolean getElitismo() {

		return _elitismo;
	}

	/**
	 * Establece el escaladoSimple a valor escaladoSimple.
	 * 
	 * @param escaladoSimple
	 *            Nuevo valor a establecer.
	 */
	public void setEscaladoSimple(boolean escaladoSimple) {

		_escaladoSimple = escaladoSimple;
	}

	/**
	 * Devuelve el valor de escaladoSimple.
	 * 
	 * @return Verdadero o falso dependiendo del valor de escaladoSimple.
	 */
	public boolean getEscaladoSimple() {

		return _escaladoSimple;
	}

	/**
	 * Establece el ifSeleccionado a valor ifSeleccionado.
	 * 
	 * @param ifSeleccionado
	 *            Nuevo valor a establecer.
	 */
	public void setIfSeleccionado(boolean ifSeleccionado) {

		_ifSeleccionado = ifSeleccionado;
	}

	/**
	 * Devuelve el valor de ifSeleccionado.
	 * 
	 * @return Verdadero o falso dependiendo del valor de ifSeleccionado.
	 */
	public boolean getIfSeleccionado() {

		return _ifSeleccionado;
	}

	/**
	 * Devuelve el campo de texto del numero de generaciones del
	 * panelBodyOpciones.
	 * 
	 * @return El campo de texto del numero de generaciones del
	 *         panelBodyOpciones.
	 */
	public JTextField getTxtNumGeneraciones() {

		return ((PanelOpciones) _panelBodyOpciones).getTxtNumGeneraciones();
	}

	/**
	 * Devuelve el campo de texto del tamanio de la poblacion del
	 * panelBodyOpciones.
	 * 
	 * @return El campo de texto del tamanio de la poblacion del
	 *         panelBodyOpciones.
	 */
	public JTextField getTxtTamPoblacion() {

		return ((PanelOpciones) _panelBodyOpciones).getTxtTamPoblacion();
	}

	/**
	 * Devuelve el campo de texto del porcentaje de la elite del
	 * panelBodyOpciones.
	 * 
	 * @return El campo de texto del porcentaje de la elite N del
	 *         panelBodyOpciones.
	 */
	public JTextField getTxtPorcentajeElite() {

		return ((PanelOpciones) _panelBodyOpciones).getTxtPorcentajeElite();
	}

	/**
	 * Devuelve el campo de texto del paso de variacion del panelBodyOpciones.
	 * 
	 * @return El campo de texto del paso de variacion del panelBodyOpciones.
	 */
	public JTextField getTxtPasoVariacion() {

		return ((PanelOpciones) _panelBodyOpciones).getTxtPasoVariacion();
	}

	/**
	 * Devuelve el campo de texto del limite de variacion del panelBodyOpciones.
	 * 
	 * @return El campo de texto del limite de variacion del panelBodyOpciones.
	 */
	public JTextField getTxtLimiteVariacion() {

		return ((PanelOpciones) _panelBodyOpciones).getTxtLimiteVariacion();
	}

	/**
	 * Devuelve el campo de texto del numero estimado de copias del mejor para
	 * el escalado simple del panelBodyOpciones.
	 * 
	 * @return El campo de texto del numero estimado de copias del mejor para el
	 *         escalado simple del panelBodyOpciones.
	 */
	public JTextField getTxtNumEstimadoCopiasMejor() {

		return ((PanelOpciones) _panelBodyOpciones)
				.getTxtNumEstimadoCopiasMejor();
	}

	/**
	 * Devuelve el tipo de variacion seleccionada.
	 * 
	 * @return El tipo de variacion seleccionada.
	 */
	public TipoVariacion getTipoVariacion() {
		return _tipoVariacion;
	}

	/**
	 * Devuelve la probabilidad de cruce.
	 * 
	 * @return La probabilidad de cruce.
	 */
	public JSpinner getSpiProbCruce() {
		return ((PanelOpciones) _panelBodyOpciones).getSpiProbCruce();
	}

	/**
	 * Devuelve la probabilidad de mutacion.
	 * 
	 * @return La probabilidad de mutacion.
	 */
	public JSpinner getSpiProbMutacion() {
		return ((PanelOpciones) _panelBodyOpciones).getSpiProbMutacion();
	}

	/**
	 * Devuelve la profundidad maxima del arbol.
	 * 
	 * @return La profundidad maxima del arbol.
	 */
	public JTextField getTxtProfundidadMaxima() {
		return ((PanelOpciones) _panelBodyOpciones)
		.getTxtProfundidadMaxima();
	}
}
