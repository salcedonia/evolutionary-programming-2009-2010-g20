package gui.logica;

import gui.Ventana;
import gui.tipos.TipoVariacion;

import javax.swing.JOptionPane;

/**
 * Clase que se encarga de validar y transformar los datos introducidos por el
 * usuario en la ventana de la interfaz.
 * 
 * @author Grupo20.
 */
public class ValidadorDatos {

	/**
	 * Ventana grafica de la aplicacion.
	 */
	private Ventana _ventana;

	/**
	 * Numero de generaciones.
	 */
	private int _numGeneraciones;

	/**
	 * Tama帽o de la poblacion.
	 */
	private int _tamPoblacion;

	/**
	 * Probabilidad de cruce.
	 */
	private double _probCruce;

	/**
	 * Probabilidad de Mutacion.
	 */
	private double _probMutacion;

	/**
	 * Porcentage de la elite respecto a la poblacion.
	 */
	private double _porcentajeElite;

	/**
	 * Paso al que avanza la variacion de par醡etros.
	 */
	private double _pasoVariacion;

	/**
	 * L韒ite para la variacion de par醡etros.
	 */
	private double _limiteVariacion;

	/**
	 * Numero de copias estimadas para el mejor individuo en el Escalado Simple.
	 */
	private int _numEstimadoCopiasMejor;

	/**
	 * Constructor de la clase Validador de Datos.
	 * 
	 * @param ventana
	 *            Ventana grafica de la aplicacion.
	 */
	public ValidadorDatos(Ventana ventana) {

		// Guardamos la ventana sobre la que realizamos la validacion.
		_ventana = ventana;
	}

