package View.AfiliadoBeneficiario;


import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controllers.ControladorCitas;
import Models.DatosSolicCita;
import Persistence.AfiliadoDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class SolicitarCitaParte2 extends JFrame implements ActionListener {
    public JPanel panel;
    ControladorCitas controlador;
    private AfiliadoDAO control;
    private java.util.Date fechaUtil;
    private java.sql.Date fechaSQL;
    SimpleDateFormat formatterDateSQL = new SimpleDateFormat("yyyy-MM-dd");
    
    private String TDA;
    private long iDAfiliado;
    
    JLabel MT = new JLabel("Medico tratante:");
    JLabel MMT = new JLabel();
    JLabel Fecha = new JLabel("Fecha:");
    JLabel MFecha = new JLabel();
    JLabel H = new JLabel("Hora:");
    JLabel MH = new JLabel();
    JLabel Sede = new JLabel("Sede:");
    JLabel MSede = new JLabel();
    JLabel Dirc = new JLabel("Direcci�n:");
    JLabel MDirc = new JLabel();
    JLabel Consul = new JLabel("Consultorio:");
    JLabel MConsul = new JLabel();
    JButton Sigui = new JButton("Siguiente");
    JButton Ante = new JButton("Anterior");
    JButton Confi = new JButton("Confirmar");
    
    ArrayList<DatosSolicCita> arreglo = new ArrayList<DatosSolicCita>();
    
    int i = 0;
    
    public SolicitarCitaParte2(String TDA, long iDAfiliado, ArrayList<DatosSolicCita> arregloCitas) {
        this.arreglo = arregloCitas;
        this.TDA = TDA;
        this.iDAfiliado = iDAfiliado;
        initComponentes();
        mostrar();
        cambiarDatos(0);
    }

    
    public void initComponentes(){
        setSize(400,400); 
        setTitle("Solicitar una cita");
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        controlador = new ControladorCitas(arreglo);
        control = AfiliadoDAO.getReference();
    }
    
    public void cambiarDatos(int i){
        MMT.setText(controlador.obtenerCita(i).getMedicoTratante());
        MFecha.setText(controlador.obtenerCita(i).getFecha());
        MH.setText(controlador.obtenerCita(i).getHora());
        MSede.setText(controlador.obtenerCita(i).getSede());
        MDirc.setText(controlador.obtenerCita(i).getDireccion());
        MConsul.setText(""+controlador.obtenerCita(i).getConsultorio());
    }
    
    public void mostrar(){
        
        MT.setBounds(5, 5, 150, 30);
        MT.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(MT);
        
        MMT.setBounds(205, 10, 150, 20);
        MMT.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MMT);
        
        Fecha.setBounds(5, 35, 150, 30);
        Fecha.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(Fecha);
        
        MFecha.setBounds(205, 40, 150, 20);
        MFecha.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MFecha);
        
        H.setBounds(5, 65, 150, 30);
        H.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(H);
        
        MH.setBounds(205, 70, 150, 20);
        MH.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MH); 
        
        Sede.setBounds(5, 95, 150, 30);
        Sede.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(Sede);
        
        MSede.setBounds(205, 100, 150, 20);
        MSede.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MSede);
        
        Dirc.setBounds(5, 125, 150, 30);
        Dirc.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(Dirc);
        
        MDirc.setBounds(205, 130, 150, 20);
        MDirc.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MDirc);
        
        Consul.setBounds(5, 155, 150, 30);
        Consul.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(Consul);
        
        MConsul.setBounds(205, 160, 150, 20);
        MConsul.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MConsul);
        
        Ante.setBounds(50, 220, 100, 30);
        Ante.addActionListener(this);
        panel.add(Ante);
        
        Sigui.setBounds(225, 220, 100, 30);
        Sigui.addActionListener(this);
        panel.add(Sigui);
        
        Confi.setBounds(138, 300, 100, 30);
        Confi.addActionListener(this);
        panel.add(Confi);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(Ante)&& i>0){
            i--;
            cambiarDatos(i);
        }
        if (ae.getSource().equals(Sigui)){
            if (i!=controlador.getTamañoArreglo() - 1){
                i++;
                cambiarDatos(i);
            }
        }
        if (ae.getSource().equals(Confi)){
            try {
                    fechaUtil = formatterDateSQL.parse(controlador.obtenerCita(i).getFecha());
                    fechaSQL = new java.sql.Date(fechaUtil.getTime());
                } catch (ParseException ex) {
                    System.out.println("Fallo el metodo Parse: " + ex.getMessage());
                }
            try {
                control.AñadirABCita(controlador.obtenerCita(i).getIdCita(), TDA, iDAfiliado);
                long cuota = control.CuotaPagar(iDAfiliado);
                long idPago = control.crearPagoID();
                control.CrearPagoCita(idPago, fechaSQL,controlador.obtenerCita(i).getIdCita());   
                JOptionPane.showMessageDialog(panel, "Exitoso " + "Su copago a realizar es de:  "+ cuota);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "No se pudo añadir", "Estado consulta", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

