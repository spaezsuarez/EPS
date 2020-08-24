package View.AfiliadoBeneficiario;

import Persistence.AfiliadoDAO;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class CancelarCita extends JFrame {
    
    private Panel panel;
    private AfiliadoDAO control;
    private long iDAfiliadoRef;
    
    public CancelarCita(long iDAfiliado) {
        iDAfiliadoRef = iDAfiliado;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Cancelar cita");
        setSize(new Dimension(660, 475));
        GenerarPanel();
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
        control = AfiliadoDAO.getReference();
    }
    
    public void GenerarPanel() {
        panel = new Panel();
        setContentPane(panel);
        panel.setLayout(null);
        panel.GenerarInterfaz();
    }
    
    public class Panel extends JPanel implements ActionListener, ItemListener {
        
        private JLabel titulo;
        private JLabel fechaCita;
        private JLabel elijaCita;
        private JLabel idCita;
        private JFormattedTextField textFieldDate;
        private JTextField textFieldHoraCita;
        private JButton botonConsultar;
        private JButton botonVolver;
        private JButton botonConfirmar;
        private MaskFormatter formatterDate = null;
        SimpleDateFormat formatterDateSQL = new SimpleDateFormat("yyyy-MM-dd");
        private JComboBox citas;
        private java.util.Date fechaUtil;
        private java.sql.Date fechaSQL;
        
        public Panel() {
            setBorder(BorderFactory.createStrokeBorder(new BasicStroke(7.0f)));
            setPreferredSize(new Dimension(660, 475));
        }
        
        public void GenerarInterfaz() {
            titulo = new JLabel("CANCELAR UNA CITA");
            fechaCita = new JLabel("Elija la fecha de la cita:");
            elijaCita = new JLabel("ID y tipo de su cita:");
            idCita = new JLabel("Hora de inicio de su cita:");
            try {
                formatterDate = new MaskFormatter("####-##-##");
                textFieldDate = new JFormattedTextField(formatterDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            textFieldHoraCita = new JTextField();
            botonConsultar = new JButton("Consultar citas");
            botonVolver = new JButton("Volver");
            botonConfirmar = new JButton("Confirmar");
            citas = new JComboBox();
            DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
            listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
            citas.setRenderer(listRenderer);
            citas.addItemListener(this);
            titulo.setSize(600, 100);
            titulo.setLocation(35, 0);
            titulo.setFont(new Font("font", Font.BOLD, 25));
            titulo.setHorizontalAlignment(SwingConstants.CENTER);
            fechaCita.setSize(330, 70);
            fechaCita.setLocation(10, 110);
            fechaCita.setFont(new Font("font", Font.BOLD, 25));
            fechaCita.setHorizontalAlignment(SwingConstants.CENTER);
            elijaCita.setSize(330, 70);
            elijaCita.setLocation(10, 230);
            elijaCita.setFont(new Font("font", Font.BOLD, 25));
            elijaCita.setHorizontalAlignment(SwingConstants.CENTER);
            idCita.setSize(330, 70);
            idCita.setLocation(10, 310);
            idCita.setFont(new Font("font", Font.BOLD, 25));
            idCita.setHorizontalAlignment(SwingConstants.CENTER);
            textFieldDate.setSize(250, 40);
            textFieldDate.setLocation(360, 127);
            textFieldDate.setFont(new Font("font", Font.BOLD, 25));
            textFieldDate.setHorizontalAlignment(SwingConstants.CENTER);
            textFieldHoraCita.setSize(250, 40);
            textFieldHoraCita.setLocation(360, 327);
            textFieldHoraCita.setFont(new Font("font", Font.BOLD, 25));
            textFieldHoraCita.setHorizontalAlignment(SwingConstants.CENTER);
            textFieldHoraCita.setFocusable(false);
            botonConsultar.setSize(170, 35);
            botonConsultar.setLocation(400, 190);
            botonConsultar.setFont(new Font("font", Font.BOLD, 18));
            botonVolver.setSize(170, 35);
            botonVolver.setLocation(110, 410);
            botonVolver.setFont(new Font("font", Font.BOLD, 25));
            botonConfirmar.setSize(170, 35);
            botonConfirmar.setLocation(380, 410);
            botonConfirmar.setFont(new Font("font", Font.BOLD, 25));
            citas.setSize(250, 35);
            citas.setLocation(360, 247);
            citas.setFont(new Font("font", Font.BOLD, 18));
            botonConsultar.addActionListener(this);
            botonVolver.addActionListener(this);
            botonConfirmar.addActionListener(this);
            add(titulo);
            add(fechaCita);
            add(elijaCita);
            add(idCita);
            add(textFieldDate);
            add(textFieldHoraCita);
            add(botonConsultar);
            add(botonVolver);
            add(botonConfirmar);
            add(citas);
        }
        
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(7));
            g.drawLine(0, 105, 660, 105);
            g.drawLine(0, 380, 660, 380);
        }
        
        public java.util.Date restarUnDia(java.util.Date fecha, int horas) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            cal.add(Calendar.HOUR, -horas);
            return cal.getTime();
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals(botonConsultar.getActionCommand())) {
                ArrayList<ArrayList> arreglo = new ArrayList<ArrayList>();
                citas.removeAllItems();
                try {
                    fechaUtil = formatterDateSQL.parse(textFieldDate.getText());
                    fechaSQL = new java.sql.Date(fechaUtil.getTime());
                } catch (ParseException ex) {
                    System.out.println("Falló el método Parse: " + ex.getMessage());
                }
                try {
                    arreglo = control.consultarCitas(iDAfiliadoRef, fechaSQL);
                    if (arreglo.get(0).isEmpty() && arreglo.get(1).isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No se encontraron citas para la fecha ingresada", "Estado consulta", JOptionPane.WARNING_MESSAGE);
                        textFieldHoraCita.setText(null);
                    } else {
                        ArrayList<Integer> arregloIds = new ArrayList<Integer>();
                        ArrayList<String> arregloTipos = new ArrayList<String>();
                        arregloIds = arreglo.get(0);
                        arregloTipos = arreglo.get(1);
                        for (int i = 0; i < arregloIds.size(); i++) {
                            citas.addItem(arregloIds.get(i) + "    - - -    " + arregloTipos.get(i));
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Fallo SQL: " + ex.getMessage());
                }
            } else if (ae.getActionCommand().equals(botonVolver.getActionCommand())) {
                dispose();
            } else if (ae.getActionCommand().equals(botonConfirmar.getActionCommand())) {
                if (citas.getItemCount() != 0 && !textFieldDate.getText().equals(null)) {
                    int numeroConfirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro quiere borrar esta cita? Esta operación es irreversible", "Confirmación cancelación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (numeroConfirmacion == 0) {
                        try {
                            
                            int horas = control.getHorasCancelacion();
                            java.util.Date fechaHoy = new java.util.Date();
                            java.util.Date fechaPagoMaximo = new java.util.Date();
                            fechaPagoMaximo = restarUnDia(fechaUtil, horas);
                            
                            if (fechaHoy.before(fechaPagoMaximo)) {
                                String[] split = citas.getSelectedItem().toString().split("    - - -");
                                control.eliminarPago(Integer.valueOf(split[0]));
                                control.eliminarRegistro(Integer.valueOf(split[0]));
                                control.actualizarCita(iDAfiliadoRef, Integer.valueOf(split[0]));
                                JOptionPane.showMessageDialog(this, "Cita cancelada exitosamente", "Estado cancelación", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                            } else {
                                String[] split = citas.getSelectedItem().toString().split("    - - -");
                                control.eliminarPago(Integer.valueOf(split[0]));
                                control.eliminarRegistro(Integer.valueOf(split[0]));
                                control.actualizarCita(iDAfiliadoRef, Integer.valueOf(split[0]));
                                JOptionPane.showMessageDialog(this, "Cita cancelada exitosamente pero con un valor de multa a pagar de: " + control.getMulta(iDAfiliadoRef), "Estado cancelación", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                            }
                            
                        } catch (SQLException ex) {
                            System.out.println("Fallo SQL: " + ex.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Complete el formulario correctamente", "Estado cancelación", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        public void itemStateChanged(ItemEvent ie) {
            if (citas.getItemCount() != 0) {
                try {
                    fechaUtil = formatterDateSQL.parse(textFieldDate.getText());
                    fechaSQL = new java.sql.Date(fechaUtil.getTime());
                } catch (ParseException ex) {
                    System.out.println("Falló el método Parse: " + ex.getMessage());
                }
                try {
                    String[] split = citas.getSelectedItem().toString().split("    - - -");
                    textFieldHoraCita.setText(String.valueOf(control.consultarHora(iDAfiliadoRef, fechaSQL, Integer.valueOf(split[0]))));
                } catch (SQLException ex) {
                    System.out.println("Error SQL: " + ex.getMessage());
                }
            }
        }
    }
}
