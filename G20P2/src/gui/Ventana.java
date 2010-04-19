package gui;

import gui.componentes.BarraDeMenu;
import gui.componentes.PanelAptitud;
import gui.componentes.PanelBotones;
import gui.componentes.PanelFuncion;
import gui.componentes.PanelOpcionesPractica1;
import gui.componentes.PanelOpcionesPractica2;
import gui.componentes.PanelResultados;
import gui.logica.ValidadorDatos;
import gui.tipos.TipoCromosoma;
import gui.tipos.TipoCruce;
import gui.tipos.TipoMutacion;
import gui.tipos.TipoProblema;
import gui.tipos.TipoSeleccion;
import gui.tipos.TipoVariacion;
import gui.tipos.TipoVersion;
import gui.tipos.TipoVista;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
	private static final int ANCHO = 600;
	private static final String TITULO = "Practica 2: AGS";
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

	/**
	 * Barra de Menu.
	 */
	@SuppressWarnings("unused")
	private BarraDeMenu _barraDeMenu;

	// ----------TIPOS-------------//
	/**
	 * Tipo de vista a mostrar.
	 */
	private TipoVista _tipoVista = TipoVista.PRACTICA1;
	/**
	 * Tipo de variacion de parametros
	 */
	private TipoVariacion _tipoVariacion = TipoVariacion.NINGUNA;
	/**
	 * Tipo de version del problema del viajante
	 */
	private TipoVersion _tipoVersion = TipoVersion.VERSION1;
	/**
	 * Tipo de seleccion.
	 */
	private TipoSeleccion _tipoSeleccion = TipoSeleccion.RULETA;
	/**
	 * Tipo de cruce.
	 */
	private TipoCruce _tipoCruce = TipoCruce.CICLOS_CX;
	/**
	 * Tipo de Mutacion.
	 */
	private TipoMutacion _tipoMutacion = TipoMutacion.INSERCION;
	/**
	 * Tipo de cromosoma a crear.
	 */
	private TipoCromosoma _tipoCromosoma = TipoCromosoma.FUNCION1;
	/**
	 * Elitismo.
	 */
	private boolean _elitismo = false;
	/**
	 * Escalado Simple.
	 */
	private boolean _escaladoSimple = false;
	/**
	 * Tipo de problema para la practica 1.
	 */
	private TipoProblema _tipoProblema = TipoProblema.MAXIMIZACION;

	/**
	 * Constructor de la clase Ventana.
	 */
	public Ventana() {

		// Set de las caracteristicas de la ventana
		setTitle(TITULO);
		setIconImage(new ImageIcon(ICONO).getImage());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(10, 50, ANCHO, ALTO);
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

		// Se crea la barra de menu
		_barraDeMenu = new BarraDeMenu(this);

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

		// Aniadimos todas las opciones de la aplicacion
		_panelPrincipal.add(creaPanelOpciones(), constraints);

		// Creamos el panel de texto que informa del analisis
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 1.5;
		constraints.weightx = 1.5;

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
		constraints.weighty = 1.0;
		constraints.weightx = 1.0;

		// Aniade las opciones del panel de opciones

		switch (_tipoVista) {

		case PRACTICA1:
			_panelBodyOpciones = new PanelOpcionesPractica1(this);
			break;
		case PRACTICA2:
			_panelBodyOpciones = new PanelOpcionesPractica2(this);
			break;
		case PRACTICA3: // Por hacer
			break;
		}
		_panelOpciones.add(_panelBodyOpciones, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 1.0;
		constraints.weightx = 1.0;

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

		switch (_tipoVista) {

		case PRACTICA1:

			// Inicializamos el tipo de problema a resolver
			setTipoProblema();

			// Creamos el objeto encargado del algoritmo genetico simple
			_AG = new AG(_validadorDatos.getNumGeneraciones(), _validadorDatos
					.getTamPoblacion(), _validadorDatos.getProbCruce(),
					_validadorDatos.getProbMutacion(), _validadorDatos
							.getPrecision(), _validadorDatos.getValorN(),
					_elitismo, _escaladoSimple, _tipoCromosoma, _tipoProblema,
					_tipoVersion, _tipoSeleccion, _tipoCruce, _tipoMutacion,
					_tipoVista, _validadorDatos.getPorcentageElite(),
					_validadorDatos.getNumEstimadoCopiasMejor());

			// Crea poblacion inicial de cromosomas
			_AG.inicializa();

			// Inicializa las componentes de las graficas
			_panelAptitud.inicializaGraficas(_validadorDatos
					.getNumGeneraciones());

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

				// Guardamos los datos de las graficas
				_panelAptitud.guardaDatosGraficas(_AG);
			}

			// Actualizamos las graficas
			_panelAptitud.imprimeDatosGraficas(_validadorDatos
					.getNumGeneraciones());

			// Mostramos el resultado en el cuadro de texto de informe.
			_panelResultados.imprimeResultadoConsola(_tipoCromosoma, _AG
					.getElMejorGlobal().toString(), _AG.getElMejorGlobal().f());

			// Volvemos a activar el boton
			_panelBotonesOpciones.activarBotonComenzar();

			break;

		case PRACTICA2:
			break;
		case PRACTICA3:
			break;
		}

	}

	/**
	 * Ejecuta varias veces el algoritmo genetico simple con la variacion de
	 * parametros introducida en pasoVariacion y limiteVariacion.
	 */
	private void comienzaAGSVariacion() {

		switch (_tipoVista) {

		case PRACTICA1:
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
									.getPrecision(), _validadorDatos
									.getValorN(), _elitismo, _escaladoSimple,
							_tipoCromosoma, _tipoProblema, _tipoVersion,
							_tipoSeleccion, _tipoCruce, _tipoMutacion,
							_tipoVista, _validadorDatos.getPorcentageElite(),
							_validadorDatos.getNumEstimadoCopiasMejor());
					break;
				case NUM_POBLACION:
					// Creamos el objeto encargado del algoritmo genetico simple
					_AG = new AG(_validadorDatos.getNumGeneraciones(), (int) i,
							_validadorDatos.getProbCruce(), _validadorDatos
									.getProbMutacion(), _validadorDatos
									.getPrecision(), _validadorDatos
									.getValorN(), _elitismo, _escaladoSimple,
							_tipoCromosoma, _tipoProblema, _tipoVersion,
							_tipoSeleccion, _tipoCruce, _tipoMutacion,
							_tipoVista, _validadorDatos.getPorcentageElite(),
							_validadorDatos.getNumEstimadoCopiasMejor());
					break;
				case PROB_CRUCE:
					// Creamos el objeto encargado del algoritmo genetico simple
					_AG = new AG(_validadorDatos.getNumGeneraciones(),
							_validadorDatos.getTamPoblacion(), i,
							_validadorDatos.getProbMutacion(), _validadorDatos
									.getPrecision(), _validadorDatos
									.getValorN(), _elitismo, _escaladoSimple,
							_tipoCromosoma, _tipoProblema, _tipoVersion,
							_tipoSeleccion, _tipoCruce, _tipoMutacion,
							_tipoVista, _validadorDatos.getPorcentageElite(),
							_validadorDatos.getNumEstimadoCopiasMejor());
					break;
				case PROB_MUTACION:
					// Creamos el objeto encargado del algoritmo genetico simple
					_AG = new AG(_validadorDatos.getNumGeneraciones(),
							_validadorDatos.getTamPoblacion(), _validadorDatos
									.getProbCruce(), i, _validadorDatos
									.getPrecision(), _validadorDatos
									.getValorN(), _elitismo, _escaladoSimple,
							_tipoCromosoma, _tipoProblema, _tipoVersion,
							_tipoSeleccion, _tipoCruce, _tipoMutacion,
							_tipoVista, _validadorDatos.getPorcentageElite(),
							_validadorDatos.getNumEstimadoCopiasMejor());
					break;
				case PRECISION:
					// Creamos el objeto encargado del algoritmo genetico simple
					_AG = new AG(_validadorDatos.getNumGeneraciones(),
							_validadorDatos.getTamPoblacion(), _validadorDatos
									.getProbCruce(), _validadorDatos
									.getProbMutacion(), i, _validadorDatos
									.getValorN(), _elitismo, _escaladoSimple,
							_tipoCromosoma, _tipoProblema, _tipoVersion,
							_tipoSeleccion, _tipoCruce, _tipoMutacion,
							_tipoVista, _validadorDatos.getPorcentageElite(),
							_validadorDatos.getNumEstimadoCopiasMejor());
					break;
				case VALOR_N:
					// Creamos el objeto encargado del algoritmo genetico simple
					_AG = new AG(_validadorDatos.getNumGeneraciones(),
							_validadorDatos.getTamPoblacion(), _validadorDatos
									.getProbCruce(), _validadorDatos
									.getProbMutacion(), _validadorDatos
									.getPrecision(), (int) i, _elitismo,
							_escaladoSimple, _tipoCromosoma, _tipoProblema,
							_tipoVersion, _tipoSeleccion, _tipoCruce,
							_tipoMutacion, _tipoVista, _validadorDatos
									.getPorcentageElite(), _validadorDatos
									.getNumEstimadoCopiasMejor());
					break;
				case ELITISMO:
					// Creamos el objeto encargado del algoritmo genetico simple
					_AG = new AG(_validadorDatos.getNumGeneraciones(),
							_validadorDatos.getTamPoblacion(), _validadorDatos
									.getProbCruce(), _validadorDatos
									.getProbMutacion(), _validadorDatos
									.getPrecision(), _validadorDatos
									.getValorN(), _elitismo, _escaladoSimple,
							_tipoCromosoma, _tipoProblema, _tipoVersion,
							_tipoSeleccion, _tipoCruce, _tipoMutacion,
							_tipoVista, i, _validadorDatos
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
				_panelResultados.imprimeResultadoConsola(_tipoCromosoma, _AG
						.getElMejorGlobal().toString(), _AG.getElMejorGlobal()
						.f());

				_panelFuncion.guardaDatosEjecucion(_AG);

				nEjecucion++;
				i += paso;
			}

			_panelBotonesOpciones.activarBotonComenzar();
			_panelFuncion.imprimeDatosPanelFuncion();

			break;

		case PRACTICA2:
			break;
		case PRACTICA3:
			break;

		}
	}

	/**
	 * Devuelve el tipo de problema segun la funcion seleccionada actualmente.
	 */
	private void setTipoProblema() {

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
		}
	}

	/**
	 * Establece el tipo de vista a mostrar en el panel de opciones.
	 * 
	 * @param tipoVista
	 *            Tipo de Vista a mostrar.
	 */
	public void setTipoVista(TipoVista tipoVista) {

		_tipoVista = tipoVista;
	}

	/**
	 * Establece el tipo de version del problema del viajante a tratar.
	 * 
	 * @param tipoVersion
	 *            El tipo de version a presentar.
	 */
	public void setTipoVersion(TipoVersion tipoVersion) {

		_tipoVersion = tipoVersion;
	}

	/**
	 * Establece el tipo de seleccion a usar en el AGS.
	 * 
	 * @param tipoSeleccion
	 *            Tipo de seleccion a usar.
	 */
	public void setTipoSeleccion(TipoSeleccion tipoSeleccion) {

		_tipoSeleccion = tipoSeleccion;
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
	 * Establece el tipo de cruce a usar en el AGS.
	 * 
	 * @param tipoCruce
	 *            Tipo de cruce a usar.
	 */
	public void setTipoCruce(TipoCruce tipoCruce) {

		_tipoCruce = tipoCruce;
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
	 * Establece el tipo de cromosoma a valor cromosoma.
	 * 
	 * @param cromosoma
	 *            Nuevo valor a establecer.
	 */
	public void setTipoCromosoma(TipoCromosoma cromosoma) {

		_tipoCromosoma = cromosoma;
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
	 * Devuelve el campo de texto del numero de generaciones del
	 * panelBodyOpciones.
	 * 
	 * @return El campo de texto del numero de generaciones del
	 *         panelBodyOpciones.
	 */
	public JTextField getTxtNumGeneraciones() {

		switch (_tipoVista) {

		case PRACTICA1:
			return ((PanelOpcionesPractica1) _panelBodyOpciones)
					.getTxtNumGeneraciones();
		case PRACTICA2:
			return ((PanelOpcionesPractica2) _panelBodyOpciones)
					.getTxtNumGeneraciones();
		case PRACTICA3: // Por hacer
			break;

		}

		return null;
	}

	/**
	 * Devuelve el campo de texto del tamanio de la poblacion del
	 * panelBodyOpciones.
	 * 
	 * @return El campo de texto del tamanio de la poblacion del
	 *         panelBodyOpciones.
	 */
	public JTextField getTxtTamPoblacion() {

		switch (_tipoVista) {

		case PRACTICA1:
			return ((PanelOpcionesPractica1) _panelBodyOpciones)
					.getTxtTamPoblacion();
		case PRACTICA2:
			return ((PanelOpcionesPractica2) _panelBodyOpciones)
					.getTxtTamPoblacion();
		case PRACTICA3: // Por hacer
			break;

		}

		return null;
	}

	/**
	 * Devuelve el campo de texto de la probabilidad de cruce del
	 * panelBodyOpciones.
	 * 
	 * @return El campo de texto de la probabilidad de cruce del
	 *         panelBodyOpciones.
	 */
	public JTextField getTxtProbCruce() {

		switch (_tipoVista) {

		case PRACTICA1:
			return ((PanelOpcionesPractica1) _panelBodyOpciones)
					.getTxtProbCruce();
		case PRACTICA2:
			return ((PanelOpcionesPractica2) _panelBodyOpciones)
					.getTxtProbCruce();
		case PRACTICA3: // Por hacer
			break;

		}

		return null;
	}

	/**
	 * Devuelve el campo de texto de la probabilidad de mutacion del
	 * panelBodyOpciones.
	 * 
	 * @return El campo de texto de la probabilidad de mutacion del
	 *         panelBodyOpciones.
	 */
	public JTextField getTxtProbMutacion() {

		switch (_tipoVista) {

		case PRACTICA1:
			return ((PanelOpcionesPractica1) _panelBodyOpciones)
					.getTxtProbMutacion();
		case PRACTICA2:
			return ((PanelOpcionesPractica2) _panelBodyOpciones)
					.getTxtProbMutacion();
		case PRACTICA3: // Por hacer
			break;

		}

		return null;
	}

	/**
	 * Devuelve el campo de texto de la precision del panelBodyOpciones.
	 * 
	 * @return El campo de texto de la precision del panelBodyOpciones.
	 */
	public JTextField getTxtPrecision() {

		switch (_tipoVista) {

		case PRACTICA1:
			return ((PanelOpcionesPractica1) _panelBodyOpciones)
					.getTxtPrecision();
		case PRACTICA2: // No tiene
		case PRACTICA3: // Por hacer
			break;

		}

		return null;
	}

	/**
	 * Devuelve el campo de texto del valor del parametro N del
	 * panelBodyOpciones.
	 * 
	 * @return El campo de texto del valor del parametro N del
	 *         panelBodyOpciones.
	 */
	public JTextField getTxtValorN() {

		switch (_tipoVista) {

		case PRACTICA1:
			return ((PanelOpcionesPractica1) _panelBodyOpciones).getTxtValorN();
		case PRACTICA2: // No tiene
		case PRACTICA3: // Por hacer
			break;

		}

		return null;
	}

	/**
	 * Devuelve el campo de texto del porcentaje de la elite del
	 * panelBodyOpciones.
	 * 
	 * @return El campo de texto del porcentaje de la elite N del
	 *         panelBodyOpciones.
	 */
	public JTextField getTxtPorcentajeElite() {

		switch (_tipoVista) {

		case PRACTICA1:
			return ((PanelOpcionesPractica1) _panelBodyOpciones)
					.getTxtPorcentajeElite();
		case PRACTICA2:
			return ((PanelOpcionesPractica2) _panelBodyOpciones)
					.getTxtPorcentajeElite();
		case PRACTICA3: // Por hacer
			break;

		}

		return null;
	}

	/**
	 * Devuelve el campo de texto del paso de variacion del panelBodyOpciones.
	 * 
	 * @return El campo de texto del paso de variacion del panelBodyOpciones.
	 */
	public JTextField getTxtPasoVariacion() {

		switch (_tipoVista) {

		case PRACTICA1:
			return ((PanelOpcionesPractica1) _panelBodyOpciones)
					.getTxtPasoVariacion();
		case PRACTICA2:
			return ((PanelOpcionesPractica2) _panelBodyOpciones)
					.getTxtPasoVariacion();
		case PRACTICA3: // Por hacer
			break;

		}

		return null;
	}

	/**
	 * Devuelve el campo de texto del limite de variacion del panelBodyOpciones.
	 * 
	 * @return El campo de texto del limite de variacion del panelBodyOpciones.
	 */
	public JTextField getTxtLimiteVariacion() {

		switch (_tipoVista) {

		case PRACTICA1:
			return ((PanelOpcionesPractica1) _panelBodyOpciones)
					.getTxtLimiteVariacion();
		case PRACTICA2:
			return ((PanelOpcionesPractica2) _panelBodyOpciones)
					.getTxtLimiteVariacion();
		case PRACTICA3: // Por hacer
			break;

		}

		return null;
	}

	/**
	 * Devuelve el campo de texto del numero estimado de copias del mejor para
	 * el escalado simple del panelBodyOpciones.
	 * 
	 * @return El campo de texto del numero estimado de copias del mejor para el
	 *         escalado simple del panelBodyOpciones.
	 */
	public JTextField getTxtNumEstimadoCopiasMejor() {

		switch (_tipoVista) {

		case PRACTICA1:
			return ((PanelOpcionesPractica1) _panelBodyOpciones)
					.getTxtNumEstimadoCopiasMejor();
		case PRACTICA2:
			return ((PanelOpcionesPractica2) _panelBodyOpciones)
					.getTxtNumEstimadoCopiasMejor();
		case PRACTICA3: // Por hacer
			break;

		}

		return null;
	}

	/**
	 * Devuelve el tipo de vista usada en la interfaz.
	 * 
	 * @return El tipo de vista usada en la interfaz.
	 */
	public TipoVista getTipoVista() {

		return _tipoVista;
	}

	/**
	 * Devuelve el tipo de variacion seleccionada.
	 * 
	 * @return El tipo de variacion seleccionada.
	 */
	public TipoVariacion getTipoVariacion() {
		return _tipoVariacion;
	}
}
