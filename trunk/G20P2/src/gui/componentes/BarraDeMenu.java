package gui.componentes;

import gui.Ventana;
import gui.tipos.TipoVista;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Clase que gestiona la barra de menus de la aplicacion.
 * 
 * @author Grupo20.
 */
public class BarraDeMenu extends JMenuBar{

	/**
	 * Identificador de la clase.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Menu de vistas disponibles
	 */
	private JMenu _menuVista;
	/**
	 * Submenu para la vista de la practica 1.
	 */
	private JMenuItem _subMenuPractica1;
	/**
	 * Submenu para la vista de la practica 2.
	 */
	private JMenuItem _subMenuPractica2;
	
	/**
	 * Ventana grafica de la aplicacion.
	 */
	private Ventana _ventana;
	
	/**
	 * Constructor de la clase BarraDeMenu.
	 * 
	 * @param ventana Ventana grafica de la aplicacion.
	 */
	public BarraDeMenu(final Ventana ventana){
		
		_ventana = ventana;
		
		_menuVista = new JMenu("Vista");
		_subMenuPractica1 = new JMenuItem("Practica 1");
		_subMenuPractica1
				.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(java.awt.event.ActionEvent e) {

						_ventana.setTipoVista(TipoVista.PRACTICA1);
						_ventana.iniciaInterfaz();
					}
				});

		_subMenuPractica2 = new JMenuItem("Practica 2");
		_subMenuPractica2
				.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(java.awt.event.ActionEvent e) {

						_ventana.setTipoVista(TipoVista.PRACTICA2);
						_ventana.iniciaInterfaz();
					}
				});

		_menuVista.add(_subMenuPractica1);
		_menuVista.add(_subMenuPractica2);
		add(_menuVista);
		ventana.setJMenuBar(this);
	}
}
