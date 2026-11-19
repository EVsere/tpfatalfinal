package pantallas;

import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
public class Menusito extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	    public static void main(String[] args) {
	        EventQueue.invokeLater(() -> {
	            try {
	            	Menusito frame = new Menusito();
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }

	    public Menusito() {
	        setTitle("Sistema CensoCABA - Gestión Demográfica y Hábitat");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 950, 580);
	        setLocationRelativeTo(null);
	        
	        JMenuBar menuBar = new JMenuBar();
	        setJMenuBar(menuBar);

	        JMenu mnInicio = new JMenu("Inicio");
	        menuBar.add(mnInicio);
	        JMenuItem mntmPantallaInicio = new JMenuItem("Volver al Inicio");
	        mntmPantallaInicio.addActionListener(e -> cambiarPanel(new Inicio(this)));
	        mnInicio.add(mntmPantallaInicio);

	        JMenu mnComunas = new JMenu("Comunas");
	        menuBar.add(mnComunas);
	        JMenuItem mntmGestionComunas = new JMenuItem("Gestionar Comunas");
	        mntmGestionComunas.addActionListener(e -> cambiarPanel(new ComunaP()));
	        mnComunas.add(mntmGestionComunas);

	        JMenu mnBarrios = new JMenu("Barrios");
	        menuBar.add(mnBarrios);
	        JMenuItem mntmGestionBarrios = new JMenuItem("Gestionar Barrios");
	        mntmGestionBarrios.addActionListener(e -> cambiarPanel(new BarrioP()));
	        mnBarrios.add(mntmGestionBarrios);

	        JMenu mnPersonas = new JMenu("Personas");
	        menuBar.add(mnPersonas);
	        JMenuItem mntmGestionPersonas = new JMenuItem("Gestionar Residentes");
	        mntmGestionPersonas.addActionListener(e -> cambiarPanel(new ResidentesP()));
	        mnPersonas.add(mntmGestionPersonas);
	        
	        Font fuenteMenu = new Font("Tahoma", Font.PLAIN, 15);
	        mnInicio.setFont(fuenteMenu);
	        mnComunas.setFont(fuenteMenu);
	        mnBarrios.setFont(fuenteMenu);
	        mnPersonas.setFont(fuenteMenu);

	        contentPane = new JPanel();
	        contentPane.setLayout(new BorderLayout(0, 0));
	        setContentPane(contentPane);

	        
	        cambiarPanel(new Inicio(this));
	    }

	    public void cambiarPanel(JPanel nuevoPanel) {
	        contentPane.removeAll();
	        contentPane.add(nuevoPanel, BorderLayout.CENTER);
	        contentPane.revalidate();
	        contentPane.repaint();
	    }
}

