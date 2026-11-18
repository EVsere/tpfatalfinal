package pantallas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import javax.swing.BorderFactory;
	import javax.swing.DefaultComboBoxModel;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.JTextField;
	import javax.swing.table.DefaultTableModel;

	public class Comuna extends JPanel {

	    private static final long serialVersionUID = 1L;

	    private JTable tablaComunas;
	    private DefaultTableModel modeloTabla;

	    
	    private JComboBox<Integer> cmbNumeroComuna;
	    private JTextField txtSedeComunal;
	    private JTextField txtJuntaComunal;


	    private JButton btnGuardar;
	    private JButton btnActualizar;
	    private JButton btnLimpiar;
	    private JButton btnEliminar;

	    public Comuna() {
	        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	        setLayout(new GridLayout(1, 2, 20, 0));

	       
	        JPanel panelIzquierdo = new JPanel(new BorderLayout(0, 10));
	        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Listado de Comunas CABA"));
	        add(panelIzquierdo);

	        modeloTabla = new DefaultTableModel(
	            new Object[][] {},
	            new String[] {"N° Comuna", "Sede Comunal", "Junta Comunal"}
	        ) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };

	        tablaComunas = new JTable(modeloTabla);
	        tablaComunas.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                cargarComunaSeleccionada();
	            }
	        });

	        panelIzquierdo.add(new JScrollPane(tablaComunas), BorderLayout.CENTER);

	    
	        JPanel panelBotonesIzq = new JPanel(new GridLayout(1, 1, 0, 8));
	        btnEliminar = new JButton("Eliminar Comuna Seleccionada");
	        btnEliminar.addActionListener(e -> eliminarComuna());
	        panelBotonesIzq.add(btnEliminar);

	        panelIzquierdo.add(panelBotonesIzq, BorderLayout.SOUTH);

	       
	        JPanel panelDerecho = new JPanel();
	        panelDerecho.setBorder(BorderFactory.createTitledBorder("Alta / Modificación de Comuna"));
	        panelDerecho.setLayout(null);
	        add(panelDerecho);

	        JLabel lblNumero = new JLabel("Número de Comuna:");
	        lblNumero.setBounds(20, 45, 150, 25);
	        panelDerecho.add(lblNumero);

	        cmbNumeroComuna = new JComboBox<>();
	        DefaultComboBoxModel<Integer> modelComunas = new DefaultComboBoxModel<>();
	        for (int i = 1; i <= 15; i++) {
	            modelComunas.addElement(i);
	        }
	        cmbNumeroComuna.setModel(modelComunas);
	        cmbNumeroComuna.setBounds(175, 45, 200, 25);
	        panelDerecho.add(cmbNumeroComuna);

	        JLabel lblSede = new JLabel("Nombre de la Sede:");
	        lblSede.setBounds(20, 95, 150, 25);
	        panelDerecho.add(lblSede);

	        txtSedeComunal = new JTextField();
	        txtSedeComunal.setBounds(175, 95, 200, 25);
	        panelDerecho.add(txtSedeComunal);

	        JLabel lblJunta = new JLabel("Junta Comunal:");
	        lblJunta.setBounds(20, 145, 150, 25);
	        panelDerecho.add(lblJunta);

	        txtJuntaComunal = new JTextField();
	        txtJuntaComunal.setBounds(175, 145, 200, 25);
	        panelDerecho.add(txtJuntaComunal);

	     
	        btnGuardar = new JButton("Guardar (Alta)");
	        btnGuardar.setBounds(20, 220, 115, 30);
	        btnGuardar.addActionListener(e -> guardarComuna());
	        panelDerecho.add(btnGuardar);

	        btnActualizar = new JButton("Actualizar");
	        btnActualizar.setEnabled(false);
	        btnActualizar.setBounds(145, 220, 115, 30);
	        btnActualizar.addActionListener(e -> actualizarComuna());
	        panelDerecho.add(btnActualizar);

	        btnLimpiar = new JButton("Limpiar");
	        btnLimpiar.setBounds(270, 220, 105, 30);
	        btnLimpiar.addActionListener(e -> limpiarFormulario());
	        panelDerecho.add(btnLimpiar);
	        
	    }

	    private void cargarComunaSeleccionada() {
	        int fila = tablaComunas.getSelectedRow();
	        if (fila != -1) {
	            Integer numeroComuna = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
	            String sede = modeloTabla.getValueAt(fila, 1).toString();
	            String junta = modeloTabla.getValueAt(fila, 2).toString();

	            cmbNumeroComuna.setSelectedItem(numeroComuna);
	            cmbNumeroComuna.setEnabled(false); 
	            
	            txtSedeComunal.setText(sede);
	            txtJuntaComunal.setText(junta);

	            btnGuardar.setEnabled(false);
	            btnActualizar.setEnabled(true);
	        }
	    }

	    private void guardarComuna() {
	        try {
	            Integer numeroComuna = (Integer) cmbNumeroComuna.getSelectedItem();
	            String sede = txtSedeComunal.getText().trim();
	            String junta = txtJuntaComunal.getText().trim();

	            if (sede.isEmpty() || junta.isEmpty()) {
	                JOptionPane.showMessageDialog(this, "Complete el nombre de la sede y la junta comunal.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	                return;
	            }

	            // DAO para insertar en la base de datos
	            modeloTabla.addRow(new Object[]{numeroComuna, sede, junta});
	            JOptionPane.showMessageDialog(this, "Comuna registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            limpiarFormulario();

	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    private void actualizarComuna() {
	        int fila = tablaComunas.getSelectedRow();
	        if (fila != -1) {
	            try {
	                String sede = txtSedeComunal.getText().trim();
	                String junta = txtJuntaComunal.getText().trim();

	                if (sede.isEmpty() || junta.isEmpty()) {
	                    JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	                    return;
	                }

	                // DAO para hacer el UPDATE
	                modeloTabla.setValueAt(sede, fila, 1);
	                modeloTabla.setValueAt(junta, fila, 2);

	                JOptionPane.showMessageDialog(this, "Comuna actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                limpiarFormulario();

	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(this, "Ocurrió un error al actualizar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Seleccione una comuna de la tabla para actualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
	        }
	    }

	    private void eliminarComuna() {
	        int fila = tablaComunas.getSelectedRow();
	        if (fila != -1) {
	            int confirm = JOptionPane.showConfirmDialog(
	                this, 
	                "¿Está seguro de eliminar la comuna seleccionada?", 
	                "Confirmar baja", 
	                JOptionPane.YES_NO_OPTION
	            );
	            
	            if (confirm == JOptionPane.YES_OPTION) {
	                //  DAO para hacer el DELETE
	                modeloTabla.removeRow(fila);
	                JOptionPane.showMessageDialog(this, "Comuna eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                limpiarFormulario();
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Seleccione una comuna de la tabla para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
	        }
	    }

	    private void limpiarFormulario() {
	        tablaComunas.clearSelection();
	        cmbNumeroComuna.setEnabled(true);
	        cmbNumeroComuna.setSelectedIndex(0);
	        txtSedeComunal.setText("");
	        txtJuntaComunal.setText("");

	        btnGuardar.setEnabled(true);
	        btnActualizar.setEnabled(false);
	    }
}
