package interfaz;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ResidenteDelete extends JPanel {

    private static final long serialVersionUID = 1L;

    // Opciones para la situación laboral exigidas por el TP
    private static final String SIT_OCUPADO = "Ocupado";
    private static final String SIT_DESOCUPADO = "Desocupado";
    private static final String SIT_SUBOCUPADO = "Subocupado";

    private JTable tablaResidentes;
    private DefaultTableModel modeloTabla;

    // Campos del formulario
    private JTextField txtDni;
    private JTextField txtNombreCompleto;
    private JTextField txtFechaNacimiento; // Formato YYYY-MM-DD (para LocalDate)
    private JComboBox<String> cmbSituacionLaboral;
    private JComboBox<String> cmbBarrioAsociado;

    // Botones
    private JButton btnGuardar;
    private JButton btnActualizar;
    private JButton btnLimpiar;
    private JButton btnEliminar;

    public ResidenteDelete() {
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setLayout(new GridLayout(1, 2, 20, 0));

        // =========================================================================
        // PANEL IZQUIERDO: LISTADO Y BAJA DE RESIDENTES
        // =========================================================================
        JPanel panelIzquierdo = new JPanel(new BorderLayout(0, 10));
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Listado de Residentes de CABA"));
        add(panelIzquierdo);

        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] {"DNI", "Nombre Completo", "F. Nacimiento", "Sit. Laboral", "Barrio"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaResidentes = new JTable(modeloTabla);
        tablaResidentes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarResidenteSeleccionado();
            }
        });

        panelIzquierdo.add(new JScrollPane(tablaResidentes), BorderLayout.CENTER);

        // Botón inferior izquierdo para eliminar
        JPanel panelBotonesIzq = new JPanel(new GridLayout(1, 1, 0, 8));
        btnEliminar = new JButton("Eliminar Residente Seleccionado");
        btnEliminar.addActionListener(e -> eliminarResidente());
        panelBotonesIzq.add(btnEliminar);

        panelIzquierdo.add(panelBotonesIzq, BorderLayout.SOUTH);

        // =========================================================================
        // PANEL DERECHO: ALTA Y MODIFICACIÓN UNIFICADA
        // =========================================================================
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBorder(BorderFactory.createTitledBorder("Alta / Modificación de Residente"));
        panelDerecho.setLayout(null);
        add(panelDerecho);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(20, 35, 150, 25);
        panelDerecho.add(lblDni);

        txtDni = new JTextField();
        txtDni.setBounds(175, 35, 200, 25);
        panelDerecho.add(txtDni);

        JLabel lblNombre = new JLabel("Nombre Completo:");
        lblNombre.setBounds(20, 75, 150, 25);
        panelDerecho.add(lblNombre);

        txtNombreCompleto = new JTextField();
        txtNombreCompleto.setBounds(175, 75, 200, 25);
        panelDerecho.add(txtNombreCompleto);

        JLabel lblFechaNac = new JLabel("F. Nacimiento (YYYY-MM-DD):");
        lblFechaNac.setBounds(20, 115, 150, 25);
        panelDerecho.add(lblFechaNac);

        txtFechaNacimiento = new JTextField();
        txtFechaNacimiento.setBounds(175, 115, 200, 25);
        panelDerecho.add(txtFechaNacimiento);

        JLabel lblSitLab = new JLabel("Situación Laboral:");
        lblSitLab.setBounds(20, 155, 150, 25);
        panelDerecho.add(lblSitLab);

        cmbSituacionLaboral = new JComboBox<>();
        cmbSituacionLaboral.setModel(new DefaultComboBoxModel<>(
            new String[] {SIT_OCUPADO, SIT_DESOCUPADO, SIT_SUBOCUPADO}
        ));
        cmbSituacionLaboral.setBounds(175, 155, 200, 25);
        panelDerecho.add(cmbSituacionLaboral);

        JLabel lblBarrio = new JLabel("Barrio Asociado:");
        lblBarrio.setBounds(20, 195, 150, 25);
        panelDerecho.add(lblBarrio);

        cmbBarrioAsociado = new JComboBox<>();
        // Carga de ejemplo de barrios (en tu proyecto real se cargan desde la BD por JDBC)
        cmbBarrioAsociado.setModel(new DefaultComboBoxModel<>(
            new String[] {"Palermo", "Villa 31", "Lugano I y II", "Recoleta", "Belgrano"}
        ));
        cmbBarrioAsociado.setBounds(175, 195, 200, 25);
        panelDerecho.add(cmbBarrioAsociado);

        // --- BOTONES DE ACCIÓN ---
        btnGuardar = new JButton("Guardar (Alta)");
        btnGuardar.setBounds(20, 260, 115, 30);
        btnGuardar.addActionListener(e -> guardarResidente());
        panelDerecho.add(btnGuardar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setEnabled(false);
        btnActualizar.setBounds(145, 260, 115, 30);
        btnActualizar.addActionListener(e -> actualizarResidente());
        panelDerecho.add(btnActualizar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(270, 260, 105, 30);
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        panelDerecho.add(btnLimpiar);
        
  
    }

    private void cargarResidenteSeleccionado() {
        int fila = tablaResidentes.getSelectedRow();
        if (fila != -1) {
            String dni = modeloTabla.getValueAt(fila, 0).toString();
            String nombre = modeloTabla.getValueAt(fila, 1).toString();
            String fechaNac = modeloTabla.getValueAt(fila, 2).toString();
            String sitLab = modeloTabla.getValueAt(fila, 3).toString();
            String barrio = modeloTabla.getValueAt(fila, 4).toString();

            txtDni.setText(dni);
            txtDni.setEnabled(false); // Bloqueado: preserva la identidad natural (DNI único)
            
            txtNombreCompleto.setText(nombre);
            txtFechaNacimiento.setText(fechaNac);
            cmbSituacionLaboral.setSelectedItem(sitLab);
            cmbBarrioAsociado.setSelectedItem(barrio);

            btnGuardar.setEnabled(false);
            btnActualizar.setEnabled(true);
        }
    }

    private void guardarResidente() {
        try {
            String dni = txtDni.getText().trim();
            String nombre = txtNombreCompleto.getText().trim();
            String fechaNac = txtFechaNacimiento.getText().trim();
            String sitLab = (String) cmbSituacionLaboral.getSelectedItem();
            String barrio = (String) cmbBarrioAsociado.getSelectedItem();

            if (dni.isEmpty() || nombre.isEmpty() || fechaNac.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validación opcional rápida para el formato de fecha LocalDate (YYYY-MM-DD)
            java.time.LocalDate.parse(fechaNac);

            // Aquí invocarías tu DAO/Servicio JDBC para insertar el residente en MySQL
            modeloTabla.addRow(new Object[]{dni, nombre, fechaNac, sitLab, barrio});
            JOptionPane.showMessageDialog(this, "Residente registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();

        } catch (java.time.format.DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "El formato de la fecha de nacimiento debe ser YYYY-MM-DD (ej: 2005-08-15).", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarResidente() {
        int fila = tablaResidentes.getSelectedRow();
        if (fila != -1) {
            try {
                String nombre = txtNombreCompleto.getText().trim();
                String fechaNac = txtFechaNacimiento.getText().trim();
                String sitLab = (String) cmbSituacionLaboral.getSelectedItem();
                String barrio = (String) cmbBarrioAsociado.getSelectedItem();

                if (nombre.isEmpty() || fechaNac.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                java.time.LocalDate.parse(fechaNac);

                // Aquí invocarías tu DAO/Servicio JDBC para hacer el UPDATE usando el DNI como identidad
                modeloTabla.setValueAt(nombre, fila, 1);
                modeloTabla.setValueAt(fechaNac, fila, 2);
                modeloTabla.setValueAt(sitLab, fila, 3);
                modeloTabla.setValueAt(barrio, fila, 4);

                JOptionPane.showMessageDialog(this, "Residente actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();

            } catch (java.time.format.DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "El formato de la fecha de nacimiento debe ser YYYY-MM-DD (ej: 2005-08-15).", "Error de formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al actualizar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un residente de la tabla para actualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarResidente() {
        int fila = tablaResidentes.getSelectedRow();
        if (fila != -1) {
            int confirm = JOptionPane.showConfirmDialog(
                this, 
                "¿Está seguro de dar de baja al residente seleccionado?", 
                "Confirmar baja", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                // Aquí invocarías tu DAO/Servicio JDBC para hacer el DELETE
                modeloTabla.removeRow(fila);
                JOptionPane.showMessageDialog(this, "Residente eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un residente de la tabla para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        tablaResidentes.clearSelection();
        txtDni.setEnabled(true);
        txtDni.setText("");
        txtNombreCompleto.setText("");
        txtFechaNacimiento.setText("");
        cmbSituacionLaboral.setSelectedIndex(0);
        cmbBarrioAsociado.setSelectedIndex(0);

        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(false);
    }

    
}