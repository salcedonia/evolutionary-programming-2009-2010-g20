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

import logica.Principal;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int ALTO = 640;
	private static final int ANCHO = 800;
	
	private static final String ICONO = "AGSIcono.gif";
	
	/* Controlador */
	private Principal controlador = null;
	
	/* Barra de Menú */
	private JMenuBar barraMenu = null;
	private JMenu funcionesMenu = null;
	private JMenuItem fMenuItem[] = null;
	
	/* Panel Principal */
	private JPanel panelPrincipal = null;
	private JPanel panelOpciones = null;
	
	public Ventana() {
		
		// Set de las características de la ventana
		this.setTitle("Práctica 1: AGS");
		this.setIconImage(new ImageIcon(ICONO).getImage());
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(10, 50, ANCHO, ALTO);
		this.setLocationRelativeTo(null);
		
		// Inicialización de los elementos de la ventana
		iniciaInterfaz();
		this.setEnabled(true);
		this.setVisible(true);
	}
	
	public void setControlador(Principal p) {
		controlador = p;
	}
	
	private void iniciaInterfaz() {
		this.setJMenuBar(getMenuPrincipal());
		this.setContentPane(getPanelPrincipal());
	}
	
	private JMenuBar getMenuPrincipal() {
		if (barraMenu == null) {
			barraMenu = new JMenuBar();
			barraMenu.add(getMenuFunciones());
		}
		return barraMenu;
	}
	
	//---------------------------------------------------------------------------
	private JMenu getMenuFunciones() {
		if (funcionesMenu == null) {
			funcionesMenu = new JMenu();
			funcionesMenu.setText("Funciones");
			fMenuItem = new JMenuItem[5];
			for (int i = 0; i < 5; i++) 
				funcionesMenu.add(getFItem(i));
		}
		return funcionesMenu;
	}
	
	//---------------------------------------------------------------------------
	private JMenuItem getFItem(int numFuncion) {
		if (fMenuItem[numFuncion] == null) {
			fMenuItem[numFuncion] = new JMenuItem();
			fMenuItem[numFuncion].setText("Función "+String.valueOf(numFuncion+1));
			fMenuItem[numFuncion].setEnabled(true);
			fMenuItem[numFuncion].addActionListener( 
					new	java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							// Acción asociada a pulsar Función 1
							// Controlador tiene actual a función 1
						}
					}
				);
		
		}
		return fMenuItem[numFuncion];
	}
	
	//---------------------------------------------------------------------------
	private JPanel getPanelPrincipal() {
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 0.7;
		constraints.weightx = 0.6;
		
		panelPrincipal.add(getPanelOpciones(),constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 0.7;
		constraints.weightx = 1.0;
		panelPrincipal.add(new JTextField(),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 0.3;
		constraints.weightx = 1.0;
		panelPrincipal.add(new JTextField(),constraints);
		
		return panelPrincipal;
	}
	
	private JPanel getPanelOpciones() {
		panelOpciones = new JPanel();
		panelOpciones.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 0.1;
		constraints.weightx = 1.0;
		
		panelOpciones.add(getCabeceraOpciones(),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 1.0;
		constraints.weightx = 1.0;
		panelOpciones.add(getBodyOpciones(),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		constraints.weighty = 0.1;
		constraints.weightx = 1.0;
		panelOpciones.add(getBotonesOpciones(),constraints);
		
		return panelOpciones;
	}
	
	private JPanel getCabeceraOpciones() {
		JPanel panelCabeceraOpciones = new JPanel();
		panelCabeceraOpciones.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		// Label título función
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		constraints.weighty = 0.0;
		
		panelOpciones.add(new JLabel("Función"), constraints);
		
		// Label con la función concreta
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		constraints.weighty = 0.0;
		
		panelOpciones.add(new JLabel("Aquí va la función."),constraints);
	
		return panelCabeceraOpciones;
	}
	
	private JPanel getBodyOpciones() {
		JPanel panelBodyOpciones = new JPanel();
		panelBodyOpciones.setLayout(new GridBagLayout());
		
		// Labels
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		
		panelBodyOpciones.add(new JLabel("Número de generaciones:"),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JLabel("Tamaño de la población:"),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JLabel("Probabilidad de cruce:"),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JLabel("Probabilidad de mutación:"),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JLabel("Precisión:"),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JLabel("Valor de n:"),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JLabel("Selección por elitismo:"),constraints);
		
		// JTextFields
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JTextField(),constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JTextField(),constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JTextField(),constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JTextField(),constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JTextField(),constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JTextField(),constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.fill = GridBagConstraints.NONE;
		panelBodyOpciones.add(new JTextField(),constraints);
		
		// Borde del panel
		panelBodyOpciones.setBorder( new CompoundBorder(
        		BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
        		new EmptyBorder(0,10,10,10) ) );
		return panelBodyOpciones;
	}
	
	private JPanel getBotonesOpciones() {
		JPanel panelBotonesOpciones = new JPanel();
		panelBotonesOpciones.setLayout(new GridBagLayout());
		
		// Labels
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		panelBotonesOpciones.add(new JButton("Comenzar"),constraints);
		return panelBotonesOpciones;
	}
	
}

