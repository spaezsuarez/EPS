package View.AfiliadoBeneficiario;

import Controllers.ControladorConsulCitas;
import Models.DatosConsulCita;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class ResulConsulCita extends JFrame implements ActionListener{
    
    private JPanel panel;
    private ControladorConsulCitas controlador;
    private JLabel Titulo = new JLabel("Datos de la cita", SwingConstants.CENTER);
    private JLabel Espe = new JLabel("Especialidad:");
    private JLabel MEspe = new JLabel();
    private JLabel TCita = new JLabel("Tipo de cita:");
    private JLabel MTCita = new JLabel();
    private JLabel MT = new JLabel("Médico tratante:");
    private JLabel MMT = new JLabel();
    private JLabel H = new JLabel("Hora:");
    private JLabel MH = new JLabel();
    private JLabel Sede = new JLabel("Sede:");
    private JLabel MSede = new JLabel();
    private JLabel Dirc = new JLabel("Dirección:");
    private JLabel MDirc = new JLabel();
    private JLabel Consul = new JLabel("Consultorio:");
    private JLabel MConsul = new JLabel();
    private JButton Sigui = new JButton("Siguiente");
    private JButton Ante = new JButton("Anterior");
    ArrayList<DatosConsulCita> arreglo = new ArrayList<DatosConsulCita>();
    
    int i = 0;
    
    public ResulConsulCita(ArrayList<DatosConsulCita> arregloCitas2){
        this.arreglo = arregloCitas2;
        initCompo();
        mostrar();
        cambiarDatos(0);
    }
    
    public void initCompo(){
        setSize(400,400); 
        setTitle("Consulta de citas");
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        controlador = new ControladorConsulCitas(arreglo);
    }
    
    public void cambiarDatos(int i){
        MMT.setText(controlador.obtenerCita(i).getMedicoTratante());
        MEspe.setText(controlador.obtenerCita(i).getEspecialidad());
        MTCita.setText(controlador.obtenerCita(i).getTipoCita());
        MH.setText(controlador.obtenerCita(i).getHora());
        MSede.setText(controlador.obtenerCita(i).getSede());
        MDirc.setText(controlador.obtenerCita(i).getDireccion());
        MConsul.setText(""+controlador.obtenerCita(i).getConsultorio());
        
    }
    
    public void mostrar(){
        Titulo.setBounds(0, 10, 400, 30);
        Titulo.setFont(new Font("Serif", Font.BOLD, 22)); 
        Titulo.setForeground(new Color(21, 67, 96));
        panel.add(Titulo);
        
        MT.setBounds(5, 45, 150, 30);
        MT.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(MT);
        
        MMT.setBounds(205, 50, 150, 20);
        MMT.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MMT);
        
        Espe.setBounds(5, 75, 150, 30);
        Espe.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(Espe);
        
        MEspe.setBounds(205, 80, 150, 20);
        MEspe.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MEspe);
        
        TCita.setBounds(5, 105, 150, 30);
        TCita.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(TCita);
        
        MTCita.setBounds(205, 110, 150, 20);
        MTCita.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MTCita);
        
        H.setBounds(5, 135, 150, 30);
        H.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(H);
        
        MH.setBounds(205, 140, 150, 20);
        MH.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MH); 
        
        Sede.setBounds(5, 165, 150, 30);
        Sede.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(Sede);
        
        MSede.setBounds(205, 170, 150, 20);
        MSede.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MSede);
        
        Dirc.setBounds(5, 195, 150, 30);
        Dirc.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(Dirc);
        
        MDirc.setBounds(205, 200, 150, 20);
        MDirc.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MDirc);
        
        Consul.setBounds(5, 225, 150, 30);
        Consul.setFont(new Font("Serif", Font.BOLD, 18)); 
        panel.add(Consul);
        
        MConsul.setBounds(205, 230, 150, 20);
        MConsul.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MConsul);
        
        Ante.setBounds(50, 290, 100, 30);
        Ante.addActionListener(this);
        panel.add(Ante);
        
        Sigui.setBounds(225, 290, 100, 30);
        Sigui.addActionListener(this);
        panel.add(Sigui);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(Ante)&& i>0){
            i--;
        }
        if (ae.getSource().equals(Sigui)){
            if (i!=controlador.getTamañoArreglo() - 1){
                i++;
            }
        }
        cambiarDatos(i);
    }
}