package logica;

import gui.Ventana;

public class Principal {

	private Ventana v = null;
	
	public Principal(Ventana v) {
		this.v = v;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ventana v = new Ventana();
		Principal p = new Principal(v);
	}

}
