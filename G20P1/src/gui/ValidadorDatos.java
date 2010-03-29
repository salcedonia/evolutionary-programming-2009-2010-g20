package gui;

import javax.swing.JOptionPane;

/**
 * Clase que se encarga de validar y transformar los datos introducidos por el usuario en la 
 * ventana de la interfaz.
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
	 * Tamaño de la poblacion.
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
	 * Precision del algoritmo.
	 */
	private double _precision;
	
	/**
	 * Valor de N del algoritmo.
	 */
	private int _valorN;
	
	/**
	 * Porcentage de la �lite respecto a la poblaci�n.
	 */
	private double _porcentageElite;
	
	/**
	 * Paso al que avanza la variaci�n de par�metros.
	 */
	private double _pasoVariacion;
	
	/**
	 * L�mite para la variaci�n de par�metros.
	 */
	private double _limiteVariacion;
	
	/**
	 * Constructor de la clase Validador de Datos.
	 * 
	 * @param ventana Ventana grafica de la aplicacion.
	 */
	public ValidadorDatos (Ventana ventana){
		
		// Guardamos la ventana sobre la que realizamos la validacion.
		_ventana = ventana;
	}
	
	/**
	 * Comprueba que todos los valores introducidos por el usuario son validos
	 * para proceder a la evaluaciÛn de la funciÛn correspondiente.
	 * 
	 * @param variacion El tipo de par�metro a variar.
	 * @return Verdadero si todos los par·metros son v·lidos y falso en caso
	 *         contrario.
	 */
	public boolean parametrosOk(TipoVariacion variacion) {

		return numGeneracionesOk() && tamPoblacionOk() && probCruceOk() && probMutacionOk() 
				&& precisionOk() && valorNOk() && porcentageEliteOk() && pasoVariacionOk()
				&& limiteVariacionOk() && VariacionOk(variacion);
	}

	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de numero de Generaciones. Cuando
	 * ha comprobado que el resultado es optimo entonces guarda el valor en la variable entera correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean numGeneracionesOk() {
		
		int numGeneraciones;
		
		try{
			
			if(_ventana.getTxtNumGeneraciones().getText().matches("")){
				JOptionPane.showMessageDialog(_ventana, "!Debe introducir el Numero de Generaciones!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				numGeneraciones = Integer.parseInt(_ventana.getTxtNumGeneraciones().getText());
			
				if(numGeneraciones < 0){
					JOptionPane.showMessageDialog(_ventana, "!El Numero de Generaciones tiene que ser un numero entero positivo!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}else
					// Guardamos el resultado de la validación
					_numGeneraciones = numGeneraciones;
			}
		}catch(NumberFormatException e){
			
			JOptionPane.showMessageDialog(_ventana, "!Numero de Generaciones tiene que ser un numero entero!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de Tamaño de Poblacion. Cuando
	 * ha comprobado que el resultado es optimo entonces guarda el valor en la variable entera correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean tamPoblacionOk() {
		
		int tamPoblacion;
		
		try{
			
			if(_ventana.getTxtTamPoblacion().getText().matches("")){
				JOptionPane.showMessageDialog(_ventana, "!Debe introducir el Tamaño de la poblacion!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				tamPoblacion = Integer.parseInt(_ventana.getTxtTamPoblacion().getText());
			
				if(tamPoblacion < 0){
					JOptionPane.showMessageDialog(_ventana, "!El Tamaño de la Poblacion tiene que ser un numero entero positivo!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}else
					// Guardamos el resultado de la validación
					_tamPoblacion = tamPoblacion;
			}
		}catch(NumberFormatException e){
			
			JOptionPane.showMessageDialog(_ventana, "!El Tamaño de la Poblacion tiene que ser un numero entero!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de Probablidad de Cruce. Cuando
	 * ha comprobado que el resultado es optimo entonces guarda el valor en la variable real correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean probCruceOk() {
		
		double probCruce;
		
		try{
			
			if(_ventana.getTxtProbCruce().getText().matches("")){
				JOptionPane.showMessageDialog(_ventana, "!Debe introducir la Probabilidad de Cruce!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				probCruce = Double.parseDouble(_ventana.getTxtProbCruce().getText());
			
				if(probCruce >= 0 && probCruce <= 1){
					// Guardamos el resultado de la validación
					_probCruce = probCruce;
				}else{
					JOptionPane.showMessageDialog(_ventana, "!La Probabilidad de Cruce tiene que ser un numero entero positivo entre 0 y 1!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}catch(NumberFormatException e){
			
			JOptionPane.showMessageDialog(_ventana, "!La Probabilidad de Cruce tiene que ser un numero entero!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de Probablidad de Mutacion. Cuando
	 * ha comprobado que el resultado es optimo entonces guarda el valor en la variable real correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean probMutacionOk() {
		
		double probMutacion;
		
		try{
			
			if(_ventana.getTxtProbMutacion().getText().matches("")){
				JOptionPane.showMessageDialog(_ventana, "!Debe introducir la Probabilidad de Mutacion!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				probMutacion = Double.parseDouble(_ventana.getTxtProbMutacion().getText());
			
				if(probMutacion >= 0 && probMutacion <= 1){
					// Guardamos el resultado de la validación
					_probMutacion = probMutacion;
				}else{
					JOptionPane.showMessageDialog(_ventana, "!La Probabilidad de Mutacion tiene que ser un numero entero positivo entre 0 y 1!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}catch(NumberFormatException e){
			
			JOptionPane.showMessageDialog(_ventana, "!La Probabilidad de Mutacion tiene que ser un numero entero!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de Precision. Cuando
	 * ha comprobado que el resultado es optimo entonces guarda el valor en la variable real correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean precisionOk() {
		
		double precision;
		
		try{
			
			if(_ventana.getTxtPrecision().getText().matches("")){
				JOptionPane.showMessageDialog(_ventana, "!Debe introducir la Precision!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				precision = Double.parseDouble(_ventana.getTxtPrecision().getText());
			
				if(precision >= 0 && precision <= 1){
					// Guardamos el resultado de la validación
					_precision = precision;
				}else{
					JOptionPane.showMessageDialog(_ventana, "!La Precision tiene que ser un numero entero positivo entre 0 y 1!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}catch(NumberFormatException e){
			
			JOptionPane.showMessageDialog(_ventana, "!La Precision tiene que ser un numero entero!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el cuadro de texto de Valor de N. Cuando
	 * ha comprobado que el resultado es optimo entonces guarda el valor en la variable entera correspondiente.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean valorNOk() {
		
		int valorN;
		
		try{
			
			if(_ventana.getTxtValorN().getText().matches("")){
				JOptionPane.showMessageDialog(_ventana, "!Debe introducir el Valor de N!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				valorN = Integer.parseInt(_ventana.getTxtValorN().getText());
			
				if(valorN < 0){
					JOptionPane.showMessageDialog(_ventana, "!El Valor de N tiene que ser un numero entero positivo!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}else
					// Guardamos el resultado de la validación
					_valorN = valorN;
			}
		}catch(NumberFormatException e){
			
			JOptionPane.showMessageDialog(_ventana, "!El Valor de N tiene que ser un numero entero!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el campo de texto del
	 * porcentage de �lite.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean porcentageEliteOk() {
		
		double porcentageElite;
		
		try {
			if(_ventana.getTxtPorcentageElite().getText().matches("")){
				JOptionPane.showMessageDialog(_ventana, "�Debe introducir el % para el tama�o de la �lite!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				porcentageElite = Double.parseDouble(_ventana.getTxtPorcentageElite().getText());
			
				if ((porcentageElite < 0) || (porcentageElite > 1)) {
					JOptionPane.showMessageDialog(_ventana, "!El porcentage de la �lite tiene que ser un numero entre 0 y 1!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}else
					// Guardamos el resultado de la validación
					_porcentageElite = porcentageElite;
			}
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(_ventana, "!El % de la �lite tiene que ser un numero real!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el campo de texto de
	 * paso de variaci�n de par�metros.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean pasoVariacionOk() {
		
		double pasoVariacion;
		
		try {
			if(_ventana.getTxtPasoVariacion().getText().matches("")){
				JOptionPane.showMessageDialog(_ventana, "�Debe introducir el paso de la variaci�n de par�metros!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				pasoVariacion = Double.parseDouble(_ventana.getTxtPasoVariacion().getText());
			
				if (pasoVariacion <= 0) {
					JOptionPane.showMessageDialog(_ventana, "!El paso de la variaci�n tiene que ser un numero positivo!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}else
					// Guardamos el resultado de la validación
					_pasoVariacion = pasoVariacion;
			}
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(_ventana, "!El paso de la variaci�n de par�metros tiene que ser un n�mero real!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Evalua la validez de los datos introducidos en el campo de texto de
	 * l�mite de variaci�n de par�metros.
	 * 
	 * @return Verdadero si el dato introducido es correcto.
	 */
	public boolean limiteVariacionOk() {
		
		double limiteVariacion;
		
		try {
			if(_ventana.getTxtLimiteVariacion().getText().matches("")){
				JOptionPane.showMessageDialog(_ventana, "�Debe introducir el l�mite para la variaci�n de par�metros!",
	                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
				return false;
			}else{
				limiteVariacion = Double.parseDouble(_ventana.getTxtLimiteVariacion().getText());
			
				if (limiteVariacion < 0) {
					JOptionPane.showMessageDialog(_ventana, "!El l�mite de la variaci�n de par�metros tiene que ser un numero positivo!",
							"Error en los datos", JOptionPane.ERROR_MESSAGE);
					return false;
				}else
					// Guardamos el resultado de la validación
					_limiteVariacion = limiteVariacion;
			}
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(_ventana, "!El l�mite de la variaci�n de par�metros tiene que ser un numero real!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Comprueba que el rango de la variaci�n es correcto.
	 * Es correcto si el l�mite es mayor que el inicio.
	 * 
	 * @param variacion El tipo de par�metro a variar.
	 * @return Verdadero si el rango de variaci�n es correcto.
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
		case PRECISION:
			varCorrecta = _precision < _limiteVariacion;
			break;
		case VALOR_N:
			varCorrecta = _valorN < _limiteVariacion;
			break;
		case ELITISMO:
			varCorrecta = _porcentageElite < _limiteVariacion;
			break;
		}
		
		if (!varCorrecta) {
			JOptionPane.showMessageDialog(_ventana, "!El rango de variaci�n no es correcto!",
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
		}
		
		return varCorrecta;
		
	}

	public int getNumGeneraciones() {
		return _numGeneraciones;
	}

	public void setNumGeneraciones(int numGeneraciones) {
		_numGeneraciones = numGeneraciones;
	}

	public int getTamPoblacion() {
		return _tamPoblacion;
	}

	public void setTamPoblacion(int tamPoblacion) {
		_tamPoblacion = tamPoblacion;
	}

	public double getProbCruce() {
		return _probCruce;
	}

	public void setProbCruce(double probCruce) {
		_probCruce = probCruce;
	}

	public double getProbMutacion() {
		return _probMutacion;
	}

	public void setProbMutacion(double probMutacion) {
		_probMutacion = probMutacion;
	}

	public double getPrecision() {
		return _precision;
	}

	public void setPrecision(double precision) {
		_precision = precision;
	}

	public int getValorN() {
		return _valorN;
	}

	public void setValorN(int valorN) {
		_valorN = valorN;
	}
	
	public double getPorcentageElite() {
		return _porcentageElite;
	}

	public void setPorcentageElite(double porcentageElite) {
		_porcentageElite = porcentageElite;
	}
	
	public double getPasoVariacion() {
		return _pasoVariacion;
	}

	public void setPasoVariacion(int pasoVariacion) {
		_pasoVariacion = pasoVariacion;
	}
	
	public double getLimiteVariacion() {
		return _limiteVariacion;
	}

	public void setLimiteVariacion(double limiteVariacion) {
		_limiteVariacion = limiteVariacion;
	}
}
