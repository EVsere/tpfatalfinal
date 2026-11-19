package pantallas;


	import java.awt.Font;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.Insets;
	import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.JPanel;

	public class Inicio extends JPanel {

	    private static final long serialVersionUID = 1L;

	    public Inicio(Menusito framePrincipal) {
	        GridBagLayout gridBagLayout = new GridBagLayout();
	        gridBagLayout.columnWidths = new int[]{0, 0};
	        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
	        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	        gridBagLayout.rowWeights = new double[]{.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	        setLayout(gridBagLayout);

	        JLabel lblTitulo = new JLabel("SISTEMA CENSOCABA");
	        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
	        GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
	        gbc_lblTitulo.insets = new Insets(40, 0, 10, 0);
	        gbc_lblTitulo.gridx = 0;
	        gbc_lblTitulo.gridy = 0;
	        add(lblTitulo, gbc_lblTitulo);

	        JLabel lblSubtitulo = new JLabel("Gestión");
	        lblSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        GridBagConstraints gbc_lblSubtitulo = new GridBagConstraints();
	        gbc_lblSubtitulo.insets = new Insets(0, 0, 35, 0);
	        gbc_lblSubtitulo.gridx = 0;
	        gbc_lblSubtitulo.gridy = 1;
	        add(lblSubtitulo, gbc_lblSubtitulo);

	        JButton btnComunas = new JButton("Gestionar Comunas");
	        btnComunas.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        btnComunas.addActionListener(e -> framePrincipal.cambiarPanel(new ComunaP()));
	        GridBagConstraints gbc_btnComunas = new GridBagConstraints();
	        gbc_btnComunas.ipadx = 60;
	        gbc_btnComunas.ipady = 12;
	        gbc_btnComunas.insets = new Insets(0, 0, 15, 0);
	        gbc_btnComunas.gridx = 0;
	        gbc_btnComunas.gridy = 2;
	        add(btnComunas, gbc_btnComunas);

	        JButton btnBarrio = new JButton("Gestionar Barrios");
	        btnBarrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        btnBarrio.addActionListener(e -> framePrincipal.cambiarPanel(new BarrioP()));
	        GridBagConstraints gbc_btnBarrio = new GridBagConstraints();
	        gbc_btnBarrio.ipadx = 75;
	        gbc_btnBarrio.ipady = 12;
	        gbc_btnBarrio.insets = new Insets(0,0,15,0);
	        gbc_btnBarrio.gridx = 0;
	        gbc_btnBarrio.gridy = 3;
	        add(btnBarrio, gbc_btnBarrio);
	        
	        JButton btnPersonas = new JButton("Gestionar Residentes (Personas)");
	        btnPersonas.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        btnPersonas.addActionListener(e -> framePrincipal.cambiarPanel(new ResidentesP()));
	        GridBagConstraints gbc_btnPersonas = new GridBagConstraints();
	        gbc_btnPersonas.insets = new Insets(0, 0, 5, 0);
	        gbc_btnPersonas.ipadx = 20;
	        gbc_btnPersonas.ipady = 12;
	        gbc_btnPersonas.gridx = 0;
	        gbc_btnPersonas.gridy = 4;
	        add(btnPersonas, gbc_btnPersonas);
	        
	       
	    }

}