	/**
	 * Comprueba que todos los valores introducidos por el usuario son validos
	 * para proceder a la evaluacion de la funcion correspondiente.
	 * 
	 * @param variacion
	 *            El tipo de parametro a variar.
	 * @param tipoVista
	 *            El tipo de vista de la interfaz.
	 * @return Verdadero si todos los parametros son validos y falso en caso
	 *         contrario.
	 */
	public boolean parametrosOk(TipoVariacion variacion) {

		return numGeneracionesOk() && tamPoblacionOk()
					&& porcentajeEliteOk() && pasoVariacionOk()
					&& limiteVariacionOk() && VariacionOk(variacion)
					&& NumEstimadoCopiasMejorOk();

	}

	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de
	 * numero de Generaciones. Cuando ha comprobado que el resultado es optimo
	 * entonces guarda el valor en la variable entera correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean numGeneracionesOk() {

		int numGeneraciones;

		try {

			if (_ventana.getTxtNumGeneraciones().getText().matches("")) {
				JOptionPane.showMessageDialog(_ventana,
						"!Debe introducir el Numero de Generaciones!",
						"Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				numGeneraciones = Integer.parseInt(_ventana
						.getTxtNumGeneraciones().getText());

				if (numGeneraciones < 0) {
					JOptionPane
							.showMessageDialog(
									_ventana,
									"!El Numero de Generaciones tiene que ser un numero entero positivo!",
									"Error en los datos",
									JOptionPane.ERROR_MESSAGE);
					return false;
				} else
					// Guardamos el resultado de la validacion
					_numGeneraciones = numGeneraciones;
			}
		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(_ventana,
					"!Numero de Generaciones tiene que ser un numero entero!",
					"Error en los datos", JOptionPane.ERROR_MESSAGE);

			return false;
		}

		return true;
	}

	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de
	 * Tamanio de Poblacion. Cuando ha comprobado que el resultado es optimo
	 * entonces guarda el valor en la variable entera correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean tamPoblacionOk() {

		int tamPoblacion;

		try {

			if (_ventana.getTxtTamPoblacion().getText().matches("")) {
				JOptionPane.showMessageDialog(_ventana,
						"!Debe introducir el Tamanio de la poblacion!",
						"Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				tamPoblacion = Integer.parseInt(_ventana.getTxtTamPoblacion()
						.getText());

				if (tamPoblacion < 0) {
					JOptionPane
							.showMessageDialog(
									_ventana,
									"!El Tamanio de la Poblacion tiene que ser un numero entero positivo!",
									"Error en los datos",
									JOptionPane.ERROR_MESSAGE);
					return false;
				} else
					// Guardamos el resultado de la validacion
					_tamPoblacion = tamPoblacion;
			}
		} catch (NumberFormatException e) {

			JOptionPane
					.showMessageDialog(
							_ventana,
							"!El Tamanio de la Poblacion tiene que ser un numero entero!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);

			return false;
		}

		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de
	 * Probablidad de Cruce. Cuando ha comprobado que el resultado es optimo
	 * entonces guarda el valor en la variable real correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean probCruceOk() {

		double probCruce;

		try {

			String valor = _ventana.getSpiProbCruce().getModel().getValue().toString();
			
			if (valor.matches("")) {
				JOptionPane.showMessageDialog(_ventana,
						"!Debe introducir la Probabilidad de Cruce!",
						"Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				probCruce = Double.parseDouble(valor);

				if (probCruce >= 0 && probCruce <= 1) {
					// Guardamos el resultado de la validacion
					_probCruce = probCruce;
				} else {
					JOptionPane
							.showMessageDialog(
									_ventana,
									"!La Probabilidad de Cruce tiene que ser un numero entero positivo entre 0 y 1!",
									"Error en los datos",
									JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		} catch (NumberFormatException e) {

			JOptionPane
					.showMessageDialog(
							_ventana,
							"!La Probabilidad de Cruce tiene que ser un numero entero!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);

			return false;
		}

		return true;
	}

	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de
	 * Probablidad de Mutacion. Cuando ha comprobado que el resultado es optimo
	 * entonces guarda el valor en la variable real correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean probMutacionOk() {

		double probMutacion;

		try {

			String valor = _ventana.getSpiProbMutacion().getModel().getValue().toString();
			
			if (valor.matches("")) {
				JOptionPane.showMessageDialog(_ventana,
						"!Debe introducir la Probabilidad de Mutacion!",
						"Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				probMutacion = Double.parseDouble(valor);

				if (probMutacion >= 0 && probMutacion <= 1) {
					// Guardamos el resultado de la validacion
					_probMutacion = probMutacion;
				} else {
					JOptionPane
							.showMessageDialog(
									_ventana,
									"!La Probabilidad de Mutacion tiene que ser un numero entero positivo entre 0 y 1!",
									"Error en los datos",
									JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		} catch (NumberFormatException e) {

			JOptionPane
					.showMessageDialog(
							_ventana,
							"!La Probabilidad de Mutacion tiene que ser un numero entero!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);

			return false;
		}

		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el campo de texto del
	 * porcentage de elite.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean porcentajeEliteOk() {

		double porcentajeElite;

		try {
			if (_ventana.getTxtPorcentajeElite().getText().matches("")) {
				JOptionPane.showMessageDialog(_ventana,
						"!Debe introducir el % para el tamanio de la elite!",
						"Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				porcentajeElite = Double.parseDouble(_ventana
						.getTxtPorcentajeElite().getText());

				if ((porcentajeElite < 0) || (porcentajeElite > 1)) {
					JOptionPane
							.showMessageDialog(
									_ventana,
									"!El porcentage de la elite tiene que ser un numero entre 0 y 1!",
									"Error en los datos",
									JOptionPane.ERROR_MESSAGE);
					return false;
				} else
					// Guardamos el resultado de la validaci贸n
					_porcentajeElite = porcentajeElite;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(_ventana,
					"!El % de la elite tiene que ser un numero real!",
					"Error en los datos", JOptionPane.ERROR_MESSAGE);

			return false;
		}

		return true;
	}

	/**
	 * Evalua la validez de los datos introducidos en el campo de texto de paso
	 * de variacion de parametros.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean pasoVariacionOk() {

		double pasoVariacion;

		try {
			if (_ventana.getTxtPasoVariacion().getText().matches("")) {
				JOptionPane
						.showMessageDialog(
								_ventana,
								"ebe introducir el paso de la Variacion de Parametros!",
								"Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				pasoVariacion = Double.parseDouble(_ventana
						.getTxtPasoVariacion().getText());

				if (pasoVariacion <= 0) {
					JOptionPane
							.showMessageDialog(
									_ventana,
									"!El paso de la Variacion de Parametros tiene que ser un numero positivo!",
									"Error en los datos",
									JOptionPane.ERROR_MESSAGE);
					return false;
				} else
					// Guardamos el resultado de la validaci贸n
					_pasoVariacion = pasoVariacion;
			}
		} catch (NumberFormatException e) {
			JOptionPane
					.showMessageDialog(
							_ventana,
							"!El paso de la Variacion de Parametros tiene que ser un n鷐ero real!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);

			return false;
		}

		return true;
	}

	/**
	 * Evalua la validez de los datos introducidos en el campo de texto de
	 * limite de variacion de parametros.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean limiteVariacionOk() {

		double limiteVariacion;

		try {
			if (_ventana.getTxtLimiteVariacion().getText().matches("")) {
				JOptionPane
						.showMessageDialog(
								_ventana,
								"!Debe introducir el Limite para la Variacion de Parametros!",
								"Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				limiteVariacion = Double.parseDouble(_ventana
						.getTxtLimiteVariacion().getText());

				if (limiteVariacion < 0) {
					JOptionPane
							.showMessageDialog(
									_ventana,
									"!El Limite de la Variacion de Parametros tiene que ser un numero positivo!",
									"Error en los datos",
									JOptionPane.ERROR_MESSAGE);
					return false;
				} else
					// Guardamos el resultado de la validaci贸n
					_limiteVariacion = limiteVariacion;
			}
		} catch (NumberFormatException e) {
			JOptionPane
					.showMessageDialog(
							_ventana,
							"!El Limite de la Variacion de Parametros tiene que ser un numero real!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);

			return false;
		}

		return true;
	}

	/**
	 * Comprueba que el Rango de la Variacion es correcto. Es correcto si el
	 * Limite es mayor que el inicio.
	 * 
	 * @param variacion
	 *            El tipo de Parametro a variar.
	 * @return Verdadero si el Rango de Variacion es correcto.
	 */
	public boolean VariacionOk(TipoVariacion variacion) {

		boolean varCorrecta = false;

		switch (variacion) {

		case NINGUNA:
			varCorrecta = true;
			break;
		case NUM_GENERACION:
			varCorrecta = _numGeneraciones < _limiteVariacion;
			break;
		case NUM_POBLACION:
			varCorrecta = _tamPoblacion < _limiteVariacion;
			break;
		case PROB_CRUCE:
			varCorrecta = _probCruce < _limiteVariacion;
			break;
		case PROB_MUTACION:
			varCorrecta = _probMutacion < _limiteVariacion;
			break;
		case ELITISMO:
			varCorrecta = _porcentajeElite < _limiteVariacion;
			break;
		}

		if (!varCorrecta) {
			JOptionPane.showMessageDialog(_ventana,
					"!El Rango de Variacion no es correcto!",
					"Error en los datos", JOptionPane.ERROR_MESSAGE);
		}

		return varCorrecta;
	}

	/**
	 * Evalua la validez de los datos introducidos en el campo de texto de
	 * numero de copias estimadas del mejor individuo para el escalado simple.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	private boolean NumEstimadoCopiasMejorOk() {

		int numEstimadoCopiasMejor;

		try {
			if (_ventana.getTxtNumEstimadoCopiasMejor().getText().matches("")) {
				JOptionPane
						.showMessageDialog(
								_ventana,
								"!Debe introducir el Numero Estimado de Copias del Mejor Individuo!",
								"Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				numEstimadoCopiasMejor = Integer.parseInt(_ventana
						.getTxtNumEstimadoCopiasMejor().getText());

				if (numEstimadoCopiasMejor < 0) {
					JOptionPane
							.showMessageDialog(
									_ventana,
									"!El Numero Estimado de Copias del Mejor Individuo tiene que ser un numero positivo!",
									"Error en los datos",
									JOptionPane.ERROR_MESSAGE);
					return false;
				} else
					// Guardamos el resultado de la validaci贸n
					_numEstimadoCopiasMejor = numEstimadoCopiasMejor;
			}
		} catch (NumberFormatException e) {
			JOptionPane
					.showMessageDialog(
							_ventana,
							"!El Numero Estimado de Copias del Mejor Individuo tiene que ser un numero entero!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);

			return false;
		}

		return true;
	}

	// ----------GETTERS & SETTERS-----------//

	/**
	 * Devuelve el numero de generaciones.
	 * 
	 * @return El numero de generaciones.
	 */
	public int getNumGeneraciones() {
		return _numGeneraciones;
	}

	/**
	 * Establece el numero de generaciones a valor numGeneraciones.
	 * 
	 * @param numGeneraciones Nuevo valor a establecer.
	 */
	public void setNumGeneraciones(int numGeneraciones) {
		_numGeneraciones = numGeneraciones;
	}

	/**
	 * Devuelve el tamanio de la poblacion.
	 *  
	 * @return El tamanio de la poblacion.
	 */
	public int getTamPoblacion() {
		return _tamPoblacion;
	}

	/**
	 * Establece el tamanio de la poblacion a valor tamPoblacion.
	 * 
	 * @param tamPoblacion Nuevo valor a establecer.
	 */
	public void setTamPoblacion(int tamPoblacion) {
		_tamPoblacion = tamPoblacion;
	}

	/**
	 * Devuelve la probabilidad de cruce.
	 * 
	 * @return La probabilidad de cruce.
	 */
	public double getProbCruce() {
		return _probCruce;
	}

	/**
	 * Establece la probabilidad de cruce a valor probCruce.
	 * 
	 * @param probCruce Nuevo valor a establecer.
	 */
	public void setProbCruce(double probCruce) {
		_probCruce = probCruce;
	}

	/**
	 * Devuelve la probabilidad de mutacion.
	 * 
	 * @return La probabilidad de mutacion.
	 */
	public double getProbMutacion() {
		return _probMutacion;
	}

	/**
	 * Establece la probabilidad de mutacion a valor probMutacion.
	 * 
	 * @param probMutacion Nuevo valor a establecer.
	 */
	public void setProbMutacion(double probMutacion) {
		_probMutacion = probMutacion;
	}

	/**
	 * Devuelve el porcentaje de la elite.
	 * 
	 * @return El porcentaje de la elite.
	 */
	public double getPorcentajeElite() {
		return _porcentajeElite;
	}

	/**
	 * Establece el porcentaje de elite a valor porcentajeElite.
	 * 
	 * @param porcentajeElite Nuevo valor a establecer.
	 */
	public void setPorcentajeElite(double porcentajeElite) {
		_porcentajeElite = porcentajeElite;
	}

	/**
	 * Devuelve el paso de la variacion.
	 * 
	 * @return El paso de la variacion.
	 */
	public double getPasoVariacion() {
		return _pasoVariacion;
	}

	/**
	 * Establece el paso de la variacion a valor pasoVariacion.
	 * 
	 * @param pasoVariacion Nuevo valor a establecer.
	 */
	public void setPasoVariacion(int pasoVariacion) {
		_pasoVariacion = pasoVariacion;
	}

	/**
	 * Devuelve el limite de la variacion.
	 * 
	 * @return El limite de la variacion.
	 */
	public double getLimiteVariacion() {
		return _limiteVariacion;
	}

	/**
	 * Establece el limite de la variacion a valor limiteVariacion.
	 * 
	 * @param limiteVariacion Nuevo valor a establecer.
	 */
	public void setLimiteVariacion(double limiteVariacion) {
		_limiteVariacion = limiteVariacion;
	}

	/**
	 * Devuelve el numero de copias estimadas del mejor individuo.
	 * 
	 * @return El numero de copias estimadas del mejor individuo.
	 */
	public int getNumEstimadoCopiasMejor() {
		return _numEstimadoCopiasMejor;
	}
}
