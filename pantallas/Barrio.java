package pantallas;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Barrio extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final String TIPO_FORMAL = "Trazado Formal";
    private static final String TIPO_PLANIFICADA = "Urbanización Planificada";
    private static final String TIPO_COMPLEJO = "Complejo Habitacional";
    private JTable tablaBarrios;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre;
    private JTextField txtSuperficie;
    private JComboBox<Integer> cmbComuna;
    private JComboBox<String> cmbTipoBarrio;
    private JPanel panelDinamico;
    private CardLayout cardLayout;
    private JTextField txtEdificiosPH;
    private JTextField txtPorcentajeAsfalto;
    private JTextField txtNumeroLey;
    private JCheckBox chkAguaPotable;
    private JTextField txtMonoblocks;
    private JCheckBox chkConsorcioActivo;
    private JButton btnGuardar;
    private JButton btnActualizar;
    private JButton btnLimpiar;
    private JButton btnEliminar;
    private JButton btnVerCriticidad;

    public Barrio() {
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setLayout(new GridLayout(1, 2, 20, 0));

    
        JPanel panelIzquierdo = new JPanel(new BorderLayout(0, 10));
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Listado de Barrios de CABA"));
        add(panelIzquierdo);

        modeloTabla = new DefaultTableModel(
        		new Object[][] {},
        		new String[] {"Nombre", "Comuna", "Tipo", "Superficie", "Dato 1", "Dato 2"} 
        		) 
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaBarrios = new JTable(modeloTabla);
        tablaBarrios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarBarrioSeleccionado();
            }
        });

        panelIzquierdo.add(new JScrollPane(tablaBarrios), BorderLayout.CENTER);

        JPanel panelBotonesIzq = new JPanel(new GridLayout(2, 1, 0, 8));
        btnVerCriticidad = new JButton("Evaluar Criticidad y Densidad");
        btnVerCriticidad.addActionListener(e -> evaluarCriticidadSeleccionado());
        panelBotonesIzq.add(btnVerCriticidad);

        btnEliminar = new JButton("Eliminar Barrio Seleccionado");
        btnEliminar.addActionListener(e -> eliminarBarrio());
        panelBotonesIzq.add(btnEliminar);

        panelIzquierdo.add(panelBotonesIzq, BorderLayout.SOUTH);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setBorder(BorderFactory.createTitledBorder("Alta / Modificación de Barrio"));
        panelDerecho.setLayout(null);
        add(panelDerecho);

        JLabel lblNombre = new JLabel("Nombre del Barrio:");
        lblNombre.setBounds(20, 35, 150, 25);
        panelDerecho.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(175, 35, 220, 25);
        panelDerecho.add(txtNombre);

        JLabel lblSuperficie = new JLabel("Superficie (ha):");
        lblSuperficie.setBounds(20, 75, 150, 25);
        panelDerecho.add(lblSuperficie);

        txtSuperficie = new JTextField();
        txtSuperficie.setBounds(175, 75, 220, 25);
        panelDerecho.add(txtSuperficie);

        JLabel lblComuna = new JLabel("Comuna Asociada:");
        lblComuna.setBounds(20, 115, 150, 25);
        panelDerecho.add(lblComuna);

        cmbComuna = new JComboBox<>();
        DefaultComboBoxModel<Integer> modelComunas = new DefaultComboBoxModel<>();
        for (int i = 1; i <= 15; i++) {
            modelComunas.addElement(i);
        }
        cmbComuna.setModel(modelComunas);
        cmbComuna.setBounds(175, 115, 220, 25);
        panelDerecho.add(cmbComuna);

        JLabel lblTipo = new JLabel("Tipo de Barrio:");
        lblTipo.setBounds(20, 155, 150, 25);
        panelDerecho.add(lblTipo);

        cmbTipoBarrio = new JComboBox<>();
        cmbTipoBarrio.setModel(new DefaultComboBoxModel<>(
            new String[] {TIPO_FORMAL, TIPO_PLANIFICADA, TIPO_COMPLEJO}
        ));
        cmbTipoBarrio.setBounds(175, 155, 220, 25);
        cmbTipoBarrio.addActionListener(e -> cambiarFormularioEspecifico());
        panelDerecho.add(cmbTipoBarrio);

        // --- CONTENEDOR DINÁMICO (CARDLAYOUT) ---
        cardLayout = new CardLayout();
        panelDinamico = new JPanel(cardLayout);
        panelDinamico.setBorder(BorderFactory.createTitledBorder("Datos Específicos según Categoría"));
        panelDinamico.setBounds(20, 195, 375, 120);
        panelDerecho.add(panelDinamico);

        // 1. Subpanel Trazado Formal
        JPanel panelFormal = new JPanel(null);
        JLabel lblEdificios = new JLabel("Cant. Edificios PH:");
        lblEdificios.setBounds(15, 25, 150, 25);
        panelFormal.add(lblEdificios);

        txtEdificiosPH = new JTextField();
        txtEdificiosPH.setBounds(175, 25, 180, 25);
        panelFormal.add(txtEdificiosPH);

        JLabel lblAsfalto = new JLabel("% Asfalto en Calles:");
        lblAsfalto.setBounds(15, 65, 150, 25);
        panelFormal.add(lblAsfalto);

        txtPorcentajeAsfalto = new JTextField();
        txtPorcentajeAsfalto.setBounds(175, 65, 180, 25);
        panelFormal.add(txtPorcentajeAsfalto);
        panelDinamico.add(panelFormal, TIPO_FORMAL);

        // 2. Subpanel Urbanización Planificada
        JPanel panelPlanificada = new JPanel(null);
        JLabel lblLey = new JLabel("N° Ley Integración:");
        lblLey.setBounds(15, 25, 150, 25);
        panelPlanificada.add(lblLey);

        txtNumeroLey = new JTextField();
        txtNumeroLey.setBounds(175, 25, 180, 25);
        panelPlanificada.add(txtNumeroLey);

        chkAguaPotable = new JCheckBox("Conexión formal a red de agua potable");
        chkAguaPotable.setBounds(15, 65, 340, 25);
        panelPlanificada.add(chkAguaPotable);
        panelDinamico.add(panelPlanificada, TIPO_PLANIFICADA);

        // 3. Subpanel Complejo Habitacional
        JPanel panelComplejo = new JPanel(null);
        JLabel lblMonoblocks = new JLabel("Cant. Monoblocks:");
        lblMonoblocks.setBounds(15, 25, 150, 25);
        panelComplejo.add(lblMonoblocks);

        txtMonoblocks = new JTextField();
        txtMonoblocks.setBounds(175, 25, 180, 25);
        panelComplejo.add(txtMonoblocks);

        chkConsorcioActivo = new JCheckBox("Consorcio de administración macro activo");
        chkConsorcioActivo.setBounds(15, 65, 340, 25);
        panelComplejo.add(chkConsorcioActivo);
        panelDinamico.add(panelComplejo, TIPO_COMPLEJO);

        // --- BOTONES DE ACCIÓN ---
        btnGuardar = new JButton("Guardar (Alta)");
        btnGuardar.setBounds(20, 335, 120, 30);
        btnGuardar.addActionListener(e -> guardarBarrio());
        panelDerecho.add(btnGuardar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setEnabled(false);
        btnActualizar.setBounds(150, 335, 115, 30);
        btnActualizar.addActionListener(e -> actualizarBarrio());
        panelDerecho.add(btnActualizar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(275, 335, 120, 30);
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        panelDerecho.add(btnLimpiar);
    }

    private void cambiarFormularioEspecifico() {
        String tipoSeleccionado = (String) cmbTipoBarrio.getSelectedItem();
        cardLayout.show(panelDinamico, tipoSeleccionado);
    }

    private void cargarBarrioSeleccionado() {
        int fila = tablaBarrios.getSelectedRow();
        if (fila != -1) {
            String nombre = modeloTabla.getValueAt(fila, 0).toString();
            Integer comuna = Integer.parseInt(modeloTabla.getValueAt(fila, 1).toString());
            String tipo = modeloTabla.getValueAt(fila, 2).toString();
            String superficie = modeloTabla.getValueAt(fila, 3).toString();
            Object dato1 = modeloTabla.getValueAt(fila, 4);
            Object dato2 = modeloTabla.getValueAt(fila, 5);

            txtNombre.setText(nombre);
            txtNombre.setEnabled(false); 
            txtSuperficie.setText(superficie);
            cmbComuna.setSelectedItem(comuna);

            cmbTipoBarrio.setSelectedItem(tipo);
            cmbTipoBarrio.setEnabled(false); 
            cambiarFormularioEspecifico();

            if (TIPO_FORMAL.equals(tipo)) {
                txtEdificiosPH.setText(dato1.toString());
                txtPorcentajeAsfalto.setText(dato2.toString());
            } else if (TIPO_PLANIFICADA.equals(tipo)) {
                txtNumeroLey.setText(dato1.toString());
                chkAguaPotable.setSelected(Boolean.parseBoolean(dato2.toString()));
            } else if (TIPO_COMPLEJO.equals(tipo)) {
                txtMonoblocks.setText(dato1.toString());
                chkConsorcioActivo.setSelected(Boolean.parseBoolean(dato2.toString()));
            }

            btnGuardar.setEnabled(false);
            btnActualizar.setEnabled(true);
        }
    }

    private void guardarBarrio() {
        try {
            String nombre = txtNombre.getText().trim();
            double superficie = Double.parseDouble(txtSuperficie.getText().trim());
            Integer comuna = (Integer) cmbComuna.getSelectedItem();
            String tipo = (String) cmbTipoBarrio.getSelectedItem();

            if (nombre.isEmpty() || superficie <= 0) {
                JOptionPane.showMessageDialog(this, "Complete nombre y superficie válida.");
                return;
            }

            Object dato1 = null;
            Object dato2 = null;

            if (TIPO_FORMAL.equals(tipo)) {
                dato1 = Integer.parseInt(txtEdificiosPH.getText().trim());
                dato2 = Double.parseDouble(txtPorcentajeAsfalto.getText().trim());
            } else if (TIPO_PLANIFICADA.equals(tipo)) {
                dato1 = txtNumeroLey.getText().trim();
                dato2 = chkAguaPotable.isSelected();
            } else if (TIPO_COMPLEJO.equals(tipo)) {
                dato1 = Integer.parseInt(txtMonoblocks.getText().trim());
                dato2 = chkConsorcioActivo.isSelected();
            }

            // DAO para insertar el Barrio y asociarlo a la Comuna
            modeloTabla.addRow(new Object[]{nombre, comuna, tipo, superficie, dato1, dato2});
            limpiarFormulario();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Verifique que los valores numéricos sean correctos.");
        }
    }

    private void actualizarBarrio() {
        int fila = tablaBarrios.getSelectedRow();
        if (fila != -1) {
            try {
                double superficie = Double.parseDouble(txtSuperficie.getText().trim());
                Integer comuna = (Integer) cmbComuna.getSelectedItem();
                String tipo = (String) cmbTipoBarrio.getSelectedItem();

                Object dato1 = null;
                Object dato2 = null;

                if (TIPO_FORMAL.equals(tipo)) {
                    dato1 = Integer.parseInt(txtEdificiosPH.getText().trim());
                    dato2 = Double.parseDouble(txtPorcentajeAsfalto.getText().trim());
                } else if (TIPO_PLANIFICADA.equals(tipo)) {
                    dato1 = txtNumeroLey.getText().trim();
                    dato2 = chkAguaPotable.isSelected();
                } else if (TIPO_COMPLEJO.equals(tipo)) {
                    dato1 = Integer.parseInt(txtMonoblocks.getText().trim());
                    dato2 = chkConsorcioActivo.isSelected();
                }

                //  DAO  para hacer UPDATE
                modeloTabla.setValueAt(comuna, fila, 1);
                modeloTabla.setValueAt(superficie, fila, 3);
                modeloTabla.setValueAt(dato1, fila, 4);
                modeloTabla.setValueAt(dato2, fila, 5);

                limpiarFormulario();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Verifique que los valores numéricos sean correctos.");
            }
        }
    }

    private void eliminarBarrio() {
        int fila = tablaBarrios.getSelectedRow();
        if (fila != -1) {
            // DAO para DELETE
            modeloTabla.removeRow(fila);
            limpiarFormulario();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un barrio de la tabla.");
        }
    }

    private void evaluarCriticidadSeleccionado() {
        int fila = tablaBarrios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un barrio para evaluar su criticidad.");
            return;
        }

        String nombre = modeloTabla.getValueAt(fila, 0).toString();
        String tipo = modeloTabla.getValueAt(fila, 2).toString();
        Object dato2 = modeloTabla.getValueAt(fila, 5);

        boolean critico = false;
        if (TIPO_FORMAL.equals(tipo)) {
            critico = Double.parseDouble(dato2.toString()) < 95.0;
        } else if (TIPO_PLANIFICADA.equals(tipo)) {
            critico = !Boolean.parseBoolean(dato2.toString());
        } else if (TIPO_COMPLEJO.equals(tipo)) {
            critico = !Boolean.parseBoolean(dato2.toString());
        }

        String estado = critico ? "CRITICIDAD HABITACIONAL ACTIVA" : "SIN CRITICIDAD";
        JOptionPane.showMessageDialog(
            this,
            "Barrio: " + nombre + "\nCategoría: " + tipo + "\nEstado: " + estado,
            "Evaluación de Hábitat",
            critico ? JOptionPane.WARNING_MESSAGE : JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void limpiarFormulario() {
        tablaBarrios.clearSelection();
        txtNombre.setEnabled(true);
        txtNombre.setText("");
        txtSuperficie.setText("");
        cmbComuna.setSelectedIndex(0);

        cmbTipoBarrio.setEnabled(true);
        cmbTipoBarrio.setSelectedIndex(0);
        cambiarFormularioEspecifico();

        txtEdificiosPH.setText("");
        txtPorcentajeAsfalto.setText("");
        txtNumeroLey.setText("");
        chkAguaPotable.setSelected(false);
        txtMonoblocks.setText("");
        chkConsorcioActivo.setSelected(false);

        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(false);
    }
}
